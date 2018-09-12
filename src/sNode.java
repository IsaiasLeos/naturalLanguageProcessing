/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Isaias Leos Ayala
 */
public class sNode
{

	public String word;
	public float[] embedding;
	public sNode next;

	/**
	 *
	 * @param S text
	 * @param E embedding
	 * @param N connection to the next sNode
	 */
	public sNode(String S, float[] E, sNode N)
	{
		word = S;
		embedding = new float[50];
		for(int i = 0; i < 50; i++)
		{
			embedding[i] = E[i];
		}
		next = N;
	}
}
