# **Vue Router**

## **1. Routing**

1. Routing
    - 네트워크에서 경로를 선택하는 프로세스
    
    - 웹 서비스에서의 라우팅
        - 유저가 방문한 URL에 대해 적절한 결과를 응답하는 것
    - 예시
        - `/articles/index/`에 접근하면 articles의 index에 대한 결과를 보내줌
    
2. Routing in SSR
    - Server가 모든 라우팅을 통제
    - URL로 요청이 들어오면 응답으로 완성된 HTML 제공
        - Django로 보낸 요청의 응답 HTML은 완성본인 상태였음
    - 결론적으로, Routing(URL)에 대한 결정권을 서버가 가짐
    
3. Routing in SPA / CSR
    - 서버는 하나의 HTML(index.html)만을 제공
    - 이후에 모든 동작은 하나의 HTML 문서 위에서 JavaScript 코드를 활용
        - DOM을 그리는데 필요한 추가적인 데이터가 있다면 axios와 같은 AJAX 요청을 보낼 수 있는 도구를 사용하여 데이터를 가져오고 처리
    - 즉, **하나의 URL만 가질 수 있음**
    
4. Why routing?
    - 그럼 동작에 따라 URL이 반드시 바뀌어야 하나?
        - 그렇지 않다! 단, 유저의 사용성 관점에서는 필요함
    - Routing이 없다면
        - 유저가 URL을 통한 페이지의 변화를 감지할 수 없음
        - 페이지가 무엇을 렌더링 중인지에 대한 상태를 알 수 없음
            - 새로고침 시 처음 페이지로 돌아감
            - 링크를 공유할 시 처음 페이지만 공유 간으
        - 브라우저의 뒤로 가기 기능을 사용할 수 없음

### **2. Vue Router**

1. Vue Router
    - Vue의 공식 라우터
    - SPA 상에서 라우팅을 쉽게 개발할 수 있는 기능을 제공
    - 라우트(routes)에 컴포넌트를 매핑한 후, 어떤 URL에서 렌더링 할지 알려줌
        - 즉, SPA를 MPA처럼 URL을 이동하면서 사용 가능
        - SPA의 단점 중 하나인 **“URL이 변경되지 않는다.”를 해결**
    - [참고] MPA (Multiple Page Application)
        - 여러 개의 페이지로 구성된 애플리케이션
        - SSR 방식으로 렌더링
    
2. Vue Router 시작하기
    - Vuex와 마찬가지의 방식으로 설치 및 반영
        
        ```jsx
        $ vue create vue-router-app  // Vue 프로젝트 생성
        
        $ cd vue-router-app          // 디렉토리 이동
        
        $ vue add router             // Vue CLI를 통해 router plugin 적용
        ```
        
        - 기존에 프로젝트를 진행하고 있던 도중에 router를 추가하게 되면 App.vue를 덮어쓰므로 필요한 경우 명령어를 실행하기 전에 파일을 백업해두어야 함
    
    - history mode 사용여부 → Yes
        
        ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/98fc43c6-6a15-4ba1-9074-86bdcdcdad9d/Untitled.png)
        
    
3. History mode
    - 브라우저의 History API를 활용한 방식
        - 새로고침 없이 URL 이동 기록을 남길 수 있음
    - 우리에게 익숙한 URL 구조로 사용 가능
        - 예시) http://localhost:8080/index
    - [참고] History mode를 사용하지 않으면 Default값인 hash mode로 설정됨 (’#’을 통해 URL을 구분하는 방식)
        - 예시) http://localhost:8080#index
    
4. Vue Router 시작하기
    - `App.vue`
        - `router-link` 요소 및 `router-view`가 추가됨
        
        ```html
        <template>
          <div id="app">
            <nav>
              <router-link to="/">Home</router-link> |
              <router-link to="/about">About</router-link>
            </nav>
            <router-view/>
          </div>
        </template>
        ```
        
    
    - `router/index.js` 생성  → Django에서 urls.py 역할
    - `views` 폴더 생성
        
        ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/362515a5-79dc-45bf-971e-0f945efd779c/Untitled.png)
        
    
    - 서버 실행하기
        
        ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/1c0f5ceb-209c-4bdd-ac03-22dfb057bd16/Untitled.png)
        
    
5. **router-link**
    - a 태그와 비슷한 기능 → URL을 이동시킴
        - routes에 등록된 컴포넌트와 매핑됨
        - 히스토리 모드에서 router-link는 클릭 이벤트를 차단하여 a 태그와 달리 브라우저가 페이지를 다시 로드 하지 않도록 함
    - 목표 경로는 **‘to’** 속성으로 지정됨
    - 기능에 맞게 HTML에서 a 태그로 rendering 되지만, 필요에 따라 다른 태그로 바꿀 수 있음
    
    - 개발자 도구 확인
        
        ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/5169cb7c-a6bc-494b-98ad-4f933c1e9340/Untitled.png)
        
    
6. **router-view**
    - 주어진 URL에 대해 일치하는 컴포넌트를 렌더링 하는 컴포넌트
    - 실제 component가 DOM에 부착되어 보이는 자리를 의미
    - router-link를 클릭하면 routes에 매핑된 컴포넌트를 렌더링
    
    - Django에서의 block tag와 비슷함
        - `App.vue`는 base.html의 역할
        - `router-view`는 block 태그로 감싼 부분
    
    - 개발자 도구 확인
        
        ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/9b1100e5-7bc5-460a-9973-8b4fefc154e6/Untitled.png)
        
    
7. `src/router/index.js`
    - 라우터에 관련된 정보 및 설정이 작성되는 곳
    - Django에서의 urls.py에 해당
    - routes에 URL과 컴포넌트를 매칭
        
        ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/ab9681b8-f4b3-468f-8571-9792178cc010/Untitled.png)
        
    
    - Django와의 비교
        
        ```jsx
        const routes = [
          {
            path: '/',
            name: 'home',
            component: HomeView
          },
        ]
        ```
        
        ```python
        urlpatterns = [
            path('', views.home, name='home'),
        ]
        ```
        
    
8. src/Views
    - router-view에 들어갈 component 작성
    - 기존 컴포넌트를 작성하던 곳은 components 폴더 뿐이었지만 이제 두 폴더로 나뉘어짐
    - 각 폴더 안의 .vue 파일들이 기능적으로 다른 것은 아님
    
    - 이제 폴더별 컴포넌트 배치는 다음과 같이 진행 (규약은 아님)
    - `views/`
        - routes에 매핑되는 컴포넌트,
        - 즉 <router-view>의 위치에 렌더링 되는 컴포넌트를 모아두는 폴더
        - 다른 컴포넌트와 구분하기 위해 View로 끝나도록 만드는 것을 권장
        - ex) App 컴포넌트 내부의 AboutView & HomeView 컴포넌트
    - `components/`
        - routes에 매핑된 컴포넌트의 하위 컴포넌트를 모아두는 폴더
        - ex) HomeView 컴포넌트 내부의 HelloWorld 컴포넌트

### **3. Vue Router 실습**

1. 주소를 이동하는 2가지 방법
    - 1) 선언적 방식 네비게이션
    - 2) 프로그래밍 방식 네비게이션
    
2. 선언적 방식 네비게이션
    - router-link의 **‘to’** 속성으로 주소 전달
        - routes에 등록된 주소와 매핑된 컴포넌트로 이동
        
        ```jsx
        // App.vue
        
        <template>
          <div id="app">
            <nav>
              <router-link to="/">Home</router-link> |
              <router-link to="/abount">About</router-link>
            </nav>
            <router-view/>
          </div>
        </template>
        ```
        
    
3. Named Routes
    - 이름을 가지는 routes
        - Django에서 path 함수의 name 인자의 활용과 같은 방식
            
            ```jsx
            // router/index.js
            
            const routes = [
              {
                path: '/',
                name: 'home',
                component: HomeView
              },
            ]
            ```
            
    
4. 선언적 방식 네비게이션
    - 동적인 값을 사용하기 때문에 v-bind를 사용해야 정상적으로 작동
        
        ```jsx
        // App.vue
        
        <template>
          <div id="app">
            <nav>
              <router-link :to="{ name: 'home' }">Home</router-link> |
              <router-link :to="{ name: 'about' }">Abount</router-link>
            </nav>
            <router-view/>
          </div>
        </template>
        ```
        
    
5. 프로그래밍 방식 네비게이션 🔥
    - Vue 인스턴스 내부에서 라우터 인스턴스에 `$router`로 접근할 수 있음
    - 다른 URL로 이동하려면 `this.$router.push`를 사용
        - history stack에 이동할 URL을 넣는(push) 방식
        - history stack에 기록이 남기 때문에 사용자가 브라우저의 뒤로 가기 버튼을 클릭하면 이전 URL로 이동할 수 있음
    - 결국 `<router-link :to="...">`를 클릭하는 것과 `$router.push(...)`를 호출하는 것은 같은 동작
    
    - 동작 원리는 선언적 방식과 같음
        
        ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/a19821fb-7ef1-453e-8791-dd09155352cd/Untitled.png)
        
        ```jsx
        // AboutView.vue
        
        <template>
          <div class="about">
            <h1>This is an about page</h1>
            <!-- 선언적 방식 -->
            <router-link to="{ name: 'home' }">Home</router-link> |
            <button @click="toHome">홈으로</button>
          </div>
        </template>
        
        <script>
          export default {
            name: 'AboutView',
            methods: {
              toHome() {
                this.$router.push({ name: 'home' })  // 프로그래밍 방식
              }
            }
          }
        </script>
        ```
        
    
6. Dynamic Route Matching
    - 동적 인자 전달
        - URL의 특정 값을 변수처럼 사용할 수 있음
    - ex) Django에서의 variable routing
    
    - `HelloView.vue` 작성 및 route 추가
    - route를 추가할 때 동적 인자를 명시
        
        ```jsx
        // router/index.js
        
        import HelloView from '@/views/HelloView'
        
        const routes = [
          ...
          {
            path: '/hello/:userName',  // 동적 인자 사용
            name: 'hello',
            component: HelloView,
          },
        ]
        ```
        
        ```jsx
        // views/HelloView.vue
        
        <template>
          <div>
          </div>
        </template>
        
        <script>
        export default {
          name: 'HelloView',
        }
        </script>
        
        <style>
        
        </style>
        ```
        
    
    - `$route.params`로 변수에 접근 가능
        
        ```jsx
        // views/HelloView.vue
        
        <template>
          <div>
            <h1>hello, {{ $route.params.userName }}</h1>
          </div>
        </template>
        
        <script>
        export default {
          name: 'HelloView',
        }
        </script>
        ```
        
    
    - 다만 HTML에서 직접 사용하기 보다는 data에 넣어서 사용하는 것을 권장
        
        ```jsx
        // views/HelloView.vue
        
        <template>
          <div>
            <h1>hello, {{ userName }}</h1>
          </div>
        </template>
        
        <script>
        export default {
          name: 'HelloView',
          data() {
            return {
              userName: this.$route.params.userName
            }
          }
        }
        </script>
        ```
        
    
7. Dynamic Route Matching - 선언적 방식 네비게이션
    - App.vue에서 harry에게 인사하는 페이지로 이동해보기
    - params를 이용하여 동적 인자 전달 가능
        
        ```jsx
        // App.vue
        
        <template>
          <div id="app">
            <nav>
              <router-link :to="{ name: 'home' }">Home</router-link> |
              <router-link :to="{ name: 'about' }">About</router-link> | 
              <router-link :to="{ name: 'hello', params: { userName: 'harry'} }">Hello</router-link> |
            </nav>
            <router-view/>
          </div>
        </template>
        ```
        
    
8. Dynamic Route Matching - 프로그래밍 방식 네비게이션
    - AboutView에서 데이터를 입력 받아 HelloView로 이동하여 입력받은 데이터에게 인사하기
        
        ```jsx
        // AboutView.vue
        
        <template>
          <div class="about">
            ...
            <input
              type="text"
              @keyup.enter="goToHello"
              v-model="inputData"
            >
          </div>
        </template>
        ```
        
        ```jsx
        // AboutView.vue
        
        <script>
        export default {
          name: 'AboutView',
          data() {
            return {
              inputData : null
            }
          },
          methods: {
            ...
            gotoHello() {
              this.$router.push({ name: 'hello', params: { userName: this.inputData } })
            },
          }
        }
        </script>
        ```
        
    
9. route에 컴포넌트를 등록하는 또다른 방법
    - router/index.js에 컴포넌트를 등록하는 또다른 방식이 주어지고 있음(about)
        - 기존방식
            
            ```jsx
            // router/index.js
            
            import HomeView from '../views/HomeView.vue'
            
            const routes = [
              {
                path: '/',
                name: 'home',
                component: HomeView
              },
            ]
            ```
            
        - Lazy-loading
            
            ```jsx
            // router/index.js
            
            // lazy-loading 방식 (첫 로딩에 렌더링 하지않고 해당 라우터가 동작할 때 컴포넌트를 렌더링 한다.)
            const routes = [
              {
                path: '/about',
                name: 'about',
                component: () => import('../views/AboutView.vue'),
              }
            ]
            ```
            
    
10. Lazy-loading
    - 모든 파일을 한 번에 로드하려고 하면 모든 걸 다 읽는 시간이 매우 오래 걸림
    - 미리 로드를 하지 않고 특정 라우트에 방문할 때 매핑된 컴포넌트의 코드를 로드하는 방식을 활용할 수 있음
        - 모든 파일을 한 번에 로드하지 않아도 되기 때문에 최초에 로드하는 시간이 빨라짐
        - 당장 사용하지 않을 컴포넌트는 먼저 로드하지 않는 것이 핵심

---