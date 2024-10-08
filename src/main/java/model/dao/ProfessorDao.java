package model.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import jpa.JpaUtils;
import model.entities.Professor;

public class ProfessorDao implements EntityDao<Professor> {
	
	private EntityManager entityManager;
	
	public void AlunoDaoImpl() {
		this.entityManager = JpaUtils.getEntityManagerFactory().createEntityManager();
	}

	@Override
	public void insert(Professor obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Professor obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Professor> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Professor findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(String id) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
