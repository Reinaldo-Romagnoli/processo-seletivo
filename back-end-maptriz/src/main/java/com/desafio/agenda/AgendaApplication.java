package com.desafio.agenda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
		scanBasePackages = {
				"com.desafio.agenda",
				"Agendador.example.Agendador"
		}
)
public class AgendaApplication {
	public static void main(String[] args) {
		SpringApplication.run(AgendaApplication.class, args);
	}
}