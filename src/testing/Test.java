package testing;

import node.*;
import tree.*;

public class Test {
	
	public static void main (String [] args){
		float [] a = {1, 5, 5, 6};
		float [] b = {2, 4, 7, 6};
		float [] c = {6, 7, 8, 9};
		float [] d = {3, 5, 7, 6};
		float [] e = {5, 1, 10, 4};
		float [] f = {2, 5, 4, 15};
		RNode r = new RNode (5, 5);
		r.addEntries(new Entry (a), new Entry (b), new Entry (c), new Entry (d), new Entry (e), new Entry (f));
		
		//System.out.println(r.toString());
		
		RTree tree = new Quadratic(2);
		tree.insert(a);
		tree.insert(b);
		tree.insert(c);
		System.out.println(tree.toString());
	}
}
