import express from 'express'
import authRoutes from '../routes/auth.Routes.js'
import { PORT } from './env.js'
import dotenv from 'dotenv'
import categoryRoutes from "../routes/category.Routes.js";
import productRoutes from "../routes/product.Routes.js";

dotenv.config();

const app = express();


app.use(express.json());

app.use('/api/auth', authRoutes)

app.use('/api/category', categoryRoutes)

app.use('/api/product', productRoutes)

app.listen(PORT, () => {
    console.log(`Running on http://localhost:${ PORT }`);
});


