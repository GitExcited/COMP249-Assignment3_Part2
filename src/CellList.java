//
// Part: 2
// Written by: Yash Patel, 40175454, David Ruiz, 40176885
//
// 
import java.util.NoSuchElementException;

public class CellList {
    class CellNode{
        private Cellphone cellphone;
        private CellNode next;
    
        CellNode(){
            cellphone = null;
            next= null;
        }
        CellNode(Cellphone _cellphone, CellNode _cellnode){
            cellphone=_cellphone;
            next=_cellnode;
        }
        /**
         * Takes a cellNode as parameter and clones it. This creates a new clonedCellNode with a deep copy of the cellphone attribute and the same next
         * node. Then initializes this CellNode's attributes to the clonde cellphone and the attribute next passed from the cellNode from the parameter.
         * @param _cellNode
         */
        CellNode(CellNode _cellNode){
            CellNode clonedCellNode = _cellNode.clone();
            cellphone = clonedCellNode.getCellphone();
            next = clonedCellNode.next;
        }
        /**
         * Clone method returns a cell node that contains a hard copy of the cellphone attribute in the original cellnode. This cellphone 
         * is a hard copy with a serial number input by user. However, the next attribute is the same for the original cellnode and the clone.
         */
        public CellNode clone(){
            CellNode newCellNode = new CellNode(cellphone.clone(),next);
            return newCellNode;
        }
    
        
        /** 
         * @return Cellphone
         * Returns cellphone object
         */
        public Cellphone getCellphone(){
            return cellphone;
        }        
        
        /** 
         * @return CellNode
         * Returns node attribute
         */
        public CellNode getCellNode(){
            return next;
        }
        
        /** 
         * @param _cellphone
         * Modifies cellphone object to the passed cellphone 
         */
        public void setCellphone(Cellphone _cellphone){
            cellphone = _cellphone;
        }
        
        /** 
         * @param _next
         * Modifies next node to the passe node
         */
        public void setCellNode (CellNode _next){
            next = _next;
        }
        
        /** 
         * @return String
         * Returns cellphone information as a string
         */
        public String toString(){
            return cellphone.toString();
        }
    }

    private CellNode head; 
    private double size;
    CellList(){
        head = null;
        size = 0;
    }
    CellList(CellList _CellList){
        //Some sort of copy constructor
    }
    
    /** 
     * 
     * @param _cellphone
     * Adds cellphone node to the start of the Cell List
     */
    public void addToStart(Cellphone _cellphone){
        head = new CellNode(_cellphone,head);
        size++;
    }
    
    /** 
     * @param _cellphone
     * @param index
     * @throws NoSuchElementException
     * Inserts cellphone node at the specified index in the cell list. The previous node at that list and all the ones after are moved up one index.
     * 
     */
    public void insertAtIndex (Cellphone _cellphone, int index) throws NoSuchElementException{
        if (index<0 || index> (size-1)) {
            throw new NoSuchElementException();
            //if this is thrown then system.exit(0)
        }
        if(_cellphone==null){
            System.out.println("Please enter a valid cellphone. This one is null");
            return;
        }
        if(head== null) {
            System.out.println("List is empty, nothing was added");
            return;
        }
        if(index ==0 ){
            addToStart(_cellphone);
            return;
        }
        CellNode t = head;
        int i =0;
        while(i!= (index-1)){
            t= t.next;
            i++;
        }
        t.next = new CellNode(_cellphone, t.next);
        size++;
    }
    
    /** 
     * @param index
     * @throws NoSuchElementException
     * Deletes node at specified index from the cell list. 
     */
    public void deleteFromIndex(int index) throws NoSuchElementException{
        //Checks for valid index
        if (index<0 || index> (size-1)) {
            throw new NoSuchElementException();
            //if this is thrown then system.exit(0)
        }
        if(index ==0){deleteFromStart();return;} //Special Case where the index is the head (See deleteFromStart())

        //Loop to find node t before the target node h 
        CellNode t = head;
        int i =0;
        while(i+1!=index){
            t=t.next;
            i++;
        }
        
        CellNode h = t.next; 
        if(h.next == null) {t.next = null;size--;return;}//Special case if target node h is at the end of the linked list
        t.next =h.next; //In normal case, simply make node t point to the node after the target node h thus deleting node h.
        size--;
        
    }

    /**
     * Deletes the first node in the cell list
     */
    public void deleteFromStart(){
        if( head == null){
            System.out.println("Nothing to delete, list is empty!");
            return;}//Special case if list is empty
        CellNode t = head;
            head = t.next;
            size--;
            return;
    }
    
    /** 
     * @param _cellphone
     * @param index
     * Replaces node at passed index with the passed cell node in the cell list
     */
    public void replaceAtIndex(Cellphone _cellphone, int index){
        if (index<0 || index> (size-1)) {
            System.out.println("Error: index invalid for this list");
            return;
        }
        if(_cellphone==null){
            System.out.println("Error: Cellphone passed is null");
            return;
        }
        if(index ==0){
            CellNode h= head.next; //Node after head node
            head = new CellNode(_cellphone,h);
            return;
        }
        CellNode t = head;
        int i=0;
        while( i+1!=index){ //Loop breaks when t is the node before the target index node
            t= t.next;
            i++;
        }
        CellNode h = t.next; //h is the target node at target index
        t.next = new CellNode(_cellphone,h.next); //Node t (the one before h ) points now at the new node. the new node points at h.next. Thus replacing h
    }
    
    /** 
     * @param _serialNum
     * @return Cellphone
     * Attemps to find a cellphone node in the cell list with the same passed serial Number. The found cellphone is returned. If no cellphone is found,
     * the method returns null. Also keeps track of the number of iterations before finding the cellphone.
     */
    public Cellphone find(long _serialNum){
        if(_serialNum<0){
            System.out.println("Serial numbers must be poisitive Integers");
            return null;
        }
        CellNode t = head;
        //Special case: List is empty
        if( t== null){
            System.out.println("List is empty");
            return null;
        }
        int counter = 0;
        while(t.getCellphone().getSerialNum() != _serialNum){
            t= t.next;
            counter++;
            //Special case: no phone is found in the list (t reaches the end where t == null);
            if (t == null){
                System.out.println("No phone with that serial Number was found after "+counter+" iterations");
                 return null;
            }
        }
        System.out.println("Phone was found after "+counter+" iterations");
        return t.getCellphone();
    }
    
    /** 
     * @param _serialNum
     * @return boolean
     * Returns true if a cellphone is found on the cell list with the passed serial number. False if not
     */
    public boolean contains(long _serialNum){
        
        Cellphone c  = new Cellphone();

        if(_serialNum<0){
            c = null;
        }
        CellNode t = head;
        //Special case: List is empty
        if( t== null){
            c= null;
        }

        if( t!=null) {
            while(t.getCellphone().getSerialNum() != _serialNum){
                t= t.next;
                //Special case: no phone is found in the list (t reaches the end where t == null);
                if (t == null){
                    c= null;
                    break;
                }
            }
        }

        if (c!= null)return true;
        else return false;
    }

    //Prints the entire cell list containing each individual cell node and each of the cell node's cellphone attributes
    public void showContents(){
        if (head == null){System.out.println("NO contents in this list"); return;}
        CellNode t = head;
        System.out.println(" The current size of this list is "+ size+". Here are the contents of this list");
        System.out.println("=================================================================================");
        for (int i = 0; i < size; i++) {
            System.out.print(t.toString()+" --> ");
            t=t.next;
        }
        System.out.print("X \n");
    }
    /**
     * @param o
     * Two lists are equal if they contain the same number of nodes. Additionally, all nodes are the same but they dont have to be on the
     * same order. Each node is compared by their cellphone attributes which must have the same brand, year and date to be equal. This makes
     * two nodes equal.
     */
    public boolean equals(Object o){
        if (o == this)
        return true;
        if (!(o instanceof CellList)) {
            System.out.println("Returned false cause object is not a cellist");
        return false;
        }
        CellList list = (CellList) o;

        CellNode t= head;// This list's head
        CellNode h= list.head; //The second list (the on passed as attribute)'s head
        //Special case where lists aren't the same size ( thus cannot be equal)
        if( list.size != size){
            System.out.println("Lists arent the same size therefore they cannot be equal");
            return false; //Checking of their attributes are equal
        }
        //Special case where both files are empty
        if(list.size == 0 ){
            System.out.println("Both Lists are empty so they are technically equal");
            return true;
        }

        int i =0;
        while( i<size){
            Cellphone cell_t = t.getCellphone();

            int j =0;
            while(j<size){
                Cellphone cell_h = h.getCellphone();

                if(cell_t.equals(cell_h)) break;


                if(h.next == null){
                    System.out.println("A cellphone from one list was not found in the other: "+cell_t.toString()+"\n NOT FOUND IN LIST");
                    return false;// if this line runs, we reached the last node (that points to null, and it no math was found thus lists are not equal )
                }
                h= h.next;
                j++;
            }
           

            t= t.next;
            i++;
        }

        return true;//This only runs if we passed through the whole while loop without finding two cellphones that are different
    }


}

