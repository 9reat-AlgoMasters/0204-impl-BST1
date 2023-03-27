public class BinarySearchTree implements IBST {

    static class Node{
        int value;
        Node leftChild;
        Node rightChild;

        public Node(int value) {
            this.value = value;
        }
    }

    private Node root;
    private int size;

    public BinarySearchTree() {
        root = null;
        size = 0;
    }

    @Override
    public boolean insert(int value) {
        //루트부터 넣을 자리 탐색
        Node node = root;
        Node parent = null;

        //넣을 자리를 찾을 때까지 반복
        while(node != null){
            //넣을 자리를 찾았을 때 node는 null이므로 그 부모 노드를 기록해놓아야 함
            parent = node;
            //넣을 값이 현재 node 값보다 작으면 왼쪽 서브트리로, 크면 오른쪽으로 이동
            if(value < node.value){
                node = node.leftChild;
            }else if (value > node.value){
                node = node.rightChild;
            }else{ // 해당 값이 이미 있으면 추가하지않고 끝
                return false;
            }
        }

        Node newNode = new Node(value);
        //루트가 null인 경우 루트로 추가 그 외에는 값 비교해서 기록해 놓은 부모노드의 자식으로 추가
        if(parent == null){
            root = newNode;
        }else if(value < parent.value){
            parent.leftChild = newNode;
        }else{
            parent.rightChild = newNode;
        }

        size++;
        return true;
    }

  //특정 값이 트리에 존재하는지 확인하는 
    @Override
    public boolean find(int value) {
        Node node = root;
        
        while(node != null){
            if(value < node.value){
                node = node.leftChild;
            }else if (value > node.value){
                node = node.rightChild;
            }else{ // 값을 찾으면 true 리턴
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean delete(int value) {
        Node node = root;
        Node parent = null;

        while(node != null){
            if(value < node.value){
                parent = node;
                node = node.leftChild;
            }else if (value > node.value){
                parent = node;
                node = node.rightChild;
            }else{
                //삭제하려는 값을 찾았음
                deleteNode(node, parent);
                return true;
            }
        }

        //삭제하려는 값이 존재하지 않음
        return false;
    }

    private void deleteNode(Node node, Node parent) {

        //삭제할 노드의 자식이 없음(리프노드) => 부모노드의 자식에서 없애줌
        if(node.leftChild == null && node.rightChild == null){
            //루트를 삭제하는 경우
            if(parent == null){
                root = null;
            }else if(parent.leftChild == node){
                System.out.println("left");
                parent.leftChild = null;
            }else{
                System.out.println("right");
                parent.rightChild = null;
            }

            //삭제할 노드가 한쪽 자식만 가짐 => 그 자식을 삭제할 노드의 부모노드의 자식으로 대체
            //루트면 그 자식이 새로운 루트가 됨
        }else if(node.rightChild == null){
            if(parent == null) {
                root = node.leftChild;
            }else if(parent.leftChild == node){
                parent.leftChild = node.leftChild;
            }else{
                parent.rightChild = node.leftChild;
            }
        }else if(node.leftChild == null){
            if(parent == null){
                root = node.rightChild;
            }else if(parent.leftChild == node){
                parent.leftChild = node.rightChild;
            }else{
                parent.rightChild = node.rightChild;
            }
            //삭제할 노드가 2개의 자식을 가짐
        }else {
            //삭제할 노드의 오른쪽 서브트리에서 가장 작은 값노드를 찾아서 부모노드의 자식 또는 루트로 설정
            Node found = findFromRightSubTree(node);

            if(parent == null){
                root = found;
            }else if(parent.leftChild == node){
                parent.leftChild = found;
            }else{
                parent.rightChild = found;
            }
        }
    }

    public Node findFromRightSubTree(Node node){
        //오른쪽 서브트리에서 제일 작은 값 => 왼쪽으로만 가다가 왼쪽 자식 없으면 해당 노드 리턴
        Node target = node.rightChild;
        Node parentOfTarget = node;
        while(target.leftChild != null){
            parentOfTarget = target;
            target = target.leftChild;
        }

        //찾아낸 target은 왼쪽 자식이 없음 => 삭제할 노드의 왼쪽 자식을 갖다 붙임
        target.leftChild = node.leftChild;
        //target이 삭제할 노드의 오른쪽 자식이었다면 그냥 그대로 삭제할 노드 자리로 올라오면 문제 없음
        //아니라면 target의 기존 오른쪽 자식 노드를 처리한 후 삭제할 노드의 오른쪽 자식을 새로 붙여줘야함
        if(target != node.rightChild){
            if(target.rightChild != null){
                parentOfTarget.leftChild = target.rightChild;
            }else{
                parentOfTarget.leftChild = null;
            }
            target.rightChild = node.rightChild;
        }
        return target;
    }

    public void inOrder(Node node){
        if(node != null) {
            inOrder(node.leftChild);
            System.out.print(node.value+" ");
            inOrder(node.rightChild);
        }
    }

    public void inOrder() {
        inOrder(root);
    }
}
