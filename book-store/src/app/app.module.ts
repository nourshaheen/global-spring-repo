import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';  
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AuthorComponent } from './author/author.component';
import { AuthorListComponent } from './author/author-list/author-list.component';
import { AuthorFormComponent } from './author/author-form/author-form.component';

@NgModule({
  declarations: [
    AppComponent,
    AuthorComponent,
    AuthorListComponent,
    AuthorFormComponent
    ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
