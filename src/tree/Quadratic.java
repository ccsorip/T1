package tree;

import java.util.LinkedList;

import node.*;

public class Quadratic extends RTree{

	public Quadratic(int blockSize) {
		super(blockSize);
	}
	
	@Override
	protected Entry[] pickSeeds(LinkedList<Entry> children){
		Entry[] best = new Entry[2];
		float maxWaste = -1.0f * Float.MAX_VALUE;
		for (Entry e1 : children){
			for (Entry e2 : children){
				if (e1 != e2){
					float a1 = e1.area();
					float a2 = e2.area();
				}
			}
		}
		return best;
	}
	
	@Override
	protected Entry pickNext(LinkedList<Entry> children, RNode[] ...n){
		return null;
	}

}
