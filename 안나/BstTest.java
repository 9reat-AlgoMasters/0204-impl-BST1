package BST;

import java.io.*;
import java.util.*;

import BST.BinarySearchTree.Node;

public class BstTest {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("================== BinarySearchTree ==================");
		System.out.print("트리에 들어갈 숫자 10개를 띄어쓰기로 구분해 작성해주세요.(숫자는 중복될 수 없습니다.) >>>> ");
		StringTokenizer st = new StringTokenizer(br.readLine());

		BinarySearchTree bst = new BinarySearchTree();
		while (st.hasMoreTokens()) {
			bst.insertNode(Integer.parseInt(st.nextToken()));
		}
		print(bst.root);

	}

	private static void print(Node root) {
		if (root == null) {
			return;
		}
		System.out.print(root.key + " => ");
		if (root.left != null) {
			System.out.print("left : "+root.left.key);
		}
		System.out.print(" ");
		if (root.right != null) {
			System.out.println("right : "+root.right.key);
		}
		System.out.println();

		print(root.left);
		print(root.right);

	}

}
//입력 3 2 1 4 5 9 7 8 6 0