package model.services;

import java.util.List;

import model.dao.CursoDao;
import model.entities.Curso;
import model.exceptions.ValidationException;

public class CursoService {

	private CursoDao cursoDao = new CursoDao();
	
	public void save(Curso curso) {
		validateForm(curso);
		
		if (curso.getId() == null) {
			cursoDao.insert(curso);
		} else {
			cursoDao.update(curso);
		}
	}
	
	public Curso findById(String id) {
		return cursoDao.findById(id);
	}
	
	public List<Curso> findAll() {
		return cursoDao.findAll();
	}
	
	public void delete(Curso curso) {
		cursoDao.deleteById(String.valueOf(curso.getId()));
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
 	
}
