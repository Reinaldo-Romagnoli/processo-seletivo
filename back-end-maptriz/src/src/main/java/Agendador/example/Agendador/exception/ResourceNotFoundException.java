package Agendador.example.Agendador.exception;

public class ResourceNotFoundException extends RuntimeException {
  public ResourceNotFoundException(String mensagem) {
    super(mensagem);
  }
}