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

@Entity
@Table(name = "cursos")
public class Curso implements Serializable {

	@OneToMany(mappedBy = "curso")
	private List<Disciplina> disciplinas;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_curso")
	private Integer idCurso;

	private String nome;
	private Integer semestres;
	private String periodo;
	
	public Curso() {}
	
	public Curso(Integer id, String nome, Integer semestres, String periodo) {
		this.idCurso = id;
		this.nome = nome;
		this.semestres = semestres;
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
	
	public Integer getSemestres() {
		return this.semestres;
	}

	public void setSemestres(Integer semestres) {
		this.semestres = semestres;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	
	public List<Disciplina> getDisciplinas() {
		return disciplinas;
	}

	public void setDisciplinas(List<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}

	@Override
	public String toString() {
		return this.nome + " | " + this.periodo;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Curso curso = (Curso) o;

		// Compare pelo atributo que torna o curso único, como o ID ou o nome
		return idCurso != null ? idCurso.equals(curso.idCurso) : curso.idCurso == null;
	}

	@Override
	public int hashCode() {
		return idCurso != null ? idCurso.hashCode() : 0;
	}
	
}
