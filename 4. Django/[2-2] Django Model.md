# **Django Model**

- 개요
    - Model(이하 모델)의 핵심 개념과 ORM을 통한 데이터베이스 조작 이해
    - Django는 웹 애플리케이션의 데이터를 구조화하고 조작하기 위한 추상적인 계층(모델)을 제공

### **1. Database**

1. Database
    - 체계화된 데이터의 모임
    - 검색 및 구조화 같은 작업을 보다 쉽게 하기 위해 조직화된 데이터를 수집하는 저장 시스템
    
2. Database 기본 구조
    - 스키마(Schema)
    - 테이블(Table)
    
3. 스키마(Schema)
    - 뼈대(Structure)
    - 데이터베이스에서 자료의 구조, 표현 방법, 관계 등을 정의한 구조
    - 예시
        
        
        | column | datatype |
        | --- | --- |
        | id | INT |
        | name | TEXT |
        | age | INT |
        | email | TEXT |
    
4. 테이블(Table)
    - 필드와 레코드를 사용해 조직된 데이터 요소들의 집합
    - 관계(Relation)라고도 부름
    - 필드(field)
        - 속성, 컬럼(Column)
    - 레코드(record)
        - 튜플, 행(Row)
    
    ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/fad86bf4-cecd-4e16-b8fd-cee3503721c6/Untitled.png)
    
5. 필드(field)
    - 속성 혹은 컬럼(column)
    - 각 필드에는 고유한 데이터 형식이 지정됨
        - INT, TEXT 등
    
6. 레코드(record)
    - 튜플 혹은 행(row)
    - 테이블의 데이터는 레코드에 저장됨
    - 예를 들어 위의 예시는 4명의 고객정보가 저장되어 있으며, 레코드는 4개가 존재
    
7. PK(Primary Key)
    - 기본 키
    - 각 레코드의 고유한 값 (식별자로 사용)
    - 기술적으로 **다른 항목과 절대로 중복되어 나타날 수 없는 단일한 값(unique)**을 가짐
    - 데이터베이스 관리 및 테이블 간 관계 설정 시 주요하게 활용 됨
    
8. PK(Primary Key) 예시
    - “주민등록번호”
        - 데이터베이스에 동일한 이름, 나이를 가진 사람들의 데이터는 존재할 수 있지만 각 사람들이 가진 주민등록번호는 절대 같을 수 없음
        - 즉, 주민등록번호는 그 사람을 나타내는 고유한 값으로써 사용할 수 있다는 것
    
9. 쿼리(Query)
    - 데이터를 조회하기 위한 명령어를 일컬음
    - 조건에 맞는 데이터를 추출하거나 조작하는 명령어 (주로 테이블형 자료구조에서)
    - “Query를 날린다.”
        - “데이터베이스를 조작한다.”

### **2. Model**

1. 개요
    - Django는 Model을 통해 데이터에 접속하고 관리
    - 단일한 데이터에 대한 정보를 가짐
    - 사용자가 저장하는 데이터들의 필수적인 필드들과 동작들을 포함
    - 저장된 데이터베이스의 구조 (layout)
    - 일반적으로 각각의 모델은 하나의 데이터베이스 테이블에 매핑(mapping)
        - 모델 클래스 1개 == 데이터베이스 테이블 1개
    - Model을 통해 데이터 관리
        - (사진)
    
2. 매핑
    - Mapping
    - 하나의 값을 다른 값으로 대응시키는 것
    
3. Model 작성하기
    - 새 프로젝트(crud), 앱(articles) 작성 및 앱 등록
        
        ```python
        $ django-admin startproject crud .
        
        $ python manage.py startapp articles
        ```
        
        ```python
        # settings.py
        
        INSTALLED_APPS = [
        		'articles',
        		...
        ]
        ```
        
    - [`model.py`](http://model.py) 작성
        - 모델 클래스를 작성하는 것은 데이터베이스 **테이블의 스키마**를 정의하는 것
        - “모델 클래스 == 테이블 스키마”
        - id 컬럼은 테이블 생성시 Django가 자동으로 생성
        
        ```python
        # articles/models.py
        
        class Article(models.Model):
        		title = models.CharField(max_length=10)
        		content = models.TextField()
        ```
        
4. Model 이해하기
    - 각 모델은 django.models.Model 클래스의 서브 클래스로 표현됨
        - 즉, 각 모델은 django.db.models 모듈의 Model 클래스를 상속받아 구성됨
        - **클래스 상속 기반 형태의 Django 프레임워크 개발**
            - 프레임워크에서는 잘 만들어진 도구를 가져다가 잘 쓰는 것
    - model 모듈을 통해 어떠한 타입의 DB 필드(컬럼)을 정의할 것인지 정의
        - 클래스 변수(속성)명 == DB 필드의 이름
        - 클래스 변수 값 (models 모듈의 Field 클래스) == DB 필드의 데이터 타입
    
5. Django Model Field
    - Django는 모델 필드를 통해 테이블의 필드(컬럼)에 저장할 데이터 유형(INT, TEXT 등)을 정의
    - 데이터 유형에 따라 다양한 모델 필드를 제공
        - `DataField()`, `CharField()`, `IntegerField()` 등
        - [http://docs.djangoproject.com/en/3.2/ref/models/fields/](http://docs.djangoproject.com/en/3.2/ref/models/fields/)
    
6. 사용한 모델 필드 알아보기
    - `CharField(max_length=None, **options)`
        - 길이의 제한이 있는 문자열을 넣을 때 사용
        - **max_length**
            - 필드의 최대 길이(문자)
            - CharField의 필수 인자
            - 데이터베이스와 Django의 유효성 검사(값을 검증하는 것)에서 활용됨
    - `TextField(**options)`
        - 글자의 수가 많을 때 사용
        - max_length 옵션 작성 시 사용자 입력 단계에서는 반영 되지만, 모델과 데이터베이스 단계에서는 적용되지 않음 (CharField를 사용해야 함)
            - 실제로 저장될 때 길이에 대한 유효성을 검증하지 않음
    
7. 데이터베이스 스키마
    - 지금까지 작성한 `models.py`는 다음과 같은 데이터베이스 스키마를 설계한 것
    - 이제 데이터베이스에 테이블을 생성하기 위한 **설계도** 작성이 필요함
    
    | Column | Data Type |
    | --- | --- |
    | title | VARCHAR(10) |
    | content | TEXT |

### **3. Migrations**

1. 개요
    - 모델에 대한 청사진(blueprint)을 만들고 이를 통해 테이블을 생성하는 일련의 과정
    - Django가 모델에 생긴 변화(필드 추가, 모델 삭제 등)를 DB에 반영하는 방법
    
2. Migraitons 관련 주요 명령어
    - `makemigrations`
    - `migrate`
    
3. makemigrations
    - 모델을 작성 혹은 변경할 것에 기반한 새로운 migration(설계도, 청사진 이하 마이그레이션)을 만들 때 사용
    - “테이블을 만들기 위한 설계도를 생성하는 것”
    
    ```python
    $ python manage.py makemigrations
    ```
    
    - 명령어 실행 후 migrations/0001_initial.py가 생성된 것을 확인
    - “파이썬으로 작성된 설계도”
    - (사진)
    
4. migrate
    - makemigrations로 만든 설계도를 실제 db.sqlite3 DB 파일에 반영하는 과정
    - 결과적으로 모델에서의 변경사항들과 DB의 스키마가 동기화를 이룸
        - “모델과 DB의 동기화”
        
        ```python
        $ python manage.py migrate
        ```
        
    - (사진)
    
5. Migrations 기타 명령어
    - `showmigrations`
        
        ```python
        $ python manage.py showmigrations
        ```
        
        - migrations 파일들이 migrate 됐는지 안됐는지 여부를 확인하는 용도
        - [X] 표시가 있으면 migrate가 완료되었음을 의미
    - sqlmigrate
        
        ```python
        $ python manage.py sqlmigrate articles 0001
        ```
        
        - 해당 migrations 파일이 SQL 문으로 어떻게 해석될 지 미리 확인 할 수 있음
    
6. 설계도는 어떻게, 누가 해석할까
    - `makemigrations`로 인해 만들어진 설계도는 파이썬으로 작성되어있음
    - 그런데 SQL만 알아들을 수 있다는 DB가 어떻게 이 설계도를 이해하고 동기화를 이룰 수 있을까?
    - 바로 이 과정에서 중간에 해석을 담당하는 것이 **ORM**

### **4. ORM**

1. 개요
    - Object-Relational-Mapping
    - 객체 지향 프로그래밍 언어를 사용하여 호환되지 않는 유형의 시스템 간에 (Django ↔ SQL)데이터를 변환하는 프로그래밍 기술
    - 객체 지향 프로그래밍에서 데이터베이스를 연동할 때, 데이터베이스와 객체 지향 프로그래밍 언어 간의 호환되지 않는 데이터를 변환하는 프로그래밍 기법
    - Django는 내장 Django ORM을 사용
    
2. ORM 예시
    
    (사진)
    
3. ORM 장단점
    - 장점
        - SQL을 잘 알지 못해도 객체지향 언어로 DB 조작이 가능
        - 객체 지향적 접근으로 인한 높은 생산성
    - 단점
        - ORM 만으로 완전한 서비스를 구현하기 어려운 경우가 있음
    
4. ORM을 사용하는 이유
    - **“생산성”**
    - 현시대 개발에서 가장 중요한 키워드는 바로 생산성
    - 우리는 DB를 객체(object)로 조작하기 위해 ORM을 사용할 것

### **5. 추가 필드 정의**

1. Model 변경사항 반영하기
    - models.py에 변경사항이 생겼을 때 어떤 과정의 migration이 필요할까?
    - 추가 모델 필드 작성 후 다시 한 번 makemigrations 진행
        
        ```python
        # articles/models.py
        
        class Article(models.Model):
        		title = models.CharField(max_length=10)
        		content = models.TextField()
        		created_at = models.DateTimeField(auto_now_add=True)
        		updated_at = models.DateTimeField(auto_now=True)
        ```
        
        ```python
        $ python manage.py makemigrations
        ```
        
    
    - 기존에 id, title, content 컬럼을 가진 테이블에 2개의 컬럼이 추가되는 상황
    - Django 입장에서는 이미 존재하는 테이블에 새로운 컬럼이 추가되는 요구 사항을 받았는데, 이 컬럼들은 기본적으로 빈 값으로 추가될 수 없음
        - 그래서 Django는 우리에게 추가되는 컬럼에 대한 기본 값을 설정해야 하니 어떻게 값을 설정할 것인지를 물어보는 과정을 진행
        
        (사진)
        
    - 각 보기 번호의 의미
        - 1 → 다음 화면으로 넘어가서 새 컬럼의 기본 겂을 직접 입력하는 방법
        - 2 → 현재 과정에서 나가고 모델 필드에 default 속성을 직접 작성하는 방법
    - “1”을 입력 후 Enter (created_at 필드에 대한 default 값 설정)
        - (사진)
    - 다음 화면에서 아무것도 입력하지 않고 Enter를 입력하면 Django에서 기본적으로 파이썬의 timezone 모듈의 now 메서드 반환 값을 기본 값으로 사용하도록 해줌
        - (사진)
    - 새로운 설계도(마이그레이션 파일)가 만들어진 것을 확인
        - (사진)
    - 새로운 설계도를 생성했기 때문에 DB와 동기화를 진행해야 함 (아직 DB에는 변경사항이 반영하지 않았기 때문)
        
        ```python
        python manage.py migrate
        ```
        
    
2. 반드시 기억해야 할 migration 3단계
    - 1) `models.py`에 변경사항이 발생하면
    - 2) migrations 파일 생성 (설계도 생성)
        - `makemigrations`
    - 3) DB 반영 (모델과 DB의 동기화)
        - `migrate`
    
3. `DateTimeField()`
    - Python의 datetime.datetime 인스턴스로 표시되는 날짜 및 시간을 값으로 사용하는 필드
    - DateField를 상속받는 클래스
    - 선택인자
        - `auto_now_add`
            - 최초 생성 일자 (Useful for creation of timestamps)
            - Django ORM이 최초 insert(테이블에 데이터 입력)시에만 현재 날짜와 시간으로 갱신(테이블에 어떤 값을 최초로 넣을 때)
        - `auto_now`
            - 최종 수정 일자 (Useful for “last-modified” timestamps)
            - Django ORM이 save를 할 때마다 현재 날짜와 시간으로 갱신
    
4. Model 정리
    - “웹 애플리케이션의 데이터를 **구조화**하고 **조작**하기 위한 도구”

---
