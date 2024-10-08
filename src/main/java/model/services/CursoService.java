package model.services;

import java.util.List;

import model.dao.CursoDao;
import model.entities.Curso;

public class CursoService {

	private CursoDao cursoDao = new CursoDao();
	
	public void save(Curso curso) {
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
 	
}
