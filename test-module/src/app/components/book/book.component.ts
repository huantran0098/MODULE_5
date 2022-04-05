import { Component, OnInit } from '@angular/core';
import {BookService} from "../../service/book.service";
import {Book} from "../../models/book";
import {FormBuilder, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-book',
  templateUrl: './book.component.html',
  styleUrls: ['./book.component.css']
})
export class BookComponent implements OnInit {
  books?: Book[]
  bookForm!: FormGroup
  book?: Book
  constructor(private bookService : BookService,
              private formGroup : FormBuilder) { }

  ngOnInit(): void {
    this.bookForm = this.formGroup.group({
      id : [''],
      title : [''],
      author : [''],
      description : ['']
    });
    this.getAllBooks();
  }

  getAllBooks() {
    this.bookService.getAllBooks().subscribe(data => {
      this.books = data;
      // @ts-ignore
      document.getElementById('formTitle').innerText = 'Create';
      // @ts-ignore
      document.getElementById('title').innerText = 'Create new product';
    })
  }
  getBookDetail(id : any){
    this.bookService.getBookDetail(id).subscribe((data) => {
      this.books = [];
      this.books.push(data)
    });
  }

  createBook() {
    const newBook = {
      id :this.bookForm.value.id,
      title : this.bookForm.value.title,
      author : this.bookForm.value.author,
      description : this.bookForm.value.description
    };
    if (newBook.id){
      this.bookService.updateBook(newBook.id, newBook).subscribe(data => {
        alert('edit successfully')
      })
    } else {
      this.bookService.createBook(newBook).subscribe(() => {
          alert('create successfully')
      })
    }
    this.bookForm.reset();
    this.getAllBooks()
  }

  editBook(id : any) {
    this.bookService.getBookDetail(id).subscribe(data => this.bookForm?.patchValue(data));
    // @ts-ignore
    document.getElementById('formTitle').innerText = 'Edit';
    // @ts-ignore
    document.getElementById('title').innerText = 'Edit product';
    this.getAllBooks();
  }
  deleteBook(id : any){
    if(confirm('Are you sure delete this book ? ')){
      this.bookService.deleteBook(id).subscribe(() =>{
        alert('delete successfully');
        this.getAllBooks()
      })
    }
  }
}
