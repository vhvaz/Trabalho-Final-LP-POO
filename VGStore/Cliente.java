package Entidades;

public class Cliente extends Pessoa {
    private String email;
    private String senha;
    private String cpf;

    // Construtor completo
    public Cliente(int id, String nome, String email, String senha, String cpf) {
        super(id, nome); // Chamada para o construtor da superclasse Pessoa
        this.email = email;
        this.senha = senha;
        this.cpf = cpf;
    }

    // Construtor sem ID para fins de teste
    public Cliente(String email, String nome, String senha, String cpf) {
        super(0, nome); // Para fins de teste, atribuímos 0 como ID
        this.email = email;
        this.senha = senha;
        this.cpf = cpf;
    }

    // Getters e setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
