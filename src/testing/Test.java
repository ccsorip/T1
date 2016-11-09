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
		float [] h = {-6, -9, 1, -4};
		float [] i = {-11, -10, -4, -7};
		float [] j = {-3, -8, 0, -6};
		float [] k = {4, -7, 11, -6};
		float [] l = {8, -8, 12, -3};
		float [] m = {-7, -8, 0, 4};
		float [] n = {4, -1, 11, 4};
		float [] o = {8, -3, 12, 8};
		
		RTree tree = new Linear(3);
		tree.insert(a);
		tree.insert(b);
		tree.insert(c);
		tree.insert(d);
		tree.insert(e);
		tree.insert(f);
//		tree.insert(g);
//		tree.insert(h);
//		tree.insert(i);
//		tree.insert(j);
//		tree.insert(k);
//		tree.insert(l);
//		tree.insert(m);
//		tree.insert(n);
//		tree.insert(o);
		
//		System.out.println(tree.getRoot().entriesNumber());
		
		System.out.println("SIZE: " + tree.getSize());
//		tree.getRoot().g
		
//		RNode r = new RRoot (4,2);
//		RNode in = new RInternal (4,2);
//		Entry e1 = new Entry(a);
//		Entry e2 = new Entry(b);
//		Entry e3 = new Entry(c);
//		Entry e4 = new Entry(d);
//		Entry e5 = new Entry(e, in);
//		r.addEntries(e1, e2, e3, e4, e5);
//		
//		RLeaf leaf = new RLeaf(r);
//		System.out.println(leaf.entriesNumber());
		
	}
}
