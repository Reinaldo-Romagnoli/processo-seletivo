import { Component, OnInit, ViewChild }      from '@angular/core';
import { CommonModule }                       from '@angular/common';
import { FormsModule }                        from '@angular/forms';
import { RouterModule, Router }               from '@angular/router';

// Angular Material
import { MatToolbarModule }                   from '@angular/material/toolbar';
import { MatTabsModule }                      from '@angular/material/tabs';
import { MatCardModule }                      from '@angular/material/card';
import { MatFormFieldModule }                 from '@angular/material/form-field';
import { MatInputModule }                     from '@angular/material/input';
import { MatTableModule, MatTableDataSource } from '@angular/material/table';
import { MatPaginatorModule, MatPaginator }   from '@angular/material/paginator';
import { MatIconModule }                      from '@angular/material/icon';
import { MatButtonModule }                    from '@angular/material/button';
import { MatSnackBarModule, MatSnackBar }     from '@angular/material/snack-bar';

import { ContactService }                     from '../../services/contact.service';
import { Contact }                            from '../../models/contact.model';

@Component({
  selector: 'app-contact-list',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    RouterModule,

    // Material
    MatToolbarModule,
    MatTabsModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatTableModule,
    MatPaginatorModule,
    MatIconModule,
    MatButtonModule,
    MatSnackBarModule
  ],
  templateUrl: './contact-list.component.html',
  styleUrls: ['./contact-list.component.scss']
})
export class ContactListComponent implements OnInit {
  displayedColumns = ['nome', 'email', 'telefone', 'ações'];
  dataSource = new MatTableDataSource<Contact>();
  filtroNome = '';

  @ViewChild(MatPaginator) paginator!: MatPaginator;

  constructor(
    private service: ContactService,
    private router: Router,
    private snack: MatSnackBar
  ) {}

  ngOnInit() {
    this.loadData();
  }

  loadData(pageIndex = 0) {
    this.service.list(this.filtroNome, pageIndex).subscribe(page => {
      this.dataSource.data = page.content;
      this.paginator.length = page.totalElements;
    });
  }

  filtrar() {
    this.paginator.pageIndex = 0;
    this.loadData();
  }

  changePage() {
    this.loadData(this.paginator.pageIndex);
  }

  novo() {
    this.router.navigate(['contatos/novo']);
  }

  editar(contato: any) {
    this.router.navigate(['/contatos', contato.id]); // supondo que sua rota de edição seja '/contatos/:id'
  }

  deletar(contato: Contact) {
    if (!confirm(`Excluir ${contato.nome}?`)) return;
    this.service.delete(contato.id).subscribe(() => {
      this.snack.open('Contato excluído e notificação enviada', 'OK', { duration: 3000 });
      this.loadData(this.paginator.pageIndex);
    });
  }
}