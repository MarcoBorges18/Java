package com.marcola.projetinho.spring.view;

import java.util.Scanner;

import org.springframework.stereotype.Service;

import com.marcola.projetinho.spring.model.Unidade;
import com.marcola.projetinho.spring.repository.UnidadeRepository;

@Service
public class CrudUnidadeService {
    
    private Boolean menu = true;

    private UnidadeRepository repository;

    public CrudUnidadeService(UnidadeRepository repository) {
        this.repository = repository;
    }

    public void inicial(Scanner scan) {
        while (menu) {
            System.out.println("""
                    \nEscolha uma opção
                    0 - Voltar
                    1 - Salvar
                    2 - Alterar
                    3 - Visualizar Todos as Unidades
                    4 - Buscar por Id
                    5 - Deletar Unidade
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
        System.out.println("Descrição da unidade");
        String descricao = scan.nextLine();
        System.out.println("Digite o endereço da unidade");
        String endereco = scan.nextLine();
        Unidade unidade = new Unidade();
        unidade.setDescricao(descricao);
        unidade.setEndereco(endereco);
        repository.save(unidade);
        System.out.println("Salvo");
    }

    private void atualizar(Scanner scan){
        System.out.println("Digite o ID da unidade que deseja alterar");
        int id = scan.nextInt();
        scan.nextLine();
        System.out.println("Digite a nova descrição que deseja inserir");
        String descricao = scan.nextLine();
        System.out.println("Digite o endereço da unidade");
        String endereco = scan.nextLine();
        Unidade unidade = new Unidade();
        unidade.setId(id);
        unidade.setDescricao(descricao);
        unidade.setEndereco(endereco);
        repository.save(unidade);
        System.out.println("Informações atualizadas");
    }

    public void visualizar(){
        Iterable<Unidade> unidades = repository.findAll();
        unidades.forEach(u -> System.out.println(u));
    }

    private void visualizarPorId(Scanner scan){
        System.out.println("Digite o ID que deseja procurar");
        int i = scan.nextInt();
        scan.nextLine();
        Unidade unidade = buscarUnidadePorId(i);
        System.out.println(unidade);
    }

    private void deletar(Scanner scan){
        System.out.println("Digite o Id da unidade que deseja deletar");
        int i = scan.nextInt();
        scan.nextLine();
        repository.deleteById(i);
        System.out.println("Unidade deletada!");
    }
    
    public Unidade buscarUnidadePorId(int i){
        Unidade unidade = repository.findById(i).get();
        return unidade;
    }
}
