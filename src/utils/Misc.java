package utils;

import java.util.ArrayList;

public class Misc
{
	public static int[][] ArrayListToInt2D(ArrayList<Integer> list, int row, int col) {
		int[][] arr = new int[row][col];
		int index = 0;

		for (int j = 0; j < row; j++) {
			for (int i = 0; i < col; i++) {
				arr[j][i] = list.get(index++);
			}
		}

		return arr;
	}

	public static int[] int2DToArrayList(int[][] array2D)
	{
		int[] array1D = new int[array2D.length * array2D[0].length];

		for (int i = 0; i < array2D.length; i++)
			System.arraycopy(array2D[i], 0, array1D, array2D.length * i, array2D[0].length);

		return array1D;
	}
}
