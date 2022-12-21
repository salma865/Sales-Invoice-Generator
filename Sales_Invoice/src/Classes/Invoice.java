package Classes;

import java.util.ArrayList;

public class Invoice {
    private int InvoiceNum;
    private String CustomerName;
    private String Date;
    private int TotalAmount;
    public static int I_num=0;
    private int index;
    ArrayList<Item> ItemsList = new ArrayList<Item>();

    public Invoice(String customerName, String date) {
        CustomerName = customerName;
        Date = date;
        I_num++;
    }

    public int getInvoiceNum() {
        return InvoiceNum;
    }

    public void setInvoiceNum(int invoiceNum) {
        InvoiceNum = invoiceNum;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public int getTotalAmount() {
        return TotalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        TotalAmount = totalAmount;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int calcTotal(){
        int total=0;
        for (int i =0 ; i<ItemsList.size() ; i++){
            total+=ItemsList.get(i).calculateTotal();
        }
        return total;
    }

    public void addItemDate(String name,int price,int count){
        ItemsList.add(new Item(name,price,count));
        calcTotal();
    }
}
