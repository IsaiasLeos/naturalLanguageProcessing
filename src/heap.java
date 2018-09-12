/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Isaias Leos Ayala
 */
public class heap
{

	public int[] H;

	public heap(int n)
	{
		H = new int[n];
		H[0] = 0;
	}

	public int insert(int element)
	{
		//Check if heap is full
		if(H[0] >= H.length - 1)
		{
			return -1;
		}
		//Increase the size of the heap
		H[0] += 1;
		int i = H[0];
		while((i > 1) && (element < H[i / 2]))
		{
			H[i] = H[i / 2];
			i = i / 2;
		}
		H[i] = element;
		return i;
	}

	public int extractMin()
	{
		if(H[0] <= 0)
		{
			return -1;
		}
		int minVal = H[1];
		H[1] = H[H[0]];
		H[0]--;
		int i = 1;
		int min = 1;
		int temp;
		while(true)
		{
			if(2 * i <= H[0] && H[min] > H[2 * i])
			{
				min = 2 * i;
			}
			if(2 * i + 1 <= H[0] && H[min] > H[2 * i + 1])
			{
				min = 2 * i + 1;
			}
			if(min == i)
			{
				break;
			}
			temp = H[i];
			H[i] = H[min];
			H[min] = temp;
			i = min;
		}
		return minVal;
	}

	public void print()
	{
		System.out.println("Heap Size: " + H[0]);
		System.out.print("H:");
		for(int i = 0; i <= H[0]; i++)
		{
			System.out.print(" " + H[i]);
		}
		System.out.println("");
	}

	public int getHeight()
	{
		int count = -1;
		for(int i = 1; i <= H[0]; i *= 2)
		{
			count++;
		}
		return count;
	}
}
