package Classes;

import Classes.Invoice;
import Classes.Item;
import gui.Gui;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static FileWriter file;
    public static void main(String[] args) throws IOException {
        Gui gui =new Gui();
        gui.setVisible(true);

        file = new FileWriter("SavedData.txt" , true);
    }

    public static void Save(Invoice invoice ,int index){
        try{
            invoice.setIndex(index);
            file.append("/////// Invoice "+(invoice.getInvoiceNum()-1)+" ///////"+"\n");
            file.append("Customer Name : "+invoice.getCustomerName()+"\n");
            file.append("Invoice Date : "+invoice.getDate()+"\n");
            file.append("Total : "+invoice.getTotalAmount()+"\n");
            file.append("Items Count : "+invoice.getItemsList().size()+"\n");
            for (int i=0 ; i<invoice.getItemsList().size() ; ++i) {
                Item item = invoice.getItemsList().get(i);
                file.append("Item "+(i+1)+"\n");
                file.append("Name : "+item.getItemName() + "     " +"Price : "+ item.getPrice() + "     " +"Count : "+ item.getCount() + "     " +"Item Total Price : "+ item.getTotal() + "\n");
                file.append("----------"+"\n");
            }
            file.close();
        }
        catch (Exception e) {
            e.getStackTrace();
        }
    }

    public static void Load(){
        try{
            File object = new File("DataFile.txt");
            Scanner input = new Scanner(object);
            while (input.hasNextLine()){
                String data = input.nextLine();
                System.out.println(data);
            }
            input.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("File Not Found");
            e.printStackTrace();
        }

    }

}