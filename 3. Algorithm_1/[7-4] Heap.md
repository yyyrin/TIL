# Heap

## **4. 힙**

- 완전 이진 트리에 있는 노드 중에서 키값이 가장 큰 노드나 키값이 가장 작은 노드를 찾기 위해서 만든 자료구조

1. 최대 힙(max heap)
    - 키값이 가장 큰 노드를 찾기 위한 **완전 이진 트리**
    - (부모노드의 키값 > 자식노드의 키값)
    - 루트 노드: 키값이 가장 큰 노드
    
2. 최소 힙(min heap)
    - 키값이 가장 작은 노드를 찾기 위한 **완전 이진 트리**
    - (부모노드의 키값 < 자식노드의 키값)
    - 루트 노드: 키값이 가장 작은 노드
    
3. 예시
    
    ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/f461f5da-8b14-4899-8275-592f1f34c556/Untitled.png)
    
    ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/e2a11b8d-1645-4615-a555-5da2d3932353/Untitled.png)
    
    - 트리1 → 완전이진트리X, 키 중복
    - 트리2 → 크기 조건 만족X
    
4. 힙 연산 - 삽입
    - 최대힙의 경우
        - 1) 완전이진트리 유지
        - 2) 부모  키값> 자식 키값 → 조건을 만족하지 않으면 조건을 만족할 때까지 자리바꿈
        
        ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/fe2765e3-4b31-4087-8325-c7cc33044db0/Untitled.png)
        
        ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/c3369f9a-e159-4ed5-bfb5-349ea7408697/Untitled.png)
        
        ```python
        # 최대힙 삽입
        
        def enq(n):
            global last
            last += 1       # 마지막 정점 추가
            heap[last] = n  # 마지막 정점에 key 추가
        
            c = last        # 자식 정점 번호
            p = c // 2      # 완전이진트리에서 부모 정점 번호
            while p and heap[p] < heap[c]:  # 부모가 있고, 부모 < 자식 인경우 자리 교환
                heap[p], heap[c] = heap[c], heap[p]
                c = p
                p = c//2
        
        heap = [0] * 100
        last = 0
        
        enq(2)
        enq(5)
        enq(7)
        enq(3)
        enq(4)
        enq(6)
        print(heap)
        ```
        
5. 힙 연산 - 삭제
    - 힙에서는 루트 노드의 원소만을 삭제할 수 있다.
    - 루트 노드의 원소를 삭제하여 반환한다.
    - 힙의 종류에 따라 최대값 또는 최소값을 구할 수 있다.
        - 완전이진트리 유지
    - 최대합 예시
        
        ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/a5b394c7-5d69-490a-8f1c-2decf5cafc15/Untitled.png)
        
        ```python
        # 최대합 삭제
        
        def deq():
            global last
            tmp = heap[1]          # 루트 백업
            heap[1] = heap[last]   # 삭제할 노드의 키를 루트에 복사
            last -= 1              # 마지막 노드 삭제
            p = 1                  # 루트에 옮긴 값을 자식과 비교
            c = p * 2              # 왼쪽 자식
            while c <= last:       # 자식이 하나라도 있으면
                if c+1 <= last and heap[c] < heap[c+1]: # 오른쪽 자식도 있고, 오른쪽 자식이 더 크면
                    c += 1         # 비교 대상을 오른쪽 자식으로 정함
                if heap[p] < heap[c]:  # 자식이 더 크면 최대값 규칙에 어긋나므로
                    heap[p], heap[c] = heap[c], heap[p]
                    p = c          # 자식을 새로운 부모로
                    c = p * 2      # 왼쪽 자식 번호를 계산
                else:              # 부모가 더 크면
                    break          # 비교 중단
            return tmp
        
        heap = [0] * 100
        last = 0
        
        enq(2)
        enq(5)
        enq(7)
        enq(3)
        enq(4)
        enq(6)
        while last:
            print(deq())
        ```
        
6. 힙을 이용한 우선순위 큐
    - 힙(Heap)
        - 완전 이진 트리로 구현된 자료구조로서, 키값이 가장 큰 노드나 가장 작은 노드를 찾기에 용한 자료구조
        - 아래의 예는 최소 힙(Min heap)으로서, 가장 작은 키값을 가진 노드가 항상 루트에 위치한다.
            
            ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/aed50cce-8829-49d2-a991-f12a902e2d57/Untitled.png)
            
        - 힙의 키를 우선순위로 활용하여 우선순위 큐를 구현할 수 있다.

---
