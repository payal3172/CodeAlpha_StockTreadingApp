import java.util.*;
import java.util.Scanner;
//import java.util.ArrayList;

//Information about Stock
class Stock{
    private String name;
    private double price;
    private int quantity;

    public Stock(String name,double price,int quantity){
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
    //getter setter method
    public String getName(){
        return name;
    }
    public double getPrice(){
        return price;
    }
    public int getQuantity(){
        return quantity;
    }

    public void updatePrice(double price){
        this.price = price;
    }
    public void reduceQuantity(int quantity){
        this.quantity = quantity;
    }
    public void increaseQuantity(int quantity){
        this.quantity = quantity;
    }
}
//portfoli details 1.buy stock,2.sell stock
class Portfolio{
    static Map <String , Stock> stocks;//make this static for accessing in static method
    public Portfolio(){
        stocks = new HashMap <> ();
    }
    public static void buyStock(String name,double price,int quantity){
        Stock stock = stocks.getOrDefault(name,new Stock(name,price,0));
        stock.increaseQuantity(quantity);
        stock.updatePrice(price);
        stocks.put(name,stock);
        System.out.println(quantity + " Shares of" + name + " bought at" +price);
    }
    public static void sellStock(String name,int quantity){
        Stock stock = stocks.get(name);
        if(stock != null && stock.getQuantity() >= quantity){
            stock.reduceQuantity(quantity);
        
            System.out.println(quantity + "Shares of" + name +"sold");
        
            if(stock.getQuantity() == 0){
                stocks.remove(name);
            }
        }else{
            System.out.println("Not enough shares to sell");
        }
    }

    public static void displayPortfolio(){
        if(stocks.isEmpty()){
            System.out.println("Portfolio is Empty");
        }else{
            System.out.println("Your Portfolio");
        
            for(Stock stock : stocks.values()){
                System.out.println(stock.getName() +":" +stock.getQuantity() + "Shares @" +stock.getPrice());
            }
        }
    }
}
//main class
public class StockTreadingPlatform{
    
   static Scanner sc = new Scanner(System.in);
    static Portfolio portfolio = new Portfolio();
    public static void main(String [] args){
        while(true){
            System.out.println("\n Menu: \n1.Buy Stock \n2.Sell Stock \n3.vivew Portfolio \n4.Exit");
            System.out.println("Choose an option:");
            int choice = sc.nextInt();
            sc.nextLine();

            switch(choice){
                case 1 :
                    buyStock();
                    break;
                case 2 :
                    sellStock();
                    break;
                case 3 :
                    Portfolio.displayPortfolio();
                    break;
                case 4 :
                    System.out.println("Existing app");
                    System.exit(0);
                default :
                    System.out.println("Invalid Option. Try Again");
            }
        }
    }
    //printing of method buyStock
    private static void buyStock(){
        System.out.println("Enter Stock Name:");
        String name = sc.nextLine();
        sc.nextLine();

        System.out.println("Enter Price");
        double price = sc.nextDouble();
        sc.nextLine();

        System.out.println("Enter Quantity");
        int quantity = sc.nextInt();
        sc.nextLine();

        Portfolio.buyStock(name, price, quantity);
    }
    //printing of method of sellStock
    private static void sellStock(){
        System.out.println("Enter Stock Name");
        String name = sc.nextLine();

        System.out.println("Enter quantity to sell");
        int quantity = sc.nextInt();

        Portfolio.sellStock(name, quantity);
    }

    }

