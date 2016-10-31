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
		
	}
	
	public int getSize(){
		return this.size;
	}
	
	public void insert(float[] coord){
		Entry e = new Entry (coord);
		RNode leaf = chooseLeaf(this.root, e);
	}
	
	protected RNode chooseLeaf(RNode N, Entry e) {
		if ((N instanceof RLeaf) || (N instanceof RRoot && ((RRoot) N).isNew()))
			return N;

		float minInc = Float.MAX_VALUE;
		RNode next = null;
		for (Entry n : N.getEntries()){
			float incArea = getExpansionArea(n, e);
			if (incArea < minInc){
				minInc = incArea;
				next = n.getChild();
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
		
		return area - mbr.area();
	}

	public void delete(float[] coord){
		
	}
	
	public RNode search(float coord){
		return null;
	}
	
}
