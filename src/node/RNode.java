package node;

import java.util.*;

public class RNode {
	
	protected float[] coord;
	protected int M;
	protected float m;
	LinkedList<Float> entries;
	
	public RNode(int M, float m){
		this.M = M;
		this.m = m;
	}
	
	public void addCoord(float[] c){
		System.arraycopy(c, 0, this.coord, 0, this.coord.length);
	}
}
