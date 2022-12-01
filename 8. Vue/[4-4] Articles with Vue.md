# **Articles with Vue**

- 개요
    - 지금까지 배운 내용들을 종합하여 Django에서 만들었던 게시판 만들기
    - 구현기능
        - Index
        - Create
        - Detail
        - Delete
        - 404
    
    - 컴포넌트 구성
        
        ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/9d27a715-8561-4a57-b40f-a724fcc8fb46/Untitled.png)
        
    
    - 완성 화면
        
        ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/8c350d0c-78da-4cce-b0d3-e9cb917b1b33/Untitled.png)
        

- 사전 준비
    - 프로젝트 시작
        - `$ vue create articles`
        - `$ cd articles`
        - `$ vue add vuex`
        - `$ vue add router`
    - App.vue는 아래 코드만 남김 (CSS 코드 유지)
        
        ```jsx
        // App.vue
        
        <template>
          <div id="app">
            <router-view/>
          </div>
        </template>
        ```
        

### **1. Index**

1. 개요
    - articles의 목록을 보여주는 index 페이지 작성
    
2. Index 구현
    - state
    - 게시글의 필드는 id, 제목, 내용, 생성일자
    - DB의 AUTO INCREMENT를 표현하기 위해 article_id를 추가로 정의해줌 (다음 article의 id로 사용 예정)
        
        ```jsx
        // store/index.js
        
        state: {
          article_id: 3,
          articles: [
            {
              id: 1,
              title: 'title',
              content: 'content1',
              createdAt: new Date().getTime(),
            },
            {
              id: 2,
              title: 'title2',
              content: 'content2',
              createdAt: new Date().getTime(),
            },
          ]
        },
        ```
        
    
    - IndexView 컴포넌트 및 라우터 작성
        
        
        ```jsx
        // views/IndexView.vue
        
        <template>
          <div>
            <h1>Articles</h1>
          </div>
        </template>
        
        <script>
        export default {
          name: 'IndexView',
        }
        </script>
        ```
        
        ```jsx
        // router/index.js
        
        const routes = [
          {
            path: '/',
            name: 'index',
            component: IndexView
          },
        ]
        ```
        
    
    - state에서 불러온 articles 출력하기
        
        ```jsx
        // views/IndexView.vue
        
        <template>
          <div>
            <h1>Articles</h1>
            {{ articles }}
          </div>
        </template>
        
        <script>
        export default {
          name: 'IndexView',
          computed: {
            articles() {
              return this.$store.state.articles
            }
          },
        }
        </script>
        ```
        
        ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/a23c6c12-71b1-4374-8575-419d4620a17f/Untitled.png)
        
    
    - ArticleItem 컴포년트 작성
        
        ```jsx
        // components/ArticleItem.vue
        
        <template>
          <div>
          </div>
        </template>
        
        <script>
        export default {
          name: 'ArticleItem',
        }
        </script>
        ```
        
    
    - IndexView 컴포넌트에서 ArticleItem 컴포넌트 등록 및 props 데이터 전달
        
        ```jsx
        // views/IntexView.vue
        
        <template>
          <div>
            <h1>Articles</h1>
            <ArticleItem
              v-for="article in articles"
              :key="article.id"
              :article=article
            />
          </div>
        </template>
        
        <script>
        import ArticleItem from '@/components/ArticleItem'
        
        export default {
          name: 'IndexView',
          components: {
            ArticleItem
          },
          computed: {
            articles() {
              return this.$store.state.articles
            }
          },
        }
        </script>
        ```
        
    
    - props 데이터 선언 및 게시글 출력
        
        ```jsx
        // components/ArticleItem.vue
        
        <template>
          <div>
            <p>글 번호 : {{ article.id }}</p>
            <p>글 제목 : {{ article.title }}</p>
            <hr>
          </div>
        </template>
        
        <script>
        export default {
          name: 'ArticleItem',
          props: {
            article: Object,
          },
        }
        </script>
        ```
        
    
    - 결과 확인
        
        ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/8284a24c-2842-4f75-b146-21e3aab875bd/Untitled.png)
        

### **2. Create**

- CreateView 컴포넌트 및 라우터 작성
    
    
    ```jsx
    // views/CreateView.vue
    
    <template>
      <div>
        <h1>게시글 작성</h1>
      </div>
    </template>
    
    <script>
    export default {
      name: 'CreateView',
    }
    </script>
    ```
    
    ```jsx
    // router/index.js
    
    const routes = [
      ...
      {
        path: '/create',
        name: 'create',
        component: CreateView
      },
    ]
    ```
    

- Form 생성 및 데이터 정의
    
    ```jsx
    // views/CreateView.vue
    
    <template>
      <div>
        <h1>게시글 작성</h1>
        <form>
          <label for="title">제목 : </label>
          <input type="text" id="title" v-model="title"><br>
          <label for="content">내용 : </label>
          <textarea
            id="content" cols="30" rows="10"
            v-model="content">
          </textarea><br>
          <input type="submit">
        </form>
      </div>
    </template>
    
    <script>
    export default {
      name: 'CreateView',
      data() {
        return {
          title: null,
          content: null,
        }
      },
    }
    </script>
    ```
    
- `v-on:{event}.prevent`를 활용하여 submit 이벤트 동작을 취소하기
    
    ```jsx
    // views/CreateView.vue
    
    <template>
      <div>
        <h1>게시글 작성</h1>
        <form @submit.prevent="createArticle">
          ...
        </form>
      </div>
    </template>
    ```
    
- actions에 여러 개의 데이터를 넘길 때 하나의 object로 만들어서 전달
    
    ```jsx
    // views/CreateView.vue
    
    <script>
    export default {
      ...
      methods: {
        createArticle() {
          const title = this.title
          const content = this.content
          const payload = {
            title, content
          }
          this.$store.dispatch('createArticle', payload)
        }
      }
    }
    </script>
    ```
    
- `v-model.trim`을 활용하여 입력 값의 공백을 제거
    
    ```jsx
    // views/CreateView.vue
    
    <template>
      <div>
        <h1>게시글 작성</h1>
        <form @submit.prevent="createArticle">
          <label for="title">제목 : </label>
          <input type="text" id="title" v-model.trim="title"><br>
          <label for="content">내용 : </label>
          <textarea
            id="content" cols="30" rows="10"
            v-model.trim="content">
          </textarea><br>
          <input type="submit">
        </form>
      </div>
    </template>
    
    <script>
    export default {
      name: 'CreateView',
      data() {
        return {
          title: null,
          content: null,
        }
      },
    }
    </script>
    ```
    
- 데이터가 없는 경우 alert & 데이터가 있는 경우 actions로 전달
    
    ```jsx
    <script>
    export default {
      ...
      methods: {
        createArticle() {
          const title = this.title
          const content = this.content
          if (!title) {
            alert('제목을 입력해주세요')
          } else if (!content) {
            alert('내용을 입력해주세요')
          } else {
            const payload = {
              title, content
            }
            this.$store.dispatch('createArticle', payload)
          }
        }
      }
    }
    </script>
    ```
    
- actions에서는 넘어온 데이터를 활용하여 article 생성 후 mutations 호출
    - 이때 id로 article_id 활용
    
    ```jsx
    // store/index.js
    
    actions: {
      createArticle(context, payload) {
        const article = {
          id: context.state.article_id,
          title: payload.title,
          content: payload.content,
          createdAt: new Date().getTime(),
        }
        context.commit('CREATE_ARTICLE', article)
      }
    },
    ```
    
- mutations에서는 전달된 article 객체를 사용해 게시글 작성
    - 다음 게시글을 위해 article_id 값 1 증가
    
    ```jsx
    // store/index.js
    
    mutations: {
        CREATE_ARTICLE(state, article) {
          state.articles.push(article)
          state.article_id = state.article_id + 1
        },
      }
    ```
    
- CreateView 컴포넌트에 Index 페이지로 이동하는 뒤로가기 링크 추가
    
    ```jsx
    // views/CreateView.vue
    
    <template>
      <div>
        <h1>게시글 작성</h1>
        ...
        <router-link :to="{ name: 'index' }">뒤로가기</router-link>
      </div>
    </template>
    ```
    
- 게시글 생성 후 Index  페이지로 이동하도록 네비게이터 작성
    
    ```jsx
    // views/CreateView.vue
    
    methods: {
      createArticle() {
        ...
        this.$store.dispatch('createArticle', payload)
        this.$router.push({ name: 'index' })
       }
      }
    }
    ```
    
- IndexView 컴포넌트에 깃글 작성 페이지로 이동하는 링크 추가
    
    ```jsx
    // views/IndexView.vue
    
    <template>
      <div>
        <h1>Articles</h1>
        <router-link :to="{ name: 'create' }">게시글 작성</router-link>
        <hr>
        <ArticleItem
          v-for="article in articles"
          :key="article.id"
          :article=article
        />
      </div>
    </template>
    ```
    

### **3. Detail**

1. Detail 구현
    - DetailView 컴포넌트 및 라우터 작성
    - id를 동적인자로 전달
        
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
        
        ```jsx
        // router/index.js
        
        const routes = [
          ...
          {
            path: '/:id',  // dynamic 변수 이용!
            name: 'detail',
            component: DetailView
          },
        ]
        ```
        
    
    - article 정의 및 state에서 articles 가져오기
        
        ```jsx
        // views/DetailView.vue
        
        export default {
          name: 'DetailView',
          data() {
            return {
              article: null,
            }
          },
          computed: {
            articles() {
              return this.$store.state.articles
            },
          },
        ```
        
    - articles에서 동적인자를 통해 받은 id에 해당하는 article 가져오기
    - 이때, 동적 인자를 통해 받은 id는 str이므로 형변환을 해서 비교
        
        ```jsx
        // views/DetailView.vue
        
        methods: {
          getArticleById() {
            const id = this.$route.params.id  // 문자로 들어옴
            for (const article of this.articles) {
              if (article.id === Number(id)) {
                this.article = article
                break
              }
            }
          },
        }
        ```
        
    - article 출력
        
        ```jsx
        // views/DetailView.vue
        
        <template>
          <div>
            <h1>Detail</h1>
            <p>글 번호 : {{ article?.id }}</p>
            <p>제목 : {{ article.title }}</p>
            <p>내용 : {{ article.content }}</p>
            <p>작성시간 : {{ article.createdAt }}</p>
          </div>
        </template>
        ```
        
    - created lifecycle hook을 통해 인스턴스가 생성되었을 때 article을 가져오는 함수 호출
        
        ```jsx
        // views/DetailView.vue
        
        methods: {
          getArticleById(id) {
            // const id = this.$route.params.id  // 문자로 들어옴
            for (const article of this.articles) {
              if (article.id === Number(id)) {
                this.article = article
                break
              }
            }
          }
        },
        created() {
          this.getArticleById(this.$route.params.id)
        }
        ```
        
    
2. 만약 서버에서 데이터를 가져왔다면?
    - 우리는 현재 state를 통해 데이터를 동기적으로 가져오지만, 실제로는 서버로부터 가져옴
        - 데이터를 가져오는데 시간이 걸림
    - created를 주석처리하고 데이터가 서버로부터 오는데 시간이 걸림을 가정해보자
        
        ```jsx
        // views/DetailView.vue
        
        <script>
        export default {
          // created() {
          //  this.getArticleById(this.$route.params.id)
          // }
        }
        ```
        
    
    - 그런데 article이 null이기 때문에 id 등의 속성을 가져올 수 없음
        
        ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/a12ac08b-6982-4424-9669-c6b25701845c/Untitled.png)
        
    
    - optional chaining( **?.** )을 통해 artilce 객체가 있을 때만 출력되도록 수정
    - created 주석을 다시 해제
        
        ```jsx
        // views/DetailView.vue
        
        <template>
          <div>
            <h1>Detail</h1>
            <p>글 번호 : {{ article?.id }}</p>
            <p>글 제목 : {{ article?.title }}</p>
            <p>글 내용 : {{ article?.content }}</p>
            <p>글 작성시간 : {{ article?.createdAt }}</p>
          </div>
        </template>
        ```
        
        ```jsx
        // views/DetailView.vue
        
        <script>
        export default {
          created() {
            this.getArticleById(this.$route.params.id)
          }
        }
        ```
        
    
3. [참고] Optional Chaining
    - Optional Chaining( **?.** ) 앞의 평가 대상이 undefined나 null이면 에러가 발생하지 않고 undefined 를 반환
    - (EX2020에서 추가된 문법)
        
        ```jsx
        const userInfo = {
          name: {
            last: "Stark",
          },
          address: {
            city: 'Seoul',
          },
          getInfo() {
            console.log(this.name)
          }
        }
        
        // Optional chaining 미사용
        const myCity = userInfo.address && userInfo.address.city
        
        // Optional chaining 사용
        const myCity = userInfo.address?.city
        
        // Optional chaining 사용 (메서드 호출 시)
        userInfo.getInfo?.()
        ```
        
    
4. Data in JavaScript
    - JavaScript에서 시간을 나타내는 Data객체는 1970년 1월 1일 UTC(협정 세계시) 자정과의 시간 차이를 밀리초로 나타내는 정수 값을 담음
        - `Date().toLocaleString()`을 사용하여 변환
    
    - 로컬시간으로 변환해주는 computed 값 작성 및 출력
        
        ```jsx
        // views/DetailView.vue
        
        <template>
          <div>
            <h1>Detail</h1>
            <p>글 번호 : {{ article?.id }}</p>
            <p>글 제목 : {{ article?.title }}</p>
            <p>글 내용 : {{ article?.content }}</p>
            <!-- <p>글 작성시간 : {{ article?.createdAt }}</p> -->
            <p>작성시간 : {{ articleCreatedAt }}</p>
          </div>
        </template>
        
        <script>
        export default {
          ...
          computed: {
            ...
            articleCreatedAt() {
              const article = this.article
              const createdAt = new Date(article?.createdAt).toLocaleString()
              return createdAt
            }
          },
        ```
        
    
5. Detail 구현
    - DetailView 컴포넌트에 뒤로가기 링크 추가
        
        ```jsx
        // views/DetailView.vue
        
        <template>
          <div>
            ...
            <router-link :to="{ name: 'index' }">뒤로가기</router-link>
          </div>
        </template>
        ```
        
    
    - 각 게시글을 클릭하면 detail 페이지로 이동하도록 ArticleItem에 이벤트 추가
    - v-on 이벤트 핸들러에도 인자 전달 가능
        
        ```jsx
        // components/ArticleItem.vue
        
        <template>
          <div @click="goDetail(article.id)">
            <p>글 번호 : {{ article.id }}</p>
            <p>글 제목 : {{ article.title }}</p>
            <hr>
          </div>
        </template>
        
        <script>
        export default {
          ...
          methods: {
            goDetail(id) {
              this.$router.push({ name: 'detail', params: {id} })
            }
          }
        }
        </script>
        ```
        

### **4. Delete**

- DetailView 컴포넌트에 삭제 버튼을 만들고, mutations를 호출
    
    ```jsx
    // views/DetailView.vue
    
    <template>
      <div>
        ...
        <button @click="deleteArticle">삭제</button><br>
        <router-link :to="{ name: 'index' }">뒤로가기</router-link>
      </div>
    </template>
    
    <script>
    export default {
      ...
      methods: {
        ...
        deleteArticle() {
          this.$store.commit('DELETE_ARTICLE', this.article.id)
        }
      },
      ...
    }
    </script>
    ```
    

- mutations에서 id에  해당하는 게시글을 지움
    
    ```jsx
    // store/index.js
    
    mutations: {
      ...
      DELETE_ARTICLE(state, id) {
        state.articles = state.articles.filter((article) => {
          return !(article.id === Id)
        })
      },
    },
    ```
    

- 삭제 후 index 페이지로 이동하도록 네비게이션 작성
    
    ```jsx
    // views/DetailView.vue
    
    <script>
    export default {
      ...
      methods: {
        ...
        deleteArticle() {
          this.$store.commit('DELETE_ARTICLE', this.article.id)
          this.$router.push({ name: 'index' })
        }
      },
      ...
    }
    </script>
    ```
    

### **5. 404 Not Found**

- NotFound404 컴포넌트 및 라우터 작성
- Detail에 대한 route보다 먼저 등록해줘야 함
    - 또한, /404로 등록하면 404번째 게시글과 혼동할 수 있게 됨
    
    ```jsx
    // views/NotFound404.vue
    
    <template>
      <div>
        <h1>404 Not Found</h1>
      </div>
    </template>
    
    <script>
    export default {
      name: 'NotFound404',
    }
    </script>
    ```
    
    ```jsx
    // router/index.js
    
    const routes = [
      ...
      {
        path: '/404-not-found',
        name: 'NotFound404',
        component: NotFound404
      },
      {
        path: '/:id',  // dynamic 변수 이용!
        name: 'detail',
        component: DetailView
      },
    ]
    
    ```
    

- DetailView 컴포넌트에 id에 해당하는 artilce이 없으면 404 페이지로 이동
    
    ```jsx
    // views/DetailView.vue
    
    <script>
    export default {
      ...
      methods: {
        getArticleById(id) {
          // const id = this.$route.params.id  // 문자로 들어옴
          for (const article of this.articles) {
            if (article.id === Number(id)) {
              this.article = article
              break
            }
          }
          if (!this.article){
            this.$router.push({ name: 'NotFound404' })
          }
        },
      },
    }
    </script>
    ```
    

- 요청한 리소스가 존재하지 않는 경우
- 없는 id가 아닌 전혀 다른 요청에도 대비하여 404 page로 `redirect` 시키기
    - `$router.push`와 마찬가지로 name을 이용하여 이동할 수 있음
        
        ```jsx
        // router/index.js
        
        const routes = [
          ...
          {
            path: '*',
            redirect: { name: 'NotFound404' }
          }
        ]
        ```
        
        → 최하단에 작성해야 함
        

---