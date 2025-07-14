import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

import { Contact, ContactRequest } from '../models/contact.model';
import { Page } from '../models/page.model';

@Injectable({ providedIn: 'root' })
export class ContactService {
  private baseUrl = 'http://localhost:8080/api/contatos';

  constructor(private http: HttpClient) {}

  list(
    nameFilter: string = '',
    page: number = 0,
    size: number = 10,
    sort: string = 'nome,asc'
  ): Observable<Page<Contact>> {
    let params = new HttpParams()
      .set('page', page.toString())
      .set('size', size.toString())
      .set('sort', sort);

    if (nameFilter) {
      // o controller usa @RequestParam("nome")
      params = params.set('nome', nameFilter);
    }

    return this.http.get<Page<Contact>>(this.baseUrl, { params });
  }

  /**
   * Busca um único Contact por ID
   */
  getById(id: number): Observable<Contact> {
    return this.http.get<Contact>(`${this.baseUrl}/${id}`);
  }

  /**
   * Cria um novo Contact a partir de ContactRequest
   */
  create(dto: ContactRequest): Observable<Contact> {
    return this.http.post<Contact>(this.baseUrl, dto);
  }

  /**
   * Atualiza um Contact já existente
   */
  update(id: number, dto: ContactRequest): Observable<Contact> {
    return this.http.put<Contact>(`${this.baseUrl}/${id}`, dto);
  }

  /**
   * Exclui um Contact pelo ID
   */
  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }
}