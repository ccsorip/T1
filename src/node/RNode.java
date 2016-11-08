package node;

import java.util.*;

public class RNode {
	
	protected int M;
	protected int m;
	protected LinkedList<Entry> entries;
	protected RNode parent;
	protected float [] mbrNode= {(float) Math.sqrt((double)Float.MAX_VALUE), (float)Math.sqrt((double)Float.MAX_VALUE), -1.0f*(float)Math.sqrt((double)Float.MAX_VALUE), -1.0f*(float)Math.sqrt((double)Float.MAX_VALUE)};
	
	
	public RNode(int M, int m){

		this.M = M;
		this.m = m;
		this.entries = new LinkedList<Entry>();
//		this.mbrNode = new float[4];
	}

	public RNode(RLeaf r){

		this.M = r.getM();
		this.m = r.getm();
		entries = r.getEntries();
		parent = r.getParent();
		//calculateMBR();
	}
	
	public boolean isFull(){

		if (this.entriesNumber() > this.M){

			return true;
		}
		return false;
	}
	
	public int getM() {
		return this.M;
	}

	public int getm() {
		return this.m;
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
				
				//calculateMBR();
				return true;
			}
		}
		return false;
	}
	
	public void removeEntry(Entry e){
		
		entries.remove(e);
		
		//calculateMBR();
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
		
//		calculateMBR();
	}
	
	public void addEntries(LinkedList<Entry> e){
		
		this.entries.addAll(e);
//		calculateMBR();
	}
	
	public void calculateMBR(){
		if (!entries.isEmpty()){
			for (Entry e : entries){
				if (e.getX1() < this.mbrNode[0])
					this.mbrNode[0] = e.getX1();
				if (e.getY1() < this.mbrNode[1])
					this.mbrNode[1] = e.getY1();
				if (e.getX2() > this.mbrNode[2])
					this.mbrNode[2] = e.getX2();
				if (e.getY2() > this.mbrNode[3])
					this.mbrNode[3] = e.getY2();
			}
			if (this.parent != null)
				this.parent.calculateMBR();
		}
	}
	
	public float[] getMBR(){
		return this.mbrNode;
	}
	
	
	public void clearChildren(){
		this.entries.clear();
	}
	
	
	
	public String treeString(StringBuilder str){
		return treeString(str);
		
//		if (r instanceof RRoot){
//			str.append("Raiz \n");// + this.mbrNode[0] + '-' + this.mbrNode[1] + '-' + this.mbrNode[2] + '-' + this.mbrNode[3] + '\n');
//			for (Entry e : r.getEntries()){
//				if (e.getChild() != null)
//					str.append(treeString(e.getChild(), str));
//			}
//		}
//		else if (r instanceof RInternal){
//			str.append("Interno \n");
//			for (Entry e : r.getEntries()){
//				if (e.getChild() != null)
//					str.append(treeString(e.getChild(), str));
//			}
//		}
//		else if (r instanceof RLeaf)
//			str.append("Hoja \n");
//		
//		return str.toString();
	}
	
	public void setMBR(float x1, float y1, float x2, float y2){
		this.mbrNode[0] = x1;
		this.mbrNode[1] = y1;
		this.mbrNode[2] = x2;
		this.mbrNode[3] = y2;
	}

	public Entry getEntry(RNode n) {
		System.out.println("getEntry...");
		Entry en = null;
		for (Entry e : entries){
			if (e.getChild().equals(n))
				en = e;
		}
		
		return en;
	}
}
