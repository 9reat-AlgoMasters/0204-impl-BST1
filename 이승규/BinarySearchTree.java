public class BinarySearchTree {
    static class Node {
        private int data;
        private Node left;
        private Node right;
        private Node parent;

        public Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
            this.parent = null;
        }
    }
    private Node root;

    private int size = 0;

    public BinarySearchTree(int key) {
        root = new Node(key);
        size++;
    }

    public int getSize() {
        return size;
    }

    public boolean addChild(int key) {
        boolean isS = addChildReCur(root, key);
        if (isS) {
            size++;
            return true;
        }
        return false;
    }

    // 입력된 data가 들어갈 노드를 찾아주는 함수
    // 중복되는 값은 없다고 한다.
    public boolean addChildReCur(Node node, int key) {
        //노드의 자식이 없을 경우 자식 추가
        if(key < node.data) { //key가 node값보다 작을 경우
            if (node.left == null) { // node의 왼쪽 자식이 없을 경우
                Node child = new Node(key);
                node.left = child;
                setParent(node, child);
                return true;
            } else { // node의 왼쪽 자식이 있는 경우
                addChildReCur(node.left, key);
            }
        }
        if(key > node.data) { // key가 node값보다 클 경우
            if (node.right == null) { // node의 오른쪽 자식이 없을 경우
                Node child = new Node(key);
                node.right = child;
                setParent(node, child);
                return true;
            }
            else {
                addChildReCur(node.right, key);
            }
        }
        if(key == node.data) {
            System.out.printf("중복된 값이 있어 key(%d)값을 추가하지 못했습니다.\n", key);
            return false;
        }

        return false;
    }
    public Node searchNode(int key) {
        return searchByKey(root, key);
    }

    // key값으로 노드를 찾는 함수
    public Node searchByKey(Node node, int key) {
        if(node.left == null && node.right == null && key != node.data) {
            System.out.printf("key값 (%d)을(를) 가지는 노드가 존재하지 않습니다.\n", key);
            return null;
        }

        if (key == node.data) {
            return node;
        }

        if(key < node.data) {
            if (node.left == null) {
                System.out.printf("key값 (%d)을(를) 가지는 노드가 존재하지 않습니다.\n", key);
                return null;
            }
            else {
                return searchByKey(node.left, key);
            }
        }

        if(key > node.data) {
            if(node.right == null) {
                System.out.printf("key값 (%d)을(를) 가지는 노드가 존재하지 않습니다.\n", key);
                return null;
            }
            else {
                return searchByKey(node.right, key);
            }
        }
        else {
            System.out.printf("key값 (%d)을(를) 가지는 노드가 존재하지 않습니다.\n", key);
            return null;
        }

    }

    public boolean remove(int key) {
        boolean isS = removeReCur(key);
        if (isS) {
            size--;
            return true;
        }
        return false;
    }

    //******************************************************** 이게 젤 어렵 **********************************************************
    public boolean removeReCur(int key) {
        Node newNode = searchNode(key);
        if(newNode == null) {
            System.out.println("노드가 트리에 존재하지 않습니다.");
            return false;
        }
        Node parentNode = newNode.parent;

        // 0. 삭제하고자 하는 노드가 트리에 없는 경우

        // 1. 삭제하고자 하는 노드가 리프노드인 경우 -> 그냥 삭제
        if (newNode.left == null && newNode.right == null) {
            //부모 노드를 불러서 끊어줘야 함
            if (parentNode == null) { // 삭제할 녀석이 리프노드이고 루트노드라면(부모노드는 null)
                this.root = null;
                return true;
            }
            if (parentNode.left == newNode) { // 삭제할 녀석이 루트노드가 아니고 왼쪽자식이었다면
                parentNode.left = null;
                return true;
            }
            if (parentNode.right == newNode) { //삭제할 녀석이 루트노드가 아니고 오른쪽 자식이었다면
                parentNode.right = null;
                return true;
            }
        }

        // 2. 삭제하고자 하는 노드가 자식을 하나만 가질 경우 -> 삭제할 노드의 자식을 삭제할 노드의 부모와 연결
        // 2-1. 왼쪽 자식만 가질 경우
        if (newNode.left != null && newNode.right == null) {
            if(parentNode == null) { // 삭제할 녀석이 왼쪽 자식만 갖고 루트노드라면(부모노드는 null)
                this.root = newNode.left;
                return true;
            }
            if (parentNode.left == newNode) { // 삭제할 녀석이 왼쪽 자식만 갖고 왼쪽 자식이었다면
                parentNode.left = newNode.left;
                return true;
            }
            if(parentNode.right == newNode) { // 삭제할 녀석이 왼쪽자식만 갖고 오른쪽 자식이었다면
                parentNode.right = newNode.left;
                return true;
            }
        }

        // 2-2. 오른쪽 자식만 가질 경우
        if (newNode.left == null && newNode.right != null) {
            if(parentNode == null) { // 삭제할 녀석이 오른쪽 자식만 갖고 루트노드라면(부모노드는 null)
                this.root = newNode.right;
                return true;
            }
            if (parentNode.left == newNode) { // 삭제할 녀석이 오른쪽 자식만 갖고 왼쪽 자식이었다면
                parentNode.left = newNode.right;
                return true;
            }
            if(parentNode.right == newNode) { // 삭제할 녀석이 오른쪽 자식만 갖고 오른쪽 자식이었다면
                parentNode.right = newNode.right;
                return true;
            }
        }

        // 3. 삭제하고자 하는 노드가 양쪽 자식 모두 가질 경우 -> 삭제할 노드의 오른쪽 서브트리중 가장 작은 노드와 맞바꾼 후 삭제
        if (newNode.left != null && newNode.right != null) {
            Node subRootNode = searchNextMinNode(newNode);
            if (parentNode == null) { //삭제할 녀석이 양쪽 자식을 모두 갖고 루트노드라면
                root = subRootNode;
                root.left = newNode.left;
                root.right = newNode.right;
                return true;
            }
            if(parentNode.left == newNode) { // 삭제할 녀석이 양쪽 자식을 모두 갖고, 왼쪽 자식이었다면
                parentNode.left = subRootNode;
                subRootNode.left = newNode.left; // 자식들 다시 링크 시키기
                subRootNode.right = newNode.right;
                return true;
            }
            if(parentNode.right == newNode) { // 삭제할 녀석이 양쪽 자식을 모두 갖고, 오른족 자식이었다면
                parentNode.right = subRootNode;
                subRootNode.left = newNode.left; // 자식들 다시 링크 시키기
                subRootNode.right = newNode.right;
                return true;
            }
        }
        return false;
    }

    public Node searchNextMinNode(Node node) { // node를 root노드로 하는 서브트리의 최소값을 가지는 노드 반환하는 함수
        return searchMinNode(node.right);
    }

    public Node searchMinNode(Node node) {
        if (node.left == null) {
            node.parent.left = null; //찾은 노드 연결 끊어주고 리턴하기
            return node;
        }
        return searchMinNode(node.left);
    }

    public void setParent(Node node1, Node node2) { // node1을 node2의 부모노드로 설정하는 함수
        node2.parent = node1;
    }

    public void inrOrderPrint() {
        inrOrderReCur(root);
        System.out.println();
    }
    public void inrOrderReCur(Node node) {
        if(node == null) return;

        inrOrderReCur(node.left);
        System.out.print(node.data + " ");
        inrOrderReCur(node.right);

    }


}
