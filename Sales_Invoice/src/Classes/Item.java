package Classes;

public class Item {
    public static int ItemNum;
    private String ItemName;
    private int Price;
    private int Count;
    private int Total;

    public Item(String itemName, int price, int count) {
        ItemName = itemName;
        Price = price;
        Count = count;
        Total = calculateTotal();
        //ItemNum++;
    }

    public int getItemNum() {
        return ItemNum;
    }

    public String getItemName() {
        return ItemName;
    }

    public int getPrice() {
        return Price;
    }

    public int getCount() {
        return Count;
    }

    public int getTotal() {
        return Total;
    }

    public void setItemNum(int itemNum) {
        ItemNum = itemNum;
    }

    public void setItemName(String itemName) {
        ItemName = itemName;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public void setCount(int count) {
        Count = count;
    }

    public void setTotal(int total) {
        Total = total;
    }

    public int calculateTotal(){
        return this.Price*this.Count;
    }
}
