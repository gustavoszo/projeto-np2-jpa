package model.entities;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

public class Disciplina {
	
	private Integer id_disciplina;
	private String nome;
	private int cargaHoraria;
	
	@OnDelete(action = OnDeleteAction.CASCADE)
	@OneToMany
	@JoinColumn(name = "id_curso")
	private Curso curso;
	
	public Disciplina() {}
	
	public Disciplina(Integer id, String nome, int cargaHoraria, Curso curso) {
		this.id_disciplina = id;
		this.nome = nome;
		this.cargaHoraria = cargaHoraria;
		this.curso = curso;
	}

	public Integer getId() {
		return id_disciplina;
	}

	public void setId(Integer id) {
		this.id_disciplina = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getCargaHoraria() {
		return cargaHoraria;
	}

	public void setCargaHoraria(int cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
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
