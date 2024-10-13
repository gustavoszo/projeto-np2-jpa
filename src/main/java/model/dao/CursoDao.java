package model.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import jpa.JpaException;
import jpa.JpaUtils;
import model.entities.Curso;

public class CursoDao implements EntityDao<Curso> {

	private EntityManager entityManager = JpaUtils.getEntityManager();

	@Override
	public void insert(Curso obj) {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(obj);
			entityManager.getTransaction().commit();
		} catch(Exception e) {
			if (entityManager.getTransaction().isActive()) {
				entityManager.getTransaction().rollback();
				throw new JpaException("Ocorreu um erro ao salvar o curso: " + e.getMessage());
			}

		} finally {
			entityManager.close();
		}
	
	}

	@Override
	public void update(Curso obj) {
		try {
			entityManager.getTransaction().begin();
			Curso curso = entityManager.find(Curso.class, obj.getId());
			// passo os attrs de obj para curso. Com isso, o hibernate vai persistir no banco as alteracoes feitas no obj quando for realizado o commit
			curso.setNome(obj.getNome());
			curso.setPeriodo(obj.getPeriodo());

			entityManager.getTransaction().commit();
		} catch(Exception e) {
			if (entityManager.getTransaction().isActive()) {
				entityManager.getTransaction().rollback();
				throw new JpaException("Ocorreu um erro ao atualizar o curso: " + e.getMessage());
			}
		
		} finally {
			entityManager.close();
		}
		
	}

	@Override
	public List<Curso> findAll() {
		try {
			entityManager.getTransaction().begin();
			List<Curso> cursos = entityManager.createQuery("select c from Curso c").getResultList();
			return cursos;

		} catch (Exception e) {
			throw new JpaException("Ocorreu um erro ao listar os cursos: " + e.getMessage());
		} finally {
			entityManager.close();
		}
	}

	@Override
	public Curso findById(String id) {
		try {
			entityManager.getTransaction().begin();
			Curso curso = entityManager.find(Curso.class, Integer.parseInt(id));
			return curso;
			
		} catch (Exception e) {
			throw new JpaException("Ocorreu um erro ao buscar o curso: " + e.getMessage());
		} finally {
			entityManager.close();
		}
	}

	@Override
	public void deleteById(String id) {
		try {
			entityManager.getTransaction().begin();
			Curso curso = entityManager.find(Curso.class, Integer.parseInt(id));
			if (curso != null) {
				entityManager.remove(curso);
			}
			entityManager.getTransaction().commit();

		} catch (Exception e) {
			throw new JpaException("Ocorreu um erro ao tentar apagar o curso: " + e.getMessage());
		} finally {
			entityManager.close();
		}
		
	}
	
}
