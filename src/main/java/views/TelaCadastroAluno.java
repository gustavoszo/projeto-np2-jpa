package views;

import model.entities.Aluno;
import model.entities.Curso;
import model.entities.Endereco;
import model.services.AlunoService;
import model.services.CursoService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.Color;

public class TelaCadastroAluno extends JFrame {

    private TelaHome home;
    private AlunoService alunoService = new AlunoService();
    private JLabel nome, cpf, email, dataNascimento, idCurso, cep, logradouro, numero, cidade, estado;
    private JTextField nomeField, cpfField, emailField, logradouroField, numeroField, cidadeField, estadoField;
    private JFormattedTextField dataNascField, cepField;
    private JList<Curso> listaCursos;
    private DefaultListModel<Curso> cursoListModel;
    private JButton btnSalvar, btnLimpar, btnConsultar, btnDeletar;
    private JMenuBar menuBar;
    private JMenu menuHome;
    private JMenuItem menuItem;
    private CursoService cursoService = new CursoService();

    // Labels de erro
    private JLabel erroNome, erroCpf, erroEmail, erroDataNascimento, erroCep, erroLogradouro, erroNumeroVazio, erroNaoNumero, erroCidade, erroEstado, erroCurso;

    public TelaCadastroAluno(TelaHome home) {
        this.home = home;
        initComponents();
    }

    private void initComponents() {
        setBounds(400, 250, 550, 700); // Ajustei a altura da tela para comportar mais campos
        getContentPane().setLayout(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        menuHome = new JMenu("Home");
        menuItem = new JMenuItem("Ir para home");
        menuItem.addActionListener(e -> {
            this.home.setVisible(true);
            this.dispose();
        });
        menuHome.add(menuItem);

        // Labels
        nome = new JLabel("Nome:");
        nome.setBounds(30, 30, 100, 25);

        cpf = new JLabel("CPF:");
        cpf.setBounds(30, 70, 100, 25);

        email = new JLabel("E-mail:");
        email.setBounds(30, 110, 100, 25);

        dataNascimento = new JLabel("Data de Nascimento:");
        dataNascimento.setBounds(30, 150, 150, 25);

        cep = new JLabel("CEP:");
        cep.setBounds(30, 190, 100, 25);

        logradouro = new JLabel("Logradouro:");
        logradouro.setBounds(30, 230, 100, 25);

        numero = new JLabel("Número:");
        numero.setBounds(30, 270, 100, 25);

        cidade = new JLabel("Cidade:");
        cidade.setBounds(30, 310, 100, 25);

        estado = new JLabel("Estado:");
        estado.setBounds(30, 350, 100, 25);

        idCurso = new JLabel("Curso:");
        idCurso.setBounds(30, 390, 100, 25);

        // Labels de erro
        erroNome = new JLabel("Preencha este campo");
        erroNome.setBounds(390, 30, 150, 25);
        erroNome.setForeground(Color.RED);
        erroNome.setVisible(false);

        erroCpf = new JLabel("Preencha este campo");
        erroCpf.setBounds(390, 70, 150, 25);
        erroCpf.setForeground(Color.RED);
        erroCpf.setVisible(false);

        erroEmail = new JLabel("Preencha este campo");
        erroEmail.setBounds(390, 110, 150, 25);
        erroEmail.setForeground(Color.RED);
        erroEmail.setVisible(false);

        erroDataNascimento = new JLabel("Preencha este campo");
        erroDataNascimento.setBounds(390, 150, 150, 25);
        erroDataNascimento.setForeground(Color.RED);
        erroDataNascimento.setVisible(false);

        erroCep = new JLabel("Preencha este campo");
        erroCep.setBounds(390, 190, 150, 25);
        erroCep.setForeground(Color.RED);
        erroCep.setVisible(false);

        erroLogradouro = new JLabel("Preencha este campo");
        erroLogradouro.setBounds(390, 230, 150, 25);
        erroLogradouro.setForeground(Color.RED);
        erroLogradouro.setVisible(false);

        erroNumeroVazio = new JLabel("Preencha este campo");
        erroNumeroVazio.setBounds(390, 270, 150, 25);
        erroNumeroVazio.setForeground(Color.RED);
        erroNumeroVazio.setVisible(false);

        erroNaoNumero = new JLabel("Somente números");
        erroNaoNumero.setBounds(390, 270, 150, 25);
        erroNaoNumero.setForeground(Color.RED);
        erroNaoNumero.setVisible(false);

        erroCidade = new JLabel("Preencha este campo");
        erroCidade.setBounds(390, 310, 150, 25);
        erroCidade.setForeground(Color.RED);
        erroCidade.setVisible(false);

        erroEstado = new JLabel("Preencha este campo");
        erroEstado.setBounds(390, 350, 150, 25);
        erroEstado.setForeground(Color.RED);
        erroEstado.setVisible(false);

        erroCurso = new JLabel("Selecione um curso");
        erroCurso.setBounds(390, 390, 150, 25);
        erroCurso.setForeground(Color.RED);
        erroCurso.setVisible(false);

        // Text Fields
        nomeField = new JTextField();
        nomeField.setBounds(180, 30, 200, 25);

        cpfField = new JTextField();
        cpfField.setBounds(180, 70, 200, 25);

        emailField = new JTextField();
        emailField.setBounds(180, 110, 200, 25);

        // Inicializando o JFormattedTextField para a data de nascimento
        try {
            MaskFormatter maskFormatter = new MaskFormatter("##/##/####");
            dataNascField = new JFormattedTextField(maskFormatter);
            dataNascField.setBounds(180, 150, 200, 25);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        try {
            MaskFormatter maskFormatterCpf = new MaskFormatter("###.###.###-##");
            cpfField = new JFormattedTextField(maskFormatterCpf);
            cpfField.setBounds(180, 70, 200, 25);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // Inicializando o JFormattedTextField para o CEP
        try {
            MaskFormatter maskFormatterCep = new MaskFormatter("#####-###");
            cepField = new JFormattedTextField(maskFormatterCep);
            cepField.setBounds(180, 190, 200, 25);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        logradouroField = new JTextField();
        logradouroField.setBounds(180, 230, 200, 25);

        numeroField = new JTextField();
        numeroField.setBounds(180, 270, 200, 25);

        cidadeField = new JTextField();
        cidadeField.setBounds(180, 310, 200, 25);

        estadoField = new JTextField();
        estadoField.setBounds(180, 350, 200, 25);

        // List (Curso)
        cursoListModel = new DefaultListModel<>();
        listaCursos = new JList<>(cursoListModel);
        listaCursos.setBounds(180, 390, 200, 100);
        retornaListaCursos();

        // Botões
        btnSalvar = new JButton("Salvar");
        btnSalvar.setBounds(30, 530, 100, 40);
        btnSalvar.addActionListener(e -> {

            boolean valid = true;


            erroNome.setVisible(false);
            erroCpf.setVisible(false);
            erroEmail.setVisible(false);
            erroDataNascimento.setVisible(false);
            erroCep.setVisible(false);
            erroLogradouro.setVisible(false);
            erroNumeroVazio.setVisible(false);
            erroNaoNumero.setVisible(false);
            erroCidade.setVisible(false);
            erroEstado.setVisible(false);
            erroCurso.setVisible(false);

            // Validação de campos
            if (nomeField.getText().trim().isEmpty()) {
                erroNome.setVisible(true);
                valid = false;
            }
            if (cpfField.getText().replaceAll("[^0-9]", "").isEmpty()) {
                erroCpf.setVisible(true);
                valid = false;
            }
            if (emailField.getText().trim().isEmpty()) {
                erroEmail.setVisible(true);
                valid = false;
            }
            if (dataNascField.getText().replaceAll("[^0-9]", "").isEmpty()) { // Remove formatação
                erroDataNascimento.setVisible(true);
                valid = false;
            }
            if (cepField.getText().replaceAll("[^0-9]", "").isEmpty()) { // Remove formatação
                erroCep.setVisible(true);
                valid = false;
            }
            if (logradouroField.getText().trim().isEmpty()) {
                erroLogradouro.setVisible(true);
                valid = false;
            }
            try {
                if (numeroField.getText().trim().isEmpty()) {
                    erroNumeroVazio.setVisible(true);
                    valid = false;
                } else {
                    Integer.parseInt(numeroField.getText());
                }

            } catch (NumberFormatException ex) {
                erroNaoNumero.setVisible(true);
                valid = false;
            }
            if (cidadeField.getText().trim().isEmpty()) {
                erroCidade.setVisible(true);
                valid = false;
            }
            if (estadoField.getText().trim().isEmpty()) {
                erroEstado.setVisible(true);
                valid = false;
            }
            if (listaCursos.getSelectedValue() == null) {
                erroCurso.setVisible(true);
                valid = false;
            }

            // Se todas as validações passarem
            if (valid) {
                Aluno aluno = getAluno();
                alunoService.save(aluno);

                JOptionPane.showMessageDialog(this, "Aluno salvo com sucesso!");
            }
        });

        btnLimpar = new JButton("Limpar");
        btnLimpar.setBounds(150, 530, 100, 40);
        btnLimpar.addActionListener(e -> limparCampos());

        btnDeletar = new JButton("Deletar");
        btnDeletar.setVisible(false);
        btnDeletar.setBounds(150, 530, 100, 40);
        btnDeletar.addActionListener(e -> {
            Aluno aluno = getAluno();
            alunoService.delete(aluno);
        });


        btnConsultar = new JButton("Consultar");
        btnConsultar.setBounds(270, 530, 100, 40);
        btnConsultar.addActionListener(e -> {
            this.setVisible(false);
            new TelaListaAluno(this).setVisible(true);
        });



        menuBar = new JMenuBar();
        menuBar.add(menuHome);
        setJMenuBar(menuBar);
        // Adicionando componentes
        add(nomeField);
        add(nome);
        add(cpf);
        add(cpfField);
        add(email);
        add(emailField);
        add(dataNascimento);
        add(dataNascField);
        add(cep);
        add(cepField);
        add(logradouro);
        add(logradouroField);
        add(numero);
        add(numeroField);
        add(cidade);
        add(cidadeField);
        add(estado);
        add(estadoField);
        add(idCurso);
        add(listaCursos);
        add(btnSalvar);
        add(btnLimpar);
        add(btnConsultar);
        add(btnDeletar);

        // Adicionando as labels de erro
        add(erroNome);
        add(erroCpf);
        add(erroEmail);
        add(erroDataNascimento);
        add(erroCep);
        add(erroLogradouro);
        add(erroNumeroVazio);
        add(erroNaoNumero);
        add(erroCidade);
        add(erroEstado);
        add(erroCurso);

        setVisible(true);
    }

    private Aluno getAluno() {
        Aluno aluno = new Aluno();
        aluno.setNome(nomeField.getText());
        aluno.setCpf(cpfField.getText());
        aluno.setEmail(emailField.getText());

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date dataNascimento = sdf.parse(dataNascField.getText());
            aluno.setDataNascimento(dataNascimento);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }

        // Endereço
        Endereco endereco = new Endereco();
        endereco.setCep(cepField.getText());
        endereco.setLogradouro(logradouroField.getText());
        endereco.setNumero(Integer.parseInt(numeroField.getText()));
        endereco.setCidade(cidadeField.getText());
        endereco.setEstado(estadoField.getText());
        aluno.setEndereco(endereco);

        aluno.setCurso(listaCursos.getSelectedValue());
        return aluno;
    }

    private void limparCampos() {
        nomeField.setText("");
        cpfField.setText("");
        emailField.setText("");
        dataNascField.setText("");
        cepField.setText("");
        logradouroField.setText("");
        numeroField.setText("");
        cidadeField.setText("");
        estadoField.setText("");
        listaCursos.clearSelection();

        // Escondendo mensagens de erro
        erroNome.setVisible(false);
        erroCpf.setVisible(false);
        erroEmail.setVisible(false);
        erroDataNascimento.setVisible(false);
        erroCep.setVisible(false);
        erroLogradouro.setVisible(false);
        erroNumeroVazio.setVisible(false);
        erroNaoNumero.setVisible(false);
        erroCidade.setVisible(false);
        erroEstado.setVisible(false);
        erroCurso.setVisible(false);
    }

    private void retornaListaCursos() {
        List<Curso> cursos = cursoService.findAll();
        for (Curso curso : cursos) {
            cursoListModel.addElement(curso);
        }
    }

    public TelaHome getHome() {
        return home;
    }

    public void setHome(TelaHome home) {
        this.home = home;
    }

    public void loadAluno(Aluno alunoSelecionado) {
        if (alunoSelecionado != null) {
            // Preenche os campos com os dados do aluno
            nomeField.setText(alunoSelecionado.getNome());
            cpfField.setText(alunoSelecionado.getCpf());
            emailField.setText(alunoSelecionado.getEmail());

            // Formatando e setando a data de nascimento
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            dataNascField.setText(sdf.format(alunoSelecionado.getDataNascimento()));

            // Preenchendo os dados do endereço
            Endereco endereco = alunoSelecionado.getEndereco();
            if (endereco != null) {
                cepField.setText(endereco.getCep());
                logradouroField.setText(endereco.getLogradouro());
                numeroField.setText(String.valueOf(endereco.getNumero()));
                cidadeField.setText(endereco.getCidade());
                estadoField.setText(endereco.getEstado());
            }

            listaCursos.setSelectedValue(alunoSelecionado.getCurso(), true);
            btnDeletar.setVisible(true);
            cpfField.setEnabled(false);
            btnLimpar.setVisible(false);
        }
    }
}
