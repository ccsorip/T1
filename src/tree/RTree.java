package tree;

import node.*;


public class RTree {
	
	protected RNode root;
	protected int height;
	protected int M;
	protected double m;
	protected int size;
	
	public RTree(int blockSize){
		this.M = blockSize;
		this.m = this.M * 0.4;
		this.root = new RRoot(this.M);
		
	}
	
	public int getSize(){
		return this.size;
	}
	
	public void insert(float[] coord){
		Entry e = new Entry (coord);
		RNode leaf = chooseLeaf(this.root, e);
		leaf.addEntry(e);
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
				this.root.addEntry(e);
				n.setParent(this.root);
				this.root.addEntry(ee);
				nn.setParent(this.root);
			}
			tight(this.root);
			return;
		}
		tight(n);
		if (nn != null){
			tight(nn);
			if (n.getParent().entriesNumber() > M){
				RNode [] splits = splitNode(n.getParent());
				adjustTree(splits[0], splits[1]);
			}
		}
		if (n.getParent() != null) {
            adjustTree(n.getParent(), null);
        }
		
	}

	private void tight(RNode root2) {
		// TODO Auto-generated method stub
		
	}

	private RNode[] splitNode(RNode leaf) {
		// TODO Auto-generated method stub
		return null;
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
