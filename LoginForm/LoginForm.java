import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginForm extends JDialog implements ActionListener {
    private JTextField txtEmail;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private JButton btnRegister;
    private JPanel loginPanel;

    DB db = new DB();
    User getUser;

    public LoginForm(JFrame parent) {
        super(parent);
        setTitle("Login");
        setContentPane(loginPanel);
        setMinimumSize(new Dimension(450, 474));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        btnLogin.setFocusable(false);
        btnRegister.setFocusable(false);
        btnLogin.addActionListener(this);
        btnRegister.addActionListener(this);

        setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        // Register button
        if(e.getSource() == btnRegister) {
            dispose();
            RegistrationForm register = new RegistrationForm(null);
        }


        // Login Button
        if(e.getSource() == btnLogin) {
            String email = txtEmail.getText();
            String password = String.valueOf(txtPassword.getPassword());

            getUser = db.getUser(email, password);

            if(getUser != null) {
                System.out.println("User: " + getUser.getName());
                dispose();
                DashBoardForm dashboard = new DashBoardForm(null);
            }else{
                JOptionPane.showMessageDialog(LoginForm.this,
                        "Email or Password is invalid",
                        "Try again",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }


}
