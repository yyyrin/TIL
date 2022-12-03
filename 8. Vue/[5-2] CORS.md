# **CORS**

### **1. Cross-Origin Resource Sharing**

1. What Happened?
    - 브라우저가 요청을 보내고 서버의 응답이 브라우저에 도착
        - Server의 log는 **200(정상)** 반환
        - 즉 Server는 정상적으로 응답했지만 브라우저가 막은 것
    - 보안상의 이유로 브라우저는 **동일 출처 정책(SOP)**에 의해 다른 출처 리소스와 상호작용 하는 것을 제한함
    
2. SOP (Same - Origin Policy)
    - “동일 출처 정책”
    - 불러온 문서나 스크립트가 다른 출처에서 가져온 리소스와 상호작용 하는 것을 제한하는 보안 방식
    - 잠재적으로 해로울 수 있는 문서를 분리함으로써 공격받을 수 있는 경로를 줄임
    - [https://developer.mozilla.org/en-US/docs/Web/Security/Same-origin_policy](https://developer.mozilla.org/en-US/docs/Web/Security/Same-origin_policy)
    
3. Origin = “출처”
    - URL의 Protocol, Host, Port를 모두 포함하여 출처라고 부름
    - Same Origin 예시
        - 아래 세 영역이 일치하는 경우에만 동일 출처로 인정
            
            ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/7314f7f4-c267-44cd-854d-2e91a5ce3dd4/Untitled.png)
            
    
    - Same Origin 예시
        - http://localhost:3000/posts/3/ 을 기준으로 출처를 비교
            
            
            | URL | 결과 | 이유 |
            | --- | --- | --- |
            | http://localhost:3000/posts/ | 성공 | path만 다름 |
            | http://localhost:3000/comments/3/ | 성공 | path만 다름 |
            | https://localhost:3000/posts/3/ | 실패 | protocol이 다름 |
            | http://localhost:80/posts/3/ | 실패 | port가 다름 |
            | https://domain:3000/posts/3/ | 실패 | Host가 다름 |
    
4. CORS - 교차 출처 리소스 공유
    - 추가 **HTTP Header**를 사용하여, 특정 출처에서 실행 중인 웹 어플리케이션이 **다른 출처의 자원에 접근할 수 있는 권한**을 부여하도록 브라우저에 알려주는 체제
        - 어떤 출처에서 자신의 컨텐츠를 불러갈 수 있는지 **서버에 지정**할 수 있는 방법
    - 리소스가 자신의 출처와 다를 때 교차 출처 HTTP 요청을 실행
        - 만약 다른 출처의 리소스를 가져오기 위해서는 이를 제공하는 서버가 브라우저에게 **다른 출처지만 접근해도 된다는 사실을 알려야 함**
        - “교차 출처 리소스 공유 정책 (CORS policy)”
    
5. CORS policy - 교차 출처 리소스 공유 정책
    - 다른 출처에서 온 리소스를 공유하는 것에 대한 정책
    - CORS policy에 위배되는 경우 브라우저에서 해당 응답 결과를 사용하지 않음
        - Server에서 응답을 주더라도 브라우저에서 거절
    - 다른 출처의 리소스를 불러오려면 그 출처에서 **올바른 CORS header**를 포함한 응답을 반환해야 함
    - [https://developer.mozilla.org/ko/docs/Web/HTTP/CORS](https://developer.mozilla.org/ko/docs/Web/HTTP/CORS)

### **2. How to set CORS**

1. How to set CORS
    - CORS 표준에 의해 추가된 HTTP Response Header를 통해 이를 통제 가능
    - HTTP Response Header 예시
        - **Access-Control-Allow-Origin** / Access-Control-Allow-Credentials / Access-Control-Allow-Headers / Access-Control-Allow-Methods
    - Access-Control-Allow-Origin
        - 단일 출처를 지정하여 브라우저가 해당 출처가 리소스에 접근하도록 허용
    
2. django-cors-headers library 사용하기
    - django-cors-headers github에서 내용 확인
        - [https://github.com/adamchainz/django-cors-headers](https://github.com/adamchainz/django-cors-headers)
    - **응답에 CORS header를 추가**해주는 라이브러리
    - 다른 출처에서 Django 애플리케이션에 대한 브라우저 내 요청을 허용함
    
    - 라이브러리 설치 및 `requirements/txt` 업데이트
        - `$ pip install django-cors-headers`
        - `$ pip freeze > requirements.txt`
    
    - **App** 추가 및 **MIDDLEWARE** 추가 주석 해제
        - 주의) CorsMiddleWare는 가능한 CommonMiddleWare 보다 먼저 정의 되어야 함
            
            ```python
            # my_api/settings.py
            
            INSTALLED_APPS = [
                ...
                # CORS policy
                'corsheaders',
                ...
            ]
            
            MIDDLEWARE = [
                ...
                'corsheaders.middleware.CorsMiddleware',
                'django.middleware.common.CommonMiddleware',
                ...
            ]
            ```
            
    
    - CORS_ALLOWED-ORIGINS에 교차 출처 자원 공유를 허용할 Domain 등록
        
        ```python
        # my_api/settings/py
        
        # 특정 Origin만 선택적으로 허용
        CORS_ALLOWED_ORIGINS = [
            'http://localhost:8080',
        ]
        ```
        
    - 만약 모든 Origin을 허용하고자 한다면
        
        ```python
        # my_api/settings.py
        
        # 모든 Origin 허용
        CORS_ALLOWED_ALL_ORIGINS = True
        ```
        
    
3. 결과 확인
    - console 창에 정상적으로 출력되는 데이터 확인
        
        ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/cdc32ea2-724b-41eb-8a37-060eb54a88af/Untitled.png)
        
    
    - 응답에 Access-Control-Allow-Origin 헤더가 있는 것을 확인
        
        ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/cc438b80-ba33-49e5-beaa-68e563e711c0/Untitled.png)
        

---
