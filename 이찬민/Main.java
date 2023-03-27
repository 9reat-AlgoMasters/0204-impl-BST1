package implementation.bst;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("이진 탐색 트리에 넣을 숫자들을 입력해주세요(-1제외) (띄워쓰기 사용)>> ");
        // 예시
        // 8 4 10 3 6 9 14 11 17 13
        // 8 4 10 3 6 9 14 13 17 12
        
        // 2번째  예시 그림
        //             8
        //      4             10
        //   3     6        9     14
        //                      13  17
        //                    12

        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        BinarySearchTreeImpl bst = new BinarySearchTreeImpl();

        while (st.hasMoreTokens()) {
            bst.insert(Integer.parseInt(st.nextToken()));
            bst.levelOrder();
            System.out.println("/////////////////////////////////////////////////////////////");
        }
        
        while (true) {
            System.out.println("이진 탐색 트리에서 삭제할 숫자를 입력해주세요(-1 입력시 종료)>> ");
            int num = Integer.parseInt(br.readLine());

            if (num == -1) {
                break;
            }

            bst.delete(num);
            bst.levelOrder();
        }
        
        System.out.println(sb.toString());
    }
}
