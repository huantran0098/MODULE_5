import { Injectable } from '@angular/core';
import {environment} from "../../environments/environment.prod";
import {Observable} from "rxjs";
import {Book} from "../models/book";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class BookService {
  private API = environment.API_SERVER ;
  constructor(private httpClient : HttpClient) { }

  getAllBooks() : Observable<Book[]>{
    return this.httpClient.get<Book[]>(this.API)
  }
  getBookDetail(id : number) : Observable<Book>{
    return this.httpClient.get<Book>(this.API + `${id}`)
  }
  createBook(book: Book): Observable<any>{
    return this.httpClient.post(this.API,book);
  }
  deleteBook(id: number): Observable<any>{
    return this.httpClient.delete(this.API + `/${id}`)
  }
  updateBook(id: number, book: Book): Observable<any>{
    return this.httpClient.put(this.API + `/${id}`,book);
  }


}
