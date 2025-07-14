import { Component, OnInit } from '@angular/core';
import { CommonModule }      from '@angular/common';
import {
  ReactiveFormsModule,
  FormBuilder,
  Validators,
  FormGroup
} from '@angular/forms';
import {
  RouterModule,
  ActivatedRoute,
  Router
} from '@angular/router';

import { MatFormFieldModule }    from '@angular/material/form-field';
import { MatInputModule }        from '@angular/material/input';
import { MatSelectModule }       from '@angular/material/select';
import { MatButtonModule }       from '@angular/material/button';
import {
  MatSnackBarModule,
  MatSnackBar
} from '@angular/material/snack-bar';
import { MatIconModule } from '@angular/material/icon';

import { ContactService }        from '../../services/contact.service';
import { ContactRequest, TipoPessoa } from '../../models/contact.model';
import { MatCard, MatCardTitle, MatCardModule } from "@angular/material/card";
import { M } from "../../../../node_modules/@angular/material/option.d-BcvS44bt";

@Component({
  selector: 'app-contact-form',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    RouterModule,
    // Material
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatButtonModule,
    MatSnackBarModule,
    MatCard,
    MatCardTitle,
    MatIconModule,
    M,
    MatCardModule
],
  templateUrl: './contact-form.component.html',
  styleUrls: ['./contact-form.component.scss']
})
export class ContactFormComponent implements OnInit {
  form!: FormGroup;
  idContato?: number;
  tipoPessoaOptions = Object.values(TipoPessoa);

  constructor(
    private fb: FormBuilder,
    private service: ContactService,
    private route: ActivatedRoute,
    private router: Router,
    private snack: MatSnackBar
  ) {}

  ngOnInit() {
    // monta o form, incluindo o sub-grupo 'endereco'
    this.form = this.fb.group({
      nome:       ['', Validators.required],
      cpf:        [''],
      cnpj:       [''],
      email:      ['', [Validators.required, Validators.email]],
      telefone:   [''],
      tipoPessoa: [null, Validators.required],
      endereco: this.fb.group({
        cep:        ['', Validators.required],
        logradouro: ['', Validators.required],
        cidade:     ['', Validators.required],
        estado:     ['', Validators.required],
        numero:     ['', Validators.required],
        complemento:['']
      })
    });

    // se for edição, busca e popula
    this.route.paramMap.subscribe(params => {
      const id = params.get('id');
      if (id) {
        this.idContato = +id;
        this.service.getById(this.idContato).subscribe(c => {
          this.form.patchValue(c);
        });
      }
    });
  }

  submit() {
    if (this.form.invalid) return;

    const dto: any = this.form.value;

    // Normalize CPF/CNPJ
    if (dto.cpf) dto.cpf = dto.cpf.replace(/\D/g, '');
    if (dto.cnpj) dto.cnpj = dto.cnpj.replace(/\D/g, '');

    // Remover campo não relevante de acordo com o tipo de pessoa
    if (dto.tipoPessoa === 'FISICA') {
      delete dto.cnpj;
    } else if (dto.tipoPessoa === 'JURIDICA') {
      delete dto.cpf;
    }

    console.log('Enviando DTO:', dto);

    const obs = this.idContato
      ? this.service.update(this.idContato, dto)
      : this.service.create(dto);

    obs.subscribe({
      next: () => {
        const msg = this.idContato
          ? 'Contato atualizado com sucesso'
          : 'Contato criado com sucesso';
        this.snack.open(msg, 'OK', { duration: 3000 });
        this.router.navigate(['/contatos']);
      },
      error: err => {
        console.error('Erro ao salvar contato:', err);
      }
    });
  }

  cancelar() {
    this.router.navigate(['/contatos']);
  }
}
