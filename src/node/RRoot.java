package node;

public class RRoot extends RNode{

	public RRoot (int M, float m){
		super(M, m);
		this.parent = null;
	}
	
	public RRoot (int M){
		super(M, 2);
		this.parent = null;
	}
	
	public boolean isNew(){
		return this.entriesNumber() == 0;
	}
}
