package com.demo.product;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
public class TrainData {

	float [][] X, Y;
	int m = 0, numItr = 2000;
	float[][] w = new float[2][1], dw;
	float b = 0, db = 0, lrate =  0.0002f;

	public static void main(String[] args) throws FileNotFoundException {
		TrainData t = new TrainData().initialize().optimize();
		//float[][] testx = {{123.f}, {65.6f}};
		//float[][] testx = {{448f}, {908f}};
		//t.predict(testx);
		
		System.out.println("\nFinal prediction: "+t.predict(36,461));
//		new TrainData().initialize().optimize();
//	       int[][] x = { {3, -2, 5}, {3, 0, 4} };
//	       int[][] y = { {2, 3}, {-9, 0}, {0, 4} };
//	       int c[][] = new TrainData().multiply(x, y);
//	       new TrainData().disp(c);
	}
	
	public int predict(int x, int y){
		float[][] testx = {{(float)x}, {(float)y}};
		float[][] p = calculateA(transpose(w), testx, b);
		System.out.println("\n Prediction matrix: ");
		disp(p);
		return sum(p)>0.5f?1:0;
	}
	
	public void predict(float[][] testX){
		System.out.println("\n\n Prediction: ");
		disp(calculateA(transpose(w), testX, b));
	}
	
	TrainData initialize() throws FileNotFoundException{
	    InputStream stream = new FileInputStream("tset.txt");	    
	    String line;
	    List<String> ls = new ArrayList<>();
	    	
	    try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream))) {
	      System.out.println("Reading training data");
	      while ((line = reader.readLine()) != null) {
	        //System.out.println(line);
	        ls.add(line);
	      }
	    } catch (IOException ex) {
	    }
	    
	    m = ls.size();
	    X = new float[2][m];
	    Y = new float[1][m];
	    
	    for(int i=0;i<m;i++){
	    	String[] part = ls.get(i).split(",");
	    	X[0][i] = Float.parseFloat(part[0]);
	    	X[1][i] = Float.parseFloat(part[1]);
	    	Y[0][i] =  Float.parseFloat(part[2]);
	    }
	    
	    System.out.println("\nInput array");
	    for(int i =0 ;i<2;i++){
	    	for(int j=0;j<m;j++){
	    		System.out.print(X[i][j]+" ");
	    	}
	    	System.out.println();
	    }
	    
	    System.out.println("\nOutput array");
	    for(int i =0 ;i<m;i++){
	    		System.out.print(Y[0][i]+" ");
	    }
	    return this;
	}
	
	float[][] calculateA(float[][] w, float[][]x, float b){
		float[][] a = multiply(w,x);
		for(int i=0;i<a.length;i++){
			for(int j=0;j<a[0].length;j++){
				a[i][j] += b;
				float eminusX = Float.parseFloat(Math.exp(Double.parseDouble(-a[i][j]+""))+"");
				a[i][j] =  1 / (1 + eminusX);
			}
		}
		
		return a;
	}
	
//	def propagate(w, b, X, Y):
//	    m = X.shape[1]
//	    A = sigmoid(np.dot(w.T,X)+b)
//	    cost = (-1/m) * np.sum( Y * np.log(A) + (1-Y) * np.log(1-A) )
//	    dw = (1/m) * np.dot(X, (A-Y).T)
//	    db = (1/m) * np.sum(A-Y) 
//	    cost = np.squeeze(cost)    
//	    return dw,db, cost
	TrainData propagate(){
		float[][] A = calculateA(transpose(w), X, b);
		System.out.println("A:");
		disp(A);
		
//		float[][] AminusY = minus(A, Y);
//		System.out.println("A-Y: ");
//		disp(AminusY);
//		float[][] XdotK = multiply (X, transpose(AminusY));
//		System.out.println("X.(A-Y)t: ");
//		disp(XdotK);
//		dw = multiply( (1f/m), XdotK);
		
		dw = multiply( (1f/m), multiply(X, transpose(minus(A, Y))) );
				
		System.out.println("dw:");
		disp(dw);
		db = (1f/m) * sum(minus(A, Y));
		System.out.println("db: "+db);
	   return this; 	
	}
	
//	def optimize(w, b, X, Y, num_iterations, lRate):        
//	    for i in range(num_iterations):
//	        dw,db, cost = propagate(w, b, X, Y)
//	        w = w - lRate * dw
//	        b = b - lRate * db	        
//	    return dw, db, w,b
	TrainData optimize(){	
		for(int i=1; i<=numItr;i++){
			System.out.println("\n===================================== ["+i+"]==============================");
			propagate();
			w = minus(w,multiply(lrate, dw));
			System.out.println("w:");
			disp(w);
			b = b - lrate * db;			
			System.out.println("b: "+b);
		}
		return this;
	}
	
	void disp(float x[][]) {
		for (int i = 0; i < x.length; i++) {
			for (int j = 0; j < x[0].length; j++) {
				System.out.print(x[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	float sum(float[][] x) {
		float c = 0;
		for (int i = 0; i < x.length; i++) {
			for (int j = 0; j < x[0].length; j++) {
				c += x[i][j];
			}
		}
		return c;
	}
	
	float[][] minus(float x[][], float y[][]) {
		float[][] c = new float[x.length][x[0].length];
		for (int i = 0; i < x.length; i++) {
			for (int j = 0; j < x[0].length; j++) {
				c[i][j] = x[i][j] - y[i][j];
			}
		}
		return c;
	}

	float[][] transpose(float[][] x) {
		float[][] c = new float[x[0].length][x.length];
		for (int i = 0; i < x.length; i++)
			for (int j = 0; j < x[0].length; j++)
				c[j][i] = x[i][j];
		return c;
	}

	
	float[][] multiply(float x[][], float y[][]) {
		float[][] c = new float[x.length][y[0].length];
		for (int i = 0; i < x.length; i++) {
			for (int j = 0; j < y[0].length; j++) {
				for (int k = 0; k < x[0].length; k++) {
					c[i][j] = c[i][j] + x[i][k] * y[k][j];
				}
			}
		}
		return c;
	}
	
	float[][] multiply(float n, float x[][]) {
		float[][] c = new float[x.length][x[0].length];
		for (int i = 0; i < x.length; i++) {
			for (int j = 0; j < x[0].length; j++) {
					c[i][j] = x[i][j] * n;
			}
		}
		return c;
	}	


}
