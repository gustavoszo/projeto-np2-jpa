package model.services;

import java.util.List;

import model.dao.DisciplinaDao;
import model.entities.Disciplina;

public class DisciplinaService {

	private DisciplinaDao disciplinaDao = new DisciplinaDao();
	
	public void save(Disciplina disciplina) {
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

	
}
