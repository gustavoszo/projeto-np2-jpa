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

public class TelaListaAluno extends JFrame {

	public TelaCadastroAluno telaCadastroAluno;

	JTable table;
	JLabel labelTitulo;
	JMenuBar menuBar;
	JMenu menuCadastro;
	JMenuItem menuItem;
	
	public TelaListaAluno(TelaCadastroAluno tela) {
		this.telaCadastroAluno = tela;
		initComponents();
	}

	public void initComponents() {
	    setTitle("Lista de alunos");
	    setResizable(false);
	    setSize(600, 500);
	    setLocation(100, 100);
	    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	   
	    menuCadastro = new JMenu("Cadastrar novo Aluno");
	    menuItem = new JMenuItem("Ir para cadastro");
        menuItem.addActionListener(e -> {
        	this.telaCadastroAluno.setVisible(true);
        	this.dispose();
        });
        menuCadastro.add(menuItem);

	    labelTitulo = new JLabel("Lista de alunos");
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