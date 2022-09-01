# **Namespace**

1. 개요
    - 개체를 구분할 수 있는 범위를 나타내는 namespace(이름 공간)에 대한 이해
<br><br><br>

2. Namespace의 필요성
    - 두 번째 app pages의 index  페이지를 작성해보고 어떤 문제가 발생하는지 확인해보기<br>
        
        ```python
        # pages/urls.py
        
        from django.urls import path
        from . import views
        
        urlpatterns = [
            path('index/', views.index, name='index')
        ]
        ```
        
        ```python
        # pages/views.py
        
        def index(rerquest):
            return render(request, 'index.html')
        ```
        
        ```html
        <!-- pages/templates/index.html -->
        
        {% extends 'base.html' %}
        
        {% block content %}
          <h1>두 번째 앱의 index</h1>
        {% endblock content %}
        ```
        
        ```html
        <!-- articles/templates/index.html -->
        
        {% extends 'base.html' %}
        {% block content %}
          <h1>만나서 반가워요!</h1>
          <a href="{% url 'greeting' %}">greeting</a>
          <a href="{% url 'dinner' %}">dinner</a>
          <a href="{% url 'throw' %}">throw</a>
        
          <a href="{% url 'index' %}">두 번째 앱 index로 이동</a>
        {% endblock content %}
        ```
<br><br>        
    
3. 2가지 문제 발생
    - \1) articles app index 페이지에 작성한 두번째 앱 index로 이동하는 하이퍼링크를 클릭 시 현재 페이지로 다시 이동
      - URL namespace<br>
    - \2) pages app의 index url (http://127.0.0.1:8000/pages/index/)로 직접 이동해도 articles app의 index 페이지가 출력됨
      - Template namespace
<br><br><br>

### **1. URL namespace**

1. 개요
    - URL namespace를 사용하면 서로 다른 앱에서 동일한 URL 이름을 사용하는 경우에도 이름이 지정된 URL을 고유하게 사용할 수 있음
    - **app_name** attribute를 작성해 URL namespace를 설정
        
        
        ```python
        # articles/urls.py
        
        app_name = 'articles'
        urlpatterns = [
            ...,
        ]
        ```
        
        ```python
        # pages/urls.py
        
        app_name = 'pages'
        urlpatterns = [
            ...,
        ]
        ```
<br><br>

2. URL tag의 변화
    - `{% url ‘index’ %}`  →  `{% url ‘articles:index’ %}`
<br><br><br>

3. 기존 URL tag 변경
    - app_name을 지정한 이후에는 url 태그에서 반드시 `app_name:url_name` 형태로만 사용해야 함.<br>

    - 그렇지 않으면 NoReverceMatch 에러가 발생
    
    ```html
    <!-- articles/templates/index.html -->
    
    {% extends 'base.html' %}
    
    {% block content %}
      <h1>만나서 반가워요!</h1>
      <a href="{% url 'articles:greeting' %}">greeting</a>
      <a href="{% url 'articles:dinner' %}">dinner</a>
      <a href="{% url 'articles:throw' %}">throw</a>
    
      <a href="{% url 'pages:index' %}">두 번째 앱 index로 이동</a>
    {% endblock content %}
    ```
    
    ```html
    <!-- catch.html -->
    
    <a href="{% url 'articles:throw' %}">다시 던지러</a>
    
    <!-- greeting, dinner.html -->
    
    <a href="{% url 'articles:index' %}">뒤로</a>
    ```
    
    ```html
    <!-- throw.html -->
    
    {% extends 'base.html' %}
    
    {% block content %}
      <h1>Throw</h1>
      <form action="{% url 'articles:catch' %}" method="GET">
        <label for="message">Throw</label>
        <input type="text" id="message" name="message">
        <input type="submit" value="던져">
      </form>
    
      <a href="{% url 'articles:index' %}">뒤로</a>
    {% endblock content %}
    ```
<br><br>

4. URL 참조
    - “:” 연산자를 사용하여 지정<br>

        - 예를 들어, app_name이 articles이고 URL name이 index인 주소 참조는 **articles:index**가 됨
<br><br><br>

### **2. Template namespace**

1. 개요
    - Django는 기본적으로 **app_name/templates/** 경로에 있는 templates 파일들만 찾을 수 있으며, settings.py의 INSTALLED_APPS에 작성한 app 순서로 template을 검색 후 렌더링 함<br>

    - 바로 이 속성 값이 해당 경로를 활성화하고 있음
    
    ```python
    # settings.py
    
    TEMPLATES = [
        {
            ...,
            'APP_DIRS': True,
            ...
        },
    ]
    ```
<br><br>

2. 디렉토리 생성을 통해 물리적인 이름공간 구분
    - Django templates의 기본 경로에 app과 같은 이름의 폴더를 생성해 폴더 구조를 **app_name/templates/app_name/** 형태로 변경<br>

    - Django templates의 기본 경로 자체를 변경할 수 없기 때문에 물리적으로 이름 공간을 만드는 것
    
    ```python
    articles/
        templates/
    				index.html
    				...
    pages/
    		templates/
    		pages/
    			index.html
    			...
    ```
<br><br><br>

3. 템플릿 경로 변경
    - 폴더 구조 변경 후 변경된 경로로 해당하는 모든 부분을 수정하기<br>
        
        ```python
        # articles/views.py
        
        return render(request, 'articles/index.html')
        ```
        
        ```python
        # pages/views.py
        
        return render(request, 'pages/index.html')
        ```
<br><br>        
    
4. 반드시 Template namespace를 고려해야 할까?
    - 만약 단일 앱으로만 이루어진 프로젝트라면 상관없음<br>
    
    - 여러 앱이 되었을 때에도 템플릿 파일 이름이 겹치지 않게 하면 되지만, 앱이 많아지면 대부분은 같은 이름의 템플릿 파일이 존재하기 마련
<br><br><br>

---
