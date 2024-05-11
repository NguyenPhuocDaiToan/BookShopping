import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { LoginRequest } from "../models/login-request";
import { RegisterRequest } from "../models/register-request";
import { Observable } from "rxjs";
import { ResponseMessage } from "../models/response-message";
const API_URL = "https://bookrecommendation.website/auth";
@Injectable({
  providedIn: "root",
})
export class AuthenService {
  constructor(private http: HttpClient) {}

  login(loginRequest: LoginRequest): Observable<ResponseMessage> {
    return this.http.post<ResponseMessage>(API_URL + "/login", loginRequest);
  }

  register(registerRequest: RegisterRequest): Observable<ResponseMessage> {
    return this.http.post<ResponseMessage>(
      API_URL + "/register",
      registerRequest
    );
  }
}
