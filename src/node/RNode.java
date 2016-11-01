package node;

import java.util.*;

public class RNode {
	
	protected int M;
	protected int m;
	protected LinkedList<Entry> entries;
	protected RNode parent;
	
	
	public RNode(int M, int m){
		this.M = M;
		this.m = m;
		this.entries = new LinkedList<Entry>();
	}

	public void setParent(RNode p){
		this.parent = p;
	}
	
	public RNode getParent(){
		return this.parent;
	}
	
	public int entriesNumber(){
		return entries.size();
	}
	
	public LinkedList<Entry> getEntries(){
		return entries;
	}
	
	public int getMaxEntries(){
		return this.M;
	}
	
	public int getMinEntries(){
		return this.m;
	}
	
	public void addEntries(Entry... en){
		for (Entry e : en){
			this.entries.add(e);
		}
	}
	
	public void addEntries(LinkedList<Entry> e){
			this.entries.addAll(e);
	}
	
	public float[] mbr(){
		float [] mbr = {Float.MIN_VALUE, Float.MIN_VALUE, Float.MIN_VALUE, Float.MIN_VALUE};
		if (!entries.isEmpty()){
			for (Entry e : entries){
				if (e.getX1() > mbr[0])
					mbr[0] = e.getX1();
				if (e.getY1() > mbr[1])
					mbr[1] = e.getY1();
				if (e.getX2() > mbr[2])
					mbr[2] = e.getX2();
				if (e.getY2() > mbr[3])
					mbr[3] = e.getY2();
			}
			return mbr;
		}
		mbr[0] = mbr[1] = mbr[2] = mbr[3] = 0;
		return mbr;
	}
	
	
	public void clearChildren(){
		this.entries.clear();
	}
	
	
	
}
