package model.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

	@Entity
	@Table(name = "alunos")
	public class Aluno implements Serializable {

		@Id
		private String cpf;
		private String nome;
		private String email;
		@Column(name = "dt_nascimento")
		private Date dtNascimento;
		@ManyToOne
		@JoinColumn(name = "id_curso")
		private Curso curso;
		@OneToOne(cascade = CascadeType.ALL)
		@JoinColumn(name = "id_endereco")
		private Endereco endereco;

		public Aluno() {}

		public Aluno(String nome, String cpf, String email, Date dtNascimento, Curso curso, Endereco endereco) {
			this.nome = nome;
			this.email = email;
			this.cpf = cpf;
			this.dtNascimento = dtNascimento;
			this.curso = curso;
			this.endereco = endereco;
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

	public String getEmail(){return email;}

	public Endereco getEndereco(){return endereco;}
	
	@Override
	public String toString() {
		return this.nome;
	}

		public void setEndereco(Endereco endereco) { this.endereco = endereco;
		}

		public void setDataNascimento(Date dataNascimento) { this.dtNascimento = dataNascimento;
		}

		public void setEmail(String email) { this.email = email;
		}

		public Date getDataNascimento() { return this.dtNascimento;
		}
	}
