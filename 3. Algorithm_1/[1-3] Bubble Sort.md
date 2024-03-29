# 버블 정렬 (Bubble Sort)

1. 정렬
    - 2개 이상의 자료를 특정 기준에 의해 작은 값부터 큰 값(오름차순 : ascending), 혹은 그 반대의 순서대로(내림차순 : descending) 재배열하는 것<br>

    - 키 : 자료를 정렬하는 기준이 되는 특정 값
    - 대표적인 정렬 방식의 종류
        - 버블 정렬 (Bubble Sort)
        - 카운티 정렬 (Counting Sort)
        - 선택 정렬 (Selection Sort)
        - 퀵 정렬 (Quick Sort)
        - 삽입 정렬 (Insertion Sort)
        - 병합 정렬 (Merge Sort)
<br><br><br>

2. 버블 정렬 (Bubble Sort)
    - 인접한 두 개의 원소를 비교하며 자리를 계속 교환하는 방식<br>

    - 정렬 과정
        - 첫 번째 원소부터 인접한 원소까지 계속 자리를 교환하면서 맨 마지막 자리까지 이동한다.<br>

        - 한 단계가 끝나면 가장 큰 원소가 마지막 자리로 정렬된다.
        - 교환하며 자리를 이동하는 모습이 물 위에 올라오는 거품 모양과 같다고 하여 버블 정렬이라고 한다.
    - 시간 복잡도 : $O(n^2)$
<br><br><br>

3. 버블 정렬 과정
    - [55, 7, 78, 12, 42]를 버블 정렬하는 과정 (오름차순)
        - 첫 번째 패스
        
            | 55 | 7 | 78 | 12 | 42 |
            | --- | --- | --- | --- | --- |
            | 7 | 55 | 78 | 12 | 42 |
            | 7 | 55 | 78 | 12 | 42 |
            | 7 | 55 | 12 | 78 | 42 |
            | 7 | 55 | 12 | 42 | 78 |
        - 두 번째 패스
        
            | 7 | 55 | 12 | 42 | 78 |
            | --- | --- | --- | --- | --- |
            | 7 | 55 | 12 | 42 | 78 |
            | 7 | 12 | 55 | 42 | 78 |
            | 7 | 12 | 42 | 55 | 78 |
        - 세 번째 패스
        
            | 7 | 12 | 42 | 55 | 78 |
            | --- | --- | --- | --- | --- |
            | 7 | 12 | 42 | 55 | 78 |
            | 7 | 12 | 42 | 55 | 78 |
        - 네 번째 패스
        
            | 7 | 12 | 42 | 55 | 78 |
            | --- | --- | --- | --- | --- |
            | 7 | 12 | 42 | 55 | 78 |
        - 정렬 끝
        
            | 7 | 12 | 42 | 55 | 78 |
            | --- | --- | --- | --- | --- |
    - 위의 정렬 과정을 코드로 구현
        
        ```python
        BubbleSort(a, N)                  # 정렬할 배열과 배열의 크기
            for i : N-1  -> 1             # 정렬될 구간의 끝
                for j : 0 -> i-1          # 비교할 원소 중 왼쪽 원소의 인덱스
                    if a[j] > a[j+1]      # 왼쪽 원소가 더 크면
                        a[j] <-> a[j+1]   # 오른쪽 원소와 교환
        ```
        
        ```python
        def BubbleSort(a, N):             # 정렬할 List, N 원소 수
            for i in range(N-1, 0 , -1):  # 범위의 끝 위치
                for j in range(0, 1):
                    if a [j] > a[j+1]:
                        a[j], a[j+1] = a[j+1], a[j]
        ```
<br><br>        

---
