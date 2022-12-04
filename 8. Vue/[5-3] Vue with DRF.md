# **Vue with DRF (feat. CORS)**

### **1. Article Read**

- 응답 받은 데이터 구조 확인
    
    ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/a5fba777-e4f9-44f9-a397-e72d24adfc52/Untitled.png)
    
    - **‘data Array’**에 각 게시글 객체
    - 각 게시글 객체는 다음으로 구성
        - 1) id
        - 2) title
        - 3) content

- `store/index.js` 수정
    
    ```jsx
    // store/index.js
    
    export default new Vuex.Store({
      state: {
        articles: [],
      },
      mutations: {
        GET_ARTICLES(state, articles) {
          state.articles = articles
        },
      },
      actions: {
        getArticles(context) {
          axios({
            method: 'get',
            url: `${API_URL}/api/v1/articles/`
          })
          .then((res) => {
            // console.log(res, context)
            // console.log(res.data)  # articles 데이터 받아오기
            context.commit('GET_ARTICLES', res.data)
          })
          .catch((err) => {
            console.log(err)
          })
        }
      },
    })
    ```
    
    - 기존 articles 데이터 삭제
    - Mutations 정의
        - 응답 받아온 데이터를 state에 저장

- 결과 확인
    
    ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/5455f4da-db2e-47cf-9e08-6b56eae65f42/Untitled.png)
    
    - 사전에 `ArticleList.vue` 에서 state로 화면을 구성하도록 설정
    - 정상적으로 데이터 출력 확인

### **2. Article Create**

- `views/CreateView.vue` 코드 확인
    
    ```jsx
    // views/CreateView.vue
    
    <template>
      <div>
        <h1>게시글 작성</h1>
        <form @submit.prevent="createArticle">
          <label for="title">제목 : </label>
          <input type="text" id="title" v-model.trim="title"><br>
          <label for="content">내용 : </label>
          <textarea id="content" cols="30" rows="10" v-model="content"></textarea><br>
          <input type="submit" id="submit">
        </form>
      </div>
    </template>
    ```
    
    - 게시글 생성을 위한 form을 제공
    - `v-model.trim`을 활용해 사용자 입력 데이터에서 공백 제거
    - `.prevent`를 활용해 form의 기본 이벤트 동작 막기

- `views/CreateView.vue` 코드 확인
    
    ```jsx
    // views/CreateView.vue
    
    <script>
    export default {
      ...
      methods: {
        createArticle() {
          const title = this.title
          const content = this.content
          if (!title) {
            alert('제목을 입력해주세요')
            return
          } else if (!content) {
            alert('내용을 입력해주세요')
            return
          }
        }
      }
    }
    </script>
    ```
    
    - title. content가 비었다면 **alert**를 통해 경고창을 띄우고
    - AJAX 요청을 보내지 않도록 **return** 시켜 함수를 종료

- `views/CreateView.vue` 코드 확인
    
    ```jsx
    // views/CreateView.vue
    
    <script>
    import axios from 'axios'
    const API_URL = 'http://127.0.0.1:8000'
    export default {
      ...
      methods: {
        createArticle() {
          ...
          axios({
            method: 'post',
            url: `${API_URL}/api/v1/articles/`,
            data: { title, content },
          })
          .then((res) => {
            console.log(res)
          })
          .catch((err) => {
            console.log(err)
          })
        }
      }
    }
    </script>
    ```
    
    - axios를 사용해 server에 게시글 생성 요청
- actions를 사용하지 않나요?
    - state를 변화 시키는 것이 아닌 DB에 개시글 생성 후, **ArticleView**로 이동할 것이므로 methods에서 직접 처리

- `router/index.js` 주석 해제
    
    ```jsx
    // router/index.js
    
    ...
    import CreateView from '@/views/CreateView'
    ...
    
    Vue.use(VueRouter)
    
    const routes = [
      ...
      {
        path: '/create',
        name: 'CreateView',
        component: CreateView
      },
    ]
    ```
    

- `views/ArticleView.vue` 주석 해제
    
    ```jsx
    // views/ArticleView.vue
    
    <template>
      <div>
        <h1>Article Page</h1>
        <router-link :to="{ name: 'CreateView' }">[CREATE]</router-link>
        <ArticleList/>
      </div>
    </template>
    ```
    
    - router-link를 통해 CreateView로 이동

- 게시글 작성 요청 결과 확인
    - 정상 작동 확인
        
        ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/f56f87a8-2550-461e-920c-caefe626f814/Untitled.png)
        

- `views/CreateView.vue` 코드 수정
    
    ```jsx
    // views/CreateView.vue
    
    export default {
      ...
      methods: {
        createArticle() {
          ...
          axios({
            method: 'post',
            url: `${API_URL}/api/v1/articles/`,
            data: {
              title : title,
              content: content,
            }
          })
          .then(() => {
            // console.log(res)
            // 제출하면 ArticleView로 이동
            this.$router.push({ name : 'ArticleView' })
          })
          .catch((err) => {
            console.log(err)
          })
        }
      }
    }
    </script>
    ```
    
    - `createArticle` `method` 수정
    - 게시글 생성 완료 후, `ArticleView`로 이동
- 응답 확인을 위해 정의한 인자 **res** 제거

- 게시글 작성 요청 결과 재확인
    
    ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/c32a8d07-6b3c-4529-ad05-7f681c20face/Untitled.png)
    
    - 게시글 생성 후, `ArticleView`로 이동
    - 새로 생성된 게시글 확인 가능
- 어떻게 router로 이동만 했는데 보일까?
    - `ArticleView`가 craete될 때 마다 server에 게시글 전체 데이터를 요청하고 있기 떄문

- [참고] 지금의 요청 방식은 효율적인가?
    - 비효율적인 부분이 존재
        - 전체 게시글 정보를  요청해야 새로 생성된 게시글을 확인할 수 있음
        - 만약 vuex state를 통해 전체 게시글 정보를 관리하도록 구성한다면?
            - 내가 새롭게 생성한 게시글은 확인할 수 있겠지만….
        - 나 이외의 유저들이 새록게 생성한 게시글은 언제 불러와야 할까?
        - 무엇을 기준으로 새로운 데이터가 생겼다는 것을 확인할 수 있을까?
    - 내가 구성하는 서비스에  따라 데이터 관리 방식을 고려해 보아야 함

### **3. Article Detail**

- `views/DetailView.vue` 코드 확인
    
    ```jsx
    // views/DetailView.vue
    
    <template>
      <div>
        <h1>Detail</h1>
      </div>
    </template>
    
    <script>
    export default {
      name: 'DetailView',
    }
    </script>
    ```
    
    - 게시글 상세 정보를 표현할 컴포넌트
    - AJAX 요청으로 응답 받아올 article의 상세 정보들을 표현

- `router/index.js` 주석 해제
    
    ```jsx
    // router/index.js
    
    ...
    import DetailView from '@/views/DetailView'
    ...
    
    Vue.use(VueRouter)
    
    const routes = [
      ...
      {
        path: '/:id',
        name: 'DetailView',
        component: DetailView,
      },
    ]
    ```
    
    - id를 동적 인자로 입력 받아 특정 게시글에 대한 요청

- `components/ArticleListItem.vue` 주석 해제
    
    ```jsx
    // components/ArticleListItem.vue
    
    <template>
      <div>
        <router-link :to="{ name: 'DetailView', params: { id: article.id } }">
          [DETAIL]
        </router-link>
      </div>
    </template>
    ```
    
    - router-link를 통해 특정 게시글의 id 값을 동적 인자로 전달
    - 게시글 상세 정보를 Server에 요청

- `views/DetailView.vue` 코드 확인
    
    ```jsx
    // views/DetailView.vue
    
    <script>
    import axios from 'axios'
    const API_URL = 'http://127.0.0.1:8000'
    
    export default {
      name: 'DetailView',
      created() {
        this.getArticleDetail()
      },
      methods: {
        getArticleDetail() {
          axios({
            method: 'get',
            url: `${API_URL}/api/v1/articles/${this.$route.params.id}`
          })
          .then((res) => {
            console.log(res)
            this.article = res.data
          })
          .catch((err) => {
            console.log(err)
          })
        }
      }
    }
    </script>
    ```
    
    - `this.$route.params`를 활용해 컴포넌트가 create될 때, 넘겨받은 id로 상세 정보 AJAX 요청

- 게시글 상세 정보 요청 결과 확인
    
    ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/170c2c18-ae52-462f-9d7e-bd7c66f51753/Untitled.png)
    
    - 정산 작동 확인
    - 넘겨 받은 데이터 구조 확인 후, 적절하게 화면 구성

- `views/DetailView.vue` 수정
    
    ```jsx
    // views/DetailView.vue
    
    <template>
      <div>
        <h1>Detail</h1>
        <p>글 번호 : {{ article?.id }}</p>
        <p>제목 : {{ article?.title }}</p>
        <p>내용 : {{ article?.content }}</p>
        <p>작성시간 : {{ article?.created_at }}</p>
        <p>수정시간 : {{ article?.updated_at }}</p>
      </div>
    </template>
    
    <script>
    import axios from 'axios'
    const API_URL = 'http://127.0.0.1:8000'
    
    export default {
      name: 'DetailView',
      data() {
        return {
          article: null,
        }
      },
      created() {
        this.getArticleDetail()
      },
      methods: {
        getArticleDetail() {
          axios({
            method: 'get',
            url: `${API_URL}/api/v1/articles/${this.$route.params.id}`
          })
          .then((res) => {
            // console.log(res)
            this.article = res.data
          })
          .catch((err) => {
            console.log(err)
          })
        }
      }
    }
    </script>
    ```
    
    - 응답 받은 정보를 data에 저장
    - data애 담기까지 시간이 걸리므로 optional chaining을 활용해 데이터 표기

- 최종 결과 확인
    
    ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/796ca450-4faf-48eb-9f0e-9dba1a933f77/Untitled.png)
    

---