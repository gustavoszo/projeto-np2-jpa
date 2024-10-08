package controllers;

import java.util.List;

import model.entities.Curso;
import model.exceptions.ValidationException;
import model.services.CursoService;

public class CursoController {
	
	private CursoService service;
	
	public CursoController() {
		service = new CursoService();
	}
	
	public void save(Curso curso) {
		validateForm(curso);
		
		service.save(curso);
	}
	
	public List<Curso> findAll() {
		return service.findAll();
	}
	
	public void delete(Curso curso) {
		service.delete(curso);
	}
	
	private void validateForm(Curso curso) {
		ValidationException exception = new ValidationException("Campos inválidos");
		boolean valid = true;
		
		if (curso.getNome() == null) {
			exception.setError("nome", "Selecione o nome do curso");
			valid = false;
		}
		
		if (curso.getPeriodo() == null) {
			exception.setError("periodo", "Selecione o periodo");
			valid = false;
		}
		
		if (!valid) throw exception;
	}
	
	// Getters e Setters
	public CursoService getService() {
		return service;
	}

	public void setService(CursoService service) {
		this.service = service;
	}
	
	
	
}
