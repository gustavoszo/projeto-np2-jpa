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
import model.services.CursoService;
import model.services.DisciplinaService;

public class TelaHome extends JFrame implements ActionListener {

    JMenuBar bMenu;
    JMenu menu1, menu2, menu3;
    JMenuItem menuCurso, menuDisciplina, menuAluno, menuProfessor, m5, m6, m7, m8, m9, m10, m11, m12;

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

        /*
        m6 = new JMenuItem("Disciplinas");
        m6.addActionListener(this);

        m7 = new JMenuItem("Aluno");
        m7.addActionListener(this);

        m8 = new JMenuItem("Professores");
        m8.addActionListener(this);

        m9 = new JMenuItem("Cursos/Professores");
        m9.addActionListener(this);

        m10 = new JMenuItem("Cursos/Disciplinas");
        m10.addActionListener(this);

        m11 = new JMenuItem("Professores/Disciplinas");
        m11.addActionListener(this);

        menu2.add(m5);
        menu2.add(m6);
        menu2.add(m7);
        menu2.add(m8);
        menu2.addSeparator();
        menu2.add(m9);
        menu2.add(m10);
        menu2.add(m11);

        menu3 = new JMenu("SAIR");
        m12 = new JMenuItem("SAIR");
        m12.addActionListener(this);
        menu3.add(m12);
        */

        bMenu = new JMenuBar();
        bMenu.add(menu1);

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
        	new TelaCadastroProfessor(this).setVisible(true);
        	this.setVisible(false);
        }
    }

}
