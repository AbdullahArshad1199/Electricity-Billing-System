package electricity.billing.systeme;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.ResultSet;
import net.proteanit.sql.DbUtils;

public class CustomerDetail extends JFrame implements ActionListener {

    JTable table;
    JButton searchButton, printButton, recoverButton, deleteButton;
    JLabel meterNumLabel;
    JTextField meterField;

    CustomerDetail() {
        super("Customer Details");

        setSize(700, 700);
        setLocation(400, 100);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        meterNumLabel = new JLabel("Meter Number");
        meterNumLabel.setBounds(20, 20, 100, 20);
        add(meterNumLabel);

        meterField = new JTextField();
        meterField.setBounds(120, 20, 150, 20);
        add(meterField);

        table = new JTable();
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from customer_detailss");
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }

        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(0, 100, 700, 550);
        add(sp);

        searchButton = new JButton("Search");
        searchButton.setBounds(20, 70, 80, 20);
        searchButton.addActionListener(this);
        add(searchButton);

        printButton = new JButton("Print");
        printButton.setBounds(120, 70, 80, 20);
        printButton.addActionListener(this);
        add(printButton);

        recoverButton = new JButton("Recover");
        recoverButton.setBounds(220, 70, 100, 20);
        recoverButton.addActionListener(this);
        add(recoverButton);

        deleteButton = new JButton("Delete");
        deleteButton.setBounds(340, 70, 80, 20);
        deleteButton.addActionListener(this);
        add(deleteButton);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == searchButton) {
            String meterNumber = meterField.getText();
            String query = "select * from customer_detailss where meter_no = '" + meterNumber + "'";
            try {
                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery(query);
                table.setModel(DbUtils.resultSetToTableModel(rs));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == printButton) {
            try {
                table.print();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == recoverButton) {
            try {
                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery("select * from customer_detailss");
                table.setModel(DbUtils.resultSetToTableModel(rs));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == deleteButton) {
            String meterNumber = meterField.getText();
            String deleteQuery = "delete from customer_detailss where meter_no = '" + meterNumber + "'";
            try {
                Conn c = new Conn();
                c.s.executeUpdate(deleteQuery);
                // Refresh the table after deletion
                ResultSet rs = c.s.executeQuery("select * from customer_detailss");
                table.setModel(DbUtils.resultSetToTableModel(rs));
                JOptionPane.showMessageDialog(null, "Customer record deleted successfully");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new CustomerDetail().setVisible(true);
    }
}
