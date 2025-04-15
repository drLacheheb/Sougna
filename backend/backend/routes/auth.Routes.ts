import express from "express"
import {getMe, login, logout, signup} from "../controllers/auth.controller.js";
import protectedRoute from "../middleware/protectedRoute.js";

const router = express.Router();

router.get("/me", protectedRoute ,getMe)

router.post("/login", login)

router.post("/logout", logout)

router.post("/signup", signup)

export default router;