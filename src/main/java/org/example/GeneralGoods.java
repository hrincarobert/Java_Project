package org.example;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class GeneralGoods extends JFrame {
    private String username;
    public String capitalizeFirstLetter(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }
        return input.substring(0, 1).toUpperCase() + input.substring(1);
    }
    public GeneralGoods(String username) {
        this.username = username;
        String username_to_show= this.username;
        username_to_show= capitalizeFirstLetter(this.username);
        setTitle("GeneralGoods");
        setSize(1300, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);


        JLabel welcomeLabel = new JLabel("<html><div style='text-align: center; font-size: 20pt; font-weight: bold;'>Welcome to the Genereal Goods section " + username_to_show + "!</div></html>");
        welcomeLabel.setBounds(500, 50, 300, 50);
        add(welcomeLabel);


        JButton backButton = new JButton("Back to menu");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ShopAround shoparound= new ShopAround(GeneralGoods.this.username);
                dispose();
            }
        });
        backButton.setBounds(20, 20, 120, 30);
        add(backButton);

        int quan_carpet = DB.getQuantity("7");
        String data_produs = DB.getDate("7");
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String data_curenta = currentDate.format(formatter);

        LocalDate date1 = LocalDate.parse(data_produs, formatter);
        LocalDate date2 = LocalDate.parse(data_curenta, formatter);

        long daysBetween_carpet = ChronoUnit.DAYS.between(date1, date2);
        String image_path = null;
        String text_label = null;

        if(quan_carpet == 0) {
            image_path = "C:/Users/Robert/IdeaProjects/Proiect_Store/images/rapire_SOLD.png";
            text_label = "Rapirea din serai carpet 1500$";
        }
        if(daysBetween_carpet > 15) {
            image_path="C:/Users/Robert/IdeaProjects/Proiect_Store/images/rapire_SALE.png";
            text_label = "Rapirea din serai carpet 1̶5̶0̶0̶$̶ -> 999$";
        }
        if(daysBetween_carpet <=15 && quan_carpet >0 )
        {
            image_path="C:/Users/Robert/IdeaProjects/Proiect_Store/images/rapire.png";
            text_label = "Rapirea din serai carpet 1500$";
        }

        ImageIcon image1 = new ImageIcon(image_path);
        JLabel imageLabel1 = new JLabel(image1);
        imageLabel1.setBounds(70, 150, image1.getIconWidth(), image1.getIconHeight());
        add(imageLabel1);


        //rapirea din serai
        JLabel textLabel1 = new JLabel(text_label);
        textLabel1.setBounds(70, 150 + image1.getIconHeight() + 10, image1.getIconWidth(), 20);
        textLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        textLabel1.setFont(new Font("Arial", Font.BOLD, 16));
        add(textLabel1);

        int quan_canapea = DB.getQuantity("8");
        data_produs = DB.getDate("8");
        date1 = LocalDate.parse(data_produs, formatter);
        long daysBetween_sofa = ChronoUnit.DAYS.between(date1, date2);

        if(quan_canapea == 0) {
            image_path = "C:/Users/Robert/IdeaProjects/Proiect_Store/images/canapea_SOLD.png";
            text_label = "Black sofa 700$";
        }
        if(daysBetween_sofa > 15) {
            image_path="C:/Users/Robert/IdeaProjects/Proiect_Store/images/canapea_SALE.png";
            text_label = "Black sofa 7̶0̶0̶$̶ -> 300$";
        }
        if(daysBetween_sofa <=15 && quan_canapea >0 )
        {
            image_path="C:/Users/Robert/IdeaProjects/Proiect_Store/images/canapea.png";
            text_label = "Black sofa 700$";
        }

        
        ImageIcon image2 = new ImageIcon(image_path);
        JLabel imageLabel2 = new JLabel(image2);
        imageLabel2.setBounds(470, 150, image2.getIconWidth(), image2.getIconHeight());
        add(imageLabel2);

        //canapea
        JLabel textLabel2 = new JLabel(text_label);
        textLabel2.setBounds(470, 150 + image2.getIconHeight() + 10, image2.getIconWidth(), 20);
        textLabel2.setHorizontalAlignment(SwingConstants.CENTER);
        textLabel2.setFont(new Font("Arial", Font.BOLD, 16));
        add(textLabel2);

        int quan_cana = DB.getQuantity("9");
        data_produs = DB.getDate("9");
        date1 = LocalDate.parse(data_produs, formatter);
        long daysBetween_cana = ChronoUnit.DAYS.between(date1, date2);

        if(quan_cana == 0) {
            image_path = "C:/Users/Robert/IdeaProjects/Proiect_Store/images/cana_SOLD.png";
            text_label = "Joan Rivers mug 15$";
        }
        if(daysBetween_cana > 15) {
            image_path="C:/Users/Robert/IdeaProjects/Proiect_Store/images/cana_SALE.png";
            text_label = "Joan Rivers mug 1̶5̶$̶ -> 7$";
        }
        if(daysBetween_cana <=15 && quan_cana >0 )
        {
            image_path="C:/Users/Robert/IdeaProjects/Proiect_Store/images/cana.png";
            text_label = "Joan Rivers mug 15$";
        }



        ImageIcon image3 = new ImageIcon(image_path);
        JLabel imageLabel3 = new JLabel(image3);
        imageLabel3.setBounds(870, 150, image3.getIconWidth(), image3.getIconHeight());
        add(imageLabel3);


        JLabel textLabel3 = new JLabel(text_label);
        textLabel3.setBounds(870, 150 + image3.getIconHeight() + 10, image3.getIconWidth(), 20);
        textLabel3.setHorizontalAlignment(SwingConstants.CENTER);
        textLabel3.setFont(new Font("Arial", Font.BOLD, 16));
        add(textLabel3);

        JTextField textField1 = new JTextField();
        textField1.setBounds(200, 150 + image1.getIconHeight() + 50, 80, 25);
        add(textField1);

        JButton buyButton1 = new JButton("Buy");
        buyButton1.setBounds(300, 150 + image1.getIconHeight() + 50, 60, 25);
        add(buyButton1);

        JTextField textField2 = new JTextField();
        textField2.setBounds(570, 150 + image2.getIconHeight() + 50, 80, 25);
        add(textField2);

        JButton buyButton2 = new JButton("Buy");
        buyButton2.setBounds(660, 150 + image2.getIconHeight() + 50, 60, 25);
        add(buyButton2);

        JTextField textField3 = new JTextField();
        textField3.setBounds(970, 150 + image3.getIconHeight() + 50, 80, 25);
        add(textField3);

        JButton buyButton3 = new JButton("Buy");
        buyButton3.setBounds(1060, 150 + image3.getIconHeight() + 50, 60, 25);
        add(buyButton3);

        buyButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(quan_carpet ==0 ) {
                    JOptionPane.showMessageDialog(null, "Out of stock!");
                }
                else {
                    LocalDate currentDate = LocalDate.now();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                    String data_curenta = currentDate.format(formatter);
                    String nr_buc_string = textField1.getText().toString().toLowerCase();
                    double nr_buc_int = Double.parseDouble(nr_buc_string);
                    double cost_final_int;

                    if(daysBetween_carpet > 15)
                         cost_final_int = nr_buc_int * 999;
                    else
                         cost_final_int = nr_buc_int * 1500;

                    String cost_final_string = Double.toString(cost_final_int);
                    String funds_old_string = DB.getFunds(GeneralGoods.this.username);
                    double funds_new_int = Double.parseDouble(funds_old_string);
                    funds_new_int -= cost_final_int;
                    textField1.setText("");
                    double final_quan = quan_carpet - nr_buc_int;
                    if (funds_new_int <= 0)
                        JOptionPane.showMessageDialog(null, "Insufficient funds!");
                    else {
                        String funds_new_string = Double.toString(funds_new_int);
                        DB.updateQuantityStock("7", final_quan);
                        DB.addSaleToDB(data_curenta, "7", "General Goods", cost_final_string);
                        DB.updateFunds(funds_new_string, GeneralGoods.this.username);
                        DB.updateHistory(GeneralGoods.this.username, "Rapirea din serai carpet", "7", "General Goods", data_curenta, cost_final_string);
                    }
                }
            }
        });

        buyButton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (quan_canapea == 0) {
                    JOptionPane.showMessageDialog(null, "Out of stock!");
                } else {
                    LocalDate currentDate = LocalDate.now();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                    String data_curenta = currentDate.format(formatter);
                    String nr_buc_string = textField2.getText().toString().toLowerCase();
                    double nr_buc_int = Double.parseDouble(nr_buc_string);
                    double cost_final_int;
                    if(daysBetween_sofa > 15)
                        cost_final_int = nr_buc_int * 300;
                    else
                        cost_final_int = nr_buc_int * 700;


                    String cost_final_string = Double.toString(cost_final_int);
                    String funds_old_string = DB.getFunds(GeneralGoods.this.username);
                    double funds_new_int = Double.parseDouble(funds_old_string);
                    funds_new_int -= cost_final_int;
                    textField2.setText("");
                    double final_quan = quan_canapea - nr_buc_int;
                    if (funds_new_int <= 0)
                        JOptionPane.showMessageDialog(null, "Insufficient funds!");
                    else {
                        String funds_new_string = Double.toString(funds_new_int);
                        DB.updateQuantityStock("8", final_quan);
                        DB.addSaleToDB(data_curenta, "8", "General Goods", cost_final_string);
                        DB.updateFunds(funds_new_string, GeneralGoods.this.username);
                        DB.updateHistory(GeneralGoods.this.username, "Black sofa", "8", "General Goods", data_curenta, cost_final_string);
                    }
                }
            }
        });

        buyButton3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (quan_cana == 0) {
                    JOptionPane.showMessageDialog(null, "Out of stock!");
                } else {
                    LocalDate currentDate = LocalDate.now();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                    String data_curenta = currentDate.format(formatter);
                    String nr_buc_string = textField3.getText().toString().toLowerCase();
                    double nr_buc_int = Double.parseDouble(nr_buc_string);
                    double cost_final_int;
                    if(daysBetween_cana > 15)
                        cost_final_int = nr_buc_int * 7;
                    else
                        cost_final_int = nr_buc_int * 15;
                    String cost_final_string = Double.toString(cost_final_int);
                    String funds_old_string = DB.getFunds(GeneralGoods.this.username);
                    double funds_new_int = Double.parseDouble(funds_old_string);
                    funds_new_int -= cost_final_int;
                    textField3.setText("");
                    double final_quan = quan_cana - nr_buc_int;
                    if (funds_new_int <= 0)
                        JOptionPane.showMessageDialog(null, "Insufficient funds!");
                    else {
                        String funds_new_string = Double.toString(funds_new_int);
                        DB.updateQuantityStock("9", final_quan);
                        DB.addSaleToDB(data_curenta, "9", "General Goods", cost_final_string);
                        DB.updateFunds(funds_new_string, GeneralGoods.this.username);
                        DB.updateHistory(GeneralGoods.this.username, "Joan Rivers mug", "9", "General Goods", data_curenta, cost_final_string);
                    }
                }
            }
        });
        setVisible(true);
    }


}
