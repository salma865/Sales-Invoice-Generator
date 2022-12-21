package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Classes.*;

import java.util.ArrayList;

public class Gui extends JFrame implements ActionListener {
    public static DefaultTableModel InvoiceModel;
    private DefaultTableModel ItemsModel;
    private static JLabel Date, CustomerName;
    private JTable InvoiceTable ;
    private JTable ItemsTable ;
    private String [] InvoiceCols = {"No." ,"Date" , "Customer" , "Total"};
    private Object [][] InvoiceData = {} ;
    private String [] ItemsCols = {"No." ,"Name" , "Price" , "Count" , "TotalPrice"};
    private Object [][] ItemsData = {} ;

    private JButton Create ,Delete;
    private JLabel I_Number,I_Name ,I_Date , I_Total , I_Table;
    private static JTextField I_NumberText,I_NameText ,I_DateText , I_TotalText ;
    private JButton Add ,DeleteItem;

    public static Data newdata=new Data();
    public Gui(){
        super("Sales Invoice System");
        //////////////// Invoice Table

        JPanel InvoicePanal = new JPanel();
        setLayout(null);
        InvoicePanal.setBackground(Color.black);
        InvoicePanal.setBounds(0,0,500,400);
        InvoiceModel = new DefaultTableModel(InvoiceData, InvoiceCols);
        InvoiceTable = new JTable(InvoiceModel);
        InvoicePanal.add(new JScrollPane(InvoiceTable));
        add (InvoicePanal);
        //////////////////////////////////////////////////////////////////////////Invoice Buttons
        JPanel InvoiceButtons = new JPanel();
        setLayout(null);
        InvoiceButtons.setBackground(Color.black);
        InvoiceButtons.setBounds(0,400,500,700);
        Create = new JButton("Create Invoice");
        Delete = new JButton("Delete Invoice");
        Create.addActionListener(this);
        Delete.addActionListener(this);
        InvoiceButtons.add(Create);
        InvoiceButtons.add(Delete);
        add (InvoiceButtons);
        //////////////////////////////////////////////////////////////////////////Invoice Data
        JPanel InvoiceData = new JPanel();
        setLayout(null);
        InvoiceData.setLayout(new GridLayout(5,1) );
        InvoiceData.setBackground(Color.black);
        InvoiceData.setBounds(500,0,500,150);
        I_Number = new JLabel("Invoice Number");
        I_Name = new JLabel("Customer Name");
        I_Date = new JLabel("Date");
        I_Total = new JLabel("Total");
        I_Table = new JLabel("Items Table");
        I_NumberText =new JTextField(2);
        I_NameText =new JTextField(15);
        I_DateText =new JTextField(10);
        I_TotalText =new JTextField(3);
        InvoiceData.add(I_Number);
        InvoiceData.add(I_NumberText);
        InvoiceData.add(I_Name);
        InvoiceData.add(I_NameText);
        InvoiceData.add(I_Date);
        InvoiceData.add(I_DateText);
        InvoiceData.add(I_Total);
        InvoiceData.add(I_TotalText);
        InvoiceData.add(I_Table);

        add (InvoiceData);

        ////////////////////////////////////////////////////////////////////////// Items Table
        JPanel ItemPanel = new JPanel();
        setLayout(null);
        ItemPanel.setBackground(Color.black);
        ItemPanel.setBounds(500,150,500,400);
        ItemsModel = new DefaultTableModel(ItemsData, ItemsCols);
        ItemsTable = new JTable(ItemsModel);
        ItemPanel.add(new JScrollPane(ItemsTable));
        add (ItemPanel);
        /////////////////////////////////////////////////// Items Buttons
        JPanel ItemsButtons = new JPanel();
        setLayout(null);
        ItemsButtons.setBackground(Color.black);
        ItemsButtons.setBounds(500,550,500,200);
        Add = new JButton("Add");
        DeleteItem = new JButton("Delete");
        Add.addActionListener(this);
        DeleteItem.addActionListener(this);
        ItemsButtons.add(Add);
        ItemsButtons.add(DeleteItem);
        add (ItemsButtons);











        setLocation(new Point(300, 100));
        setSize(new Dimension(1000, 700));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void updateIData (int Index , String Name , String Date , float total)
    {
        I_NumberText.setText(Integer.toString(Index));
        I_DateText.setText(Date);
        I_NameText.setText(Name);
        I_TotalText.setText(Float.toString(total));


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(Create))
        {
            Data.PanalNumber=1;
            Data data = new Data();
            data.setVisible(true);

        }
        if (e.getSource().equals(Add))
        {
            Data.PanalNumber=2;
            Data data = new Data();
            data.setVisible(true);

        }


    }
}
