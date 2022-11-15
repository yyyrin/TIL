# **Vue intro**

- 개요
    - Front-end 개발이란 무엇인가
    - Front-end framework란 무엇인가
    - Vue를 배우는 이유
    - Vue 기초 문법

### **1. Front-end Development**

1. 개요
    - 우리가 앞으로 할 일은 JavaScript를 활용한 Front-end 개발
    - Back-end 개발은 Back-end 개발에 특화된 Django로 진행
    - Front-end 개발은?
        - **Vue.js**
        - Vue.js === JavaScript Front-end Framework
    
2. Front-end Framework
    - Front-end(FE) 개발이란?
        - 사용자에게 보여주는 화면 만들기
    - **Web App**(SPA)을 만들 때 사용하는 도구
        - SPA - Single Page Application
    
3. Web App이란?
    - 웹 브라우저에서 실행되는 어플리케이션 소프트웨어
    - VIBE 웹 사이트로 이동
        - [https://vibe.naver.com/today](https://vibe.naver.com/today)
    - 개발자 도구 > 디바이스 모드
    - 웹 페이지가 그대로 보이는 것이 아닌 **디바이스에 설치된 App**처럼 보이는 것
    - 웹 페이지가 디바이스에 맞는 적절한 UX/UI로 표현되는 형태
    
4. SPA (Single Page Application)
    - Web App과 함께 자주 등장할 용어 SPA
    - 이전까지는 사용자의 요청에 적절한 페이지 별 template을 반환
    - SPA는 서버에서 최초 1장의 HTML만 전달받아 모든 요청에 대응하는 방식을 의미
        - 어떻게 한 페이지에 모든 요청에 대응할 수 있을까?
        - **CSR** (Client Side Rendering) 방식으로 요청을 처리하기 때문
    
5. [참고] SSR (Server Side Rendering) 이란?
    
    
    - 기존의 요청 처리 방식은 SSR
    - Server가 사용자의 요청에 적합한 HTML을 렌더링하여 제공하는 방식
    - 전달 받은 새 문서를 보여주기 위해 브라우저는 새로고침을 진행
    
    ```html
    {% extends 'base.html' %}
    
    {% block content %}
      <h1>User List Page</h1>
      {% for user in users %}
        <p>{{ user.pk }}</p>
        <p>{{ user.username }}</p>
        <p>{{ user.password }}</p>
        <hr>
      {% empty %}
        <p>아직 가입한 유저가 없습니다.</p>
      {% endfor %}
    {% endblock content %}
    ```
    
    ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/7112e242-575a-410a-8bc4-0ae06667730b/Untitled.png)
    
6. CSR (Client Side Rendering) 이란?
    - 최초 한 장의 HTML을 받아오는 것은 동일
        - 단, server로부터 최초로 받아오는 문서는 빈 html 문서
    
    ```html
    <body>
      <noscript>
        <strong>We're sorry but ...
        </strong>
      </noscript>
      <div id="app"></div>
      <!-- built files will be auto injected -->
    </body>
    ```
    
    - 각 요청에 대한 대응을 JavaScript를 사용하여 필요한 부분만 다시 렌더링
        - 1) 새로운 페이지를 서버에 **AJAX**로 요청
        - 2) 서버는 화면을 그리기 위해 필요한 데이터를 JSON 방식으로 전달
        - 3) **JSON** 데이터를 JavaScript로 처리, DOM 트리에 반영(렌더링)
        
        ```jsx
        axios.get(
            HOST_URL,
            {
              headers:{
                Authorization: `Token ${key}`
              }
            }
          )
          .then(res => {
            this.todos = res.data
          })
          .catch(err => console.log(err))
        ```
        
    
    ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/42e6796e-6801-4a74-8453-097a95456f7c/Untitled.png)
    
7. 왜 CSR 방식을 사용하는 걸까?
    - 1) 모든 HTML 페이지를 서버로부터 받는 것이 아니기 때문
        - 클라이언트 - 서버간 통신 즉, 트래픽이 감소
        - 트래픽이 감소한다 = 응답 속도가 빨라진다.
    - 2) 매번 새 문서를 받아 새로고침하는 것이 아니라 팔요한 부분만 고쳐 나가므로 각 요청이 끊김없이 진행
        - SNS에서 추천을 누를 때마다 첫 페이지로 돌아간다 = 끔찍한 App!
        - 요청이 자연스럽게 진행이 된다 = UX 향상
    - 3) BE와 FE의 작업 영역을 명확하게 분리할 수 있음
        - 각자 맡은 역할을 명확히 분리한다 = 협업이 용이해짐
    
8. CSR은 만능일까?
    - 첫 구동 시 필요한 데이터가 많으면 많을수록 최초 작동 시작까지 오랜 시간이 소요
    - Naver, Netflix, Disney+ 등 모방일에 설치된 Web-App을 실행하게 되면 잠깐의 로딩 시간이 필요
    
    - **검색 엔진 최적화**(SEO. Search Engine Optimization)가 어려움
        - 서버가 제공하는 것은 텅 빈 HTML
        - 내용을 채우는 것은 AJAX 요청으로 얻은 JSON 데이터로 클라이언트(브라우저)가 진행
    - 대체적으로 HTML에 작성된 내용을 기반으로 하는 검색 엔진에 빈 HTML을 공유하는 SPA 서비스가 노출되기는 어려움
    
9. [참고] SEO(Search Engline Optimization)
    - google, bing과 같은 검색 엔진 등에 내 서비스나 제품 등이 효율적으로 검색 엔진에 노출되도록 개선하는 과정을 일컫는 작업
    - **검색** = 각 사이트가 운용하는 검색 엔진에 의해 이루어지는 작업
    - **검색 엔진** = 웹 상에 존재하는 가능한 모든 정보들을 긁어 모으는 방식으로 동작
        - 정보의 대상은 주로 HTML에 작성된 내용
        - JavaScript가 실행된 이후의 결과를 확인하는 과정이 없음
    
    - 최근에는 SPA, 즉 CSR로 구성된 서비스의 비중이 증가
        - SPA 서비스도 검색 대상으로 넓히기 위해 JS를 지원하는 방식으로 발전
    - 단, 단순 HTML만을 분석하는 것보다 몇 배의 리소스가 필요한 작업이기에 여전히 CSR의 검색 엔진 최적화 문제가 모두 해결된 것은 아님
    
10. CSR vs SSR
    - CSR과 SSR은 흑과 백이 아님
        - 내 서비스에 적합한 렌더링 방식을 적절하게 활용할 수 있어야 함
    - SPA 서비스에서도 SSR을 지원하는 Framework도 발전하고 있음
        - Vue의 Nuxt.js
        - React의 Next.js
        - Angular Universal 등
    
11. 여러가지 Front-end Framework
    - Front-end Framework == HTML + CSS + JS 를 더 편하게 작업하기 위한 툴
        - React, Angular, Svelte, **Vue** 등
    - 2022년 Front-end Framework 인기도
        
        ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/8c20d097-6b6c-45c3-8169-97e56e77a4e6/Untitled.png)
        
    
12. 그럼 이런 프레임워크를 꼭 써야 할까?
    - No, 더 쉽게 개발하기 위해서 사용하는 것
    - 실제로 Github는 이러한 Front-end Framework를 사용하지 않음
    - 하지만 **대부분의 기업**에서는 생산성과 협업을 위해 Framework를 사용해서 개발

### **2. Why Vue**

1. 왜 우리는 Vue를 배울까?
    - 쉽다.
    - Vue는 타 Framework에 비해 입문자가 시작하기에 좋은 Framework
    - 왜 Vue는 상대적으로 낮은 진입 장벽을 가질 수 있었을까?
    
    - Vue를 발표한 개발자 Evan You
    - 학사 - 미술, 미술사 / 석사 - 디자인 & 테크놀로지 전공
    - 구글의 Angular 개발자 출신
        - Vue는 타 Framework에 비해 입문자가 시작하기 좋은 Framework
        - Angular보다 **가볍고, 간편하게 사용할 수 있는** Framework를 만들기 위해 퇴사
        - 2014년 **Vue** 발표
    
2. Vue 국내/외 실용 사례
    
    ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/6be26124-77d1-47e8-8bd2-80c0fcf46361/Untitled.png)
    
3. Vue는 정말 쉬울까?
    
    
    - Vue 구조는 매우 직관적임
    - FE Framework를 빠르고 쉽게 학습하고 활용 가능
    - 추후 필요하다면, 다른 FE Framework 학습 시 빠르게 적응 가능
    
    ```html
    // 01_vue_intro.vue
    
    <template>
      <!-- HTML -->
      <div>
        <p>Hello :)</p>
      </div>
    </template>
    
    <script>
      // JavaScript
    </script>
    
    <style>
      /* CSS */
      p {
        color: black;
      }
    </style>
    ```
    
4. Vue 없이 코드 작성하기
    - 입력 받을 값을 **name** 뒤에 출력하기
    - `02_html_only.html`에서 진행
        
        ```html
        // 02_html_only.html
        
        <!DOCTYPE html>
        <html lang="en">
          ...
          <body>
            <div id="app">
              <p id="name">name : </p>
              <input id="inputName" type="text">
            </div>
        
            <script>
              // CODE HERE
            </script>
          </body>
        </html>
        ```
        
    
    - 1) input tag 선택
    - 2) P tag 선택
    - 3) addEventListener 추가
        
        ```html
        // 02_html_only.html
        
        <body>
          <div id="app">
            <p id="name">name : </p>
            <input id="inputName" type="text">
          </div>
        
          <script>
            // CODE HERE
            const name = document.querySelector('#name')
            const input = document.querySelector('$inputName')
            input.addEventListener('input', function (e) {
              ...
            })
          </script>
        </body>
        ```
        
    
    - 입력 받은 데이터를 p tag에 추가하려고 한다면?
    - 기존에 가지고 있었던 text도 신경 써야함
        - data를 관리하기 위한 추가 작업이 필요함
            
            ```jsx
            // 02_html_only.html
            
            input.addEventListener('input', function (e) {
              name.innerText = name.innerText + e.target.value
            })
            ```
            
    
5. Vue CDN
    - Vue로 작업을 시작하기 위하여 CDN을 가져와야 함
    - Django == Python Web Framework
        - pip install
    - Vue === JS Front-end Framework
        - Bootstrap에서 사용하였던 CDN 방식 제공
    
    - Vue CDN을 위하여 **Vue2 공식 문서** 접속
        - [https://v2.vuejs.org/](https://v2.vuejs.org/)
    - 1) Getting Started
    - 2) Installation
    - 3) Development version CDN 복사
        
        ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/1ed043d3-de07-46a7-9434-5085f47ba927/Untitled.png)
        
    
6. Vue로 코드 작성하기
    - 입력 받은 값을 **name** 뒤에 출력하기
    - `03_html_vue.html` 에서 진행
        
        ```html
        // 03_html_vue.html
        
        <!DOCTYPE html>
        <html lang="en">
          ...
          <body>
            <div id="app">
              <p id="name">name : </p>
              <input id="inputName" type="text">
            </div>
        
            <script>
              // CODE HERE
            </script>
          </body>
        </html>
        ```
        
    
    - 1) Vue CDN 가져오기
    - 2) Vue instance 생성
        - Vue instance - 1개 Object
        - 정해진 속성명을 가진 Object
    - 3) **el**, **data** 설정
        - **data**에 관리할 속성 정의
    - 4) 선언적 렌더링 **{{  }}**
        - Vue data를 화면에 렌더링
    
    ```jsx
    // 03_html_vue.html
    
    <body>
      <div id="app">
        <p id="name">name : {{ message }}</p>
        <input type="text">
      </div>
      <!-- Vue CDN 생략 -->
      <script>
        const app = new Vue({
          el: '#app',
          data: {
            message: '',
          },
        })
      </script>
    </body>
    ```
    
    - 5) input tag에 **v-model** 작성
        - input에 값 입력 → Vue data 반영
        - Vue data → Dom 반영
        
        ```jsx
        // 03_html_vue.html
        
        <body>
          <div id="app">
            <p id="name">name : {{ message }}</p>
            <input type="text" v-model="message">
          </div>
          <!-- Vue CDN 생략 -->
          <script>
            const app = new Vue({
              el: '#app',
              data: {
                message: '',
              },
            })
          </script>
        </body>
        ```
        
    
7. [참고] Dev Tools 확인
    - Vue devtools에서 data 변경 → DOM 반영
    - 눈에 보이는 화면을 조작하는 것이 아닌 Vue가 가진 data를 조작
        
        ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/3dd84aa8-447d-498c-bffa-e06de1251a1d/Untitled.png)
        
    
8. Facebook 예시
    - 한 명의 유저가 이름을 변경한다면 화면에서 조작해야 할 영역이 매우 많음
        
        ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/fb2225e1-3eb4-4370-93ce-0c7190811a13/Untitled.png)
        
    
    - Vanilla JS만으로 모든 데이터를 조작한다면?
        
        
        ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/578db9ab-d2d9-4a45-a37e-5d6a62458b8d/Untitled.png)
        
        ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/7448f7a4-55e0-4f37-a4d3-a97944befcc4/Untitled.png)
        
        → 불필요한 코드의 반복
        
    
    - Vue를 통해 데이터를 관리한다면? = 변경 사항도 한 번에 반영
        
        
        ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/87ffaf1e-3ece-4209-98ae-12387f260de7/Untitled.png)
        
        ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/ab7850b6-d344-493f-9fde-7d86db99113b/Untitled.png)
        
        → 하나의 Data로 관리
        

### 3**. Vue 2 vs Vue 3**

- Vue3
    - 2022년 02월부터 vue 프레임워크의 기본 버전이 3버전으로 전환
    - 대체적인 설정들이 Vue3을 기본으로 적용되어 있음
        - ex) 공식문서, CDN, npm 등
    
- Vue2
    - 여전히 vue2가 많이 사용됨 (legacy code)
    - 사용된 기간이 긴 만큼 상대적으로 많은 문서의 양, 참고자료, 질문/답변
    - 안정적인 측면에서는 아직 vue2가 우세한 편

---