package model;

import java.util.ArrayList;
import java.util.List;

public class Biblioteca {

    private final List<Livro> acervo;

    private static int totalBibliotecas = 0;

    public Biblioteca() {
        this.acervo = new ArrayList<>();
        totalBibliotecas++;
    }

    public void adicionarLivro(Livro livro) {
        acervo.add(livro);
    }

    public boolean removerLivro(String isbn) {
        return acervo.removeIf(livro -> livro.getIsbn().equals(isbn));
    }

    public Livro buscarLivroPorIsbn(String isbn) {
        for (Livro livro : acervo) {
            if (livro.getIsbn().equals(isbn)) {
                return livro;
            }
        }
        return null;
    }

    public List<Livro> listarTodosOsLivros() {
        return new ArrayList<>(acervo);
    }

    public boolean emprestarLivro(String isbn) {
        Livro livro = buscarLivroPorIsbn(isbn);
        if (livro != null && !livro.isEmprestado()) {
            livro.emprestar();
            return true;
        }
        return false;
    }

    public boolean devolverLivro(String isbn) {
        Livro livro = buscarLivroPorIsbn(isbn);
        if (livro != null && livro.isEmprestado()) {
            livro.devolver();
            return true;
        }
        return false;
    }

    public List<Livro> buscarLivrosPorAutor(String autor) {
        List<Livro> encontrados = new ArrayList<>();
        for (Livro livro : acervo) {
            if (livro.getAutor().equalsIgnoreCase(autor)) {
                encontrados.add(livro);
            }
        }
        return encontrados;
    }

    public List<Livro> listarLivrosEmprestados() {
        List<Livro> emprestados = new ArrayList<>();
        for (Livro livro : acervo) {
            if (livro.isEmprestado()) {
                emprestados.add(livro);
            }
        }
        return emprestados;
    }

    public List<Livro> listarLivrosDisponiveis() {
        List<Livro> disponiveis = new ArrayList<>();
        for (Livro livro : acervo) {
            if (!livro.isEmprestado()) {
                disponiveis.add(livro);
            }
        }
        return disponiveis;
    }

    public void exibirEstatisticasEmprestimos() {
        System.out.println("Total de livros: " + acervo.size());
        System.out.println("Livros emprestados: " + listarLivrosEmprestados().size());
        System.out.println("Livros disponíveis: " + listarLivrosDisponiveis().size());
    }

    public static int getTotalBibliotecas() {
        return totalBibliotecas;
    }

    public int getTotalDeLivrosCadastrados() {
        return acervo.size();
    }
}