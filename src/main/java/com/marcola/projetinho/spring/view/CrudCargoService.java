package com.marcola.projetinho.spring.view;

import java.util.Scanner;
import org.springframework.stereotype.Service;
import com.marcola.projetinho.spring.model.Cargo;
import com.marcola.projetinho.spring.repository.CargoRepository;

@Service
public class CrudCargoService {

    private Boolean menu = true;

    private final CargoRepository repository;

    public CrudCargoService(CargoRepository repository) {
        this.repository = repository;
    }

    public void inicial(Scanner scan) {
        while (menu) {
            System.out.println("""
                    \nEscolha uma opção
                    0 - Voltar
                    1 - Salvar
                    2 - Alterar
                    3 - Visualizar Todos os Cargos
                    4 - Buscar por Id
                    5 - Deletar Cargo
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
        System.out.println("Descrição do Cargo");
        String descricao = scan.nextLine();
        Cargo cargo = new Cargo();
        cargo.setNomeCargo(descricao);
        repository.save(cargo);
        System.out.println("Salvo");
    }

    private void atualizar(Scanner scan){
        System.out.println("Digite o ID do objeto que deseja alterar");
        int id = scan.nextInt();
        scan.nextLine();
        System.out.println("Digite o novo nome que deseja inserir");
        String nome = scan.nextLine();
        Cargo cargo = new Cargo();
        cargo.setId(id);
        cargo.setNomeCargo(nome);
        repository.save(cargo);
        System.out.println("Informações atualizadas");
    }

    public void visualizar(){
        Iterable<Cargo> cargos = repository.findAll();
        cargos.forEach(s -> System.out.println(s));
    }

    private void visualizarPorId(Scanner scan){
        System.out.println("Digite o ID que deseja procurar");
        int i = scan.nextInt();
        scan.nextLine();
        Cargo cargo = buscarCargoId(i);
        System.out.println(cargo);
    }

    private void deletar(Scanner scan){
        System.out.println("Digite o Id do Cargo que deseja deletar");
        int i = scan.nextInt();
        scan.nextLine();
        repository.deleteById(i);
        System.out.println("Cargo deletado");
    }

    public Cargo buscarCargoId(int i){
        Cargo cargo = repository.findById(i).get();
        return cargo;
    }
}
