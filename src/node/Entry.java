package node;

public class Entry {
	
	private RNode child;
	private float[] coord = new float [4]; // Se guarda de la forma [x1, y1, x2, y2]
	private float area;
	
	public Entry (float[] c){
		System.arraycopy(c, 0, this.coord, 0, c.length);
		this.area = Math.abs(coord[2] - coord[0]) * Math.abs(coord[3] - coord[1]);
		this.child = null;
	}
	
	public Entry (float[] c, RNode ch){
		System.arraycopy(c, 0, this.coord, 0, c.length);
		this.area = Math.abs(coord[2] - coord[0]) * Math.abs(coord[3] - coord[1]);
		this.child = ch;
	}
	
	public Entry(){
		this.child = null;
	}
	
	public void setCoord(float[] c){
		System.arraycopy(c, 0, this.coord, 0, c.length);
		this.area = Math.abs(coord[2] - coord[0]) * Math.abs(coord[3] - coord[1]);
	}
	
	public void setChild (RNode child){
		this.child = child;
	}
	
	public RNode getChild (){
		return this.child;
	}
	
	public float area(){
		return this.area;
	}
	
	public float getX1(){
		return this.coord[0];
	}
	
	public float getX2(){
		return this.coord[2];
	}
	
	public float getY1(){
		return this.coord[1];
	}
	
	public float getY2(){
		return this.coord[3];
	}
	
	public float[] getCoords(){
		return this.coord;
	}
	
	public String toString(){
		return "entry: " + coord[0] + "," + coord[1]  + "," + coord[2]  + "," + coord[3];
	}
}
