# 패턴 매칭

1. **패턴 매칭에서 사용되는 알고리즘**
    - 고지식한 패턴 검색 알고리즘
    - 카프-라빈 알고리즘
    - KMP 알고리즘
    - 보이어-무어 알고리즘
<br><br>

2. **고지식한 알고리즘(Brute Force)**
    - 본문 문자열을 처음부터 끝까지 차레대로 순회하면서 패턴 내의 문자들을 일일이 비교하는 방식으로 동작
    - 완전탐색

      ![brute force img](./images/brute%20force.png)
    
    - 알고리즘 설명
        
        ![brute force algorithm img](./images/brute%20force%20algo1.png)
        
    
      ![brute force algorithm img2](./images/brute%20force%20algo2.png)
    
    - 코드
        
        ```python
        p = "is"  # 찾을 패턴
        t = "This is a book~!"  # 전체 텍스트(target)
        M = len(p)  # 찾을 패턴의 길이
        N = len(t)  # 전체 텍스트의 길이
        
        def BruteForce(p, t):
            i = 0  # t의 인덱스 -> 비교인덱스
            j = 0  # p의 인덱스 -> 비교인덱스
            while j < M and i < N:   # -> 비교 인덱스가 문장의 길이 넘어가면 탐색 필요 x
                if t[i] != p[j]:     # 다를 때 -> i는 1칸 넘어가고, j는 처음으로 가야함
                    i = i - j      
                    j = -1
                i = i + 1    # 같은 경우 다음 문자 비교, index 증가
                j = j + 1    # 같은 경우 다음 문자 비교, index 증가
            if j == M:
                return i - M  # 검색 성공
            else:
                return -1  # 검색 실패
        ```
        
        ```python
        # python으로 작성된 코드(이해하기 쉬움)
        
        text = 'This is a book~!'
        pattern = 'is'
        
        def bruteforce(pattern, text):
            M = len(pattern)  # 패턴의 길이
            N = len(text)     # 텍스트의 길이
        
            for idx in range(N - M + 1):  # 텍스트 순회
                for jdx in range(M):      # 패턴의 길이만큼 순회
                    if pattern[jdx] != text[idx]:
                        break   # jdx의 순회 중지 -> idx + 1, jdx는 처음으로 돌아감
        
                else:   # 패턴이 매칭된 상태
                    return idx
        
            else:      # 탐색 실패
                return -1
        ```
<br>        
    
3. **고지식한 패턴 알고리즘의 시간 복잡도**
    - 최악의 경우 시간 복잡도는 텍스트의 모든 위치에서 패턴을 비교해야 하므로 O(MN)이 됨
    - 예에서는 최악의 경우 약 10,000 * 80 = 800,000 번의 비교가 일어난다.
    - 비교횟수를 줄일 수 있는 방법은 없는가?
<br><br>

4. **KMP 알고리즘**
    - 불일치가 발생한 텍스트 스트링의 **앞 부분에 어떤 문자가 있는지**를 미리 알고 있으므로(테이블로 미리 작성), 불일치가 발생한 앞 부분에 대하여 다시 비교하지 않고 매칭을 수행
    - 패턴을 **전처리**하여 배열 next[M]을 구해서 **잘못된 시작을 최소화**함
        - next[M] : 불일치가 발생했을 경우 이동할 다음 위치
    - 시간 복잡도: O(M+N)
    - 아이디어 설명
        - 텍스트에서 abcdabc까지는 매치되고, e에서 실패한 상황 패턴의 맨 앞의 abc와 실패 직전의 abc는 동일함을 이용할 수 있다.
        - 실패한 텍스트 문자와 P[4]를 비교한다.
        
          ![kmp idea img](./images/kmp%20idea.png)
        
    - 매칭이 실패했을 때 돌아갈 곳을 게산한다.  → 전처리 (참고 - 갤탭 필기)
        - 맨 앞에 ‘-1’은 의미 x, 0으로 해도 무방
        
          ![kmp algorithm img](./images/kmp%20algorithm.png)
        
    - 코드
    
    ```python
    def pre_process(pattern):
        # 전처리를 위한 테이블을 작성 (LPS longest prefix suffix)
        lps = [0] * len(pattern)
        j = 0  # lps를 만들기 위한 prefix index
    
        for i in range(1, len(pattern)):  # 0번째 자리는 패턴 확인할 필요x
    
            # prefix index 위치에 있는 문자와 비교
            if pattern[i] == pattern[j]:
                lps[i] = j + 1  # i의 앞에 중복되는 패턴이 존재한다.
                j += 1          # j는 중복된 글자의 자리 수
    
            else:
                j = 0
                # 여기서 0으로 이동한 다음 prefix idx 비교를 한 번 더 해야함
                if pattern[i] == pattern[j]:
                    lps[i] = j + 1
                    j += 1
    
        return lps
    
    def KMP(text, pattern):
        lps = pre_process(pattern)  # 전처리로 lps 테이블 생성
    
        i = 0  # text index
        j = 0  # pattern index
        while i < len(text):
            if pattern[j] == text[i]:  # 같은 문자라면
                # 다음 문자 비교
                i += 1
                j += 1
            else:
                if j != 0:
                    j = lps[j - 1]
                else:
                    i += 1
    
            if j == len(pattern):  # pattern이 전부 일치할 때
                return i - j       # text의 위치
    
        return -1    # 일치하는 문장이 없는 경우
    
    text = 'ABC ABCDAB ABCDABCDABDE'
    pattern = 'ABCDABD'
    print(KMP(text, pattern))  # 15
    ```
<br>

5. **보이어-무어 알고리즘**
    - 오른쪽에서 왼쪽으로 비교
    - 대부분의 상용 소프트웨어에서 채택하고 있는 알고리즘
    - 보이어-무어 알고리즘은 패턴에 오른쪽 끝에 있는 문자가 불일치하고, 이 문자가 패턴 내에 존재하지 않는 경우, 이동 거리는 무려 패텬의 길이만큼이 된다.
    
      ![boyer moore img](./images/boyer%20moore%201.png)
    
    - 오른쪽 끝에 있는 문자가 불일치하고 이 문자가 패턴 내에 존재할 경우
        
        ![boyer moore img2](./images/boyer%20moore%202.png)
        
    - 예시
        
        ![boyer moore example img](./images/boyer%20moore%20example.png)
        
    - rithm 문자열의 skip 배열
        
        
        | m | h | t | i | r | 다른 모든 문자 |
        | --- | --- | --- | --- | --- | --- |
        | 0 | 1 | 2 | 3 | 4 | 5 |
    - 문자열 매칭 알고리즘 비교
        - 찾고자 하는 문자열 패턴의 길이 m, 총 문자열 길이 n
        - 고지식한 패턴 검색 알고리즘: 수행시간 O(mn)
        - 카프-라빈 알고리즘: 수행시간 세타(n)
        - KMP 알고리즘: 수행시간 세타{n}
<br><br>

6. **보이어-무어 알고리즘 특징**
    - 앞의 두 매칭 알고리즘들의 공통점 텍스트 문자열의 문자를 적어도 한 번씩 훑는다는 것이다. 따라서 최선의 경우에도 오메가(n)
    - 보이어-무어 알고리즘은 텍스트 문자를 다 보지 않아도 된다.
    - 발상의 전환: 패턴의 오른쪽부터 비교
    - 최악의 경우 수행시간: 세타(mn)
    - 입력에 따라 다르지만 일반적으로 세타(n)보다 시간이 덜 든다.
<br><br>

7. **코드**
    
    ```python
    def pre_process(pattern):
        M = len(pattern)  # 패턴의 길이
    
        skip_table = dict()
        for i in range(M-1):
            skip_table[pattern[i]] = M - i - 1
    
        return skip_table
    
    def boyer_moore(text, pattern):
        skip_table = pre_process(pattern)
        M = len(pattern)
    
        i = 0  # text index
        while i <= len(text) - M:
            j = M -1  # 뒤에서 비교해야 되기 때문에 j를 끝에 index로 설정
            k = i + (M-1)  # 비교를 시작할 위치 (현재위치 + M번째 인덱스)
    
            # 비교할 j가 남아있고, text와 pattern이 일치하면
            # 그 다음 앞에 글자를 비교하기 위해 인덱스 감소
            while j >= 0 and pattern[j] == text[k]:
                j -= 1
                k -= 1
    
            if j == -1:  # 일치함
                return i
    
            # 일치하지 않는다면
            else:
                # i를 비교할 시작 위치를 skip table에서 가져온다.
                i += skip_table.get(text[i+M-1], M)
    
        return -1   # 일치되는 패턴이 없음
    
    text = 'ABC ABCDAB ABCDABCDABDE'
    pattern = 'ABCDABD'
    print(boyer_moore(text, pattern))
    ```
<br>    

---