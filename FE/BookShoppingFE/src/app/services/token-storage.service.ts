import { Injectable } from "@angular/core";
import { Cart } from "../models/cart";
import { CartItem } from "../models/cart-item";

const TOKEN_KEY = "auth-token";
const USER_KEY = "auth-user";

@Injectable({
  providedIn: "root",
})
export class TokenStorageService {
  constructor() {}

  signOut() {
    window.sessionStorage.clear();
  }

  isLogin() {
    window.sessionStorage.setItem("isLogin", String(true));
  }

  checkIsLogin() {
    return Boolean(sessionStorage.getItem("isLogin"));
  }

  isLogout() {
    window.sessionStorage.removeItem("isLogin");
    window.sessionStorage.removeItem(TOKEN_KEY);
  }

  public saveToken(token: string) {
    window.sessionStorage.removeItem(TOKEN_KEY);
    window.sessionStorage.setItem(TOKEN_KEY, token);
  }

  public getToken(): string {
    return sessionStorage.getItem(TOKEN_KEY);
  }

  public saveUser(user: any) {
    window.sessionStorage.removeItem(USER_KEY);
    window.sessionStorage.setItem(USER_KEY, JSON.stringify(user));
  }

  public getUser() {
    return JSON.parse(sessionStorage.getItem(USER_KEY));
  }
}
