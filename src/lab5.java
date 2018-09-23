
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

/**
 * Objective - To use and understand heaps and hash tables Date Last Modified -
 * 3/30/18 Course - Data Structures Lab 5 Instructor - Olac Fuentues Teaching
 * Assistants - Zakia Al Kadri
 *
 * @author Isaias Leos Ayala
 */
public class lab5 {

    /**
     * Obtains a string array and converts its contents into floats
     *
     * @param S
     * @return
     */
    public static float[] stringArrToFloatArr(String[] S) {
        float[] convertedArray = new float[S.length];//create array
        for (int i = 1; i < S.length; i++) {
            convertedArray[i] = Float.parseFloat(S[i]);//convert
        }
        return convertedArray;//return
    }

    /**
     * Creates a hash table given the text file.
     *
     * @param filename
     * @return returns a hash table
     */
    public static hashTableStrings createHTableFromFile(String filename) {
        hashTableStrings hashTable = new hashTableStrings(101);//create hashtable
        int number = 0;
        float[] floatArray;//create float array for numbers
        String[] holdingArray;//create array for holding items from text file
        String line;//a whole line
        try (BufferedReader br = new BufferedReader(new FileReader(filename)))//read file
        {
            line = br.readLine();
            while (line != null) {
                holdingArray = line.split(" ");
                floatArray = stringArrToFloatArr(holdingArray);//convert to float
                hashTable.insert(holdingArray[0], floatArray);//insert the text and the embedding
                line = br.readLine();
                System.out.println(number++);
            }
        } catch (IOException e)//catch errors
        {
            System.out.println("Error: " + e);
        }
        return hashTable;
    }

    public static bstNode insert(bstNode T, String word, float[] embedding) {
        if (T == null) {//if null
            T = new bstNode(word, embedding);
        } else if (word.compareTo(T.item) < 0) {//to the left
            T.left = insert(T.left, word, embedding);
        } else {//to the right
            T.right = insert(T.right, word, embedding);
        }
        return T;
    }

    public static bstNode createBinarySearchTree(String filename) {
        bstNode B = null;//create binary search tree
        float[] embedding;
        String[] holdingArray;//1 word followed by numbers
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(filename)))//read file
        {
            line = br.readLine();
            while (line != null) {
                holdingArray = line.split(" ");
                embedding = stringArrToFloatArr(holdingArray);//creates the embedding
                if (Character.isLetter(holdingArray[0].charAt(0))) {//prevent non letters and numbers from passing
                    B = insert(B, holdingArray[0], embedding);//insert the text and the embedding
                }
                line = br.readLine();
            }
        } catch (IOException e)//catch errors
        {
            System.out.println("Error: " + e);
        }
        return B;
    }

    /**
     * Auxiliary Method for {@link#cosineSimilarityFunc(sNode, sNode)}
     *
     * @param hashTable
     */
    public static void compareHTableWords(hashTableStrings hashTable, String filename) {
        String[] holdingArray;
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(filename)))//read the file
        {
            line = br.readLine();
            while (line != null) {
                holdingArray = line.split(" ");//split the array
                sNode firstWord = hashTable.search(holdingArray[0]);
                sNode secondWord = hashTable.search(holdingArray[1]);
                float similarity = cosineSimilarityFunc(firstWord, secondWord);//obtain the similiarity value
                System.out.println("Similarity [" + firstWord.word + ", " + firstWord.word + "] = " + similarity);//display
                line = br.readLine();
            }
        } catch (IOException e)//catch any errors
        {
            System.out.println("Error: " + e);
        }
    }

    /**
     * Reads a file containing pairs of words (two words per line) and for every
     * pair of words find and display the ”similarity” of the words.
     *
     * @param w0 left word
     * @param w1 right word
     * @return
     */
    public static float cosineSimilarityFunc(sNode w0, sNode w1) {
        float aX = 0;
        float bX = 0;
        float top = 0;
        for (int i = 0; i < w0.embedding.length; i++) {
            top += w0.embedding[i] * w1.embedding[i];
            aX += Math.pow(w0.embedding[i], 2);
            bX += Math.pow(w1.embedding[i], 2);
        }
        aX = (float) Math.sqrt(aX);
        bX = (float) Math.sqrt(bX);

        return (top) / (aX * bX);
    }

    /**
     * Traverse through the hash table to find if any of indexes of the hash
     * table are empty.
     *
     * @param hashTable
     * @return
     */
    public static float percentageOfEmptyLists(hashTableStrings hashTable) {
        int countEmpty = 0;
        for (int i = 0; i < hashTable.H.length; i++) {
            if (hashTable.H[i] == null)//checks if the hash table is empty
            {
                countEmpty++;
            }
        }
        return (float) countEmpty / hashTable.H.length;
    }

    /**
     * Obtain the standard Deviation from the following array given.
     *
     * @param hashTable for length of the linked list inside the hash table
     */
    public static void standardDeviationFunc(hashTableStrings hashTable) {
        double[] stndDev = new double[hashTable.H.length];

        for (int i = 0; i < hashTable.H.length; i++) {
            double countList = 0.0;//keep track of how many nodes their are in the index i of the hash table
            for (sNode T = hashTable.H[i]; T != null; T = T.next)//traverses through the nodes inside the hash table
            {
                countList++;
            }
            stndDev[i] = countList;
        }

        float f1 = 0;
        for (int i = 0; i < stndDev.length; i++) {
            f1 += stndDev[i];
        }
        f1 /= stndDev.length;
        float f2 = 0;
        for (int i = 0; i < stndDev.length; i++) {
            f2 += (float) Math.pow(stndDev[i] - f1, 2);
        }
        f2 /= stndDev.length;
        System.out.println("Standard Deviation: " + Math.sqrt(f2));
    }

    public static void drawHeapTree(heap heap, int index, double x0, double x1, double y, double y_inc) {
        if (index > heap.H[0]) {
            return;
        }
        double xm = (x0 + x1) / 2;
        double yn = y - y_inc;
        if (index * 2 <= heap.H[0]) {//Checks the left child
            StdDraw.line(xm, y, (x0 + xm) / 2, yn);
            drawHeapTree(heap, index * 2, x0, xm, yn, y_inc);
        }
        if (index * 2 + 1 <= heap.H[0]) {//Checks the right child
            StdDraw.line(xm, y, (x1 + xm) / 2, yn);
            drawHeapTree(heap, index * 2 + 1, xm, x1, yn, y_inc);
        }
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.filledCircle(xm, y, 3);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.circle(xm, y, 3);
        StdDraw.text(xm, y, Integer.toString(heap.H[index]));//Prints the value
    }

    public static void pauseAndClear() {
        Scanner s = new Scanner(System.in);
        System.out.println("Press enter to continue.....");
        s.nextLine();
        StdDraw.clear();
    }

    public static int[] randomArray(int size, int range)//O(N)
    {
        int[] generatedArray = new int[size];
        Random random = new Random();
        for (int i = 0; i < generatedArray.length; i++) {
            generatedArray[i] = random.nextInt(range);
        }
        return generatedArray;
    }

    public static void inOrder(bstNode T) {//Eg. 1,2,3,4,5
        if (T != null) {//Check left then check right if empty
            inOrder(T.left);
            System.out.println(T.item);
            inOrder(T.right);
        }
    }

    public static void createFile() {
        try {
            File tmpDir = new File("score.txt");
            boolean exists = tmpDir.exists();
            if (!exists) {
                PrintWriter writer = new PrintWriter("score.txt", "UTF-8");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] Args) throws IOException {
        int x_max = 100;
        int y_max = 100;
        StdDraw.setXscale(0, x_max);
        StdDraw.setYscale(0, y_max);
        StdDraw.setPenColor(StdDraw.BLACK);
        String filename1 = "D:\\OneDrive\\Documents\\UTEP\\Computer Science\\CS2302\\Lab 5\\src\\glove.6B.50d.txt";
        String filename2 = "D:\\OneDrive\\Documents\\UTEP\\Computer Science\\CS2302\\Lab 5\\src\\appendix.txt";//file location

        int[] heapArray
                = {
                    32, 1, 24, 54, 12, 42, 46, 73, 15, 5
                };
        heap heapObject = new heap(heapArray.length);
        for (int i = 0; i < heapArray.length; i++) {
            heapObject.insert(heapArray[i]);
            pauseAndClear();
            drawHeapTree(heapObject, 1, 0, x_max, y_max - 5, (y_max - 10.0) / heapObject.getHeight());
        }
        bstNode binaryTree = createBinarySearchTree(filename1);
        createFile();
        hashTableStrings hashTable = createHTableFromFile(filename1);
        System.out.println("Word Similarities: ");
        compareHTableWords(hashTable, filename2);
        System.out.println("Load factor: " + hashTable.loadFactor());
        System.out.println("Percentage of empty lists:" + percentageOfEmptyLists(hashTable));
        standardDeviationFunc(hashTable);//Obtains the standard deviation
    }
}
