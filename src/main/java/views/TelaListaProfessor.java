package views;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
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

import model.entities.Curso;
import model.entities.Disciplina;
import model.entities.Endereco;
import model.entities.Professor;
import model.services.ProfessorService;

public class TelaListaProfessor extends JFrame {

	public TelaCadastroProfessor telaCadastroProfessor;
	
	private ProfessorService professorService;

	JTable table;
	JLabel labelTitulo;
	JMenuBar menuBar;
	JMenu menuCadastro;
	JMenuItem menuItem;

	public TelaListaProfessor(TelaCadastroProfessor tela) {
		this.telaCadastroProfessor = tela;
		this.professorService = new ProfessorService();
		initComponents();
		
	}

	public void initComponents() {
	    setTitle("Lista de Professores");
	    setResizable(false);
	    setSize(600, 500);
        setLocation(400, 250);
	    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	    
	    menuCadastro = new JMenu("Cadastrar nova Disciplina");
	    menuItem = new JMenuItem("Ir para cadastro");
        menuItem.addActionListener(e -> {
        	this.telaCadastroProfessor.setVisible(true);
        	this.dispose();
        });
        menuCadastro.add(menuItem);

	    labelTitulo = new JLabel("Lista de professores");
	    labelTitulo.setFont(new Font("Calibri", Font.BOLD, 20));
	    labelTitulo.setSize(250, 30);
	    labelTitulo.setLocation(195, 20);

	    List<Professor> listaProfessor = professorService.findAll();
	    
	    DefaultTableModel tableModel = new DefaultTableModel(
                new Object[][] {},
                new String[] {"CPF", "Nome", "Email", "Data de Nasc.", "Curso", "Disc.", "E-mail"}
        ) {	
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Desabilita a edição das células
            }
        };
        
	    listaProfessor.forEach(p -> {
	    	tableModel.addRow(new Object[] {p.getCpf(), p.getNome(), p.getEmail(), p.getDtNascimento(), p.getCurso(), p.getDisciplina(), p.getEndereco()});
	    });
        
	    table = new JTable(tableModel);
	    
	    JScrollPane scrollPane = new JScrollPane(table);
	    scrollPane.setSize(350, 300);
	    scrollPane.setLocation(90, 80);

	    // Adicionando o listener para eventos de clique
	    table.addMouseListener(new MouseAdapter() {
	        @Override
	        public void mouseClicked(MouseEvent e) {
	            if (e.getClickCount() == 2) {
	                tableMouseListener(e);
	            }
	        }
	    });
	    
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
			String id_cpfProfessor = (String) table.getValueAt(selectedRow, 0);
			String nome = (String) table.getValueAt(selectedRow, 1);
			String email = (String) table.getValueAt(selectedRow, 2);
			Date dtNasc = (Date) table.getValueAt(selectedRow, 3);
			Curso curso = (Curso) table.getValueAt(selectedRow, 4);
			Disciplina disciplina = (Disciplina) table.getValueAt(selectedRow, 5);
			Endereco endereco = (Endereco) table.getValueAt(selectedRow, 6);
			
			System.out.println("Editando o professor");
			Professor professor = new Professor(id_cpfProfessor, nome, email, dtNasc, curso, disciplina, endereco);
			
			this.telaCadastroProfessor.loadProfessor(professor);
			this.telaCadastroProfessor.setVisible(true);
			this.dispose();
		}
	}
}