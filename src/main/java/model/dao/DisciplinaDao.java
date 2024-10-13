package model.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import jpa.JpaException;
import jpa.JpaUtils;
import model.entities.Disciplina;

public class DisciplinaDao implements EntityDao<Disciplina> {
	
	private EntityManager entityManager;

	@Override
	public void insert(Disciplina obj) {
		entityManager = JpaUtils.getEntityManager();
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(obj);
			entityManager.getTransaction().commit();
		} catch(Exception e) {
			throw new JpaException("Ocorreu um ao tentar salvar a disciplina: " + e.getMessage());

		} finally {
			entityManager.close();
		}
		
	}

	@Override
	public void update(Disciplina obj) {
		entityManager = JpaUtils.getEntityManager();
		try {
			entityManager.getTransaction().begin();
			Disciplina disciplina = entityManager.find(Disciplina.class, obj.getId());
			disciplina.setNome(obj.getNome());
			disciplina.setCargaHoraria(obj.getCargaHoraria());
			disciplina.setCurso(obj.getCurso());

			entityManager.getTransaction().commit();
		
		} catch(Exception e) {
			throw new JpaException("Ocorreu um ao tentar atualizar a disciplina: " + e.getMessage());

		} finally {
			entityManager.close();
		}
	}

	@Override
	public List<Disciplina> findAll() {
		entityManager = JpaUtils.getEntityManager();
		try {
			entityManager.getTransaction().begin();
			List<Disciplina> disciplinas = entityManager.createQuery("select d from Disciplina d").getResultList();
			return disciplinas;
		
		} catch(Exception e) {
			throw new JpaException("Ocorreu um ao buscar as disciplinas: " + e.getMessage());

		} finally {
			entityManager.close();
		}
	}

	@Override
	public Disciplina findById(String id) {
		entityManager = JpaUtils.getEntityManager();
		try {
			entityManager.getTransaction().begin();
			Disciplina disciplina = entityManager.find(Disciplina.class, Integer.parseInt(id));
			return disciplina;

		} catch(Exception e) {
			throw new JpaException("Ocorreu um erro ao buscar a disciplina: " + e.getMessage());
		} finally {
			entityManager.close();
		}
	}

	@Override
	public void deleteById(String id) {
		entityManager = JpaUtils.getEntityManager();
		try {
			entityManager.getTransaction().begin();
			Disciplina disciplina = entityManager.find(Disciplina.class, Integer.parseInt(id));
			if (disciplina != null) {
				entityManager.remove(disciplina);
			}

			entityManager.getTransaction().commit();

		} catch(Exception e) {
			throw new JpaException("Ocorreu um erro ao tentar apagar a disciplina: " + e.getMessage());
		} finally {
			entityManager.close();
		}
	}
	
}
