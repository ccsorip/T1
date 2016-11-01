package data4exp;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class GenSynthData {

	public GenSynthData(){}
	
	public static void genFile(int n){
		
		int n_exp=(int)Math.pow(2,n);
		try (Writer writer = new BufferedWriter(new OutputStreamWriter(
	              new FileOutputStream("experiment_"+Integer.toString(n)+".txt"), "utf-8"))){ 
			while(n_exp!=0){
				Rectangle rect = new SynthRectangle();
				writer.write(rect.toString());
				n_exp--;
				System.out.println(n_exp);
			}}catch(IOException e){}
		}
	
	public void genFiles(int ini, int end){
		
	}
	
	public static void main(String [] args){
		genFile(2);
		System.out.println("Holaaa");
	}
}
