# **View decorators**

1. 개요
    - View decorators 를 사용해 view 함수를 단단하게 만들기
<br><br><br>

2. 데코레이터 (Decorator)
    - 기존에 작성된 함수에 기능을 추가하고 싶을 때, 해당 함수를 수정하지 않고 기능을 추가해주는 함수<br>

    - Django는 다양한 HTTP 기능을 지원하기 위해 view 함수에 적용할 수 있는 여러 데코레이터를 제공
    - 데코레이터 동작 예시
    
    ```python
    def hello(func):
    		def wrapper():
    				print('HIHI')
    				fun()
    				print('HIHI')
    		return wrapper
    
    @hello
    def bye():
    		print('byebye')
    
    bye()
    ```
    
    ```python
    # 출력
    
    HIHI
    byebye
    HIHI
    ```
<br><br>
---    

### **1. Allowed HTTP methods**

1. 개요
    - django.views.decorators.http의 데코레이터를 사용하여 요청 메서드를 기반으로 접근을 제한할 수 있음<br>

    - 일치하지 않는 메서드 요청이라면 405 Method Not Allowed를 반환
    - 메서드 목록
        - `require_http_methods()`<br>

        - `require_POST()`
        - `require_safe()`
<br><br><br>

2. \[참고] 405 Method Not Allowed
    - 요청 방법이 서버에 전달 되었으나 사용 불가능한 상태
<br><br><br>

3. require_http_methods()
    - View 함수가 특정한 요청 method만 허용하도록 하는 데코레이터
        
        ```python
        # views.py
        
        from django.views.decorators.http import require_http_methods
        
        @require_http_methods(['GET', 'POST'])
        def create(request):
        		pass
        
        @require_http_method(['GET', 'POST'])
        def update(request, pk):
        		pass
        ```
<br><br>

4. require_POST()
    - View 함수가 POST 요청 method만 허용하도록 하는 데코레이터
        
        ```python
        # views.py
        
        from django.views.decorators.http import require_http_methods, **require_POST**
        
        **@require_POST**
        def delete(request, pk):
        		article = Article.objects.get(pk=pk)
        		article.delete()
        		return redirect('articles:index')
        ```
       <br> 
    - url로 delete 시도 후 서버 로그에서 405 http status code 확인 해보기
        
        ```python
        Method Not Allowed (GET): /articles/3/delete/
        [04/Jan/2022 04:52:10] "GET /articles/3/delete/ HTTP/1.1" 405 0
        ```
<br><br>

5. require_safe()
    - `require_GET`이 있지만 Django에서는 `require_safe`를 사용하는 것을 권장
        
        ```python
        # views.py
        
        from django.views.decorators.http import require_http_methods, require_POST, **require_safe**
        
        **@require_safe**
        def index(request):
        		...
        
        **@require_safe**
        def detail(request, pk):
        		...
        ```
        
<br><br>

---

# **마무리**

- Django Form Class<br>

    - Django 프로젝트의 주요 유효성 검사 도구<br>

    - 공격 및 데이터 손상에 대한 중요한 방어 수단
    - 유효성 검사에 대한 개발자에게 강력한 편의를 제공
    <br><br>
- View 함수 구조 변화
    - HTTP requests 처리에 따른 구조 변화
<br><br><br>
---
