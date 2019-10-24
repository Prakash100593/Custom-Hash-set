
public class MyHashSet<E> extends CustomHashSet<E> implements SetI <E>{
	/**
	 * Extends CustomHashSet and implements SetI interface.
	 */
	Node <E> head = null;
	public int bucketindex = -1;
	private E data;
	
	static int intitialcapacity = 10;
	
	public MyHashSet(int bucketsLength) {
		super(bucketsLength);
		// TODO Auto-generated constructor stub
	}


	
	public static void main(String args[] )      {
        SetI<String> aSet = new MyHashSet<String>(intitialcapacity);
        SetI<String> bSet = new MyHashSet<String>(intitialcapacity);
        SetI<String> cSet = new MyHashSet<String>(intitialcapacity);

        String[] aStrings = { "a", "b", "c" };
        String[] bStrings = { "A", "B", "C" };
        String[] cStrings = { "P", "Q", "R" };
        int [] hashcodearray = new int[intitialcapacity];

        
        aSet.add(aStrings[0]); aSet.add(aStrings[1]);           // setup a, b
        bSet.add(bStrings[0]); bSet.add(bStrings[1]);           // setup A, B
        cSet.add(cStrings[0]); cSet.add(cStrings[1]);           // setup A, B

        System.out.println("aSet = " + aSet );                  // --> a, b

        for (int index = 0; index < aStrings.length; index ++ ) {       // contans a and b, not c
                System.out.println("does " +
                                ( aSet.contains(aStrings[index]) ? "" : " not " ) + "contain: " +
                                aStrings[index] );
        }
        Object[] customArray = aSet.toArray();
        for(int j=0;j<customArray.length;j++)
        {
        	if(customArray[j]!= null)
        	System.out.println(customArray[j]);
        }


        System.out.println("aSet.remove(aStrings[0]); = " + aSet.remove(aStrings[0]) ); // contains b
        System.out.println("aSet.remove(aStrings[2]); = " + aSet.remove(aStrings[2]) ); // can not remove x
        System.out.println("aSet = " + aSet );

        aSet.addAll((MyHashSet<String>) bSet);                                      
        System.out.println("aSet = " + aSet );

        System.out.println("aSet = " + aSet );
        
        aSet.add(null); 
        // --> b, A, B, null
        System.out.println("cSet = " + cSet );        
        System.out.println("aSet = " + aSet );
        
        aSet.addAll((MyHashSet<String>) cSet);                                      
        System.out.println("aSet = " + aSet );

        aSet.removeAll((MyHashSet<String>) cSet);                                      
        System.out.println("aSet = " + aSet );

        System.out.println("aSet.remove(null); = " + aSet.remove(null) );       
 
  }


	@Override
	public boolean contains(Object o) {
		/**
		 * Checks if the hashset contains any object o.
		 * based on the generated hashcode.
		 * 
		 * @param - Object o to be found
		 * return - True/ False based on the conditions
		 */
		  int hashcode = getindex((E) o);
	
	       head = buckets[hashcode];
	       while (head != null)
	     {
	    	if(head.data!= null)
	    	{
	          if (head.data.equals(o)) 
	          {
	        	  return true;
	          }
	    	}
	    	else
	    		if(head.data==null && o==null)
	    		{
	    			return true;
	    			
	    		}
	          else
	          {
	          head = head.next;
	          }
	       }
		return false;
	}

			
	@Override
	public boolean add(E value) {
		/**
		 * Adds an Element based on the Hashcode value.
		 * If the Hashcode value is same, it will chain the
		 * element at the start of the link list.
		 * 
		 * In case the loadfactor increases 0.85, it will
		 * do rehashing thus increasing the capcity by twice 
		 * of initial and will move all the data from the old
		 * to new.
		 * 
		 * @param - Value - Element to be inserted
		 * return - True or false.
		 */
		   int hashcode = getindex(value);
		   
		   
		   head = buckets[hashcode];
		   
		   while (head != null)
		   {
		      if (head.data.equals(value)) 
		      { 
		    	  return false; 
		      }
		      head = head.next;
		   }
		   
		   Node<E> node = new Node<E>(value);
		   node.next = buckets[hashcode];
		   buckets[hashcode] = node;
		   size++;

		   if((size/buckets.length) >=0.85)
		   {
			   rehash();
		   }

		   
		   return true;
	}
	
	public boolean isEmpty()
	{
		/**
		 * Check if the hashset is empty
		 * i.e. is size is equal to 0.
		 * 
		 * return - True/false
		 */
		return size==0;
	}

	public int getindex(E Value) 
	{ 
		/**
		 * Generates the Hashcode value for the
		 * given element, if the enetered value is null
		 * the index returned will be 0
		 * 
		 * @param - Value - The element
		 * return - Index value(Hashvalue)
		 */
		if(Value == null)
			return 0;
		else
		{
	    int hashCode = Value.hashCode(); 
        hashCode = 31*hashCode + (Value==null ? 0 : Value.hashCode());
	    int index = (((hashCode+1) *17) % buckets.length); 
	    return index;
		}
	} 

	public int hashcode() 
	/**
	 * Returns the sum of all the hashvalue 
	 * of all the elements from the hashset
	 */
	{ 
	  int hashvalue=0;
		if(isEmpty())
	    {
	        System.out.println("The set is empty");
	        return 0;
	    }
	    else
	    {
	    	for(int i =0; i<buckets.length;i++)
	    	{
	    	
	    		if(buckets[i] != null) {
	    			hashvalue = hashvalue + i;
	    			}
	    		}
	    	return hashvalue;	
	    	} 
	}	
	
    public boolean addAll(MyHashSet <E> c){
    	/**
    	 * Adds all athe element from the new set
    	 * to the old set, if the entry is duplicated
    	 * the element will be present in the list only
    	 * once.
    	 */
            Node<E>[] oldnode = null;
            Node<E>[] newnode = null;
            
            oldnode = this.buckets;
            newnode = c.buckets;
            
            if(c.getClass() == this.getClass()) {

            	for(int i =0;i<newnode.length;i++)
            	{
            		if(newnode[i]!=null)
            		{
            		this.add((E) newnode[i].data);
            		}
            	}
                return true;
            }
            else{
                return false;
            }
    }

    public boolean removeAll(MyHashSet<E> c){
    	/**
    	 * Remove the common elements from the new set and old
    	 * set from the old set.
    	 */
        Node<E>[] oldnode = null;
        Node<E>[] newnode = null;

        oldnode = this.buckets;
        newnode = c.buckets;
    	
        boolean check = false;
        if(this.isEmpty()){
            System.out.println("Empty set");
            return false;
        }
        else{
        	for(int i =0;i<newnode.length && newnode[i]!= null;i++)
	        	{
        			if (this.contains((newnode[i].data)))
        				{
        					this.remove(newnode[i].data);
        					check = true;
        					this.size--;
        				}
	        	}
            }
        return check;


    }

    public boolean containsAll(MyHashSet<E> c){
    	/**
    	 * Checks if all the elements from the new set
    	 * are present in the old set.
    	 */

        Node<E>[] oldnode = null;
        Node<E>[] newnode = null;

        oldnode = this.buckets;
        newnode = c.buckets;
    	    	
    	
        boolean check = false;
        if(this.isEmpty()){
            System.out.println("Empty set");
            return false;
        }
        else{
        	for(int i =0;i<newnode.length && newnode[i]!= null;i++)
	        	{
        			if (this.contains((newnode[i].data)))
        				{
        					check = true;
        				}
        			else
        			{
        				check = false;
        				break;
        			}
	        	}
            }
        return check;



    }
    
    
	
	public String toString(){

	    /** toString
	     *
	     *  Overrides tostring method of Java class
	     *  to print the set instead of object reference.
	     *
	     */

	    if(isEmpty())
	    {
	        System.out.println("The set is empty");
	        return null;
	    }
	    else
	    {
    		String str="";
	    	for(int i =0; i<buckets.length;i++)
	    	{
	    		if(buckets[i] != null) {
		    		Node <E> node = buckets[i];
		    		String linkstr = "";
		    		while (node.next!= null)
		    		{
		    			linkstr = linkstr  + "/" +node.next.data ;		    			
		    			node = node.next;
		    		}
	    			linkstr = linkstr + "";
	    			str = str + "-->" + buckets[i].data + linkstr;
	    			}
	    		}
	    	return str;
	    }

	}
	

	@Override
	public void clear() {
		/**
		 * Clears all the elements from the 
		 * called object buckets array.
		 */
		for(int i=0;i<this.buckets.length;i++)
		{
			this.buckets[i]= null;
		}
		
	}

    private void rehash() {
    	
    	/**
    	 * It is called whenever the load factor is
    	 * increased to more than 0.85
    	 * 
    	 * It will double the size of the initial array
    	 * and will move the data from the old one to 
    	 * the new one based on new hash code.
    	 */
    	
    	 Node <E> [] old = buckets;
    	int  capacity = 2* intitialcapacity;
    	buckets = new Node[capacity];
        
        for (int i = 0; i < old.length; i++) {
                Node <E> e;
                e = old[i];
                while(e!= null)
                {	
                this.add(e.data);
                e=e.next;
                }
            }
    }	

	
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean	equals(Object o) {
		if(o==null)
			return false;
	    if(this == o) 
	            return true; 
	    if(o.getClass()!= this.getClass()) 
	            return false; 
	           
	        Node <E> temp = (Node<E>) o; 
	        return (temp.data == this.data); 
		
	}	
	
	
	@Override
	public boolean remove(Object o) {
		/**
		 * Removes the element from the hashset and decrements
		 * the size
		 */
		   @SuppressWarnings("unchecked")
		int hashcode = getindex((E) o);
		   
		   if(isEmpty()) {return false; }
		   else
		   {
			   Node <E> current = buckets[hashcode];
			   Node <E> previous = null;
			   if(contains(o)) {
				   while(current != null)
				   {
					   if(previous == null)
					   {
						   buckets[hashcode] = current.next;
					   }
					   else
					   {
						   previous.next = current.next;
					   }
					   size = size-1;
					   return true;   
				   }
				   previous = current;
				   current = current.next;
			   }
			   else
			   {
				   System.out.println("Element not present");
			   }
				   return false;
			   
		   }
		
	
	
	}




	@Override
	public int size() {
		/**
		 * returns the size of the current hashset
		 */
		// TODO Auto-generated method stub
		return size;
		}

	@Override
	public Object[] toArray() {
		/**
		 * Converts the list into a array
		 */
		// TODO Auto-generated method stub
		
		Object CustomArray [] = new Object [this.size];
		int arraycounter = 0;
		for (int i =0;i<buckets.length;i++)
		{
			if(buckets[i]!= null)
			{
				CustomArray[arraycounter] = buckets[i].data;
				arraycounter = arraycounter + 1;				
			}
		}
		
		return CustomArray;
	}

}
