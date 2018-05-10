package br.uema.application.entities;

import javax.persistence.*;

@Entity
@Table(name = "usuario", schema = "public")
public class Usuario extends EntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer id;

    @Column (name = "login", nullable = false, length = 45, unique = true)
    private String login;

    @Column (name = "senha", nullable = false)
    private String senha;

    @Column (name = "nome", length = 50)
    private String nome;

    @Column (name = "ativo", nullable = false, columnDefinition = "boolean default true")
    private Boolean ativo;

    @Column (name = "email", length = 100)
    private String email;

    @Column (name = "token_senha", columnDefinition = "TEXT")
    private String tokenSenha;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTokenSenha() {
        return tokenSenha;
    }

    public void setTokenSenha(String tokenSenha) {
        this.tokenSenha = tokenSenha;
    }
}
