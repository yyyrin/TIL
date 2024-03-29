# **1. 복잡도 분석**

1. 알고리즘
    - 유한한 단계를 통해 문제를 해결하기 위한 절차나 방법
    - 주로 컴퓨터용어로 쓰이며, 컴퓨터가 어떤 일을 수행하기 위한 단계적 방법
    - 간단하게 다시 말하면 어떠한 문제를 해결하기 위한 절차
    
2. 알고리즘의 효율
    - 공간적 효율성과 시간적 효율성
        - 공간적 효율성: 연산량 대비 얼마나 적은 메모리 공간을 요하는가
        - 시간적 효율성: 연산량 대비 얼마나 적은 시간을 요하는가
        - 효율성을 뒤집어 표현하면 복잡도(Complexity)가 된다. 복잡도가 높을수록 효율성은 저하된다.
    - 시간적 복잡도 분석
        - 하드웨어 환경에 따라 처리시간이 달라진다.
            - 부동소수 처리 프로세서 존재유무, 나눗셈 가속기능 유무
            - 입출력 장비의 성능, 공유여부
        - 소프트웨어 환경에 따라 처리시간이 달라진다.
            - 프로그램 언어의 종류
            - 운영체제, 컴파일러의 종류
        - 이러한 환경적 차이로 인해 분석이 어렵다.
    
3. 복잡도의 점근적 표기
    - 시간 (또는 공간)복잡도는 입력 크기에 대한 함수로 표기하는데, 이 함수는 주로 여러 개의 항을 가지는 다항식이다.
    - 이를 단순한 함수로 표현하기 위해 점근적 표기 (Asymptotic Notation)를 사용한다.
    - 입력 크기 n이 무한대로 커질 때의 복잡도를 간단히 표현하기 위해 사용하는 표기법이다.
        - O(Big-Oh)-표기
        - Ω(Big-Omega)-표기
        - ϴ(Big-Theta)-표기
    
4. O(Big-Oh)-표기
    - O-표기는 복잡도의 **점근적 상한**을 나타낸다. (최악의 경우)
    - 복잡도가 f(n)=2n^2-7n+4 이라면, f(n)의 O-표기는 O(n^2)이다.
    - 먼저 f(n)의 단순화된 표현은 n^2이다.
    - 단순화된 함수 n^2에 임의의 상수 c를 곱한 cn^2이 n이 증가함에 따라 f(n)의 상한이 된다. (단, c>0)
    - 단순히 **”실행시간이 n^2에 비례”**하는 알고리즘이라고 말함
        
        ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/80ba7feb-aa5a-4a61-bed5-d663e127bd43/Untitled.png)
        
    
---
