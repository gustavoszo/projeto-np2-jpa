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
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import jpa.JpaException;
import model.entities.Curso;
import model.exceptions.ValidationException;
import model.services.CursoService;

public class TelaCadastroCurso extends JFrame {

    private TelaHome home;
    private Curso curso;
    private CursoService cursoService;
    
    // Components
    JTextField txtCurso;
    JComboBox<Integer> comboBoxSemestres;
    JButton btnSalvar, btnLimpar, btnConsultar, btnDeletar;
    JLabel labelTitulo, labelCurso, labelSemestres, labelPeriodo;
    JLabel labelErrorCurso, labelErrorSemestres, labelErrorPeriodo;
    JRadioButton radioManha, radioNoite, radioTarde;
    ButtonGroup groupPeriodo;
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
        setSize(500, 410);
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

        labelCurso = new JLabel("Curso");
        labelCurso.setLocation(35, 65);
        labelCurso.setSize(120, 20);

        // Adding JLabel for Semestres
        labelSemestres = new JLabel("Semestres");
        labelSemestres.setLocation(35, 145);
        labelSemestres.setSize(80, 20);
        
        labelPeriodo = new JLabel("Período");
        labelPeriodo.setLocation(35, 190);  
        labelPeriodo.setSize(80, 20);
        
        labelErrorCurso = new JLabel("");
        labelErrorCurso.setLocation(260, 90);
        labelErrorCurso.setSize(170, 30);
        labelErrorCurso.setForeground(Color.red);
        
        labelErrorSemestres = new JLabel("");
        labelErrorSemestres.setLocation(260, 145);
        labelErrorSemestres.setSize(170, 30);
        labelErrorSemestres.setForeground(Color.red);
        
        labelErrorPeriodo = new JLabel("");
        labelErrorPeriodo.setLocation(310, 210);  
        labelErrorPeriodo.setSize(120, 30);
        labelErrorPeriodo.setForeground(Color.red);

        txtCurso = new JTextField(""); 
        txtCurso.setLocation(35, 90);
        txtCurso.setSize(190, 30);

        // Adding JComboBox for Semestres
        comboBoxSemestres = new JComboBox<>();
        for (int i = 1; i <= 10; i++) {  
            comboBoxSemestres.addItem(i);
        }
        comboBoxSemestres.setLocation(125, 145);  
        comboBoxSemestres.setSize(70, 20);

        radioManha = new JRadioButton("Manhã", false);
        radioManha.setLocation(35, 210);  
        radioManha.setSize(80, 30);

        radioTarde = new JRadioButton("Tarde", false);
        radioTarde.setLocation(115, 210);  
        radioTarde.setSize(70, 30);

        radioNoite = new JRadioButton("Noite", false);
        radioNoite.setLocation(190, 210); 
        radioNoite.setSize(80,30);

        groupPeriodo = new ButtonGroup();
        groupPeriodo.add(radioManha);
        groupPeriodo.add(radioTarde);
        groupPeriodo.add(radioNoite);

        btnSalvar = new JButton("Salvar");
        btnSalvar.setLocation(10, 290);
        btnSalvar.setSize(100, 30);
        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnSalvarActionListener(e);
            }
        });

        btnLimpar = new JButton("Limpar");
        btnLimpar.setLocation(130, 290);
        btnLimpar.setSize(100, 30);
        btnLimpar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnLimparActionListener(e);
            }
        });

        btnConsultar = new JButton("Consultar");
        btnConsultar.setSize(100, 30);
        btnConsultar.setLocation(250, 290);
        btnConsultar.addActionListener(e -> {
            TelaListaCurso telaListaCurso = new TelaListaCurso(this);
            telaListaCurso.setVisible(true);
            this.setVisible(false);
        });

        btnDeletar = new JButton("Deletar");
        btnDeletar.setSize(100, 30);
        btnDeletar.setLocation(370, 290);
        btnDeletar.setVisible(false);
        btnDeletar.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(null, "Deseja realmente apagar o curso?", "Confirmação", JOptionPane.YES_NO_OPTION);
            if (confirm != JOptionPane.YES_OPTION) return;

            try {
                cursoService.delete(this.curso);         
                JOptionPane.showMessageDialog(null, "Curso apagado com sucesso!");
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
        add(labelSemestres);  
        add(labelPeriodo);
        add(labelErrorCurso);
        add(labelErrorSemestres); 
        add(labelErrorPeriodo);
        add(txtCurso);
        add(comboBoxSemestres);  
        add(radioManha);
        add(radioTarde);
        add(radioNoite);
        add(btnSalvar);
        add(btnLimpar);
        add(btnConsultar);
        add(btnDeletar);
    }
    
    private void btnSalvarActionListener(ActionEvent e) {
        String nome = txtCurso.getText();
        String periodo = null;
        Integer semestres = (Integer) comboBoxSemestres.getSelectedItem();
        
        if (radioManha.isSelected()) {
            periodo = "Manhã";
        } else if (radioTarde.isSelected()) {
            periodo = "Tarde";
        } else if (radioNoite.isSelected()) {
            periodo = "Noite";
        }
        
        curso.setNome(nome);
        curso.setPeriodo(periodo);
        curso.setSemestres(semestres);
        Integer id = curso.getId();
        
        try {
            cursoService.save(curso);
            
            if (id == null) {
                JOptionPane.showMessageDialog(null, "Curso cadastrado com sucesso!");
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
            if (errors.contains("semestres")) {
                labelErrorSemestres.setText(f.getErrors().get("semestres"));
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
        labelErrorSemestres.setText(""); 
        labelErrorPeriodo.setText("");
        txtCurso.setText("");
        comboBoxSemestres.setSelectedIndex(0);  
        groupPeriodo.clearSelection();
    }
    
    public void loadCurso(Curso curso) {
        labelTitulo.setText("Editando o curso ID " + curso.getId());
        btnDeletar.setVisible(true);
        
        this.curso = curso;
        txtCurso.setText(curso.getNome());
        comboBoxSemestres.setSelectedItem(curso.getSemestres());
        
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

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public CursoService getCursoService() {
        return cursoService;
    }

    public void setCursoService(CursoService cursoService) {
        this.cursoService = cursoService;
    }

}