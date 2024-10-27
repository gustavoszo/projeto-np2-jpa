package model.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import jpa.JpaException;
import jpa.JpaUtils;
import model.entities.Usuario;
import model.exceptions.ValidationException;

public class UsuarioDao {

    private EntityManager entityManager;

	public UsuarioDao() {	}

	public void insert(Usuario obj) {
		entityManager = JpaUtils.getEntityManager();
		try {
            Usuario usuario = findByUsername(obj.getUsername());
            if (usuario != null) throw new ValidationException(String.format("Usuário '%s' já cadastrado", obj.getUsername()));

			entityManager.getTransaction().begin();
			entityManager.persist(obj);
			entityManager.getTransaction().commit();
        
		} catch(Exception e) {
			throw new JpaException(e.getMessage());
		} finally {
			entityManager.close();
		}
	}

	public Usuario findByUsername(String username) {
		entityManager = JpaUtils.getEntityManager();
        try {
            String jpql = "SELECT u FROM Usuario u WHERE u.username = :username";
            List<Usuario> usuarios = entityManager.createQuery(jpql, Usuario.class).setParameter("username", username).getResultList();
            return usuarios.isEmpty() ? null : usuarios.get(0);

        } catch (Exception e) {
            throw new JpaException("Ocorreu um erro ao buscar o usuário: " + e.getMessage());
        }
	}
    
}
