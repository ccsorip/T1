package node;

public class RRoot extends RNode{

	
	
	public RRoot (int M, int m){
		super(M, m);
		this.parent = null;
	}
	
	public RRoot (int M){
		super(M, 2);
		this.parent = null;
	}
	
	public RRoot(RLeaf r){
		super(r);
	}
	
	/* Retorna true si los hijos de la raiz son hojas y false si los hijos son nodos internos
	 * Habla del estado del arbol en su creación */
	public boolean state(){
		RNode r = entries.peek().getChild();
		return (r instanceof RLeaf);
	}
	
	@Override
	public String toString(){
		String str = "Raiz: " + this.mbrNode[0] + "," + this.mbrNode[1] + "," + this.mbrNode[2] + "," + this.mbrNode[3] + '\n';
		for (Entry e : entries){
			if (e.getChild() != null)
				str = str + e.getChild().toString();
		}
		return str;
	}
}
