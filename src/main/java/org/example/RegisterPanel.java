package org.example;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Toolkit;

public class RegisterPanel extends JPanel {

    JButton btnRegister;
    private JPanel contentPane;
    private JTextField usernameField;
    private JTextField fundsField;
    private JPasswordField passwordField;
    private String password,username,funds1;
    public RegisterPanel ()
    {
        setLayout(null);
        setBounds(100, 100, 840, 619);
        setBorder(new EmptyBorder(5, 5, 5, 5));


        JLabel headerLabel = new JLabel("Registration for customers");
        headerLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        headerLabel.setBounds(130, 20, 300, 30);
        add(headerLabel);

        JLabel lblUserName = new JLabel("User name");
        lblUserName.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblUserName.setBounds(154, 141, 91, 14);
        add(lblUserName);

        usernameField = new JTextField();
        usernameField.setBounds(282, 140, 129, 20);
        add(usernameField);
        usernameField.setColumns(10);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblPassword.setBounds(154, 174, 91, 14);
        add(lblPassword);

        passwordField = new JPasswordField();
        passwordField.setBounds(282, 173, 129, 20);
        add(passwordField);

        JLabel funds = new JLabel("Funds");
        funds.setFont(new Font("Tahoma", Font.PLAIN, 14));
        funds.setBounds(154, 204, 91, 14);
        add(funds);

        fundsField = new JTextField();
        fundsField.setBounds(282, 203, 129, 20);
        add(fundsField);

        btnRegister = new JButton("Register");
        btnRegister.setBounds(282, 250, 89, 23);
        add(btnRegister);

        btnRegister.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                password= passwordField.getText().toString().toLowerCase();
                username= usernameField.getText().toString().toLowerCase();
                funds1 = fundsField.getText().toString().toLowerCase();

                DB.addCustomer(username, password,funds1);

                JOptionPane.showMessageDialog(RegisterPanel.this, "Registered successfully!");

                usernameField.setText("");
                passwordField.setText("");
                fundsField.setText("");

                Login a= new Login();
                a.setVisible(true);
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(RegisterPanel.this);
                frame.dispose();
            }
        });


    }

}