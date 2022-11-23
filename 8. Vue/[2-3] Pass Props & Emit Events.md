# **Pass Props & Emit Events**

### 1. Data in components

1. Data in components
    - 우리는 정적 웹페이지가 아닌, 동적 웹페이지를 만들고 있음
        - 즉, 웹페이지에서 다뤄야 할 데이터가 등장
        - User data, 게시글 data, 등…
    - 한 페이지 내에서 같은 데이터를 공유 해야 함
        - 하지만 페이지들은 component로 구분이 되어있음
    - MyComponent에 정의된 data를 MyChild에서 사용하려면 어떻게 해야할까?
    
    - MyChild에도 똑같은 data를 정의
        - MyComponent의 data와 MyChild의 데이터가 동일한 data가 맞는가?
        - MyComponent의 data가 변경된다면 MyChild도 같이 변경이 될까?
        - 아니다. 각 Component는 독립적이므로 서로 다른 data를 갖게 될 것이다.
        - 그렇다면 완전히 동일한 data를 서로 다른 Component에서 보여주려면 어떻게 해야 할까?
    
    - 필요한 컴포넌트들끼리 데이터를 주고받으면 될까?
        
        ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/060f75fc-61b8-44f7-a0c3-fa3549666c06/Untitled.png)
        
        - 데이터의 흐름을 파악하기 힘듦
        - 개발 속도 저하
        - 유지보수 난이도 증가
    
    - 컴포넌트는 부모-자식 관계를 가지고 있으므로, 부모-자식 관계만 데이터를 주고받게 하자!
        
        ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/37821be8-275f-4b83-a7e1-f1fc2d99307e/Untitled.png)
        
        - 데이터의 흐름을 파악하기 용이
        - 유지 보수하기 쉬워
    
2. pass props & emit event
    - 부모 ⇒ 자식으로의 데이터의 이름
        - pass **props**의 방식
    - 자식 ⇒ 부모로의 데이터의 흐름
        - **emit** event의 방식

### 2. Pass Props

1. Pass Props
    - 요소의 속성(property)을 사용하여 데이터 전달
    - props는 부모(상위) 컴포넌트의 정보를 전달하기 위한 사용자 지정 특성
    - 자식(하위) 컴포넌트는 props 옵션을 사용하여 수신하는 props를 명시적으로 선언해야 함
    
2. props in HelloWorld
    - 사실, 우리는 Vue app은 이미 props를 사용하고 있었다!
    - Vue CLI를 설치할 때 만들어주었던 App.vue의 HelloWorld 컴포넌트를 살펴보면 msg라는 property가 작성되어 있음
        
        ```jsx
        // App.vue
        
        <template>
          <div id="app">
            <img alt="Vue logo" src="./assets/logo.png">
            <MyComponent/>
            <HelloWorld msg="Welcome to Your Vue.js App"/>
          </div>
        ```
        
    
    - HelloWorld.vue에서 msg를 사용한 것을 확인할 수 있음
        
        ```jsx
        // HellowWorld.vue
        
        <template>
          <div class="hello">
            <h1>{{ msg }}</h1>
            <p>
        ```
        
    - App.vue에서 property로 넘긴 msg가  출력되는 것을 확인할 수 있음
        
        ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/451b3619-dd81-47bf-9e94-aebf4baf9867/Untitled.png)
        
    
3. props in HelloWorld 정리
    - App.vue의 <HelloWorld/> 요소에서 **msg=”~”** 라는 property를 설정하였고, 하위 컴포넌트인 HelloWorld는 자신에게 부여된 msg property를 template에서 **{{ msg }}**의 형태로 사용한 것
    
4. props in HelloWorld 실습
    - msg property의 value를 바꾸면 화면에 보이는 문장이 달리짐
        
        
        ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/6fc04ea4-79f6-4c73-943b-c37f48392aa6/Untitled.png)
        
        ```jsx
        // App.vue
        
        <template>
          <div id="app">
            <img alt="Vue logo" src="./assets/logo.png">
            <MyComponent/>
            <HelloWorld msg="App.vue에서 작성한 msg입니다!"/>
          </div>
        </template>
        ```
        
    
5. Pass Props
    - 이렇게 부모 ⇒ 자식으로의 data 전달 방식을 pass props라고 함
    - 정적인 데이터를 전달하는 경우 static props라고 명시하기도 함
    - 요소에 속성을 작성하듯이 사용 가능하여, **prop-data-name=”value”**의 형태로 데이터를 전달
        - 이때 속성의 키 값은 kebab-case를 사용
    - (케밥으로 보내고, 카멜로 받는다!!!)
    
    - Prop 명시
    - 데이터를 받는 쪽, 즉 하위 컴포넌트에서도 props에 대해 명시적으로 작성 해주어야 함
    - 전달받은 props를 type과 함께 명시
        
        ```jsx
        // HelloWorld.vue
        
        <script>
        export default {
          name: 'HelloWorld',
          props: {
            msg: String
          }
        }
        </script>
        ```
        
    - 컴포넌트를 문서화할 뿐만 아니라, 잘못된 타입이 전달하는 경우 브라우저의 자바스크립트 콘솔에서 사용자에게 경고
    - [https://v2.vuejs.org/v2/guide/components-props.html#Prop-Validation](https://v2.vuejs.org/v2/guide/components-props.html#Prop-Validation)
    
6. MyComponent to MyChild
    
    ```jsx
    // MyComponent.vue
    
    <template>
      <div class="border">
        <h1>This is my component</h1>
        <MyChild static-props="component에서 child로"/>
      </div>
    </template>
    ```
    
    ```jsx
    // MyChild.vue
    
    <template>
      <div>
        <h3>This is child component</h3>
        <p>{{ staticProps }}</p>
      </div>
    </template>
    
    <script>
    export default {
      name: 'MyChild',
      props: {
        staticProps: String,
      }
    }
    </script>
    ```
    
    ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/553a51bb-cf8c-440c-aab6-4ebb869a5cbd/Untitled.png)
    
7. Pass Props convention
    - 부모에게 넘겨주는 props
        - **kebab-case**
        - (HTML 속성명은 대소문자를 구분하지 않기 때문)
    - 자식에서 받는 props
        - **camelCase**
    - 부모 템플릿(html)에서 kebab-case로 넘긴 변수를 자식의 스크립트(vue)에서 자동으로 camelCase로 변환하여 인식함
    
8. Dynamic props
    - 변수를 props로 전달할 수 있음
    - v-bind directive를 사용해 데이터를 동적으로 바인딩
    - 부모 컴포넌트의 데이터가 업데이트 되면 자식 컴포넌트로 전달되는 데이터 또한 업데이트 됨
    
9. Dynamic props 실습
    
    ```jsx
    // MyComponent.vue
    
    <template>
      <div class="border">
        <h1>이거는 내가 만든 새로운 컴포넌트다!</h1>
        <MyComponentItem
        static-props="MyComponent에서 보낸 데이터"
        :dynamic-props="dynamicProps"
        />
      </div>
    </template>
    
    <script>
    // 1. 불러오기
    import MyComponentItem from '@/components/MyComponentItem'
    
    export default {
      ...
      data: function () {
        return {
          dynamicProps: '이건 동적인 데이터!',
        }
      }
    }
    </script>
    ```
    
    ```jsx
    // MyChild.vue
    
    <template>
      <div>
        <h3>This is child component</h3>
        <p>{{ staticProps }}</p>
        <p>{{ dynamicProps }}</p>
      </div>
    </template>
    
    <script>
    export default {
      name: 'MyChild',
      props: {
        staticProps: String,
        dynamicProps: String,
      }
    }
    </script>
    ```
    
10. 컴포넌트의 data 함수
    - 각 vue 인스턴스는 같은 data 객체를 공유하므로 새로운 data 객체를 반환(return)하여 사용해야 함
        
        ```jsx
        data: function () {
          return {
            // component's data in here
          }
        }
        
        // data () {
        //   return {
        //     // component's data in here
        //   }
        // }
        ```
        
    - [https://v2.vuejs.org/v2/guide/components.html#data-Must-Be-a-Function](https://v2.vuejs.org/v2/guide/components.html#data-Must-Be-a-Function)
    
11. Pass Props
    - **:dynamic-props=”dynamicProps”** 는 앞의 key값(**dynamic-props**)이란 이름으로 뒤의 “ “ 안에 오는 데이터(**dynamicProps**)를 전달하겠다는 뜻
    - 즉, **:my-props=”dynamicProps”**로 데이터를 넘긴다면, 자식 컴포넌트에서 **myProps**로 데이터를 받아야 함
    
    ```jsx
    // MyComponent.vue
    
    <template>
      <div class="border">
        <h1>This is my component</h1>
        <MyChild
        static-props="component에서 child로"
        :my-props="dynamicProps"
        />
      </div>
    </template>
    ```
    
    ```jsx
    // MyChild.vue
    
    <template>
      <div>
        <h3>This is child component</h3>
        <p>{{ staticProps }}</p>
        <p>{{ myProps }}</p>
      </div>
    </template>
    
    <script>
    export default {
      name: 'MyChild',
      props: {
        staticProps: String,
        myProps: String,
      }
    }
    </script>
    ```
    
    - v-bind로 묶여있는 **“ “** 안의 구문은 JavaScript의 구문으로 볼 수 있음
        - 따라서 **dynamicProps** 라고 하는 변수에 대한 data를 전달할 수 있는 것
    
    - 그렇다면 숫자를 props로 전달하기 위해서 다음 두 방법 중 어떤 게 맞을까?
        
        ```jsx
        // 1
        <SomeComponent num-props="1"/>
        
        //2
        <SomeComponent :num-props="1"/>
        ```
        
        - 첫 번째 방식은 static props로 string으로써의 “1”을 전달
        - 두 번째 방식은 dynamic props로 숫자로써의 1을 전달
    
12. **단방향 데이터 흐름**  🔥
    - 모든 props는 부모에서 자식으로 즉 아래로 단방향 바인딩을 형성
    - 부모 속성이 업데이트되면 자식으로 흐르지만 반대 방향은 아님
        - 부모 컴포넌트가 업데이트될 때마다 자식 컴포넌트의 모든 prop들이 최신 값으로 새로고침 됨
    
    - 목적
        - 하위 컴포넌트가 실수로 상위 컴포넌트 상태를 변경하여 앱의 데이터 흐름을 이해하기 힘들게 만드는 것을 방지
    - 하위 컴포넌트에서 prop를 변경하려고 시도해서는 안되며 그렇게 되면 Vue는 콘솔에서 경고를 출력함
    - (자식에서는 Read Only다!)

### 3. Emit Event

1. Emit Event
    - 부모 컴포넌트에서 자식 컴포넌트로 데이터를 전달할 때는 **이벤트를 발생 시킴**
    - 이벤트를 발생시키는 게 어떻게 데이터를 전달하는 것이냐?
        - 1) 데이터를 이벤트 리스너의 **콜백함수의 인자로 전달**
        - 2) 상위 컴포넌트는 해당 **이벤트를 통해 데이터를 받음**
    - (자식 - 이벤트 발생 / 부모 - 이벤트 처리)
    
2. $emit
    - `$emit` 메서드를 통해 부모 컴포넌트에 이벤트를 발생
        - `$emit('event-name')` 형식으로 사용하며 부모 컴포넌트에 **event-name**이라는 이벤트가 발생했다는 것을 알림
        - 마치 사용자가 마우스 클릭을 하면 click 이벤트가 발생하는 것처럼 `$emit(’event-name’)`가 실행되면 `event-name` 이벤트가 발생하는 것
    - 참고)  **$**
        - JavaScript는 변수에 _ , $ 두 개의 특수문자를 사용 가능
        - 이때, 기존에 사용하던 변수, 메서드들과 겹치지 않게 하기 위해서 vue는 $emit를 이벤트 전달을 위한 방식으로 택하였다.
    - `@click="onClick"` vs `@click.native="onClick"`
        - 컴포넌트 요소에서 사용하려면 **.native** 를 붙여서 native 이벤트임을 알려야 한다.
            
            ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/12f0c3a5-2e3c-44d2-b30e-cdf5c2fd4be9/Untitled.png)
            
    
3. Emit Event
    - 1) 자식 컴포넌트에 버튼을 만들고 클릭 이벤트를 추가
    - 2) `$emit`을 통해 부모 컴포넌트에게 `child-to-parent` 이벤트를트리거
        
        ```jsx
        // MyChild.vue
        
        <template>
          <div>
            ...
            <button @click="childToParent">클릭!</button>
          </div>
        </template>
        
        <script>
        export default {
          ...
          methods: {
            childToParent: function () {
              this.$emit('child-to-parent')
            }
          },
        }
        </script>
        ```
        
    
    - emit된 이벤트를 상위 컴포넌트에서 청취 후 핸들러 함수 실행
        
        ```jsx
        // MyComponent.vue
        
        <template>
          <div class='border'>
            <h1>This is my component</h1>
            <MyChild
              ...
              @child-to-parent="parentGetEvent"
            />
          </div>
        </template>
        
        <script>
        export default {
          ...
          methods: {
            parentGetEvent: function () {
              console.log("자식 컴포넌트에서 발생한 이벤트!")
            }
          }
        }
        </script>
        ```
        
    
    ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/d8a5bbb2-49e5-4455-aad8-1fe713d45fa5/Untitled.png)
    
4. Emit Event 흐름 정리
    - 1) 자식 컴포넌트에 있는 버튼 클릭 이벤트를 청취하여 연결된 핸들러 함수(**childToParent**) 호출
    - 2) 호출된 함수에서 **$emit** 을 통해 상위 컴포넌트에 이벤트(**child-to-parent**) 발생
    - 3) 상위 컴포넌트는 자식 컴포넌트가 발생시킨 이벤트(**child-to-parent**)를 청취하여 연결된 핸들러 함수(**parentGetEvent**) 호출
    
5. Emit with data
    - 이벤트를 발생(emit)시킬 때 인자로 데이터를 전달 가능
        
        ```jsx
        // MyChild.vue
        
        <template>
          <div>
            ...
            <button @click="childToParent">클릭!</button>
          </div>
        </template>
        
        <script>
        export default {
          ...
          methods: {
            childToParent: function () {
              this.$emit('child-to-parent', 'child data')  // 문자열인 'child data' 전달
            }
          },
        }
        </script>
        ```
        
    
    - 이렇게 전달한 데이터는 이벤트와 연결된 부모 컴포넌트의 핸들러 함수의 인자로 사용 가능
        
        ```jsx
        // MyComponent.vue
        
        <template>
          <div class="border">
            <h1>This is my component</h1>
            <MyChild
            ...
            @child-to-parent="parentGetEvent"
            />
          </div>
        </template>
        
        <script>
        export default {
          ...
          methods: {
            parentGetEvent: function (inputData) {     // 매개변수 선언 : 자식이 전달하는 data가 들어감
              console.log('자식 컴포넌트에서 발생한 이벤트!')
              console.log(`child component로부터 ${inputData}를 받음!`)
            }
          }
        }
        </script>
        ```
        
    
    ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/dffb213e-5ec4-414c-9177-27960e3f3616/Untitled.png)
    
6. emit with data 흐름 정리
    - 1) 자식 컴포넌트에 있는 버튼 클릭 이벤트를 청취하여 연결된 핸들러 함수(**ChildToParent**) 호출
    - 2) 호출된 함수에서 **$emit**을 통해 부모 컴포넌트에 이벤트(**child-to-parent**)를 발생
        - 이벤트에 데이터(**child data**)를 함께 전달
    - 3) 부모 컴포넌트는 자식 컴포넌트의 이벤트(**child-to-parent**)를 청취하여 연결된 핸들러 함수(**parentGetEvent**) 호출, 함수의 인자로 전달된 데이터(**child data**)가 포함되어 있음
    - 4) 호출된 함수에서 **console.log(`~child data~`)** 실행
    
7. emit with dynamic data
    - pass props와 마찬가지로 동적인 데이터도 전달 가능
    - 자식 컴포넌트에서 입력받은 데이터를 부모 컴포넌트에게 전달하여 출력해보자!
    
    ```jsx
    // MyChild.vue
    
    <template>
      <div>
        ...
        <input
          type="text"
          v-model="childInputData"
          @keyup.enter="childInput"
          >
      </div>
    </template>
    
    <script>
    export default {
      ...
      data: function () {
        return {
          childInputData: null,
        }
      },
      methods: {
        ...
        childToParent: function () {
          this.$emit('child-input', this.childInputData)
          this.childInputData = ""
        }
      },
    }
    </script>
    ```
    
    ```jsx
    // MyComponent.vue
    
    <template>
      <div class="border">
        <h1>This is my component</h1>
        <MyChild
          ...
          @child-input="getDynamicData"
        />
      </div>
    </template>
    
    <script>
    // 1. 불러오기
    import MyChild from '@/components/MyChild'
    
    export default {
      ...
      methods: {
        ...
        getDynamicData: function (inputData) {
          console.log(`child component로부터 ${inputData}를 입력받음!`)
        }
      }
    }
    </script>
    ```
    
    ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/5a232285-bcdb-4bb4-9182-1aa079f945e9/Untitled.png)
    
8. emit with dynamic data 흐름 정리
    - 1) 자식 컴포넌트에 있는 `keyup.enter` 이벤트를 청취하여 연결된 핸들러 함수(**ChildInput**) 호출
    - 2) 호출된 함수에서 **$emit**을 통해 부모 컴포넌트에 이벤트(**child-input**)를 발생
        - 이벤트에 v-model로 바인딩 된 **입력받은 데이터**를 전달
    - 3) 상위 컴포넌트는 자식 컴포넌트의 이벤트(**child-input)**를 청취하여 연결된 핸들러 함수(**getDynamicData**) 호출, 함수의 인자로 전달된 데이터가 포함되어 있음
    - 4) 호출된 함수에서 **console.log(`~입력받은 데이터~`)** 실행
    
9. 정리
    - 자식 컴포넌트에서 부모 컴포넌트로 이벤트를 발생시킴
        - 이벤트에 데이터를 담아 전달 가능
    - 부모 컴포넌트에서는 자식 컴포넌트의 이벤트를 청취
        - 전달받은 데이터는 이벤트 핸들러 함수의 인자로 사용
    
10. pass props / emit event 컨벤션
    - 아니 그래서 언제는 **kebab-case**고 언제는 **camelCase**야?
        - HTML 요소에서 사용할 때는 **kebab-case**
        - JavaScript에서 사용할 때는 **camelCase**
    - props
        - 상위 ⇒ 하위 흐름에서 HTML 요소로 내려줌 : **kebab-case**
        - 하위에서 받을 때 JavaScript에서 받음 : **camelCase**
    - emit
        - emit 이벤트를 발생시키면 HTML 요소가 이벤트를 청취함 : **kebab-case**
        - 메서드, 변수명 등은 JavaScript에서 사용함 : **camelCase**

---
