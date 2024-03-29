# Queue

1. **큐(Queue)의 특성**
    - 삽입과 삭제의 위치가 제한적인 구조<br>

        - 큐의 뒤에서는 삽입만 하고, 큐의 앞에서는 삭제만 이루어지는 구조<br><br>

    - **선입선출구조(FIFO: First In First Out)**
    <br>
        - 큐에 삽입한 순서대로 원소가 저장되어, 가장 먼저 삽입(First In)된 원소는 가장 먼저 삭제(First Out)된다.
<br><br><br>

2. **선입선출 구조**
    
    ![FIFO img](./images/fifo.png)
<br><br><br>

3. **기본 연산**
    - 삽입: `enQueue`
    - 삭제: `deQueue`
<br><br><br>

4. **주요 연산**
    
    
    | 연산 | 기능 |
    | --- | --- |
    | enQueue(item) | 큐의 뒤쪽(rear 다음)에 원소를 삽입하는 연산 |
    | deQueue() | 큐의 앞쪽(front)에서 원소를 삭제하고 반환하는 연산 |
    | createQueue() | 공백 상태의 큐를 생성하는 연산 |
    | isEmpty() | 큐가 공백상태인지를 확인하는 연산 |
    | isFull() | 큐가 포화상태인지를 확인하는 연산 |
    | Qpeek() | 큐의 앞쪽(front)에서 원소를 삭제 없이 반환하는 연산 |
<br><br>

5. **연산 과정**
    - 공백 큐 생성: `createQueue();`
        - Q = []
        - Q = [0] * 1000  → 크기가 정해진 방식으로 구현하는 게 좋다.<br><br>
        
        ![createQueue img](./images/createQueue.png)
    <br><br>    
    
    - 원소 A 삽입: `enQueue(A);`
        - rear += 1
        - Q[rear] = ‘A’<br><br>
        
        ![enQueue(A) img](./images/enQueue.png)
    <br><br>    
    
    - 원소 B 삽입: `enQueue(B);`
        
        ![enQueue(B) img](./images/enQueue2.png)
    <br><br>    
    
    - 원소 반환/삭제: `deQueue();`
        - front: 마지막으로 꺼낸 자리
        - rear: 마지막 저장 위치<br><br>
        
        ![deQueue() img](./images/deQueue1.png)
    <br><br>    
    
    - 원소 C 삽입: `enQueue(C);`
        
        ![enQueue(C) img](./images/enQueue3.png)
    <br><br>    
    
    - 원소 반환/삭제: `deQueue();`
        
        ![deQueue() img](./images/deQueue2.png)
    <br><br>    
    
    - 원소 반환/삭제: `deQueue();`
        
        ![deQueue() img](./images/deQueue3.png)
<br><br><br> 

6. **실습**
    
    ```python
    N = 3
    q = [0]* N
    front = -1
    rear = -1
    
    # 1) enQueue
    rear += 1      # enQueue(10)
    q[rear] = 10
    
    rear += 1      # enQueue(20)
    q[rear] = 20
    
    rear += 1      # enQueue(30)
    q[rear] = 30
    
    # 2) deQueue
    front += 1      # deQueue()     
    print(q[front]) # 10
    
    # -------------
    ## 순환 큐
    
    N = 3
    q = [0]* N
    front = 0
    rear = 0
    
    # 1) enQueue
    rear = (rear + 1) % N      # enQueue(10)
    q[rear] = 10
    
    rear = (rear + 1) % N      # enQueue(20)
    q[rear] = 20
    
    rear = (rear + 1) % N      # enQueue(30)
    q[rear] = 30
    
    rear = (rear + 1) % N      # enQueue(40)
    q[rear] = 40
    
    # 2) deQueue
    front = (front + 1) % N          
    print(q[front])             # 40
    
    front = (front + 1) % N         
    print(q[front])             # 20
    
    front = (front + 1) % N     
    print(q[front])             # 30
    ```
    
    ```python
    # append와 pop을 사용했을 때, 느려지는 문제를 해결하기 위한 방법
    # 주의) 코테 때 사용하면 감점될 수 있음
    
    from collections import deque
    
    q = deque()
    
    q.append(10)
    q.append(20)
    q.append(30)
    
    print(q.popleft())
    print(q.popleft())
    print(q.popleft())
    ```
<br><br>

---
