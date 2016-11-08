package data4exp;

import java.util.Random;

public class SynthRectangle implements Rectangle {

	private float min_val=0.0f;
	private float max_val=500000.0f;
	protected float x1_,y1_,x2_,y2_;
	
	public SynthRectangle(){

		float x1,y1,x2,y2;
		Random rand = new Random();
		x1=x2=y1=y2=0.0f;
		
		while(Math.abs(x2-x1)>100 || Math.abs(x2-x1)==0){

			x1= rand.nextFloat() * (max_val - min_val) + min_val;
			x2= rand.nextFloat() * (max_val - min_val) + min_val;
		}
		
		while(Math.abs(y2-y1)>100 || Math.abs(y2-y1)==0){

			y1= rand.nextFloat() * (max_val - min_val) + min_val;
			y2= rand.nextFloat() * (max_val - min_val) + min_val;
		}
		

		this.x1_=x1<x2?x1:x2;
		this.x2_=x1>x2?x1:x2;
		this.y1_=y1<y2?y1:y2; 
		this.y2_=y1>y2?y1:y2;
		
	}
	

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder(); 
		sb.append(x1_);
		sb.append(',');
		sb.append(y1_);
		sb.append(',');
		sb.append(x2_);
		sb.append(',');
		sb.append(y2_);
		sb.append('\n');
		
		return sb.toString();
	}

	@Override
	public float width() {
		// TODO Auto-generated method stub
		return Math.abs(this.x2_-this.x1_);
	}

	@Override
	public float height() {
		// TODO Auto-generated method stub
		return Math.abs(y2_-y1_);
	}

	
	@Override
	public float[] coords() {
		// TODO Auto-generated method stub
		float [] coords = {x1_, y1_, x2_, y2_};
		return coords;
	}
}
