
public class bstNode {

	public String item;//word
	public bstNode left;
	public bstNode right;
	public float[] embedding;//values

	public bstNode(String item) {
		this.item = item;
		left = null;
		right = null;
		embedding = null;
	}

	public bstNode(String item, float[] embedding) {
		this.item = item;
		left = null;
		right = null;
		this.embedding = embedding;
	}

	public bstNode(String item, bstNode left, bstNode right, float[] embedding) {
		this.item = item;
		this.left = left;
		this.right = right;
		this.embedding = embedding;
	}


}
