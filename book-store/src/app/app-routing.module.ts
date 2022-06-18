import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthorFormComponent } from './author/author-form/author-form.component';
import { AuthorListComponent } from './author/author-list/author-list.component';
import { AuthorComponent } from './author/author.component';

const routes: Routes = [
  { path: '', component: AuthorComponent},  
  { path: 'list', component: AuthorListComponent },  
  { path: 'form', component: AuthorFormComponent },   
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
