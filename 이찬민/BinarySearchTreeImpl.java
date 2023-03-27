package implementation.bst;

import java.util.ArrayDeque;
import java.util.Queue;

public class BinarySearchTreeImpl implements BinarySearchTree{

    static Node root;
    int size;

    class Node {
        Node left;
        int val;
        Node right;

        public Node(int val) {
            this.left = null;
            this.val = val;
            this.right = null;
        }
    }

    public BinarySearchTreeImpl() {
        this.root = null;
        this.size = 0;
    }


    @Override
    public void insert(int val) {

        Node currentNode = root;
        Node currentParent = null;

        if (currentNode == null) {
            root = new Node(val);

            size++;
            return;
        }

        while(currentNode != null) {
            currentParent = currentNode;

            if (val < currentNode.val) {
                currentNode = currentNode.left;

            } else if (val >= currentNode.val) {
                currentNode = currentNode.right;
            }
        }


        if (val < currentParent.val) {
            currentParent.left = new Node(val);
        }
        if (val >= currentParent.val) {
            currentParent.right = new Node(val);
        }
        size++;

    }

    @Override
    public void delete(int val) {
        Node currentNode = root;
        Node parentNode = null;

        // root 임시 ㅊ라
        if (val == root.val) {
            root = null;
            System.out.println("root를 삭제했습니다");
            System.exit(0);
        }
        // root 따로 처리 필요!!!

        while(true) {
            if ((currentNode.left != null && currentNode.left.val == val)
                    || (currentNode.right != null && currentNode.right.val == val)) {
                
                parentNode = currentNode;
                int count = 0;

                // currentNode의 자식수 확인
                if (currentNode.left != null && currentNode.left.val == val) {
                    currentNode = parentNode.left;
                    count = check(currentNode);
                } else if (currentNode.right != null && currentNode.right.val == val) {
                    currentNode = parentNode.right;
                    count = check(currentNode);
                }
                
                // 자식 0개
                if (count == 0) {
                    System.out.println("자식이 없습니다.");
                    
                    // val값을 통해 부모의 왼쪽인지 오른쪽인지 판별
                    setRightChild(val, parentNode ,null);

                    size--;
                    break;
                }
                
                // 자식 1개
                if (count == 1) {
                    System.out.println("자식이 한개입니다.");
                    if (currentNode.left == null) {
                        System.out.println("오른쪽 자식만 있습니다.");
                        if (val == root.val) {
                            root = root.right;
                        } else {
                            // 오른쪽으로
                            currentNode = currentNode.right;
                            // parent와 연결
                            setRightChild(val, parentNode , currentNode);
                        }
                    } else {
                        System.out.println("왼쪽 자식만 있습니다.");
                        if (val == root.val) {
                            root = root.left;
                        } else {
                            // 왼쪽으로 
                            currentNode = currentNode.left;
                            // parent와 연결
                            setRightChild(val, parentNode , currentNode);
                        }
                    }
                    size--;
                    break;
                }
                
                // 자식 2개
                if (count == 2) {
                    // 자식 2개다  있을때
                    // 삭제할 노드가 currentNode
                    System.out.println("자식이 2개 있습니다.");
                    Node temp = currentNode.right;
                    Node tempParent = null;

                    // 삭제할 노드의 오른쪽 자식을 봤을때
                    // 왼쪽 자식이 null이라면 삭제할 노드의 오른쪽 자식이 올라와야한다.
                    if (temp.left == null) {
                        currentNode.right = temp.right;
                        temp.right = null;

                        currentNode.val = temp.val;
                        break;
                    }
                    
                    // 오른쪽 서브트리의 가장 작은 값을 찾아간다.
                    while (temp.left != null) {
                        tempParent = temp;
                        temp = temp.left;
                    }

                    // 오른쪽에서 제일 작은 노드를 찾았는데
                    // 그 노드의 오른쪽 노드가 있는 경우
                    // 바로 위의 부모에 연결 해줌
                    // 오른쪽 노드가 없다면 left를 null로 초기화 해주는 부분이된다.
                    tempParent.left = temp.right;
                    temp.right = null;
                    
                    // 찾은 값을 위로 올려주기
                    currentNode.val = temp.val;

                    size--;
                    break;
                }

            }
            
            // 맞는 위치 찾아가기
            if (val < currentNode.val) {
                currentNode = currentNode.left;

            } else if (val > currentNode.val) {
                currentNode = currentNode.right;
            }
        }
    }

    // 부모의 알맞은 left, right 위치에 자식을 넣는 메서드
    public void setRightChild(int val, Node parentNode, Node currentNode) {
        // val값을 통해 부모의 왼쪽인지 오른쪽인지 판별
        if (val < parentNode.val) {
            parentNode.left = currentNode;
        } else {
            parentNode.right = currentNode;
        }
    }

    // 레벨 순회
    public void levelOrder() {
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            System.out.print(node.val +" ");
            if(node.left != null) queue.offer(node.left);
            if(node.right != null) queue.offer(node.right);
        }
        System.out.println("\n");
    }

    // 자식 수 확인하는 메서드
    private int check(Node currentNode) {
        int count = 0;
        if(currentNode.left != null) {
            count += 1;
        }

        if(currentNode.right != null) {
            count += 1;
        }

        return count;
    }
}
