package tree;

import node.RNode;

public class RTree {
	
	protected RNode root;
	protected int height;
	protected int M;
	protected double m;
	
	public RTree(int blockSize){
		this.M = blockSize;
		this.m = this.M * 0.4;
		
	}
	
	public void insert(float[] coord){
		
	}
	
	public void delete(float[] coord){
		
	}
	
	public RNode search(float coord){
		return null;
	}
	
}
