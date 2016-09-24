package apps;

import java.io.IOException;
import java.util.ArrayList;

import structures.*;

public class Test {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Graph g2 = new Graph("graph5.txt");
		PartialTreeList list = MST.initialize(g2);
		
		ArrayList<PartialTree.Arc> arr = new ArrayList<PartialTree.Arc>();
		arr = MST.execute(list);
		System.out.println(arr);
		//before, when the remove method didn't decrement the size after
		//removing the front tree, the below statement would print the front twice
		//^^^^WHY??????????????
		/*for(PartialTree tree : list){
			
			System.out.println(tree.getRoot().neighbors.next.weight);
		}*/
	}

}
