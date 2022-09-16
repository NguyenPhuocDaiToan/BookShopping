import {AfterViewChecked, Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {CartStorageService} from './services/cart-storage.service';
import {render} from 'creditcardpayments/creditCardPayments';
import {environment} from '../environments/environment.prod';

declare var paypal;

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  constructor(private cartStorageService: CartStorageService, private el: ElementRef) {
  }

  ngOnInit(): void {
    this.cartStorageService.loadCart();
  }
}
