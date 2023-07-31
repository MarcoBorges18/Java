package com.marcola.projetinho.spring.view;

import java.util.List;
import java.util.Scanner;
import org.springframework.stereotype.Service;
import com.marcola.projetinho.spring.model.Funcionario;
import com.marcola.projetinho.spring.repository.FuncionarioRepository;

@Service
public class RelatioriosService {
    
    private Boolean menu = true;

    private final FuncionarioRepository funcionarioRepository;
    
    public RelatioriosService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public void inicial(Scanner scan) {
        while (menu) {
            System.out.println("""
                    \nEscolha uma opção
                    0 - Voltar
                    1 - Busca Funcionário Por Nome;
                    """);
            int i = scan.nextInt();
            scan.nextLine();
            System.out.println("\n");
            switch (i) {
                case 0:
                    menu = false;
                    break;
                case 1:
                    buscaFuncionarioNome(scan);
                    break;
                default:
                    System.out.println("Opção inexistente");
                    break;
            }
        }
    }

    private void buscaFuncionarioNome(Scanner scan){
        System.out.println("Digite o nome que deseja procurar");
        String nome = scan.nextLine();
        String nomeConcatenado = "%" + nome + "%";
        List<Funcionario> list = funcionarioRepository.findByNomeLike(nomeConcatenado);
        list.forEach(f -> System.out.println(f));
    }
}
