package node;
public class RLeaf extends RNode{

	public RLeaf(int M, int m2) {
		super(M, m2);
	}

	@Override
	public String toString(){
		String str = "";
		for (Entry e : entries){
			str = "Leaf: " + e.getX1() + "," + e.getY1() + "," + e.getX2() + "," + e.getY2() + "\n";
		}
		return str;
		//return "Leaf: "  + this.mbrNode[0] + "," + this.mbrNode[1] + "," + this.mbrNode[2] + "," + this.mbrNode[3] + '\n';
	}
	
	public String treeString(StringBuilder str){
		return "Hoja \n" + this.mbrNode[0] + " " + this.mbrNode[1] + " " + this.mbrNode[2] + " " + this.mbrNode[3] + "\n";
	}

}
