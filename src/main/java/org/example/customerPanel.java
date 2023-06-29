package org.example;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class  customerPanel extends JFrame {

    private String username;
    public String capitalizeFirstLetter(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }
        return input.substring(0, 1).toUpperCase() + input.substring(1);
    }

    public customerPanel(String username)
    {
        this.username = username;
        String username_to_show= this.username;
        username_to_show= capitalizeFirstLetter(this.username);
        setTitle("Customer Panel");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(400, 350));
        setResizable(false);
        setLocationRelativeTo(null); // Centers the frame on the screen

        JPanel contentPane = new JPanel();
        contentPane.setLayout(null);
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);

        JLabel welcomeLabel = new JLabel("Hello " + username_to_show + "! Check out:");
        welcomeLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        welcomeLabel.setBounds(20, 20, 300, 30);
        contentPane.add(welcomeLabel);

        JButton storesButton = new JButton("Shop");
        storesButton.setBounds(90, 80, 200, 50);
        contentPane.add(storesButton);

        JButton historyButton = new JButton("History");
        historyButton.setBounds(90, 150, 200, 50);
        contentPane.add(historyButton);

        JButton fundsButton = new JButton("Check/Add Funds");
        fundsButton.setBounds(90, 220, 200, 50);
        contentPane.add(fundsButton);

        JButton logoutButton = new JButton("Logout");
        logoutButton.setBounds(300, 10, 80, 30);
        contentPane.add(logoutButton);

        logoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                Login a= new Login();
                a.setVisible(true);
                dispose();


            }
        });

        fundsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String funds_string= DB.getFunds(customerPanel.this.username);
                double funds = Double.parseDouble(funds_string);
                CheckAddFunds checkAddFunds = new CheckAddFunds(customerPanel.this.username,funds);
                checkAddFunds.setVisible(true);
            }
        });

        storesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ShopAround shopAround = new ShopAround(customerPanel.this.username);
                shopAround.setVisible(true);
            }
        });

        historyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                History hist = new History(customerPanel.this.username);
                hist.setVisible(true);
            }
        });



        pack();

        setVisible(true);



    }



}


