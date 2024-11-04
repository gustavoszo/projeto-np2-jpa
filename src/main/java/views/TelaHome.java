package views;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;

import model.entities.Curso;
import model.entities.Disciplina;
import model.entities.Professor;
import model.services.CursoService;
import model.services.DisciplinaService;
import model.services.ProfessorService;

public class TelaHome extends JFrame implements ActionListener {

    JMenuBar bMenu;
    JMenu menu1, menu2, menu3;
    JMenuItem menuCurso, menuDisciplina, menuAluno, menuProfessor, menuSair, menuCursoDisciplinas, menuCursoAlunos, menuCursoProfessores;
    
    JButton btnCurso, btnDisciplina, btnAluno, btnProfessor;
    JLabel labelTitulo;

    public TelaHome() {
        initComponents();

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    public void initComponents() {
        setTitle("Home");
        setResizable(false);
        setSize(600, 500);
        setLocation(400, 250);
        setLayout(null);

        // Title Label
        labelTitulo = new JLabel("Sistema da Faculdade", SwingConstants.CENTER);
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 40)); // Increased font size
        labelTitulo.setBounds(0, 100, 600, 40); // Adjusted bounds for better positioning
        add(labelTitulo);

        // Buttons for "Cadastrar" menu items
        btnCurso = new JButton("Cadastrar Curso");
        btnCurso.setBounds(70, 250, 200, 50); // Set position lower on the screen
        btnCurso.addActionListener(this);
        add(btnCurso);

        btnDisciplina = new JButton("Cadastrar Disciplina");
        btnDisciplina.setBounds(330, 250, 200, 50); // Set position lower on the screen
        btnDisciplina.addActionListener(this);
        add(btnDisciplina);

        btnAluno = new JButton("Cadastrar Aluno");
        btnAluno.setBounds(70, 320, 200, 50); // Set position lower on the screen
        btnAluno.addActionListener(this);
        add(btnAluno);

        btnProfessor = new JButton("Cadastrar Professor");
        btnProfessor.setBounds(330, 320, 200, 50); // Set position lower on the screen
        btnProfessor.addActionListener(this);
        add(btnProfessor);

        // Menu items
        menu1 = new JMenu("Cadastrar");
        menuCurso = new JMenuItem("Curso");
        menuCurso.addActionListener(this);
        
        menuDisciplina = new JMenuItem("Disciplina");
        menuDisciplina.addActionListener(this);

        menuAluno = new JMenuItem("Aluno");
        menuAluno.addActionListener(this);

        menuProfessor = new JMenuItem("Professor");
        menuProfessor.addActionListener(this);

        menu1.add(menuCurso);
        menu1.add(menuDisciplina);
        menu1.add(menuAluno);
        menu1.add(menuProfessor);
        
        // Other menus
        menu2 = new JMenu("Consulta");
        menuCursoDisciplinas = new JMenuItem("Curso/Disciplinas");
        menuCursoDisciplinas.addActionListener(this);
        menu2.add(menuCursoDisciplinas);
        menuCursoAlunos = new JMenuItem("Curso/Alunos");
        menuCursoAlunos.addActionListener(this);
        menu2.add(menuCursoAlunos);
        menuCursoProfessores = new JMenuItem("Curso/Professores");
        menuCursoProfessores.addActionListener(this);
        menu2.add(menuCursoProfessores);

        menu3 = new JMenu("Conta");
        menuSair = new JMenuItem("Sair");
        menuSair.addActionListener(this);
        menu3.add(menuSair);
  
        bMenu = new JMenuBar();
        bMenu.add(menu1);
        bMenu.add(menu2);
        bMenu.add(menu3);

        setJMenuBar(bMenu);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == menuCurso || e.getSource() == btnCurso) {
            TelaCadastroCurso telaCadastroCurso = new TelaCadastroCurso(this);
            telaCadastroCurso.setCursoService(new CursoService());
            telaCadastroCurso.setCurso(new Curso());
            telaCadastroCurso.setVisible(true);
            this.setVisible(false);
        }
        
        if (e.getSource() == menuDisciplina || e.getSource() == btnDisciplina) {
            TelaCadastroDisciplina telaCadastroDisciplina = new TelaCadastroDisciplina(this);
            telaCadastroDisciplina.setDisciplinaService(new DisciplinaService());
            telaCadastroDisciplina.setDisciplina(new Disciplina());
            telaCadastroDisciplina.setVisible(true);
            this.setVisible(false);
        }
        
        if (e.getSource() == menuAluno || e.getSource() == btnAluno) {
            new TelaCadastroAluno(this).setVisible(true);
            this.setVisible(false);
        }
        
        if (e.getSource() == menuProfessor || e.getSource() == btnProfessor) {
            TelaCadastroProfessor telaCadastroProfessor = new TelaCadastroProfessor(this);
            telaCadastroProfessor.setProfessorService(new ProfessorService());
            telaCadastroProfessor.setProfessor(new Professor());
            telaCadastroProfessor.setVisible(true);
            this.setVisible(false);
        }
        
        if (e.getSource() == menuSair) {
            new TelaLogin().setVisible(true);
            this.dispose();
        }
        
        if (e.getSource() == menuCursoDisciplinas) {
            TelaCursoDisciplinas telaCursoDisciplinas = new TelaCursoDisciplinas(this);
            telaCursoDisciplinas.setCursoService(new CursoService());
            telaCursoDisciplinas.setVisible(true);
            this.setVisible(false);
        }
        
        if (e.getSource() == menuCursoAlunos) {
            TelaCursoAlunos telaCursoAlunos = new TelaCursoAlunos(this);
            telaCursoAlunos.setVisible(true);
            this.setVisible(false);
        }
        
        if (e.getSource() == menuCursoProfessores) {
            TelaCursoProfessores telaCursoProfessores = new TelaCursoProfessores(this);
            telaCursoProfessores.setVisible(true);
            this.setVisible(false);
        }
    }
}
