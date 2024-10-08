package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.WindowConstants;

import controllers.CursoController;
import jpa.JpaException;
import model.entities.Curso;
import model.exceptions.ValidationException;

public class TelaCadastroCurso extends JFrame {

	private TelaHome home;
    private Curso curso;
    private CursoController cursoController;
    
    // Components
    JButton btnSalvar, btnLimpar, btnConsultar, btnDeletar;
    JLabel labelTitulo, labelCurso, labelPeriodo;
    JLabel labelErrorCurso, labelErrorPeriodo;
    JRadioButton radioManha, radioNoite, radioTarde;
    ButtonGroup groupPeriodo;
    JComboBox<String> listCursos;
    JMenu menuHome;
    JMenuBar menuBar;
    JMenuItem menuItem;

    public TelaCadastroCurso(TelaHome home) {
        this.home = home;
        initComponents();
    }

    public void initComponents() {
    	setTitle("Gerenciamento de Cursos");
        setResizable(false);
        setSize(500, 380);
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
        

        labelTitulo = new JLabel("CADASTRO DE CURSO");
        labelTitulo.setLocation(35, 20);
        labelTitulo.setSize(250, 20);
        labelTitulo.setFont(new Font("calibri", Font.BOLD, 18));

        labelCurso = new JLabel("Selecione o curso");
        labelCurso.setLocation(35, 65);
        labelCurso.setSize(120, 20);

        labelPeriodo = new JLabel("Periodo");
        labelPeriodo.setLocation(35, 150);
        labelPeriodo.setSize(80, 20);
        
        labelErrorCurso = new JLabel("");
        labelErrorCurso.setLocation(220, 90);
        labelErrorCurso.setSize(170, 30);
        labelErrorCurso.setForeground(Color.red);
        
        labelErrorPeriodo = new JLabel("");
        labelErrorPeriodo.setLocation(310, 170);
        labelErrorPeriodo.setSize(120, 30);
        labelErrorPeriodo.setForeground(Color.red);

        String[] cursos = { "Administração de Empresas", "Bio Medicina", "Ciências Biológicas", "Ciencias da Computação",
                "Direito", "Educação Física", "Farmacologia", "Rede de Computadores", "Sistemas de Informações" };

        listCursos = new JComboBox<String>(cursos); 
        listCursos.setSelectedItem(null);
        listCursos.setLocation(35, 90);
        listCursos.setSize(160, 30);

        radioManha = new JRadioButton("Manhã", false);
        radioManha.setLocation(35, 170);
        radioManha.setSize(80, 30);

        radioTarde = new JRadioButton("Tarde", false);
        radioTarde.setLocation(115, 170);
        radioTarde.setSize(70, 30);

        radioNoite = new JRadioButton("Noite", false);
        radioNoite.setLocation(190, 170);
        radioNoite.setSize(80, 30);

        groupPeriodo = new ButtonGroup();
        groupPeriodo.add(radioManha);
        groupPeriodo.add(radioTarde);
        groupPeriodo.add(radioNoite);

        btnSalvar = new JButton("Salvar");
        btnSalvar.setLocation(10, 260);
        btnSalvar.setSize(100, 30);
        btnSalvar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnSalvarActionListener(e);
			}
        });

        btnLimpar = new JButton("Limpar");
        btnLimpar.setLocation(130, 260);
        btnLimpar.setSize(100, 30);
        btnLimpar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnLimparActionListener(e);
			}
        });

        btnConsultar = new JButton("Consultar");
        btnConsultar.setSize(100, 30);
        btnConsultar.setLocation(250, 260);
        btnConsultar.addActionListener(e -> {
            TelaListaCurso telaListaCurso = new TelaListaCurso(this);
            telaListaCurso.setVisible(true);
            this.setVisible(false);
        });

        btnDeletar = new JButton("Deletar");
        btnDeletar.setSize(100, 30);
        btnDeletar.setLocation(370, 260);
        btnDeletar.setVisible(false);
        btnDeletar.addActionListener(e -> {
        	int confirm = JOptionPane.showConfirmDialog(null, "Deseja realmente apagar o curso?", "Confirmação", JOptionPane.YES_NO_OPTION);
        	if (confirm != JOptionPane.YES_OPTION) return;
        	
        	try {
        		cursoController.delete(this.curso);        	
        		JOptionPane.showMessageDialog(null, "Curso apagado com suceso!");
        		btnLimparActionListener(e);
        		unloadCurso();
        	} catch (JpaException ex) {
        		JOptionPane.showMessageDialog(null, ex.getMessage());
        	}
        	
        });
        
        menuBar = new JMenuBar();
        menuBar.add(menuHome);
        setJMenuBar(menuBar);
        add(labelTitulo);
        add(labelCurso);
        add(labelPeriodo);
        add(labelErrorCurso);
        add(labelErrorPeriodo);
        add(labelPeriodo);
        add(listCursos);
        add(radioManha);
        add(radioTarde);
        add(radioNoite);
        add(btnSalvar);
        add(btnLimpar);
        add(btnConsultar);
        add(btnDeletar);
    }
    
    private void btnSalvarActionListener(ActionEvent e) {
    	String nome = (String) listCursos.getSelectedItem();
    	String periodo = null;
    	
    	if (radioManha.isSelected()) {
    		periodo = "Manhã";
    	} else if (radioTarde.isSelected()) {
    		periodo = "Tarde";
    	} else if (radioNoite.isSelected()) {
    		periodo = "Noite";
    	}
    	
    	curso.setNome(nome);
    	curso.setPeriodo(periodo);
    	Integer id = curso.getId();
    	
    	try {
    		cursoController.save(curso);
    		
    		if (id == null) {
    			JOptionPane.showMessageDialog(null, "Curso cadastro com sucesso!");
    		} else {
    			JOptionPane.showMessageDialog(null, "Curso atualizado com sucesso!");
    		}
    		
    		btnLimparActionListener(e);
    		unloadCurso();
    	}
  	
    	catch (ValidationException f) {
    		Set<String> errors = f.getErrors().keySet();
    		
    		if (errors.contains("nome")) {
    			labelErrorCurso.setText(f.getErrors().get("nome"));
    		}
    		if (errors.contains("periodo")) {
    			labelErrorPeriodo.setText(f.getErrors().get("periodo"));
    		}
    	}
    	
    	catch (JpaException dbEx) {
    		JOptionPane.showMessageDialog(null, dbEx.getMessage());
    	}
    }
    
    private void btnLimparActionListener(ActionEvent e) {
    	labelErrorCurso.setText("");
		labelErrorPeriodo.setText("");
		listCursos.setSelectedItem(null);
		groupPeriodo.clearSelection();
    }
    
    public void loadCurso(Curso curso) {
    	labelTitulo.setText("Editando o curso ID " + curso.getId());
    	btnDeletar.setVisible(true);
    	
    	this.curso = curso;
    	listCursos.setSelectedItem(curso.getNome());
    	
    	switch(curso.getPeriodo()) {
    		case "Manhã":
    			radioManha.setSelected(true);
    			break;
    		case "Tarde":
    			radioTarde.setSelected(true);
    			break;
    		default:
    			radioNoite.setSelected(true);
    	}
    }
    
    public void unloadCurso() {
    	this.curso = new Curso();
    	labelTitulo.setText("CADASTRO DE CURSO");
    	btnDeletar.setVisible(false);
    }
    
    // Getters e Setters
	public TelaHome getHome() {
		return home;
	}

	public void setHome(TelaHome home) {
		this.home = home;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public CursoController getCursoController() {
		return cursoController;
	}

	public void setCursoController(CursoController cursoController) {
		this.cursoController = cursoController;
	}
	
	
    

}