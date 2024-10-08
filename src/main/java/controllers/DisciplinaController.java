package controllers;

import java.util.List;

import model.entities.Disciplina;
import model.exceptions.ValidationException;
import model.services.DisciplinaService;

public class DisciplinaController {
	
	private DisciplinaService service;
	
	public DisciplinaController() {
		this.service = new DisciplinaService();
	}
	
	public void save(Disciplina disciplina) {
		validationForm(disciplina);
		
		service.save(disciplina);
	}
	
	public List<Disciplina> findAll() {
		return service.findAll();
	}
	
	public void delete(Disciplina disciplina) {
		service.delete(disciplina);
	}
	
	private void validationForm(Disciplina disciplina) {
		ValidationException validationException = new ValidationException("Campo(s) inválido(s)");
		boolean valid = true;
		
		if (disciplina.getNome().trim().isEmpty()) {
			validationException.setError("nome", "Informe o nome da disciplina");
			valid = false;
		}
		
		if (!(disciplina.getCargaHoraria() > 0)) {
			 validationException.setError("cargaHoraria", "Informe a carga horária");
			 valid = false;
		}
		
		if (disciplina.getCurso() == null) {
			validationException.setError("curso", "Selecione o curso");
			valid = false;
		}
		
		if (!valid) throw validationException;
		
	}
	
}
