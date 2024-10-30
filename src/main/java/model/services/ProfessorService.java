package model.services;

import java.util.List;

import model.dao.ProfessorDao;
import model.entities.Professor;

public class ProfessorService {
	
	private ProfessorDao professorDao = new ProfessorDao();
	
	public void save(Professor professor) {
		if (findById(professor.getCpf()) == null) {
			System.out.println("inserindo professor");
			professorDao.insert(professor);
		} else {
			System.out.println("atualizando o professor");
			professorDao.update(professor);
		}
	}
	
	public Professor findById(String id) {
		return professorDao.findById(id);
	}
	
	public List<Professor> findAll() {
		return professorDao.findAll();
	}
	
	public void delete(Professor professor) {
		professorDao.deleteById(String.valueOf(professor.getCpf()));
	}

	
}
