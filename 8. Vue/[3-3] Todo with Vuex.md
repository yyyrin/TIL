# **Todo with Vuex**

- 개요
    - Vuex를 사용한 Todo 프로젝트 만들기
    - 구현 가능
        - Todo CRUD
        - Todo 개수 계산
            - 전체 Todo
            - 완료된 Todo
            - 미완료된 Todo
    
    - 컴포넌트 구성
        
        ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/37f77738-7303-4c6a-8266-b2e9d273c643/Untitled.png)
        
    
    - 완성 화면
        
        ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/f3377b4c-4199-467f-bb90-ffec566c8ae9/Untitled.png)
        

### **1. 사전 준비**

1. Init Project
    - 1) 프로젝트 생성 및 vuex 플러그인 추가
        
        ```jsx
        $ vue create todo-vuex-app
        
        $ cd todo-vuex-app
        
        $ vue add vuex
        ```
        
    - 2) HelloWorld 컴포넌트 및 관련 코드 삭제
        - `App.vue`의 CSS 코드는 남김
    
2. 컴포넌트 작성
    - `TodoListItem.vue`
        
        ```jsx
        // components/TodoListItem.vue
        
        <template>
          <div>Todo</div>
        </template>
        
        <script>
        export default {
          name: 'TodoListItem',
        }
        </script>
        ```
        
    
    - `TodoList.vue`
        
        ```jsx
        // components/TodoList.vue
        
        <template>
          <div>
            <TodoListItem/>
          </div>
        </template>
        
        <script>
        import TodoListItem from '@/components/TodoListItem'
        
        export default {
          name: 'TodoList',
          components: {
            TodoListItem,
          }
        }
        </script>
        ```
        
    
    - `TodoForm.vue`
        
        ```jsx
        // components/Todoform.vue
        
        <template>
          <div>Todo Form</div>
        </template>
        
        <script>
        export default {
          name: 'TodoForm',
        }
        </script>
        ```
        
    
    - `App.vue`
        
        ```jsx
        // App.vue
        
        <template>
          <div>
            <h1>Todo List</h1>
            <TodoList/>
            <TodoForm/>
          </div>
        </template>
        
        <script>
        import TodoList from '@/components/TodoList'
        import TodoForm from '@/components/TodoForm'
        
        export default {
          name: 'App',
          components: {
            TodoList,
            TodoForm,
          }
        }
        </script>
        ```
        
    
    - 페이지 확인
        
        ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/0698b327-e371-490d-b054-51ddda06712f/Untitled.png)
        

### **2. Read Todo**

1. State 세팅
    - 출력을 위한 기본 todo 작성
        
        ```jsx
        // index.js
        
        import Vue from 'vue'
        import Vuex from 'vuex'
        
        Vue.use(Vues)
        
        export default new Vuex.Store({
          state: {
            todos: [
              {
                title: '할 일 1',
                isCompleted: false,
              }
            ]
          },
          ...
        })
        ```
        
    
    - Vue 개발자 도구에서 state 데이터 확인
        
        ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/7bf09249-a7c1-4a0e-bdcb-b86435266da1/Untitled.png)
        
    
2. state 데이터 가져오기
    - 컴포넌트에서 Vuex Store의 state에 접근(`$store.state`)
    - computed로 계산된 todo 목록을 가져올 수 있도록 설정
    - v-for의 key는 배열의 각 요소 간의 유일한 식별자 값을 사용해야 하지만 vuex 흐름에 집중하기 위해 index를 key로 사용하도록 함
        
        ```jsx
        // components/TodoList.vue
        
        <template>
          <div>
            <TodoListItem
              v-for="(todo, index) in todos"
              :key="index"
            />
          </div>
        </template>
        
        <script>
        import TodoListItem from '@/components/TodoListItem'
        
        export default {
          name: 'TodoList',
          components: {
            TodoListItem,
          },
          computed: {
            todos() {
              return this.$store.state.todos
            }
          }
        }
        </script>
        ```
        
    
3. Pass Props
    - `TodoList.vue` → `TodoListItem.vue`
        
        ```jsx
        // components/TodoList.vue
        
        <template>
          <div>
            <TodoListItem
              v-for="(todo, index) in todos"
              :key="index"
              :todo="todo"
            />
          </div>
        </template>
        ```
        
    
    - todo 데이터 내려받기
        
        ```jsx
        // components/TodoList.vue
        
        <template>
          <div>{{ todo.title }}</div>
        </template>
        
        <script>
        export default {
          name: 'TodoListItem',
          props: {
            todo: Object,
          },
        }
        </script>
        ```
        
    
    - 출력 확인
        
        ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/612a4e81-4940-445b-bde2-06d40467dd37/Untitled.png)
        

### **3. Create Todo**

1. TodoForm
    - todoTitle을 입력 받을 input 태그 생성
    - todoTitle을 저장하기 위해 data를 정의하고 input과 v-model을 이용해 양방향 바인딩
    - enter 이벤트를 사용해 createTodo 메서드 출력 확인
        
        ```jsx
        // components/Todoform.vue
        
        <template>
          <div>
            <input
              type="text"
              v-model="todoTitle"  // 입력한 내용을 담아야 하기 때문에 v-model 사용
              @keyup.enter="createTodo"
            >
          </div>
        </template>
        
        <script>
        export default {
          name: 'TodoForm',
          data() {
            return {
              todoTitle: null,
            }
          },
          methods: {
            createTodo() {
              console.log(this.todoTitle)
            }
          }
        }
        </script>
        ```
        
    
    - 출력 확인
        
        ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/860c375b-aeef-4f7b-88ee-f8a658e82c84/Untitled.png)
        
    
2. Actions
    - createTodo 메서드에서 actions을 호출 (`dispatch`)
    - todoTitle까지 함께 전달
        
        ```jsx
        // components/Todoform.vue
        
        <script>
        export default {
          name: 'TodoForm',
          data() {
            return {
              todoTitle: null,
            }
          },
          methods: {
            createTodo() {
              // console.log(this.todoTitle)
              this.$store.dispatch('createTodo', this.todoTitle)
              this.todoTitle = null
            }
          }
        }
        </script>
        ```
        
    
    - actions에는 보통 비동기 관련 작업이 진행 되지만 현재 별도의 비동기 관련 작업이 불필요하기 때문에 입력 받은 todo 제목(todoTitle)을 todo 객체(todoItem)로 만드는 과정을 Actions에서 작성할 예정
    - createTodo에서 보낸 데이터를 수신 후 todoItem object를 생성
        
        ```jsx
        // index.js
        
        export default new Vuex.Store({
          ...
          actions: {
            createTodo(context, todoTitle) {
              const todoItem = {
                title: todoTitle,
                isCompleted: false,
              }
              console.log(todoItem)
            }
          },
          modules: {
          }
        })
        ```
        
    
    - actions 동작 확인
        
        ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/66a37127-3cbc-4133-926d-39d713adc1ef/Untitled.png)
        
    
3. Mutations
    - CREATE_TODO mutations 메서드에 todoItem를 전달하며 호출(`commit`)
        
        ```jsx
        // index.js
        
        export default new Vuex.Store({
          ...
          actions: {
            createTodo(context, todoTitle) {
              const todoItem = {
                title: todoTitle,
                isCompleted: false,
              }
              // console.log(todoItem)
              context.commit('CREATE_TODO', todoItem)
            }
          },
          modules: {
          }
        })
        ```
        
    
    - mutations에서 state의 todo에 접근해 배열에 요소를 추가
        
        ```jsx
        // index.js
        
        export default new Vuex.Store({
          ...
          mutations: {
            CREATE_TODO(state, todoItem) {
              state.todos.push(todoItem)
            }
          },
          ...
        })
        ```
        
    
    - Todo 작성 후 확인
        
        ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/2fca68db-688f-403d-a53d-4cea3d66477b/Untitled.png)
        
    
    - Todos의 기존 dummy 데이터를 삭제
    - 빈 배열로 수정
        
        ```jsx
        // index.js
        
        export default new Vuex.Store({
          state: {
            todos: [],
          },
        ```
        
    
4. 공백 문자가 입력되지 않도록 처리하기
    - `v-model.trim` & `if (this.todoTitle)`
        - 좌우 공백 삭제
        - 빈 문자열이 아닐 경우만 작성
    
    ```jsx
    // components/Todoform.vue
    
    <template>
      <div>
        <input
          type="text"
          v-model.trim="todoTitle"
          @keyup.enter="createTodo"
        >
      </div>
    </template>
    
    <script>
    export default {
      ...
      methods: {
        createTodo() {
          // console.log(this.todoTitle)
          if (this.todoTitle) {
            this.$store.dispatch('createTodo', this.todoTitle)
          }
          this.todoTitle = null
        }
      }
    }
    </script>
    ```
    
5. 중간 정리
    - Vue 컴포넌트의 method에서 `dispatch()`를 사용해 actions 메서드를 호출
    - Actions에 정의된 함수는 `commit()`을 사용해 mutations를 호출
    - Mutations에 정의된 함수가 최종적으로 state를 변경

### **4. Delete Todo**

1. TodoListItem
    - TodoListItem 컴포넌트에  삭제 버튼 및 delete Todo 메서드 작성
        
        ```jsx
        // components/TodoListItem.vue
        
        <template>
          <div>
            <span>{{ todo.title }}</span>
            <button @click="deleteTodo">Delete</button>
          </div>
        </template>
        
        <script>
        export default {
          name: 'TodoListItem',
          props: {
            todo: Object,
          },
          methods: {
            deleteTodo() {
              console.log('Todo 삭제')
            },
          },
        }
        </script>
        ```
        
    
    - delete Todo 메서드 동작 확인
        
        ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/66bbb92e-f0ee-408b-875d-8ac50a03163d/Untitled.png)
        
    
2. Actions
    - deleteTodo 메서드에서 deleteTodo actions 메서드 호출(`dispatch`)
    - 삭제되는 todo를 함께 전달
        
        ```jsx
        // components/TodoListItem.vue
        
        <script>
        export default {
          name: 'TodoListItem',
          props: {
            todo: Object,
          },
          methods: {
            deleteTodo() {
              // console.log('Todo 삭제')
              this.$store.dispatch('deleteTodo', this.todo)
            },
          },
        }
        </script>
        ```
        
    
    - deleteTodo actions 메서드에서 DELETE_TODO mutations 메서드 호출(`commit`)
        
        ```jsx
        // index.js
        
        export default new Vuex.Store({
          ...
          actions: {
            createTodo(context, todoTitle) {
              const todoItem = {
                title: todoTitle,
                isCompleted: false,
              }
              // console.log(todoItem)
              context.commit('CREATE_TODO', todoItem)
            },
            deleteTodo(context, todoItem) {
              context.commit('DELETE_TODO', todoItem)
            },
          },
          modules: {
          }
        })
        ```
        
    
3. Mutataions
    - DELETE_TODO 메서드 작성
        
        ```jsx
        // index.js
        
        export default new Vuex.Store({
          ...
          mutations: {
            CREATE_TODO(state, todoItem) {
              state.todos.push(todoItem)
            },
            DELETE_TODO(state, todoItem) {
              console.log(todoItem)
            },
          },
          ...
        })
        ```
        
    
    - Vue Dev Tools를 통해 Action 및 Mutation 동작 확인
        
        ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/22bb6aea-4a93-4061-b346-f55b43d4d7e3/Untitled.png)
        
    
    - 전달된 todoItem에 해당하는 todo 삭제
    - 작성 후 삭제 테스트
        
        ```jsx
        // index.js
        
        export default new Vuex.Store({
          ...
          mutations: {
            CREATE_TODO(state, todoItem) {
              state.todos.push(todoItem)
            },
            DELETE_TODO(state, todoItem) {
              // console.log(todoItem)
              const index = state.todos.indexOf(todoItem)
              state.todos.splice(index, 1)
            },
          },
          ...
        })
        ```
        

### **5. Update Todo**

1. TodoListItem
    - todo를 클릭하면 완료 표시의 의미로 취소선 스타일을 적용하는 기능 구현
        - 즉 todo의 isCompleted 값 토글하기
    - TodoListItem 컴포넌트에 클릭 이벤트를 추가 후 관련 actions 메서드 호출
        
        ```jsx
        // components/TodoListItem.vue
        
        <template>
          <div>
            <span @click="updateTodoStatus">{{ todo.title }}</span>
            <button @click="deleteTodo">DELETE</button>
          </div>
        </template>
        
        <script>
        export default {
          name: 'TodoListItem',
          props: {
            todo: Object,
          },
          methods: {
            ...
            updateTodoStatus() {
              this.$store.dispatch('updateTodoStatus', this.todo)
            },
          }
        }
        </script>
        ```
        
    
2. Actions
    - `updateTodoStatus` 메서드 작성
    - 관련 mutations 메서드 호출
        
        ```jsx
        // index.js
        
        export default new Vuex.Store({
          ...
          actions: {
            ...
            // 딱히 할 일 없는 actions임
            updateTodoStatus(context, todoItem) {
              context.commit('UPDATE_TODO_STATUS', todoItem)
            },
          },
          modules: {
          }
        })
        ```
        
    
3. Mutations
    - `UPDATE_TODO_STATUS` 메서드 작성
        
        ```jsx
        // index.js
        
        export default new Vuex.Store({
          ...
          mutations: {
            ...
            UPDATE_TODO_STATUS(state, todoItem) {
              console.log(todoItem)
            },
          ...
        })
        ```
        
    
    - Vue Dev Tools를 통해 Action 및 Mutation 동작 확인
        
        ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/daf60ff7-a3aa-4bda-b342-12e4c7d9fddb/Untitled.png)
        
    
    - map 메서드를 활용해 선택된 todo의 isCompleted를 반대로 변경 후 기존 배열 업데이트
        
        ```jsx
        // index.js
        
        export default new Vuex.Store({
          ...
          mutations: {
            ...
            UPDATE_TODO_STATUS(state, todoItem) {
              // console.log(todoItem)
        
              // todos 배열에서 선택된 todo의 is_completed값만 토글한 후
              // 업데이터 된 todos 배열로 되어야 함
              // 방법 1) by 겨수님
              state.todos = state.todos.map((todo) => {
                if (todo === todoItem) {
                  todo.isCompleted = !todo.isCompleted
                }
                return todo
              })
              // 방법 2)
              // const index = state.todos.indexOf(todoItem)
              // state.todos[index].isCompleted = !state.todos[index].isCompleted
            },
          },
          ...
        })
        ```
        
    
    - 특정 todo 선택 후 isCompleted 값이 변경되는 것을 확인
        
        ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/d741d656-bdf7-4e28-a963-0d0de0b2d14d/Untitled.png)
        
    
4. 취소선 스타일링
    - CSS 작성
        
        ```jsx
        // components/TodoListItem.vue
        
        <style>
          .is-completed {
            text-decoration: line-through;
          }
        </style>
        ```
        
    
    - v-bind를 활용해 isCompleted 값에 따라 css 클래스가 토글 방식으로 적용되도록 작성하기
        
        ```html
        // components/TodoListItem.vue
        
        <template>
          <div>
            <span
              @click="updateTodoStatus"
              :class="{ 'is-completed': todo.isCompleted }"
            >
        		<!-- todo.isCompleted가 true라면 is-completed를 적용해라! -->
              {{ todo.title }}
            </span>
            <button @click="deleteTodo">DELETE</button>
          </div>
        </template>
        ```
        
    
    - 동작 확인
        
        ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/abd3b9fe-9cbb-4b62-a0e5-e3b4c7f8f99a/Untitled.png)
        

### **6. 상태별 todo 개수 계산**

1. 전체 todo 개수
    - `allTodosCount` getters 작성
    - state에 있는 todos 배열의 길이 계산
        
        ```jsx
        // index.js
        
        export default new Vuex.Store({
          state: {
            todos: [],
          },
          getters: {
            allTodosCount(state) {
              return state.todos.length
            },
          },
          ...
        })
        ```
        
    
    - getters에 계산된 값을 각 컴포넌트의 computed에서 사용하기
        
        ```jsx
        // App.vue
        
        <template>
          <div id="app">
            <h1>Todo List</h1>
            <h2>All Todos: {{ allTodosCount }}</h2>
            <TodoList/>
            <TodoForm/>
          </div>
        </template>
        
        <script>
        import TodoList from '@/components/TodoList'
        import TodoForm from '@/components/TodoForm'
        
        export default {
          ...
          computed: {
            allTodosCount() {
              return this.$store.getters.allTodosCount
            },
          }
        }
        </script>
        ```
        
    
    - 동작 확인
        
        ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/011d6cb8-0c2d-4a76-8501-369f30b2603b/Untitled.png)
        
    
2. 완료된 todo 개수
    - `completedTodosCount` getters 작성
    - isCompleted가 true인 todo들만 필터링한 배열을 만들고 길이 계산
    - filter를 활용하여 완료 여부에 따른 새로운 객체 목록을 작성 후 길이 반환
        
        ```jsx
        // index.js
        
        export default new Vuex.Store({
          state: {
            todos: [],
          },
          getters: {
            allTodosCount(state) {
              return state.todos.length
            },
            completedTodosCount(state) {
              // 1. 완료된 todo만 모아놓은 새로운 객체 생성
              const completedTodos = state.todos.filter((todo) => {
                return todo.isCompleted === true
              })
              // 2. 길이 반환
              return completedTodos.length
            },
          },
        ```
        
    
    - getters에 계산된 값을 각 컴포넌트의 computed에서 사용하기
        
        ```jsx
        // App.vue
        
        <template>
          <div id="app">
            <h1>Todo List</h1>
            <h2>All Todos: {{ allTodosCount }}</h2>
            <h2>Completed Todo: {{ completedTodosCount }}</h2>
            <TodoList/>
            <TodoForm/>
          </div>
        </template>
        
        <script>
        import TodoList from '@/components/TodoList'
        import TodoForm from '@/components/TodoForm'
        
        export default {
          ...
          computed: {
            allTodosCount() {
              return this.$store.getters.allTodosCount
            },
            completedTodosCount() {
              return this.$store.getters.completedTodosCount
            },
          }
        }
        </script>
        ```
        
    
    - 동작 확인
        
        ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/a292d34b-8ce5-47e6-b98b-63310be8c697/Untitled.png)
        
    
3. 미완료된 todo 개수
    - 미완료된 todo 개수 === 전체 개수 - 완료된 개수
    - getters가 두 번째 인자로 getters를 받는 것을 활용
    - `unCompletedTodosCount` getters 작성
        
        ```jsx
        // index.js
        
        export default new Vuex.Store({
          state: {
            todos: [],
          },
          getters: {
            ...
            // 위에서 사용한 값 이용하기 위해 getters 이용!
            unCompletedTodosCount(state, getters) {
              return getters.allTodosCount - getters.completedTodosCount
            },
          },
        ```
        
    
    - getters에 계산된 값을 각 컴포넌트의 computed에서 사용하기
        
        ```jsx
        // App.vue
        
        <template>
          <div id="app">
            <h1>Todo List</h1>
            <h2>All Todos: {{ allTodosCount }}</h2>
            <h2>Completed Todo: {{ completedTodosCount }}</h2>
            <h2>unCompleted Todo: {{ unCompletedTodosCount }}</h2>
            <TodoList/>
            <TodoForm/>
          </div>
        </template>
        
        <script>
        import TodoList from '@/components/TodoList'
        import TodoForm from '@/components/TodoForm'
        
        export default {
          ...
          computed: {
            ...
            unCompletedTodosCount() {
              return this.$store.getters.unCompletedTodosCount
            },
          },
        }
        </script>
        ```
        
    
    - 동작 확인
        
        ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/73c36c05-5ac7-4a8a-9c7c-6243a05d9408/Untitled.png)
        

### **7. Local Storage**

1. 개요
    - 브라우저의 **Local Storage**에 todo 데이터를 저장하여 브라우저를 종료하고 다시 실행해도 데이터가 보존될 수 있도록 하기
    
2. Window.**localStorage**
    - 브라우저에서 제공하는 저장공간 중 하나인 Local Storage에 관련된 속성
    - 만료되지 않고 브라우저를 종료하고 다시 실행해도 데이터가 보존됨
    - 데이터가 문자열 형태로 저장됨
    - 관련 메서드
        - `setItem(key, value)` - key, value 형태로 데이터 저장
        - `getItem(key)` - key에 해당하는 데이터 조회
    - [https://developer.mozilla.org/ko/docs/Web/API/Window/localStorage](https://developer.mozilla.org/ko/docs/Web/API/Window/localStorage)
    
3. Local Storage 실습
    - todos 배열을 Local Storage에 저장하기
    - 데이터가 문자열 형태로 저장되어야 하기 때문에 `JSON.stringify`를 사용해 문자열로 변환해주는 과정 필요
    - state를 변경하는 작업이 아니기 때문에 mutations가 아닌 actions에 작성
        
        ```jsx
        // index.js
        
        export default new Vuex.Store({
          ...
          actions: {
            ...
            saveTodoLocalStorage(context) {
              const jsonTodos = JOSN.stringify(context.state.todos)   // JSON으로 변환하지 않으면 글자 깨진다.
              localStorage.setItem('todos', jsonTodos)   // localStorage : 내장된 함수 , ('임의의 단어', 저장할 데이터)
            },
          },
          modules: {
          }
        })
        ```
        
    
    - todo 생성, 삭제, 수정시에 모두 `saveTodosLocalStorage` action 메서드가 실행 되도록 함
        
        ```jsx
        // index.js
        
        export default new Vuex.Store({
          ...
          actions: {
            createTodo(context, todoTitle) {
              const todoItem = {
                title: todoTitle,
                isCompleted: false,
              }
              // console.log(todoItem)
              context.commit('CREATE_TODO', todoItem)
              context.dispatch('saveTodosToLocalStorage')
            },
            deleteTodo(context, todoItem) {
              context.commit('DELETE_TODO', todoItem)
              context.dispatch('saveTodosToLocalStorage')
            },
            updateTodoStatus(context, todoItem) {
              context.commit('UPDATE_TODO_STATUS', todoItem)
              context.dispatch('saveTodosToLocalStorage')
            },
             saveTodosToLocalStorage(context) {
               const jsonTodos = JSON.stringify(context.state.todos)
               localStorage.setItem('todos', jsonTodos)
             },
          },
          modules: {
          }
        })
        ```
        
    
    - 개발자도구 ⇒ Application ⇒ Storage ⇒ Local Storage에서 todos가 저장된 것을 확인
        
        ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/fae058b8-45f6-458f-905f-f68fd0ba19dc/Untitled.png)
        
    
    - 하지만 아직 Local Storage에 있는 todo 목록을 불러오는 것이 아니기 때문에 페이지 새로고침 이후 목록이 모두 사라짐
    - 불러오기 버튼을 누르면 Local Storage에 저장된 데이터를 가져오도록 할 것
        - 1) 불러오기 버튼 작성
        - 2) `localTodos` action 메서드 작성
        - 3) `LOAD_TODOS` mutation 메서드 작성
    
    - 1) 불러오기 버튼 작성
        
        ```jsx
        // App.vue
        
        <template>
          <div id="app">
            <h1>Todo List</h1>
            <h2>All Todos: {{ allTodosCount }}</h2>
            <h2>Completed Todo: {{ completedTodosCount }}</h2>
            <h2>Uncompleted Todo: {{ unCompletedTodosCount }}</h2>
            <TodoList/>
            <TodoForm/>
            <button @click="loadTodos">Todo 불러오기</button>
          </div>
        </template>
        ```
        
    
    - 2) `loadTodos` 메서드 작성
        
        ```jsx
        // App.vue
        
        <script>
        import TodoList from '@/components/TodoList'
        import TodoForm from '@/components/TodoForm'
        
        export default {
          name: 'App',
          components: {
            TodoList,
            TodoForm,
          },
           methods: {
             loadTodos() {
               this.$store.dispatch('loadTodos')
             }
            // 구조분해할당 적용 방법!
            // loadLocalStorage({ commit }) {  // { commit } : 원하는 데이터만 가져오는 방법 (구조분해할당 작용)
            //   const jsonData = localStorage.getItem('todos')
            //   const data = jsonData.toUpperCase(jsonData)
            //   commit('LOAD_TODOS')
            // }
           },
        ...
        ```
        
    
    - 3) `loadTodos` action 메서드 작성
        
        ```jsx
        // index.js
        
        export default new Vuex.Store({
          ...
          actions: {
            ...,
             loadTodos(context) {
               context.commit('LOAD_TODOS')
             },
          },
          modules: {
          }
        })
        ```
        
    
    - 4) `LOAD_TODOS` mutation 메서드 작성
    - 문자열 데이터를 다시 object 타입으로 변환(**JSON.parse**)하여 저장
        
        ```jsx
        // index.js
        
        export default new Vuex.Store({
          ...
          mutations: {
            ...,
             LOAD_TODOS(state) {
               const localStorageTodos = localStorage.getItem('todos') 
               const parsedTodos = JSON.parse(localStorageTodos)
               state.todos = parsedTodos
             },
          },
        ```
        
    
    - 동작 확인
        
        ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/b5106b14-74af-4169-84af-c929eb4154cd/Untitled.png)
        
    
4. vuex-persistedstate
    - Vuex state를 자동으로 브라우저의 Local Storage에 저장해주는 라이브러리 중 하나
    - 페이지가 새로고침 되어도 Vuex state를 유지시킴
    - Local Storage에 저장된 data를 자동으로 state로 불러옴
    - [https://github.com/robinvdvleuten/vuex-persistedstate](https://github.com/robinvdvleuten/vuex-persistedstate)
    
    - 설치
        - `$ npm i vuex-persistedstate`
    - 적용
        
        ```jsx
        // index.js
        
        import createPersistedState from 'vuex-persistedstate'
        
        Vue.use(Vuex)
        
        export default new Vuex.Store({
          plugins: [
            createPersistedState(),
          ],
        ```
        
    
    - 이전에 작성한 localStorage 관련코드를 모두 주석 처리하기
        - `App.vue`
            - 불러오기 버튼
            - `loadTodos` 메서드
        - `index.js`
            - `LOAD_TODOS` mutation 메서드
            - `saveTodosToLocalStorage` action 메서드
            - `loadTodos` action 메서드
            - `context.dispatch(’saveTodosToLocalStorage’)` 코드 3줄
    
    - 이제는 불러오기 버튼 없이 자동으로 저장된 데이터를 불러올 수 있음
        
        ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/9736949d-faa6-4bc8-9cdd-154faf44384c/Untitled.png)
        
    
5. 그냥 mutations으로만 state를 변경하면 안될까?
    - “가능하다”
    - 단, 저장소의 각 컨셉(`state`, `getters`, `mutations`, `actions`)은 각자의 역할이 존재하도록 설계 되어있음
    - 물론 우리가 작성한 todo app처럼 actions의 로직이 특별한 작업 없이 단순히 mutations만을 호출하는 경우도 있으나 이 경우는 Vuex 도입의 적절성을 판단해 볼 필요가 있음
    
6. Vuex, 그럼 언제 사용해야 할까?
    - Vuex는 공유된 상태 관리를 처리하는 데 유용하지만, 개념에 대한 이해와 시작하는 비용이 큼
    - 애플리케이션이 단순하다면 Vuex가 없는 것이 더 효율적일 수 있음
    - 그러나 중대형 규모의 SPA를 구축하는 경우 Vuex는 자연스럽게 선택할 수 있는 단계가 오게 됨
    - 결과적으로 역할에 적절한 상황에서 활용했을 때 Vuex 라이브러리 효용을 극대화할 수 있음
    - 즉, 필요한 순간이 왔을 때 사용하는 것을 권장

---