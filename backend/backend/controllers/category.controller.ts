import {Response, Request} from "express";

import prisma from "../DB/prisma.js";


export const getAllCategories = async (req: Request, res: Response) => {
    try {
        const categories = await prisma.category.findMany({
            include: {
                products: true // Include related products if needed
            },
            orderBy: {
                createdAt: 'desc' // Optional: order newest first
            }
        });

        res.status(200).json({
            success: true,
            message: 'Categories retrieved successfully',
            data: categories
        });
    } catch (error) {
        console.error('Error fetching categories:', error);
        res.status(500).json({
            success: false,
            message: 'Internal server error'
        });
    }
};

export const getCategory = async (req: Request, res: Response): Promise<any> => {
    try {
        const { id } = req.params;

        // Fetch category by ID
        const category = await prisma.category.findUnique({
            where: { id },
            include: {
                products: true // include products related to this category
            }
        });

        if (!category) {
            return res.status(404).json({
                success: false,
                message: 'Category not found',
            });
        }

        return res.status(200).json({
            success: true,
            message: 'Category retrieved successfully',
            data: category,
        });

    } catch (error) {
        console.error('Error fetching category:', error);
        return res.status(500).json({
            success: false,
            message: 'Internal server error',
        });
    }
};

export const addCategory = async (req: Request, res: Response) => {
    try {
        const { name, description, icon } = req.body;

        // Basic validation
        if (!name || !description || icon === undefined) {
            res.status(400).json({
                success: false,
                message: 'Name, description, and icon are required',
            });
        }

        // Check for existing category with the same name
        const existingCategory = await prisma.category.findUnique({
            where: { name },
        });

        if (existingCategory) {
            res.status(409).json({
                success: false,
                message: 'Category with this name already exists',
            });
        }

        // Create new category
        const newCategory = await prisma.category.create({
            data: {
                name,
                description,
                icon,
            },
        });

        res.status(201).json({
            success: true,
            message: 'Category created successfully',
            data: newCategory,
        });
    } catch (error) {
        console.error('Error adding category:', error);
        res.status(500).json({
            success: false,
            message: 'Internal server error',
        });
    }
};

export const updateCategory = async (req: Request, res: Response): Promise<any> => {
    try {
        const { id } = req.params;
        const { name, description, icon } = req.body;

        // Check if the category exists
        const existingCategory = await prisma.category.findUnique({
            where: { id },
        });

        if (!existingCategory) {
            return res.status(404).json({
                success: false,
                message: 'Category not found',
            });
        }

        // Optional: check for duplicate name if name is being updated
        if (name && name !== existingCategory.name) {
            const duplicate = await prisma.category.findUnique({
                where: { name },
            });

            if (duplicate) {
                return res.status(409).json({
                    success: false,
                    message: 'Another category with this name already exists',
                });
            }
        }

        // Update the category
        const updatedCategory = await prisma.category.update({
            where: { id },
            data: {
                name: name ?? existingCategory.name,
                description: description ?? existingCategory.description,
                icon: icon ?? existingCategory.icon,
            },
        });

        return res.status(200).json({
            success: true,
            message: 'Category updated successfully',
            data: updatedCategory,
        });
    } catch (error) {
        console.error('Error updating category:', error);
        return res.status(500).json({
            success: false,
            message: 'Internal server error',
        });
    }
};

export const deleteCategory = async (req: Request, res: Response): Promise<any> => {
    try {
        const { id } = req.params;

        // Check if the category exists
        const existingCategory = await prisma.category.findUnique({
            where: { id },
        });

        if (!existingCategory) {
            return res.status(404).json({
                success: false,
                message: 'Category not found',
            });
        }

        // Optionally: check if category has products before deleting
        const relatedProducts = await prisma.product.findMany({
            where: { categoryId: id },
        });

        if (relatedProducts.length > 0) {
            return res.status(400).json({
                success: false,
                message: 'Cannot delete category with associated products',
            });
        }

        // Delete the category
        await prisma.category.delete({
            where: { id },
        });

        return res.status(200).json({
            success: true,
            message: 'Category deleted successfully',
        });

    } catch (error) {
        console.error('Error deleting category:', error);
        return res.status(500).json({
            success: false,
            message: 'Internal server error',
        });
    }
};