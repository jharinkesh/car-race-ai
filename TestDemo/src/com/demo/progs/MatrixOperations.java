package com.demo.progs;

public class MatrixOperations {

	
	public static void main(String[] args) {
		
		int a[][] = {
				{ 3, 5, 8},
				{ 4, 9, 5 },
		};
		
		int b[][] = {
				{ 6, 1, 3},
				{ 2, 4, 7 },
		};
		
		int c[][] = {
				{4, 9},
				{5, 6},
				{4, 3}
		};
		
		//disp(a);
		//int d[][] = add(a,b);
		//disp(add(a,b));
		//disp(transpose(a));
		//disp(multiply(a,c));
		
	}
	
	
	//To display a matrix elements 
	static void disp(int x[][]){
		int rows = x.length, cols = x[0].length;
		for(int i=0;i<rows;i++){
			for(int j=0;j<cols;j++){
				System.out.print(x[i][j]+" ");
			}
			System.out.println();
		}
	}
	
	// Matrix addition
	static int[][] add(int x[][], int y[][]){
		int rows = x.length, cols = x[0].length;
		for(int i=0;i<rows;i++){
			for(int j=0;j<cols;j++){
				x[i][j] = x[i][j] + y[i][j];
			}
		}
		return x;
	}
	
	static int[][] multiply(int x[][], int y[][]) {
		int xrows = x.length, xcols = x[0].length, ycols = y[0].length;
		int z[][] = new int[xrows][ycols];
		for (int i = 0; i < xrows; i++) {
			for (int j = 0; j < ycols; j++) {
				for (int k = 0; k < xcols; k++) {
					z[i][j] = z[i][j] + x[i][k] * y[k][j];
				}
			}
		}
		return z;
	}
	
	// Transpose of a matrix
	static int[][] transpose(int x[][]){
		int rows = x.length, cols = x[0].length;
		int y[][] = new int[cols][rows];
		for(int i=0;i<rows;i++)
			for(int j=0;j<cols;j++)
				y[j][i] = x[i][j];
		
		return y;
	}
	
}
