package Classes;

import java.util.ArrayList;

public class Invoice {
    public static int InvoiceNum = 0;
    private String CustomerName;
    private String Date;
    private int TotalAmount=0;
    private int index = 0;
    private int icount = 0;
    public ArrayList<Item> ItemsList = new ArrayList<Item>();

    public Invoice(String customerName, String date) {
        CustomerName = customerName;
        Date = date;
        InvoiceNum++;
        index = InvoiceNum;
    }

    public int getInvoiceNum() {
        return InvoiceNum;
    }

    public static void setInvoiceNum(int invoiceNum) {
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

    public ArrayList<Item> getItemsList() {
        return ItemsList;
    }

    public void setItemsList(ArrayList<Item> itemsList) {
        ItemsList = itemsList;
    }

    public int calcTotal(){
        int total=0;
        for (int i =0 ; i<ItemsList.size() ; i++){
            total+=ItemsList.get(i).calculateTotal();
        }
        this.TotalAmount = total;
        return total;
    }

    public void addItemDate(String name,int price,int count){
        ItemsList.add(new Item(name,price,count));
        calcTotal();
        icount++;
    }

}
