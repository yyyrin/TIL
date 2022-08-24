# 선형 큐(Linear Queue)

1. **1차원 배열을 이용한 큐**
    - 큐의 크기 = 배열의 크기<br>

    - front: 저장된 첫 번째 원소의 인덱스
    - rear: 저장된 마지막 원소의 인덱스
<br><br><br>

2. **상태 표현**
    - 초기 상태: front = rear = -1<br>

    - 공백 상태: front == rear
    - 포화 상태: rear == n-1 (n: 배열의 크기, n-1: 배열의 마지막 인덱스)
<br><br><br>

3. **초기 공백 큐 생성**
    - 크기 n인 1차원 배열 생성<br>

    - front와 rear을 -1로 초기화
<br><br><br>

4. **삽입: `enQueue(item)`**
    - 마지막 원소 뒤에 새로운 원소를 삽입하기 위해
        - rear 값을 하나 증가시켜 새로운 원소를 삽입할 자리를 마련<br>

        - 그 인덱스에 해당하는 배열원소 Q[rear]에 item을 저장
        
        ```python
        def enQueue(item):
            global rear
            if isFull():
                print("Queue_Full")  # 디버깅용
            else:
                rear <- rear + 1;
                Q[rear] <- item;
        ```
<br><br>   
    
5. **삭제: `deQueue()`**
    - 가장 앞에 있는 원소를 삭제하기 위해
        - front 값을 하나 증가시켜 큐에 남아있게 될 첫 번째 원소 이동<br>

        - 새로운 첫 번째 원소를 리턴함으로써 삭제와 동일한 기능함
        
        ```python
        def deQueue():
            if(isEmpty()) then Queue_Empty();
            else{
                front <- front + 1;
                return Q[front];
            }
        ```
<br><br>        
    
6. **공백상태 및 포화상태 검사: `isEmpty()`, `isFull()`**
    - 공백상태: front == rear<br>

    - 포화상태: rear == n-1 (n: 배열의 크기, n-1: 배열의 마지막 인덱스)
    
    ```python
    def isEmpty():
        return front == rear
    
    def Full():
        return rear == len(Q) -1
    ```
<br><br>

7. **검색: `Qpeek()`**
    - 가장 앞에 있는 원소를 검색하여 반환하는 연산<br>

    - 현재 front의 한자리 뒤(front+1)에 있는 원소, 즉 큐의 첫 번째에 있는 원소를 반환
    
    ```python
    def Qpeek():
        if isEmpty():
            print("Queue_Empty")
        else:
            return Q[front+1]
    ```
<br><br>

8. **선형 큐 이용시의 문제점**
    - 잘못된 포화상태 인식
        - 선형 큐를 이용하여 원소의 삽입과 삭제를 계속할 경우, 배열의 앞부분에 활용할 수 있는 공간이 있음에도 불구하고, rear = n-1인 상태, 즉 포화상태로 인식하여 더 이상의 삽입을 수행하지 않게 됨<br><br>
        
        ![linear Queue problem img](./images/linear%20queue%20problem.png)
    <br><br>

    - 해결방법 1
        - 매 연산이 이루어질 때마다 저장된 원소들을 배열의 앞부분으로 모두 이동시킴<br>

        - 원소 이동에 많은 시간이 소요되어 큐의 효율성이 급격히 떨어짐
        
        ![queue solution1 img](./images/queue%20solution.png)
    <br><br>

    - 해결방법 2
        - 1차원 배열을 사용하되, 논리적으로는 배열의 처음과 끝이 연결되어 원형 형태의 큐를 이룬다고 가정하고 사용<br>
        
        - 원형 큐를 사용한다고 해서 완전히 해결되는 것은 x
        - 원형 큐의 논리적 구조
        
        ![queue solution2 img](./images/queue%20solution2.png)
<br><br><br>        

---
