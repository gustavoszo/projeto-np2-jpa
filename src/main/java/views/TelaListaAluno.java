package views;

import model.entities.Aluno; // Certifique-se de importar a classe Aluno
import model.services.AlunoService;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List; // Importar a classe List

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class TelaListaAluno extends JFrame {

	public TelaCadastroAluno telaCadastroAluno;

	JTable table;
	JLabel labelTitulo;
	JMenuBar menuBar;
	JMenu menuCadastro;
	JMenuItem menuItem;
	AlunoService alunoService = new AlunoService();
	private List<Aluno> alunos;

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

		table = new JTable(new NonEditableTableModel(
				new Object[][] {},
				new String[] {"CPF", "Nome", "Curso", "Email"}
		));

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

		// Preencher a tabela com alunos
		fillTableWithAlunos();
	}

	private void fillTableWithAlunos() {
		alunos = alunoService.findAll(); // Chama o método findAll do alunoService
		NonEditableTableModel model = (NonEditableTableModel) table.getModel();

		// Limpa a tabela antes de adicionar novos dados
		model.setRowCount(0);

		// Adiciona cada aluno à tabela
		for (Aluno aluno : alunos) {
			model.addRow(new Object[]{
					aluno.getCpf(),     // ID do aluno
					aluno.getNome(),   // Nome do aluno
					aluno.getCurso(),    // CPF do aluno
					aluno.getEmail()    // Email do aluno
			});
		}
	}

	private void tableMouseListener(MouseEvent e) {
		int selectedRow = this.table.getSelectedRow();
		if (selectedRow != -1) {
			Aluno alunoSelecionado = this.alunos.get(selectedRow);

			this.telaCadastroAluno.loadAluno(alunoSelecionado);
			this.dispose();
			this.telaCadastroAluno.setVisible(true);
		} else {
			JOptionPane.showMessageDialog(null, "Nenhum aluno selecionado!", "Erro", JOptionPane.INFORMATION_MESSAGE);
		}
	}


	// Classe interna para um modelo de tabela não editável
	private static class NonEditableTableModel extends DefaultTableModel {
		public NonEditableTableModel(Object[][] data, Object[] columnNames) {
			super(data, columnNames);
		}

		@Override
		public boolean isCellEditable(int row, int column) {
			return false; // Torna todas as células não editáveis
		}
	}
}
