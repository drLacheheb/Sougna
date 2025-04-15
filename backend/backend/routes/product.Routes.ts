import express from "express";
import { addProduct, deleteProduct, getAllProducts, getProduct, updateProduct } from "../controllers/product.controller.js";


const router = express.Router();

router.get('/product', getAllProducts);

router.get('/product/:id', getProduct);

router.post('/product', addProduct);

router.put('/product/:id', updateProduct);

router.delete('/product/:id', deleteProduct);

export default router;