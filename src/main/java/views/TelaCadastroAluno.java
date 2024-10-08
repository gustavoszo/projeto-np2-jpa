package views;

import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.text.MaskFormatter;

public class TelaCadastroAluno extends JFrame {
	
	private TelaHome home;

	private JLabel nome, cpf, email, dataNascimento, idCurso;
	private JTextField nomeField, cpfField, emailField;
	private JFormattedTextField dataNascField;
	private JList<String> listaCursos;
	private JButton btnSalvar, btnLimpar, btnConsultar, btnDeletar;
	private JMenuBar menuBar;
	private JMenu menuHome;
	JMenuItem menuItem;

	public TelaCadastroAluno(TelaHome home) {
		this.home = home;
		initComponents();
	}
	
	private void initComponents() {
		setBounds(400, 250, 500, 400);
		getContentPane().setLayout(null);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		menuHome = new JMenu("Home");
		menuItem = new JMenuItem("Ir para home");
	    menuItem.addActionListener(e -> {
	    	this.home.setVisible(true);
	        this.dispose();
	    });
	    menuHome.add(menuItem);
		
		// Labels
		nome = new JLabel("Nome:");
		nome.setBounds(30, 30, 100, 25);

		cpf = new JLabel("CPF:");
		cpf.setBounds(30, 70, 100, 25);

		email = new JLabel("E-mail:");
		email.setBounds(30, 110, 100, 25);

		dataNascimento = new JLabel("Data de Nascimento:");
		dataNascimento.setBounds(30, 150, 150, 25);

		idCurso = new JLabel("Curso:");
		idCurso.setBounds(30, 190, 100, 25);

		// Text Fields
		nomeField = new JTextField();
		nomeField.setBounds(180, 30, 200, 25);

		cpfField = new JTextField();
		cpfField.setBounds(180, 70, 200, 25);

		emailField = new JTextField();
		emailField.setBounds(180, 110, 200, 25);
		
		// Inicializando o JFormattedTextField para a data de nascimento
        try {
            MaskFormatter maskFormatter = new MaskFormatter("##/##/####");
            dataNascField = new JFormattedTextField(maskFormatter);
            dataNascField.setBounds(180, 150, 200, 25);
        } catch (ParseException e) {
            e.printStackTrace();
        }

		// List
		listaCursos = new JList<>();
		listaCursos.setBounds(180, 190, 200, 100);

		// Buttons
		btnSalvar = new JButton("Salvar");
		btnSalvar.setBounds(30, 480, 100, 40);

		btnLimpar = new JButton("Limpar");
		btnLimpar.setBounds(150, 480, 100, 40);

		btnConsultar = new JButton("Consultar");
		btnConsultar.setBounds(270, 480, 100, 40);
		btnConsultar.addActionListener(e -> {
			this.setVisible(false);
			new TelaListaAluno(this).setVisible(true);
		});

		btnDeletar = new JButton("Deletar");
		btnDeletar.setBounds(390, 480, 100, 40);
		btnDeletar.setVisible(false);
		
	    menuBar = new JMenuBar();
	    menuBar.add(menuHome);
	    setJMenuBar(menuBar);
		getContentPane().add(nome);
		getContentPane().add(cpf);
		getContentPane().add(email);
		getContentPane().add(dataNascimento);
		getContentPane().add(idCurso);
		getContentPane().add(nomeField);
		getContentPane().add(cpfField);
		getContentPane().add(emailField);
		getContentPane().add(dataNascField);
		getContentPane().add(listaCursos);
		getContentPane().add(btnSalvar);
		getContentPane().add(btnConsultar);
		getContentPane().add(btnLimpar);
		getContentPane().add(btnDeletar);
	}

	public TelaHome getHome() {
		return home;
	}

	public void setHome(TelaHome home) {
		this.home = home;
	}
	
}
