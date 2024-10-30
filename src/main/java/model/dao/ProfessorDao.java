package model.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jpa.JpaException;
import jpa.JpaUtils;
import model.entities.Aluno;
import model.entities.Curso;
import model.entities.Professor;

public class ProfessorDao implements EntityDao<Professor> {
	
	private EntityManager entityManager;

	@Override
	public void insert(Professor obj) {
		try {
			entityManager = JpaUtils.getEntityManager();
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
	public void update(Professor obj) {
		entityManager = JpaUtils.getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();
			//Atualiza Professor existente no BD
			Professor professorExistente = entityManager.find(Professor.class, obj.getCpf());
			if (professorExistente != null) {
				professorExistente.setNome(obj.getNome());
				professorExistente.setEmail(obj.getEmail());
				professorExistente.setDtNascimento(obj.getDtNascimento());
				professorExistente.setCurso(obj.getCurso());
				professorExistente.setEndereco(obj.getEndereco());
				professorExistente.setDisciplina(obj.getDisciplina());
				entityManager.merge(professorExistente); // Salva as alterações
			}
			transaction.commit();
		} catch (Exception e) {
			if (transaction.isActive()) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		finally {
			entityManager.close();
		}
		
	}

	@Override
	public List<Professor> findAll() {
		try {
			entityManager = JpaUtils.getEntityManager();
			entityManager.getTransaction().begin();
			List<Professor> professores = entityManager.createQuery("select p from Professor p", Professor.class).getResultList();
			return professores;

		} catch (Exception e) {
			throw new JpaException("Ocorreu um erro ao listar os professores: " + e.getMessage());
		} finally {
			entityManager.close();
		}
	}

	@Override
	public Professor findById(String id) {
		entityManager = JpaUtils.getEntityManager();
		Professor professor = entityManager.find(Professor.class, id);
		return professor;
	}

	@Override
	public void deleteById(String id) {
		entityManager = JpaUtils.getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();
			// Busca o professor pelo CPF (id)
			Professor professor = entityManager.find(Professor.class, id);
			if (professor != null) {
				// Remove o professor se encontrado
				entityManager.remove(professor);
			}
			transaction.commit();
		} catch (Exception e) {
			if (transaction.isActive()) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		finally {
		entityManager.close();
		}
		
	}
	
	
	
}
