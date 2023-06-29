package org.example;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShopAround extends JFrame {

    private String username;
    public ShopAround(String username) {
        this.username = username;
        setTitle("Shop Around");
        setPreferredSize(new Dimension(800, 400));
        setResizable(false);
        setLocationRelativeTo(null);

        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());
        contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
        setContentPane(contentPane);

        JLabel titleLabel = new JLabel("Choose where you want to shop");
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBorder(new EmptyBorder(0, 0, 20, 0));
        contentPane.add(titleLabel, BorderLayout.NORTH);

        JPanel picturePanel = new JPanel();
        picturePanel.setBorder(new EmptyBorder(20, 0, 0, 0));
        contentPane.add(picturePanel, BorderLayout.CENTER);

        ImageIcon image1 = new ImageIcon("C:/Users/Robert/IdeaProjects/Proiect_Store/images/food.png");
        ImageIcon image2 = new ImageIcon("C:/Users/Robert/IdeaProjects/Proiect_Store/images/phones.png");
        ImageIcon image3 = new ImageIcon("C:/Users/Robert/IdeaProjects/Proiect_Store/images/goods.png");


        JButton button1 = new JButton();
        button1.setIcon(image1);
        button1.setPreferredSize(new Dimension(200, 200));
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                FoodPage foodPage = new FoodPage(ShopAround.this.username);
                dispose();
            }
        });

        JButton button2 = new JButton();
        button2.setIcon(image2);
        button2.setPreferredSize(new Dimension(200, 200));
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ElectricsPage electricpage = new ElectricsPage(ShopAround.this.username);
                dispose();

            }
        });

        JButton button3 = new JButton();
        button3.setIcon(image3);
        button3.setPreferredSize(new Dimension(200, 200));
        button3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GeneralGoods gengod = new GeneralGoods(ShopAround.this.username);
                dispose();
            }
        });



        picturePanel.add(button1);
        picturePanel.add(button2);
        picturePanel.add(button3);


        pack();
        setVisible(true);
    }
}