import model.Biblioteca;
import model.Livro;

import java.util.List;
import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcMain;
        Biblioteca minhaBiblioteca = new Biblioteca();

        do {
            System.out.println("\n === Menu de Gestão da Biblioteca === ");
            System.out.println("[1] Gestão de livros");
            System.out.println("[2] Gestão de empréstimos");
            System.out.println("[3] Relatórios e consultas");
            System.out.println("[4] Administração do sistema");
            System.out.println("[5] Sair");
            System.out.print("Escolha sua opção: ");
            opcMain = scanner.nextInt();
            scanner.nextLine();

            switch (opcMain) {
                case 1 -> gestaoLivros(minhaBiblioteca);
                case 2 -> menuGestaoDeEmprestimos(minhaBiblioteca);
                case 3 -> menuRelatoriosEConsultas(minhaBiblioteca);
                case 4 -> menuAdministracao(minhaBiblioteca);
                case 5 -> System.out.println("Saindo do sistema...");
                default -> System.out.println("Opção inválida! Tente novamente.");
            }

        } while (opcMain != 5);
    }

    public static void gestaoLivros(Biblioteca biblioteca) {
        int opcLivros;

        do {
            System.out.println("\n===== Menu de Gestão de Livros =====");
            System.out.println("[1] Cadastrar");
            System.out.println("[2] Editar");
            System.out.println("[3] Remover");
            System.out.println("[4] Listar");
            System.out.println("[5] Buscar por ISBN");
            System.out.println("[6] Voltar ao Menu Principal");
            System.out.print("Escolha sua opção: ");
            opcLivros = scanner.nextInt();
            scanner.nextLine();

            switch (opcLivros) {
                case 1 -> cadastrarNovoLivro(biblioteca);
                case 2 -> editarLivro(biblioteca);
                case 3 -> removerLivro(biblioteca);
                case 4 -> listarLivros(biblioteca.listarTodosOsLivros(), "Todos os Livros");
                case 5 -> buscarLivroPorIsbn(biblioteca);
                case 6 -> System.out.println("Voltando ao menu principal...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcLivros != 6);
    }

    private static void cadastrarNovoLivro(Biblioteca biblioteca) {
        System.out.print("Digite o ISBN: ");
        String isbn = scanner.nextLine();
        if (biblioteca.buscarLivroPorIsbn(isbn) != null) {
            System.out.println("Já existe um livro com esse ISBN.");
            return;
        }
        System.out.print("Digite o Título: ");
        String titulo = scanner.nextLine();
        System.out.print("Digite o Autor: ");
        String autor = scanner.nextLine();
        biblioteca.adicionarLivro(new Livro(isbn, titulo, autor));
        System.out.println("Livro cadastrado com sucesso!");
    }

    private static void editarLivro(Biblioteca biblioteca) {
        System.out.print("Digite o ISBN do livro: ");
        String isbn = scanner.nextLine();
        Livro livro = biblioteca.buscarLivroPorIsbn(isbn);
        if (livro == null) {
            System.out.println("Livro não encontrado.");
            return;
        }
        System.out.print("Novo título (Enter para manter): ");
        String titulo = scanner.nextLine();
        if (!titulo.isBlank()) livro.setTitulo(titulo);

        System.out.print("Novo autor (Enter para manter): ");
        String autor = scanner.nextLine();
        if (!autor.isBlank()) livro.setAutor(autor);

        System.out.println("Livro atualizado com sucesso.");
    }

    private static void removerLivro(Biblioteca biblioteca) {
        System.out.print("Digite o ISBN do livro: ");
        String isbn = scanner.nextLine();
        if (biblioteca.removerLivro(isbn)) {
            System.out.println("Livro removido.");
        } else {
            System.out.println("Livro não encontrado.");
        }
    }

    private static void buscarLivroPorIsbn(Biblioteca biblioteca) {
        System.out.print("Digite o ISBN: ");
        String isbn = scanner.nextLine();
        Livro livro = biblioteca.buscarLivroPorIsbn(isbn);
        if (livro != null) {
            System.out.println(livro.gerarDescricao());
        } else {
            System.out.println("Livro não encontrado.");
        }
    }

    private static void listarLivros(List<Livro> livros, String titulo) {
        System.out.println("\n--- " + titulo + " ---");
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro encontrado.");
        } else {
            for (Livro livro : livros) {
                System.out.println(livro.gerarDescricao());
            }
        }
    }

    private static void menuGestaoDeEmprestimos(Biblioteca biblioteca) {
        int opc;
        do {
            System.out.println("\n===== Gestão de Empréstimos =====");
            System.out.println("[1] Registrar Empréstimo");
            System.out.println("[2] Registrar Devolução");
            System.out.println("[3] Listar Livros Emprestados");
            System.out.println("[4] Listar Livros Disponíveis");
            System.out.println("[5] Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            opc = scanner.nextInt();
            scanner.nextLine();

            switch (opc) {
                case 1 -> registrarEmprestimo(biblioteca);
                case 2 -> registrarDevolucao(biblioteca);
                case 3 -> listarLivros(biblioteca.listarLivrosEmprestados(), "Livros Emprestados");
                case 4 -> listarLivros(biblioteca.listarLivrosDisponiveis(), "Livros Disponíveis");
                case 5 -> System.out.println("Retornando...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opc != 5);
    }

    private static void registrarEmprestimo(Biblioteca biblioteca) {
        System.out.print("Digite o ISBN: ");
        String isbn = scanner.nextLine();
        if (biblioteca.emprestarLivro(isbn)) {
            System.out.println("Livro emprestado.");
        } else {
            System.out.println("Não foi possível emprestar o livro.");
        }
    }

    private static void registrarDevolucao(Biblioteca biblioteca) {
        System.out.print("Digite o ISBN: ");
        String isbn = scanner.nextLine();
        if (biblioteca.devolverLivro(isbn)) {
            System.out.println("Livro devolvido.");
        } else {
            System.out.println("Não foi possível devolver o livro.");
        }
    }

    private static void menuRelatoriosEConsultas(Biblioteca biblioteca) {
        int opc;
        do {
            System.out.println("\n===== Relatórios e Consultas =====");
            System.out.println("[1] Relatório Completo do Acervo");
            System.out.println("[2] Buscar Livros por Autor");
            System.out.println("[3] Estatísticas de Empréstimos");
            System.out.println("[4] Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            opc = scanner.nextInt();
            scanner.nextLine();

            switch (opc) {
                case 1 -> listarLivros(biblioteca.listarTodosOsLivros(), "Relatório Completo");
                case 2 -> buscarPorAutor(biblioteca);
                case 3 -> biblioteca.exibirEstatisticasEmprestimos();
                case 4 -> System.out.println("Retornando...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opc != 4);
    }

    private static void buscarPorAutor(Biblioteca biblioteca) {
        System.out.print("Digite o nome do autor: ");
        String autor = scanner.nextLine();
        listarLivros(biblioteca.buscarLivrosPorAutor(autor), "Livros de " + autor);
    }

    private static void menuAdministracao(Biblioteca biblioteca) {
        System.out.println("\n===== Administração do Sistema =====");
        System.out.println("Total de Livros Cadastrados: " + biblioteca.getTotalDeLivrosCadastrados());
        System.out.println("Total de Bibliotecas Criadas: " + Biblioteca.getTotalBibliotecas());
        System.out.println("Pressione Enter para voltar...");
        scanner.nextLine();
    }
}