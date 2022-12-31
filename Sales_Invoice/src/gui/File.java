package gui;

import Classes.Invoice;
import Classes.Item;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class File {

    public static FileWriter Invoices ;
    public static FileWriter Items ;

    public File(){
        try{
            Invoices = new FileWriter("invoice.csv",true);
            Items = new FileWriter("items.csv",true);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static void saveToFile(Invoice invoice,int invoices_count){
        try{
            Invoices = new FileWriter("invoice.csv",true);
            Items = new FileWriter("items.csv",true);
            //System.out.println(invoice.getInvoiceNum());
            Invoices.append((invoice.getIndex()+1)+",");
            Invoices.append(invoice.getCustomerName()+",");
            Invoices.append(invoice.getDate()+",");
            Invoices.append(invoice.getTotalAmount()+",");
            Invoices.append("\n");

            for (int i=0 ; i<invoice.getItemsList().size() ; ++i) {
                Items.append((invoice.getIndex()+1)+",");
                Item item = invoice.getItemsList().get(i);
                Items.append(item.getItemName()+",");
                Items.append(item.getPrice()+",");
                Items.append(item.getCount()+",");
                Items.append(item.getTotal()+",");
                Items.append("\n");
            }
            if(invoice.getInvoiceNum()==invoices_count){
                Invoices.close();
                Items.close();
            }
        }
        catch (Exception e) {
            e.getStackTrace();
        }
    }

    public static void loadFile(){
        try{
            java.io.File object = new java.io.File("invoice.csv");
            Scanner input = new Scanner(object);
            Invoice.setInvoiceNum(0);
            while (input.hasNextLine()){
                String data = input.nextLine();
                String invData[] = data.split(",");
                String name =invData[1];
                String date =invData[2];
                int total = Integer.parseInt(invData[3]);
                Data.temp =  new Invoice(name,date);
                Data.InvoicesList.add(Data.temp);
                Object record[]={Data.temp.InvoiceNum,name,date,total};
                Gui.InvoiceModel.addRow(record);
                Gui.updateIData(Data.temp.InvoiceNum,Gui.I_NameText.getText(),Gui.I_DateText.getText(),total);
            }
            input.close();


            java.io.File object2 = new java.io.File("items.csv");
            Scanner input2 = new Scanner(object2);
            while (input2.hasNextLine()) {
                String data = input2.nextLine();
                String itemData[] = data.split(",");
                int invNumber=Integer.parseInt(itemData[0]);
                String name=itemData[1];
                int price=Integer.parseInt(itemData[2]);
                int count=Integer.parseInt(itemData[3]);
                Data.InvoicesList.get(invNumber-1).addItemDate(name,price,count);
                Data.temp.addItemDate(name,price,count);
                int total=price*count;
                Object record[]={'0',name,price,count,total};
                Gui.ItemsModel.addRow(record);
                Data.temp.calcTotal();
                Gui.ItemsModel.setValueAt(Data.temp.getTotalAmount(),Data.temp.getIndex(),3);
            }
            input2.close();

        }
        catch (FileNotFoundException e) {
            System.out.println("Required File Not Found");
            e.printStackTrace();
        }
    }
}