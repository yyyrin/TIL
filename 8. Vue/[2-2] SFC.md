# **SFC**

### 1. Component

1. Component
    - UI를 독립적이고 재사용 가능한 조각들로 나눈 것
        - 즉, 기능별로 분화한 코드 조각
    - CS에서는 다시 사용할 수 있는 범용성을 위해 개발된 소프트웨어 구성 요소를 의미
    - 하나의 app을 구성할 때 중첩된 컴포넌트들의 tree로 구성하는 것이 보편적임
        - Web시간에 배운 HTML 요소들의 중첩을 떠올려 보자!
            - Body tag를 root node로 하는 tree의 구조이다
            - 마찬가지로, Vue에서는 src/App.vue를 root node로 하는 tree의 구조를 가짐
    - 컴포넌트는 유지보수를 쉽게 만들어 줄 뿐만 아니라 재사용성의 측면에서도 매우 강력한 기능을 제공
    
    - 우리가 사용하는 웹 서비스 여러 개도 컴포넌트로 이루어져 있음
    - 하나의 컴포넌트를 만들어~면 반복되는 UI를 쉽게 처리할 수 있음
    
    ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/3440e878-526f-481a-a1cc-b9b30751068d/Untitled.png)
    
2. Django에서의 예시
    - 우리는 base.html과 index.html을 분리하여 작성하였지만, 하나의 화면으로 볼 수 있다
        - 즉, 한 화면은 여러 개의 컴포넌트로 이루어질 수 있음
            
            ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/b43ea8c1-8b76-4856-9d94-25723132d3bb/Untitled.png)
            
    
    - base.html을 변경하면 이를 extends하는 모든 화면에 영향을 미침
        - 유지 보수를 쉽게 해줌
            
            ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/0a866b7d-04e6-4ebd-bc6c-c0e17bfdcf13/Untitled.png)
            
    
    - index.html에서 for문을  통해 여러 게시글들을 하나의 형식에 맞추어서 출력해줌
        - 형식을 재사용하고 있었음
            
            ```html
            {% for article in articles %}
              <p>글 번호 : {{ article.pk }}</p>
              <p>제목 : {{ article.title }}</p>
              <p>내용 : {{ article.content }}</p>
              <a href="{% url 'articles:detail' article.pk %}">상세 페이지</a>
              <hr>
            {% endfor %}
            ```
            
            → 우리는 너무 자연스럽게 컴포넌트 기반으로 개발을 진행하고 있었다!
            
    
3. Component based architecture 특징
    - 관리가 용이
        - 유지/보수 비용 감소
    - 재사용성
    - 확장 가능
    - 캡슐화
    - 독립적

### 2. SFC

1. component in Vue
    - 그렇다면 Vue에서 말하는 component란 무엇일까?
        - 이름이 있는 재사용 가능한 Vue instance
    - 그렇다면 Vue instance란?
        - 앞서 수업에서 사용한 `new Vue()`로 만든 인스턴스
    
2. SFC (Single File Component)
    - 하나의 `.vue` 파일이 하나의 **Vue instance**이고, 하나의 **컴포넌트**이다
        - 즉, Single File Component
    - Vue instance에서는 HTML, CSS, JavaScript 코드를 한번에 관리
        - 이 Vue instance를 기능 단위로 작성하는 것이 핵심!
    - 컴포넌트 기반 개발의 핵심 기능
    
3. 정리
    - HTML, CSS 그리고 JavaScript를 .vue라는 확장자를 가진 파일 안에서 관리하며 개발
    - 이 파일을 Vue instance, 또는 Vue component라고 하며, 기능 단위로 작성
    - Vue CLI가 Vue를 Component based하게 사용하도록 도와줌

### 3. Vue component

1. Vue component 구조
    
    ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/46371bc9-8a19-4df2-9b7e-083a9c73e463/Untitled.png)
    
    - 템플릿(HTML)
        - HTML의 body 부분
        - 눈으로 보여지는 요소 작성
        - 다른 컴포넌트를 HTML 요소처럼 추가 가능
        
    - 스크립트 (JavaScript)
        - JavaScript 코드가 작성되는 곳
        - 컴포넌트 정보, 데이터, 메서드 등 vue 인스턴스를 구성하는 대부분이 작성됨
    
    - 스타일(CSS)
        - CSS가 작성되며 컴포넌트의 스타일을 담당
    
2. Vue component 구조 정리
    - 컴포넌트들이 tree 구조를 이루어 하나의 페이지를 만듦
    - root에 해당하는 최상단의 component가 `APP.vue`
    - 이 `App.vue`를 `index.html`과 연결
    - 결국 index.html 파일 하나만을 rendering
        - 이게 바로 SPA

### 4. Vue component 실습

1. 현재 구조
    - Vue CLI를 실행하면 이미 `HelloWorld.vue`라는 컴포넌트가 생성되어 있고 App.vue에 등록되어 사용되고 있음
        - `npm run serve` 명령어를 진행했을 때 나온 화면의 대부분이 `HelloWorld.vue`
    
    ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/bd105501-a2d2-42d4-bd4f-f03929fedb2e/Untitled.png)
    
    ```jsx
    // App.vue
    
    <template>
      <div id="app">
        <img alt="Vue logo" src="./assets/logo.png">
        <HelloWorld msg="Welcome to Your Vue.js App"/>
      </div>
    </template>
    ```
    
2. MyComponent.vue
    - 1) src/components/ 안에 생성
    - 2) script에 이름 등록
    - 3) template에 요소 추가
    
    ```jsx
    // MyComponent.vue
    
    <template>
      <div>
        <h1>This is my component</h1>
      </div>
    </template>
    
    <script>
    export default {
        name: 'MyComponent',  // 2) 파일 이름과 동일하게 작성
    }
    </script>
    
    <style>
    
    </style>
    ```
    
    - 주의) templates 안에는 **반드시 하나의 요소만** 추가 가능
        - 비어 있어도 안됨
        - 해당 요소 안에 추가 요소를 작성해야 함
    
3. component 등록 3단계
    - 1) 불러오기
    - 2) 등록하기
    - 3) 보여주기
    
    ```jsx
    // App.vue
    
    <template>
      <div id="app">
        <img alt="Vue logo" src="./assets/logo.png">
        <!-- 3. 보여주기 -->
        <HelloWorld msg="Welcome to Your Vue.js App"/>
      </div>
    </template>
    
    <script>
    // 1. 불러오기
    import HelloWorld from './components/HelloWorld.vue'
    
    export default {
      name: 'App',
      components: {
        // 2. 등록하기
        HelloWorld,
      }
    }
    </script>
    ```
    
4. component 등록 - 불러오기
    - `import {instance name} from {위치}`
    - instance name은 instance 생성 시 작성한 name
    - **@**는 src의 shortcut
    - **.vue** 생략 가능
        
        ```jsx
        // App.vue
        
        <script>
        import HelloWorld from './components/HelloWorld.vue'
        // 1. 불러오기
        // import MyComponent from './components/MyComponent.vue' (아래와 같은 코드)
        import MyComponent from '@/components/MyComponent'
        
        export default {
          name: 'App',
          components: {
            HelloWorld
          }
        }
        </script>
        ```
        
    
5. component 등록 - 등록하기
    
    ```jsx
    // App.vue
    
    <script>
    import HelloWorld from './components/HelloWorld.vue'
    // 1. 불러오기
    // import MyComponent from './components/MyComponent.vue' (아래와 같은 코드)
    import MyComponent from '@/components/MyComponent'
    
    export default {
      name: 'App',
      components: {
        HelloWorld,
        // 2. 등록하기
        MyComponent,
      }
    }
    </script>
    ```
    
6. component 등록 - 보여주기
    - 닫는 태그만 있는 요소처럼 사용
        
        ```jsx
        // App.vue
        
        <template>
          <div id="app">
            <img alt="Vue logo" src="./assets/logo.png">
            <!-- 3. 보여주기 -->
            <MyComponent/>
            <HelloWorld msg="Welcome to Your Vue.js App"/>
          </div>
        </template>
        ```
        
        - 로고와 기본 컴포넌트 사이에 위치
    
7. component 등록 결과
    
    ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/178ad2bd-7639-4df7-8e9f-8aa2cf42cae8/Untitled.png)
    
8. 자식 컴포넌트 작성
    - 이제 MyComponent의 자식 컴포넌트를 만들어보자
    - 자식 관계를 표현하기 위해 기존 My Component에 간단한 border를 추가
    
    ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/cc67ef25-25b6-41ba-8428-8ffce12d3e3c/Untitled.png)
    
    ```jsx
    // MyComponent.vue
    
    <template>
      <div class="border">
        <h1>This is my component</h1>
      </div>
    </template>
    
    <style>
      .border {
        border: solid;
      }
    </style>
    ```
    
    - src/components/ 안에 `MyComponentItem.vue` 생성
        
        ```jsx
        // MyChild.vue
        
        <template>
          <div>
            <h3>This is child component</h3>
          </div>
        </template>
        
        <script>
        export default {
          name: 'MyChild',
        }
        </script>
        ```
        
    - MyComponent에 MyChild 등록
        
        
        ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/133fcc95-e2ce-414c-b26f-476cc001f1a8/Untitled.png)
        
        ```jsx
        // MyComponent.vue
        
        <template>
          <div class="border">
            <h1>This is my component</h1>
            <MyChild/>
          </div>
        </template>
        
        <script>
        import MyChild from '@/components/MyChild'
        
        export default {
          name: 'MyComponent',
          // 2. 등록하기
          components: {
            MyChild,
          }
        }
        </script>
        ```
        
    
    - component의 재사용성
        
        
        ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/5a951ee2-f91c-44b8-9475-11e283b03680/Untitled.png)
        
        ```jsx
        // MyComponent.vue
        
        <template>
          <div class="border">
            <h1>This is my component</h1>
            <MyChild/>
            <MyChild/>
            <MyChild/>
          </div>
        </template>
        ```
        
        ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/7d4811e7-f2de-4f82-afec-db2a9abd93d5/Untitled.png)
        
        ```jsx
        // App.vue
        
        <template>
          <div id="app">
            <img alt="Vue logo" src="./assets/logo.png">
            <MyComponent/>
            <MyComponent/>
            <MyComponent/>
            <HelloWorld msg="Welcome to Your Vue.js App"/>
          </div>
        </template>
        ```
        
    
9. Vue component 마무리
    - 다음 실습을 위해 컴포넌트 정리
        
        ```jsx
        // MyComponent.vue
        
        <template>
          <div class="border">
            <h1>This is my component</h1>
            <MyChild/>
          </div>
        </template>
        ```
        
        ```jsx
        // App.vue
        
        <template>
          <div id="app">
            <img alt="Vue logo" src="./assets/logo.png">
            <MyComponent/>
            <HelloWorld msg="Welcome to Your Vue.js App"/>
          </div>
        </template>
        ```
        

---