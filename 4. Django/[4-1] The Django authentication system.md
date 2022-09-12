## **1. The Django authentication system**

1. 개요
    - Django authentication system(인증 시스템)은 **인증(Authentication)**과 **권한(Authorization)** 부여를 함께 제공(처리)하며, 이러한 기능을 일반적으로 인증 시스템이라고 함<br>

    - 필수 구성은 settings.py에 이미 포함되어 있으며 INSTALLED_APPS에서 확인 가능
        - `django.contrib.auth`
    - **Authentication (인증)**
        - 신원 확인<br>

        - 사용자가 자신이 누구인지 확인하는 것
    - **Authorization (권한, 허가)**
        - 권한 부여<br>

        - 인증된 사용자가 수행할 수 있는 작업을 결정
<br><br><br>

2. 사전 설정
    - 두번째 app accounts 생성 및 등록<br>

        - auth와 관련된 경로나 키워드들을 Django 내부적으로 accounts라는 이름으로 사용하고 있기 때문에 되도록 accounts로 지정하는 것을 권장<br>

        - 다른 이름으로 설정해도 되지만 나중에 추가 설정을 해야할 일들이 생기게 됨
            
            ```python
            $ python manage.py startapp accounts
            ```
            
            ```python
            # settings.py
            
            INSTALLED_APPS = [
            		'articles',
            		'accounts',
            		...
            ```
            
    - url 분리 및 매핑
        
        ```python
        # accounts/urls.py
        
        from django.urls import path
        from . import views
        
        app_name = 'accounts'
        urlpatterns = [
        
        ]
        ```
        
        ```python
        # crud/urls.py
        
        urlpatterns = [
        		...,
        		path('accounts/', include('accounts.urls')),
        ]
        ```
<br><br>

---        

### **1. Substituting a custom User model**

1. 개요
    - “Custom User Model로 **대체하기**”<br>

    - 기본 User Model을 필수적으로 Custom User model로 대체하는 이유 이해하기
    - Django는 기본적으로 인증 시스템과 여러 가지 필드가 포함된 User Model을 제공, 대부분의 개발 환경에서 기본 User Model을 Custom User Model로 대체함
    - 개발자들이 작성하는 일부 프로젝트에서는 Django에서 제공하는 built-in User model의 기본 인증 요구사항이 적절하지 않을 수 있음
        - 예를 들어, 내 서비스에서 회원가입 시 username 대신 email을 식별 값으로 사용하는 것이 더 적합한 사이트인 경우,<br>

        - Django의 User Model은 기본적으로 username을 식별 값으로 사용하기 때문에 적합하지 않음
    - Django는 현재 프로젝트에서 사용할 User Model을 결정하는 **AUTH_USER_MODEL** 설정 값으로 Default User Model을 재정의(override)할 수 있도록 함
<br><br><br>

2. AUTH_USER_MODEL
    - 프로젝트에서 User를 나타낼 때 사용하는 모델<br>

    - 프로젝트가 진행되는 동안 (모델을 만들고 마이그레이션 한 후) 변경할 수 없음
    - 프로젝트 시작 시 설정하기 위한 것이며, 참조하는 모델은 첫 번째 마이그레이션에서 사용할 수 있어야 함
        - 즉, 첫번째 마이그레이션 전에 확정 지어야 하는 값
    - 다음과 같은 기본 값을 가지고 있음
        
        ```python
        # settings.py
        
        AUTH_USER_MODEL = 'auth.User'
        ```
<br><br>

3. \[참고] settings의 로드 구조
    - AUTH_USER_MODEL은 settings.py에서 보이지 않는데 어디에 기본 값이 작성되어 있는 걸까?<br>

        - 우리가 작성하는 settings.py는 사실 `global_settings.py`를 상속받아 재정의하는 파일임<br>

        - [https://github.com/django/django/blob/main/django/conf/global_settings.py](https://github.com/django/django/blob/main/django/conf/global_settings.py)
<br><br><br>
---

### **2. How to substituting a custom User model**

1. 개요
    - “custom User model로 대체하기”<br>

    - 대체하는 과정을 외우기 어려울 경우 해당 공식문서를 보며 순서대로 진행하는 것을 권장
        - [https://docs.djangoproject.com/en/3.2/topics/auth/customizing/#substituting-a-custom-user-model](https://docs.djangoproject.com/en/3.2/topics/auth/customizing/#substituting-a-custom-user-model)
<br><br><br>

2. 대체하기
    - AbstractUser를 상속받는 커스텀 User 클래스 작성<br>

    - 기존 User 클래스도 AbstractUser를 상속받기 때문에 커스텀 User 클래스도 완전히 같은 모습을 가지게 됨
        - [https://github.com/django/django/blob/main/django/contrib/auth/models.py#L405](https://github.com/django/django/blob/main/django/contrib/auth/models.py#L405)
    
    ```python
    # accounts/models.py
    
    from django.contrib.auth.models import AbstractUser
    
    class User(AbstractUser):
    		pass
    ```
    <br>
    - Django 프로젝트에서 User를 나타내는데 사용하는 모델을 방금 생성한 커스텀 User 모델로 지정<br>

        
        ```python
        # settings.py
        
        AUTH_USER_MODEL = 'accounts.User'  # 우리가 만든 User model
        ```
        
    - admin.py에 커스텀 User 모델을 등록
        - 기본 User 모델이 아니기 때문에 등록하지 않으면 admin site에 출력되지 않음
            
            ```python
            # accounts/admin.py
            
            from django.contrib import admin
            from django.contrib.auth.admin import UserAdmin
            from .models import User
            
            admin.site.register(User, UserAdmin)
            ```
<br><br>            
    
3. \[참고] User 모델 상속 관계
    
    ![user model img](./images/user%20model.png)
<br><br><br>

4. \[참고] AbstractUser
    - “관리자 권한과 함께 완전한 기능을 가지고 있는 User model을 구현하는 추상 기본클래스”<br>

    - **Abstract base classes ( 추상 기본 클래스)**
        - 몇 가지 공통 정보를 여러 다른 모델에 넣을 때 사용하는 클래스<br>

        - 데이터베이스 테이블을 만드는 데 사용되지 않으며, 대신 다른 모델의 기본 클래스로 사용되는 경우 해당 필드가 하위 클래스의 필드에 추가됨<br>

        - [https://docs.python.org/3/library/abc.html](https://docs.python.org/3/library/abc.html)
<br><br><br>

5. \[주의] 프로젝트 중간에 AUTH_USER_MODEL 변경하기
    - 모델 관계에 영향을 미치기 때문에 훨씬 더 어려운 작업이 필요<br>

        - 예를 들면 변경사항이 자동으로 수행될 수 없기 때문에 DB 스키마를 직접 수정하고, 이전 사용자 테이블에서 데이터를 이동하고, 일부 마이그레이션을 수동으로 다시 적용해야 하는 등…
    - 결론은 중간 변경은 권장하지 않음 **(프로젝트 처음에 진행하기)**
<br><br><br>

6. 데이터베이스 초기화
    - 수업 진행을 위한 데이터베이스 초기화 후 마이그레이션 (프로젝트 중간일 경우)<br>

    - migrations 파일 삭제
        - migrations 폴더 및 \__init__.py는 삭제하지 않음<br>

        - 번호가 붙은 파일만 삭제
    - db.sqlit3 삭제
    - migrations 진행
        - makemigrations<br>

        - migrate
<br><br><br>

7. custom User로 변경된 테이블 확인
    - 이제 auth_user 테이블이 아니라 accounts_user 테이블을 사용하게 됨<br>

    - 어플리케이션이름_클래스명
        
        ![custom user img](./images/custom%20user.png)
<br><br><br>

8. 반드시 User 모델을 대체해야 할까?
    - Django는 새 프로젝트를 시작하는 경우 비록 기본 User 모델이 충분 하더라도 커스텀 User 모델을 설정하는 것을 **강력하게 권장(highly recommended)**<br>

    - 커스텀 User 모델은 **기본 User 모델과 동일하게 작동 하면서도 필요한 경우 나중에 맞춤 설정할 수 있기 때문**
        - 단, User 모델 대체 작업은 프로젝트의 모든 migrations 혹은 첫 migrate를 실행하기 전에 이 작업을 마쳐야 함
<br><br><br>

---
