package views;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.WindowConstants;

public class TelaCursoProfessores extends JFrame {

    private TelaHome home;

    JMenuBar menuBar;
    JMenu menuHome;
    JMenuItem menuItem;

    public TelaCursoProfessores(TelaHome home) {
        this.home = home;
        initComponents();
    }

    public void initComponents() {
        setTitle("Professores do curso");
        setResizable(false);
        setSize(630, 670);
        setLocation(400, 250);
        setLayout(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        menuHome = new JMenu("Home");
        menuItem = new JMenuItem("Ir para home");
        menuItem.addActionListener(e -> {
            this.home.setVisible(true);
            this.dispose();
        });
        menuHome.add(menuItem);
    }   

    // Getters e Setters
    public TelaHome getTelaHome() {
        return home;
    }

    public void setTelaHome(TelaHome home) {
        this.home = home;
    }
    
}
