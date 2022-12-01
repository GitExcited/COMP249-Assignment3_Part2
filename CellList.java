//Missing: CellNode copy constructor, cellnode clone(),
//CellList copy constructor,
import java.util.NoSuchElementException;

public class CellList {
    //Some stuff missing below vvvv
    private class CellNode{
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

        CellNode(CellNode _cellNode){
            //Dont know 
        }
        //Shallow copy
        public CellNode clone(){
            CellNode newCellNode = new CellNode(cellphone,next);
            return newCellNode;
        }
        public Cellphone getCellphone(){
            return cellphone;
        }        
        public CellNode getCellNode(){
            return next;
        }
        public void setCellphone(Cellphone _cellphone){
            cellphone = _cellphone;
        }
        public void setCellNode (CellNode _next){
            next = _next;
        }
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
    public void addToStart(Cellphone _cellphone){
        head = new CellNode(_cellphone,head);
        size++;
    }
    public void insertAtIndex (Cellphone _cellphone, int index) throws NoSuchElementException{
        if (index<0 || index> (size-1)) {
            throw new NoSuchElementException();
            //if this is thrown then system.exit(0)
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
        ///index = 3
        ///  0  1   2   3   4     <-- index
        ///  c1 c2  c3  c4  c5    <--Nodes
        ///  -  -   i
        ///  -  -   t
        ///  -  -   -   h   h.next
        ///         t------>h.next  
        ///****Result****///
        ///  c1 c2 c3  c5 
    }
    public void deleteFromStart(){
        if( head == null){System.out.println("Nothing to delete, list is empty!");return;}//Special case if list is empty
        CellNode t = head;
            head = t.next;
            size--;
            return;
    }
    public void replaceAtIndex(Cellphone _cellphone, int index){
        if (index<0 || index> (size-1)) {
            System.out.println("Error: index not found @ replaceAtIndex()");
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
    public boolean contains(long _serialNum){
        Cellphone c = find(_serialNum);
        if (c!= null)return true;
        else return false;
    }
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
            //If j index passes the last index of h Nodes then no equal phone was found
           // 0  1   2  3
           // c2 c3 c4 c6
            // - -  -  j

            t= t.next;
            i++;
        }

        return true;//This only runs if we passed through the whole while loop without finding two cellphones that are different
    }


}

