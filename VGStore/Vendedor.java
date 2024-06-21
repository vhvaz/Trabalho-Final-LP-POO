package Entidades;

public class Vendedor extends Pessoa {
    private String email;
    private String senha;

    public Vendedor(int id, String nome, String email, String senha) {
        super(id, nome); // Chamada para o construtor da superclasse Pessoa
        this.email = email;
        this.senha = senha;
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
}
