package model.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import jpa.JpaException;
import jpa.JpaUtils;
import model.entities.Aluno;

public class AlunoDao implements EntityDao<Aluno> {
	
	private EntityManager entityManager = JpaUtils.getEntityManager();
	
	public AlunoDao() {	}

	@Override
	public void insert(Aluno obj) {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(obj);
			entityManager.getTransaction().commit();

		} catch(Exception e) {
			throw e;
		} finally {
			entityManager.close();
		}
	}

	public void update(Aluno obj) {
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();
			// Atualiza o aluno existente no banco de dados
			Aluno alunoExistente = entityManager.find(Aluno.class, obj.getCpf());
			if (alunoExistente != null) {
				alunoExistente.setNome(obj.getNome());
				alunoExistente.setEmail(obj.getEmail());
				alunoExistente.setDtNascimento(obj.getDtNascimento());
				alunoExistente.setCurso(obj.getCurso());
				alunoExistente.setEndereco(obj.getEndereco());
				entityManager.merge(alunoExistente); // Salva as alterações
			}
			transaction.commit();
		} catch (Exception e) {
			if (transaction.isActive()) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	@Override
	public List<Aluno> findAll() {
		String jpql = "Select a from Aluno a";
		return entityManager.createQuery(jpql, Aluno.class).getResultList();
	}

	@Override
	public Aluno findById(String id) {
		Aluno aluno = entityManager.find(Aluno.class, id);
		return aluno;
	}

	@Override
	public void deleteById(String id) {
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();
			// Busca o aluno pelo CPF (id)
			Aluno aluno = entityManager.find(Aluno.class, id);
			if (aluno != null) {
				// Remove o aluno se encontrado
				entityManager.remove(aluno);
			}
			transaction.commit();
		} catch (Exception e) {
			if (transaction.isActive()) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}
	
	

}
