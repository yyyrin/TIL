# **Admin site**

1. 개요
    - **Django의 가장 강력한 기능 중 하나**인 automatic admin interface 알아보기<br>

    - “관리자 페이지”
        - 사용자가 아닌 서버의 관리자가 활용하기 위한 페이지<br>

        - 모델 class를 admin.py에 등록하고 관리
        - 레코드 생성 여부 확인에 매우 유용하며 직접 레코드를 삽입할 수도 있음
<br><br><br>

2. admin 계정 사용
    
    `$ python manage.py createsuperuser`
    
    - username과 password를 입력해 관리자 계정 생성<br>

        - email은 선택사항이기 때문에 입력하지 않고 enter를 입력하는 것이 가능<br>

        - 비밀번호 생성 시 보안상 터미널에 입력되지 않으니 무시하고 입력을 이어가도록 함
<br><br><br>

3. admin site 로그인
    - http://127.0.0.1:8000/admin/ 로 접속 후 로그인<br>

    - 계정만 만든 경우 Django 관리자 화면에서 모델 클래스는 모이지 않음
<br><br><br>

4. admin에 모델 클래스 등록
    - 모델의 record를 보기 위해서는 admin.py에 등록 필요

        ```html
        # articles/admin/py
        
        from django.contrib import admin
        from .models import Article
        
        admin.site.register(Article)
        ```
<br><br>        
---

# **마무리**

- Model<br>

    - Django는 Model을 통해 데이터에 접속하고 관리
- ORM
    - 객체지향  프로그래밍을 이용한 DB 조작
- Migrations
    - 모델에 생긴 변화(필드 추가, 모델 삭제 등)를 DB에 반영하는 방법(과정)
- HTTP request & response
    - 요청에 행동을 표현하는 HTTP request method<br>

    - 요청에 대한 성공 여부 응답을 숫자로 표현하는 HTTP response status codes
<br><br><br>

---
