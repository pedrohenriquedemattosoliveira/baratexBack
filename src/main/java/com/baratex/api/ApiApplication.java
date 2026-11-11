package com.baratex.api;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class ApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

	// Código temporário para criar a coluna que falta no PostgreSQL
	@Bean
	public CommandLineRunner criarColunaUsuarioSeNaoExistir(JdbcTemplate jdbcTemplate) {
		return args -> {
			try {
				jdbcTemplate.execute("ALTER TABLE oficinas ADD COLUMN IF NOT EXISTS usuario_id UUID REFERENCES usuarios(id);");
				System.out.println(">>> Coluna usuario_id verificada/criada com sucesso na tabela oficinas! <<<");
			} catch (Exception e) {
				System.err.println(">>> Erro ao tentar criar coluna usuario_id: " + e.getMessage());
			}
		};
	}
}