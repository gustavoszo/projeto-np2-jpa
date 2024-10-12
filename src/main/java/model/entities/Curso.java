package model.entities;

import java.util.List;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Table(name = "cursos")
@Entity
public class Curso implements Serializable {

	@OneToMany(mappedBy = "curso")
	List<Disciplina> disciplinas;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_curso")
	private Integer idCurso;

	@Column
	private String nome;
	
	@Column
	private String periodo;
	
	public Curso() {}
	
	public Curso(Integer id, String nome, String periodo) {
		this.idCurso = id;
		this.nome = nome;
		this.periodo = periodo;
	}

	public Integer getId() {
		return idCurso;
	}

	public void setId(Integer id) {
		this.idCurso = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	
	@Override
	public String toString() {
		return this.nome + " | " + this.periodo;
	}

}
