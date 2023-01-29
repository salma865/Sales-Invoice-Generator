package gui;

import Classes.Invoice;
import Classes.Item;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.*;
import java.util.Scanner;

public class Files {

    public static FileWriter Invoices ;
    public static FileWriter Items ;

    public static Invoice temp;

    public Files(){
        try{
            Invoices = new FileWriter("invoice.csv",true);
            Items = new FileWriter("items.csv",true);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static void saveToFile(Invoice invoice, int invoices_count){
        try{
            Invoices = new FileWriter("invoice.csv", true);
            Items = new FileWriter("items.csv", true);
            Invoices.append(invoice.getIndex()+ ",");
            Invoices.append(invoice.getCustomerName() + ",");
            Invoices.append(invoice.getDate() + ",");
            Invoices.append(invoice.getTotalAmount() + ",");
            Invoices.append("\n");

            for(int i = 0; i < invoice.getItemsList().size(); ++i) {
                Items.append(invoice.getIndex()+ ",");
                Item item =invoice.getItemsList().get(i);
                Items.append(item.getItemName() + ",");
                Items.append(item.getPrice() + ",");
                Items.append(item.getCount() + ",");
                Items.append(item.getTotal() + ",");
                Items.append("\n");
            }
            if (invoice.getInvoiceNum() == invoices_count) {
                Invoices.close();
                Items.close();
            }
        }
        catch (Exception e) {
            e.getStackTrace();
        }
    }

    public static void loadFile(File invoice,File item){
        try{
            java.io.File object = invoice;
            Scanner input = new Scanner(object);
            Invoice.setInvoiceNum(0);
            while (input.hasNextLine()){
                String data = input.nextLine();
                String invData[] = data.split(",");
                int invNumber=Integer.parseInt(invData[0]);
                String name =invData[1];
                String date =invData[2];
                int total = Integer.parseInt(invData[3]);
                temp =  new Invoice(name,date);
                Data.InvoicesList.add(temp);
                Object record[]={invNumber,name,date,total};
                Gui.InvoiceModel.addRow(record);
                Gui.updateIData(invNumber,Gui.I_NameText.getText(),Gui.I_DateText.getText(),total);
                System.out.println("Invoice_Number : " + invNumber + " " +"Customer_Name : " + name + " " +"Date : " + date + " " + "Total Price : " + total );
            }
            input.close();


            java.io.File object2 = item;
            Scanner input2 = new Scanner(object2);
            while (input2.hasNextLine()) {
                String data = input2.nextLine();
                String itemData[] = data.split(",");
                int invNumber=Integer.parseInt(itemData[0]);
                String name=itemData[1];
                int price=Integer.parseInt(itemData[2]);
                int count=Integer.parseInt(itemData[3]);
                int total=Integer.parseInt(itemData[4]);
                //temp.ItemsList.clear();
                Data.InvoicesList.get(invNumber).addItemDate(name,price,count);
                //temp.addItemDate(name,price,count);
                Object record[]={'0',name,price,count,total};
                Gui.ItemsModel.addRow(record);
                //temp.calcTotal();
                //Gui.ItemsModel.setValueAt(temp.getTotalAmount(),temp.getIndex(),3);
                System.out.println("Invoice Number : " + invNumber + " " + "Item Name : " + name + " " + "Price : " + price + " " + "Count : " + count + " " + "Total :" + total );

            }
            input2.close();

        }
        catch (FileNotFoundException e) {
            System.out.println("Required File Not Found");
            e.printStackTrace();
        }
    }
}