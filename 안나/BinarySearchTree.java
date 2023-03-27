package BST;
// 참고 블로그 https://yoongrammer.tistory.com/71
public class BinarySearchTree implements IBinarySearchTree {
	class Node {
		int key;
		Node left;
		Node right;
		
		Node(int key){
			this.key = key;
		}
	}
	
	Node root;
	/*
	 @ 삽입 연산
	 1. 루트에서 시작
	 2. 삽입할 값과 루트와 비교
	 3. 루트보다 값이 작으면 왼쪽 서브트리에 대해 재귀
		루트보다 값이 크면 오른쪽 서브 트리에 대해 재귀
	 4. 자식이 한개이거나 없는 노드에 도달한 후 노드보다 크다면 오른쪽 자식노드로,  작다면 왼쪽 자식노드로 삽입
	 */

	@Override
	//내가 작성한 코드
	public void insertNode(int key) {
		insertNode(root, key);
	}
	private void insertNode(Node parentNode, int key) {
		//루트가 비어있는지 판별
		if(root == null) {
			root = new Node(key);
			return;
		}
		
		//4번 자식이 한개이거나 없는 노드에 도달한 후 노드보다 크다면 오른쪽 자식노드로,  작다면 왼쪽 자식노드로 삽입
		if(parentNode.left == null && parentNode.key > key) {
			parentNode.left = new Node(key);
			return;
		}else if(parentNode.right == null && parentNode.key < key) {
			parentNode.right = new Node(key);
			return;
		}
		
		//3번 루트보다 값이 작으면 왼쪽 서브트리에 대해 재귀, 루트보다 값이 크면 오른쪽 서브 트리에 대해 재귀
		if(parentNode.key > key) {
			insertNode(parentNode.left, key);
		}else if(parentNode.key < key) {
			insertNode(parentNode.right, key);
		}
	}
	//모범답안
	public Node insertNode_answer(Node parentNode, int key) {
		//루트가 비어있는지 판별
		if(parentNode == null) {
			return  new Node(key);
		}

		//3번 루트보다 값이 작으면 왼쪽 서브트리에 대해 재귀, 루트보다 값이 크면 오른쪽 서브 트리에 대해 재귀
		if(parentNode.key > key) {
			parentNode.left = insertNode_answer(parentNode.left, key);
		}else if(parentNode.key < key) {
			parentNode.right = insertNode_answer(parentNode.right, key);
		}
		return parentNode;
	}
	/*
	@ 삭제연산
	 0. 삭제할 노드를 찾는다.
	 1. 삭제할 노드가 단말노드라면 부모의 해당 노드 끊기
	 2. 삭제할 노드가 자식이 하나라면 자식노드를 부모노드와 연결
	 3. 삭제할 노드에 자식이 둘 있는 경우
	 	3-1. 오른쪽 서브트리에서 최소값을 찾고 그 노드와 삭제할 노드를 바꾼다.(오른쪽 서브트리에서 계속 왼쪽으로 내려가면 된다.)
	 	3-2. 노드를 삭제한다.
	 	+) 이진트리를 삭제하는 것처럼 단말노드 루트와 바꾸고 삭제하는 것과 비슷하지만
	 		오른쪽 서브트리에서 찾는 이유는 루트와 바꿨을 때 루트로 올라온 노드가 
	 		왼쪽 자식노드보다 커야하기 때문이다.
	*/
	@Override
	public void deleteNode(int key) {
		Node node = deleteNode(root, key);
	}
	private Node deleteNode(Node parentNode, int key) {
		if(parentNode == null) { //key와 같은 노드가 없어서 삭제를 못하는 경우
			return null;
		}else if(parentNode.key > key) {
			//삭제할 노드를 찾아서 1번경우라면 null 2번경우라면 하나남은 자식노드의 주소, 3번 경우라면 삭제되고 변경된 노드의 주소를 반환
			parentNode.left = deleteNode(parentNode.left, key);
		}else if(parentNode.key < key) {
			parentNode.right = deleteNode(parentNode.right, key);
		}else if(parentNode.key == key) {
			if(parentNode.left == null && parentNode.right == null) { //자식노드가 없는 경우
				return null;
			}else if(parentNode.left == null) { //오른쪽 자식 노드만 있는 경우 
				return parentNode.right;
			}else if(parentNode.right == null) { //왼쪽 자식 노드만 있는 경우
				return parentNode.left;
			}
			
			//자식이 둘다 있는 경우
			Node minTemp = finMinNode(parentNode.right);
			parentNode.key = minTemp.key;
			parentNode.right = deleteNode(parentNode.right, minTemp.key);
		}
		return parentNode;
	}
	
	private Node finMinNode(Node node) {
		if(node.left == null && node.right == null) {
			return node;
		}
		return finMinNode(node.left);
	}
	/*
	 @ 검색 연산
	 1. 루트에서 시작
	 2. 검색할 값과 루트와 비교
	 3. 루트보다 값이 작으면 왼쪽 서브 트리에 대해 재귀
		루트보다 값이 크면 오른쪽 서브 트리에 대해 재귀
	 4. 값이 나올때 까지 반복 단말 노드까지 찾았지만 값이 없으면 null
	 */
	@Override
	public void searchNode(int key) {
		searchNode(root, key);
	}
	private Node searchNode(Node parentNode, int key){
		Node node = null;;
		if (parentNode == null) {
			return null;
		}else if (parentNode.key > key){
			 node = searchNode(parentNode.left, key);
		}else if (parentNode.key < key){
			 node = searchNode(parentNode.right, key);
		}else if (parentNode.key == key) {
			node = parentNode;
		}
		return node;
	}
}
/*
 이진 탐색 트리 = 이진탐색 + 연결리스트
 이진 탐색의 탐색의 계산복잡도는 O(logN)으로 빠르지만 자료 입력, 삭제가 불가하고
 연결리스트의 경우 입력, 삭제에 대한 계산 복잡도는 O(1)이나 탐색의 계산복잡도는 O(n)dlek.
 두개를 결합시켜 효율적인 자료구조를 만들어 낸 것이 이진 탐색트리이다.
 
 이진 탐색트리의 속성
 1. 노드의 왼쪽 자식 서브 트리는 노드의 키보다 작은 키가 있는 노드만 포함된다.
 2. 노드의 오른쪽 자식 서브 트리에는 노드의 키보다 큰 키가 있는 노드만 포함된다.
 3. 왼쪽 및 오른쪽 자식 서브 트리도 각각 이진 탐색 트리이다.
 4. 중복된 키를 허용하지 않는다.
 
 균형 상태이면 검색연산의 시간복잡도는 O(logN) 불균형 상태이면 최대 O(N)
 
 
*/