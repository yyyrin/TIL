# **DRF Auth System**

## **1. Authentication & Authorization**

1. Authentication - 인증, 입증
    - 자신이라고 주장하는 사용자가 누구인지 확인하는 행위

    - 모든 보안 프로세스의 첫 번째 단계 (가장 기본 요소)
    - 즉, 내가 누구인지를 확인하는 과정
    - 401 Unauthorized

        - 비록 HTTP 표준에서는 “미승인(unauthorized)”을 명확히 하고 있지만, 의미상 이 응답은 “비인증(unauthenticated)”을 의미

<br><br><br>

2. Authentication - 권한 부여, 허가
    - 사용자에게 특정 리소스 또는 기능에 대한 액세스 권한을 부여하는 과정 (절차)

    - 보안 환경에서 권한 부여는 항상 인증이 먼저 필요함
        - 사용자는 조직에 대한 액세스 권한을 부여 받기 전에 먼저 자신의 ID가 진짜인지 먼저 확인해야 함
    - 서류의 등급, 웹 페이지에서 글을 조회 & 삭제 & 수정할 수 있는 방법, 제한 구역
        - 인증이 되었어도 모든 권한을 부여 받는 것은 아님
    - 403 Forbidden
        - 401과 다른 점은 서버는 클라이언트가 누구인지 알고 있음(권한은 없음)

<br><br><br>

3. Authentication and authorization work together
    - 회원가입 후, 로그인 시 서비스를 이용할 수 있는 권한 생성

        - 인증 이후 권한이 따라오는 경우가 많음
    - 단, 모든 인증을 거쳐도 권한이 동일하게 부여되는 것은 아님
        - Django에서 로그인을 했더라도 다른 사람의 글까지 수정 / 삭제가 가능하지 않음
    - 세션, 토큰, 제 3자를 활용하는 등의 다양한 인증 방식이 존재

<br><br><br>

---

## **2. How to authentication determined**

1. 인증 여부 확인 방법
    - DRF 공식문서에서 제안하는 인증 절차 방법
    
        - [https://www.django-rest-framework.org/api-guide/authentication/](https://www.django-rest-framework.org/api-guide/authentication/)
            
            ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/a77201a7-b31a-44f2-95c7-b3b169201430/Untitled.png)
            
        - `BasicAuthentication, SessionAuthentication`는 뭘까?
    
    - settings.py에 작성하여야 할 설정
        - “기본적인 인증 절차를 어떠한 방식으로 둘 것이냐”를 설정하는 것
        - 예시의 2가지 방법 외에도 각 framework마다 다양한 인증 방식이 있음
    - 우리가 사용할 방법은 DRF가 기본으로 제공해주는 인증 방식 중 하나인 **TokenAuthentication**
    - 모든 상황에 대한 인증 방식을 정의하는 것이므로, 각 요청에 따라 다른 인증 방식을 거치고자 한다면 다른 방식이 필요
    
    - view 함수마다 (각 요청마다) 다른 인증 방식을 설정하고자 한다면 decorator 활용
        
        ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/b195360a-9ab2-4a5d-b99b-bac8d63d92bf/Untitled.png)
        
    - [참고] permission_classes
        - 권한 관련 설정
        - 권한 역시 특정 view 함수마다 다른 접근 권한을 요구할 수 있음
    
2. 다양한 인증 방식
    - `BasicAuthentication`
        - 가장 기본적인 수준의 인증 방식
        - 테스트에 적합
    - `SessionAuthentication`
        - Django에서 사용하였던 session 기반의 인증 시스템
        - DRF와 Django의 session 인증 방식은 보안적 측면을 구성하는 방법에 차이가 있음
    - `RemoteUserAuthentication`
        - Django의 Remote user 방식을 사용할 때 활용하는 인증 방식
    
    - `TokenAuthentication`
        - 매우 간단하게 구현 할 수 있음
        - 기본적인 보안 기능 제공
        - 다양한 외부 패키지가 있음
    - 🔥 `settings.py`에서  `DEFAULT_AUTHENTICATION_CLASSES`를 정의
        - `TokenAuthentication` 인증 방식을 사용할 것임을 명시
    
3. TokenAuthentication 사용 방법
    - INSTALLED_APPS에 `rest_framework.authtoken` 등록
        
        ```python
        INSTALLED_APPS = [
            ...
            'rest_framework.authtoken'
        ]
        ```
        
    - 각 User 마다 고유 Token 생성
        
        ```python
        from rest_framework.authtoken.models import Token
        
        token = Token.objects.create(user=...)
        print(token.key)
        ```
        
    
    - 생성한 Token을 각 User에게 발급
        - User는 발급 받은 Token을 요청과 함께 전송
        - Token을 통해 User 인증 및 권한 확인
    - Token 발급 방법
        
        ```python
        def some_view_func(request):
            token = Token.objects.create(user=...)
            return Response({ 'token': token.key })
        ```
        
    
    - User는 발급 받은 Token을 headers에 담아 요청과 함께 전송
        - 단, 반드시 **Token** 문자열 함께 삽입
            - 삽입해야 할 문자열은 각 인증 방식 마다 다름 (ex. Bearer, Auth, JWT 등)
        - 주의) Token 문자열과 발급받은 실제 token 사이를 **‘ ‘(공백)**으로 구분
    - Authorization HTTP headers 작성 방법
        - `Authorization: Token 9944b09199c62bcf9418ad846dd0e4bbdfc6ee4b`
    
4. 토큰 생성 및 관리 문제점
    - 기본 제공 방식에서 고려해야할 사항들
        - 1) Token 생성 시점
        - 2) 생성한 Token 관리 방법
        - 3) User와 관련된 각종 기능 관리 방법
            - 회원가입
            - 로그인
            - 회원 정보 수정
            - 비밀 번호 변경 등 …

### **3. dj-rest-auth**

1. Dj-Rest-Auth
    - 회원가입, 인증(소셜미디어 인증 포함), 비밀번호 재설정, 사용자 세부 정보 검색, 회원 정보 수정 등을 위한 REST API end point 제공
    - 주의) django-rest-auth는 더 이상 업데이트를 지원하지 않음 → **dj-rest-auth** 사용
        
        ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/7238d0ff-5dcc-4d98-a8d4-74014d8f00ad/Untitled.png)
        
    - [https://github.com/iMerica/dj-rest-auth](https://github.com/iMerica/dj-rest-auth)
    
2. dj-rest-auth 사용 방법
    - 1) 패키지 설치
    - 2) App 등록
    - 3) url 등록
        
        ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/b1395e76-8ca6-4ab2-bb56-539d4e3c2964/Untitled.png)
        
    
3. 시작하기 전에…
    - 시작하기 전, `auth.User`를 accounts.User로 변경 필요
    - `auth.User`로 설정된 DB 제거
        
        ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/7eee80cc-cc96-4609-a0ec-5670b501d817/Untitled.png)
        
    - `my_api/settings.py` 주석 해제
        
        ```python
        # my_api/settings.py
        
        INSTALLED_APPS = [
            # Django Apps
            'accounts',
            'articles',
            ...
        ]
        
        AUTH-USER_MODEL = 'accounts.User'
        ```
        
    
4. dj-rest-auth 사용하기
    - `dj-rest-auth` 설치
        - `python install dj-rest-auth`
    - `my_api/settings.py` 주석 해제
        
        ```python
        # my_api/settings.py
        
        INSTALLED_APPS = [
            ...
            # Auth
            'rest_framework.authtoken',
            'dj_rest_auth',
        ]
        ```
        
    - `migrate`
        - `$ pip [manage.py](http://manage.py) migrate`
    - `my_api/urls.py` 주석 해제
        
        ```python
        # my_api/urls.py
        
        urlpatterns = [
            ...
            path('accounts/', include('dj_rest_auth.urls')),
        ]
        ```
        
    
    - 결과 확인
        - /accounts/로 이동
        - 회원 가입 기능 없음
            
            ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/2977e56d-67ed-4fbe-940f-b33e3c74549f/Untitled.png)
            
    
    - Github 재확인
        - 상세 옵션은 공식 문서를 참고하도록 안내
            
            ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/4925747c-bfc9-4b61-8641-dcb8eb2a71e6/Untitled.png)
            
    - 공식 문서로 이동
        - Registration (optional) 확인
            
            ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/a2490632-4ce0-4f5d-8b3a-4f69b60e729a/Untitled.png)
            
    
5. Registration
    - Registration 기능을 사용하기 위해 추가 기능 등록 및 설치 필요
        - `dj-rest-auth`는 소셜 회원가입을 지원한다.
        - `dj-rest-auth`의 회원가입 기능을 사용하기 위해서는 **django-allauth** 필요
            
            ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/1b33810d-56fb-4e88-8751-44392e433e55/Untitled.png)
            
    - `django-allauth` 설치
        
        ```python
        # 반드시 ''도 함께 입력
        $ pip install 'dj-rest-auth[with_social]'
        ```
        
    
    - **my_api/settings/py** 주석 해제
        
        ```python
        # my_api/settings.py
        
        INSTALLED_APPS = [
            ...
            # registration
            'django.contrib.sites',
            'allauth',
            'allauth.account',
            'allauth.socialaccount',
            'dj_rest_auth.registration',
        ]
        
        SITE_ID = 1
        ```
        
        - App 등록 및 SITE_ID 설정
    - [참고] SITE_ID는 무엇인가요?
        - Django는 하나의 컨텐츠를 기반으로 여러 도메인에 컨텐츠를 게시 가능하도록 설계됨
        - 다수의 도메인이 하나의 데이터베이스에 등록
        - 현재 프로젝트가 첫번째 사이트임을 나타냄
    
    - **my_api/urls.py** 주석 해제
        
        ```python
        # my_api/urls.py
        
        urlpatterns = [
            path('accounts/signup/', includes('dj_rest_auth.registration.urls'))
        ]
        ```
        
    - migrate
        
        ```python
        # allauth 추가에 대한 migrate
        $ python manage.py migrate
        ```
        
    
    - **/accounts/signup/** 페이지 확인
        
        ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/7e053004-f5ef-4e1f-8e0a-59dc5692b048/Untitled.png)
        
    - Get method는 접근 불가
    - 회원가입 POST 요청 양식 제공
        - email 생략 가능
    
6. Sign up & Login
    - 회원가입 요청 후 결과 확인
        
        ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/e08bb552-0744-4893-85c4-3a29a8b358bb/Untitled.png)
        
        - 요청에 대한 응답으로 **Token 발급**
    - 로그인 시에도 동일한 토큰 발급
        
        ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/5fbf2fa9-92b5-4124-b571-9a58c2c7454e/Untitled.png)
        
        - 정상적인 로그인 가능
    - 발급 받은 토큰은 **테스트를 위해 기록**
    
7. Password change
    - /accounts/password/change/ 기능 확인
        - 로그인 되어 있거나, 인증이 필요한 기능
        - DRF 자체 제공 HTML form에서는 토큰을 입력할 수 있는 공간이 없음
        - Postman 에서 진행
    - [참고] Raw data에서 직접 headers 추가 가능
        
        ```python
        {
          "headers": {"Authoriation": "Token token"},
          "new_password1": "new password",
          "new_password2": "new password"
        }
        ```
        
    
    - Postman으로 양식에 맞춰 POST 요청
        - `body/form-data`에 값 입력
            
            ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/040b5297-8353-45cd-b889-200baf402e55/Untitled.png)
            
    
    - headers에 Token 입력
        - **Authorization: Token { your token }** 형식에 맞춰 입력
            
            ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/8c2d441b-b33d-4604-91da-c718dcaa5773/Untitled.png)
            
    
    - 실패 이유는?
        - 인증 방법이 입증되지 않음
    - `my_api/settings.py` 주석 해제
        
        ```python
        # my_api/settings.py
        
        REST_FRAMEWORK = {
            # Authentication
            'DEFAULT_AUTHENTICATION_CLASSES': [
                'rest_framework.authentication.TokenAuthentication',
            ],
        }d
        ```
        
    
    - 최종 결과 확인
        - 정상적으로 비밀번호 변경 완료
            
            ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/0b4ac6d1-d448-4f4f-ab0e-6a58cf66d6e3/Untitled.png)
            

### **4. Permission setting**

1. Permission setting
    - 권한 설정 방법 확인
        - DRF 공식 문서 > API Guide > Permissions 확인
        - [https://www.django-rest-framework.org/api-guide/permissions/](https://www.django-rest-framework.org/api-guide/permissions/)
        
        ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/1675b66d-6a0f-4730-8668-96a1a20d9191/Untitled.png)
        
    
    - 권한 세부 설정
        - 1) 모든 요청에 대해 인증을 요구하는 설정
        - 2) 모든 요청에 대해 인증이 없어도 허용하는 설정
    - 설정 위치 == 인증 방법을 설정하는 곳과 동일
        - 우선 모든 요청에 대해 허용 설정
            
            ```python
            'DEFAULT_PERMISSION_CLASSES': [
                'rest_framework.permissions.AllowAny',  # 인증됐다면 모두 허용
            ],
            ```
            
    
    - my_api/settings.py 주석 해제
        - 모두 허용만 주석 해제
            
            ```python
            # my_api/settings.py
            
            REST_FRAMEWORK = {
                # permission
                'DEFAULT_PERMISSION_CLASSES': [
                    'rest_framework.permissions.AllowAny',  # 인증됐다면 모두 허용
                ],
            }
            ```
            
    
2. Article List Read
    - **articles/views.py** 주석 해제
        
        ```python
        # articles/views.py
        
        from rest_framework.decorators import permission_classes
        from rest_framework.permissions import IsAuthenticated
        
        @api_view(['GET', 'POST'])
        @permission_classes([IsAuthenticated])
        def article_list(request):
            if request.method == 'GET':
                # articles = Article.objects.all()
                ...
                return Response(serializer.data)
            elif request.method == 'POST':
                ...
        ```
        
    - 게시글 조회 및 생성 요청 시 인증된 경우만 허용하도록 권한 부여
        - decorator를 활용
    
    - 
    
3. df
4. dfd
5. f
6. dfd
7. f
8. df
9. d
10. f
11. dfd

---
