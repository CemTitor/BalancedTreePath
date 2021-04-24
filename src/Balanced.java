import java.io.*;
import java.util.*;

public class Balanced {

    static String inputPath;
    static String outputPath;
    NodeTree root;

    public static void main(String[] args) throws IOException {
/** for command line input
 *
 */
        inputPath = args[0];
        outputPath = args[1];
        /**
         * File reading
         */
        FileReader fileReader1 = new FileReader(inputPath);
        BufferedReader oku1 = new BufferedReader(fileReader1);

        /**
         * Read from input file to arraylist
         */
        ArrayList<Integer> list = new ArrayList<Integer>();
        String[] readerList;
        String reader = oku1.readLine();
        readerList = reader.split(" ");

        for (int i = 0; i < readerList.length; i++) {
            if (!(readerList[i].contains("-"))) {
                list.add(Integer.parseInt(readerList[i]));
            } else {
                list.add(null);
            }
        }
        /**
         * Constructing tree from arraylist
         */
        Balanced t2 = new Balanced();
        t2.root = t2.constructTree(list, t2.root, 0);

        /**
         * Writing to a file.
         */
        File file = new File(outputPath);
        if (!file.exists()) {
            file.createNewFile();
        }
        FileWriter fileWriter = new FileWriter(file, false);
        BufferedWriter bufferedWr1 = new BufferedWriter(fileWriter);
        System.out.println("Is tree balanced ?");
        bufferedWr1.write("Is tree balanced ?");
        bufferedWr1.newLine();
        if (t2.isBalanced(t2.root)) {
            System.out.println(true);
            bufferedWr1.write("true");
        } else {
            System.out.println(false);
            bufferedWr1.write("false");
        }
        bufferedWr1.close();
    }

    /**
     * finding maximum depth of tree
     * @param root
     * @return
     */
    public int maximumDepth(NodeTree root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(maximumDepth(root.right), maximumDepth(root.left));
    }

    /**
     * is tree balanced?
     * @param root
     * @return
     */
    public boolean isBalanced(NodeTree root) {
        if (root == null) {
            return true;
        }
        return (Math.abs(maximumDepth(root.right) - maximumDepth(root.left)) <= 1);

    }

    /** tree class
     *
     */
    static class NodeTree {
        NodeTree left;
        NodeTree right;
        int value;
        int nodeIndex;

        public NodeTree(int value) {
            left = null;
            right = null;
            this.value = value;
            nodeIndex=0;
        }
    }

    /**
     * constructing tree method
     * @param list
     * @param root
     * @param i
     * @return
     */
    public NodeTree constructTree(ArrayList<Integer> list, NodeTree root, int i) {

        if (i >= list.size()) {
            return root;
        }

        if (root == null) {
            if (list.get(i) != null) {
                root = new NodeTree(list.get(i));
                root.nodeIndex = i;
            } else {
                return root;
            }
        }
        if (root.left == null && root.right == null) {
            if (2 * i + 1 < list.size() && list.get(2 * i + 1) != null) {
                root.left = constructTree(list, root.left, 2 * i + 1);
                root.left.nodeIndex = 2*i+1;
            }
            if (2 * i + 2 < list.size() && list.get(2 * i + 2) != null) {
                root.right = constructTree(list, root.right, 2 * i + 2);
                root.right.nodeIndex = 2*i+2;
            }
        }
        return root;
    }
}