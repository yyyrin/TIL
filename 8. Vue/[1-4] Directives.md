# **Directives**

1. Directives 기본 구성
    - v-접두사가 있는 특수 속성에서는 값을 할당할 수 있음
        - 값에는 JS 표현식을 작성할 수 있음
    - directive의 역할은 **표현식의 값**이 **변경**될 때 **반응적**으로 DOM에 적용하는 것
    
    ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/d314ead2-9206-48cd-882a-f4ad74de1500/Untitled.png)
    
    - **:**  → 을 통해 전달인자를 받을 수 있음
    - **.**  → 으로 표기되는 특수 접미사 - directive를 특별한 방법으로 바인딩 해야 함
    
2. 새 Vue istance 생성
    
    
    - `06_basic_of_sytax.html`에서 진행
    - 각각의 instance들은 연결된 DOM element에만 영향을 미침
    - 연결되지 않은 DOM이 Vue의 영향을 받지 않았던 것과 동일한 상황
    
    ```jsx
    <div id="app">
      ...
    </div>
    
    <div id="app2">
    </div>
    
    <!-- Vue CDN -->
    <script>
      ...
      const app2 = new Vue({
        el: '#app2',
      })
    </script>
    ```
    
3. v-text
    
    
    - Template Interpolation과 함께 가장 기본적인 바인딩 방법
    - {{  }} 와 동일한 역할
        - 정확히 동일한 역할인 것은 아님
    
    ```jsx
    <div id="app2">
      <p v-text="message"></p>
      <!-- 같음 -->
      <p>{{ message }}</p>
    </div>
    
    <!-- Vue CDN -->
    <script>
      const app2 = new Vue({
        el: '#app2',
        data: {
          message: 'Hello!',
        }
      })
    </script>
    ```
    
4. v-html
    
    
    - RAW HTML을 표현할 수 있는 방법
    - 단, 사용자가 입력하거나 제공하는 컨텐츠에는 **졀대 사용 금지**
        - XSS 공격 참고
    
    ```jsx
    <div id="app2">
      ...
      <p v-html="html"></p>
    </div>
    
    <!-- Vue CDN -->
    <script>
      const app2 = new Vue({
        el: '#app2',
        data: {
          ...
          html: '<a href="https://www.google.com">GOOGLE</a>'
        }
      })
    </script>
    ```
    
5. v-show
    
    
    - 표현식에 작성된 값에 따라 element를 보여 줄 것인지 결정
        - boolean 값이 변경 될 때마다 반응
    - 대상 element의 display 속성을 기본 속성과 none으로 toogle
    - 요소 자체는 항상 DOM에 렌더링 됨
    
    ```jsx
    <div id="app3">
      <p v-show="isActive">보이니? 안 보이니?</p>
    </div>
    
    <!-- Vue CDN -->
    <script>
      const app3 = new Vue({
        el: '#app2',
        data: {
          isActive: false
        }
      })
    </script>
    ```
    
    - 바인딩 된 isActive의 값이 false이므로 첫 방문 시 p tag는 보이지 않음
        - vue dev tools에서 isActive 변경 시 화면에 출력
        - 값을 false로 변경 시 다시 사라짐
    - 화면에서만 사라졌을 뿐, Dom에는 존재한다.
        - display 속성이 변경되었을 뿐
    
    ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/b235745d-795d-43cf-acaa-5a3ed99482bd/Untitled.png)
    
6. v-if
    
    
    - v-show와 사용 방법은 동일
    - isActive의 값이 변경 될 때 반응
    - 단, 값이 false인 경우 **DOM에서 사라짐**
    - `v-if` `v-else-if` `v-else`형태로 사용
    
    ```jsx
    <div id="app3">
      ...
      <p v-if="isActive">안 보이니? 보이니?</p>
    </div>
    
    <!-- Vue CDN -->
    <script>
      const app3 = new Vue({
        el: '#app3',
        data: {
          isActive: false
        }
      })
    </script>
    ```
    
7. v-show VS v-if
    - `v-show` (Expensive inital load, cheap toggle)
        - 표현식 결과와 관계 없이 렌더링 되므로 초기 렌더링에 필요한 비용은 v-if 보다 높을 수 있음
        - display 속성 변경으로 표현 여부를 판단하므로 렌더링 후 toggle 비용은 적음
    - `v-if` (Cheap initial load, expensive toggle)
        - 표현식 결과가 false인 경우 렌더링조차 되지 않으므로 초기 렌더링 비용은 v-show 보다 낮을 수 있음
        - 단, 표현식 값이 자주 변경되는 경우 잦은 재 렌더링으로 비용이 증가할 수 있음
    
8. v-for
    
    
    - `07_basic_of_sytax_2.html`에서 진행
    - **for .. in ..** 형식으로 작성
    - 반복한 데이터 타입에 모두 사용 가능
    - index를 함께 출력하고자 한다면 **(char, index)** 형태로 사용 가능
    
    ```jsx
    <div id="app">
      <h2>String<h2>
      <!-- <div v-for="char in myStr"> -->
      <div v-for="(char, index) in myStr">
        <p>{{ index }}번째 문자열 {{ char }}</p>
      </div>
    </div>
    <!-- Vue CDN -->
    <script>
      const app3 = new Vue({
        el: '#app3',
        data: {
          myStr: 'Hello, World!'
        }
      })
    </script>
    ```
    
    - 배열 역시 문자열과 동일하게 사용 가능
    - 각 요소가 객체라면 **dot notation**으로 접근할 수 있음
    
    ```jsx
    <h2>Array</h2>
    <div v-for="(item, index) in myArr2">
      <p>{{ index }}번째 아이템</p>
      <p>{{ item.name }}</p>
    </div>
    
    <script>
      const app = new Vue({
        data: {
          myArr2: [
            { id: 1, name: 'python' },
            ...
          ],
        }
      })
    </script>
    ```
    
    - 객체 순회 시 value가 할당되어 출력
    - 2번째 변수 할당 시 key 출력 가능
    - value가 key 보다 먼저! 쓰여야 한다!
    
    ```jsx
    <h2>Object</h2>
    <div v-for="(value, key) in myObj">
      <p>{{ key }} : {{ value }}</p>
    </div>
    
    <script>
      const app = new Vue({
        data: {
          myObj: {
            name: 'aiden',
            age: 100
          },
        }
      })
    </script>
    ```
    
9. [참고] 특수 속성 key
    
    
    - **“v-for 사용 시 반드시 key 속성을 각 요소에 작성”**
    - 주로 **v-for directive** 작성 시 사용
    - vue 화면 구성 시 이전과 달라진 점을 확인 하는 용도로 활용
        - 따라서 key가 중복되어서는 안됨
    - 각 요소가 고유한 값을 가지고 있지 않다면 생략할 수 있음
    - 배열의 경우, 문자열과 index 섞어서 씀
    - 객체에서 key값 쓰는 게 일반적
    
    ```jsx
    <div
      v-for="(item, index) in myArr2"
      :key="`arry-${index}`"
    >
    <p>{{ index.id }}번째 아이템</p>
    <p>{{ item.name }}</p>
    </div>
    ```
    
10. v-on
    
    
    - `08_basic_of_sytax_3.html`에서 진행
    - **:** 을 통해 전달받은 인자를 확인
    - 값으로 JS 표현식 작성
    - addEventListener의 첫 번째 인자와 동일한 값들로 구성
    - 대기하고 있던 이벤트가 발생하면 할당된 표현식 실행
    
    ```jsx
    <div id="app">
      <button v-on:click="number++">
        increase Number
      </button>
      <p>{{ number }}</p>
    </div>
    
    <!-- Vue CDN -->
    <script>
      const app = new Vue({
        el: '#app',
        data: {
          number: 0,
        },
      })
    </script>
    ```
    
    - method를 통한 data 조작도 가능
    - method에 인자를 넘기는 방법은 일반 함수를 호출할 때와 동일한 방식
    - **‘ : ‘**을 통해 전달된 인자에 따라 특별한 modifiers (수식어)가 있을 수 있음
        - ex) **v-on:keyup.enter** 등
        - vue 2 가이드 > api > v-on 파트 참고
    - **‘ @ ‘** shortcut 제공 🔥
        - ex) `@keyup.click`
    
11. v-bind
    
    
    - HTML 기본 속성에 Vue data를 연결
    - class의 경우 다양한 형태로 연결 가능
        - **조건부 바인딩**
            - {’class Name’: ‘조건 표현식’}
            - 삼항 연산자도 가능
        - **다중 바인딩**
            - [’JS 표현식’, ‘JS 표현식’, …]
            
    
    ```jsx
    <div id="app2">
      <a v-bind:href="url">Go To GOOGLE</a>
    </div>
    
    <!-- Vue CDN -->
    <script>
      const app2 = new Vue({
        el: '#app2',
        data: {
          url: 'https://www.google.com/',
        },
      })
    </script>
    ```
    
    - Vue data의 변화에 반응하여 DOM에 반영하므로 상황에 따라 유동적 할당 가능
    - **‘ : ‘** shortcut 제공
        - ex) `:class` 등
        - v-for에서 사용하였던 `:key`는 v-bind의 shortcut을 활용한 것
    
12. v-model
    
    
    - `09_basic_of_sytax_4.html`에서 진행
    - Vue instance와 DOM의 **양방향 바인딩**
    - Vue data 변경 시 v-model로 연결된 사용자 입력 element에도 적용
    - 한국어는 양방향 바인딩이 한 박자 느리다.
        
        ```jsx
        // 대신 이걸로 사용해라
        
        <h2>1. Input -> Data</h2>
        <h3>{{ myMessage }}</h3>
        <input @input="onInputChange" type="text">
        ```
        
    
    ```jsx
    <div id="app">
      <h3>{{ myMessage }}</h3>
      <input v-model="myMessage" type="text">
      <hr>
    </div>
    
    <!-- Vue CDN -->
    <script>
      const app = new Vue({
        el: '#app',
        data: {
          myMessage2: '',
        },
      })
    </script>
    ```
    

---