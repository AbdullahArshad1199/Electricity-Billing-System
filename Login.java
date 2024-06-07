package electricity.billing.systeme;

import java.sql.PreparedStatement;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Login extends JFrame implements ActionListener {
    JButton loginbutton, cancelbutton, signup;
    JTextField usernamee;
    JPasswordField passworde;
    JComboBox<String> loginase;
    JLabel secondloginpageicon4, loogelabel;

    Login() {
        super("Login page");
        getContentPane().setBackground(new Color(255, 153, 0));
        setLayout(null);

        ImageIcon loogeicon = new ImageIcon(ClassLoader.getSystemResource("icon/looge.png"));
        Image loogeimage = loogeicon.getImage().getScaledInstance(1366, 768, Image.SCALE_DEFAULT);
        ImageIcon loogeicon2 = new ImageIcon(loogeimage);
        loogelabel = new JLabel(loogeicon2);
        loogelabel.setBounds(0, 0, 1366, 768);
        add(loogelabel);

        JLabel username = new JLabel("Username");
        username.setBounds(570, 200, 100, 30);
        username.setFont(new Font("Arial", Font.BOLD, 16));
        username.setForeground(Color.WHITE);
        add(username);

        usernamee = new JTextField();
        usernamee.setBounds(670, 200, 200, 30);
        usernamee.setFont(new Font("Arial", Font.PLAIN, 16));
        add(usernamee);

        JLabel password = new JLabel("Password");
        password.setBounds(570, 250, 100, 30);
        password.setFont(new Font("Arial", Font.BOLD, 16));
        password.setForeground(Color.WHITE);
        add(password);

        passworde = new JPasswordField();
        passworde.setBounds(670, 250, 200, 30);
        passworde.setFont(new Font("Arial", Font.PLAIN, 16));
        add(passworde);

        JLabel loginas = new JLabel("Login as");
        loginas.setBounds(570, 300, 100, 30);
        loginas.setFont(new Font("Arial", Font.BOLD, 16));
        loginas.setForeground(Color.WHITE);
        add(loginas);

        loginase = new JComboBox<>(new String[]{"Admin", "Customer"});
        loginase.setBounds(670, 300, 200, 30);
        loginase.setFont(new Font("Arial", Font.PLAIN, 16));
        add(loginase);

        loginbutton = new JButton("Login");
        loginbutton.setBounds(650, 370, 100, 40);
        loginbutton.setFont(new Font("Arial", Font.BOLD, 16));
        loginbutton.addActionListener(this);
        add(loginbutton);

        cancelbutton = new JButton("Cancel");
        cancelbutton.setBounds(780, 370, 100, 40);
        cancelbutton.setFont(new Font("Arial", Font.BOLD, 16));
        cancelbutton.addActionListener(this);
        add(cancelbutton);

        signup = new JButton("Sign up");
        signup.setBounds(715, 430, 100, 40);
        signup.setFont(new Font("Arial", Font.BOLD, 16));
        signup.addActionListener(this);
        add(signup);

        ImageIcon secondloginpageicon = new ImageIcon(ClassLoader.getSystemResource("icon/second.jpg"));
        Image secondloginpageicon2 = secondloginpageicon.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
        ImageIcon secondloginpageicon3 = new ImageIcon(secondloginpageicon2);
        secondloginpageicon4 = new JLabel(secondloginpageicon3);
        secondloginpageicon4.setBounds(100, 200, 250, 250);
        add(secondloginpageicon4);

        getContentPane().setComponentZOrder(loogelabel, getContentPane().getComponentCount() - 1);

        setSize(1366, 768);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == loginbutton) {
            String checkusername = usernamee.getText();
            String checkpassword = new String(passworde.getPassword());
            String checkuser_type = (String) loginase.getSelectedItem();

            if (checkusername.isEmpty() || checkpassword.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter username and password.");
                return;
            }

            try {
                Conn c = new Conn();
                String query = "SELECT * FROM user_accountsss WHERE username = ? AND password = ? AND user_type = ?";
                PreparedStatement pst = c.c.prepareStatement(query);
                pst.setString(1, checkusername);
                pst.setString(2, checkpassword);
                pst.setString(3, checkuser_type);
                
                // Debugging: Print the parameters being used
                System.out.println("Executing query with parameters:");
                System.out.println("Username: " + checkusername);
                System.out.println("Password: " + checkpassword);
                System.out.println("User Type: " + checkuser_type);

                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    String meter = rs.getString("meter_no");
                    this.setVisible(false);
                    new Project(checkuser_type, meter).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid login");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == cancelbutton) {
            setVisible(false);
        } else if (ae.getSource() == signup) {
            new Signup();
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}
