package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import model.entities.Curso;
import model.entities.Disciplina;
import model.entities.Professor;
import model.services.CursoService;
import model.services.DisciplinaService;
import model.services.ProfessorService;
import model.services.ProfessorService;

public class TelaHome extends JFrame implements ActionListener {

    JMenuBar bMenu;
    JMenu menu1, menu2, menu3;
    JMenuItem menuCurso, menuDisciplina, menuAluno, menuProfessor, menuSair, menuCursoDisciplinas, menuCursoAlunos, menuCursoProfessores;

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
        if (e.getSource() == menuCurso) {
            TelaCadastroCurso telaCadastroCurso = new TelaCadastroCurso(this);
            telaCadastroCurso.setCursoService(new CursoService());
            telaCadastroCurso.setCurso(new Curso());
            telaCadastroCurso.setVisible(true);
            this.setVisible(false);
        }
        
        if (e.getSource() == menuDisciplina) {
        	TelaCadastroDisciplina telaCadastroDisciplina = new TelaCadastroDisciplina(this);
        	telaCadastroDisciplina.setDisciplinaService(new DisciplinaService());
        	telaCadastroDisciplina.setDisciplina(new Disciplina());
        	telaCadastroDisciplina.setVisible(true);
        	this.setVisible(false);
        }
        
        if (e.getSource() == menuAluno) {
        	new TelaCadastroAluno(this).setVisible(true);
        	this.setVisible(false);
        }
        
        if (e.getSource() == menuProfessor) {
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
