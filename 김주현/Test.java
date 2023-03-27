import java.io.*;

/*
 *  Level : 0                  6
 *                        /         \
 *  Level : 1            3           20
 *                    /     \      /    \
 *  Level : 2       1        5   8       25
 *                   \            \
 *  Level : 3         2            10
 *                                  \
 *  Level : 4                       19
 *                                 /
 *  Level : 5                     15
 * */

public class Test {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
    
        BinarySearchTree bst = new BinarySearchTree();
        bst.insert(6);
        bst.insert(3);
        bst.insert(20);
        bst.insert(1);
        bst.insert(5);
        bst.insert(8);
        bst.insert(25);
        bst.insert(2);
        bst.insert(10);
        bst.insert(19);
        bst.insert(15);
    
        
        bst.printTree();
    
        /*for (int i = 1; i <= 10; i++) {
            System.out.printf("has %d ? ---> %b\n", i, bst.contains(i));
        }*/
        
        /*bst.delete(20);
        System.out.println("20을 지웠습니다!");
        bst.printTree();*/
        
        bst.delete(6);
        System.out.println("6을 지웠습니다!");
        bst.printTree();
    
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
