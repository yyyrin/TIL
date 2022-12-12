# **Vue instance**

1. MVVM Pattern
    - 소프트웨어 아키텍처 패턴의 일종

    - 마크업 언어로 구현하는 그래픽 사용자 인터페이스**(view)**의 개발을 Back-end**(model)**로부터 분리시켜 view가 어느 특정한 모델 플랫폼에 종속되지 않도록 함
    
    ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/1d2ffa9e-1be0-441f-97af-0b1d1ec8690b/Untitled.png)
    
    ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/e2e498d1-ffa2-480d-baf8-5be223eb8ee0/Untitled.png)
    
    - **View** - 우리 눈에 보이는 부분 = DOM !
    - **Model** - 실제 데이터 = JSON !
    - **View Model** (Vue)
        - View를 위한 Model
        - View와 연결(binding)되어 Action을 주고 받음
        - Model이 변경되면 View Model도 변경되고 바인딩된 View도 변경됨
        - View에서 사용자가 데이터를 변경하면 View Model의 데이터가 변경되고 바인딩된 다른 View도 변경됨

<br><br><br>

2. MVVM Pattern 정리
    - MVC 패턴에서 Controller를 제외하고 View Model을 넣은 패턴
    - View는 Model을 모르고, Model도 View를 모른다
        - == DOM은 Data를 모른다. Data도 DOM을 모른다. (독립성 증가, 작은 의존성)
    - View에서 데이터를 변경하면 View Model의 데이터가 변경되고, 연관된 다른 View도 함께 변경된다.
    
3. Vue instance
    - `04_vue.start.html`에서 작업 진행
    - 1) Vue CDN 가져오기
    - 2) **new** 연산자를 사용한 생성자 함수 호출
        - vue instance 생성
    - 3) 인스턴스 출력 및 확인
        
        ```html
        // 04_vue_start.html
        
        <!DOCTYPE html>
        <html lang="en">
        <head>
          ...
        </head>
        <body>
          <!-- Vue CDN 작성 -->
          <script>
            // CODE HERE
            const vm = new Vue()
            console.log(vm)
          </script>
        </body>
        </html>
        ```
        
    
    ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/28ab8874-80b8-4c19-b994-118ae03ba384/Untitled.png)
    
    - Vue instance === 1개의 객체
    - 아주 많은 속성과 메서드를 이미 가지고 있고, 이러한 기능들을 사용하는 것
    
4. [참고] 생성자 함수
    
    
    - `05_constructor_func.js`에서 진행
    - JS에서 객체를 하나 생성한다고 한다면?
        - 하나의객체를 선언하여 생성
    
    ```jsx
    const member = {
      name: 'aiden',
      age: 22,
      sId: 2022311491,
    }
    ```
    
    - 동일한 형태의 객체를 또 만든다면?
        - 또 다른 객체를 선언하여 생성
    
    ```jsx
    const member2 = {
      name: 'haley',
      age: 20,
      sId: 2022311492,
    }
    ```
    
    - 동일한 구조의 객체를 여러 개 만들고 싶다면?
    - 생성자 함수는 특별한 함수를 의미하는 것이 아님
    - **new** 연산자로 사용하는 함수
        
        ```jsx
        function Member(name, age, sId) {
          this.name = name
          this.age = age
          this.sId = sId
        }
        
        const member3 = new Member('isaac', 21, 2022654321)
        ```
        
    
    - 함수 이름은 반드시 대문자로 시작
    - 생성자 함수를 사용할 때는 반드시 **new** 연산자를 사용
    
5. el (element)
    - Vue instance와 DOM을 mount(연결)하는 옵션
        - View와 Model을 연결하는 역할
        - HTML id 혹은 class와 마운트 가능
    - Vue instance와 **연결되지 않은 DOM 외부는 Vue의 영향을 받지 않음**
        - Vue 속성 및 메서드 사용 불가
    
    - `04_vue_start.html`에서 작업 진행
    - 새로운 Vue instance 생성
    - 생성자 함수 첫 번째 인자로 **Object** 작성
    - el 옵션에 **#app** 작성 = DOM 연결
    - 인스턴스 출력
    
    ```jsx
    <div id="app">
    </div>
    ...
    <script>
      // const vm = new Vue()
      // console.log(vm)
    
      const app = new Vue({
        el: '#app'
      })
      console.log(app)
    </script>
    ```
    
    ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/d470500b-e94a-45fb-a57e-29fcfc4276cd/Untitled.png)
    
    - Vue와 연결되지 않은 div 생성
        - 두 div 모두 **{{ message }}** 작성
        - 결과 확인
    - **message** 속성이 정의 되지 않았다는 경고와 **{{ message }}**가 그대로 출력되는 차이
    
    ```jsx
    <div id="app">
      {{ message }}
    </div>
    <div>
      {{ message }}
    </div>
    <script>
      const app = new Vue({
        el: '#app'
      })
      console.log(app)
    </script>
    ```
    
    ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/366c78fa-6181-4cac-ad95-37127fa94f63/Untitled.png)
    
6. data
    - Vue instance의 **데이터 객체** 혹은 **인스턴스 속성**
    - 데이터 객체는 반드시 기본 객체 **{ } (Object)** 여야 함
    - 객체 내부의 아이템들은 value로 모든 타입의 객체를 가질 수 있음
    - 정의된 속성은 **interpolation {{ }}**을 통해 view에 렌더링 가능함
    
    - Vue instance에 **data** 객체 추가
    - data 객체에 **message** 값 추가
    - 결과 확인
    - 추가된 객체의 각 값들은 **this.message** 형태로 접근 가능
    
    ```jsx
    <div id="app">
      {{ message }}
    </div>
    
    <!-- Vue CDN -->
    <script>
      const app = new Vue({
        el: '#app',
        data: {
          message: 'Hello, Vue!'
        },
      })
    </script>
    ```
    
7. methods
    
    
    - Vue instance의 **method**들을 정의하는 곳
    - **methods** 객체 정의
        - 객체 내 **print method** 정의
        - print method 실행 시 Vue instance의 data내 message 출력
    - 콘솔창에서 `app.print()` 실행
    - this는 객체(newVue)를 가리킴
    - 원래 this.$data.message로 작성해야 하지만 $data는 생략 가능
    
    ```jsx
    <script>
      const app = new Vue({
        el: '#app',
        data: {
          message: 'Hello, Vue!'
        },
        methods: {
          print: function () {
            console.log(this.message)
          },
        }
      })
    </script>
    ```
    
    ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/63793bb7-da2b-4e05-8401-9a7f3be16929/Untitled.png)
    
    - method를 호출하여 data 변경 가능
        - 객체 내 **bye method** 정의
        - print method 실행 시 Vue instance의 data내 message 변경
    - 콘솔창에서 `app.bye()` 실행
        - DOM에 바로 변경된 결과 반영
        - Vue의 강력한 반응성(reactivity)
        
    
    ```jsx
    <script>
      const app = new Vue({
        el: '#app',
        data: {
          message: 'Hello, Vue!'
        },
        methods: {
          ...
          bye: function () {
            this.message = 'Bye, Vue!'
          },
        }
      })
    </script>
    ```
    
    ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/bd28cf28-8bda-46ab-96d0-f8fd44e86d96/Untitled.png)
    
8. [주의] methods with Arrow Function
    
    
    - **메서드를 정의할 때, Arrow Function을 사용하면 안됨**
    - Arrow Function의 this는 함수가 선언될 때 상위 스코프를 가리킴
    - 즉 this가 상위 객체 window를 가리킴
    - 호출은 문제 없이 가능하나 this로 Vue의 data를 변경하지 못함
    - 전역변수 선언하는 것과 동일
    
    ```jsx
    <script>
      const app = new Vue({
        el: '#app',
        ...
        methods: {
          ...
          arrowBye: () => {
            this.message = 'Arrow?'
            console.log(this)
          }
        }
      })
    </script>
    ```
    
    ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/0df43d65-669e-4012-8a0e-2f32c5cebb13/Untitled.png)
    

---