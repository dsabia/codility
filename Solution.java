// you can also use imports, for example:
// import java.util.*;

// you can write to stderr for debugging purposes, e.g.
// System.err.println("this is a debug message");

class Solution {
	
	public static void main(String[] args) {
		Solution s = new Solution();
		
		s.solution(new int[]{4, 35, 80, 123, 12345, 44, 8, 5}, 10);
		
		s.solution(new int[]{4, 35, 80, 123, 12345, 44, 8, 5}, 3);
		
		s.solution(new int[]{4, 35, 80, 123, 12345, 44, 8, 5}, 4);
	}


    public void solution(int[] A, int K) {
        int cellMaxLength = findMaxLength(A);
        int numCol = A.length < K ? A.length : K;
        boolean extracol = A.length%numCol>0;
        int numRows = (A.length / numCol) + (extracol?1:0);
        
        int[][] matrix = createMatrix(A,numCol,numRows);
        
        printCmdTable(matrix, K, cellMaxLength, numRows);
    }
    
    int findMaxLength(int[] A){
        int maxLength = 0;
        for(int i = 0; i < A.length; i ++){
            int size = String.valueOf(A[i]).length();
            if(size > maxLength){
                maxLength = size;
            }
        }
        return maxLength;
    }
    
    int[][] createMatrix(int[]A, int col, int row){
        int[][] matrix = new int[row][col];
        for(int i = 0; i < A.length; i ++){
            matrix[i/col][i%col] = A[i];
        }
        
        return matrix;
    }
    
    void printCmdTable(int[][] matrix, int K, int cellMaxLength, int numRows){
        StringBuilder sb = new StringBuilder();
        
        for(int k = 0; k < matrix.length; k++){
            boolean firstRow = k == 0;
            boolean lastRow = k == matrix.length -1;
            
            if(firstRow){
                sb.append(getTableHorizontalLine(matrix[0].length, cellMaxLength));
                sb.append("\n");
            }
            
            if(!lastRow){
	            sb.append(getNumberLine(matrix[k], matrix[k].length, cellMaxLength));
	            sb.append("\n");
	            sb.append(getTableHorizontalLine(matrix[k].length, cellMaxLength));
	            sb.append("\n");
	            
            }else{
            	int submatrix[] = getSubArray(matrix[k]);
            	
            	sb.append(getNumberLine(submatrix, submatrix.length, cellMaxLength));
            	sb.append("\n");
	            sb.append(getTableHorizontalLine(submatrix.length, cellMaxLength));
	            sb.append("\n");
            }
        }
        System.out.println(sb.toString());
    }
    
    private int[] getSubArray(int[] rowData) {
    	int indexFirstZero = rowData.length;
    	for (int i = 0; i < rowData.length; i++) {
			if(rowData[i] == 0){
				indexFirstZero = i;
			}
		}
    	
    	int [] submatrix = new int[indexFirstZero];
    	
    	for (int i = 0; i < indexFirstZero; i++) {
			submatrix[i] = rowData[i];
		}
		return submatrix;
	}


	String getNumberLine(int[]A, int K, int cellLength){
        StringBuilder sb = new StringBuilder();
        sb.append("|");
        for(int i = 0; i < A.length; i ++){
            sb.append(getNumberCell(A[i], cellLength));
            sb.append("|");    
        }
        return sb.toString();
    }
    
    String getTableHorizontalLine(int K, int cellLength){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < K; i ++){
            sb.append("+");
            sb.append(getHorizontalLine(cellLength));
        }
        sb.append("+");
        return sb.toString();  
    }
    
    String getHorizontalLine(int length){
        StringBuilder sb = new StringBuilder();    
        for(int i = 0; i < length; i ++){
            sb.append("-");
        }
        return sb.toString();    
    }
    
    public String getNumberCell(int n, int length){
        String numberString = String.valueOf(n);
        StringBuilder sb = new StringBuilder();  
        for(int i = numberString.length(); i< length; i++){
            sb.append(" ");
        }
        sb.append(numberString);
        return sb.toString();     
    }
}
