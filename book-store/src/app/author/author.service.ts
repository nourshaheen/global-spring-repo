import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AuthorDto } from './dto/author-dto';

@Injectable({
  providedIn: 'root'
})
export class AuthorService {

  constructor(private httpClient: HttpClient) { }

   baseUrl : string = 'http://localhost:8080'

  getAll(){
   return this.httpClient.get(this.baseUrl + '/auther');
  }

  getById(id:number){
    return this.httpClient.get(this.baseUrl + '/auther/' + id );
  }

  addAuthor (author: AuthorDto){

    return this.httpClient.post(this.baseUrl + '/auther', author)
  }


  updateAuthor (author: AuthorDto){

    return this.httpClient.put(this.baseUrl + '/auther', author)
  }
}
