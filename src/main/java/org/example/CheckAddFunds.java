package org.example;
import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.awt.event.ActionEvent;

public class CheckAddFunds extends JFrame {


    private String username;
    private double sum;

    private JTextField sumField;
    private JButton addButton;

    public String capitalizeFirstLetter(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }
        return input.substring(0, 1).toUpperCase() + input.substring(1);
    }
    public String decapitalizeFirstLetter(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }
        char firstChar = input.charAt(0);
        if (Character.isLowerCase(firstChar)) {

            return input;
        }
        char decapitalizedChar = Character.toLowerCase(firstChar);
        return decapitalizedChar + input.substring(1);
    }
    public CheckAddFunds(String username, double sum) {
        this.username = username;
        String username_to_show= this.username;
        username_to_show= capitalizeFirstLetter(this.username);
        String sum1=Double.toString(sum);
        setTitle("Check/Add Funds");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(500, 300));
        setResizable(false);
        setLocationRelativeTo(null);

        JPanel contentPane = new JPanel();
        contentPane.setLayout(null);
        setContentPane(contentPane);

        JTextArea messageArea = new JTextArea();
        messageArea.setText("Hello " + username_to_show + ", your remaining funds are: $" + sum1);
        messageArea.setFont(new Font("Tahoma", Font.BOLD, 16));
        messageArea.setBounds(20, 20, 460, 30);
        messageArea.setLineWrap(true);
        messageArea.setWrapStyleWord(true);
        messageArea.setEditable(false);
        contentPane.add(messageArea);

        JLabel sumLabel = new JLabel("Introduce the sum:");
        sumLabel.setBounds(20, 60, 150, 30);
        contentPane.add(sumLabel);

        sumField = new JTextField();
        sumField.setBounds(180, 60, 100, 30);
        contentPane.add(sumField);

        addButton = new JButton("Add funds");
        addButton.setBounds(300, 60, 100, 30);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sumText = sumField.getText().toString().toLowerCase();
                try {
                    CheckAddFunds.this.username=decapitalizeFirstLetter(username);

                    //adaugam la suma veche suma noua sumtext+sumold
                    String sumOld=DB.getFunds(CheckAddFunds.this.username);
                    double sumOld_int= Double.parseDouble(sumOld);
                    double sumText_int= Double.parseDouble(sumText);
                    double new_sum_int= sumOld_int + sumText_int;
                    sumText=Double.toString(new_sum_int);
                    DB.updateFunds(sumText,CheckAddFunds.this.username);
                    double newSum = Double.parseDouble(sumText);


                    CheckAddFunds newPage = new CheckAddFunds(CheckAddFunds.this.username, newSum);
                    newPage.setVisible(true);
                    dispose();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid sum format. Please enter a valid number.");
                }
            }
        });
        contentPane.add(addButton);

        pack();
    }
}
