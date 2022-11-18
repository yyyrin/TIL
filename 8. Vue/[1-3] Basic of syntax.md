# **Basic of syntax**

1. Template Syntax
    - Vue2 guide > template syntax 참고<br>

    - **렌더링 된 DOM**을 기본 Vue instance의 data에 **선언적으로 바인딩**할 수 있는 **HTML 기반 template syntax**를 사용
        - 렌더링 된 DOM - 브라우저에 의해 보기 좋게 그려질 HTML 코드<br>

        - HTML 기반 template syntax - HTML 코드에 직접 작성할 수 있는 문법 제공
        - 선언적으로 바인딩 - Vue instance와 DOM을 연결
<br><br><br>

2. Template Interpolation
    
    - `06_basic_of_syntax.html`에서 진행
    <br>

    - 가장 기본적인 바인딩(연결) 방법
    - 중괄호 2개로 표기
    - DTL과 동일한 형태로 작성
    - Template interpolation 방법은 HTML을 **일반 텍스트**로 표현
    <br><br>
    ```html
    <div id="app">
      <p>메시지: {{ msg }}</p>
    	<p>HTML 메시지 : {{ rawHTML }}</p>
    </div>
    
    <!-- Vue CDN -->
    <script>
      const app = new Vue({
        el: '#app',
        data: {
          msg: 'Text interpolation',
          rawHTML: '<span style="color:red"> 빨간 글씨</span>'
        }
      })
    </script>
    ```
<br><br>

3. RAW HTML
    
    - **v-html** directive을 사용하여 data와 바인딩<br>

    - directive - HTML 기반 template syntax
    - HTML의 기본 속성이 아닌 Vue가 제공하는 특수 속성의 값으로 data를 작성
   <br><br> 
    ```html
    <div id="app">
      <p>HTML 메시지 :
        <span v-html="rawHTML"></span>
      </p>
    </div>
    
    <!-- Vue CDN -->
    <script>
      const app = new Vue({
        el: '#app',
        data: {
          rawHTML: '<span style="color:red"> 빨간 글씨</span>'
        }
      })
    </script>
    ```
<br><br>

4. [참고] JS 표현식
    
    
    - 표현식 형태로 작성 가능
    <br><br>
    
    ```html
    <div id="app">
      <p>{{ msg.split('').reverse().join('') }}</p>
    </div>
    
    <!-- Vue CDN -->
    <script>
      const app = new Vue({
        el: '#app',
        data: {
          msg: 'Text interpolation',
        }
      })
    </script>
    ```
<br><br>    

---