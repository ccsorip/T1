package data4exp;
import tree.*;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class LinearTest {
	
	protected Linear ltree;
	protected static String path; 
	
	public LinearTest(int blocksize, String path){
		ltree= new Linear(blocksize);
		this.path = path;
	}
	
	public void test(){
		try{
		Scanner scanner = new Scanner(new File(path));
		scanner.useDelimiter(",|\n");
        while(scanner.hasNext()){
            float x1=Float.parseFloat(scanner.next());
            float y1=Float.parseFloat(scanner.next());
            float x2=Float.parseFloat(scanner.next());
            float y2=Float.parseFloat(scanner.next());
            float [] coords= {x1,y1,x2,y2};
            ltree.insert(coords);
            //System.err.println("Insertando weas");
        }
        scanner.close();
		
		}catch(FileNotFoundException e){System.err.println("File not found");}
	}

}
