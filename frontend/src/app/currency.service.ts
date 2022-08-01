import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CurrencyService {
  private apiServerUrl = environment?.apiBaseUrl??'';

  constructor(private http: HttpClient) { }

  public getCurrencySymbols(): Observable<string[]> {
    return this.http.get<string[]>(`${this.apiServerUrl}/symbols`);
  }

  public convertCurrency(from: string, to: string, amount: number): Observable<number> {

    let params = new HttpParams()
    .set('from', from)
    .set('to', to)
    .set('amount', amount);

    return this.http.get<number>(`${this.apiServerUrl}/convert`, { params: params });
  }
}
