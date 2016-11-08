package testing;

import java.util.LinkedList;

import node.*;
import tree.*;

public class Test {
	
	public static void main (String [] args){
		float [] a = {-9, -6, -6, 6};
		float [] b = {-5, 3, -3, 8};
		float [] c = {-8, 1, 1, 5};
		float [] d = {-2, -2, 1, 5};
		float [] e = {-1, 3, 7, 8};
		float [] f = {3, -10, 5, 2};
		float [] g = {-4, -5, 6, -1};
		float [] h = {-6, -4, 1, -9};
		float [] i = {-11, -7, -4, -10};
		float [] j = {-3, -6, 0, -8};
		float [] k = {4, -6, 11, -7};
		float [] l = {8, -3, 12, -8};
		RNode r = new RNode (5, 5);
		//r.addEntries(new Entry (a), new Entry (b), new Entry (c), new Entry (d), new Entry (e), new Entry (f));
		
		//System.out.println(r.toString());
//		
//		RTree tree = new Quadratic(2);
//		tree.insert(a);
//		tree.insert(b);
//		tree.insert(c);
//		System.out.println(tree.toString());
		
//		RTree tree = new Quadratic(2);
//		tree.insert(a);
//		tree.insert(b);
//		tree.insert(c);
//		tree.insert(d);
//		tree.insert(e);
//		tree.insert(f);
//		float [] aa = tree.getRoot().getMBR();
//		System.out.println(aa[0] + "$" + aa[1] + "$" + aa[2] + "$" + aa[3]);
		//System.out.println(tree.toString());
		
//		RLeaf q = new RLeaf(2,2);
//		q.addEntries(new Entry (a));
//		StringBuilder str = new StringBuilder();
//		System.out.println(q.treeString(str));
		
		RTree t = new Linear(2);
		LinkedList<Entry> children = new LinkedList<Entry>();
		children.add(new Entry(a));
		children.add(new Entry(b));
		children.add(new Entry(c));
		Entry [] e1 = t.pickSeeds(children);
		
		System.out.println("t1: " );
		for (int x=0 ; x<4; x++){
			System.out.println(e1[0].getCoords()[x]);
		}
		System.out.println("t2: " );
		for (int x=0 ; x<4; x++){
			System.out.println(e1[1].getCoords()[x]);
		}
		
		
		
	}
}
