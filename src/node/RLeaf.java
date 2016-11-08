package node;
public class RLeaf extends RNode{

	public RLeaf(int M, int m2) {
		super(M, m2);
	}


	
	public String treeString(StringBuilder str){
		return "Hoja \n" + this.mbrNode[0] + " " + this.mbrNode[1] + " " + this.mbrNode[2] + " " + this.mbrNode[3] + "\n";
	}

}
