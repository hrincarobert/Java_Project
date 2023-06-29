package org.example;



import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class History extends JFrame {
    private JTable stockTable;
    JComboBox<String> comp;
    DefaultTableModel model;
    private String username;

    public History(String username) {
        this.username=username;
        setLayout(null);
        setBounds(100, 100, 840, 619);
        JLabel lblStock = new JLabel("Purchase History");
        lblStock.setBounds(328, 26, 182, 21);
        lblStock.setFont(new Font("Tahoma", Font.PLAIN, 17));
        add(lblStock);



        model = new DefaultTableModel();
        stockTable = new JTable(model);
        stockTable.setBounds(98, 112, 645, 397);
        add(stockTable);
        model.addColumn("Product Name");
        model.addColumn("Company");
        model.addColumn("Date");
        model.addColumn("Cost");
        JScrollPane scroll = new JScrollPane(stockTable);
        scroll.setBounds(98, 112, 645, 397);
        add(scroll);

        comp = new JComboBox<String>();
        comp.setBackground(Color.WHITE);
        comp.setBounds(583, 81, 160, 20);
        add(comp);
        comp.addItem("All");
        comp.addItem("General Goods");
        comp.addItem("Food");
        comp.addItem("Electrics");
        comp.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent arg0) {

                updateTable();
            }
        });

        JLabel lblCompany = new JLabel("Company");
        lblCompany.setBounds(582, 68, 161, 14);
        add(lblCompany);

        JButton btnRefresh = new JButton("Refresh");
        btnRefresh.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                updateTable();
            }
        });
        btnRefresh.setBounds(457, 525, 138, 23);
        add(btnRefresh);
        updateTable();
        setVisible(true);

    }

    public void updateTable()
    {
        model.setRowCount(0);
        ArrayList<String> stock=new ArrayList<String>();
        stock=DB.getHistory(History.this.username, comp.getSelectedItem().toString());
        for(int x=0;x<stock.size();x+=4)
        {
            model.addRow(new Object[]{stock.get(x),stock.get(x+1),stock.get(x+2),stock.get(x+3)});
        }
    }
}
