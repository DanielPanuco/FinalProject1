/**
 * HashTable.java
 * @author Henry Choy, Mario Panuco, Nigel Erlund, Weifeng Bai, Thanyared Wong
 * CIS 22C, Final Project
 */
import java.util.ArrayList;

public class HashTable<T> {

    private int numElements;
    private ArrayList<List<T>> Table;

    /**
     * Constructor for the hash 
     * table. Initializes the Table to
     * be sized according to value passed
     * in as a parameter
     * Inserts size empty Lists into the
     * table. Sets numElements to 0
     * @param size the table size
     */
    public HashTable(int size) {
        Table = new ArrayList<>();
        for(int i = 0; i < size; i++) {
            Table.add(new List<T>());
        }
        numElements = 0;
    }

    /**Accessors*/
    //one string only necess
    //TODO: put this method in hash (need to call hashcode!!)
    
    /**
     * returns the hash value in the Table
     * for a given Object 
     * @param t the Object
     * @return the index in the Table
     */

    private int hash(String key) { //pass in some new parameters (take in a string key parameter)
    	int code = 0;
    	for (int i = 0; i < key.length(); i++) {
            code += (int) key.charAt(i);
        }
        return code % Table.size();
    }
    
    /**
     * counts the number of elements at this index
     * @param index the index in the Table
     * @precondition 0 <=  index < Table.length
     * @return the count of elements at this index
     * @throws IndexOutOfBoundsException
     */
    public int countBucket(int index) throws IndexOutOfBoundsException{
        if(index < 0 || index >= Table.size()) {
            throw new IndexOutOfBoundsException("countBucket(): "
                    + "index is outside bounds of the table");
        }
        return Table.get(index).getLength();
    }

    /**
     * returns total number of elements in the Table
     * @return total number of elements
     */
    public int getNumElements() {
        return numElements;
    }

    
//    public T get(T t) throws NullPointerException{
//        if(t == null) {
//            throw new NullPointerException("get: cannot get null");
//        }else {
//            int bucket = hash(t);
//            List<T> list = Table.get(bucket);
//            int position = list.linearSearch(t);
//            if(position == -1) {
//                return null;
//            }else {
//                list.iteratorToIndex(position);
//                return list.getIterator();
//            }
//        }
//    }
    /**
     * Accesses a specified element in the Table
     * @param t the element to search for //updated @Param
     * @return the element stored in the Table, 
     * or null if this Table does not contain t. 
     * @precondition t != null
     * @throws NullPointerException when t is null
     */
    //TODO: pass in the same single key (concatenate in other class, have only one get method)
    public T get(T t, String key) throws NullPointerException{
        if(t == null) {
            throw new NullPointerException("get: cannot get null");
        }else {
            int bucket = hash(key);
            List<T> list = Table.get(bucket);
            int position = list.linearSearch(t);
            if(position == -1) {
                return null;
            }else {
                list.iteratorToIndex(position);
                return list.getIterator();
            }
        }
    }

    /**
     * Determines whether a specified key is in 
     * the Table
     * @param t the element to search for
     * @return  whether the element is in the Table 
     * @precondition t != null
     * @throws NullPointerException when t is null
     */
//    public boolean contains(T t) throws NullPointerException{
//        if(t == null) {
//            throw new NullPointerException("contains: cannot contain null");
//        }else {
//            int bucket = hash(t);
//            return Table.get(bucket).linearSearch(t) == -1 ? false : true;
//        }
//    }
    //TODO: one contains, same thing. just have one key after prior concat
    public boolean contains(T t, String key) throws NullPointerException{
        if(t == null) {
            throw new NullPointerException("contains: cannot contain null");
        }else {
            int bucket = hash(key);
            return Table.get(bucket).linearSearch(t) == -1 ? false : true;
        }
    }
    
    /**Mutators*/

    
//    public void insert(T t) throws NullPointerException{
//        if(t == null) {
//            throw new NullPointerException("insert: cannot insert null");
//        }else {
//            int bucket = hash(t);
//            Table.get(bucket).addLast(t);
//            numElements++;
//        }
//    }
    
    /**
     * Inserts a new element in the Table
     * at the end of the chain in the bucket
     * @param t the element to insert
     * @precondition t != null
     * @throws NullPointerException when t is null
     */
    //TODO:one insert, same thing. just have one key after prior concat
    public void insert(T t, String key) throws NullPointerException{
        if(t == null) {
            throw new NullPointerException("insert: cannot insert null");
        }else {
            int bucket = hash(key);
            Table.get(bucket).addLast(t);
            numElements++;
        }

    }

    /**
     * removes the key t from the Table
     * calls the hash method on the key to
     * determine correct placement
     * has no effect if t is not in
     * the Table or for a null argument          
     * @param t the key to remove
     * @throws NullPointerException when t is null
     */
//    public void remove(T t) throws NullPointerException {
//        if(t == null) {
//            throw new NullPointerException("remove: object to be removed is null, "
//                    + "cannot remove");
//        }else {
//            int bucket = hash(t);
//            List<T> list = Table.get(bucket);
//            int position = list.linearSearch(t);
//            if(position != -1) {
//                list.iteratorToIndex(position);
//                list.removeIterator();
//                numElements--;
//            }
//        }
//    }
    //TODO:same thing here, one string key passed. one method
    public void remove(T t, String key) throws NullPointerException {
        if(t == null) {
            throw new NullPointerException("remove: object to be removed is null, "
                    + "cannot remove");
        }else {
            int bucket = hash(key);
            List<T> list = Table.get(bucket);
            int position = list.linearSearch(t);
            if(position != -1) {
                list.iteratorToIndex(position);
                list.removeIterator();
                numElements--;
            }
        }
    }

    /**
     * Clears this hash table so that it contains no keys.
     */
    public void clear() {
        int size = Table.size();
        Table = new ArrayList<>();
        for(int i = 0; i < size; i++) {
            Table.add(new List<T>());
        }
        numElements = 0;
    }

    /**Additional Methods*/

    /**
     * Prints all the keys at a specified
     * bucket in the Table. Tach key displayed
     * on its own line, with a blank line 
     * separating each key
     * Above the keys, prints the message
     * "Printing bucket #<bucket>:"
     * Note that there is no <> in the output
     * @param bucket the index in the Table
     */
    public void printBucket(int bucket) {
        if(bucket < 0 || bucket >= Table.size()) {
            System.out.println("Invalid bucket index!");
        }else {
            System.out.println("Printing bucket #" + bucket +": \n");
            System.out.print(Table.get(bucket));
        }
    }

    /**
     * Prints the first key at each bucket
     * along with a count of the total keys
     * with the message "+ <count> -1 more 
     * at this bucket." Each bucket separated
     * with two blank lines. When the bucket is 
     * empty, prints the message "This bucket
     * is empty." followed by two blank lines
     */
    public void printTable(){
        for(int i = 0; i < Table.size(); i++) {
            System.out.println("Bucket: " + i);
            if(Table.get(i).getLength() == 0) {
                System.out.println("This bucket is empty.");
            }else {
                System.out.println(Table.get(i).getFirst());
                System.out.println("+ " + (Table.get(i).getLength() - 1)
                        + " more at this bucket");
            }
            System.out.println("\n");
        }
    }

    /**
     * Starting at the first bucket, and continuing
     * in order until the last bucket, concatenates
     * all elements at all buckets into one String
     */
    @Override public String toString() {
        String result = "";
        for(int i = 0; i < Table.size(); i++) {
            if(Table.get(i).getLength() == 0) {
                continue;
            }
            result += Table.get(i);
        }
        return result;
    }
    
    
}