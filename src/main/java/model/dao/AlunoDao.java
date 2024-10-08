package model.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import jpa.JpaUtils;
import model.entities.Aluno;

public class AlunoDao implements EntityDao<Aluno> {
	
	private EntityManager entityManager;
	
	public AlunoDao() {
		this.entityManager = JpaUtils.getEntityManagerFactory().createEntityManager();
	}

	@Override
	public void insert(Aluno obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Aluno obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Aluno> findAll() {
		// TODO
		return null;
	}

	@Override
	public Aluno findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(String id) {
		// TODO Auto-generated method stub
		
	}
	
	

}
