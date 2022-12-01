import java.util.NoSuchElementException;

public class CellListUtilization {
    public static void main(String[] args) {
    CellList list1 = new CellList();
    Cellphone c1 = new Cellphone("Iphone 1",2010,600);
    Cellphone c2 = new Cellphone("Iphone 2",2010,600);
    Cellphone c3 = new Cellphone("Iphone 3",2010,600);
    Cellphone c4 = new Cellphone("Iphone 4",2010,600);
    Cellphone c5 = new Cellphone("Iphone 5",2010,600);

    list1.addToStart(c5);
    list1.addToStart(c4);
    list1.addToStart(c3);
    list1.addToStart(c2);
    list1.addToStart(c1);

    CellList list2 = new CellList();
    list2.addToStart(c1);
    list2.addToStart(c4);
    list2.addToStart(c3);
    list2.addToStart(c2);
    list2.addToStart(c1);
    try{    
        list1.showContents(); //Prints from index 0 to size-1
        list2.showContents();
        
    // list1.insertAtIndex(c2, 0);
    // list1.insertAtIndex(c3, 1);
    // list1.insertAtIndex(c1,2);
    // list1.deleteFromIndex(4);
    // list1.replaceAtIndex(c3, 5);
    // list1.deleteFromStart();

    //**Testing find() */
    // System.out.println("Phone found *********");
    // try{
    // System.out.println( list1.find(4).toString());
    // }
    // catch(NullPointerException e){
    //     System.out.println("Null Pointer exception, phone was not found ");
    // }
    // System.out.println("*******************");

    // //* Testing contains()*/ 
    // int INDEX = -1;
    // System.out.println("Checking if list contains index " +INDEX+" = "+list1.contains(INDEX)); ;

    // }

    //*Testing equals method for lists */
    System.out.println("Should give false:  " +list1.equals(list2));


    }
    catch(NoSuchElementException e){
        System.out.println("Element could not be found at index! Now exiting");
        System.exit(0);
    }
  
    }

}
// c1
// 0

// c2 c1
// 0  1

// c2 c3 c1
// 0  1  2

// c2 c3 c1 c1

// OUTPUT
// c1  c2  c3