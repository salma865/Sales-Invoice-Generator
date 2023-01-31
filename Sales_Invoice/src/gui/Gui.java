package gui;

import Classes.Invoice;
import Classes.Item;
import gui.Files;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;


public class Gui extends JFrame implements ActionListener {
    public static DefaultTableModel InvoiceModel;
    public static DefaultTableModel ItemsModel;
    private JTable InvoiceTable ;
    private JTable ItemsTable ;
    private String [] InvoiceCols = {"No." , "Customer" , "Date" , "Total"};
    private Object [][] InvoiceData = {} ;
    private String [] ItemsCols = {"No." ,"Name" , "Price" , "Count" , "TotalPrice"};
    private Object [][] ItemsData = {} ;

    private JButton Create ,Delete;
    private JLabel I_Number,I_Name ,I_Date , I_Total , I_Table;
    public static JTextField I_NumberText,I_NameText ,I_DateText , I_TotalText ;
    private JButton Add ,DeleteItem;
    private JMenuBar menu;
    private JMenu MainMenu;
    private JMenuItem LoadItem;
    private JMenuItem SaveItem;
    public static Data newdata=new Data();
    public static int rowindex;
    public static int Irowindex;
    JFileChooser ChoosePath1;
    JFileChooser ChoosePath2;
    File file1path;
    File file2path;
    public Gui(){
        super("Sales Invoice System");
        ///////////////////////////////////////////////////////////////////////// Invoice Table

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
        I_Table.setForeground(Color.WHITE);
        I_Total.setForeground(Color.WHITE);
        I_Date.setForeground(Color.WHITE);
        I_Name.setForeground(Color.WHITE);
        I_Number.setForeground(Color.WHITE);
        I_NumberText =new JTextField(2);
        I_NumberText.setEditable(false);
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
        ////////////////////////////////////////////////////////////////////////// Items Buttons
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
        ////////////////////////////////////////////////////////////////////////// Menu
        menu = new JMenuBar();
        LoadItem = new JMenuItem("Load File");
        SaveItem = new JMenuItem("Save File");
        LoadItem.addActionListener(this);
        SaveItem.addActionListener(this);
        MainMenu = new JMenu("File");
        MainMenu.add(LoadItem);
        MainMenu.add(SaveItem);
        menu.add(MainMenu);
        setJMenuBar(menu);
        ChoosePath1 =new JFileChooser();
        ChoosePath1.setCurrentDirectory(new File("."));
        ChoosePath2 =new JFileChooser();
        ChoosePath2.setCurrentDirectory(new File("."));

        setLocation(new Point(300, 100));
        setSize(new Dimension(1000, 700));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        InvoiceTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                rowindex =InvoiceTable.getSelectedRow();
                //System.out.print(rowindex);
                removeItemTable();
                getInvoiceItems(rowindex);


            }
        });

        ItemsTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Irowindex =ItemsTable.getSelectedRow();
                //System.out.print(rowindex);
                //removeItemTable();
                //getInvoiceItems(rowindex);


            }
        });
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
        else if (e.getSource().equals(Delete)){
            deleteSelectedInvoice(rowindex);
        }
        else if (e.getSource().equals(DeleteItem)){
            deleteSelectedItem(Irowindex);
        }
        else if (e.getSource().equals(SaveItem)){
            for(int i = 0; i < Invoice.InvoiceNum; ++i) {
                Files.saveToFile(Data.InvoicesList.get(i), Invoice.InvoiceNum);
            }
        }
        else if (e.getSource().equals(LoadItem)){
            InvoiceModel.setRowCount(0);
            ItemsModel.setRowCount(0);
            //removeItemTable();
            Data.InvoicesList.clear();
            //Data.temp.ItemsList.clear();
            int x = ChoosePath1.showDialog(null,"Choose Invoices file");
            if (x == JFileChooser.APPROVE_OPTION)
            {
                file1path = new File(ChoosePath1.getSelectedFile().getAbsolutePath());
            }
            x = ChoosePath2.showDialog(null,"Choose Items file");
            if (x == JFileChooser.APPROVE_OPTION)
            {
                file2path = new File(ChoosePath2.getSelectedFile().getAbsolutePath());
            }
            gui.Files.loadFile(file1path,file2path);
            removeItemTable();
        }


    }

    public void removeItemTable()
    {
        ItemsModel.setRowCount(0);

    }
    public void getInvoiceItems (int Index)
    {

        Invoice I = Data.InvoicesList.get(Index);
        Data.temp=I;
        I_NumberText.setText(Integer.toString(Index+1));
        ArrayList<Item> Items = I.getItemsList();
        for (int i = 0 ; i<Items.size();++i)
        {
            String ItemName= Items.get(i).getItemName();
            int ItemPrice= Items.get(i).getPrice();
            int ItemCount= Items.get(i).getCount();
            int ItemTotal= Items.get(i).getTotal();
            Object[] newRecord = {i , ItemName, ItemPrice , ItemCount ,ItemTotal};
            ItemsModel.addRow(newRecord);
        }
        updateIData(Index+1,I.getCustomerName(),I.getDate(),I.calcTotal());



    }

    public void deleteSelectedInvoice(int Index)
    {
        Data.InvoicesList.remove(Index);
        InvoiceModel.removeRow(Index);
        int Size=Data.InvoicesList.size();
        //Data.temp.InvoiceNum--;
        if(Size != 0) {
            Data.temp = Data.InvoicesList.get(Size);
        }
        removeItemTable();

    }

    public void deleteSelectedItem(int Index)
    {
        Data.temp.getItemsList().remove(Index);
        ItemsModel.removeRow(Index);
        int Size = Data.temp.getItemsList().size();
        //System.out.print(Index+" "+Size);
        //Data.ItemTemp.ItemNum--;
        if(Size != 0) {
            Data.ItemTemp = Data.temp.getItemsList().get(Size-1);
        }
        Data.temp.calcTotal();
        InvoiceModel.setValueAt(Data.temp.getTotalAmount(),Data.temp.getIndex(),3);
    }


}
