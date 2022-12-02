# **Vue with DRF**

- 개요
    - Server와 Client의 통신 방법 이해하기
    - CORS 이슈 이해하고 해결하기
    - DRF Auth System 이해하기
    - Vue와 API server 통신하기

### **1. Server & Client**

1. Server
    - 서버(server)란?
        - 클라이언트에게 **정보**와 **서비스**를 제공하는 컴퓨터 시스템
        - 서비스 전체를 제공 == Django Web Service
        - 정보를 제공 == DRF API Service
    
    - 서비스 전체를 제공 == Django Web Service
        - Django를 통해 전달받은 HTML에는 하나의 웹 페이지를 구성할 수 있는 모든 데이터가 포함
        - 즉, 서버에서 모든 내용을 렌더링 하나의 HTML 파일로 제공
        - 정보를 포함한 web 서비스를 구성하는 모든 내용을 서버 측에서 제공
            
            ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/446d2807-a599-46d8-9cd9-7df181d54971/Untitled.png)
            
    
    - 정보를 제공 == DRF API Service
        - Django를 통해 관리하는 정보만을 클라이언트에게 제공
        - DRF를 사용하여 JSON으로 변환
            
            ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/5c562628-3fe9-4a6d-a538-9d5a64de8593/Untitled.png)
            
    
2. Client
    - 클라이언트(Client)란?
        - **Server가 제공하는 서비스에 적절한 요청**을 통해 **Server로부터 반환 받은 응답을 사용자에게 표현**하는 기능을 가진 프로그램 혹은 시스템
    
    - Server가 제공하는 서비스에 적절한 요청
        - Server가 정의한 방식대로 요청 인자를 넘겨 요청
        - Server는 정상적인 요청에 적합한 응답 제공
        
        ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/e48b10a6-9fc6-43a6-8a4a-ef65b7bcb649/Untitled.png)
        
    
    - 잘못된 요청 예
        - 아래와 같은 Model이 정의되어 있다면
            
            ```python
            class Article(models.Model):
                title = models.CharField(max_length=100)
                content = models.TextField()
            ```
            
        - 잘못된 field 명으로 요청을 보낼 경우 처리할 수 없음
            
            ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/e76e12bd-9c73-4784-a2ee-9af75790e269/Untitled.png)
            
    
    - **Server로부터 반환 받은 응답을 사용자에게 표현**
        - 사용자의 요청에 적합한 data를 server에 요청하여 응답 받은 결과로 **적절한 화면을 구성**
            
            ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/bc2201c6-612d-4873-8a47-f4f9f86f033c/Untitled.png)
            
    
3. 정리
    - Server는 정보와 서비스를 제공
        - DB와 통신하며 데이터를 생성, 조회, 수정, 삭제를 담당
        - 요청을 보낸 Client에게 정상적인 요청이었다면 처리한 결과를 응답
    - Client는 사용자의 정보 요청을 처리, server에게 응답 받은 정보를 표현
        - Server에게 정보(데이터)를 요청
        - 응답 받은 정보를 가공하여 화면에 표현

### **2. Again DRF**

- Model 구조 확인
    
    ```python
    # articles/models.py
    
    class Article(models.Model):
        # user = models.ForeignKey(settings.AUTH_USER_MODEL, on_delete=models.CASCADE)
        title = models.CharField(max_length=100)
        content = models.TextField()
        created_at = models.DateTimeField(auto_now_add=True)
        updated_at = models.DateTimeField(auto_now=True)
    
    class Comment(models.Model):
        article = models.ForeignKey(Article, on_delete=models.CASCADE)
        content = models.TextField()
        created_at = models.DateTimeField(auto_now_add=True)
        updated_at = models.DateTimeField(auto_now=True)
    ```
    

- 요청 경로 확인
    
    ```python
    # my_api/urls/py
    
    urlpatterns = [
        path('admin/', admin.site.urls),
        path('api/v1/', include('articles.urls')),
        ...
    ]
    ```
    
    ```python
    # articles/urls.py
    
    urlpatterns = [
        path('articles/', views.article_list),
        path('articles/<int:article_pk>/', views.article_detail),
        ...
    ]
    ```
    
- Dummy data 확인
    
    ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/cd03ee99-6121-4e72-89aa-4badea29ddcb/Untitled.png)
    
- 데이터 삽입
    
    ```python
    # migrations 정보는 이미 있음
    $ python manage.py migrate
    
    # articles와 comments 동시 삽입 > N:1 관계 유지
    $ python manage.py loaddata articles.json comments.json
    ```
    

- 서버 실행 후 ,전체 게시글 조회
    - Broswer에서 serve에 전체 게시글 조회 요청 → 데이터 반환 확인
        
        ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/3923fb94-237f-4d5d-8eb2-c8c4fec8bf58/Untitled.png)
        
    
    - Postman에서 올바른 방법으로 요청 → 데이터 반환 확인
        
        ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/1137e030-eeae-4f35-bbf7-31cfb1affb6a/Untitled.png)
        

### **3. Back to Vue**

- 컴포넌트 구조 확인
    
    ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/28880c2d-a651-42df-922d-3b1958b4b58a/Untitled.png)
    

- 메인 페이지 구성
    - `views/ArticleView.vue` component 확인 및 route 등록
        
        
        ```jsx
        // src/views/ArticleView.vue
        
        <template>
          <div>
            <h1>Article Page</h1>
            <hr>
          </div>
        </template>
        
        <script>
        export default {
          name: 'ArticleView',
        }
        </script>
        ```
        
        ```jsx
        // src.router.index.js
        
        import Vue from 'vue'
        import VueRouter from 'vue-router'
        import ArticleView from '@/views/ArticleView'
        
        const routes = [
           {
             path: '/',
             name: 'ArticleView',
             component: ArticleView
           },
        ]
        
        ...
        ```
        
    - `src/App.vue` router-link 주석 해제 및 결과 확인
        
        ```jsx
        // src/App.vue
        
        <nav>
          <router-link :to="{ name: 'ArticleView' }">Articles</router-link>
        </nav>
        ```
        
        ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/5d3c41af-dc7f-4035-a70f-d31f0a21ad8f/Untitled.png)
        
    
    - `components/ArticleList.vue` 확인
        - 전체 게시물을 표현할 컴포넌트
        - 화면 구성을 위한 최소한의 style 포함
        
        ```jsx
        // components/ArticleList.vue
        
        <template>
          <div class="article-list">
            <h3>Article List</h3>
          </div>
        </template>
        
        <script>
        export default {
          name: 'ArticleList',
        }
        </script>
        
        <style>
        .article-list {
          text-align: start;
        }
        </style>
        ```
        
    
    - `views/ArticleView.vue` 주석 해제
        - `ArticleLilst` 하위 컴포넌트 등록
            - 1) 불러오기
            - 2) 등록하기
            - 3) 보여주기
        
        ```jsx
        // views/ArticleView.vue
        
        <template>
          <div>
            <h1>Article Page</h1>
            <ArticleList/>
          </div>
        </template>
        
        <script>
        import ArticleList from '@/components/ArticleList'
        
        export default {
          name: 'ArticleView',
          components: {
            ArticleList,
          },
        }
        </script>
        ```
        
    
    - `components/ArticleListItem.vue` 확인
        - 각 게시글들의 정보를 표현할 컴포넌트
        - 데이터 없이 최소한의 기본 구조만 확인
        
        ```jsx
        // components/ArticleListItem.vue
        
        <template>
          <div>
            <h5>PK</h5>
            <p>제목</p>
            <hr>
          </div>
        </template>
        
        <script>
        export default {
          name: 'ArticleListItem',
        }
        </script>
        ```
        
    
    - `components/ArticleList.vue` 주석 해제
    - `ArticleListItem` 하위 컴포넌트 등록
        - 1) 불러오기
        - 2) 보여주기
        - 3) 등록하기
        
        ```jsx
        // components/ArticleList.vue
        
        <template>
          <div class="article-list">
            <h3>Article List</h3>
            <ArticleListItem/>
          </div>
        </template>
        
        <script>
        import ArticleListItem from '@/components/ArticleListItem'
        
        export default {
          name: 'ArticleList',
          components: {
            ArticleListItem,
          },
        }
        </script>
        ```
        
    
    - `store/index.js` 주석 해제
    - state articles 배열 정의
    - 화면 표현 체크용 데이터 생성
        
        ```jsx
        // store/index.js
        
        export default new Vuex.Store({
          state: {
            articles: [
              {
                id: 1,
                title: '제목',
                content: '내용'
              },
              {
                id: 2,
                title: '제목2',
                content: '내용2'
              },
            ],
          },
        })
        ```
        
    
    - `components/ArticleList.vue` 코드 수정
        - state에서 articles 데이터 가져오기
        - v-for 디렉티브를 활용하여 하위 컴포넌트에서 사용할 article 단일 객체 정보를 pass props
            
            ```jsx
            // components/ArticleList.vue
            
            <template>
              <div class="article-list">
                <ArticleListItem
                  v-for="article in articles"
                  :key="article.id"
                  :article="article"
                />
              </div>
            </template>
            
            <script>
            export default {
              ...
              computed: {
                articles() {
                  return this.$store.state.articles
                }
              }
            }
            </script>
            ```
            
    
    - `components/ArticleListItem.vue` 수정
        - 내려 받은 props 데이터로 화면 구성
        - prop 데이터 타입은 명확하게 표기할 것
            
            ```jsx
            // components/ArticleListItem.vue
            
            <template>
              <div>
                <h5>{{ article.id }}</h5>
                <p>{{ article.title }}</p>
                <hr>
              </div>
            </template>
            
            <script>
            export default {
              name: 'ArticleListItem',
              props: {
                article: Object,
              }
            }
            </script>
            ```
            

### **4. Vue with DRF**

- AJAX 요청 준비
    - **axios** 설정
        - 설치
            - `$ npm install axios`
        - `store/index.js` 에서 불러오기
            - 요청 보낼 API server 도메인 변수에 담기
                
                ```jsx
                // store/index.js
                
                import axios from 'axios'
                
                const API_URL = 'http://127.0.0.1:8000'
                ```
                
    
    - `store/index.js` 주석 해제
        - `getArticles` 메서드 정의
        - 요청 보낼 경로 확인 필수
        - 성공 시 `.then`
        - 실패 시 `.catch`
        
        ```jsx
        // store/index.js
        
        export default new Vuex.Store({
          ...
          actions: {
            getArticles(context) {
              axios({
                method: 'get',
                url: `${API_URL}/api/v1/articles/`
              })
              .then((res) => {
                console.log(res, context)
              })
              .catch((err) => {
                console.log(err)
              })
            }
          [},](https://www.notion.so/5-1-Vue-with-DRF-6b6df8190f314b51a30da09226c9b3b9)
        })
        ```
        
    
    - `views/ArticleView.vue` 주석 해제
        - `getArticles` actions 호출
        - 인스턴스가 생성된 직후 요청을 보내기 위해 created() hook 사용
            
            ```jsx
            //views/ArticleView.vue
            
            <script>
            export default {
              name: 'ArticleView',
              components: {
                ArticleList,
              },
              computed:{
              },
              created() {
                this.getArticles()
              },
              methods: {
                getArticles() {
                  this.$store.dispatch('getArticles')
                }
              }
            }
            </script>
            ```
            

- 요청 결과 확인
    - Vue와 Django 서버를 모두 켠 후 메인 페이지 접속
    - Server에서는 200을 반환하였으나 Client Console에서는 Error를 확인
        
        ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/106de0f3-e768-4f83-914c-cdd839275a83/Untitled.png)
        

---
