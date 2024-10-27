package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.mindrot.jbcrypt.BCrypt;

import jpa.JpaException;
import model.entities.Usuario;
import model.services.UsuarioService;

public class TelaLogin extends JFrame implements ActionListener {
	
	private UsuarioService usuarioService;

	JLabel user, senha;
	JTextField userTextField;
	JButton login, registro;
	JPasswordField senhaTextField;

	public TelaLogin() {
		this.usuarioService = new UsuarioService();

		setTitle("LOGIN");
		setLayout(null);

		user = new JLabel("Usuário");
		user.setBounds(30, 20, 80, 25);

		senha = new JLabel("Senha");
		senha.setBounds(30, 60, 80, 25);

		userTextField = new JTextField();
		userTextField.setBounds(120, 20, 165, 25);

		senhaTextField = new JPasswordField();
		senhaTextField.setBounds(120, 60, 165, 25);

		login = new JButton("Login");
		login.setBounds(55, 100, 80, 25);

		registro = new JButton("Registro");
		registro.setBounds(155, 100, 90, 25);

		getContentPane().add(user);
		getContentPane().add(senha);
		getContentPane().add(userTextField);
		getContentPane().add(senhaTextField);
		getContentPane().add(login);
		getContentPane().add(registro);

		login.addActionListener(this);
		registro.addActionListener(this);

		setDefaultCloseOperation(EXIT_ON_CLOSE);

		setSize(320, 180);

		setLocationRelativeTo(null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == login) {
			String userText = userTextField.getText();
			String senhaText = new String(senhaTextField.getPassword());

			try {
				Usuario usuario = usuarioService.findByUsername(userText);
				if (usuario != null) {
					if (BCrypt.checkpw(senhaText, usuario.getSenha())) {
						new TelaHome().setVisible(true);
						this.dispose();
						return;
					}	
				}	
				JOptionPane.showMessageDialog(null, "Usuário e/ou senha inválido(s)");

			} catch(JpaException f) {
				JOptionPane.showMessageDialog(null, f.getMessage());
			}

		} else {
			TelaRegistro telaRegistro = new TelaRegistro();
			telaRegistro.setUsuarioService(new UsuarioService());
			telaRegistro.setVisible(true);
			this.dispose();
		}
	}

	public UsuarioService getUsuarioService() {
		return usuarioService;
	}

	public void setUsuarioService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

}

