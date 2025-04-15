import {Request, Response} from "express";
import prisma from "../DB/prisma.js";
import bcryptjs from "bcryptjs";
import generateToken from "../utils/generateToken.js";
import bcrypt from "bcryptjs";

export const login = async (req: Request, res: Response): Promise<any> => {
    try {
        const { username, password } = req.body;

        // Validate input
        if (!username || !password) {
            return res.status(400).json({
                success: false,
                message: 'Username and password are required',
            });
        }

        // Find user with case-insensitive username match
        const user = await prisma.user.findFirst({
            where: {
                username: {
                    equals: username.trim().toLowerCase(),
                    mode: 'insensitive',
                },
            },
        });

        // Handle user not found
        if (!user) {
            return res.status(401).json({
                success: false,
                message: 'Invalid credentials',
            });
        }

        // Now TypeScript knows user is not null

        // Verify password
        const isPasswordValid = await bcrypt.compare(password, user.password);
        if (!isPasswordValid) {
            return res.status(401).json({
                success: false,
                message: 'Invalid credentials',
            });
        }

        // Generate token
        generateToken(user.id, res);

        // Respond with user data (excluding sensitive info)
        return res.status(200).json({
            success: true,
            message: 'Login successful',
            user: {
                id: user.id,
                fullName: user.fullName,
                username: user.username,
                email: user.email,
                profilePic: user.profilePic,
                createdAt: user.createdAt,
            },
        });

    } catch (error) {
        console.error('Login error:', error);
        return res.status(500).json({
            success: false,
            message: 'Internal server error',
        });
    }
};

export const logout = (req: Request, res: Response) => {
    res.cookie("jwt", "", {
        maxAge: 0,
        httpOnly: true,
        sameSite: "strict",
        secure: process.env.NODE_ENV === "production",
    });

    res.status(200).json({ message: "Logged out successfully" });
};

export const signup = async (req: Request, res: Response) => {
    try {
        const { fullName, username, email, password, confirmPassword, gender } = req.body;
        if (!fullName || !username || !email || !password || !confirmPassword || !gender) {
            res.status(400).json({ error: "Please fill in all fields" });
        }
        if (password !== confirmPassword) {
            res.status(400).json({ error: "Passwords don't match" });
        }
        const user = await prisma.user.findUnique({ where: { username } });
        if (user) {
            res.status(400).json({ error: "Username already exists" });
        }
        const salt = await bcryptjs.genSalt(10);
        const hashedPassword = await bcryptjs.hash(password, salt);

        const boyProfilePic = `https://avatar.iran.liara.run/public/boy?username=${username}`;
        const girlProfilePic = `https://avatar.iran.liara.run/public/girl?username=${username}`;
        const newUser = await prisma.user.create({
            data: {
                fullName,
                username,
                email,
                password: hashedPassword,
                gender,
                profilePic: gender === "male" ? boyProfilePic : girlProfilePic,
            },
        });
        if (newUser) {
            // generate token in a sec
            generateToken(newUser.id, res);
            res.status(201).json({
                id: newUser.id,
                fullName: newUser.fullName,
                username: newUser.username,
                email: newUser.email,
                profilePic: newUser.profilePic,
            });
        }
        else {
            res.status(400).json({ error: "Invalid user data" });
        }
    }
    catch (error) {
        console.log("Error in signup controller", error);
        res.status(500).json({ error: "Internal Server Error" });
    }
}

export const getMe = async (req: Request, res: Response): Promise<void> => {
    try {
        if (!req.user) {
            res.status(401).json({ error: "Unauthorized: No user found in request" });
            return;
        }

        const user = await prisma.user.findUnique({
            where: { id: req.user.id },
            select: {
                id: true,
                fullName: true,
                email: true,
                username: true,
                profilePic: true,
            },
        });

        if (!user) {
            res.status(404).json({ error: "User not found" })
            return;
        }

        res.status(200).json(user);
    } catch (error) {
        console.error("Error while getting user:", error);
        res.status(500).json({ error: "Internal Server Error" });
    }
};