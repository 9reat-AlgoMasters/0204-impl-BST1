# 🔖 이진 탐색 트리 (BinarySearchTree)
## 🔍 이진 탐색 트리 등장 배경
 "**이진 탐색**" :  <U>탐색의 시간복잡도</U>는 O(logN)으로 빠름, <U>삽입, 삭제</U>가 불가능  
 "**연결리스트**" : <U>탐색의 시간복잡도</U>는 O(n) 비교적 느림, <U> 삽입, 삭제</U> 가능  계산 복잡도는 O(1)   
 이 둘의 장점을 결합시켜 탐색의 효율을 높이고 자료의 삽입, 삭제를 가능한 자료구조를 만들어 낸 것이 이진 탐색트리이다.

<br/>

## 🔍 이진 탐색 트리의 속성
 1. 노드의 왼쪽 자식 서브 트리는 노드의 키보다 작은 키가 있는 노드만 포함된다.
 2. 노드의 오른쪽 자식 서브 트리에는 노드의 키보다 큰 키가 있는 노드만 포함된다.
 3. 왼쪽 및 오른쪽 자식 서브 트리도 각각 이진 탐색 트리이다.
 4. 중복된 키를 허용하지 않는다.
 5. 순회할 때에는 중위 순회방법을 사용하여 정렬된 데이터를 만든다.  
   
 ![image](https://t1.daumcdn.net/cfile/tistory/99ACAF335989659B19)

<br/>

## 🔍 삽입 연산
루트와 값이 같을 때 까지  
->  삽입할 노드의 값이 루트보다 작다면 왼쪽 서브트리에 대해 탐색  
->  삽입할 노드의 값이 루트보다 크다면 오른쪽 서브트리에 대해 탐색

<br/>

## 🔍 삭제 연산
1. 삭제할 노드의 자식이 없을 때  
    - 적어주세요
  
2. 삭제할 노드의 자식이 한 개가 있을 때  
    - 적어주세요  
3. 삭제할 노드의 자식이 두 개가 있을 때 
    - 적어주세요
    
<br/>

## 🔍 재귀를 사용한 BST 
1. 설명  
  ```java
  
  //삭제연산
  private Node deleteNode(Node root, int key) {
	  	if(root == null) { //key와 같은 노드가 없어서 삭제를 못하는 경우
	  		return null;

	     //삭제할 노드의 위치 찾기(재귀를 이용)  
	  	if(root.key > key) { 
	  		root.left = deleteNode(root.left, key);
	  		 //삭제한 노드가 부모의 왼쪽 혹은 오른쪽 노드의 값을 재귀의 반환된 값으로 바꿔준다.
	    
	  	}else if(root.key < key) {
	  		root.right = deleteNode(root.right, key);
	            //삭제한 노드가 부모의 왼쪽 혹은 오른쪽 노드의 값을 재귀의 반환된 값으로 바꿔준다.
	    
	  	}else if(root.key == key) {
	  		//삭제할 노드를 찾았다면 
	  		if(root.left == null && root.right == null) { 
	  			//삭제할 노드에 자식이 없다면 null을 반환
	  			return null;
	      
	  		}else if(root.left == null) { 
	  			//삭제할 노드에 오른쪽 자식 노드만 있는 경우 오른쪽 자식노드의 주소를 반환 
	  			return root.right;
	      
	  		}else if(root.right == null) {
	  			//삭제할 노드에 왼쪽 자식 노드만 있는 경우 왼쪽 자식노드의 주소를 반환 
	  			return root.left;
	  		}

	  		//자식을 둘 다 가지고 있다면
	  		Node minTemp = finMinNode(root.right); //findMinNode() =>서브트리에 가장 값이 작은 노드를 찾아서 반환하는 함수
	  		//삭제할 노드의 오른쪽 트리에서 가장 작은 값을 찾아서
	  		root.key = minTemp.key;
	  		//루트의 값을 가장 작은 값으로 바꾸고
	  		Node node = deleteNode(root.right, minTemp.key);
	  		//가장 작은 값을 가지는 원래 노드를 삭제
	    
	  	}
	  	return root;
	  }
  
  ```
2. 장점  
    - 적어주세요
3. 단점  
   - 적어주세요
   - 
<br/>

## 🔍 BST의 문제점
- 균형 상태이면 검색연산의 시간복잡도는 O(logN) 불균형 상태이면 최대 O(N)이여서 O(logN)의 시간복잡도를 보장 할 수 없다.  
- 적어주세요  
- 적어주세요  
<br/>

이러한 BST의 문제점은 AVL과 RedBlackTree를 사용하여 해결 할 수 있다.

