package data4exp;

import java.util.Random;

public class SynthRectangle extends Rectangle {

	private float min_val=0.0f;
	private float max_val=500000.0f;
	
	public SynthRectangle(){

		float x1,y1,x2,y2;
		Random rand = new Random();
		x1=x2=y1=y2=0;
		
		while(Math.abs(x2-x1)>100 || Math.abs(x2-x1)==0){

			x1= rand.nextFloat() * (max_val - min_val) + min_val;
			x2= rand.nextFloat() * (max_val - min_val) + min_val;
		}
		
		while(Math.abs(y2-y1)>100 || Math.abs(y2-y1)==0){

			x1= rand.nextFloat() * (max_val - min_val) + min_val;
			x2= rand.nextFloat() * (max_val - min_val) + min_val;
		}
		
		this.x1=x1; this.x2=x2; this.y1=y1; this.y2=y2;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder(); 
		sb.append(x1);
		sb.append(',');
		sb.append(y1);
		sb.append(',');
		sb.append(x2);
		sb.append(',');
		sb.append(y2);
		sb.append('\n');
		return sb.toString();
	}

	@Override
	public float width() {
		// TODO Auto-generated method stub
		return Math.abs(this.x2-this.x1);
	}

	@Override
	public float height() {
		// TODO Auto-generated method stub
		return Math.abs(this.y2-this.y1);
	}

	@Override
	public float[] coords() {
		// TODO Auto-generated method stub
		float [] coords = {this.x1, this.y1, this.x2, this.y2};
		return coords;
	}
}
