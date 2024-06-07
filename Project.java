package electricity.billing.systeme;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Project extends JFrame implements ActionListener {
    String checkuser_type, meter;
    JLabel loogelabel1;

    Project(String checkuser_type, String meter) {
        this.checkuser_type = checkuser_type;
        this.meter = meter;
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        ImageIcon loogeicon = new ImageIcon(ClassLoader.getSystemResource("icon/elect1.jpg"));
        Image loogeimage = loogeicon.getImage().getScaledInstance(1366, 768, Image.SCALE_DEFAULT);
        ImageIcon loogeicon2 = new ImageIcon(loogeimage);
        loogelabel1 = new JLabel(loogeicon2);
        loogelabel1.setBounds(0, 0, 1366, 768); // Set the bounds to cover the entire frame
        add(loogelabel1);

        JLabel content = new JLabel("<html><h2>Objectives of Billing System Project</h2>"
                + "<p>Due to the automation of the process, billing systems make it easy to keep records of items. "
                + "This helps immensely in accounting and reports preparation by preventing errors and omissions.</p>"
                + "<h3>Easy Payments:</h3>"
                + "<ul>"
                + "<li>The payment process is made easy with the help of online invoices.</li>"
                + "<li>Customers can pay their bills online, reducing the need for physical transactions.</li>"
                + "</ul>"
                + "<h3>Improved Record Keeping:</h3>"
                + "<ul>"
                + "<li>Automation ensures that all transactions are recorded accurately.</li>"
                + "<li>This minimizes the risk of human error and ensures that records are up-to-date.</li>"
                + "</ul>"
                + "<h3>Efficient Report Generation:</h3>"
                + "<ul>"
                + "<li>Automated systems can quickly generate reports on sales, inventory, and other key metrics.</li>"
                + "<li>This enables better decision-making and efficient business management.</li>"
                + "</ul>"
                + "<h3>Time-Saving:</h3>"
                + "<ul>"
                + "<li>Automating billing processes saves time for both businesses and customers.</li>"
                + "<li>It allows staff to focus on more critical tasks rather than manual billing and record-keeping.</li>"
                + "</ul>"
                + "<h3>Customer Convenience:</h3>"
                + "<h3>Enhanced Security:</h3>"
                + "<p>Implementing a billing system with these objectives ensures a more streamlined, accurate, and efficient billing process, benefiting both the business and its customers.</p></html>");
        content.setBounds(50, 20, 1200, 700); // Adjust bounds to fit the text
        content.setFont(new Font("Arial", Font.PLAIN, 16)); // Set font
        content.setForeground(Color.WHITE); // Set text color to white
        loogelabel1.add(content);

        JMenuBar mb = new JMenuBar();
        mb.setPreferredSize(new Dimension(getWidth(), 40)); // Increase the size of the menu bar
        mb.setBackground(new Color(0, 0, 128)); // Set background color
        setJMenuBar(mb);

        JMenu master = new JMenu("Master");
        master.setForeground(Color.WHITE);
        master.setFont(new Font("SansSerif", Font.BOLD, 14)); // Custom font

        JMenuItem newcustomer = new JMenuItem("New Customer");
        styleMenuItem(newcustomer, "icon/icon1.png", 'D', KeyEvent.VK_D);

        JMenuItem customerdetails = new JMenuItem("Customer Details");
        styleMenuItem(customerdetails, "icon/icon2.png", 'M', KeyEvent.VK_M);

        JMenuItem depositdetails = new JMenuItem("Deposit Details");
        styleMenuItem(depositdetails, "icon/icon3.png", 'N', KeyEvent.VK_N);

        JMenuItem calculatebill = new JMenuItem("Calculate Bill");
        styleMenuItem(calculatebill, "icon/icon5.png", 'B', KeyEvent.VK_B);

        master.add(newcustomer);
        master.add(customerdetails);
        master.add(depositdetails);
        master.add(calculatebill);

        JMenu info = new JMenu("Information");
        info.setForeground(Color.WHITE);
        info.setFont(new Font("SansSerif", Font.BOLD, 14)); // Custom font

        JMenuItem updateinformation = new JMenuItem("Update Information");
        styleMenuItem(updateinformation, "icon/icon5.png", 'B', KeyEvent.VK_B);

        JMenuItem viewinformation = new JMenuItem("View Information");
        styleMenuItem(viewinformation, "icon/icon6.png", 'L', KeyEvent.VK_L);

        info.add(updateinformation);
        info.add(viewinformation);

        JMenu user = new JMenu("User");
        user.setForeground(Color.WHITE);
        user.setFont(new Font("SansSerif", Font.BOLD, 14)); // Custom font

        JMenuItem paybill = new JMenuItem("Pay Bill");
        styleMenuItem(paybill, "icon/icon4.png", 'R', KeyEvent.VK_R);

        JMenuItem billdetails = new JMenuItem("Bill Details");
        styleMenuItem(billdetails, "icon/icon6.png", 'B', KeyEvent.VK_B);

        user.add(paybill);
        user.add(billdetails);

        JMenu report = new JMenu("Report");
        report.setForeground(Color.WHITE);
        report.setFont(new Font("SansSerif", Font.BOLD, 14)); // Custom font

        JMenuItem generatebill = new JMenuItem("Generate Bill");
        styleMenuItem(generatebill, "icon/icon7.png", 'G', KeyEvent.VK_G);

        report.add(generatebill);

        JMenu utility = new JMenu("Utility");
        utility.setForeground(Color.WHITE);
        utility.setFont(new Font("SansSerif", Font.BOLD, 14)); // Custom font

        JMenuItem notepad = new JMenuItem("Note Pad");
        styleMenuItem(notepad, "icon/icon12.png", 'N', KeyEvent.VK_N);

        JMenuItem calculator = new JMenuItem("Calculator");
        styleMenuItem(calculator, "icon/icon9.png", 'C', KeyEvent.VK_C);

        utility.add(notepad);
        utility.add(calculator);

        JMenu mexit = new JMenu("Exit");
        mexit.setForeground(Color.WHITE);
        mexit.setFont(new Font("SansSerif", Font.BOLD, 14)); // Custom font

        JMenuItem exit = new JMenuItem("Exit");
        styleMenuItem(exit, "icon/icon11.png", 'W', KeyEvent.VK_W);

        mexit.add(exit);

        if (checkuser_type.equals("Admin")) {
            mb.add(master);
        } else {
            mb.add(info);
            mb.add(user);
            mb.add(report);
        }

        mb.add(utility);
        mb.add(mexit);

        setLayout(new FlowLayout());

        setVisible(true);
    }

    private void styleMenuItem(JMenuItem menuItem, String iconPath, char mnemonic, int acceleratorKey) {
        menuItem.setFont(new Font("monospaced", Font.BOLD, 14));
        menuItem.setBackground(Color.LIGHT_GRAY);
        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource(iconPath));
        Image image = icon.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        menuItem.setIcon(new ImageIcon(image));
        menuItem.setMnemonic(mnemonic);
        menuItem.addActionListener(this);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(acceleratorKey, ActionEvent.CTRL_MASK));
        menuItem.setOpaque(true);

        // Adding hover effect
        menuItem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                menuItem.setBackground(Color.GRAY);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                menuItem.setBackground(Color.LIGHT_GRAY);
            }
        });
    }

    public void actionPerformed(ActionEvent ae) {
        String msg = ae.getActionCommand();

        if (msg.equals("New Customer")) {
            new NewCustomer().setVisible(true);
        } else if (msg.equals("Customer Details")) {
            new CustomerDetail().setVisible(true);
        } else if (msg.equals("Deposit Details")) {
            new DepositeDetailes().setVisible(true);
        } else if (msg.equals("Calculate Bill")) {
            new CalculateBill().setVisible(true);
        } else if (msg.equals("View Information")) {
            new ViewInformation(meter).setVisible(true);
        } else if (msg.equals("Update Information")) {
            new UpdateInformation(meter).setVisible(true);
        } else if (msg.equals("Bill Details")) {
            new BillDetails(meter).setVisible(true);
        } else if (msg.equals("Generate Bill")) {
            new GenerateBill(meter).setVisible(true);
        } else if (msg.equals("Note Pad")) {
            try {
                Runtime.getRuntime().exec("notepad.exe");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (msg.equals("Calculator")) {
            try {
                Runtime.getRuntime();

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (msg.equals("Pay Bill")) {
            new PayBill(meter).setVisible(true);
        } else if (msg.equals("Exit")) {
            setVisible(false);
            new Login();
        }
    }

    public static void main(String[] args) {
        new Project("", "");
    }
}
