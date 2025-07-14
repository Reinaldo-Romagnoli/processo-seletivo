export enum TipoPessoa {
  FISICA = 'FISICA',
  JURIDICA = 'JURIDICA'
}

export interface Endereco {
  cep: string;
  logradouro: string;
  cidade: string;
  estado: string;
  numero: string;
  complemento?: string;
}

export interface Contact {
  id: number;
  nome: string;
  cpf?: string;
  cnpj?: string;
  email: string;
  telefone?: string;
  tipoPessoa: TipoPessoa;
  endereco: Endereco;
}

export interface ContactRequest {
  nome: string;
  cpf?: string | null;
  cnpj?: string | null;
  email: string;
  telefone?: string;
  tipoPessoa: TipoPessoa;
  endereco: {
    cep: string;
    logradouro: string;
    cidade: string;
    estado: string;
    numero: string;
    complemento?: string;
  };
}