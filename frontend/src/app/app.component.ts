import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { CurrencyService } from './currency.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  public symbols: string[];
  public currencyFrom: string;
  public currencyTo: string;
  public currencyOutput: string;

  constructor(private currencyService: CurrencyService) { }

  ngOnInit(): void {
    this.getCurrencySymbols();
    this.currencyOutput = '';
    
    this.currencyService.getCurrencySymbols().subscribe(
      (response: string[]) => {
        this.currencyFrom = this.symbols[0];
        this.currencyTo = this.symbols[1];
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public getCurrencySymbols(): void {
    this.currencyService.getCurrencySymbols().subscribe(
      (response: string[]) => {
        this.symbols = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public convertCurrency(from: string, to: string, amount: number): void {
    this.currencyService.convertCurrency(from, to, amount).subscribe(
      (response: number) => {
        this.currencyOutput = response + ' ' + this.currencyTo;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public onClickCurrencyFromSelection(selectedSymbol: string): void {
    this.currencyFrom = selectedSymbol;
  }

  public onClickCurrencyToSelection(selectedSymbol: string): void {
    this.currencyTo = selectedSymbol;
  }

  public onClickConvertCurrency(): void {

    var inputValue = (<HTMLInputElement>document.getElementById("AmountInput")).value;

    var numberValue = Number(inputValue);

    if (inputValue.length === 0) {
      this.currencyOutput = 'Error. Amount is empty.';
    } else if(Number.isNaN(numberValue)) {
      this.currencyOutput = 'Error. Amount must be a number.';
    } else {
      this.currencyOutput = 'Please wait...';
      this.convertCurrency(this.currencyFrom, this.currencyTo, numberValue);
    }
  }
}
