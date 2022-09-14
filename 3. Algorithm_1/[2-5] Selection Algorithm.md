# 선택 알고리즘 (Selection Algorithm)

1. **셀렉션 알고리즘(Selection Algorithm)**
    - 저장되어 있는 자료로부터 k번째로 큰 혹은 작은 원소를 찾는 방법
        - 최소값, 최대값 혹은 중간값을 찾는 알고리즘을 의미하기도 한다.
    - 선택 과정
        - 정렬 알고리즘을 이용하여 자료 정렬
        - 원하는 순서에 있는 원소 가져오기
<br><br>

2. 일반적인 셀렉션 알고리즘
    - k번째로 작은 원소를 찾는 알고리즘
        - 1번부터 k번째까지 작은 원소들을 찾아 배열의 앞쪽으로 이동시키고, 배열의 k번째를 반환한다.
        - k가 비교적 작을 때 유용하며 O(kn)의 수행시간을 필요로 한다.
        
        ```python
        def select(arr, k):
            for i in range(0, k):
                minIndex = i
                for j in range(i+1, len(arr)):
                    if arr[minIndex] > arr[j]:
                        minIndex = j
                arr[i], arr[minIndex] = arr[minIndex], arr[i]
            return arr[k-1]
        ```
<br>        

---
