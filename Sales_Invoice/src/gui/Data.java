package gui;

import Classes.Invoice;
import Classes.Item;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

public class Data extends JFrame implements ActionListener {

    public static ArrayList<Invoice> InvoicesList = new ArrayList<Invoice>();
    private static JLabel Date, CustomerName;
    private static JTextField CustomerName_f, Date_f;
    private static JButton Create,Cancel;
    ///////////////////////////////////////////////////
    private static JLabel ItemName, Price , Count;
    private static JTextField ItemName_T , Price_T , Count_T;
    private static JButton CreateItem,CancelItem;
    private String PanalName ;
    public static int PanalNumber;
    public  static Invoice temp;
    public  static Item ItemTemp;

    public Data() {

        if (PanalNumber==1) {
            setName("Create Invoice");
            JPanel panel = new JPanel();
            panel.setLayout(null);
            setLocation(new Point(500, 300));
            setSize(new Dimension(400, 200));
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            CustomerName = new JLabel("Customer Name : ");
            CustomerName.setBounds(50, 30, 100, 20);
            panel.add(CustomerName);
            CustomerName_f = new JTextField();
            CustomerName_f.setBounds(150, 30, 193, 28);
            panel.add(CustomerName_f);
            ///////////////////////////////////////////////////
            Date = new JLabel("Date : ");
            Date.setBounds(50, 70, 40, 20);
            panel.add(Date);
            Date_f = new JTextField();
            Date_f.setBounds(150, 70, 193, 28);
            panel.add(Date_f);
            ///////////////////////////////////////////////////////////
            Create = new JButton("Create");
            Create.setBounds(100, 110, 90, 25);
            Create.setForeground(Color.WHITE);
            Create.setBackground(Color.BLACK);
            Create.addActionListener(this);
            panel.add(Create);

            Cancel = new JButton("Cancel");
            Cancel.setBounds(200, 110, 90, 25);
            Cancel.setForeground(Color.WHITE);
            Cancel.setBackground(Color.BLACK);
            Cancel.addActionListener(this);
            panel.add(Cancel);
            add(panel);
        }
        else if (PanalNumber==2)
        {
            setName("Create Item");
            JPanel panel = new JPanel();
            panel.setLayout(null);
            setLocation(new Point(500, 300));
            add(panel);
            setSize(new Dimension(400, 400));
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            ItemName = new JLabel("Item Name : ");
            ItemName.setBounds(50, 50, 100, 20);
            panel.add(ItemName);
            ItemName_T = new JTextField();
            ItemName_T.setBounds(150, 50, 193, 28);
            panel.add(ItemName_T);
            ///////////////////////////////////////////////////
            Price = new JLabel("Price : ");
            Price.setBounds(50, 100, 70, 20);
            panel.add(Price);
            Price_T = new JTextField();
            Price_T.setBounds(150, 100, 193, 28);
            panel.add(Price_T);
            ///////////////////////////////////////////////////
            Count = new JLabel("Count : ");
            Count.setBounds(50, 150, 70, 20);
            panel.add(Count);
            Count_T = new JTextField();
            Count_T.setBounds(150, 150, 193, 28);
            panel.add(Count_T);
            ///////////////////////////////////////////////////////////
            CreateItem = new JButton("Create");
            CreateItem.setBounds(100, 200, 90, 25);
            CreateItem.setForeground(Color.WHITE);
            CreateItem.setBackground(Color.BLACK);
            CreateItem.addActionListener(this);
            panel.add(CreateItem);

            CancelItem = new JButton("Cancel");
            CancelItem.setBounds(200, 200, 90, 25);
            CancelItem.setForeground(Color.WHITE);
            CancelItem.setBackground(Color.BLACK);
            CancelItem.addActionListener(this);
            panel.add(CancelItem);

        }
    }


    public void creatInvoice(){
        String customer_name=CustomerName_f.getText();
        String date=Date_f.getText();
        temp = new Invoice(customer_name,date);
        int total = temp.calcTotal();
        Object[] record ={temp.InvoiceNum,customer_name,date,total};
        Gui.InvoiceModel.addRow(record);

        int row_number =  Gui.InvoiceModel.getRowCount();
        temp.setIndex(row_number-1);
        InvoicesList.add(temp);

        Gui.updateIData(temp.InvoiceNum,CustomerName_f.getText(),Date_f.getText(),total);
        CustomerName_f.setText(null);
        Date_f.setText(null);
        Gui.newdata.setVisible(false);

    }

    public  void createItems()
    {
        String ItemName = ItemName_T.getText().toString();
        String Price = Price_T.getText().toString();
        String Count = Count_T.getText().toString();
        temp.addItemDate(ItemName,Integer.parseInt(Price),Integer.parseInt(Count));
        int CountTotal = Integer.parseInt(Price)*Integer.parseInt(Count);
        Object[] record = {'0' , ItemName, Integer.parseInt(Price) , Integer.parseInt(Count) ,CountTotal};
        Gui.ItemsModel.addRow(record);
        temp.calcTotal();
        Gui.InvoiceModel.setValueAt(temp.getTotalAmount(),temp.getIndex(),3);
        ItemName_T.setText(null);
        Price_T.setText(null);
        Count_T.setText(null);
        Gui.newdata.setVisible(false);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(Create)) {
            creatInvoice();

        }
        else if(e.getSource().equals(Cancel)){
            Data data = new Data();
            data.setVisible(false);
        }
        else if (e.getSource().equals(CreateItem)) {
            createItems();

        }
        else if(e.getSource().equals(CancelItem)){
            Data data = new Data();
            data.setVisible(false);
        }
    }
}
