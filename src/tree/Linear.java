package tree;

import java.util.LinkedList;

import node.*;

public class Linear extends RTree{


	public Linear(int blockSize) {
		super(blockSize);
	}
	
	@Override
	public Entry[] pickSeeds(LinkedList<Entry> children){
		Entry[] ret = new Entry[2];
		float lowestHighSideX = Float.MAX_VALUE, lowestHighSideY = Float.MAX_VALUE;
		float highestLowSideX = -1.0f * Float.MAX_VALUE, highestLowSideY = -1.0f * Float.MAX_VALUE;
		float maxX = Float.MAX_VALUE, minX = -1.0f * Float.MAX_VALUE;
		float maxY = Float.MAX_VALUE, minY = -1.0f * Float.MAX_VALUE;

		Entry maxLBX = null, minUBX = null, maxLBY = null, minUBY = null;
		for (Entry e : children){
			// Primera dimensión
			
			// Si coord x1 nueva es 
			if (e.getX1() > highestLowSideX){
				highestLowSideX = e.getX1();
				minUBX = e;
			}
			if (e.getX2() < lowestHighSideX ){
				lowestHighSideX = e.getX2();
				maxLBX = e;
			}
			// Segunda dimensión
			if (e.getY1() > highestLowSideY ){
				highestLowSideY = e.getY1();
				minUBY = e;
			}
			if (e.getY2() < lowestHighSideY){
				lowestHighSideY = e.getY2();
				maxLBY = e;
			}
			
			// Se calcula el ancho de ambas dimensiones
			if (e.getX1() < minX)
				minX = e.getX1();
			if (e.getX2() > maxX)
				maxX = e.getX2();
			if (e.getY1() < minY)
				minY = e.getY1();
			if (e.getY2() < maxY)
				maxY = e.getY2();
		}
		 // Se calcula la separación normalizada
		float sepX = Math.abs(highestLowSideX - lowestHighSideX) / Math.abs(minX - maxX);
		float sepY = Math.abs(highestLowSideY - lowestHighSideY) / Math.abs(minY - maxY);
		// Se decide que entrada retornar según la separación normalizada más grande
		ret[0] = (sepX > sepY) ? maxLBX : maxLBY;
		ret[1] = (sepX > sepY) ? minUBX : minUBY;
		
		children.remove(ret[0]);
		children.remove(ret[1]);
		
		return ret;
	}

	@Override
	protected Entry pickNext(LinkedList<Entry> children, RNode[] n){
		return children.pop();
	}
}
