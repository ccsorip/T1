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
		this.root = new RRoot(this.M, 2);
		this.root.setParent(null);
		size = 0;
	}
	
	public RNode getRoot(){
		return this.root;
	}
	
	public int getSize(){
		return size;
	}
	
	public void insert(float[] coord){
		Entry e = new Entry (coord);
		RNode leaf = chooseLeaf(this.root, e); // Retorna la hoja donde insertar coord
		leaf.addEntries(e); // Insertar e en la hoja
		size++;
		if (leaf.isOverflowed()){ // Pregunta si tiene superó el maximo de entradas
			System.out.println("Overflow");
			RNode[] splits = splitNode(leaf); // Retorna dos nodos
//			tightenTree(splits[0]);
//			tightenTree(splits[1]);
			adjustTree(splits[0], splits[1]); // Ajusta los mbr y propaga la division de nodos
		}else{
			adjustTree(leaf, null);
		}
	}
	
	private void tightenTree(RNode r){
		float minCoordX = Float.MAX_VALUE;
		float maxCoordX = -1.0f * Float.MAX_VALUE;
		float minCoordY = Float.MAX_VALUE;
		float maxCoordY = -1.0f * Float.MAX_VALUE;
		for (Entry e : r.getEntries()){
			if (e.getChild() != null) e.getChild().setParent(r);
			if (e.getX1() < minCoordX)
				minCoordX = e.getX1();
			if (e.getY1() < minCoordY)
				minCoordY = e.getY1();
			if (e.getX2() > maxCoordX)
				maxCoordX = e.getX2();
			if (e.getY2() > maxCoordY)
				maxCoordY = e.getY2();
		}
		r.setMBR(minCoordX, minCoordY, maxCoordX, maxCoordY);
	}
	
	
	private void adjustTree(RNode n, RNode nn) {
		System.out.println("adjustTree");
		if (n instanceof RRoot){
			if (nn != null){
				root = new RRoot(this.M, this.m);
				Entry en = new Entry(n.getMBR());
				Entry enn = new Entry (nn.getMBR());
				RLeaf l = new RLeaf(n);
				en.setChild(l);
				enn.setChild(nn);
				root.addEntries(en, enn);
				System.out.println("NUEVA RAIZ");
			}
			tightenTree(root);
			return;
		}
		
		tightenTree(n);
		if (nn != null){
			tightenTree(nn);
			if (n.getParent().isOverflowed()){
				RNode[] splits = splitNode(n.getParent());
				adjustTree(splits[0], splits[1]);
			}
		}
		if (n.getParent() != null){
			adjustTree(n.getParent(), null);
		}
	}

	protected RNode[] splitNode(RNode n) {
//		System.out.println("splitNode init con nodo: " + n.getMBR()[0] + "-" + n.getMBR()[1] + "-" + n.getMBR()[2] + "-" + n.getMBR()[3]);
		RNode [] newNodes = new RNode [2]; //Dos nodos divididos, a retornar al final
		newNodes[0] = n;
		if (n instanceof RLeaf){
			newNodes[1] = new RLeaf(this.M, this.m);
		} else if (n instanceof RInternal){
			newNodes[1] = new RInternal(this.M, this.m);
		} else if (n instanceof RRoot){
			newNodes[1] = new RInternal(this.M, this.m);
		}
		
		//"colgamos" los nuevos nodos al mismo padre que el nodo a dividir
		newNodes[1].setParent(n.getParent());
		
		//Colgamos los hijos desde el padre original*(?)
		Entry e = new Entry();
		
		/**
	     * 
	     * TODO : verificar asignación de hijo en la entrada
	     * 
	     */
		//AQUI SETEO EL HIJO DEL NODO? PORQUE TENGO UNA REFERENCIA DE UN HIJO DE UNA
		//ENTRADA QUE ESTÁ EN NULL, Y ESO ME HACE QUE SE CREE UN NULLPOINTER
		//EN CHOOSELEAF
		
//		e.setChild(newNodes[0]);
		// Cuando el nodo a dividir NO es la raiz, lo coloco como hijo del mismo padre del nodo
		// a dividir
		if (n.getParent() != null){ 
			n.getParent().addEntries(e);
			//tightenTree(n.getParent());
		}
		
		//Rescato los hijos del nodo que busco dividir y los saco después //Aun conserva lista de entrdas
		LinkedList<Entry> children = new LinkedList<Entry>(n.getEntries());
		
		n.clearChildren();
		
		//Según el tipo de overflow, dividimos las entradas
		Entry [] s = this.pickSeeds(children);
		newNodes[0].addEntries(s[0]);
		newNodes[1].addEntries(s[1]);
		tightenTree(newNodes[1]);
		while (!children.isEmpty()){
			//Si uno de los dos tiene más de m entradas, el otro se rellena con lo que queda en children
			if ((newNodes[0].entriesNumber() >= m) && (newNodes[1].entriesNumber() + children.size() == m)){
				newNodes[1].addEntries(children);
				children.clear();
				tightenTree(newNodes[1]);
				return newNodes;
			}else if ((newNodes[1].entriesNumber() >= m) && (newNodes[0].entriesNumber() + children.size() == m)){
				newNodes[0].addEntries(children);
				children.clear();
				tightenTree(newNodes[1]);
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
				int n1 = newNodes[0].entriesNumber();
				int n2 = newNodes[1].entriesNumber();
				if (area0 < area0)
					pref = newNodes[0];
				else if (area0 > area1)
					pref = newNodes[1];
				else if (n1 < n2)
					pref = newNodes[0];
				else if (n2 > n1)
					pref = newNodes[1];
				else
					pref = newNodes[(int) Math.round(Math.random())];
			}
			pref.addEntries(c);
			tightenTree(pref);
		}
		return newNodes;
	}

	protected Entry pickNext(LinkedList<Entry> children, RNode[] n) {
		return this.pickNext(children, n);
	}

	public Entry[] pickSeeds(LinkedList<Entry> children) {
		return this.pickSeeds(children);
	}

	/*
	 * Retorna la hoja donde debe ir la nueva entrada que se quiere insertar.
	 * 
	 * */
	protected RNode chooseLeaf(RNode N, Entry e) {
		
		if (N instanceof RLeaf)
			return N;

		// Aseguramos siempre dos entradas en la raiz
		if ((N instanceof RRoot) && (N.entriesNumber() < 2)){
			RLeaf q = new RLeaf(this.M, this.m);
			q.setParent(N);
			Entry en = new Entry(e.getCoords());
			en.setChild(q);
			N.addEntries(en);
			return q;
		}
			
		float minInc = Float.MAX_VALUE;
		RNode next = null;
		Entry nextEntry = null;
		System.out.println("N donde se busca: " + N.getMBR()[0] + "," + N.getMBR()[1] + "," + N.getMBR()[2] + "," + N.getMBR()[3]);
		System.out.println("# entries: " + N.entriesNumber());
		for (Entry n : N.getEntries()){
			float incArea = getExpansionArea(n, e) - n.area();
			if (incArea < minInc){
				System.out.println("incArea < minInc");
				System.out.println("incArea:" + incArea);
				if (n.getChild() == null) System.out.println("null");
				minInc = incArea;
				next = n.getChild();
				nextEntry = n;
			}else if (incArea == minInc){ //Cuando hay dos areas de expansión iguales, elegimos la que tenga menos area total
				if (getExpansionArea(n,e) < getExpansionArea(nextEntry, e)){
					next = n.getChild();
					nextEntry = n;
				}	
			}
		}
		return chooseLeaf(next, e);
	}

	protected float getExpansionArea(Entry mbr, Entry newEntry) {
		float [] newCoord = new float[4];
		
		if (newEntry.getX1() < mbr.getX1())
			newCoord[0] = newEntry.getX1();
		else
			newCoord[0] = mbr.getX1();
		
		if (newEntry.getY1() < mbr.getY1())
			newCoord[1] = newEntry.getY1();
		else
			newCoord[1] = mbr.getY1();
		
		if (newEntry.getX2() > mbr.getX2())
			newCoord[2] = newEntry.getX2();
		else
			newCoord[2] = mbr.getX2();
		
		if (newEntry.getY2() > mbr.getY2())
			newCoord[3] = newEntry.getY2();
		else
			newCoord[3] = mbr.getY2();
		
		float area = Math.abs(newCoord[2] - newCoord[0]) * Math.abs(newCoord[3] - newCoord[1]);
		
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
				tightenTree(en.getChild());
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
			tightenTree(this.root);
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
		return this.root.toString(); //this.root.toString();
		
	}
	
}