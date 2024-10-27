package model.services;

import model.dao.UsuarioDao;
import model.entities.Usuario;

public class UsuarioService {

	private UsuarioDao usuarioDao = new UsuarioDao();

	public void save(Usuario usuario) {
		usuarioDao.insert(usuario);
	}

	public Usuario findByUsername(String username) {
		return usuarioDao.findByUsername(username);
	}


}