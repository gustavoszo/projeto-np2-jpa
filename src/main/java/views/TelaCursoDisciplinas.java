package views;

import javax.swing.*;

import java.awt.Font;
import java.util.List;
import java.awt.event.ActionEvent;

import javax.swing.table.DefaultTableModel;

import model.entities.Curso;
import model.entities.Disciplina;
import model.services.CursoService;

public class TelaCursoDisciplinas extends JFrame {

    private TelaHome home;
    private CursoService cursoService;
    
    // Components
    DefaultTableModel tableModel;
    List<Curso> cursos;
    JComboBox<Curso> comboBoxCurso;
    JTable table;
    JLabel labelCurso, labelTitulo;
    JMenuBar menuBar;
    JMenu menuHome;
    JMenuItem menuItem;

    public TelaCursoDisciplinas(TelaHome tela) {
        this.home = tela;
        this.cursoService = new CursoService();
        initComponents();
    }

    public void initComponents() {
        setTitle("Disciplinas do curso");
        setResizable(false);
        setSize(630, 670);
        setLocation(400, 150);
        setLayout(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        menuHome = new JMenu("Home");
        menuItem = new JMenuItem("Ir para home");
        menuItem.addActionListener(e -> {
            this.home.setVisible(true);
            this.dispose();
        });
        menuHome.add(menuItem);

        labelCurso = new JLabel("Selecione o curso");
		labelCurso.setLocation(90, 30);
		labelCurso.setSize(120, 30);

        cursos = cursoService.findAll();
        DefaultComboBoxModel<Curso> model = new DefaultComboBoxModel<Curso>();
        cursos.forEach(c -> model.addElement(c));

        comboBoxCurso = new JComboBox<>(model);
        comboBoxCurso.setSelectedItem(null);
        comboBoxCurso.setBounds(210, 30, 200, 30);
        comboBoxCurso.addActionListener((e) -> {
            comboBoxCursoActionListener(e);
        });

        labelTitulo = new JLabel("Disciplinas do curso");
        labelTitulo.setFont(new Font("calibri", Font.BOLD, 20));
		labelTitulo.setLocation(90, 100);
		labelTitulo.setSize(450, 30);
		
	    tableModel = new DefaultTableModel(
                new Object[][] {},
                new String[] { "ID", "Disciplina", "Carga Horária"}
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Desabilita a edição das células
            }
        };
        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        JScrollPane scrollPane = new JScrollPane(table);
     	scrollPane.setSize(400, 400);
	    scrollPane.setLocation(90, 130);

        menuBar = new JMenuBar();
        menuBar.add(menuHome);
        setJMenuBar(menuBar);
        add(labelTitulo);
        add(labelCurso);
        add(comboBoxCurso);
        add(scrollPane);
    }

    private void comboBoxCursoActionListener(ActionEvent e) {
        tableModel.setNumRows(0);
        Curso curso = (Curso) comboBoxCurso.getSelectedItem();
        labelTitulo.setText("Disciplinas do curso - " + curso);
        List<Disciplina> disciplinas = curso.getDisciplinas();

        
        disciplinas.forEach(d -> {
        	tableModel.addRow(new Object[] { d.getId(), d.getNome(), d.getCargaHoraria() });
        });
        table.setModel(tableModel);
    }
    
    // Getters e Setters
	public TelaHome getTelaHome() {
		return home;
	}

	public void setTelaHome(TelaHome home) {
		this.home = home;
	}

	public CursoService getCursoService() {
		return cursoService;
	}

	public void setCursoService(CursoService cursoService) {
		this.cursoService = cursoService;
	}
    
}
