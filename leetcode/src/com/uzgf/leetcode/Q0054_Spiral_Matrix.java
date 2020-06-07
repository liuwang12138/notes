package com.uzgf.leetcode;

import java.util.List;
import java.util.ArrayList;

/**
 * 给定一个包含 m x n 个元素的矩阵（m 行, n 列），请按照顺时针螺旋顺序，返回矩阵中的所有元素。
 *
 *	示例 1:
 *	
 *	输入:
 *	[
 *	 [ 1, 2, 3 ],
 *	 [ 4, 5, 6 ],
 *	 [ 7, 8, 9 ]
 *	]
 *	输出: [1,2,3,6,9,8,7,4,5]
 *	示例 2:
 *	
 *	输入:
 *	[
 *	  [1, 2, 3, 4],
 *	  [5, 6, 7, 8],
 *	  [9,10,11,12]
 *	]
 *	输出: [1,2,3,4,8,12,11,10,9,5,6,7]
 *	
 *	来源：力扣（LeetCode）
 *	链接：https://leetcode-cn.com/problems/spiral-matrix
 *	著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class Q0054_Spiral_Matrix {
    public List<Integer> spiralOrder(int[][] matrix) {
    	
    	List<Integer> result = new ArrayList<>();
    	
    	if (matrix == null || matrix.length < 1) {
    		return result;
    	}
    	
    	int topBound = 0;
    	int leftBound = 0;
    	int rightBound = matrix[0].length - 1;
    	int bottomBound = matrix.length - 1;
    	
    	int maxSize = (rightBound + 1) * (bottomBound + 1);
    	
    	int i = 0, j = 0;
    	
    	DIR dir = DIR.RIGHT;
    	
    	while (result.size() < maxSize) {

    		switch(dir) {
    			case RIGHT:
    				if (j < rightBound) {
    					result.add(matrix[i][j++]);
    				} else if (j == rightBound) {
    					result.add(matrix[i++][j]);
    					dir = DIR.DOWN;
    					topBound++;
    				} else {
    					throw new RuntimeException("j > rightBound....");
    				}
    				break;
    				
    			case DOWN:
    				if (i < bottomBound) {
    					result.add(matrix[i++][j]);
    				} else if (i == bottomBound) {
    					result.add(matrix[i][j--]);
    					dir = DIR.LEFT;
    					rightBound--;
    				} else {
    					throw new RuntimeException("i > bottomBound");
    				}
    				break;
    				
    			case LEFT:
    				if (j > leftBound) {
    					result.add(matrix[i][j--]);
    				} else if (j == leftBound) {
    					result.add(matrix[i--][j]);
    					dir = DIR.UP;
    					bottomBound--;
    				} else {
    					throw new RuntimeException("j < leftBound");
    				}
    				break;
    				
    			case UP:
    				if (i > topBound) {
    					result.add(matrix[i--][j]);
    				} else if (i == topBound) {
    					result.add(matrix[i][j++]);
    					dir = DIR.RIGHT;
    					leftBound++;
    				} else {
    					throw new RuntimeException("i < topBound");
    				}
    				break;
    		}
    		
    	}
    	
    	return result;
    }
    
    private enum DIR {
    	LEFT,
    	RIGHT,
    	UP,
    	DOWN
    }
    
    public static void main(String[] args) {
    	int[][] arr = new int[3][3];
    	
    	int k = 0;
    	for (int i=0; i<3; i++) {
    		for (int j=0; j<3; j++) {
    			arr[i][j] = ++k; 
    		}
    	}
    	
    	for (int i=0; i<3; i++) {
    		for (int j=0; j<3; j++) {
    			System.out.print(arr[i][j] + " ");
    		}
    		System.out.println();
    	}
    	
    	/*
    	 *  1 2 3 
		 *	4 5 6 
		 *	7 8 9 
		 *	1 2 3 6 9 8 7 4 5 
    	 */

    	
//    	int[][] arr = new int[4][4];
//    	
//    	int k = 0;
//    	for (int i=0; i<4; i++) {
//    		for (int j=0; j<4; j++) {
//    			arr[i][j] = ++k; 
//    		}
//    	}
//    	
//    	for (int i=0; i<4; i++) {
//    		for (int j=0; j<4; j++) {
//    			System.out.print(arr[i][j] + " ");
//    		}
//    		System.out.println();
//    	}
    	
    	/*
    	 *  1 2 3 4 
		 *	5 6 7 8 
		 *	9 10 11 12 
		 *	13 14 15 16 
		 *	1 2 3 4 8 12 16 15 14 13 9 5 6 7 11 10 
    	 */
    	
    	
    	
    	Q0054_Spiral_Matrix s = new Q0054_Spiral_Matrix();
    	
    	List<Integer> result = s.spiralOrder(arr);
    	
    	result.forEach(i -> System.out.print(i + " "));
    	
    }
}
