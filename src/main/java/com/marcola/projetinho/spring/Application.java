package com.marcola.projetinho.spring;

import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.marcola.projetinho.spring.view.CrudCargoService;
import com.marcola.projetinho.spring.view.CrudFuncionarioService;
import com.marcola.projetinho.spring.view.CrudUnidadeService;
import com.marcola.projetinho.spring.view.RelatioriosService;

@SpringBootApplication
public class Application implements CommandLineRunner {

	private Boolean system = true;

	private final CrudCargoService cargoService;
	private final CrudUnidadeService unidadeService;
	private final CrudFuncionarioService funcionarioService;
	private final RelatioriosService relatioriosService;

	public Application(CrudCargoService cargoService, CrudUnidadeService unidadeService, CrudFuncionarioService funcionarioService, RelatioriosService relatioriosService) {
		this.cargoService = cargoService;
		this.unidadeService = unidadeService;
		this.funcionarioService = funcionarioService;
		this.relatioriosService = relatioriosService;
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner scan = new Scanner(System.in);

		while (system) {
			System.out.println("""
					\nMenu:
					0 - Sair
					1 - Cargo
					2 - Unidade
					3 - Funcionário
					4 - Relatórios
					""");
			int a = scan.nextInt();
			scan.nextLine();
			switch (a) {
				case 0:
					System.out.println("Finalizando");
					system = false;
					break;
				case 1:
					cargoService.inicial(scan);
					break;
				case 2:
					unidadeService.inicial(scan);
					break;
				case 3:
					funcionarioService.inicial(scan);
					break;
				case 4:
					relatioriosService.inicial(scan);
					break;
				default:
					System.out.println("Opção inexistente");
					break;
			}
		}
	}

}
