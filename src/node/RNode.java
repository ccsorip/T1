package node;

import java.util.*;

public class RNode {
	
	protected int M;
	protected float m;
	protected LinkedList<Entry> entries;
	protected RNode parent;
	
	public RNode(int M, float m){
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
	
}
