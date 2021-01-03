public class ListNode<KeyType, ValueType> {

    // Private data fields of the inner class
    private KeyType key;
    private ValueType data;

    // Next node pointer
    private ListNode next;

    /**
     * Constructor
     * 
     * @param key-  The key of the node
     * @param data- The value of the node
     */
    public ListNode(KeyType key, ValueType data) {
      this.key = key;
      this.data = data;
      next = null;
    }

    /**
     * Returns the value for the node
     * 
     * @return- the value
     */
    public ValueType getValue() {
      return this.data;
    }
    
    /**
     * Returns the value for the node
     * 
     * @return- the value
     */
    public KeyType getKey() {
      return this.key;
    }

  }