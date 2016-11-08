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
		//RNode r = new RNode (5, 5);
		//r.addEntries(new Entry (a), new Entry (b), new Entry (c), new Entry (d), new Entry (e), new Entry (f));
		
		//System.out.println(r.toString());
//		
		RTree tree = new Linear(2);
		tree.insert(a);
		tree.insert(b);
		tree.insert(c);
		tree.insert(d);
//		tree.insert(e);
		
		System.out.println(tree.getRoot().toString());
//		
//		if (tree.getRoot() instanceof RRoot){
//			System.out.println("Raiz");
//			System.out.println("Entry number :" + tree.getRoot().entriesNumber());
//		}
//		
//		for (Entry en : tree.getRoot().getEntries()){
//			if (en.getChild() instanceof RInternal){
//				System.out.println("Interno");
//				System.out.println("Interno");
//				}
//		}
//		
//		for (Entry en : tree.getRoot().getEntries()){
//			if (en.getChild() instanceof RLeaf){
//				System.out.println("Hoja");
//				System.out.println("Entry number :" + en.getChild().entriesNumber());
//				System.out.println("mbr: " + en.getChild().getMBR()[0] + "," + en.getChild().getMBR()[1] + "," + en.getChild().getMBR()[2] + "," + en.getChild().getMBR()[3]);
//			}
//		}
//		
////		
//		
//		RNode rn = new RNode(2,1);
//		Entry e1 = new Entry(a);
//		Entry e2 = new Entry(b);
//		Entry e3 = new Entry(c);
//		Entry e4 = new Entry(d);
//		
//		rn.addEntries(e1, e2, e3, e4);
//		
//		System.out.println(rn.entriesNumber());
//		if (rn.removeEntry(d))
//			System.out.println("borro");
//		else
//			System.out.println("no borro");
//		System.out.println(rn.entriesNumber());
//		
		
	}
}
