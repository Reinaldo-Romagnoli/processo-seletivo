// src/app/app-routing.module.ts
import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ContactListComponent } from './components/contact-list/contact-list.component';
import { ContactFormComponent } from './components/contact-form/contact-form.component';

export const appRoutes: Routes = [
  { path: '',           component: ContactListComponent },
  { path: 'contatos/novo', component: ContactFormComponent },
  { path: 'contatos/:id', component: ContactFormComponent },
  { path: '**',         redirectTo: '' }
];

export class AppRoutingModule {}
