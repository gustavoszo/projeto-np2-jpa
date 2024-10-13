package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class TelaLogin extends JFrame implements ActionListener {
	JLabel user, senha;
	JTextField userTextField;
	JButton login, registro;
	JPasswordField senhaTextField;

	public TelaLogin() {
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

			if ("admin".equals(userText) && "admin".equals(senhaText)) {
				new TelaHome().setVisible(true);
				this.dispose();
			}

			else {
				JOptionPane.showMessageDialog(this, "Usuário e/ou senha inválido(s)");
				userTextField.setText("");
				senhaTextField.setText("");
			}
		}
	}

}

