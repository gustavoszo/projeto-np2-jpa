package views;

import java.awt.Font;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.text.MaskFormatter;

public class TelaCadastroProfessor extends JFrame {

	private TelaHome home;
	
	JLabel labelTitulo, labelNome, labelDtNascimento, labelEmail, labelCep, labelLogradouro, labelNumero, labelCidade, labelEstado, labelCurso, labelDisciplina;
    JTextField txtNome, txtEmail, txtCep, txtLogradouro, txtNumero, txtCidade;
    JFormattedTextField txtDtNascimento;
    JComboBox<String> comboBoxEstados;
    // Mudar para ComboBox de <Curso>
    JComboBox<String> comboBoxCursos;
    // Mudar para ComboBox de <Disiciplina>
    JComboBox<String> comboBoxDisciplinas;
    JButton btnSalvar, btnLimpar, btnConsultar, btnDeletar;
    JMenuBar menuBar;
    JMenu menuHome;
    JMenuItem menuItem;

    public TelaCadastroProfessor(TelaHome home) {
    	this.home = home;
    	initComponents();
    }

    public void initComponents() {
    	 setTitle("Cadastro de Professor");
         setResizable(false);
         setSize(680, 580);
         setLocation(100, 100);
         setLayout(null);
         setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
         
         menuHome = new JMenu("Home");
         menuItem = new JMenuItem("Ir para home");
         menuItem.addActionListener(e -> {
         	this.home.setVisible(true);
         	this.dispose();
         });
         menuHome.add(menuItem);

         // Inicializando os JLabels
         labelTitulo = new JLabel("Cadastro de professor");
         labelTitulo.setBounds(30, 15, 250, 30);
         labelTitulo.setFont(new Font("Calibri", Font.BOLD, 18));

         labelNome = new JLabel("Nome:");
         labelNome.setBounds(30, 50, 100, 30);

         labelDtNascimento = new JLabel("Data de Nascimento:");
         labelDtNascimento.setBounds(30, 100, 150, 30);

         labelEmail = new JLabel("Email:");
         labelEmail.setBounds(30, 150, 100, 30);

         labelCep = new JLabel("CEP:");
         labelCep.setBounds(30, 200, 100, 30);

         labelLogradouro = new JLabel("Logradouro:");
         labelLogradouro.setBounds(30, 250, 100, 30);

         labelNumero = new JLabel("Número:");
         labelNumero.setBounds(30, 300, 100, 30);

         labelCidade = new JLabel("Cidade:");
         labelCidade.setBounds(30, 350, 100, 30);

         labelEstado = new JLabel("Estado:");
         labelEstado.setBounds(300, 350, 100, 30);

         labelCurso = new JLabel("Curso:");
         labelCurso.setBounds(30, 400, 100, 30);

         labelDisciplina = new JLabel("Disciplina:");
         labelDisciplina.setBounds(300, 400, 100, 30);

         // Inicializando os JTextFields
         txtNome = new JTextField();
         txtNome.setBounds(150, 50, 300, 30);

         txtEmail = new JTextField();
         txtEmail.setBounds(150, 150, 300, 30);

         txtCep = new JTextField();
         txtCep.setBounds(150, 200, 150, 30);

         txtLogradouro = new JTextField();
         txtLogradouro.setBounds(150, 250, 300, 30);

         txtNumero = new JTextField();
         txtNumero.setBounds(150, 300, 100, 30);

         txtCidade = new JTextField();
         txtCidade.setBounds(150, 350, 130, 30);

         // Inicializando o JFormattedTextField para a data de nascimento
         try {
             MaskFormatter maskFormatter = new MaskFormatter("##/##/####");
             txtDtNascimento = new JFormattedTextField(maskFormatter);
             txtDtNascimento.setBounds(180, 100, 100, 30);
         } catch (ParseException e) {
             e.printStackTrace();
         }

         // Inicializando JComboBox para Estado
         String[] estados = {"AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO"};
         comboBoxEstados = new JComboBox<>(estados);
         comboBoxEstados.setBounds(370, 350, 80, 30);

         // Inicializando JComboBox para Curso e Disciplina
         comboBoxCursos = new JComboBox<>();
         comboBoxCursos.setBounds(150, 400, 130, 30);

         comboBoxDisciplinas = new JComboBox<>();
         comboBoxDisciplinas.setBounds(370, 400, 130, 30);

         // Inicializando botões
         btnSalvar = new JButton("Salvar");
         btnSalvar.setBounds(30, 480, 100, 40);

         btnLimpar = new JButton("Limpar");
         btnLimpar.setBounds(150, 480, 100, 40);

         btnConsultar = new JButton("Consultar");
         btnConsultar.setBounds(270, 480, 100, 40);
         btnConsultar.addActionListener(e -> {
             this.setVisible(false);
             new TelaListaProfessor(this).setVisible(true);
         });

         btnDeletar = new JButton("Deletar");
         btnDeletar.setBounds(390, 480, 100, 40);
         btnDeletar.setVisible(false);
         
         menuBar = new JMenuBar();
         menuBar.add(menuHome);
         setJMenuBar(menuBar);
         // Adicionando os componentes ao JFrame
         add(labelTitulo);
         add(labelNome);
         add(labelDtNascimento);
         add(labelEmail);
         add(labelCep);
         add(labelLogradouro);
         add(labelNumero);
         add(labelCidade);
         add(labelEstado);
         add(labelCurso);
         add(labelDisciplina);
         add(txtNome);
         add(txtDtNascimento);
         add(txtEmail);
         add(txtCep);
         add(txtLogradouro);
         add(txtNumero);
         add(txtCidade);
         add(comboBoxEstados);
         add(comboBoxCursos);
         add(comboBoxDisciplinas);
         add(btnSalvar);
         add(btnLimpar);
         add(btnConsultar);
         add(btnDeletar);
    }

	public TelaHome getHome() {
		return home;
	}

	public void setHome(TelaHome home) {
		this.home = home;
	}


}