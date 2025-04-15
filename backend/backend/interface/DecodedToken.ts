import {JwtPayload} from "jsonwebtoken";

export interface DecodedToken  extends JwtPayload{
    userId: string
}

declare global {
    namespace Express {
        export interface Request {
            user?: {
                id: string;
            };
        }
    }
}