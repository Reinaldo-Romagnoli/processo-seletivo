package Agendador.example.Agendador;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
		scanBasePackages = {
				"com.desafio.agenda",
				"Agendador.example.Agendador"
		}
)
public class AgendadorApplication {

	public static void main(String[] args) {
		SpringApplication.run(AgendadorApplication.class, args);
	}

}
