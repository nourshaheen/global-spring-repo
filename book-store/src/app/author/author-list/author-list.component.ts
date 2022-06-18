import { Component, OnInit } from '@angular/core';
import { AuthorService } from '../author.service';
import { AuthorDto } from '../dto/author-dto';

@Component({
  selector: 'app-author-list',
  templateUrl: './author-list.component.html',
  styleUrls: ['./author-list.component.css']
})
export class AuthorListComponent implements OnInit {

  constructor(private authorService: AuthorService) {

  }

  authors: any;  

  pageTitle :string | undefined ;

  ngOnInit(): void {
    this.getAll();

    setTimeout(() =>{  
      this.pageTitle = 'List of Authors From TS';  
    }, 5000);  
  } 

  

  getAll() {
    this.authorService.getAll().subscribe(res => {

      this.authors = res;

      console.log("in next method  response is === >> " , res);

      console.log("in next method  authors is === >> " , this.authors);

    }, error => console.log(' get all error is ', error)), () => {
      console.log('get all complate method');
    }

  }

}
