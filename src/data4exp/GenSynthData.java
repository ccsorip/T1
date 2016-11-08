package data4exp;


import java.io.PrintWriter;

public class GenSynthData {

	public GenSynthData(){}
	
	public static void genFile(int n){
		
		int n_exp=(int)Math.pow(2,n);

		try{
			PrintWriter writer = new PrintWriter("experiment_"+Integer.toString(n)+".txt", "UTF-8");
			while(n_exp!=0){
				SynthRectangle rect = new SynthRectangle();
				writer.write(rect.toString());
		    	n_exp--;
			}
			writer.close();
		} catch (Exception e) {System.out.println("Holiii");}
		
	}

	
	public static void genFiles(int ini, int end){
		
		for(int i=ini; i<=end;i++){
			genFile(i);
		}
	}
	
	public static void main(String [] args){
		genFiles(9,25);
	}
}
