import exceptions.CustomDuplicatedElementException;

import java.util.ArrayDeque;
import java.util.Deque;

public class BinarySearchTree implements SearchTree {
    static class Node {
        int value;
        Node parent, left, right;
        
        public Node(int value) {
            this.value = value;
            parent = null;
            left = null;
            right = null;
        }
        
        boolean isChildEmpty() {
            return left == null && right == null;
        }
        
        boolean hasAllChild() {
            return left != null && right != null;
        }
        
        boolean hasOneChild() {
            return !isChildEmpty() && !hasAllChild();
        }
    }
    
    private Node root;
    private int size;
    
    @Override
    public void insert(int value) {
        if (isEmpty()) {
            root = new Node(value);
        } else {
            insertRecur(root, value);
        }
        
        size++;
    }
    
    private void insertRecur(Node node, int value) {
        if (node.value == value) {
            throw new CustomDuplicatedElementException(String.format("이미 %d는 트리에 있습니다.", value));
        }
    
        if (value < node.value) {
            if (node.left == null) {
                node.left = new Node(value);
                node.left.parent = node;
            } else {
                insertRecur(node.left, value);
            }
        } else {
            if (node.right == null) {
                node.right = new Node(value);
                node.right.parent = node;
            } else {
                insertRecur(node.right, value);
            }
        }
    }
    
    @Override
    public boolean contains(int value) {
        return containsRecur(root, value);
    }
    
    private boolean containsRecur(Node node, int value) {
        if (node == null) {
            return false;
        }
        
        if (node.value == value) {
            return true;
        }
    
        if (value < node.value) {
            return containsRecur(node.left, value);
        } else {
            return containsRecur(node.right, value);
        }
    }
    
    @Override
    public void delete(int value) {
        Node target = findByValue(root, value); // 없으면 CustomDuplicatedElementException 발생
        System.out.printf("찾은 target : %d\n", target.value);
    
        if (target.isChildEmpty()) { // 자식이 없을 때
            if (target == root) {
                root = null;
                return;
            }
            
            if (target.parent.left == target) {
                target.parent.left = null;
            } else {
                target.parent.right = null;
            }
        } else if (target.hasOneChild()) { // 자식이 하나만 있을 때
            Node next = target.left == null ? target.right : target.left;
            if (target == root) {
                root = next;
                return;
            }
            
            next.parent = target.parent;
            if (target.parent.left == target) {
                target.parent.left = next;
            } else {
                target.parent.right = next;
            }
        } else { // 자식이 둘일 때
            Node replaceNode = findReplaceNode(target.left);
            if (target == root) {
                System.out.println("root!");
                root = replaceNode;
            } else {
                if (target.parent.left == target) {
                    target.parent.left = replaceNode;
                } else {
                    target.parent.right = replaceNode;
                }
            }
            
            if (replaceNode.left != null) {
                if (replaceNode == target.left) {
                    replaceNode.parent.left = replaceNode.left;
                } else {
                    replaceNode.parent.right = replaceNode.left;
                }
            } else {
                if (replaceNode == target.left) {
                    replaceNode.parent.left = null;
                } else {
                    replaceNode.parent.right = null;
                }
            }
            
            replaceNode.parent = target == root ? null : target.parent;
            target.right.parent = replaceNode;
            
            
            replaceNode.left = target.left;
            replaceNode.right = target.right;
        }
        
        size--;
    }
    
    private Node findReplaceNode(Node node) {
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }
    
    private Node findByValue(Node node, int value) {
        if (node == null) {
            throw new CustomDuplicatedElementException(String.format("%d는 트리에 존재하지 않습니다.", value));
        }
    
        if (node.value == value) {
            return node;
        }
    
        if (value < node.value) {
            return findByValue(node.left, value);
        } else {
            return findByValue(node.right, value);
        }
    }
    
    @Override
    public boolean isEmpty() {
        return size==0;
    }
    
    @Override
    public int size() {
        return size;
    }
    
    public void printTree() {
        Deque<Node> q = new ArrayDeque<>();
        q.add(root);
        int level = -1;
    
        while (!q.isEmpty()) {
            level++;
            int size = q.size();
            System.out.printf("level %d ---> ", level);
    
            for (int i = 0; i < size; i++) {
                Node now = q.poll();
                System.out.printf("%d ", now.value);
    
                for (int j = 0; j < 2; j++) {
                    Node next = j==0 ? now.left : now.right;
                    if (next == null) continue;
                    q.add(next);
                }
            }
            System.out.println();
        }
    }
}
