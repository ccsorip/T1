package tree;

import java.util.LinkedList;

import node.*;

public class Linear extends RTree{

	public Linear(int blockSize) {
		super(blockSize);
	}
	
	@Override
	protected Entry[] pickSeeds(LinkedList<Entry> children){
		Entry[] ret = new Entry[2];
		
		
		
		return ret;
	}

	@Override
	protected Entry pickNext(LinkedList<Entry> children, RNode[] n){
		return children.pop();
	}
}
