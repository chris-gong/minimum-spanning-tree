package apps;

import structures.*;
import java.util.ArrayList;

public class MST {
	
	/**
	 * Initializes the algorithm by building single-vertex partial trees
	 * 
	 * @param graph Graph for which the MST is to be found
	 * @return The initial partial tree list
	 */
	public static PartialTreeList initialize(Graph graph) {
	
		/* COMPLETE THIS METHOD */
		PartialTreeList list = new PartialTreeList();
		for(int i = 0; i < graph.vertices.length; i++){
			Vertex.Neighbor nbr = graph.vertices[i].neighbors;
			PartialTree tree = new PartialTree(graph.vertices[i]);
			while(nbr != null){
				tree.getArcs().insert(new PartialTree.Arc(graph.vertices[i], nbr.vertex , nbr.weight));
				nbr = nbr.next;
			}
			list.append(tree);
		}
		return list;
	}

	/**
	 * Executes the algorithm on a graph, starting with the initial partial tree list
	 * 
	 * @param ptlist Initial partial tree list
	 * @return Array list of all arcs that are in the MST - sequence of arcs is irrelevant
	 */
	public static ArrayList<PartialTree.Arc> execute(PartialTreeList ptlist) {
		
		/* COMPLETE THIS METHOD */
		ArrayList<PartialTree.Arc> arcs = new ArrayList<PartialTree.Arc>();
		while(ptlist.size() > 1){
			PartialTree t1 = ptlist.remove();
			MinHeap<PartialTree.Arc> t1arcs = t1.getArcs();
			while(!t1arcs.isEmpty() && t1arcs.getMin().v2.getRoot().equals(t1.getRoot())){
				t1arcs.deleteMin();
			}
			
			PartialTree.Arc arc = t1arcs.deleteMin();
			//System.out.println(arc);
			Vertex v = arc.v2;//or Vertex v = arc.v2.getRoot();
			PartialTree t2 = ptlist.removeTreeContaining(v);
			t1.merge(t2);
			ptlist.append(t1);
			arcs.add(arc);
		}
		
		
		return arcs;
	}
}
