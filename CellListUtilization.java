import java.util.NoSuchElementException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class CellListUtilization {
    public static void main(String[] args) {

        //Populating CellList with Cell_Info.txt
        CellList Inventory = new CellList();
        CellList Inventory2 = new CellList();
        Scanner cellFile = null;
        //Opening file
        try{
         cellFile = new Scanner(new FileInputStream("Cell_info.txt"));//Open File
        }
        catch(FileNotFoundException e){
            System.out.println("File not found!!");
        }
        //Reading file and creating a cellphone object to be added to the CellList Inventory
        while(cellFile.hasNextLine()){
        long serialNum = cellFile.nextLong();
        String brand = cellFile.next();
        double price = cellFile.nextDouble();
        int year = cellFile.nextInt();
        Cellphone newPhone = new Cellphone(serialNum,brand,year,price);
        if(Inventory.contains(newPhone.getSerialNum()))continue;//Takes care of duplicates
        Inventory.addToStart(newPhone);

        }
        //Show INvnetory contents and close file
        Inventory.showContents();
        cellFile.close();

        //Search for a phone
        System.out.println("==============================");
        System.out.println("To search for a phone please enter its serial number (7 digits):");
        Scanner key = new Scanner(System.in);
        int NUMBEROFTRIES = 3; //Number of user searches
        int i = 0;
        while(i<NUMBEROFTRIES){

            long ID = 0;
            //Asking for a correct ID
            while(true){
                try{
                    
                    ID = key.nextLong();
                    break;
                }
                catch(Exception e){
                    System.out.println("Error please enter a correct number");
                    key.nextLine();
                }
            }
            Cellphone foundCell = Inventory.find(ID);
            if(foundCell!=null)System.out.println(foundCell.toString());
            i++;
            System.out.println("You have "+(NUMBEROFTRIES-i)+" more searches");

        }
        key.close();

        //**TESTING STUFF */
        //Insert at Index
        Cellphone TrialPhone = new Cellphone();
        try{
        Inventory.insertAtIndex(TrialPhone, 5);
        Inventory.showContents(); // Works. THere is a default phone at 5th index.
            //Testing some Special cases
            // Inventory.insertAtIndex(TrialPhone, -20);//Index <0 throws Exception
            // Inventory.insertAtIndex(TrialPhone, 100);//Index > size Throws Exception
            // Inventory.insertAtIndex(null, 2);//Passing null object shows error
        }
        catch(NoSuchElementException e){
            System.out.println("No such element Exception!");

        }
        catch(Exception e){
            System.out.println("Found an Exception!");

        }

        //deleteFromIndex
        try{
        Inventory.deleteFromIndex(0);// Works, deletes Cellphone at index 0
        Inventory.deleteFromIndex(2);// Works, deletes Cellphone at index 2
        Inventory.deleteFromIndex(21);//Works, deletes last element
        // Inventory.deleteFromIndex(21);//Throws No such elemtn exception
        Inventory.showContents();
        }
        catch(NoSuchElementException e){
            System.out.println("No such element Exception!");

        }
        catch(Exception e){
            System.out.println("Found an Exception!");

        }

        //deleteFromStart
        try{
        Inventory.deleteFromStart();//Works. Deletes first element
        Inventory2.deleteFromStart();//Works, shows message that list is empty thus nothing to delete.
        Inventory.showContents();
        }
        catch(NoSuchElementException e){
                System.out.println("No such element Exception!");
    
        }
        catch(Exception e){
                System.out.println("Found an Exception!");
    
        }

        //ReplaceAtIndex 
        try{
            Inventory.replaceAtIndex(TrialPhone, 0);//Works, replaces index 0 phone with default phone
            Inventory.replaceAtIndex(TrialPhone, 2);// Works, replaces index 2 phone with default phone.
            Inventory.replaceAtIndex(null, 2);//Works, shows null object passed error
            Inventory.replaceAtIndex(TrialPhone, 100);
            Inventory.showContents();
        }
        catch(NoSuchElementException e){
                System.out.println("No such element Exception!");
    
        }
        catch(Exception e){
                System.out.println("Found an Exception!");
    
        }
        
    }
}
