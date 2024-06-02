import { Injectable } from "@angular/core";
import { environment } from "../../../environments/environment";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";

const API_URL = `${environment.apiurl}` + "/rating-recommendation";

@Injectable({
  providedIn: "root",
})
export class UserBookRatingService {
  constructor(private http: HttpClient) {}

  updateRating(
    userId: number,
    bookId: number,
    rating: number
  ): Observable<any> {
    return this.http.post<any>(API_URL + "/modify", {
      userId,
      bookIds: [bookId],
      ratingRecommendations: [rating],
    });
  }
}
