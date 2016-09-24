package apps;

import java.util.Iterator;
import java.util.NoSuchElementException;

import structures.Vertex;


public class PartialTreeList implements Iterable<PartialTree> {
    
	/**
	 * Inner class - to build the partial tree circular linked list 
	 * 
	 */
	public static class Node {
		/**
		 * Partial tree
		 */
		public PartialTree tree;
		
		/**
		 * Next node in linked list
		 */
		public Node next;
		
		/**
		 * Initializes this node by setting the tree part to the given tree,
		 * and setting next part to null
		 * 
		 * @param tree Partial tree
		 */
		public Node(PartialTree tree) {
			this.tree = tree;
			next = null;
		}
	}

	/**
	 * Pointer to last node of the circular linked list
	 */
	private Node rear;
	
	/**
	 * Number of nodes in the CLL
	 */
	private int size;
	
	/**
	 * Initializes this list to empty
	 */
    public PartialTreeList() {
    	rear = null;
    	size = 0;
    }

    /**
     * Adds a new tree to the end of the list
     * 
     * @param tree Tree to be added to the end of the list
     */
    public void append(PartialTree tree) {
    	Node ptr = new Node(tree);
    	if (rear == null) {
    		ptr.next = ptr;
    	} else {
    		ptr.next = rear.next;
    		rear.next = ptr;
    	}
    	rear = ptr;
    	size++;
    }

    /**
     * Removes the tree that is at the front of the list.
     * 
     * @return The tree that is removed from the front
     * @throws NoSuchElementException If the list is empty
     */
    public PartialTree remove() 
    throws NoSuchElementException {
    		
    		/* COMPLETE THIS METHOD */
    		PartialTree tree;
    		if(size == 0 || rear == null){
    			throw new NoSuchElementException();
    		}
    		else if(size == 1 || rear.next == rear){
    			tree = rear.tree;
    			rear = null;
    			size = 0;
    		}
    		else{
    			tree = rear.next.tree;
        		rear.next = rear.next.next;
        		size--;
    		}
    		
    		return tree;
    }

    /**
     * Removes the tree in this list that contains a given vertex.
     * 
     * @param vertex Vertex whose tree is to be removed
     * @return The tree that is removed
     * @throws NoSuchElementException If there is no matching tree
     */
    public PartialTree removeTreeContaining(Vertex vertex) 
    throws NoSuchElementException {
    		/* COMPLETE THIS METHOD */
    	//REDO THIS WITH A PREV POINTER
    	//AND CHECK IF DELETING THE REAR SO THAT U CAN RESET IT
    		//check if empty first before initializing 
    		if(size == 0 || rear == null){
    			throw new NoSuchElementException();
    		}
    		PartialTreeList.Node node = rear.next;
    		PartialTreeList.Node prev = rear;
    		PartialTree tree = node.tree;
    		int count = size;
    		boolean vertexWasFound = false;
    		while(count > 0){
    			
    			if(tree.getRoot().equals(vertex.getRoot())){
    				vertexWasFound = true;
    				break;
    			}
    			//update variables
    			count--;
    			prev = node;
    			node = node.next;
    			tree = node.tree;
    		}
    		if(!vertexWasFound){
    			throw new NoSuchElementException();
    		}
    		else{
    			//removing the tree
    			//if only element in the list
    			if(size == 1 || rear.next == rear){
    				rear = null;
    				size = 0;
    			}
    			//if deleting the rear
    			else if(node == rear){
    				rear = prev;
    				prev.next = node.next;
    				size--;
    			}
    			//deleting any other node and list > 1
    			else{
    				prev.next = node.next;
    				size--;
    			}
    			return tree;
    		}
    		
     }
    
    /**
     * Gives the number of trees in this list
     * 
     * @return Number of trees
     */
    public int size() {
    	return size;
    }
    
    /**
     * Returns an Iterator that can be used to step through the trees in this list.
     * The iterator does NOT support remove.
     * 
     * @return Iterator for this list
     */
    public Iterator<PartialTree> iterator() {
    	return new PartialTreeListIterator(this);
    }
    
    private class PartialTreeListIterator implements Iterator<PartialTree> {
    	
    	private PartialTreeList.Node ptr;
    	private int rest;
    	
    	public PartialTreeListIterator(PartialTreeList target) {
    		rest = target.size;
    		ptr = rest > 0 ? target.rear.next : null;
    	}
    	
    	public PartialTree next() 
    	throws NoSuchElementException {
    		if (rest <= 0) {
    			throw new NoSuchElementException();
    		}
    		PartialTree ret = ptr.tree;
    		ptr = ptr.next;
    		rest--;
    		return ret;
    	}
    	
    	public boolean hasNext() {
    		return rest != 0;
    	}
    	
    	public void remove() 
    	throws UnsupportedOperationException {
    		throw new UnsupportedOperationException();
    	}
    	
    }
}


