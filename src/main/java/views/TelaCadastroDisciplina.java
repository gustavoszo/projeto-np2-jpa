package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Set;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import jpa.JpaException;
import model.entities.Curso;
import model.entities.Disciplina;
import model.exceptions.ValidationException;
import model.services.CursoService;
import model.services.DisciplinaService;

public class TelaCadastroDisciplina extends JFrame {

    private TelaHome home;
    private Disciplina disciplina;
    private DisciplinaService disciplinaService;
    private CursoService cursoService;
    private List<Curso> cursos;
    
    JLabel labelTitulo, labelNome, labelCargaHoraria, labelCurso;
    JLabel labelErrorNome, labelErrorCargaHoraria, labelErrorCurso;
    JTextField txtNome, txtCargaHoraria;
    JComboBox<Curso> listaCurso;
    JButton btnSalvar, btnLimpar, btnConsultar, btnDeletar;
    JMenuBar menuBar;
    JMenu menuHome;
    JMenuItem menuItem;

    public TelaCadastroDisciplina(TelaHome home) {
        this.home = home;
        this.cursoService = new CursoService();
        initComponents();
    }

    public void initComponents() {
        setTitle("Cadastro de Disciplina");
        setSize(630, 400);
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

        // Label Título
        labelTitulo = new JLabel("CADASTRO DE DISCIPLINA");
        labelTitulo.setBounds(30, 20, 300, 30);
        labelTitulo.setFont(new Font("calibri", Font.BOLD, 18));

        // Label e Campo Nome
        labelNome = new JLabel("Nome: ");
        labelNome.setBounds(30, 80, 120, 30);

        txtNome = new JTextField("");
        txtNome.setBounds(150, 80, 250, 30);
        
        labelErrorNome = new JLabel("");
        labelErrorNome.setBounds(410, 80, 180, 30);
        labelErrorNome.setForeground(Color.red);

        // Label e Campo Carga Horária
        labelCargaHoraria = new JLabel("Carga Horária: ");
        labelCargaHoraria.setBounds(30, 130, 120, 30);
        
        txtCargaHoraria = new JTextField("");
        txtCargaHoraria.setBounds(150, 130, 250, 30);
        txtCargaHoraria.addKeyListener(new KeyAdapter() {
        	@Override
        	public void keyTyped(KeyEvent e) {
        		char c = e.getKeyChar();
        		
        		 if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE) {
        	            e.consume();
        	     }
        	}	
        });
        
        labelErrorCargaHoraria = new JLabel("");
        labelErrorCargaHoraria.setBounds(410, 130, 150, 30);
        labelErrorCargaHoraria.setForeground(Color.red);

        // Label e ComboBox Curso
        labelCurso = new JLabel("Curso:");
        labelCurso.setBounds(30, 180, 120, 30);
        
        cursos = cursoService.findAll();
        DefaultComboBoxModel<Curso> model = new DefaultComboBoxModel<Curso>();
        cursos.forEach(c -> model.addElement(c));

        listaCurso = new JComboBox<>(model);
        listaCurso.setSelectedItem(null);
        listaCurso.setBounds(150, 180, 250, 30);
        
        labelErrorCurso = new JLabel("");
        labelErrorCurso.setBounds(410, 180, 150, 30);
        labelErrorCurso.setForeground(Color.red);

        // Botões
        btnSalvar = new JButton("Salvar");
        btnSalvar.setBounds(30, 270, 100, 40);
        btnSalvar.addActionListener(e -> {
        	btnSalvarActionListener(e);
        });

        btnLimpar = new JButton("Limpar");
        btnLimpar.setBounds(150, 270, 100, 40);
        btnLimpar.addActionListener(e -> {
        	btnLimparActionListener(e);
        });

        btnConsultar = new JButton("Consultar");
        btnConsultar.setBounds(270, 270, 100, 40);
        btnConsultar.addActionListener(e -> {
            this.setVisible(false);
            new TelaListaDisciplina(this).setVisible(true);
        });

        btnDeletar = new JButton("Deletar");
        btnDeletar.setBounds(390, 270, 100, 40);
        btnDeletar.setVisible(false);
		btnDeletar.addActionListener(e -> {
			int confirm = JOptionPane.showConfirmDialog(null, "Deseja realmente apagar a disciplina?", "Confirmação", JOptionPane.YES_NO_OPTION);
			if (confirm != JOptionPane.YES_OPTION)
				return;

			try {
				disciplinaService.delete(this.disciplina);
				JOptionPane.showMessageDialog(null, "Disciplina apagada com suceso!");
				btnLimparActionListener(e);
				unloadDisciplina();
			} catch (JpaException ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage());
			}
		});
		

        menuBar = new JMenuBar();
        menuBar.add(menuHome);
        setJMenuBar(menuBar);
        add(labelTitulo);
        add(labelNome);
        add(txtNome);
        add(labelCargaHoraria);
        add(txtCargaHoraria);
        add(labelCurso);
        add(listaCurso);
        add(labelErrorNome);
        add(labelErrorCargaHoraria);
        add(labelErrorCurso);
        add(btnSalvar);
        add(btnLimpar);
        add(btnConsultar);
        add(btnDeletar);
    }
    
    private void btnSalvarActionListener(ActionEvent e) {
    	String nome = txtNome.getText();
    	int cargaHoraria;
    	try {
    		cargaHoraria = Integer.parseInt(txtCargaHoraria.getText());
    	} catch (Exception f) {
    		cargaHoraria = 0;
    	}
    	Curso curso = (Curso) listaCurso.getSelectedItem();
    	
    	disciplina.setNome(nome);
    	disciplina.setCargaHoraria(cargaHoraria);
    	disciplina.setCurso(curso);
    	Integer id = disciplina.getId();
    	
    	try {
    		disciplinaService.save(disciplina);
    		
    		if (id == null) {
    			JOptionPane.showMessageDialog(null, "Disciplina cadastrada com sucesso!");
    		} else {
    			JOptionPane.showMessageDialog(null, "Disciplina atualizada com sucesso!");
    		}
    		
    		btnLimparActionListener(e);
    		unloadDisciplina();
    		
    	} catch (ValidationException validationEx) {
    		Set<String> fields = validationEx.getErrors().keySet();
    		
    		if (fields.contains("nome")) {
    			labelErrorNome.setText(validationEx.getErrors().get("nome"));
    		}
    		
    		if (fields.contains("cargaHoraria")) {
    			labelErrorCargaHoraria.setText(validationEx.getErrors().get("cargaHoraria"));
    		}
    		
    		if (fields.contains("curso")) {
    			labelErrorCurso.setText(validationEx.getErrors().get("curso"));
    		}
    		
    	} catch (JpaException dbEx) {
    		JOptionPane.showMessageDialog(null, dbEx.getMessage());
    	}
    }
    
    private void btnLimparActionListener(ActionEvent e) {
    	txtNome.setText("");
    	txtCargaHoraria.setText("");
    	listaCurso.setSelectedItem(null);
    	
    	labelErrorNome.setText("");
    	labelErrorCargaHoraria.setText("");
    	labelErrorCurso.setText("");
    }
    
    public void loadDisciplina(Disciplina disciplina) {
    	this.disciplina = disciplina;
    	txtNome.setText(disciplina.getNome());
    	txtCargaHoraria.setText(String.valueOf(disciplina.getCargaHoraria()));
    	
        DefaultComboBoxModel<Curso> model = new DefaultComboBoxModel<Curso>();
        cursos.forEach(c -> model.addElement(c));
    	model.setSelectedItem(disciplina.getCurso());
    	listaCurso.setModel(model);
    	
    	labelTitulo.setText("Editando a Disciplina Id " + disciplina.getId());
    	btnDeletar.setVisible(true);
    }
    
    public void unloadDisciplina() {
    	this.disciplina = new Disciplina();  	
    	labelTitulo.setText("CADASTRO DE DISCIPLINA");
    	btnDeletar.setVisible(false);
    }
    
    // Getters e Setters
    public TelaHome getHome() {
        return home;
    }

    public void setHome(TelaHome home) {
        this.home = home;
    }

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public DisciplinaService getDisciplinaService() {
		return disciplinaService;
	}

	public void setDisciplinaService(DisciplinaService disciplinaService) {
		this.disciplinaService = disciplinaService;
	}

	public CursoService getCursoService() {
		return cursoService;
	}

	public void setCursoService(CursoService cursoService) {
		this.cursoService = cursoService;
	}
    
}