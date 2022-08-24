# 원형 큐(Circular Queue)
1. **초기 공백 상태**
    - front = rear = 0
<br><br><br>

2. **Index의 순환**
    - front와 rear의 위치가 배열의 마지막 인덱스인 n-1을 가리킨 후, 그 다음에는 논리적 순환을 이루어 배열의 처음 인덱스인 0으로 이동해야 함<br>

    - 이를 위해서 나머지 연산자 mod를 사용함
<br><br><br>

3. **front 변수**
    - 공백 상태와 포화 상태 구분을 쉽게 하기 위해 front가 있는 자리는 사용하지 않고 항상 빈자리로 둠
<br><br><br>

4. **삽입 위치 및 삭제 위치**
    
    
    |  | 삽입 위치 | 삭제 위치 |
    | --- | --- | --- |
    | 선형 큐 | rear = rear + 1 | front = front + 1 |
    | 원형 큐 | rear = (rear + 1) mod n | front = (front + 1) mod n |
<br><br>

5. **원형 큐의 연산 과정**
    
    ![circular queue1 img](./images/circular%20queue1.png)
    
    ![circular queue2 img](./images/circular%20queue2.png)
    
    ![circular queue3 img](./images/circular%20queue3.png)
<br><br><br>

6. **초기 공백 큐 생성**
    - 크기 n인 1차원 배열 생성<br>

    - front 와 rear을 0으로 초기화
    
    ```python
    Q = [0] * n
    front = rear = 0
    ```
<br><br>

7. **공백상태 및 포화상태 검사: `isEmpty()`, `isFull()`**
    - 공백상태: front == rear<br>

    - 포화상태: 삽입할 rear의 다음 위치 == 현재 front
        - `(rear + 1) mod n == front`<br><br>
    
    ```python
    def isEmpty():
        return front == rear
    
    def isFull():
        return (rear+1) % len(cQ) == front
    ```
<br><br>

8. **삽입: `enQueue(item)`**
    - 마지막 원소 뒤에 새로운 원소를 삽입하기 위해<br>

        - rear 값을 조정하여 새로운 원소를 삽입할 자리를 마련함<br>

            - `rear ← (rear+1) mod n;`
        - 그 인덱스에 해당하는 배열원소 cQ[rear]에 item을 저장
    ```python
    def enQueue(item):
        global rear
        if isFull():  # 디버깅용
            print("Queue_Full")
        else:
            rear = (rear+1) % len(Q)
            cQ[rear] = item
    ```
<br><br>

9. **삭제: `deQueue()`, `delete()`**
    - 가장 앞에 있는 원소를 삭제하기 위해<br>

        - front 값을 조정하여 삭제할 자리를 준비함<br>

        - 새로운 front 원소를 리턴 함으로써 삭제와 동일한 기능함
    
    ```python
    def deQueue():
        global front
        if isEmpty():  # 디버깅용
            print("Queue_Empty")
        else:
            front = (front + 1) % len(cQ)
            return cQ[front]
    
    def isEmpty():
        return front == rear
    
    def isFull:
         return (rear+1) % len(cQ) == front
    ```
<br><br>    

---
