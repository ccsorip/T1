package tree;

import java.util.*;

import node.*;


public class RTree {
	
	protected RNode root;
	protected int height;
	protected int M;
	protected int m;
	protected int size;
	
	public RTree(int blockSize){
		this.M = blockSize;
		this.m = (int) (this.M * 0.4);
		this.root = new RRoot(this.M);
		
	}
	
	public int getSize(){
		return this.size;
	}
	
	public void insert(float[] coord){
		Entry e = new Entry (coord);
		RNode leaf = chooseLeaf(this.root, e);
		leaf.addEntries(e);
		if (leaf.entriesNumber() > this.M){
			RNode[] splits = splitNode(leaf);
			adjustTree(splits[0], splits[1]);
		}else{
			adjustTree(leaf, null);
		}
	}
	
	private void adjustTree(RNode n, RNode nn) {
		if (n instanceof RRoot){
			if (nn != null){ //Crear una nueva raiz y agregar hijos
				this.root = new RRoot(this.M);
				Entry e = new Entry(n.mbr(), n);
				Entry ee = new Entry(nn.mbr(), nn);
				this.root.addEntries(e);
				n.setParent(this.root);
				this.root.addEntries(ee);
				nn.setParent(this.root);
			}
			this.root.mbr();
			return;
		}
		n.mbr();
		if (nn != null){
			nn.mbr();
			if (n.getParent().entriesNumber() > M){
				RNode [] splits = splitNode(n.getParent());
				adjustTree(splits[0], splits[1]);
			}
		}
		if (n.getParent() != null) {
            adjustTree(n.getParent(), null);
        }
		
	}

	protected RNode[] splitNode(RNode n) {
		RNode [] newNodes = {n, new RNode(this.M, this.m)};  //Dos nodos divididos, a retornar al final
		newNodes[1].setParent(n.getParent());
		Entry e = new Entry(newNodes[1].mbr(), newNodes[1]);
		if (n.getParent() != null) n.getParent().addEntries(e);
		LinkedList<Entry> children = new LinkedList<Entry>(n.getEntries());
		n.clearChildren();
		//Según el tipo de overflow, dividimos las entradas
		Entry [] s = this.pickSeeds(children);
		newNodes[0].addEntries(s[0]);
		newNodes[1].addEntries(s[1]);
		//Volvemos a fijar el mbr de acuerdo a las entradas hijas
		newNodes[0].mbr();
		newNodes[1].mbr();
		while (!children.isEmpty()){
			//Si uno de los dos tiene más de m entradas, el otro se rellena con lo que queda en children
			if ((newNodes[0].entriesNumber() >= m) && (newNodes[1].entriesNumber() + children.size() == m)){
				newNodes[1].addEntries(children);
				children.clear();
				newNodes[1].mbr();
				return newNodes;
			}else if ((newNodes[1].entriesNumber() >= m) && (newNodes[0].entriesNumber() + children.size() == m)){
				newNodes[0].addEntries(children);
				children.clear();
				newNodes[0].mbr();
				return newNodes;
			}
			Entry c = this.pickNext(children);
			RNode pref;
			float delta0 = this.getExpansionArea(new Entry(newNodes[0].mbr()), c) - new Entry(newNodes[0].mbr()).area();
			float delta1 = this.getExpansionArea(new Entry(newNodes[1].mbr()), c) - new Entry(newNodes[1].mbr()).area();
			if (delta0 < delta1)
				pref = newNodes[0];
			else if (delta0 > delta1)
				pref = newNodes[1];
			else {
				float area0 = new Entry(newNodes[0].mbr()).area();
				float area1 = new Entry(newNodes[1].mbr()).area();
				if (area0 < area0)
					pref = newNodes[0];
				else if (area0 > area1)
					pref = newNodes[1];
				else 
					pref = newNodes[(int) Math.round(Math.random())];
			}
			pref.addEntries(c);
			pref.mbr();
		}

		return newNodes;
	}

	protected Entry pickNext(LinkedList<Entry> children) {
		return this.pickNext(children);
	}

	protected Entry[] pickSeeds(LinkedList<Entry> children) {
		return this.pickSeeds(children);
	}

	/*
	 * Retorna la hoja donde debe ir la nueva entrada que se quiere insertar.
	 * 
	 * */
	protected RNode chooseLeaf(RNode N, Entry e) {
		if ((N instanceof RLeaf) || (N instanceof RRoot && ((RRoot) N).isNew()))
			return N;

		float minInc = Float.MAX_VALUE;
		RNode next = null;
		Entry nextEntry = null;
		for (Entry n : N.getEntries()){
			float incArea = getExpansionArea(n, e) - n.area();
			if (incArea < minInc){
				minInc = incArea;
				next = n.getChild();
				nextEntry = n;
			}else if (incArea == minInc){ //Cuando hay dos areas de expansión iguales, elegimos la que tenga menos area total
				if (getExpansionArea(n,e) < getExpansionArea(nextEntry, e)){
					minInc = incArea;
					next = n.getChild();
					nextEntry = n;
				}	
			}
		}
		return chooseLeaf(next, e);
	}

	
	
	private float getExpansionArea(Entry mbr, Entry newEntry) {
		// TODO Auto-generated method stub
		float [] newCoord = new float[4];
		
		if (mbr.getX1() < newEntry.getX1())
			newCoord[0] = newEntry.getX1();
		else 
			newCoord[0] = mbr.getX1();
		if (mbr.getY1() < newEntry.getY1())
			newCoord[1] = newEntry.getY1();
		else 
			newCoord[1] = mbr.getY1();
		if (mbr.getX2() < newEntry.getX2())
			newCoord[2] = newEntry.getX2();
		else 
			newCoord[2] = mbr.getX2();
		if (mbr.getY2() < newEntry.getY2())
			newCoord[3] = newEntry.getY2();
		else 
			newCoord[3] = mbr.getY2();
		
		float area = (newCoord[2] - newCoord[0]) * (newCoord[3] - newCoord[1]);
		
		return area;
	}

	public void delete(float[] coord){
		
	}
	
	public RNode search(float coord){
		return null;
	}
	
}
