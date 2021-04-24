import java.io.*;
import java.util.*;

public class Path {

    static String inputPath;
    static String outputPath;
    static String entryValue;
    NodeTree root;

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

    public static void main(String[] args) throws IOException {
        /** for command line input
         *
         */
        inputPath = args[0];
        outputPath = args[1];
        entryValue = args[2];
        int entryValue = Integer.parseInt(Path.entryValue);
        
        FileReader fileReader2 = new FileReader(inputPath);
        BufferedReader oku2 = new BufferedReader(fileReader2);

        /**
         * Read from input file to arraylist
         */

        String[] readerList;
        String reader = oku2.readLine();
        readerList = reader.split(" ");
        ArrayList<Integer> list = new ArrayList<Integer>();

        for (int i = 0; i < readerList.length; i++) {
            System.out.print(readerList[i]+" ");
            if (!(readerList[i].contains("-"))) {
                list.add(Integer.parseInt(readerList[i]));
            } else {
                list.add(null);
            }
        }
        System.out.println();
        System.out.println(list.toString());


        Path t2 = new Path();
        t2.root = t2.constructTree(list, t2.root, 0);
        
        ArrayList<Integer> newList = new ArrayList<>();


        Path.isPathSumValid(newList,outputPath,entryValue,0,t2.root);
    }

    /**
     * Consturcting tree method
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


    static void isPathSumValid(ArrayList<Integer> list, String outputPath,int entry, int sum,NodeTree node) throws IOException {
        if (node != null) {
            sum = sum + node.value;
            list.add(node.nodeIndex);
            if (sum == entry && node.right == null && node.left == null) {
                FileWriter fileWr = new FileWriter(outputPath);
                BufferedWriter bufferedWr = new BufferedWriter(fileWr);
                int i = 0;
                while (i < list.size()) {
                    if (i == list.size() - 1) {
                        System.out.println("T[" + list.get(i) + "]=" + entry);
                        bufferedWr.write("T[" + list.get(i) + "]=" + entry);
                    } else {
                        System.out.print("T[" + list.get(i) + "]+");
                        bufferedWr.write("T[" + list.get(i) + "]+");
                    }
                    i++;
                }


            }
            if (node.left != null) {
                isPathSumValid(list, outputPath, entry, sum, node.left);
            }

            if (node.right != null) {
                isPathSumValid(list, outputPath, entry, sum, node.right);
            }

            list.remove(list.size() - 1);
        } else {
        }
    }
}