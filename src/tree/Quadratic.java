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
		float maxWaste = -1.0f * Float.MAX_VALUE; // El menor float para el máximo "desperdicio"
		for (Entry e1 : children){
			for (Entry e2 : children){
				if (e1 != e2){
					float a1 = e1.area();
					float a2 = e2.area();
					float[] J = new float[4];
					J[0] = (e1.getX1() > e2.getX1()) ? e1.getX1() : e2.getX1();
					J[1] = (e1.getY1() > e2.getY1()) ? e1.getY1() : e2.getY1();
					J[2] = (e1.getX2() > e2.getX2()) ? e1.getX2() : e2.getX2();
					J[3] = (e1.getY2() > e2.getY2()) ? e1.getY2() : e2.getY2();
					float d = (J[2] - J[0]) * (J[3] - J[1]); //new Entry (J).area();
					float waste = d - a1 - a2;
					if (waste > maxWaste){
						maxWaste = waste;
						best[0] = e1;
						best[1] = e2;
					}
						
				}
			}
		}
		children.remove(best[0]);
		children.remove(best[1]);
		return best;
	}
	
	@Override
	protected Entry pickNext(LinkedList<Entry> children, RNode[] n){
		Entry best = null;
		float maxDiff = -1.0f * Float.MAX_VALUE;
		Entry mbr1 = new Entry(n[0].getMBR()); 
		Entry mbr2 = new Entry(n[1].getMBR());
		for (Entry e : children){
			float incArea1 = getExpansionArea(mbr1, e) - mbr1.area();
			float incArea2 = getExpansionArea(mbr2, e) - mbr2.area();
			float diff = Math.abs(incArea1 - incArea2);
			if (diff > maxDiff){
				maxDiff = diff;
				best = e;
			}
		}
		return best;
	}

}
