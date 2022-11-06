# **Event**

- 개요
    - **Event**란 프로그래밍하고 있는 시스템에서 일어나는 사건(action) 혹은 발생(occurrence)인데, 우리가 원한다면 그것들에 어떠한 방식으로 응답할 수 있도록 특정 시점을 시스템이 말해주는 것
        - 예를 들어 사용자가 웹 페이지의 버튼을 클릭한다면 우리는 클릭이라는 사건에 대한 결과를 응답 받기를 원할 수 있음
    - 클릭 말고도 웹에서는 각양각색의 Event가 존재
        - 키보드 키 입력, 브라우저 닫기, 데이터 제출, 텍스트 복사 등

### **1. Event Intro**

1. **Event** object
    - 네트워크 활동이나 사용자와의 상호작용 같은 사건의 발생을 알리기 위한 객체
    - Event 발생
        - 마우스를 클릭하거나 키보드를 누르는 등 사용자 행동으로 발생할 수도 있고
        - 특정 메서드를 호출하여 프로그래밍적으로도 만들어 낼 수 있음
    
    - DOM 요소는 Event를 받고(**”수신”**)
    - 받은 Event를 **”처리”**할 수 있음
        - Event 처리는 주로 **addEventListener()**라는 Event 처리기(Event handler)를 사용해 다양한 html 요소에 **”부착”**하게 됨
    
2. Event handler - **addEventListener()**
    
    ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/503b7115-2c9e-406a-a442-80e30bff69aa/Untitled.png)
    
    - `EventTarget.addEventListener(type, listener[, options])`
        - 지정한 Event가 대상에 전달될 때마다 호출할 함수를 설정
        - Event를 지원하는 모든 객체(Element, Document, Window 등)를 대상(EventTarget)으로 지정 가능
    
    - EventTarget.addEventListener(**type**, listener[, options])
        - `type`
            - 반응 할 Event 유형을 나타내는 대소문자 구분 문자열
            - 대표 이벤트
                - `input`, `click`, `submit` …
                - 다양한 이벤트 확인([https://developer.mozilla.org/en-US/docs/Web/Events](https://developer.mozilla.org/en-US/docs/Web/Events))
    
    - EventTarget.addEventListener(type, **listener**[, options]) 🔥
        - `listener`
            - 지정된 타입의 Event를 수신할 객체
            - JavaScript function 객체(콜백 함수)여야 함
            - 콜백 함수는 발생한 Event의 데이터를 가진 Event 기반 객체를 유일한 매개변수로 받음

### **2. Event 실습**

1. 01_button.html
    - 버튼을 클릭하면 특정 변수 값 변경하기
        
        ```jsx
        <body>
          <button id="btn">버튼</button>
          <p id="counter">0</p>
          <script>
          // 초기값
          let countNumber = 0
        
          // ID가 btn인 요소를 선택
          const btn = document.querySelector('#btn')
          console.log(btn)
          // btn이 클릭 되었을 때마다 함수가 실행됨
          btn.addEventListener('click', function() {
            console.log('버튼 클릭!')
            // countNumber를 증가시키고
            countNumber += 1
            // id가 counter인의 내용을 변경 시킨다.
            const counter = document.querySelector('#counter')
            counter.innerText = countNumber
          })
          </script>
        </body>
        ```
        
    - 실행 결과
        
        ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/874f09a6-b875-430e-9de8-654011a4df46/Untitled.png)
        
    
2. 02_input.html
    - input에 입력하면 입력 값을 실시간으로 출력하기
        
        ```jsx
        <body>
          <input type="text" id="text-input">
          <p></p>
          <script>
          // 1. input 선택
          const textInput = document.querySelector('#text-input')
          // 2. input 이벤트 등록
          textInput.addEventListener('input', function (event) {
            console.log(event)
            // input은 이벤트의 대상
            console.log(event.target)
            // input의 value를 받아오기
            console.log(event.target.value)
        
            // 3. input에 작성한 값을 p 태그에 출력하기
            const pTag = document.querySelector('p')
            pTag.innerText = event.target.value
          })
          </script>
        </body>
        ```
        
    - 실행 결과
        
        ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/c44e2084-2132-48d7-b255-d027f82617ae/Untitled.png)
        
    
3. 03_button_input.html
    - input에 입력하면 입력 값을 실시간으로 출력하고 버튼을 클릭하면 출력된 값의 클래스를 토글하기
        
        ```jsx
        ...
          <style>
            .blue {
              color: blue;
            }
          </style>
        </head>
        <body>
          <h1></h1>
          <button id="btn">클릭</button>
          <input type="text">
        
          <script>
            const btn = document.querySelector('#btn')
            // btn이 클릭되면 함수 실행
            btn.addEventListener('click', function() {
              // h1 태그를 선택해서
              console h1 = document.querySelector('h1')
              // 클래스 blue를 토글하기
              h1.classList.toggle('blue')
            })
        
            // input
            const input = document.querySelect('input')
            // input에 값이 입력되면 함수 실행
            input.addEventListener('input', function(event) {
              // h1 태그를 선택해서
              const h1Tag = document.querySelector('h1')
              // input값을 태그의 컨텐츠로 채우기
              h1Tag.innterText = event.target.value
            })
          </script>
        ```
        
    - 실행 결과
        
        ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/8591912e-3118-4ff9-8e99-7d7b7fdb39f2/Untitled.png)
        
    
4. addEventListener 정리
    - “~하면 ~한다.”
        - “클릭하면, 경고창을 띄운다”
        - “특정 Event가 발생하면, 할 일(콜백 함수)을 등록한다.”

### **3. Event 취소**

- **event.preventDefault()** 🔥
    - 현재 Event의 기본 동작을 중단
    - HTML 요소의 기본 동작을 작동하지 않게 막음
    - HTML 요소의 기본 동작 예시 🔥
        - a 태그: 클릭 시 특정 주소로 이동
        - form 태그 : form 데이터 전송

### **4. Event 취소 실습**

- 04_prevent.html
    - 웹 페이지 내용을 복사하지 못하도록 하기
        
        ```jsx
        <body>
          <div>
            <h1>정말 중요한 내용</h1>
          </div>
          <script>
            const h1 = document.querySelector('h1')
            h1.addEventListener('copy', function(event) {
              // copy event의 기본 동작을 막기
              event.preventDefault()
              alert('삐빅 복사 할 수 없습니다.')
            })
          </script>
        </body>
        ```
        
    - 실행 결과
        
        ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/4b2e8ba5-bdaf-4ce4-89b8-6894cbffe8e1/Untitled.png)
        

### **5. Event 종합 실습**

1. 종합 실습 1
    - 05_lotto.html
        - 버튼을 클릭하면 랜덤 로또 번호 6개를 출력하기
            
            ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/dc3ed5b4-6a4c-49ca-a31b-1d136672c809/Untitled.png)
            
        
        ```jsx
        <h1>로또 추천 번호</h1>
        <button id="lotto-btn">행운 번호 받기</button>
        <div id="result"></div>
        ```
        
        ```jsx
        <script src="https://cdn.jsdeliver.net/npm/lodash@4.17.21/lodash.min.js"></script>
        <script>
          const button = document.querySelector('#lotto-btn')
          button.addEventListner('click', function() {
          // 컨테이너를 만들고
          const ballContainer = document.createElement('div')
            ballContainer.classList.add('ball-container')
        
          // 랜덤 숫자 6개 만들기
          const numbers = _.sampleSize(_.range(1, 46), 6)
          // console.log(numbers)
        
          // 공 만들기
          numbers.forEach((element, idx) => {
            const ball = document.createElement('div')
            ball.classList.add('ball')
            ball.innerText = number[idx]
            ball.style.backgroundColor = 'crimson'
            // 공을 컨테이너의 자식으로 추가
            ballContainer.appendChild(ball)
          })
        
          // 컨테이너를 결과 영역의 자식으로 추가
          const result = document.querySelect('#result')
          result.appendChild(ballContainer)
        })
        </script>
        ```
        
    
2. [참고] lodash
    - 모듈성, 성능 및 추가 기능을 제공하는 JavaScript 유틸리티 라이브러리
    - array, object 등 자료구조를 다룰 때 사용하는 유용하고 간편한 유틸리티 함수들을 제공
    - 함수 예시
        - `reverse`, `sortBy`, `range`, `random` …
    - [https://lodash.com/](https://lodash.com/)
    
3. 종합 실습 2
    - 06_todo.html
        - CREATE, READ 기능을 충족하는 todo app 만들기
            
            ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/dc260a25-c1ac-4aee-a6e0-5b58549ec2fb/Untitled.png)
            
        
        ```jsx
        <form action="#">
          <input type="text" class="inputData">
          <input type="submit" value="Add">
        </form>
        <ul></ul>
        ```
        
        ```jsx
        <script>
          const formTag = document.querySelector('form')
        
          const addTodo = function (event) {
            event.preventDefault()
        
            const inputTag = document.querySelector('.inputData')
            const data = inputTag.value
        
            if (data.trim()) {
              const lliTag = document.createElement('li')
              liTag.innerText = data
        
              const ulTag = document.querySelector('ul')
              ulTag.appendChild(liTag)
            } else {
              alert('할 일을 입력하세요.')
            }
            event.target.reset()
          }
        
          formTag.addEventListener('submit', addTodo)
        </script>
        ```
        
        ```jsx
        <body>
          <form action="#">
            <input type="text" class="inputData">
            <input type="submit" value="Add">
          </form>
          <ul></ul>
        
          <script>
            const formTag = document.querySelector('form')
        
            const addTodo = function (event) {
              // console.log(event)
              event.preventDefault()
        
              const inputTag = document.querySelector('.inputData')
              const data = inputTag.value 
              // console.log(data)
        
              if (data.trim()) {
                const liTag = document.createElement('li')
                liTag.innerText = data
        
                const ulTag = document.querySelector('ul')
                ulTag.appendChild(liTag)
                event.target.reset()
              } else {
                alert('내용을 입력하세요')
              }
            }
        
            formTag.addEventListener('submit', addTodo)
          </script>
        </body>
        </html>
        ```
        

---