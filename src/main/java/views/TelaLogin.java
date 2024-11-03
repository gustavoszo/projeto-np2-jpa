package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import org.mindrot.jbcrypt.BCrypt;
import jpa.JpaException;
import model.entities.Usuario;
import model.services.UsuarioService;

public class TelaLogin extends JFrame implements ActionListener {
    
    private UsuarioService usuarioService;
    private JLabel user, senha;
    private JTextField userTextField;
    private JButton login, registro;
    private JPasswordField senhaTextField;

    public TelaLogin() {
        this.usuarioService = new UsuarioService();
        initUI();
    }

    private void initUI() {
        setTitle("Login");
        setLayout(null);

        // User Label
        user = new JLabel("Usuário");
        user.setBounds(30, 20, 80, 25);
        getContentPane().add(user);

        // Password Label
        senha = new JLabel("Senha");
        senha.setBounds(30, 60, 80, 25);
        getContentPane().add(senha);

        // User TextField
        userTextField = new JTextField();
        userTextField.setBounds(120, 20, 165, 25);
        getContentPane().add(userTextField);

        // Password TextField
        senhaTextField = new JPasswordField();
        senhaTextField.setBounds(120, 60, 165, 25);
        getContentPane().add(senhaTextField);

        // Login Button
        login = new JButton("Login");
        login.setBounds(55, 100, 80, 25);
        getContentPane().add(login);

        // Registro Button
        registro = new JButton("Registro");
        registro.setBounds(155, 100, 90, 25);
        getContentPane().add(registro);

        // Action Listeners
        login.addActionListener(this);
        registro.addActionListener(this);

        // JFrame settings
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(320, 180);
        setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == login) {
            handleLogin();
        } else {
            handleRegister();
        }
    }

    private void handleLogin() {
        String userText = userTextField.getText();
        String senhaText = new String(senhaTextField.getPassword());
        
        try {
            Usuario usuario = usuarioService.findByUsername(userText);
            if (usuario != null && BCrypt.checkpw(senhaText, usuario.getSenha())) {
                new TelaHome().setVisible(true);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Usuário e/ou senha inválido(s)");
            }
        } catch (JpaException f) {
            JOptionPane.showMessageDialog(null, f.getMessage());
        }
    }

    private void handleRegister() {
        TelaRegistro telaRegistro = new TelaRegistro();
        telaRegistro.setUsuarioService(new UsuarioService());
        telaRegistro.setVisible(true);
        this.dispose();
    }

    public UsuarioService getUsuarioService() {
        return usuarioService;
    }

    public void setUsuarioService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
}
