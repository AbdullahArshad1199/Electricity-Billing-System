package electricity.billing.systeme;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Signup extends JFrame implements ActionListener {

    JPanel panel;
    JTextField meter, name, username;
    Choice accountType;
    JLabel heading, lblMeter, lblName, lblUsername, lblPassword;
    JButton back, create;
    JPasswordField password;

    public Signup() {
        setTitle("Create Account");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setBackground(new Color(204, 204, 255));
        panel.setLayout(null);
        add(panel);

        heading = new JLabel("Create Account");
        heading.setBounds(100, 50, 200, 40);
        heading.setFont(new Font("Tahoma", Font.BOLD, 20));
        panel.add(heading);

        accountType = new Choice();
        accountType.add("Admin");
        accountType.add("Customer");
        accountType.setBounds(350, 50, 200, 30);
        panel.add(accountType);

        lblMeter = new JLabel("Meter Number");
        lblMeter.setBounds(100, 110, 200, 30);
        lblMeter.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblMeter.setVisible(false);
        panel.add(lblMeter);

        meter = new JTextField();
        meter.setBounds(350, 110, 200, 30);
        meter.setFont(new Font("Tahoma", Font.PLAIN, 18));
        meter.setVisible(false);
        panel.add(meter);

        lblUsername = new JLabel("User Name");
        lblUsername.setBounds(100, 160, 200, 30);
        lblUsername.setFont(new Font("Tahoma", Font.BOLD, 18));
        panel.add(lblUsername);

        username = new JTextField();
        username.setBounds(350, 160, 200, 30);
        username.setFont(new Font("Tahoma", Font.PLAIN, 18));
        panel.add(username);

        lblName = new JLabel("Name");
        lblName.setBounds(100, 210, 200, 30);
        lblName.setFont(new Font("Tahoma", Font.BOLD, 18));
        panel.add(lblName);

        name = new JTextField();
        name.setBounds(350, 210, 200, 30);
        name.setFont(new Font("Tahoma", Font.PLAIN, 18));
        panel.add(name);

        meter.addFocusListener(new FocusAdapter() {
            public void focusLost(FocusEvent e) {
                if (accountType.getSelectedItem().equals("Customer")) {
                    String meterNumber = meter.getText();
                    if (!meterNumber.isEmpty()) {
                        try {
                            Conn c = new Conn();
                            ResultSet rs = c.s.executeQuery("SELECT name FROM customer_detailss WHERE meter_no = '" + meterNumber + "'");
                            if (rs.next()) {
                                name.setText(rs.getString("name"));
                            } else {
                                JOptionPane.showMessageDialog(null, "Meter Number not found");
                                name.setText("");
                            }
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                        }
                    }
                }
            }
        });

        lblPassword = new JLabel("Password");
        lblPassword.setBounds(100, 260, 200, 30);
        lblPassword.setFont(new Font("Tahoma", Font.BOLD, 18));
        panel.add(lblPassword);

        password = new JPasswordField();
        password.setBounds(350, 260, 200, 30);
        password.setFont(new Font("Tahoma", Font.PLAIN, 18));
        panel.add(password);

        accountType.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent ae) {
                String user = accountType.getSelectedItem();
                if (user.equals("Customer")) {
                    lblMeter.setVisible(true);
                    meter.setVisible(true);
                    name.setEditable(false);
                    name.setText("");
                } else {
                    lblMeter.setVisible(false);
                    meter.setVisible(false);
                    name.setEditable(true);
                }
            }
        });

        create = new JButton("Create");
        create.setBackground(Color.BLACK);
        create.setForeground(Color.WHITE);
        create.setBounds(140, 320, 140, 35);
        create.setFont(new Font("Tahoma", Font.BOLD, 18));
        panel.add(create);
        create.addActionListener(this);

        back = new JButton("Back");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setBounds(300, 320, 140, 35);
        back.setFont(new Font("Tahoma", Font.BOLD, 18));
        panel.add(back);
        back.addActionListener(this);

        ImageIcon signupImageIcon = new ImageIcon(getClass().getResource("/icon/signupImage.png"));
        Image signupImage = signupImageIcon.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
        ImageIcon signupImageIconScaled = new ImageIcon(signupImage);
        JLabel image = new JLabel(signupImageIconScaled);
        image.setBounds(570, 50, 250, 250);
        panel.add(image);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == create) {
            String user = accountType.getSelectedItem();
            String username = this.username.getText();
            String name = this.name.getText();
            String password = new String(this.password.getPassword());
            String meter = this.meter.getText();
            try {
                Conn c = new Conn();
                String str = null;
                if (user.equals("Admin")) {
                    str = "INSERT INTO user_accountsss (meter_no, username, name, password, user_type) VALUES (?, ?, ?, ?, ?)";
                    PreparedStatement pst = c.c.prepareStatement(str);
                    pst.setString(1, meter);
                    pst.setString(2, username);
                    pst.setString(3, name);
                    pst.setString(4, password);
                    pst.setString(5, user);
                    pst.executeUpdate();
                } else {
                    str = "INSERT INTO user_accountsss (meter_no, username, name, password, user_type) VALUES (?, ?, ?, ?, ?) ON DUPLICATE KEY UPDATE username = VALUES(username), password = VALUES(password), user_type = VALUES(user_type)";
                    PreparedStatement pst = c.c.prepareStatement(str);
                    pst.setString(1, meter);
                    pst.setString(2, username);
                    pst.setString(3, name);
                    pst.setString(4, password);
                    pst.setString(5, user);
                    pst.executeUpdate();
                }
                JOptionPane.showMessageDialog(null, "Account Created Successfully");
                this.setVisible(false);
                new Login().setVisible(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == back) {
            this.setVisible(false);
            new Login().setVisible(true);
        }
    }

    public static void main(String[] args) {
        new Signup();
    }
}
