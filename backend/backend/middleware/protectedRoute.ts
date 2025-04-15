import jwt from "jsonwebtoken";
import { Request, Response, NextFunction } from "express";
import { DecodedToken } from "../interface/DecodedToken.js";
import prisma from "../DB/prisma.js";


const protectedRoute = async (req: Request, res: Response, next: NextFunction): Promise<any> => {
    try {
        const token = req.cookies.jwt;

        // Check if token exists
        if (!token) {
            return res.status(401).json({
                success: false,
                message: "Unauthorized: No token provided"
            });
        }

        // Verify token
        const decoded = jwt.verify(token, process.env.JWT_SECRET!) as DecodedToken;
        if (!decoded) {
            return res.status(401).json({
                success: false,
                message: "Unauthorized: Invalid token"
            });
        }

        // Find user in database
        const user = await prisma.user.findUnique({
            where: { id: decoded.userId },
            select: {
                id: true,
                username: true,
                fullName: true,
                profilePic: true
            },
        });

        if (!user) {
            return res.status(404).json({
                success: false,
                message: "User not found"
            });
        }

        // Attach user to request
        req.user = user;
        next();

    } catch (error: unknown) {
        console.error("Error in protectedRoute:", error);

        if (error instanceof jwt.JsonWebTokenError) {
            return res.status(401).json({
                success: false,
                message: "Unauthorized: Invalid token"
            });
        }

        if (error instanceof jwt.TokenExpiredError) {
            return res.status(401).json({
                success: false,
                message: "Unauthorized: Token expired"
            });
        }

        return res.status(500).json({
            success: false,
            message: "Internal server error"
        });
    }
};

export default protectedRoute;