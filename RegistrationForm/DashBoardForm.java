import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DashBoardForm extends JDialog implements ActionListener {
    private JPanel dashBoardPanel;
    private JLabel lbAdmin;
    private JButton btnLogout;


    public DashBoardForm(JFrame parent) {
        super(parent);
        setTitle("Dashboard");
        lbAdmin.setText("Hi!");
        setContentPane(dashBoardPanel);
        setMinimumSize(new Dimension(500, 430));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        btnLogout.setFocusable(false);
        btnLogout.addActionListener(this);

        setVisible(true);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        // Logout button
        if(e.getSource() == btnLogout) {
            dispose();
            LoginForm login = new LoginForm(null);
        }
    }


}

