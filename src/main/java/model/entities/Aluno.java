package model.entities;

import java.util.Date;

public class Aluno {
	
	private Integer id_aluno;
	private String nome;
	private String cpf;
	private Date dtNascimento;
	private Curso curso;
	
	public Aluno() {}
	
	public Aluno(Integer id, String nome, String cpf, Date dtNascimento, Curso curso) {
		this.id_aluno = id;
		this.nome = nome;
		this.cpf = cpf;
		this.dtNascimento = dtNascimento;
		this.curso = curso;
	}

	public Integer getId() {
		return id_aluno;
	}

	public void setId(Integer id) {
		this.id_aluno = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Date getDtNascimento() {
		return dtNascimento;
	}

	public void setDtNascimento(Date dtNascimento) {
		this.dtNascimento = dtNascimento;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}
	
	@Override
	public String toString() {
		return this.nome;
	}

}
