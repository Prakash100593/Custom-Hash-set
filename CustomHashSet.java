class Node <E>{
	
	   /**
		 *    Node
		 *    creates a class Node with variables next and
		 *    previous of object type node
		 *    
		 */
	
	int key;
	E data;
	Node<E> next;
	Node<E> previous;
	
	
	public Node(E value){
		   /**
			 *    Node constructor
			 *    sets data of object type E with value.    
			 */
				
		this.data = value;
	}
	
	
}


public class CustomHashSet<E> {
	
	/**
	 *    CustomHashSet
	 *    creates a class CustomHashSet wit Node reference 
	 *    to Node class and instantiates buckets,head and size
	 *    
	 */
	
protected Node <E> [] buckets;
protected static int size;


public CustomHashSet(int bucketsLength) {
	buckets = new Node[bucketsLength];
	size = 0;
}



}