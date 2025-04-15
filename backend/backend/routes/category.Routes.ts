import express from "express";
import { addCategory,  deleteCategory, getAllCategories, getCategory, updateCategory } from "../controllers/category.controller.js";

const router = express.Router();

router.get('/categories', getAllCategories);

router.get('/category/:id', getCategory);

router.post('/category', addCategory);

router.put('/category/:id', updateCategory);

router.delete('/category/:id', deleteCategory);

export default router;