package tree;

import java.util.*;

import node.*;


public class RTree {
	
	protected RNode root;
	protected int height;
	protected int M;
	protected int m;
	protected int size;
	
	public RTree(int blockSize){
		this.M = blockSize;
		this.m = (int) (this.M * 0.4);
		this.root = new RRoot(this.M);
		
	}
	
	public int getSize(){
		return this.size;
	}
	
	public void insert(float[] coord){
		Entry e = new Entry (coord);
		RNode leaf = chooseLeaf(this.root, e);
		leaf.addEntries(e);
		if (leaf.entriesNumber() > this.M){
			RNode[] splits = splitNode(leaf);
			adjustTree(splits[0], splits[1]);
		}else{
			adjustTree(leaf, null);
		}
	}
	
	private void adjustTree(RNode n, RNode nn) {
		if (n instanceof RRoot){
			if (nn != null){ //Crear una nueva raiz y agregar hijos
				this.root = new RRoot(this.M);
				Entry e = new Entry(n.getMBR(), n);
				Entry ee = new Entry(nn.getMBR(), nn);
				this.root.addEntries(e);
				n.setParent(this.root);
				this.root.addEntries(ee);
				nn.setParent(this.root);
			}
			this.root.calculateMBR();
			return;
		}
		n.calculateMBR();
		if (nn != null){
			nn.calculateMBR();
			if (n.getParent().entriesNumber() > M){
				RNode [] splits = splitNode(n.getParent());
				adjustTree(splits[0], splits[1]);
			}
		}
		if (n.getParent() != null) {
            adjustTree(n.getParent(), null);
        }
		
	}

	protected RNode[] splitNode(RNode n) {
		RNode [] newNodes = {n, new RNode(this.M, this.m)};  //Dos nodos divididos, a retornar al final
		newNodes[1].setParent(n.getParent());
		Entry e = new Entry(newNodes[1].getMBR(), newNodes[1]);
		if (n.getParent() != null) n.getParent().addEntries(e);
		LinkedList<Entry> children = new LinkedList<Entry>(n.getEntries());
		n.clearChildren();
		//Según el tipo de overflow, dividimos las entradas
		Entry [] s = this.pickSeeds(children);
		newNodes[0].addEntries(s[0]);
		newNodes[1].addEntries(s[1]);
		//Volvemos a fijar el mbr de acuerdo a las entradas hijas
		newNodes[0].calculateMBR();
		newNodes[1].calculateMBR();
		while (!children.isEmpty()){
			//Si uno de los dos tiene más de m entradas, el otro se rellena con lo que queda en children
			if ((newNodes[0].entriesNumber() >= m) && (newNodes[1].entriesNumber() + children.size() == m)){
				newNodes[1].addEntries(children);
				children.clear();
				newNodes[1].calculateMBR();
				return newNodes;
			}else if ((newNodes[1].entriesNumber() >= m) && (newNodes[0].entriesNumber() + children.size() == m)){
				newNodes[0].addEntries(children);
				children.clear();
				newNodes[0].calculateMBR();
				return newNodes;
			}
			Entry c = this.pickNext(children, newNodes);
			RNode pref;
			float delta0 = getExpansionArea(new Entry(newNodes[0].getMBR()), c) - new Entry(newNodes[0].getMBR()).area();
			float delta1 = getExpansionArea(new Entry(newNodes[1].getMBR()), c) - new Entry(newNodes[1].getMBR()).area();
			if (delta0 < delta1)
				pref = newNodes[0];
			else if (delta0 > delta1)
				pref = newNodes[1];
			else {
				float area0 = new Entry(newNodes[0].getMBR()).area();
				float area1 = new Entry(newNodes[1].getMBR()).area();
				if (area0 < area0)
					pref = newNodes[0];
				else if (area0 > area1)
					pref = newNodes[1];
				else 
					pref = newNodes[(int) Math.round(Math.random())];
			}
			pref.addEntries(c);
			pref.calculateMBR();
		}

		return newNodes;
	}

	protected Entry pickNext(LinkedList<Entry> children, RNode[] n) {
		return this.pickNext(children, n);
	}

	protected Entry[] pickSeeds(LinkedList<Entry> children) {
		return this.pickSeeds(children);
	}

	/*
	 * Retorna la hoja donde debe ir la nueva entrada que se quiere insertar.
	 * 
	 * */
	protected RNode chooseLeaf(RNode N, Entry e) {
		if ((N instanceof RLeaf) || (N instanceof RRoot ))
			return N;

		float minInc = Float.MAX_VALUE;
		RNode next = null;
		Entry nextEntry = null;
		for (Entry n : N.getEntries()){
			float incArea = getExpansionArea(n, e) - n.area();
			if (incArea < minInc){
				minInc = incArea;
				next = n.getChild();
				nextEntry = n;
			}else if (incArea == minInc){ //Cuando hay dos areas de expansión iguales, elegimos la que tenga menos area total
				if (getExpansionArea(n,e) < getExpansionArea(nextEntry, e)){
					minInc = incArea;
					next = n.getChild();
					nextEntry = n;
				}	
			}
		}
		return chooseLeaf(next, e);
	}

	
	
	protected float getExpansionArea(Entry mbr, Entry newEntry) {
		float [] newCoord = new float[4];
		
		if (mbr.getX1() < newEntry.getX1())
			newCoord[0] = newEntry.getX1();
		else 
			newCoord[0] = mbr.getX1();
		if (mbr.getY1() < newEntry.getY1())
			newCoord[1] = newEntry.getY1();
		else 
			newCoord[1] = mbr.getY1();
		if (mbr.getX2() < newEntry.getX2())
			newCoord[2] = newEntry.getX2();
		else 
			newCoord[2] = mbr.getX2();
		if (mbr.getY2() < newEntry.getY2())
			newCoord[3] = newEntry.getY2();
		else 
			newCoord[3] = mbr.getY2();
		
		float area = (newCoord[2] - newCoord[0]) * (newCoord[3] - newCoord[1]);
		
		return area;
	}

	public boolean delete(float[] coord){
		RNode l = findLeaf(this.root, coord);
		if (l== null) return false;
		l.removeEntry(coord);
		condenseTree(l);
		if (this.root.entriesNumber() == 1){
			RRoot nR = new RRoot(this.M);
			nR.addEntries(this.root.getEntries());
			this.root = nR;
		}
		return true;
	}
	

	private void condenseTree(RNode l) {
		RNode N = l;
		RNode P = null;
		Entry en = null;
		Set<RNode> Q = new HashSet<RNode>();
		while (!this.root.equals(N)){
			P = N.getParent();
			for (Entry e : P.getEntries()){
				if (e.getChild().equals(N))
					en = e;
			}
			if (N.entriesNumber() < this.m ){
				P.removeEntry(en);
				Q.add(N);
			}else {
				en.getChild().calculateMBR();
			}
			N = P;
		}
		if (this.root.entriesNumber() == 0){
			this.m = (int) (this.M * 0.4);
			this.root = new RRoot(this.M);
		}else if (this.root.entriesNumber() == 1){
			RRoot nR = new RRoot(this.M);
			nR.addEntries(this.root.getEntries());
			this.root = nR;
		}else {
			this.root.calculateMBR();
		}
		
		for (RNode r : Q){
			LinkedList<Entry> le = r.getEntries();
			for (Entry e : le){
				insert(e.getCoords());
			}
		}
	}
	

	private RNode findLeaf(RNode r, float[] coord) {
		if (r instanceof RLeaf){
			for (Entry e : r.getEntries()){
				if ( isOverlapping(coord, e.getCoords()) )
					return findLeaf(e.getChild(), coord);
			}
		}else{
			for (Entry e : r.getEntries()){
				float [] c = e.getCoords();
				if (coord[0] == c[0] && coord[1] == c[1] && coord[2] == c[2] && coord[3] == c[3])
					return r;
			}
		}
		return null;
	}

	public LinkedList<float[]> search(float []coord){
		LinkedList<float[]> results = new LinkedList<float[]>();
		searchT(this.root, coord, results);
		return results;
	}

	private void searchT(RNode r, float[] coord, LinkedList<float[]> results) {
		//Si no es una hoja busco en todos los rectangulos que hagan overlapping
		if (!(r instanceof RLeaf)){
			for (Entry e : r.getEntries()){
				if (isOverlapping(coord, e.getCoords())){
					searchT(e.getChild(), coord, results);
				}
			}
		//Si es una hoja, agrego a los resultados todos aquellos rectangulos que hagan overlap
		}else{
			for (Entry e : r.getEntries()){
				if (isOverlapping(coord, e.getCoords()))
					results.add(e.getCoords());
			}
		}
		
	}
	
	private boolean isOverlapping(float [] c1, float [] c2){
		//Si un rectangulo está a la izquierda del otro
		if (c1[0] > c2[2] || c2[0] > c1[2])
			return false;
		//Si uno está sobre el otro
		if (c1[3] < c2[1] || c2[3] < c1[1])
			return false;
		return true;
	}
	
	@Override
	public String toString(){
		return this.root.toString();
	}
	
}