// --== CS400 File Header Information ==--
// Name: Ayushi Mishra
// Email: mishra37@wisc.edu
// Team: LB
// TA: Divyanshu Saxena
// Lecturer: Florian
// Notes to Grader: <optional extra notes>
import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * a generic class which implements the methods of class MapADT and generates functionality of a
 * hashtable
 * 
 * @author ayushi
 *
 * @param <KeyType>   specifies the type of key stored in the hashtable
 * @param <ValueType> specifies the type of value stored in the hashtable
 */
public class HashTableMap<KeyType, ValueType> implements MapADT<KeyType, ValueType> {
  // max capacity of table
  private int capacity;
  // current number of items in the hashtable
  private int size;
  // creates an array of linkedlist of type ListNode
  public LinkedList<ListNode<KeyType, ValueType>>[] arr;

  /**
   * Constructor which Initializes capacity and with the passed in value
   * 
   * @param initialCapacity
   */
  @SuppressWarnings("unchecked")
  public HashTableMap(int capacity) {
    this.capacity = capacity;
    this.size = 0;
    this.arr = new LinkedList[this.capacity];
  }

  /**
   * Constructor with default initial capacity and size at the beginning is zero due to no key-value
   * pair being assigned Also, setting all elements in the array of nodes to null
   * 
   */
  @SuppressWarnings("unchecked")
  public HashTableMap() {
    this.capacity = 10;
    this.size = 0;
    this.arr = new LinkedList[this.capacity];
  }

  /**
   * 
   * @return true if a key-value pair gets inserted in the hashtable, otherwise false
   * 
   */
  @SuppressWarnings("unchecked")
  @Override
  public boolean put(KeyType key, ValueType value) {
    // index is found by getting the hashVal of the key, by using built in hashcode()
    // we use abs function defined in Math class to get absolute value
    // for indices (to avoid negatives)
    int hashVal = key.hashCode();
    int index = Math.abs(hashVal % capacity);

    // creates a key-value pair with the given paramenter
    ListNode<KeyType, ValueType> toAdd = new ListNode<KeyType, ValueType>(key, value);
    // if hashtable has a null index, then we add the key-value pair by
    // initializing a new linkedlist
    if (arr[index] == null) {
      arr[index] = new LinkedList<ListNode<KeyType, ValueType>>();
    }
    // or we traverse through the linked list and check for similar key
    // and the method returns false without making any changes to the
    // hashtable
    else {
      for (int i = 0; i < arr[index].size(); i++) {
        if (arr[index].get(i).getKey().equals(key)) {
          // key already exists
          return false;
        }
      }
    }
    // adding key-value pair to the index calculated by using built in add
    // method of LinkedList class
    this.arr[index].add(toAdd);
    // increases size by 1 on adding a key-value pair
    size++;
    // grow by doubling and hashing, whenever its capacity becomes greater than or
    // equal to 80%
    if (((double) size() / arr.length) >= 0.8) {
      rehash();
    }
    // returns true if method adds a key-value pair
    return true;
  }

  /**
   * the method resizes table to twice the previous capacity and elements are rehashed by inserting
   * to the new table again.
   * 
   */
  @SuppressWarnings("unchecked")
  public void rehash() {
    // stores new capacity, which is double the old one
    int new_capacity = 2 * capacity;
    // stores new index
    int newindex;
    // Making a new array
    LinkedList<ListNode<KeyType, ValueType>>[] new_table = new LinkedList[new_capacity];

    ListNode<KeyType, ValueType> temp;
    // Traversing through the original array
    for (int i = 0; i < this.arr.length; i++) {
      // traverses only in the index is not null
      if (arr[i] != null) {
        // traverses through the linked list at each index
        for (int j = 0; j < arr[i].size(); j++) {
          // stores the key-value pair
          temp = this.arr[i].get(j);
          // calculates the new index of the key-value pair
          // by taking the absolute value of the hashcode modulus the hashtablemap's capacity
          newindex = Math.abs(temp.getKey().hashCode()) % new_capacity;

          if (new_table[newindex] == null) {
            new_table[newindex] = new LinkedList<ListNode<KeyType, ValueType>>();
          }
          // add the key value-pair at the head on linked list
          new_table[newindex].add(temp);
        }
      }
    }

    // this stores the new changed values of new hashtable and capacity
    this.capacity = new_capacity;
    this.arr = new_table;
  }

  /**
   * 
   * @return the value of the key that is passed as an argument
   * @throws an exception if the key is not found in the table
   */
  @Override
  public ValueType get(KeyType key) throws NoSuchElementException {
    // stores the index calculated by the hashcode corresponding to the key
    // we use abs function defined in Math class to get absolute value
    // for indices (to avoid negatives)
    int index = Math.abs(key.hashCode() % capacity);
    // creates a node object which we will use to get the value of the key
    ListNode<KeyType, ValueType> temp;
    // traverses only if the index corresponding to the key is not null
    if (arr[index] != null) {
      for (int i = 0; i < this.arr[index].size(); i++) {
        // temp stores the key-value pair of element at index
        temp = this.arr[index].get(i);
        // checks if the key equals the key of the element and returns its value
        if (temp.getKey().equals(key)) {
          return temp.getValue();
        }
      }
    }
    // throws an exception if the key does not exist in the table
    throw new NoSuchElementException();
  }

  /**
   * 
   * method calculates the number of key-value pair stored at anytime in the hashtable
   */
  @Override
  public int size() {
    return size;
  }

  /**
   * method to check if a particular key is present in the table
   * 
   * @returns true if the key is present in the hashtable and false otherwise
   */
  @Override
  public boolean containsKey(KeyType key) {
    // if no key-value pair are present in the table then it just
    // returns false
    if (size == 0) {
      return false;
    }
    // stores the index calculated by the hashcode corresponding to the key
    // we use abs function defined in Math class to get absolute value
    // for indices (to avoid negatives)
    int index = Math.abs(key.hashCode()) % capacity;
    // creates a node object which we will use to get the value of the key
    ListNode<KeyType, ValueType> temp;
    // if no item is present at that index(indicating a null value)
    // function returns false
    if (arr[index] == null) {
      return false;
    }
    // if array is not null at that index then it traverses through
    // the linked list to get the node which stores the key-value pair
    for (int i = 0; i < this.arr[index].size(); i++) {
      temp = this.arr[index].get(i);
      // checks if the key of that node equals the key that we were looking for
      if (temp.getKey().equals(key)) {
        // if found the method returns true
        return true;
      }
    }
    // else it returns false
    return false;
  }

  /**
   * 
   * method returns the value of the key which is removed
   */
  public ValueType remove(KeyType key) {
    if (key == null)
      return null;
    if (size == 0) {
      return null;
    }
    int index = Math.abs(key.hashCode()) % capacity;
    ListNode<KeyType, ValueType> temp;
    if (arr[index] == null) {
      return null;
    }
    for (int i = 0; i < this.arr[index].size(); i++) {
      temp = this.arr[index].get(i);
      if (temp == null) {
        return null;
      }
      if (temp.getKey().equals(key)) {
        // we use built-in remove function of the linkedlist
        this.arr[index].remove(temp);
        // return the reference of removed value
        size--;
        return temp.getValue();
      }
    }
    return null;
  }

  /**
   * clears the hashtable by simply assigning size to 0, which represents the key value pair
   */
  @SuppressWarnings("unchecked")
  @Override
  public void clear() {
    size = 0;
    // re-assigns the array to a new array of linked list with the new capacity
    int new_capacity = arr.length;
    arr = new LinkedList[new_capacity];
  }

  /**
   * 
   * @return returns the capacity of the hashtable
   */
  public int capacity() {
    return arr.length;
  }

}
