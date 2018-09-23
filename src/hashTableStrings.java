
/**
 * Objective - To use and understand heaps and hash tables
 *
 * @author Isaias Leos Ayala
 */
public class hashTableStrings {

    public sNode[] H;
    public int nElements = 0;

    /**
     * Creates a hash table with the given parameters.
     *
     * @param n length of the hash table
     */
    public hashTableStrings(int n) {
        H = new sNode[n];
        for (int i = 0; i < n; i++) {
            H[i] = null;
        }
    }

    /**
     * Obtains the key from a string
     *
     * @param S any string
     * @return
     */
    private int h(String S) {
        int h = 0;
        for (int i = 0; i < S.length(); i++) {
            h = (h * 27 + S.charAt(i)) % H.length;
        }
        return h;
    }

    /**
     * Searches the linked list for the given string
     *
     * @param S any string
     * @return
     */
    public sNode search(String S) {
        int k = h(S);
        for (sNode T = H[k]; T != null; T = T.next) {
            if (S.equals(T.word)) {
                return T;
            }
        }
        return null;
    }

    /**
     *
     * @param text
     * @param x
     */
    public void insert(String text, float[] x) {
        if (nElements / H.length > 2) {
            doubleSize();
        }
        int pos = h(text);
        H[pos] = new sNode(text, x, H[pos]);
        nElements++;
    }

    /**
     * Insert a node into the hash table
     *
     * @param t
     */
    private void insert(sNode t) {
        insert(t.word, t.embedding);
    }

    /**
     * Obtains the load factor
     *
     * @return
     */
    public float loadFactor() {
        int count = 0;
        for (int i = 0; i < H.length; i++) {
            for (sNode t = H[i]; t != null; t = t.next) {
                count++;
            }
        }
        return (float) count / H.length;
    }

    /**
     *
     */
    private void doubleSize() {
        hashTableStrings hashTableTemp = new hashTableStrings((H.length * 2) + 1);
        for (int i = 0; i < H.length; i++) {
            for (sNode t = H[i]; t != null; t = t.next) {
                hashTableTemp.insert(t);
            }
        }
        H = hashTableTemp.H;
        nElements = hashTableTemp.nElements;
    }
}
