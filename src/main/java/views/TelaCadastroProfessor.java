package views;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.text.MaskFormatter;

import jpa.JpaException;
import model.entities.Curso;
import model.entities.Disciplina;
import model.entities.Endereco;
import model.entities.Professor;
import model.services.CursoService;
import model.services.ProfessorService;

public class TelaCadastroProfessor extends JFrame {

    private TelaHome home;
    private CursoService cursoService;
    private Professor professor;
    private List<Curso> listaCursos;
    private ProfessorService professorService;

	JLabel labelTitulo, labelCpf, labelNome, labelDtNascimento, labelEmail, labelCep, labelLogradouro, labelNumero, labelCidade, labelEstado, labelCurso, labelDisciplina;
	JLabel labelErrorTitulo, labelErrorCpf, labelErrorNome, labelErrorDtNascimento, labelErrorEmail, labelErrorCep, labelErrorLogradouro, labelErrorNumero, labelErrorCidade, labelErrorEstado, labelErrorCurso, labelErrorDisciplina;
    JTextField txtNome, txtCpf, txtEmail, txtCep, txtLogradouro, txtNumero, txtCidade;
    JFormattedTextField txtDtNascimento;
    JComboBox<String> comboBoxEstados;
    JComboBox<Curso> comboBoxCursos;
    JComboBox<Disciplina> comboBoxDisciplinas;
    JButton btnSalvar, btnLimpar, btnConsultar, btnDeletar;
    JMenuBar menuBar;
    JMenu menuHome;
    JMenuItem menuItem;

    public TelaCadastroProfessor(TelaHome home) {
        this.home = home;
        this.cursoService = new CursoService();
        initComponents();
    }

    public void initComponents() {
        setTitle("Cadastro de Professor");
        setResizable(false);
        setSize(580, 500);
        setLocation(400, 250);
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
        labelTitulo = new JLabel("CADASTRO DE PROFESSOR");
        labelTitulo.setBounds(180, 15, 250, 30);
        labelTitulo.setFont(new Font("Calibri", Font.BOLD, 18));

        labelNome = new JLabel("Nome:");
        labelNome.setBounds(30, 50, 100, 30);

        labelCpf = new JLabel("CPF: ");
        labelCpf.setBounds(30, 85, 120, 30);

        labelDtNascimento = new JLabel("Data de Nascimento:");
        labelDtNascimento.setBounds(30, 120, 180, 30);

        labelEmail = new JLabel("Email:");
        labelEmail.setBounds(30, 155, 100, 30);

        labelCep = new JLabel("CEP:");
        labelCep.setBounds(30, 190, 100, 30);

        labelLogradouro = new JLabel("Logradouro:");
        labelLogradouro.setBounds(30, 225, 100, 30);

        labelNumero = new JLabel("Número:");
        labelNumero.setBounds(30, 260, 100, 30);

        labelCidade = new JLabel("Cidade:");
        labelCidade.setBounds(30, 295, 100, 30);

        labelEstado = new JLabel("Estado:");
        labelEstado.setBounds(300, 295, 100, 30);

        labelCurso = new JLabel("Curso:");
        labelCurso.setBounds(30, 330, 100, 30);

        labelDisciplina = new JLabel("Disciplina:");
        labelDisciplina.setBounds(300, 330, 100, 30);

        // Inicializando os JTextFields
        txtNome = new JTextField();
        txtNome.setBounds(150, 50, 300, 30);

        txtEmail = new JTextField();
        txtEmail.setBounds(150, 155, 300, 30);

        txtLogradouro = new JTextField();
        txtLogradouro.setBounds(150, 225, 300, 30);

        txtNumero = new JTextField();
        txtNumero.setBounds(150, 260, 100, 30);

        txtCidade = new JTextField();
        txtCidade.setBounds(150, 295, 130, 30);

        // Inicializando o JFormattedTextField para a data de nascimento
        try {
            MaskFormatter maskFormatter = new MaskFormatter("##/##/####");
            txtDtNascimento = new JFormattedTextField(maskFormatter);
            txtDtNascimento.setBounds(180, 120, 100, 30);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        try {
            MaskFormatter maskFormatterCpf = new MaskFormatter("###.###.###-##");
            txtCpf = new JFormattedTextField(maskFormatterCpf);
            txtCpf.setBounds(150, 85, 300, 30);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // Inicializando o JFormattedTextField para o CEP
        try {
            MaskFormatter maskFormatterCep = new MaskFormatter("#####-###");
            txtCep = new JFormattedTextField(maskFormatterCep);
            txtCep.setBounds(150, 190, 150, 30);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // Inicializando JComboBox para Estado
        String[] estados = {"AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO"};
        comboBoxEstados = new JComboBox<>(estados);
        comboBoxEstados.setBounds(370, 295, 80, 30);

        // Inicializando JComboBox para Curso e Disciplina
        listaCursos = cursoService.findAll();
        DefaultComboBoxModel<Curso> model = new DefaultComboBoxModel<Curso>();
        listaCursos.forEach(c -> model.addElement(c));
        comboBoxCursos = new JComboBox<>(model);
        comboBoxCursos.setBounds(150, 330, 130, 30);
        comboBoxCursos.setSelectedItem(null);
        comboBoxCursos.addActionListener(e -> cursoListener(e));

        comboBoxDisciplinas = new JComboBox<>();
        comboBoxDisciplinas.setBounds(370, 330, 130, 30);

        // Inicializando botões
        btnSalvar = new JButton("Salvar");
        btnSalvar.setBounds(120, 390, 100, 40);
        btnSalvar.addActionListener(e -> salvar(e));

        btnLimpar = new JButton("Limpar");
        btnLimpar.setBounds(240, 390, 100, 40);
        btnLimpar.addActionListener(e -> limpar());

        btnConsultar = new JButton("Consultar");
        btnConsultar.setBounds(360, 390, 100, 40);
        btnConsultar.addActionListener(e -> {
            this.setVisible(false);
            new TelaListaProfessor(this).setVisible(true);
        });

        btnDeletar = new JButton("Deletar");
        btnDeletar.setBounds(240, 390, 100, 40);
        btnDeletar.setVisible(false);
        btnDeletar.addActionListener(e -> {
        	if(txtCpf != null) {
        		SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
    	        Date dataNasc = null;
    			try {
    				dataNasc = formatoData.parse(txtDtNascimento.getText());
    			} catch (ParseException e1) {
    				// TODO Auto-generated catch block
    				e1.printStackTrace();
    			}
        		Endereco endereco = new Endereco(txtCep.getText(), txtLogradouro.getText(), Integer.valueOf(txtNumero.getText()), txtCidade.getText(),(String) comboBoxEstados.getSelectedItem());
        		Professor professor = new Professor(txtCpf.getText(), txtNome.getText(), txtEmail.getText(), dataNasc, (Curso) comboBoxCursos.getSelectedItem(), (Disciplina) comboBoxDisciplinas.getSelectedItem(), endereco);
        		professorService.delete(professor);
        		btnDeletar.setVisible(false);
        		btnLimpar.setVisible(true);
        		new TelaListaProfessor(this).setVisible(true);
        		this.setVisible(false);
        	}
        });

        menuBar = new JMenuBar();
        menuBar.add(menuHome);
        setJMenuBar(menuBar);
        // Adicionando os componentes ao JFrame
        add(labelTitulo);
        add(labelNome);
        add(labelCpf);
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
        add(txtCpf);
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

        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void cursoListener(ActionEvent e) {
        DefaultComboBoxModel<Disciplina> modelDisciplina = new DefaultComboBoxModel<Disciplina>();

        Curso curso = (Curso) comboBoxCursos.getSelectedItem();
        if (curso != null) {
        	List<Disciplina> listaDisciplinas = curso.getDisciplinas();
        	listaDisciplinas.forEach(d -> modelDisciplina.addElement(d));
        	comboBoxDisciplinas.setModel(modelDisciplina);
        }
    }

    private void salvar(ActionEvent e) {
    	Date dataNasc = null;
    	Disciplina disciplina = null;
    	Curso curso = null;
    	
    	boolean valid = true;
    	
    	if(professor != null) {
	        String nome = txtNome.getText();
	        if(nome.isBlank()) {
	        	valid = false;
	        }
	        
	        String cpf = txtCpf.getText();
	        if(cpf.isBlank()) {
	        	valid = false;
	        }
	        
	        String email = txtEmail.getText();
	        if(email.isBlank()) {
	        	valid = false;
	        }
	        
	        if(!txtDtNascimento.getText().isBlank()) {
		        SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
				try {
					dataNasc = formatoData.parse(txtDtNascimento.getText());
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					valid = false;
				}
	        }
	        else {
	        	valid = false;
	        }
	        
	        if(comboBoxDisciplinas.getSelectedItem() != null) {
	        	curso = (Curso) comboBoxCursos.getSelectedItem();
	        	disciplina = (Disciplina) comboBoxDisciplinas.getSelectedItem();
	        }
	        else {
	        	valid = false;
	        }
		    
	        if (!valid) return;
	        
	        Endereco endereco = new Endereco();
	        endereco.setCep(txtCep.getText());
	        endereco.setLogradouro(txtLogradouro.getText());
	        endereco.setNumero(Integer.parseInt(txtNumero.getText()));
	        endereco.setCidade(txtCidade.getText());
	        endereco.setEstado((String) comboBoxEstados.getSelectedItem());
	        
	        professor.setProfessor(cpf, nome, email, dataNasc, endereco, curso, disciplina);
	        try {
	        	professorService.save(professor);
	        	JOptionPane.showMessageDialog(null, "Professor cadastrado com sucesso!");
	        	limpar();
			} catch (JpaException jpae) {
				JOptionPane.showMessageDialog(null, jpae.getMessage());
			}
    	}
    }
    
    public void loadProfessor(Professor professor) {
    	txtNome.setText(professor.getNome());
    	txtCpf.setText(professor.getCpf());
    	txtEmail.setText(professor.getEmail());
    	
    	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    	String dataNascimento = sdf.format(professor.getDtNascimento());
    	txtDtNascimento.setText(dataNascimento);
    	    	
    	Endereco endereco = professor.getEndereco();
    	txtCep.setText(endereco.getCep());
    	txtNumero.setText(String.valueOf(endereco.getNumero()));
    	txtLogradouro.setText(endereco.getLogradouro());
    	txtCidade.setText(endereco.getCidade());
    	comboBoxEstados.setSelectedItem(endereco.getEstado());
    	comboBoxCursos.setSelectedItem(professor.getCurso());
    	comboBoxDisciplinas.setSelectedItem(professor.getDisciplina());
    	
    	btnDeletar.setVisible(true);
    	btnLimpar.setVisible(false);
    }
    
    private void limpar() {
    	 txtNome.setText("");
         txtDtNascimento.setText("");
         txtEmail.setText("");
         txtCpf.setText("");
         txtCep.setText("");
         txtLogradouro.setText("");
         txtNumero.setText("");
         txtCidade.setText("");
         comboBoxCursos.setSelectedItem(null);
         comboBoxCursos.setSelectedItem(null);
         
         comboBoxDisciplinas.setModel(new DefaultComboBoxModel<>());
    }
        

    public TelaHome getHome() {
        return home;
    }

    public void setHome(TelaHome home) {
        this.home = home;

    }

    public CursoService getCursoService() {
        return cursoService;
    }

    public void setCursoService(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }
    
    public ProfessorService getProfessorService() {
 		return professorService;
 	}

 	public void setProfessorService(ProfessorService professorService) {
 		this.professorService = professorService;
 	}
}