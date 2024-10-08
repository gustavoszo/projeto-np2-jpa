package views;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import controllers.DisciplinaController;
import model.entities.Curso;
import model.entities.Disciplina;

public class TelaListaDisciplina extends JFrame {

	private TelaCadastroDisciplina telaCadastroDisciplina;
	private DisciplinaController disciplinaController;
	
	JLabel labelTitulo;
	JTable table;
	JMenuBar menuBar;
	JMenu menuCadastro;
	JMenuItem menuItem;

	public TelaListaDisciplina(TelaCadastroDisciplina tela) {
		this.telaCadastroDisciplina = tela;
		this.disciplinaController = new DisciplinaController();
		initComponents();
	}

	public void initComponents() {
		setTitle("Lista de Disciplinas");
	  	setResizable(false);
	    setSize(600, 500);
	    setLocation(100, 100);
	    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	    
	    menuCadastro = new JMenu("Cadastrar nova Disciplina");
	    menuItem = new JMenuItem("Ir para cadastro");
        menuItem.addActionListener(e -> {
        	this.telaCadastroDisciplina.setVisible(true);
        	this.dispose();
        });
        menuCadastro.add(menuItem);

		labelTitulo = new JLabel("Lista de Disciplinas");
		labelTitulo.setFont(new Font("calibri", Font.BOLD, 20));
		labelTitulo.setLocation(80, 45);
		labelTitulo.setSize(190, 30);
		
		DefaultTableModel tableModel = new DefaultTableModel(
                new Object[][] {},
                new String[] { "ID", "Nome", "Carga Horária", "Id Curso", "Curso" }
        ) {	
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Desabilita a edição das células
            }
        };
        List<Disciplina> disciplinas = disciplinaController.findAll();
        disciplinas.forEach(d -> {
        	tableModel.addRow(new Object[] {
        		d.getId(), d.getNome(), d.getCargaHoraria(), d.getCurso().getId(), d.getCurso().getNome()
        	});
        });

		table = new JTable(tableModel);
		table.addMouseListener(new MouseAdapter() {
			@Override
	        public void mouseClicked(MouseEvent e) {
	            if (e.getClickCount() == 2) {
	                tableMouseListener(e);
	            }
	        }
	    });

		JScrollPane scrollPane = new JScrollPane(table);
	  	scrollPane.setSize(420, 300);
	    scrollPane.setLocation(80, 80);
	    
	    menuBar = new JMenuBar();
        menuBar.add(menuCadastro);
        setJMenuBar(menuBar);
		add(labelTitulo);
		add(scrollPane);

		setLayout(null);

	}
	
	private void tableMouseListener(MouseEvent e) {
		int selectedRow = table.getSelectedRow();
		if (selectedRow != -1) {
			Integer id_disciplina = (Integer) table.getValueAt(selectedRow, 0);
			String nome = (String) table.getValueAt(selectedRow, 1);
			int cargaHoraria = (int) table.getValueAt(selectedRow, 2);
			Integer id_curso = (Integer) table.getValueAt(selectedRow, 3);
			Curso curso = this.telaCadastroDisciplina.getCursoService().findById(String.valueOf(id_curso));
			
			Disciplina disciplina = new Disciplina(id_disciplina, nome, cargaHoraria, curso);
			
			this.telaCadastroDisciplina.loadDisciplina(disciplina);
			this.telaCadastroDisciplina.setVisible(true);
			this.dispose();
		}
	}

	// Getter e Setters
	public TelaCadastroDisciplina getTelaCadastroDisciplina() {
		return telaCadastroDisciplina;
	}

	public void setTelaCadastroDisciplina(TelaCadastroDisciplina telaCadastroDisciplina) {
		this.telaCadastroDisciplina = telaCadastroDisciplina;
	}

	public DisciplinaController getDisciplinaController() {
		return disciplinaController;
	}

	public void setDisciplinaController(DisciplinaController disciplinaController) {
		this.disciplinaController = disciplinaController;
	}
		
}
