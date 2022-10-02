# **프로그래밍과 논리/수학**

1. 논리(Hard Logic)
    - Hard vs. Soft Logic
    - 카드문제
    - 사실: 모든 카드의 한쪽에는 알파벳이, 다른 쪽에는 숫자가 써 있음
    - 주장: 만약 한쪽이 D이면 반대쪽은 3
    - 주장이 사실인지 확인하기 위해 다음 카드들 중 반드시 뒤집어 보아야 하는 것은 몇 개이고 어느 것인가?
        
        ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/aa8ce580-2d53-4d8c-9cea-3777ea5c253b/Untitled.png)
        
    - 답: [D]와 [7]
    - [D]를 뒤집어 보아야 한다는 것은 누구나 알아 냄
    - [3]을 뒤집어 보아야 한다고 말하는 경우가 많이 있음
    - 중요: [3] 뒤에 [D]가 있든 없든 주장이 사실인지 여부에 영향이 없음
    - [7]을 뒤집어 볼 필요가 없다고 말하는 경우도 많음
    - 중요: [7] 뒤에 [D]가 있으면 주장이 성립하지 않게 됨
    
2. 맥주집 문제
    - 규칙: 20세 이하인 사람은 맥주를 마실 수 없음
    - 나이 혹은 마시고 있는 것을 표시한 다음 4명 중 확인이 필요한 사람으 몇 명이고 누구인가?
        
        ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/15a9d063-11a0-42ad-a555-5149f84583c6/Untitled.png)
        
    - 답: [17세]와 [맥주]
    
    - 카드 문제와 맥주집 문제의 비교
    - 맥주집 문제가 훨씬 풀기 쉽다.
    - 사실, 두 문제는 완전히 같은 문제임. 즉, 논리적 구성은 완전히 동일함
    - 왜 맥주집 문제가 풀기 쉬운가?
    - 논리 구조를 정확히 이해하고 맥주집 문제를 푸는 사람은 카드 문제를 똑같이 풀 수 있음
    - 즉, 맥주집 문제를 풀 때 논리를 사용한 것이 아니다!
    
3. Hard vs. Soft Logic
    - 맥주집 문제를 풀 때는 직관을 사용한 것
    - 직관은 논리적인 느낌을 주는 것
    - 직관의 장점은 (익숙한 상황에서) 빠르다는 것
    - 직관의 단점은 정확하지 않다는 것 (가끔은 익숙한 상황에서도 틀림)
    - 또 다른 단점은 강한 착각을 일으킨다는 것
    
4. 과자와 버스
    - “너 과자 몇 개 먹었니?” vs. “버스 타려고 하는데 천원 있니?”
    - 두 질문은 같은 표현을 사용하지만, 하나는 정확한 개수를 요구하고, 다른 하나는 천원 이상이 있는지 물어보는 것
    
5. 토플과 복권
    - “합격하려면 토플 500점 이상 혹은 토익 600점 이상이 필요” vs. “복권에 당첨되면 자동차 혹은 천만원을 줍니다.”
    - 두 말은 같은 표현을 사용하지만 하나는 inclusive or, 다른 하나는 exclusive or
    
6. 일상 생활에서는
    - Soft Logic이 빠르기 때문에 유용
    - 논리적으로 부정확한 표현을 사용하지만, 어떤 의미인지 모든 사람이 이미 알고 있다는 가정이 존재
    
7. 프로그래밍은 Hard Logic을 사용
    - 프로그래밍 언어의 표현들이 모두 논리학에서 나온 것
    - 사용되는 수많은 알고리즘들을 이해하기 위해서는 Hard Logic이 필요
    
8. 오해의 근원
    - Soft Logic으로 알고리즘을 이해하려고 하는 것!
    - 알고리즘 설명을 보고 또 봐도 이해가 안 되는 것은 증명을 안 봤기 때문
    - 증명을 봐도 이해가 안 되는 것은 직관으로 이해하려고 하기 때문
    - 가끔 직관적으로 이해되는 알고리즘이 있지만 조금만 어려워지면 직관으로 완전한 이해를 얻는 것은 사실상 불가능
    
9. **명제**
    - 참이나 거짓을 알 수 있는 식이나 문장
    - p, q, r, …로 표현
    - 예) 서울은 대한민국의 수도다, 1+1=3
    
10. **진리값**
    - 참이나 거짓을 표현
    - T, F 또는 1, 0
    
11. **부정 NOT**
    - p가 명제일 때, 명제의 진리값이 반대
    - ~p 또는 ㄱp로 표기 (not p 또는 p의 부정으로 읽음)
        
        ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/012a0c54-76d0-4e40-a71e-2d300363e25a/Untitled.png)
        
    
12. **논리곱 AND**
    - p, q가 명제일 때, p, q 모두 참일 때만 참이 되는 명제
    - p ^ q (p and q, p 그리고 q)
        
        ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/ea75f602-5e6d-4641-b376-4f0c608a4e78/Untitled.png)
        
    
13. **논리합 OR**
    - p, q가 명제일 때, p, q 모두 거짓일 때만 거짓이 되는 명제
    - p V q (p or q, p 또는 q)
        
        ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/4396b16a-15ec-4b76-83a1-078fcd8cf467/Untitled.png)
        
14. 배타적 논리합 XOR
    - p, q가 명제일 때, p, q 중 하나만 참일 때 참이 되는 명제
    - p xor q
        
        ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/dc6af376-d478-4f5c-9310-de021b5121ed/Untitled.png)
        
15. 합성
    - 연산자 우선 순위
        - ㄱ > V, ^ > →, ↔
    - **항진명제**: 진리값이 항상 참
    - **모순명제**: 진리값이 항상 거짓
    - **사건명제**: 항진명제도 모순명제도 아닌 명제
    - **조건명제**
        - p, q가 명제일 때, 명제 p가 조건(또는 원인), q가 결론(또는 결과)로 제시되는 명제
        - p → q (p이면 q이다.)
            
            ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/74635b65-8ee5-43d2-9bb5-fb838f631f36/Untitled.png)
            
    - **쌍방조건명제**
        - p, q가 명제일 때, 명제 p와 q가 모두 조건이면서 결론인 명제
        - p ↔ q (p이면 q이고, q면 p다.)
    - 조건명제의 역, 이, 대우
        - 역: q → p
        - 이: ㄱp → ㄱq
        - 대우: ㄱq → ㄱp
            
            ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/dd715be6-223c-44ac-bb90-1fa05b68267e/Untitled.png)
            
    
16. **증명**
    - 정확한 명제식으로 표현할 수 있는 것이어야 함
    - 보통은 정확한 명제식까지 쓰지는 않으나 근본적으로는 명제식으로 바꿀 수 있음
    - 증명에 대한 수많은 오해가 p → q를 p ↔ q와 혼동하는 것에서 일어남
    
    - 모든 당구공은 색이 같다는 다음 증명에서 잘못된 것은?
        - **수학적 귀납법**: P(1)이 참이고, P(n) → P(n+1)이 참이면 P(n)은 모든 자연수 n에 대해서 참이다.
        - 모든 자연수 n에 대해 당구공 n개가 들어있는 집합에서 그 집합에 포함된 당구공은 모두 색이 같다는 것을 증명함
        - P(1): 당구공 1개가 들어있는 집합은 모두 색이 같음
        - P(n) → P(n+1)을 증명하기 위해 P(n)이 참이라고 가정
        - 당구공 n+1개가 들어있는 임의의 집합을 생각함
        - 이 집합에서 하나를 빼면 당구공 n개가 있는 집합이 되므로 지금 상황에서 모든 당구공의 색이 같음
        - 방금 뺀 원소를 다시 넣고, 다른 당구공을 빼면 역시 당구공 n개가 있는 집합이 되므로 지금 상황에서도 모든 당구공의 색이 같음
        - 위의 두 상황에서 처음 뺀 당구공과 두번째로 뺀 당구공의 색이 같음을 알 수 있으므로 당구공 n+1개가 들어 있는 임의의 집합은 색이 같은 것 만을 포함함
        
        - 대부분의 사람들이 P(n)이 참이라고 가정할 수 없다고 반론함
        - 수학적 귀납법에서 필요한 것은 P(n) → P(n+1)이 참임을 보이는 것 뿐이므로 P(n)이 정말로 참일 필요는 없음
        
    - 위 증명에서 실제로 잘못된 것은 다음 부분
        - 위의 두 상황에서 처음 뺀 당구공과 두번째로 뺀 당구공의 색이 같음을 알 수 있으므로…
    - 처음 뺀 당구공과 두번째로 뺀 당구공의 색이 같다는 것은 공통 부분이 있다는 것인데, 실제로 n=1인 경우, 즉 n+1=2인 경우 공통 부분이 없음
    
    - Prime Number의 개수는 무한히 많다는 다음 증명은 옳은가?
        - Prime Number의 개수가 유한한 k개라고 가정
        - 모든 Prime Number를 다 곱하고 1을 더한 수를 n이라고 하자
        - 이 수 n은 어떤 Prime으로 나누어도 나머지가 1이다
        - 그런데 n은 어떤 Prime보다도 크므로 합성수이다.
        - 합성수이지만 어떤 Prime으로도 나누어지지 않으므로 모순 발생
        
        - 이 증명에 대한 반론으로 몇 개의 Prime이 더 존재하면 되는 것이 아니냐는 주장이 자주 있음
        - 위 증명은 “Prime Number가 k개 이면 모순이 발생”, 즉, “Prime Number가 k개” → “항상 거짓”, 이 명제가 항상 참임을 확인한 것
        - 즉, “Prime Number가 k개”라는 명제가 항상 거짓일 수 밖에 없다!
    
17. 수학적 귀납법과 증명의 수준
    - 수학적 귀납법의 기본형: P(1)이 참이고, P(n) → P(n+1)이 참이면 P(n)은 모든 자연수 n에 대해서 참이다.
    - 수학적 귀납법의 강한 형태: P(1)이 참이고, P(1)^P(2)^…^P(n) → P(n+1)이 참이면 P(n)은 모든 자연수 n에 대해서 참이다.
    
    - 다음 함수가 1부터 x까지의 합을 계산함을 증명해 보자.
        
        ```python
        int sum(int x)
        {
            if (x <= 0) return 0;
            return x + sum(x-1);
        }
        ```
        
    - High-level 증명에서는 1부터 x까지 합의 정의 중 하나인 S(n)=S(n-1)+n을 그대로 코딩한 것이므로 증명이 된 것이라고 말하는 경우가 많음
    
    - 상세한 증명을 하려면 단순히 “답이 맞는 것이 당연하다”라고 말하는 것으로는 충분하지 않음
        - 증명이 가능한 명제를 만들어야 함
        - 이 경우 증명이 가능한 명제는 다음과 같음: “sum(x)가 리턴하는 값은 1+2+…+x의 값과 항상 같다.”
        - 이제 수학적 귀납법을 적용할 수 있음
        - P(1)이 참이다.: “sum(1)이 리턴하는 겂은 1이다.”를 증명하면 됨. 실제 코드에 1을 대입하면 1을 리턴함을 알 수 있음
        - P(x) → P(X+1)이 참이다.: “sum(x-1)이 1+2+…+(x-1)을 리턴하면 sum(x)는 1+2+…+x를 리턴한다.”를 증명하면 됨. 코드를 보면 sum(x)는 x+sum(x-1)의 값을 리턴함. sum(x-1)의 리턴 값은 1+2+…+(x-1)과 같다고 가정했으므로 sum(x)는 1+2+…+(x-1)+x를 리턴함을 확인할 수 있음
        - sum(x-1)을 블랙박스로 보는 것이 이해에 도움을 줄 때가 있음
    
18. 소팅의 사례
    - High-level 증명에서는 소팅이 된다는 것을 직관적인 수준에서 설명하는 경우가 많음
    - 상세한 증명을 위해서는 증명이 가능한 명제가 필요
    - 배열 A[1], A[2], …, A[n]을 소팅하는 알고리즘의 정확성을 증명하려고 한다면, 증명이 가능한 명제는 다음과 같을 것임: “A[1] < A[2] < … < A[n]”
    - 버블 소트가 정확함을 어떻게 증명할 지 생각해 봅시다.

---