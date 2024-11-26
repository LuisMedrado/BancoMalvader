package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

import dao.ConnectionFactory;

public abstract class Usuario {

    // Declarando Atributos
    protected int id_usuario;
    protected String nome_usuario;
    protected String cpf_usuario;
    protected LocalDate dataNascimento_usuario;
    protected String telefone_usuario;
    protected Endereco endereco;

    // Construtor
    public Usuario(int id_usuario, String nome_usuario, String cpf_usuario, LocalDate dataNascimento_usuario,
	    String telefone_usuario, Endereco endereco) {
	super();
	this.id_usuario = id_usuario;
	this.nome_usuario = nome_usuario;
	this.cpf_usuario = cpf_usuario;
	this.dataNascimento_usuario = dataNascimento_usuario;
	this.telefone_usuario = telefone_usuario;
	this.endereco = endereco;
    }

    // Getters e Setters
    // Getter e setter ---> ID USUARIO
    public int getId_usuario() {
	return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
	this.id_usuario = id_usuario;
    }

    // Getter e setter ---> NOME USUARIO
    public String getNome_usuario() {
	return nome_usuario;
    }

    public void setNome_usuario(String nome_usuario) {
	this.nome_usuario = nome_usuario;
    }

    // Getter e setter ---> CPF USUARIO
    public String getCpf_usuario() {
	return cpf_usuario;
    }

    public void setCpf_usuario(String cpf_usuario) {
	this.cpf_usuario = cpf_usuario;
    }

    // Getter e setter ---> DATA DE NASCIMENTO
    public LocalDate getDataNascimento_usuario() {
	return dataNascimento_usuario;
    }

    public void setDataNascimento_usuario(LocalDate dataNascimento_usuario) {
	this.dataNascimento_usuario = dataNascimento_usuario;
    }

    // Getter e setter ---> TELEFONE USUARIO
    public String getTelefone_usuario() {
	return telefone_usuario;
    }

    public void setTelefone_usuario(String telefone_usuario) {
	this.telefone_usuario = telefone_usuario;
    }

    // Getter e setter ---> ENDERECO
    public Endereco getEndereco() {
	return endereco;
    }

    public void setEndereco(Endereco endereco) {
	this.endereco = endereco;
    }

    // Métodos
    public boolean login(String senha) {
    	return false;
    }

    public static boolean validarLogin(String cpf, String senha, String tipoUsuario) {
	// A consulta verifica se o CPF, senha e tipo de usuário correspondem
	String sql = "SELECT id_usuario FROM usuario WHERE cpf = ? AND senha = ? AND tipo_usuario = ?";

	try (Connection conn = ConnectionFactory.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

	    stmt.setString(1, cpf);
	    stmt.setString(2, senha);
	    stmt.setString(3, tipoUsuario);

	    ResultSet rs = stmt.executeQuery();
	    return rs.next(); // Retorna true se encontrar o usuário
	} catch (Exception e) {
	    e.printStackTrace();
	    return false;
	}
    }

    public String logout() {
    	return "";
    }

    public String consultarDados() {
	return "Usuario [id_usuario=" + id_usuario + ", nome_usuario=" + nome_usuario + ", cpf_usuario=" + cpf_usuario
		+ ", dataNascimento_usuario=" + dataNascimento_usuario + ", telefone_usuario=" + telefone_usuario
		+ ", endereco=" + endereco.getBairro() + " " + endereco.getCep() + " " + endereco.getCidade() + " "
		+ endereco.getEstado() + " " + endereco.getLocal() + " " + endereco.getNumeroCasa() + "]";
    }

}
