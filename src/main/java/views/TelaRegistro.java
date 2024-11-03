package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import org.mindrot.jbcrypt.BCrypt;

import jpa.JpaException;
import model.entities.Usuario;
import model.services.UsuarioService;

public class TelaRegistro extends JFrame implements ActionListener {

    private UsuarioService usuarioService;

    JLabel user, senha;
    JTextField userTextField;
    JButton cadastrar, cancelar;
    JPasswordField senhaTextField;

    public TelaRegistro() {
        setTitle("Cadastro de usuário");
        setResizable(false);
        setSize(320, 180);;
        getContentPane().setLayout(null);

        user = new JLabel("Novo Usuário");
        user.setBounds(30, 20, 80, 25);

        senha = new JLabel("Nova Senha");
        senha.setBounds(30, 60, 80, 25);

        userTextField = new JTextField();
        userTextField.setBounds(120, 20, 165, 25);

        senhaTextField = new JPasswordField();
        senhaTextField.setBounds(120, 60, 165, 25);

        cadastrar = new JButton("Cadastrar");
        cadastrar.setBounds(55, 100, 90, 25);
        
        cancelar = new JButton("Cancelar");
        cancelar.setBounds(155, 100, 90, 25); // Positioning next to "Cadastrar"
        
        getContentPane().add(user);
        getContentPane().add(senha);
        getContentPane().add(userTextField);
        getContentPane().add(senhaTextField);
        getContentPane().add(cadastrar);
        getContentPane().add(cancelar); // Adding "Cancelar" button

        cadastrar.addActionListener(this);
        cancelar.addActionListener(e -> {
        	new TelaLogin().setVisible(true);
            this.dispose();
        });

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        	if(e.getSource() == cadastrar){
            String username = userTextField.getText();
            String senha = new String(senhaTextField.getPassword());

            if (username.isEmpty() || senha.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Informe usuário e senha");
                return;
            } else {
                if (senha.length() < 3) {
                    JOptionPane.showMessageDialog(null, "A senha deve ter no mínimo 3 caracteres");
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
            } catch (JpaException f) {
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
