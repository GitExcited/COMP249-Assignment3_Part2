import java.util.Scanner;

public class Cellphone implements Cloneable{

 private long serialNum = 0 ;
 private String brand; // single word
 private int year;
 private double price;
private static int counter; 

 public Cellphone() {
 brand = "Default";
 year = 0000;
 price = 0;
 serialNum = counter;
 counter++;
}

public Cellphone (long _serialNum,String _brand, int _year, double _price){
    brand = _brand;
    year = _year;
    price = _price;
    serialNum = _serialNum;
    counter++;
}

// This may break because we are passing serial Number as a parameter
public Cellphone( Cellphone _cellphone, long _serialNum){
    brand = _cellphone.getBrand();
    year = _cellphone.getYear();
    price = _cellphone.getPrice();
    serialNum = _serialNum;//If this serial numebr doesnt follow rules (must be next SN in line) bug may happen
    counter ++;
}

public Cellphone clone(){
    System.out.println("Pleas enter a new serial number for the clone phone");
    Scanner key = new Scanner(System.in);
    long ID;
    while(true){
        try{
         ID= key.nextLong();
        break;
        }
        catch(Exception e){
            System.out.println("Please enter a valid number");
        }
    }
    Cellphone newCell = new Cellphone(ID,brand,year,price);
    return newCell;
}

@Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Cellphone)) {
            return false;
        }
        Cellphone cell = (Cellphone) o;
        return (year ==cell.year && brand ==cell.brand && price ==cell.price);
    }


    @Override
    public String toString() {
        return "["+serialNum+": "+brand+" "+price+"$ "+year+"]";
    }

    public long getSerialNum() {
        return this.serialNum;
    }

    public void setSerialNum(long serialNum) {
        this.serialNum = serialNum;
    }

    public String getBrand() {
        return this.brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getYear() {
        return this.year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
}