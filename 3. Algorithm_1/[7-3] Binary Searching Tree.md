# **이진탐색 트리**

1. 이진 탐색 트리
    - 탐색작업을 효율적으로 하기 위한 자료구조
    - 모든 원소는 서로 다른 유일한 키를 갖는다.
    - key(왼쪽 서브트리) < key(루트 노드) < key(오른쪽 서브트리)
    - 왼쪽 서브트리와 오른쪽 서브트리도 이진 탐색 트리다.
    - 중위 순회하면 오름차순으로 정렬된 값을 얻을 수 있다.
    
    ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/8bb0356b-bc9a-480c-8f5b-b900eac9e10b/Untitled.png)
    
    ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/9c7aa327-dfce-4ef4-9c8d-1a93767cc76c/Untitled.png)
    
2. **탐색연산**
    - 루트에서 시작
    - 탐색할 키 값 x를 루트 노드의 키 값과 비교
        - (키 값x = 루트노드의 키 값)인 경우: 원하는 원소를 찾았으므로 탐색연산 성공
        - (키 값x < 루트노드의 키 값)인 경우: 루트노드의 왼쪽 서브트리에 대해서 탐색연산 수행
        - (키 값x > 루트노드의 키 값)인 경우: 루트노드의 오른쪽  서브트리에 대해서 탐색연산 수행
    - 서브트리에 대해서 순환적으로 탐색 연산을 반복
    - 13 탐색
        
        ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/09fae7de-0376-4d3a-9403-c2eee78491b9/Untitled.png)
        
    
3. 삽입 연산
    - 1) 먼저 탐색 연산을 수행
        - 삽입할 원소와 같은 원소가 트리에 있으면 삽입할 수 없으므로, 같은 원소가 트리에 있는지 탐색하여 확인
        - 탐색에서 탐색 실패가 결정되는 위치가 삽입 위치가 된다.
    - 2) 탐색 실패한 위치에 원소를 삽입
    - 예시) 5 삽입
        
        ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/979b437d-d319-4f59-8aa5-5f9136242883/Untitled.png)
        
4. 이진 탐색 트리 - 성능
    - **탐색(searching), 삽입(insertion), 삭제(deletion) 시간은 트리의 높이 만큼 시간이 걸린다.**
        - O(h), h: BST의 깊이(height)
    - 평균의 경우
        - 이진 트리가 균형적으로 생성되어 있는 경우
        - O(log n)
    - 최악의 경우
        - 한쪽으로 치우친 경사 이진트리의 경우
        - O(n)
        - 순차탐색과 시간복잡도가 같다.
    
5. 검색 알고리즘의 비교
    - 배열에서 순차 검색: O(n)
    - 정렬된 배열에서의 순차 검색: O(n)
    - 정렬된 배열에서의 이진탐색: O(logN)
        - 고정 배열 크기와 삽입, 삭제 시 추가 연산 필요
    - 이진 탐색트리에서의 평균: O(logN)
        - 최악의 경우: O(N)
        - 완전 이진 트리 또는 균형트리로 바꿀 수 있다면 최악의 경우를 없앨 수 있다.
            - 새로운 원소를 삽입할 때 삽입 시간을 줄인다.
            - 평균과 최악의 시간이 같다. O(logN)
    - 해쉬 검색: O(1)
        - 추가 저장 공간 필요
    
6. 삭제 연산
    - 삭제연산에 대해 알고리즘을 생각해 봅시다.
    - 다음 트리에 대하여 13, 12, 9를 차례로 삭제해 보자.
        
        ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/266fa02e-d40b-497a-bf81-38d71d5514c6/Untitled.png)
        

---
