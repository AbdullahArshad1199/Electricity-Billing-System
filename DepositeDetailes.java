package electricity.billing.systeme;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.ResultSet;
import net.proteanit.sql.DbUtils;

public class DepositeDetailes extends JFrame implements ActionListener {

    JTable table;
    JButton searchButton, printButton, recoverButton, deleteButton;
    JLabel meterNumLabel, monthLabel;
    JTextField meterField;
    Choice monthChoice;

    DepositeDetailes() {
        super("Deposit Details");

        setSize(700, 700);
        setLocation(400, 100);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        meterNumLabel = new JLabel("Search by Meter Number");
        meterNumLabel.setBounds(20, 20, 150, 20);
        add(meterNumLabel);

        meterField = new JTextField();
        meterField.setBounds(180, 20, 150, 20);
        add(meterField);

        monthLabel = new JLabel("Search by Month");
        monthLabel.setBounds(400, 20, 100, 20);
        add(monthLabel);

        monthChoice = new Choice();
        monthChoice.setBounds(520, 20, 150, 20);
        add(monthChoice);
        monthChoice.add("January");
        monthChoice.add("February");
        monthChoice.add("March");
        monthChoice.add("April");
        monthChoice.add("May");
        monthChoice.add("June");
        monthChoice.add("July");
        monthChoice.add("August");
        monthChoice.add("September");
        monthChoice.add("October");
        monthChoice.add("November");
        monthChoice.add("December");

        table = new JTable();
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select id, meter_no, month, units, total_bill, status, customer_id from billing_infoe");
            table.setModel(DbUtils.resultSetToTableModel(rs));
            table.removeColumn(table.getColumnModel().getColumn(6)); 

        } catch (Exception e) {
            e.printStackTrace();
        }

        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(0, 100, 700, 650);
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
            String str = "select id, meter_no, month, units, total_bill, status, customer_id from billing_infoe where meter_no = '" + meterField.getText() + "' AND month = '" + monthChoice.getSelectedItem() + "'";
            try {
                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery(str);
                table.setModel(DbUtils.resultSetToTableModel(rs));
                table.removeColumn(table.getColumnModel().getColumn(6)); 
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == recoverButton) {
            try {
                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery("select id, meter_no, month, units, total_bill, status, customer_id from billing_infoe");
                table.setModel(DbUtils.resultSetToTableModel(rs));
                table.removeColumn(table.getColumnModel().getColumn(6)); 
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == printButton) {
            try {
                table.print();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == deleteButton) {
            String meterNumber = meterField.getText();
            String selectedMonth = monthChoice.getSelectedItem();
            String deleteQuery = "delete from billing_infoe where meter_no = '" + meterNumber + "' AND month = '" + selectedMonth + "'";
            try {
                Conn c = new Conn();
                c.s.executeUpdate(deleteQuery);
                // Refresh the table after deletion
                ResultSet rs = c.s.executeQuery("select id, meter_no, month, units, total_bill, status, customer_id from billing_infoe");
                table.setModel(DbUtils.resultSetToTableModel(rs));
                table.removeColumn(table.getColumnModel().getColumn(6));
                JOptionPane.showMessageDialog(null, "Record deleted successfully");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new DepositeDetailes().setVisible(true);
    }
}
