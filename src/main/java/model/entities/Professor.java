package model.entities;

import java.util.Date;

public class Professor {
	
	private Integer id_professor;
	private String nome;
	private String cpf;
	private Date dtNascimento;
	private Curso curso;
	private Disciplina disciplina;
	private Endereco endereco;
	
	public Professor() {}
	
	public Professor(Integer id, String nome, String cpf, Date dtNascimento, Curso curso, Disciplina disciplina,
			Endereco endereco) {
		this.id_professor = id;
		this.nome = nome;
		this.cpf = cpf;
		this.dtNascimento = dtNascimento;
		this.curso = curso;
		this.disciplina = disciplina;
		this.endereco = endereco;
	}

	public Integer getId() {
		return id_professor;
	}

	public void setId(Integer id) {
		this.id_professor = id;
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

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	@Override
	public String toString() {
		return this.nome;
	}

}
