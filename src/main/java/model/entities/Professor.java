package model.entities;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
@Entity
@Table(name= "professores")
public class Professor implements Serializable {
	
	@Id
	private String cpf;
	private String nome;
	private String email;
	@Column(name= "dt_nascimento")
	private Date dtNascimento;
	@ManyToOne
	@JoinColumn(name= "id_curso")
	private Curso curso;
	@ManyToOne
	@JoinColumn(name= "id_disciplina")
	private Disciplina disciplina;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name= "id_endereco")
	private Endereco endereco;
	
	public Professor() {}
	
	public Professor(String cpf, String nome, String email, Date dtNascimento, Curso curso, Disciplina disciplina,
			Endereco endereco) {
		this.nome = nome;
		this.cpf = cpf;
		this.dtNascimento = dtNascimento;
		this.curso = curso;
		this.disciplina = disciplina;
		this.endereco = endereco;
		this.email = email;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setProfessor(String cpf, String nome, String email, Date dtNascimento, Endereco endereco, Curso curso, Disciplina disciplina) {
		this.setNome(nome);
		this.setCpf(cpf);
		this.setDisciplina(disciplina);
		this.setDtNascimento(dtNascimento);
		this.setEmail(email);
		this.setEndereco(endereco);
		this.setCurso(curso);
	}

}
