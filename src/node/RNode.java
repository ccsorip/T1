package node;

import java.util.*;

public class RNode {
	
	protected int M;
	protected int m;
	protected LinkedList<Entry> entries;
	protected RNode parent;
	protected float [] mbrNode = {Float.MIN_VALUE, Float.MIN_VALUE, Float.MIN_VALUE, Float.MIN_VALUE};
	
	
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
	
	public boolean removeEntry(float [] coord){
		for (Entry e : entries){
			float [] c = e.getCoords();
			if (coord[0] == c[0] && coord[1] == c[1] && coord[2] == c[2] && coord[3] == c[3]){
				entries.remove(e);
				return true;
			}
		}
		return false;
	}
	
	public void removeEntry(Entry e){
		entries.remove(e);
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
		calculateMBR();
	}
	
	public void addEntries(LinkedList<Entry> e){
		this.entries.addAll(e);
		calculateMBR();
	}
	
	public void calculateMBR(){
		if (!entries.isEmpty()){
			for (Entry e : entries){
				if (e.getX1() > this.mbrNode[0])
					this.mbrNode[0] = e.getX1();
				if (e.getY1() > this.mbrNode[1])
					this.mbrNode[1] = e.getY1();
				if (e.getX2() > this.mbrNode[2])
					this.mbrNode[2] = e.getX2();
				if (e.getY2() > this.mbrNode[3])
					this.mbrNode[3] = e.getY2();
			if (e.getChild() != null)
				e.getChild().calculateMBR();
			}
		}
	}
	
	public float[] getMBR(){
		return this.mbrNode;
	}
	
	
	public void clearChildren(){
		this.entries.clear();
	}
	
	@Override
	public String toString(){
		StringBuilder str = new StringBuilder();
		str.append("N:");
		str.append(this.mbrNode[0] + "-");
		str.append(this.mbrNode[1] + "-");
		str.append(this.mbrNode[2] + "-");
		str.append(this.mbrNode[3] + "-");
		str.append("\n");
		int i = 1;
		for (Entry e : entries){
			str.append('\t' + "E" + i + ":");
			for (int j = 0; j<4 ; j++)
				str.append(e.getCoords()[j] + "-");
			str.append('\n');
			if (e.getChild() != null){
				str.append('\t');
				e.getChild().toString();
			}
			i++;
		}
		
		return str.toString();
	}
	
}
