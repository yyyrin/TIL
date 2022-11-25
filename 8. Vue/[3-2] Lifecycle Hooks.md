# **Lifecycle Hooks**

1. Lifecycle Hooks
    - 각 Vue 인스턴스는 생성과 소멸의 과정 중 단계별 초기화 과정을 거침
        - Vue 인스턴스가 생성된 경우, 인스턴스를 DOM에 마운트하는 경우, 데이터가 변경되어 DOM을 업데이트하는 경우 등
    - 각 데이터가 트리거가 되어 특정 로직을 실행할 수 있음
    - vue instance 생성 → mount(DOM과 연결) → update → mount → … → 소멸 단계
    - 이를 Lifecycle Hooks이라고 함
        
        ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/ef7de98c-3bf0-43ca-8a7f-bf22e5d3ca95/Untitled.png)
        
        → 로직을 추가할 수 있는 순간들!
        
    
2. Lifecycle Hooks 맛보기
    
    
    ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/ad0e6373-15f9-4517-a5f5-44416b648405/Untitled.png)
    
    ```jsx
    // components/ChildComponents.vue
    
    export default {
      ...
      beforeCreate() {
        console.log('beforeCreate')
      },
      created() {
        console.log('created')
      },
      beforeMount() {
        console.log('beforeMount')
      },
      mounted() {
        console.log('mounted')
      }}
    ```
    
    ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/56287b01-7966-4211-8b8c-071918f4c4f9/Untitled.png)
    
    ```jsx
    // components/ChildComponents.vue
    
    <template>
      <div>
        value: {{ value }}
        <button @click="changeValue">change value</button>
      </div>
    </template>
    
    <script>
    export default {
      ...
      data() {
        value: 0,
      }
    },
    methods: {
      changeValue() {
        this.value = this.value + 1
      }
    },
    beforeUpdate() {
      console.log('beforeUpdate')
    },
    updated() {
      conosle.log('updated')
    },}
    </script>
    ```
    
    ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/63cf4397-c773-4034-9b0a-46c92b60114a/Untitled.png)
    
    ```jsx
    // App.vue
    
    <template>
      <div id="app">
        <button @click="toggle">toggle</button>
        <ChildComponent v-if="flag"/>
        <hr>
      </div>
    </template>
    
    <script>
    export default {
      data() {
        return {
          flag: ture,
        }
      },
      methods: {
        toggle() {
          this.flag = !thisflag
        }
      },
    }
    </script>
    ```
    
    ```jsx
    // components/ChildComponents.vue
    
    <script>
    export default {
      ...
      beforeDestroy() {
        console.log('beforeDestroy')
      },
      destroyed() {
        console.log('destroyed')
      }
    }
    </script>
    ```
    
3. created
    - Vue instance가 생성된 후 호출됨
    - data, computed 등의 설정이 완료된 상태
    - 서버에서 받은 데이터를 vue instance의 data에 할당하는 로직을 구현하기 적합
    - 단, mount되지 않은 요소에 접근할 수 없음
    
    - JavaScript에서 학습한 Dog API 활용 실습의 경우 버튼을 누르면 강아지 사진을 보여줌
    - 버튼을 누르지 않아도 첫 실행 시 기본 사진이 출력되도록 하고 싶다면?
        - created 함수에 강아지 사진을 가져오는 함수를 추가
        
        ```jsx
        // components/DogComponent.vue
        
        export default {
          ...
          created() {
            this.getDogImage()
          },
        ```
        
    
4. mounted
    - Vue instance가 요소에 mount된 후 호출됨
    - mount된 요소를 조작할 수 있음
    - DOM과 연결 마무리된 상태 → class 나 setAttribute 사용 가능
        
        ```jsx
        // components/DogComponent.vue
        
        export default {
          ...
          mounted() {
            const btn = document.querySelector('button')
            btn.innerText = '멍멍!'
          },
        ```
        
        ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/75aa5d47-963d-4180-9843-fd557aa1c925/Untitled.png)
        
    
    - created의 경우, mount 되기 전이기 때문에 DOM에 접근할 수 없으므로 동작하지 않음
    - mounted는 주석 처리
        
        ```jsx
        // components/DogComponent.vue
        
        export default {
          ...
          created() {
            this.getDogImage()
            const btn = document.querySelector('button')
            btn.innerText = '멍멍!'  // 바뀌지 않음
          },
        ```
        
    
5. updated
    - 데이터가 변경되어 DOM에 변화를 줄 때 호출됨
        
        ```jsx
        // components/DogComponent.vue
        
        export default {
          ...
          updated() {
            console.log('새로운 멍멍!')
          },
        ```
        
        ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/d4e049f1-95a4-4d11-9780-9dfa60ec7685/Untitled.png)
        
    
6. Lifecycle Hooks 특징
    - instance마다 각각의 Lifecycle을 가지고 있음
        
        
        ```jsx
        // App.vue
        
        export default {
          ...
          created() {
            console.log('App created!')
          },
          mounted() {
            console.log('App mounted!')
          },
        }
        ```
        
        ```jsx
        // DogComponent.vue
        
        export default {
          ...
          created() {
            this.getDogImage()
            console.log('Child created!')
          },
          mounted() {
            const btn = document.querySelector('button')
            btn.innerText = '멍멍!'
            console.log('Child mounted!')
          },
          updated() {
            console.log('새로운 멍멍!')
            console.log('Child updated!')
          },
        }
        ```
        
    
    - Lifecycle Hooks는 컴포넌트별로 정의할 수 있음
    - 현재 해당 프로젝트는 App.vue 생성 ⇒ ChildComponent 생성 ⇒ ChildComponent 부착 ⇒ App.vue 부착 ⇒ ChildComponent 업데이트 순으로 동작한 것
        
        ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/ff5d0eb3-a810-4148-8673-ae941787af76/Untitled.png)
        
    
    - 부모 컴포넌트의 mounted hook이 실행되었다고 해서 자식이 mount된 것이 아니고, 부모 컴포넌트가 updated hook이 실행 되었다고 해서 자식이 updated된 것이 아님(서로 영향 X)
        - 부착 여부가 부모-자식 관계에 따라 순서를 가지고 있지 않은 것
    - **instance마다 각각의 Lifecycle을 가지고 있기 때문**

---