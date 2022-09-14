# Memoization

- 피보나치 수를 구하는 함수를 재귀함수로 구현한 알고리즘의 문제점 → “엄청난 중복 호출이 존재!”
- 피보나치 수열의 Call Tree
    
    ![fibonacci call tree img](./images/fibonacci%20call%20tree.png)
<br><br><br>    

1. **Memoization**
    - 컴퓨터 프로그램을 실행할 때 이전에 계산한 값을 메모리에 저장해서 매번 다시 계산하지 않도록 하여 전체적인 실행속도를 빠르게 하는 기술<br>

    - 동적 계획법의 핵심이 되는 기술
    - ‘memoizaiton’은 글자 그대로 해석하면 ‘메모리에 넣기(to put in memory)’라는 의미
    - ‘기억되어야 할것’이라는 뜻의 라틴어 memorandum에서 파생
    - 흔히 ‘기억하기’, ‘암기하기’라는 뜻의 memorizaiton과 혼동하지만, 정확한 단어는 memoization (동사형: memoize)
<br><br><br>

2. **Memoization 적용 알고리즘**
    - 앞의 예에서 피보나치 수를 구하는 알고리즘에서 fibo(n)의 값을 계산하자마자 저장하면(memoize), 실행시간을 O(n)으로 줄일 수 있음<br><br>

    
    ```python
    # memo를 위한 배열을 할당하고, 모두 0으로 초기화한다.
    # memo[0]을 0으로 memo[1]는 1로 초기화한다.
    
    def fibo1(n):
        global memo
        if n >= 2 and len(memo) <= n:
            memo.append(fibo1(n-1) + fibo1(n-2))
        return memo[n]
    
    memo = [0, 1]
    ```
<br><br>    

---