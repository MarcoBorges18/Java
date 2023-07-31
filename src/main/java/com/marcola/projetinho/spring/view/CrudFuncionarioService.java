package com.marcola.projetinho.spring.view;

import java.util.Scanner;

import org.springframework.stereotype.Service;

import com.marcola.projetinho.spring.model.Cargo;
import com.marcola.projetinho.spring.model.Funcionario;
import com.marcola.projetinho.spring.model.Unidade;
import com.marcola.projetinho.spring.repository.FuncionarioRepository;

@Service
public class CrudFuncionarioService {
 
    private Boolean menu = true;

    private final FuncionarioRepository repository;
    private final CrudUnidadeService unidadeService;
    private final CrudCargoService cargoService;


    public CrudFuncionarioService(FuncionarioRepository repository, CrudUnidadeService unidadeService, CrudCargoService cargoService) {
        this.repository = repository;
        this.unidadeService = unidadeService;
        this.cargoService = cargoService;
    }

    public void inicial(Scanner scan) {
        while (menu) {
            System.out.println("""
                    \nEscolha uma opção
                    0 - Voltar
                    1 - Salvar
                    2 - Alterar
                    3 - Visualizar Todos os Funcionário
                    4 - Buscar por Id
                    5 - Deletar Funcionário
                    """);
            int i = scan.nextInt();
            scan.nextLine();
            System.out.println("\n");
            switch (i) {
                case 0:
                    menu = false;
                    break;
                case 1:
                    salvar(scan);
                    break;
                case 2:
                    atualizar(scan);
                    break;
                case 3:
                    visualizar();
                    break;
                case 4:
                    visualizarPorId(scan);
                    break;
                case 5:
                    deletar(scan);
                    break;
                default:
                    System.out.println("Opção inexistente");
                    break;
            }
        }
    }

    private void salvar(Scanner scan) {
        System.out.println("Nome do Funcionário: ");
        String nome = scan.nextLine();
        System.out.println("CPF do Funcionário: ");
        String cpf = scan.nextLine();
        System.out.println("Salário do Funcionário: ");
        Double salario = scan.nextDouble();

        cargoService.visualizar();
        System.out.println("\nDigite a ID do Cargo que deseja cadastrar para esse funcionário");
        int idCargo = scan.nextInt();
        Cargo cargo = cargoService.buscarCargoId(idCargo);

        System.out.println("\n");

        unidadeService.visualizar();
        System.out.println("\nDigite a ID da Unidade que deseja cadastrar o funcionário");
        int idUnidade = scan.nextInt();
        Unidade unidade = unidadeService.buscarUnidadePorId(idUnidade);

        Funcionario funcionario = new Funcionario();
        funcionario.setNome(nome);
        funcionario.setCpf(cpf);
        funcionario.setSalario(salario);
        funcionario.setCargo(cargo);
        funcionario.setUnidade(unidade);

        repository.save(funcionario);
        System.out.println("\nSalvo");
    }

    private void atualizar(Scanner scan){
        System.out.println("Digite o ID do objeto que deseja alterar");
        int id = scan.nextInt();
        scan.nextLine();
        
        System.out.println("Novo nome do Funcionário: ");
        String nome = scan.nextLine();
        System.out.println("Novo CPF do Funcionário: ");
        String cpf = scan.nextLine();
        System.out.println("Novo salário do Funcionário: ");
        Double salario = scan.nextDouble();

        cargoService.visualizar();
        System.out.println("Digite a ID do Cargo que deseja atualizar para esse funcionário");
        int idCargo = scan.nextInt();
        Cargo cargo = cargoService.buscarCargoId(idCargo);

        unidadeService.visualizar();
        System.out.println("Digite a ID da Unidade que deseja atualizar o funcionário");
        int idUnidade = scan.nextInt();
        Unidade unidade = unidadeService.buscarUnidadePorId(idUnidade);

        Funcionario funcionario = new Funcionario();
        funcionario.setId(id);
        funcionario.setNome(nome);
        funcionario.setCpf(cpf);
        funcionario.setSalario(salario);
        funcionario.setCargo(cargo);
        funcionario.setUnidade(unidade);

        repository.save(funcionario);
        System.out.println("Informações atualizadas");
    }

    private void visualizar(){
        Iterable<Funcionario> funcionario = repository.findAll();
        funcionario.forEach(f -> System.out.println(f));
    }

    private Funcionario visualizarPorId(Scanner scan){
        System.out.println("Digite o ID que deseja procurar");
        int i = scan.nextInt();
        scan.nextLine();
        Funcionario funcionario = repository.findById(i).get();
        System.out.println(funcionario);
        return funcionario;
    }

    private void deletar(Scanner scan){
        System.out.println("Digite o Id do Funcionário que deseja deletar");
        int i = scan.nextInt();
        scan.nextLine();
        repository.deleteById(i);
        System.out.println("Funcionário deletado");
    }
}
