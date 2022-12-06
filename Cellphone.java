//
// Part: 2
// Written by: Yash Patel, 40175454, David Ruiz, 40176885
//
// 

import java.util.Scanner;

//Celphone object
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


/** 
 * @return Cellphone
 * Creates a deep copy of the cellphone calling it and returns this copy 
 */
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


/** 
 * @param o
 * @return boolean
 * Verifies if two cellphones are equal. They are equal if their brand, year and price are the same. Returns true if equal and false if not
 */
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


    
    /** 
     * @return String
     * Returns the information on this cellphone as a string 
     */
    @Override
    public String toString() {
        return "["+serialNum+": "+brand+" "+price+"$ "+year+"]";
    }

    
    /** 
     * @return long
     * Returns the serial number attribute 
     */
    public long getSerialNum() {
        return this.serialNum;
    }

    
    /** 
     * @param serialNum
     * Modifies this cellphone's serial number to the passed long
     */
    public void setSerialNum(long serialNum) {
        this.serialNum = serialNum;
    }

    
    /** 
     * @return String
     * Returns brand attribute
     */
    public String getBrand() {
        return this.brand;
    }

    
    /** 
     * @param brand
     * Modifies this cellphone's brand to the passed string
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    
    /** 
     * @return int
     * Returns year attribute
     */
    public int getYear() {
        return this.year;
    }

    
    /** 
     * @param year
     * Modifies this cellphone's year to the passed integer
     */
    public void setYear(int year) {
        this.year = year;
    }

    
    /** 
     * @return double
     * Returns the price attribute
     */
    public double getPrice() {
        return this.price;
    }

    
    /** 
     * @param price
     * Modifies this cellphone's price attribute to the passed double
     */
    public void setPrice(double price) {
        this.price = price;
    }
    
}