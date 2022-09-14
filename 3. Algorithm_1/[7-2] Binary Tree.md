# **Binary Tree**

1. 이진트리
    - 모든 노드들이 2개의 서브트리를 갖는 특별한 형태의 트리<br>

    - 각 노드가 자식 노드를 최대한 2개 까지만 가질 수 있는 트리
        - 왼쪽 자식 노드(left child node)<br>

        - 오른쪽 자식 노드(right child node)
    - 이진 트리의 예
        
        ![binary tree example img](./images/binary%20tree%20example.png)
<br><br><br>        
    
2. 특성
    - 레벨 i에서의 노드의 최대 개수는 2^i개<br>

    - 높이가 h인 이진 트리가 가질 수 있는 노드의 최소 개수는 (h+1)개가 되며, 최대 개수는 (2^(h+1)-1)가 된다.
        
        ![binary tree features img](./images/binary%20tree%20features.png)
<br><br><br>         
    
3. 종류
    - **포화 이진 트리(Full Binary Tree)**<br>

        - 모든 레벨에서 노드가 포화상태로 차 있는 이진 트리<br>

        - 높이가 h일 때, 최대의 노드 개수인 (2^(h+1)-1)의 노드를 가진 이진 트리
            - 높이 3일 때 2^(3+1)-1 = 15개의 노드
        - 루트를 1번으로 하여 2^(h+1)-1까지 정해진 위치에 대한 노드 번호를 가짐
            
            ![full binary tree img](./images/binary%20tree%20features.png)
      <br><br>      
    
    - **완전 이진 트리(Complete Binary Tree)**
        - 높이가 h이고 노드 수가 n개일 때(단, h+1 ≤ n ≤ 2^(h+1)-1), 포화 이진 트리의 노드 번호 1번부터 n번까지 빈 자리가 없는 이진 트리<br>

        - 예) 노드가 10개인 완전 이진 트리
            
            ![complete binary tree img](./images/complete%20binary%20tree.png)
       <br><br>      
    
    - **편향 이진 트리(Skewed Binary Tree)**
        - 높이 h에 대한 최소 개수의 노드를 가지면서 한쪽 방향의 자식 노드만을 가진 이진 트리<br>

            - 왼쪽 편향 이진 트리<br>

            - 오른쪽 편향 이진 트리
        
        ![skewed binary tree img](./images/skewed%20binary%20tree.png)
<br><br><br>         
    
4. **순회(traversal)**
    - 트리의 각 노드를 중복되지 않게 전부 방문(visit) 하는 것을 말하는데 트리는 비 선형 구조이기 때문에 선형구조에서와 같이 선호 연결 관계를 알 수 없다.
    - 따라서 특별한 방법이  필요하다.
    - **순회(traversal): 트리의 노드들을 체계적으로 방문하는 것**
    - 3가지 기본적인 순회방법
        - 전위순회(preorder  traversal; VLR)
            - 부모노드 방문 후, 자식노드를 좌, 우 순서로 방문
        - 중위순회(inorder traversal; LVR)
            - 왼쪽 자식노드, 부모노드, 오른쪽 자식노드 순으로 방문
        - 후위순회(postorder traversal; LRV)
            - 자식노드를 좌우 순서로 방문한 후, 부모노드로 방문
        
        ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/24761a60-8c0f-4228-a1fb-7df78b2f5e07/Untitled.png)
<br><br><br>         
    
5. **전위순회(preorder  traversal)**
    - 수행방법
        - 1) 현재 노드 n을 방문하여 처리한다. → V
        - 2) 현재 노드 n의 왼쪽 서브트리로 이동한다. → L
        - 3) 현재 노드 n의 오른쪽 서브트리로 이동한다. → R
    - 전위순회 알고리즘
        
        ```python
        def preorder_traverse(T):  # 전위순회
            if T:    # T is not None
                visit(T)    # print(T.item)
                preorder_traverse(T.left)
                preorder_traverse(T.right)
        ```
        
    - 전위순회 예
        
        ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/21d114fc-faa1-4fb6-af0f-d0ce5e491525/Untitled.png)
<br><br><br>         
    
6. **중위순회(inorder traversal)**
    - 수행 방법
        - 1) 현재 노드 n의 왼쪽 서브트리로 이동한다. → L
        - 2) 현재 노드 n을 방문하여 처리한다. → V
        - 3) 현재 노드 n의 오른쪽 서브트리로 이동한다. → R
    - 중위순회 알고리즘
        
        ```python
        def inorder_traverse(T):    # 중위순회
            if T:                   # T is not None
                inorder_traverse(T.left)
                visit(T)            # print(T.item)
                inorder_traverse(T.right)
        ```
        
    - 중위순회 예
        
        ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/408b0c2f-a5cf-472f-8b14-857a1eb94555/Untitled.png)
<br><br><br>         
    
7. **후위순회(postorder traversal)**
    - 수행 방법
        - 1) 현재 노드 n의 왼쪽 서브트리로 이동한다. → L
        - 2) 현재 노드 n의 오른쪽 서브트리로 이동한다. → R
        - 3) 현재 노드 n을 방문하여 처리한다. → V
    - 후위순회 알고리즘
        
        ```python
        def postorder_traverse(T):        # 후위순위
            if T:                         # T is not None
                postorder_traverse(T.left)
                postorder_traverse(T.right)
                visit(T)                  # print(T.item)
        ```
        
    - 후위순회 예
        
        ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/e46350ed-50c6-40a0-81a1-c6c2e92603a5/Untitled.png)
<br><br><br>         
    
8. 순회 연습 문제
    - 이진 트리의 순회
        - 전위 순회는?
        - 중위 순회는?
        - 후위 순회는?
    
    ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/465eac46-1cfc-4308-8e83-a76e195b246c/Untitled.png)
<br><br><br> 

9. 이진트리의 표현
    - 배열을 이용한 이진 트리의 표현
        - 이진 트리에 각 노드 번호를 다음과 같이 부여
        - 루트의 번호를 1로 함
        - 레벨 n에 있는 노드에 대하여 왼쪽부터 오른쪽으로 2^n부터 2^(n+1)-1까지 번호를 차례로 부여
    
    ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/8c69b487-3373-44ae-8408-c7438bf44622/Untitled.png)
<br><br><br>     

10. 이진트리의 표현 - 배열
    - 노드 번호의 성질
        - 노드 번호가 i인 노드의 부모 노드 번호? → i/2
        - 노드 번호가 i인 노드의 왼쪽 자식 노드 번호? → 2*i
        - 노드 번호가 i인 노드의 오른쪽 자식 노드 번호? → 2*i+1
        - 레벨 n의 노드 번호 시작 번호는? → 2^n
    
    ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/b2ffb20f-a970-447d-b2fc-ffbeb7dfb289/Untitled.png)
    
    ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/40674e9a-3a59-4054-b774-a774393425af/Untitled.png)
    
    - 노드 번호를 배열의 인덱스로 사용
    - 높이가 h인 이진 트리를 위한 배열의 크기는?
        - 레벨 i의 최대 노드 수는? → 2^i
        - 따라서 1+2+4+8+…+2^i = 2^(h+1)-1
    
    ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/8c69b487-3373-44ae-8408-c7438bf44622/Untitled.png)
    
    ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/e31fe4d4-3092-4812-9d45-4c2087484c52/Untitled.png)
    
    ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/6156e50b-bdc9-4603-9d54-3a3f6461f571/Untitled.png)
    
    ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/923b739e-20b8-425e-95f3-b6e877679fe5/Untitled.png)
<br><br><br> 

11. [참고] 이진 트리의 저장
    - 부모 번호를 인덱스로 자식 번호를 저장
        
        ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/0a8cde8c-c5a1-4c74-83b7-1805a3ba43a4/Untitled.png)
        
    - 자식 번호를 인덱스로 부모 번호를 저장
        
        ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/b493c2d0-388c-4ad9-a4ce-e5ea61309691/Untitled.png)
        
    - 루트 찾기, 조상 찾기
        
        ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/54f05796-d714-40f3-a9f5-188a238ed266/Untitled.png)
<br><br><br>         
    
12. 배열을 이용한 이진 트리 표현의 단점
    - 편향 이진 트리의 경우에 사용하지 않는 배열 원소에 대한 메모리 공간 낭비 발생
    - 트리의 중간에 새로운 노드를 삽입하거나 기존의 노드를 삭제할 경우 배열의 크기 변경 어려워 비효울적
<br><br><br> 

13. 트리의 표현 - 연결리스트
    - 배열을 이용한 이진 트리 표현읜 단점을 보완하기 위해 연결리스트를 이용하여 트리를 표현할 수 있다.
    - 연결 자료구조를 이용한 이진트리의 표현
        - 이진 트리의 모든 노드는 최대 2개의 자식 노드를 가지므로 일정한 구조의 단순 연결 리스트 노트를 사용하여 구현
            
            ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/cf52cafd-7524-440d-8999-96e364e8075e/Untitled.png)
            
    - 완전 이진 트리의 연결 리스트 표현
        
        ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/4f0a83e1-d389-4a1d-80fe-7bf3e60463d5/Untitled.png)
<br><br><br>         
    
14. 연습문제
    - 첫 줄에는 트리의 정점의 총 수 V가 주어진다. 그 다음 줄에는 V-1개 간선이 나열된다. 간선은 그것을 이루는 두 정점으로 표기된다. 간선은 항상 “부모 자식 순서로 표기된다. 아래 예에서 두 번째 줄 처음 1과 2는 정점 1과 2를 잇는 간선을 의미하며 1이 부모, 2가 자식을 의미한다. 간선은 부모 정점 번호가 작은 것부터 나열되고, 부모 정점이 동일하다면 자식 정점 번호가 작은 것부터 나열된다.
    - 다음 이진 트리 표현에 대하여 전위 순회하여 정점의 번호를 출력하시오.
        - 13 ← 정점의 개수
        - 1 2 1 3 2 4 3 5 3 6 4 7 5 8 5 9 6 10 6 11 7 12 11 13
<br><br><br> 

15. **수식 트리**
    - 수식을 표현하는 이진 트리
    - 수식 이진 트리(Expression Binary Tree)라고 부르기도 함.
    - 연산자는 루트 노드이거나 가지 노드
    - 피연산자는 모두 잎 노드
        
        ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/2b122b80-1487-4e02-805c-2c11113428d8/Untitled.png)
<br><br><br>         
    
16. 수식 트리의 순회
    - 중위 순회: A / B * C * D + E (식의 중위 표기법)
    - 후위 순회: A B / C * D * E + (식의 후위 표기법)
    - 전위 순회: + * * / A B C D E (식의 전위 표기법)
    
    ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/b27fc35d-5527-45f7-adbf-ff3774a07d4b/Untitled.png)
<br><br><br>     

---
