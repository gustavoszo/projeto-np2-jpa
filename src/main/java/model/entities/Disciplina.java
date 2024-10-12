package model.entities;

import java.io.Serializable;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Table(name = "disciplinas")
@Entity
public class Disciplina implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_disciplina")
	private Integer idDisciplina;

	@Column
	private String nome;
	
	@Column(name = "carga_horaria")
	private int cargaHoraria;
	
	@ManyToOne
	@JoinColumn(name = "id_curso")
	private Curso curso;
	
	public Disciplina() {}
	
	public Disciplina(Integer id, String nome, int cargaHoraria, Curso curso) {
		this.idDisciplina = id;
		this.nome = nome;
		this.cargaHoraria = cargaHoraria;
		this.curso = curso;
	}

	public Integer getId() {
		return idDisciplina;
	}

	public void setId(Integer id) {
		this.idDisciplina = id;
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
