# Python 기초

# 1. 코드 작성법

### a. 들여쓰기

- Space Sensitive
  - 문장을 구분할 때, 중괄호 ({,}) 대신 들여쓰기 (indentation)를 사용
  - 들여쓰기를 할 때는 **4칸**(space키 4번) 혹은 1탭(Tab키 1번)을 입력
    - 주의! 한 코드 안에서는 반드시 한 종류의 들여쓰기를 사용 → 혼용금지
    - Tab으로 들여쓰면 계속 탭으로 들여써야 함
    - 원칙적으로 공백(빈칸, space) 사용을 권장 *PEP8 권장사항

### b. 주석(Comment)

- 코드에 대한 설명
  - 코드를 보다 이해하기 쉽게 하여 분석 및 수정 용이해짐
  - 주석은 코드에 영향을 주지 않으며, 오로지 개발자를 위한 것
- 초기부터 들여야 할 가장 중요한 습관
  - 개발자에게 주석을 다는 습관은 매우 중요
  - 잘 달린 주석은 그 어떤 정보보다 유용함
    - 주석은 실행에 영향을 미치지 않을 뿐만 아니라
    - 프로그램의 속도를 느리게 하지 않으며, 용량을 늘리지 않음
- 한 줄 주석
  - 주석으로 처리될 내용 앞에 ‘#’ 을 입력
    - 한 줄을 온전히 사용할 수도 있고, 코드 뒷부분에 작성할 수도 있음
- 여러 줄 주석
  - 한 줄씩 # 을 사용하거나, “”” 또는 ‘’’ 으로 묶어서 표현
- 주석(Comment)의 장점
  - 개발자에게 주석을 다는 습관은 매우 중요
    - 코드의 내용을 잘 이해할 수 있도록 작성
    - 가독성을 저해할 정도로 무분별한 사용은 자제
  - 코드를 쉽게 이해할 수 있어서 코드 수정 및 협업에 유리

---

# 2. 파이썬 기초 문법

## i. 변수

1. 변수(Variable)
   
   - 변수란?
     - 데이터를 저장하기 위해서 사용
     - 변수를 사용하면 복잡한 값들을 쉽게 사용할 수 있음(추상화)
   - 동일 변수에 다른 데이터를 언제든 할당(저장)할 수 있기 때문에, ‘변수’라고 불림

2. 추상화(변수를 사용해야 하는 이유)
   
   - 코드의 가독성 증가
   - 숫자를 직접 적지 않고, 의미 단위로 작성 가능
   - 코드 수정이 용이해짐

3. 변수의 할당
   
   - 변수는 할당 연산자(=)를 통해 값을 할당(assignment)
   - 같은 값을 동시에 할당할 수 있음
   
   ```python
   americano_price = cookie_price = 2000
   print(americano_price, cookie_price)  # 2000 2000
   ```
   
   - 다른 값을 동시에 할당할 수 있음
   
   ```python
   americano_price, mocha_price = 2000, 3000
   print(americano_price, mocha_price)  # 2000 3000
   ```

4. 각 변수의 값을 바꿔서 저장하기
   
   - x = 10 y = 20 일 때, 각각 값을 바꿔서 저장하는 코드를 작성하시오.
   
   - 방법1) 임시 변수 활용
     
     ```python
     x, y = 10, 20
     
     tmp = x
     x = y
     y = tmp
     print(x, y)  # 20, 10
     ```
   
   - 방법2) Pythonic!
     
     ```python
     x, y = 10, 20
     
     y, x = x, y
     print(x, y)  # 20 10
     ```

5. 식별자
   
   - 변수 이름 규칙
     
     - 식별자의 이름은 영문 알파벳, 언더스코어(_), 숫자로 구성
     
     - 첫 글자에 숫자가 올 수 없음
     
     - 길이 제한이 없고, 대소문자를 구별
     
     - 다음 의 키워드(keywords)는 예약어(reserved words)로 사용할 수 없음
       
       ```python
       import keyword
       print(keyword.kwlist)
       
       # 출력 결과
       ['False', 'None', 'True', '__peg_parser__', 'and', 'as', 'assert', 'async', 'await', 'break', 'class', 'continue', 'def', 'del', 'elif', 'else', 'except', 'finally', 'for', 'from',
       'global', 'if', 'improt', 'in', 'is', 'lambda', 'nonlocal', 'not', 'or', 'pass', 'raise', 'return', 'try', 'while', 'with', 'yield']
       ```
     
     - 내장 함수나 모듈 등의 이름도 사용하지 않아야 함
     
     - 동작을 예상할 수 없게 임의로 값을 할당하게 되므로 범용적이지 않은 코드가 됨

## ii. 연산자

1. 산술 연산자(Arithmetic Operator)
   
   - 기본적인 사칙연산 및 수식 계산
     
     | 연산자 | +   | -   | *   | /   | //  | **   |
     | --- | --- | --- | --- | --- | --- | ---- |
     | 내용  | 덧셈  | 뺄셈  | 곱셈  | 나눗셈 | 몫   | 거듭제곱 |

2. 연산자 우선순위
   
   - 기본적으로 수학에서 우선순위와 같음
   - 괄호가 가장 먼저 계산되고, 그 다음에 곱하기(*)와 나누기(/)가 더하기(+)와 빼기(-)보다 먼저 계산됨

---

# 3. 자료형

1. 자료형(Datatype) 분류
   
   - 프로그래밍에서는 다양한 종류의 값(데이터)을 쓸 수 있음
   
   - 사용할 수 있는 데이터의 종류들을 자료형(Datatype)이라고 함
   
   - 수치형(Numeric Type)
     
     - int (정수, integer)
     - float (부동소수점, 실수, floating point number)
     - complex (복소수, complex number)
   
   - 문자열(String Type)
   
   - 불린형(Boolean Type)
   
   - None

## i. 수치형 (Numeric Type)

1. 정수 자료형(int)
   
   - 0, 100, -200과 같은 정수를 표현하는 자료형
     - 일반적으로 수학 연산(사칙 연산) 가능

2. 진수 표현
   
   - 여러 진수 표현 가능
     - 2진수(**b**inary) : 0b
     - 8진수(**o**ctal) : 0o
     - 16진수(he**x**adecimal) : 0x

3. 실수 자료형(float)
   
   - 유리수와 무리수를 포함하는 ‘실수’를 다루는 자료형
     - 0.1, 100.0, -0.001 등

4. 실수 연산시 주의할 점(부동 소수점)
   
   - 실수의 값을 처리할 때 의도하지 않은 값이 나올 수 있음
   - 컴퓨터는 2진수를 사용, 사람은 10진법을 사용
   - 이런 과정에서 예상치 못한 결과가 나타남
   - 해결책
     - 값 비교하는 과정에서 정수가 아닌 실수면 주의할 것
     - 매우 작은 수보다 작은지를 확인하거나 math 모듈 활용

## ii. 문자열 자료형 (String Type)

1. 문자열 자료형의 정의
   
   - 모든 문자는 str 타입
   - 문자열은 작은 따옴표(’)나 큰따옴표(”)를 활용하여 표기
     - 문자열을 묶을 때 동일한 문장부호를 활용
     - PEP8에서는 소스코드 내에서 하나의 문장부호를 선택하여 유지하도록 함

2. 중첩 따옴표
   
   - 따옴표 안에 따옴표를 표현할 경우
     - 작은따옴표가 들어 있는 경우는 큼따옴표로 문자열 생성
     - 큰따옴표가 들어 있는 경우는 작은따옴표로 문자열 생성

3. 삼중 따옴표(Triple Quotes)
   
   - 작은따옴표나 큰따옴표를 삼중으로 사용
     - 따옴표 안에 따옴표를 넣을 때
     - 여러 줄을 나눠 입력할 때 편리

4. Escape sequence
   
   - 역슬래시(backslash)뒤에 특정 문자가 와서 특수한 기능을 하는 문자 조합
     
     | 예약 문자 | 내용(의미)    |
     | ----- | --------- |
     | \n    | 줄 바꿈      |
     | \t    | 탭         |
     | \r    | 캐리지 리턴    |
     | \0    | 널(Null)   |
     | \\    | \         |
     | \’    | 단일인용부호(’) |
     | \”    | 이중인용부호(”) |

5. 문자열 연산
   
   - 덧셈
     
     - 파이썬에서 문자열 덧셈은 문자열을 **연결**
     
     - 영어로는 String Concatenation이라고 함
       
       ```python
       print("Hello" + "World")  # HelloWorld
       ```
   
   - 곱셈
     
     ```python
     print("Python" * 3)  # PythonPythonPython
     ```

6. String Interpolation (문자열을 변수를 활용하여 만드는 법)
   
   - f-strings
     
     ```python
     name = 'Kim'
     score = 4.5
     
     print(f'Hello, {name}! 성적은 {score}')  # Hello, Kim! 성적은 4.5
     
     import datetime  # 시간을 다루는 라이브러리
     today = datetime.datetime.now()  # 현시간
     print(today)  # 2022-07-08 16:04:15.200411
     
     print(f'오늘은 {today:%y}년 {today:%m}월 {today:%d}일')  # 오늘은 22년 07월 08일
     
     pi = 3.141592
     print(f'원주율은 {pi:.3}. 반지름이 2일 때 원의 넓이는 {pi*2*2}')
     # 원주율은 3.14. 반지름이 2일때 원의 넓이는 12.566368
     ```

## iii. None

- 파이썬 자료형 중 하나
- 값이 없음을 표현하기 위해 None 타입이 존재
- 일반적으로 반환 값이 없는 함수에서 사용하기도 함

## iv. 불린형

1. 불린형(Boolean)이란?
   
   - 논리 자료형으로 참과 거짓을 표현하는 자료형
   - True 또는 False 를 값으로 가짐
   - 비교 / 논리 연산에서 활용됨

2. 비교 연산자
   
   - 수학에서 등호와 부등호와 동일한 개념
   - 주로 조건문에 사용되며 값을 비교할 때 사용
   - 결과는 True / False 값을 리턴함
   
   | 연산자 | <   | < = | >   | > = | ==  | ! =  | is            | is not          |
   | --- | --- | --- | --- | --- | --- | ---- | ------------- | --------------- |
   | 내용  | 미만  | 이하  | 초과  | 이상  | 같음  | 같지않음 | 객체 아이덴티티(OOP) | 객체 아이덴티티가 아닌 경우 |

3. 논리 연산자
   
   - 여러 가지 조건이 있을 때
     
     - 모든 조건을 만족하거나(And), 여러 조건 중 하나만 만족해도 될 때(or) 특정 코드를 실행하고 싶을 때 사용
   
   - 일반적으로 비교연산자와 함께 사용됨
     
     | 연산자     | 내용                         |
     | ------- | -------------------------- |
     | A and B | A와 B 모두 True시, True        |
     | A or B  | A와 B 모두 False시, False      |
     | Not     | True를 False로, False를 True로 |
   
   - and는 2개 다 True인 경우에 True
   
   - or는 1개라도 True면 True

4. 논리 연산자 주의할 점 / not 연산자
   
   - Falsy : False는 아니지만 False로 취급 되는 다양한 값
     
     - 0, 0.0, (), [], {}, None, “”(빈 문자열)
   
   - 논리 연산자도 우선순위가 존재
     
     - not, and, or 순으로 우선순위가 높음
     
     ```python
     print(not True)  # False
     print(not 0)  # True
     print(not 'hi')  # False
     print(not True and False or not False)  # True
     # 위와 같음
     print(((not True) and False) or (not False))  # True
     ```

5. 논리 연산자의 단축 평가
   
   - 결과가 확실한 경우 두번째 값은 확인하지 않고 첫번째 값 반환
   - and 연산에서 첫번째 값이 False인 경우 무조건 False ⇒ 첫번째 값 반환
   - or 연산에서 첫번째 값이 True인 경우 무조건 True ⇒ 첫번째 값 반환
   - 0은 False, 1은 True
   
   ```python
   print(3 and 5)  # 5
   print(3 and 0)  # 0
   print(0 and 3)  # 0
   print(0 and 0)  # 0
   
   print(5 or 3)  # 5
   print(3 or 0)  # 3
   print(0 or 3)  # 3
   print(0 or 0)  # 0
   ```

```python
a = 5 and 4
print(a)  # 4

b = 5 or 3
print(b)  # 5

c = 0 and 5
print(c)  # 0

d = 5 or 0
print(d)  # 
```

---

# 4. 컨테이너

- 여러 개의 값(데이터)을 담을 수 있는 것(객체)으로, 서로 다른 자료형을 저장할 수 있음
  - 예시 : List
- 컨테이너의 분류
  - 순서가 있는 데이터(Ordered) vs. 순서가 없는 데이터 (Unordered)
  - 순서가 있다 ≠ 정렬되어 있다.

## i. 리스트

- 리스트는 여러 개의 값을 순서가 있는 구조로 저장하고 싶을 때 사용
1. 리스트의 생성과 접근
   
   - 리스트는 대괄호([]) 혹은 list()를 통해 생성
     
     - 파이썬에서는 어떠한 자료형도 저장할 수 있으며, 리스트 안에 리스트도 넣을 수 있음
     - 생성된 이후 내용 변경이 가능 → 가변 자료형
     - 이러한 유연성 때문에 파이썬에서 가장 흔히 사용
   
   - 순서가 있는 시퀀스로 인덱스를 통해 접근 가능
     
     - 값에 대한 접근은 list[i]
     
     ```python
     # 리스트명 = [요소1, 요소2, 요소3, ...]
     list_a = []
     list_b = [1, 2, 3]
     list_c = ['Life', 'is', 'too', 'short']
     list_d = [1, 2, 3, 'Python', ['리스트', '안에', '리스트']]
     ```

2. 예시
   
   ```python
   my_list = []
   another_list = list()
   print(type(my_list))  # <class 'list'>
   print(type(another_list))  # <class 'list'>
   
   location = ['서울', '대전', '구미', '광주', '부울경']
   print(location)  # ['서울', '대전', '구미', '광주', '부울경']
   print(type(location))  # <class 'list'>
   print(location[0])  # 서울
   ```
   
   - 리스트는 담고 있는 요소를 바꿀 수 있다. → 가변 자료형(mutable)
     
     ```python
     location[0] = '양양'
     print(location)  # ['양양', '대전', '구미', '광주', '부울경']
     ```

```python
boxes = ['A', 'B', ['apple', 'banana', 'cherry']]
print(len(boxes))  # 3
print(boxes[2]))  # ['apple', 'banana', 'cherry']
print(boxes[2][-1])  # 'cherry'
print(boxes[-1][1][0])  # 'b'
```

## ii. 튜플

1. 튜플의 정의
   
   - 튜풀은 여러 개의 값을 순서가 있는 구조로 저장하고 싶을 때 사용
     
     - 리스트와의 차이점은 생성 후, 담고 있는 값 변경이 불가 (불변 자료형)
   
   - 항상 소괄호 형태로 사용
     
     ```python
     print((1, 2, 3, 1))  # (1, 2, 3, 1)
     print(tuple((1, 2, 3, 1)))  # (1, 2, 3, 1)
     print(type((1, 2, 3, 1)))  # <class 'tuple'>
     ```

2. 튜플의 생성과 접근
   
   - 소괄호(()) 혹은 tuple()을 통해 생성
   
   - 튜플은 수정 불가능한(immutable) 시퀸스로 인덱스로 접근 가능
   
   - 값에 대한 접근은 my_tuple[i]
     
     ```python
     # 값 접근
     a = (1, 2, 3, 1)
     print(a[1])  # 2
     
     # 값 변경 => 불가능
     a[1] = '3'  # TypeError: 'tuple' object does not support item assignment
     ```

3. 튜플(Tuple) 생성 주의사항
   
   - 단일 항목의 경우
     - 하나의 항목으로 구성된 튜풀은 생성 시 값 뒤에 쉼표를 붙여야 함
   - 복수 항목의 경우
     - 마지막 항목에 붙은 쉼표는 없어도 되지만, 넣는 것을 권장(Trailing comma)
   
   ```python
   tuple_a = (1,)
   print(tuple_a)  # (1,)
   print(type(tuple_a))  # <class 'tuple'>
   
   tuple_b = (1, 2, 3,)
   print(tuple_b)  # (1, 2, 3)
   print(type(tuple_b))  # <class 'tuple'>
   ```
