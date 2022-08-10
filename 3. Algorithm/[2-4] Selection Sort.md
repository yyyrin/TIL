# 선택 정렬 (Selection Sort)


1. 인덱스
    - Database에서 유래
    - 테이블에 대한 동작 속도를 높여주는 자료 구조를 일컫는다.
    - Database 분야가 아닌 곳에서는 Look up table 등의 용어를 사용
    - 인덱스를 저장하는데 필요한 디스크 공간은 보통 테이블을 저장하는데 필요한 디스크 공간보다 작다.
        - 보통 인덱스는 키-필드만 갖고 있고, 테이블의 다른 세부 항목들은 갖고 있지 않기 때문
    - 배열을 사용한 인덱스
        - 대량의 데이터를 매번 정렬하면, 프로그램의 반응은 느려질 수 밖에 없다.
        - 이러한 대량 데이터의 성능 저하 문제를 해결하기 위해 배열 인덱스 사용!
    - 예시
        - 원본 데이터 배열과 별개로, 배열 인덱스를 추가한 예
        - 원본 데이터에 데이터가 삽입될 경우 상대적으로 크기가 작은 인덱스 배열을 정렬하기 때문에 속도가 빠르다.
        
        ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/93691437-1681-4756-8ef1-19f19464bad4/Untitled.png)
<br><br>        
    
2. **선택 정렬(Selection Sort)**
    - 주어진 자료들 중 가장 작은 값의 원소부터 차례대로 선택하여 위치를 교환하는 방식
    - 정렬 과정
        - 주어진 리스트 중에 최소값을 찾는다.
        - 그 값을 리스트의 맨 앞에 위치한 값과 교환한다.
        - 맨 처음 위치를 제외한 나머지 리스트를 대상으로 위의 과정을 반복한다.
    - 시간 복잡도
        - O(n^2)
    - 정렬 과정 예시
        - 주어진 리스트
<br><br>

3. 선택 정렬 과정 예시
    - 주어진 리스트에서 최소값을 찾는다.
        
        ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/4a9ca652-8c29-40a9-b7a1-10c9dfce553b/Untitled.png)
        
    - 리스트의 맨 앞에 위치한 값과 교환한다.
        
        ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/2eb8ae18-7340-40ed-afe7-4a8962df8cc5/Untitled.png)
        
    - 미정렬 리스트에서 최소값을 찾는다.
        
        ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/5b522563-9c90-45f6-9785-8065bac461c2/Untitled.png)
        
    - 리스트의 맨 앞에 위치한 값과 교환한다.
        
        ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/93fef535-be25-4ea2-88f5-264b95813b7c/Untitled.png)
        
    - 미정렬 리스트에서 최소값을 찾는다.
        
        ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/2753bd77-00db-4921-90ce-406b9c151539/Untitled.png)
        
    - 리스트의 맨 앞에 위치한 값과 교환한다.
        
        ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/c035c549-38ff-4b1c-85c5-141bb3c5a588/Untitled.png)
        
    - 미정렬 리스트에서 최소값을 찾는다.
        
        ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/a4d3675b-b6cb-49b6-a1a0-6169a9056b96/Untitled.png)
        
    - 리스트의 맨 앞에 위치한 값과 교환한다.
        
        ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/9355bada-cfd5-498b-b7a8-6d1bce93b01e/Untitled.png)
        
    - 미정렬 원소가 하나 남은 상황에서는 마지막 원소가  가장 큰 값을 갖게 되므로, 실행을 종료하고 선택 정렬이 완료된다.
<br><br>

4. 선택 정렬 알고리즘
    - 알고리즘
        
        ```python
        def SelectionSort(a[], n)
            for i from 0 to n-2:
                a[i], ..., a[n-1] 원소 중 최소값 a[k] 찾음
                a[i]와 a[k] 교환
        ```
        
    - 선택 정렬
        
        ```python
        def selectionSort(a, N):
            for i in range(N-1):
                minIdx = i
                for j in range(i+1, N):
                    if a[minIdx] > a[j]:
                        minIdx = j
                a[i], a[minIdx] = a[minIdx], a[i]
        ```
<br>        

---
