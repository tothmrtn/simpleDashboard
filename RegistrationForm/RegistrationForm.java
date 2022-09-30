import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistrationForm extends JDialog implements ActionListener {
    private JTextField txtName;
    private JTextField txtEmail;
    private JPasswordField txtPassword;
    private JPasswordField txtConfirm;
    private JButton btnRegister;
    private JButton btnReset;
    private JPanel registerPanel;

    DB db = new DB();
    public User newUser;


    public RegistrationForm(JFrame parent) {
        super(parent);
        setTitle("Register");
        setContentPane(registerPanel);
        setMinimumSize(new Dimension(450, 360));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        btnRegister.setFocusable(false);
        btnReset.setFocusable(false);
        btnRegister.addActionListener(this);
        btnReset.addActionListener(this);

        setVisible(true);
    }


    // Special character checker
    public boolean hasSpecials(String text) {
        Pattern specials = Pattern.compile("[%@&*/\"']");
        Matcher matcher = specials.matcher(text);
        return matcher.find();
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        // Reset Button
        if(e.getSource() == btnReset) {
            txtName.setText("");
            txtEmail.setText("");
            txtPassword.setText("");
            txtConfirm.setText("");
        }


        // Register Button
        if(e.getSource() == btnRegister) {
            String name = txtName.getText();
            String email = txtEmail.getText();
            String password = String.valueOf(txtPassword.getPassword());
            String confirm = String.valueOf(txtConfirm.getPassword());

            if(name.isBlank() || email.isBlank() || password.isBlank() || confirm.isBlank()) {
                JOptionPane.showMessageDialog(this,
                        "Field cannot be empty!",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }else if(!email.contains("@") || !email.contains(".")) {
                JOptionPane.showMessageDialog(this,
                        "Add a valid email address!",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }else if(hasSpecials(name)) {
                JOptionPane.showMessageDialog(this,
                        "Name cannot contain special characters!",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }else if(!password.equals(confirm)) {
                JOptionPane.showMessageDialog(this,
                        "Passwords are not the same!",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }else{
                newUser = new User(name, email, password);
                db.addUser(newUser);
                JOptionPane.showMessageDialog(this,
                        newUser.getName() + " has been added!");
                dispose();
                LoginForm login = new LoginForm(null);
            }
        }
    }


}
