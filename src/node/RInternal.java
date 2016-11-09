package node;
public class RInternal extends RNode{

	public RInternal(int M, int m2) {
		super(M, m2);
	}

	@Override
	public String toString(){
//		String str = "";
		String str = "Interno: " + this.mbrNode[0] + "," + this.mbrNode[1] + "," + this.mbrNode[2] + "," + this.mbrNode[3] + '\n';
		for (Entry e : entries){
			if (e.getChild() != null)
				str = str + "\n" + e.getChild().toString();
		}
		return str;
	}
}
