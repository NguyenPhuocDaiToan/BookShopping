import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {CartItem} from '../../models/cart-item';
import {ResponseMessage} from '../../models/response-message';

const API_URL = 'http://localhost:8080/api/cart';

@Injectable({
  providedIn: 'root'
})
export class CartService {

  constructor(private http: HttpClient) {
  }

  getCartItemByUserId(id: number): Observable<CartItem[]> {
    return this.http.get<CartItem[]>(API_URL + '/getCartByUserId?id=' + id);
  }

  addToCart(amount: number, cartId: number, bookId: number): Observable<ResponseMessage> {
    return this.http.get<ResponseMessage>(API_URL + '/addToCart?'
                                                      + 'amount=' + amount
                                                      + '&cartId=' + cartId
                                                      + '&bookId=' + bookId);
  }

  updateCartItem(amount: number, cartItemId: number, bookId: number): Observable<ResponseMessage> {
    return this.http.get<ResponseMessage>(API_URL + '/updateCartItem?'
                                                      + 'amount=' + amount
                                                      + '&cartItemId=' + cartItemId
                                                      + '&bookId=' + bookId
    );
  }

  deleteCartItem(cartItemId: number): Observable<ResponseMessage> {
    return this.http.delete<ResponseMessage>(API_URL + '/delete?cartItemId=' + cartItemId);
  }


  synchronizedCart(cartId: number, cartItemRequests): Observable<ResponseMessage> {
    return this.http.post<ResponseMessage>(API_URL + '/synchronizedCart?cartId=' + cartId, cartItemRequests)
  }

}
