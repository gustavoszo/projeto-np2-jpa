package model.services;

import java.util.List;

import model.dao.DisciplinaDao;
import model.entities.Disciplina;
import model.exceptions.ValidationException;

public class DisciplinaService {

	private DisciplinaDao disciplinaDao = new DisciplinaDao();
	
	public void save(Disciplina disciplina) {
		validationForm(disciplina);

		if (disciplina.getId() == null) {
			disciplinaDao.insert(disciplina);
		} else {
			disciplinaDao.update(disciplina);
		}
	}
	
	public Disciplina findById(Disciplina disciplina) {
		return disciplinaDao.findById(String.valueOf(disciplina.getId()));
	}
	
	public List<Disciplina> findAll() {
		return disciplinaDao.findAll();
	}
	
	public void delete(Disciplina disciplina) {
		disciplinaDao.deleteById(String.valueOf(disciplina.getId()));
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
