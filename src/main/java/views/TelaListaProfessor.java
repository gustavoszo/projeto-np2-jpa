package views;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

public class TelaListaProfessor extends JFrame {

	public TelaCadastroProfessor telaCadastroProfessor;

	JTable table;
	JLabel labelTitulo;
	JMenuBar menuBar;
	JMenu menuCadastro;
	JMenuItem menuItem;

	public TelaListaProfessor(TelaCadastroProfessor tela) {
		this.telaCadastroProfessor = tela;
		initComponents();
	}

	public void initComponents() {
	    setTitle("Lista de Professores");
	    setResizable(false);
	    setSize(600, 500);
	    setLocation(100, 100);
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

	    table = new JTable(new DefaultTableModel(
	        new Object[][] {},
	        new String[] {"ID", "Nome", "CPF", "Email"}
	    ));

	    JScrollPane scrollPane = new JScrollPane(table);
	    scrollPane.setSize(350, 300);
	    scrollPane.setLocation(90, 80);

	    // Adicionando o listener para eventos de clique
	    table.addMouseListener(new MouseAdapter() {
	        @Override
	        public void mouseClicked(MouseEvent e) {
	            if (e.getClickCount() == 2) {
	                System.out.println("Pronto");
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

}