import { Request, Response } from 'express';

import prisma from "../DB/prisma.js";

export const getAllProducts = async (req: Request, res: Response) => {
    try {
        const products = await prisma.product.findMany({
            include: {
                category: true, // Include category info in the response
            },
            orderBy: {
                createdAt: 'desc', // Optional: order products by creation date
            },
        });

        res.status(200).json({
            success: true,
            message: 'Products retrieved successfully',
            data: products,
        });
    } catch (error) {
        console.error('Error fetching products:', error);
        res.status(500).json({
            success: false,
            message: 'Internal server error',
        });
    }
};

export const getProduct = async (req: Request, res: Response): Promise<any> => {
    try {
        const { id } = req.params;

        const product = await prisma.product.findUnique({
            where: { id },
            include: {
                category: true, // Include category details
            },
        });

        if (!product) {
            return res.status(404).json({
                success: false,
                message: 'Product not found',
            });
        }

        res.status(200).json({
            success: true,
            message: 'Product retrieved successfully',
            data: product,
        });
    } catch (error) {
        console.error('Error fetching product:', error);
        res.status(500).json({
            success: false,
            message: 'Internal server error',
        });
    }
};

export const addProduct = async (req: Request, res: Response): Promise<any> => {
    try {
        const { name, description, price, imageUrl, categoryId } = req.body;

        // Basic validation
        if (!name || price === undefined || !categoryId) {
            return res.status(400).json({
                success: false,
                message: 'Name, price, and categoryId are required',
            });
        }

        // Create new product
        const newProduct = await prisma.product.create({
            data: {
                name,
                description,
                price,
                imageUrl,
                categoryId,
            },
        });

        res.status(201).json({
            success: true,
            message: 'Product created successfully',
            data: newProduct,
        });
    } catch (error) {
        console.error('Error adding product:', error);
        res.status(500).json({
            success: false,
            message: 'Internal server error',
        });
    }
};

export const updateProduct = async (req: Request, res: Response): Promise<any> => {
    try {
        const { id } = req.params;
        const { name, description, price, imageUrl, categoryId } = req.body;

        // Check if product exists
        const existingProduct = await prisma.product.findUnique({
            where: { id },
        });

        if (!existingProduct) {
            return res.status(404).json({
                success: false,
                message: 'Product not found',
            });
        }

        // Update product
        const updatedProduct = await prisma.product.update({
            where: { id },
            data: {
                name: name ?? existingProduct.name,
                description: description ?? existingProduct.description,
                price: price ?? existingProduct.price,
                imageUrl: imageUrl ?? existingProduct.imageUrl,
                categoryId: categoryId ?? existingProduct.categoryId,
            },
        });

        return res.status(200).json({
            success: true,
            message: 'Product updated successfully',
            data: updatedProduct,
        });
    } catch (error) {
        console.error('Error updating product:', error);
        return res.status(500).json({
            success: false,
            message: 'Internal server error',
        });
    }
};

export const deleteProduct = async (req: Request, res: Response): Promise<any> => {
    try {
        const { id } = req.params;

        // Check if the product exists
        const existingProduct = await prisma.product.findUnique({
            where: { id },
        });

        if (!existingProduct) {
            res.status(404).json({
                success: false,
                message: 'Product not found',
            });
        }

        // Delete product
        await prisma.product.delete({
            where: { id },
        });

        res.status(200).json({
            success: true,
            message: 'Product deleted successfully',
        });
    } catch (error) {
        console.error('Error deleting product:', error);
        res.status(500).json({
            success: false,
            message: 'Internal server error',
        });
    }
};

