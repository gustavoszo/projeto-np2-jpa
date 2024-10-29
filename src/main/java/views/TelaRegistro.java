package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

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

public class TelaRegistro extends JFrame implements ActionListener {

	private UsuarioService usuarioService;

	JLabel user, senha;
	JTextField userTextField;
	JButton cadastrar;
	JPasswordField senhaTextField;
    JMenuBar menuBar;
    JMenu menu;
    JMenuItem menuLogin;

	public TelaRegistro() {
		setTitle("Cadastro de usuário");
        setResizable(false);
        setSize(350, 330);
		getContentPane().setLayout(null);

		user = new JLabel("Usuário");
		user.setBounds(30, 20, 80, 25);

		senha = new JLabel("Senha");
		senha.setBounds(30, 60, 80, 25);

		userTextField = new JTextField();
		userTextField.setBounds(120, 20, 165, 25);

		senhaTextField = new JPasswordField();
		senhaTextField.setBounds(120, 60, 165, 25);

		cadastrar = new JButton("Cadastrar");
        cadastrar.setBounds(55, 100, 90, 25);

        menu = new JMenu("Login");
        menuLogin = new JMenuItem("Ir para login");
        menu.add(menuLogin);

        menuBar = new JMenuBar();
        menuBar.add(menu);
        
		getContentPane().add(user);
		getContentPane().add(senha);
		getContentPane().add(userTextField);
		getContentPane().add(senhaTextField);
		getContentPane().add(cadastrar);
        setJMenuBar(menuBar);

		cadastrar.addActionListener(this);
		menuLogin.addActionListener(this);

		setDefaultCloseOperation(EXIT_ON_CLOSE);

		setSize(320, 180);

		setLocationRelativeTo(null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == menuLogin) {
			new TelaLogin().setVisible(true);
			this.dispose();

		} else {
			String username = userTextField.getText();
			String senha = new String(senhaTextField.getPassword());

			if (username.isEmpty() || senha.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Informe usuário e senha");
				return;
			} else {
                if (senha.length() < 3) {
                    JOptionPane.showMessageDialog(null, "A senha deve ter no minímo 3 caracteres");
                    return;
                }
            }

            senha = BCrypt.hashpw(senha, BCrypt.gensalt());
			Usuario usuario = new Usuario(username, senha);

			try {
                usuarioService.save(usuario);
				JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!");
				new TelaLogin().setVisible(true);
				this.dispose();
			} catch(JpaException f) {
				JOptionPane.showMessageDialog(null, f.getMessage());
			}	

		}

	}

	public UsuarioService getUsuarioService() {
		return usuarioService;
	}

	public void setUsuarioService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

}
