package tree;

import java.util.LinkedList;

import node.*;

public class Linear extends RTree{

	public Linear(int blockSize) {
		super(blockSize);
	}
	
	protected Entry[] pickSeeds(LinkedList<Entry> children){
		return null;
	}

	protected Entry pickNext(LinkedList<Entry> children){
		return null;
	}
}
