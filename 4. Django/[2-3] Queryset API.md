# **Queryset API**

### **1. 사전 준비**

1. 외부 라이브러리 설치 및 설정<br>

    - 실습 편의를 위한 추가 라이브러리 설치 및 설정
        
        ```python
        $ pip install ipython
        $ pip install django-extensions
        ```
        
        ```python
        # settings.py
        
        INSTALLED_APPS = [
        		'articles',
        		'django_extensions',
        		...,
        ]
        ```
        
    - 패키지 목록 업데이트
        
        ```python
        $ pip freeze > requirements.txt
        ```
<br><br>       
    
2. IPython & django-extensions
    - IPython<br>

        - 파이썬 기본 쉘보다 더 강력한 파이썬 쉘<br>

        - django-extensions
    - django-extensions
        - Django 확장 프로그램 모음<br>

        - shell_plus, graph model 등 다양한 확장 기능 제공
<br><br><br>

3. Shell
    - 운영체제 상에서 다양한 기능과 서비스를 구현하는 인터페이스를 제공하는 프로그램<br>

    - 셸(껍데기)은 사용자와 운영 체제의 내부사이의 인터페이스를 감싸는 층이기 때문에 그러한 이름이 붙음
    - “사용자 ↔ 셸 ↔ 운영체제”
<br><br><br>

4. Python Shell
    - 파이썬 코드를 실행하는 인터프리터<br>

        - 인터프리터: 코드를 한 줄씩 읽어 내려가며 실행하는 프로그램<br>
    - 인터렉티브 혹은 대화형 shell 이라고 부름
    - Python 명령어를 실행하여 그 결과를 바로 제공
    
    ```python
    # git bash (windows)
    $ python -i
    ```
<br><br>

5. Django shell
    - ORM 관련 구문 연습을 위해 파이썬 쉘 환경을 사용<br>

    - 다만 일반 파이썬 쉘을 통해서는 장고 프로젝트 환경에 영향을 줄 수 없기 때문에 Django환경 안에서 진행할 수 있는 Django 쉘을 사용
    - 원래는 다음과 같은 명령어를 통해 Django shell으 사용하지만
        
        ```python
        $ python manage.py shell
        ```
        
    - Django-extension이 제공하는 더 강력한 shell_plus로 진행
        
        ```python
        $ python manage.py shell_plus
        ```
<br><br>

6. Django shell 실행
    
    ```python
    $ python manage.py shell_plus
    ```
    
    ```python
    # Shell Plus Model Import
    from articles.models import Article
    from django.contrib.admin.models import LogEntry
    from django.contrib.auth.models import Group, Permission, User
    from django.contrib.contenttypes.models import ContentType
    from django.contrib.sessions.models import Sessions
    # Shell Plus Django Imports
    from django.core.cache import cache
    from django.conf import settings
    from django.contrib.auth import get_user_model
    from django.db import transaction
    from django.db.models import Avg, Case, Count, F, Max, Min, Prefetch, Q, Sum, When
    from django.utils import timezone
    from django.urls import reverse
    from django.db.models import Exists, OuterRef, Subquery
    Python 3.9.13 (main, Aug 24 2022, 22:54:29)
    Type 'copyright', 'credits' or 'license' for more information
    IPython 8.4.0 -- An enhanced Interactive Python. Type '?' for help.
    
    In [1]:
    ```
<br><br>

7. 첫 ORM 명령어 사용하기
    
    ```python
    In [1]: Article.objects.all()
    Out [1]: <QuerySet []>
    ```
    
    - 이제 ORM 명령어의 구조와 QuerySet에 대해 알아볼 것
<br><br><br>
---

### 2. **QuerySet API**

1. Database API
    - Django가 기본적으로 ORM을 제공함에 따른 것으로 DB를 편하게 조작할 수 있도록 도움<br>

    - Model을 만들면 Django는 객체들을 만들고 읽고 수정하고 지울 수 있는 DB API를 자동으로 만듦
<br><br><br>

2. Database API 구문
    
    ![database api img](./images/database%20api.png)
<br><br><br>

3. “objects”  manager
    - Django 모델이 데이터베이스 쿼리 작업을 가능하게 하는 인터페이스<br>

    - Django는 기본적으로 모든 Django 모델 클래스에 대해 objects 라는 Manager 객체를 자동으로 추가함
    - 이 Manager(objects)를 통해 특정 데이터를 조작(메서드)할 수 있음
    - **“DB를 Python class로 조작할 수 있도록 여러 메서드를 제공하는 manager”**
<br><br><br>

4. Query
    - 데이터베이스에 특정한 데이터를 보여 달라는 요청<br>

        - “쿼리문을 작성한다.”
            
            → 원하는 데이터를 얻기 위해 데이터베이스에 요청을 보낼 코드를 작성한다.
            
    - 이 때, 파이썬으로 작성한 코드가 ORM에 의해 SQL로 변환되어 데이터베이스에 전달되며, 데이터베이스의 응답 데이터를 ORM이 **QuerySet**이라는 자료 형태로 변환하여 우리에게 전달
<br><br><br>

5. QuerySet
    - 데이터베이스에게서 전달 받은 객체 목록(데이터 모음)<br>

        - 순회가 가능한 데이터로써 1개 이상의 데이터를 불러와 사용할 수 있음
    - Django ORM을 통해 만들어진 자료형이며, 필터를 걸거나 정렬 등을 수행할 수 있음
    - “objects” manager를 사용하여 복수의 데이터를 가져오는 queryset method를 사용할 때 반환되는 객체
    - 단, 데이터베이스가 단일한 객체를 반환할 때는 QuerySet이 아닌 모델(class)의 인스턴스로 반환됨
<br><br><br>

6. QuerySet API
    - “QuerySet과 상호작용하기 위해 사용하는 도구 (메서드, 연산자 등)”
    
    ![queryset api img](./images/queryset%20api.png)
<br><br><br>
---    

### 3. **QuerySet API 익히기**

1. QuerySet API 익히기
    - QuerySet API를 활용해 데이터를 생성하고, 읽고, 수정하고 삭제해보기
<br><br><br>

2. CRUD
    - Create / Read / Update / Delete<br>

        - 생성 / 조회 / 수정 / 삭제
    - 대부분의 컴퓨터 소프트웨어가 가지는 기본적인 데이터 처리 기능 4가지를 묶어서 일컫는 말
<br><br><br>
---

### 4. **CREATE**

1. 데이터 객체를 만드는(생성하는) 3가지 방법
    - 첫 번째 방법<br>
        - `article = Article()`<br>

            - 클래스를 통한 인스턴스 생성
        - `article.title`
            - 클래스 변수명과 같은 이름의 인스턴스 변수를 생성 후 값 할당
        - `article.save()`
            - 인스턴스로 save 메서드 호출
<br><br><br>

2. 첫 번째 방법
    
    ```python
    # 특정 테이블에 새로운 행을 추가하여 데이터 추가
    >>> article = Article()  # Article(class)로부터 article(instance)
    >>> article
    <Article: Article object (None)>
    
    >>> article.title = 'fisrt'  # 인스턴스 변수(title)에 값을 할당
    >>> article.content = 'django!'  # 인스턴스 변수(content)에 값을 할당
    
    # save를 하지 않으면 아직 DB에 값이 저장되지 않음
    >>> article
    <Article: Article object (None)>
    
    >>> Article.objects.all()
    <QuerySet []>
    ```
    
    - save 메서드를 호출해야 비로소 DB에 데이터가 저장된다. (레코드 생성)<br>

        - DB 테이블의 컬럼 이름이 id 임에도 pk를 사용할 수 있는 이유는 Django가 제공하는 shortcut이기 때문
            
            ```python
            # save를 하고 확인하면 저장된 것을 확인할 수 있다.
            
            >>> article.save()
            >>> article
            <Article: Article object (1)>
            >>> article.id
            1
            >>> article.pk
            1
            >>> Article.objects.all()
            <QuerySet [Article: Article object (1)]>
            ```
            
    
    ```python
    # 인스턴스인 article을 활용하여 변수에 접근해보자(데이터 저장된 것을 확인)
    
    >>> article.title
    'first'
    >>> article.content
    'django!'
    >>> article.created_at
    datetime.datetime(2022, 8, 21, 2, 43, 56, 49345, tzinfo=<UTC>)
    ```
<br><br>

3. 두 번째 방법
    - 인스턴스 생성 시 초기 값을 함께 작성하여 생성<br>

        
        ```python
        >>> article = Article(title='second', content='django!')
        
        # 아직 저장 되어있지 않음
        >>> article
        <Article: Article object (None)>
        
        # save를 호출해야 저장됨
        >>> article.save()
        >>> article
        <Article: Article object (2)>
        >>> Article.objects.all()
        <QuerySet [<Article: Article object (1)>, <Article: Article object (2)>]>
        ```
        
        ```python
        # 값 확인
        >>> article.pk
        2
        >>> article.title
        'second'
        >>> article.content
        'django!'
        ```
<br><br>        
    
4. 세 번째 방법
    - QuerySet API 중 `create()` 메서드 활용
        
        ```python
        # 위 2가지 방식과는 다르게 바로 생성된 데이터가 반환된다.
        
        >>> Article.objects.create(title='third', content='django!')
        <Article: Article object (3)>
        ```
<br><br>

5. `.save()`
    - “Saving object”<br>

    - 객체를 데이터베이스에 저장함
    - 데이터 생성 시 save를 호출하기 전에는 객체의 id 값은 None
        - id 값은 Django가 아니라 데이터베이스에서 계산되기 때문
    - 단순히 모델 클래스를 통해 인스턴스를 생성하는 것은 DB에 영향을 미치지 않기 때문에 반드시 save를 호출해야 테이블에 레코드가 생성됨
<br><br><br>
---

### **5. READ**

1. 개요
    - QuerySet API method를 사용해 데이터를 다양하게 조회하기<br>

    - QuerySet API method는 크게 2가지로 분류됨
        - 1) Methods that **“return new querysets”**<br>

        - 2) Method that **“do not return querysets”**
<br><br><br>

2. `all()`
    - QuerySet return<br>

    - 전체 데이터 조회
    
    ```python
    >>> Article.objects.all()
    <QuerySet [<Article: Article object (1)>, <Article: Article object (2)>, <Article: Article object (3)>]>
    ```
<br><br><br>

3. `get()`
    - 단일 데이터 조회<br>

    - 객체를 찾을 수 없으면 DoesNotExist 예외를 발생시키고, 둘 이상의 객체를 찾으면 MultipleObjectsReturned 예외를 발생시킴
    - 위와 같은 특징을 가지고 있기 때문에 primary key와 같이 **고유성(uniqueness)을 보장하는 조회에서 사용해야 함**
    
    ```python
    >>> Article.objects.get(pk=1)
    <Article: Article object (1)>
    
    >>> Article.objects.get(pk=100)
    DoesNoExists: Article matching query does not exist.
    
    >>> Article.objects.get(content='django!')
    MultipleObjectsReturned: get() returned more than one Article -- it returned 2!
    ```
<br><br>

4. `filter()`
    - 지정된 조회 매개 변수와 일치하는 객체를 포함하는 새 QuerySet을 반환<br>

    - 조회된 객체가 없거나 1개여도 QuerySet을 반환
    
    ```python
    >>> Article.objects.filter(content='django!')
    <QuerySet [<Article: Article object (1)>, <Article: Article object (2)>, <Article: Article object (3)>]>
    
    >>> Article.objects.filter(title='student')
    <QuerySet []>
    
    >>> Article.objects.filter(title='first')
    <QuerySet [<Article: Article object (1)>]>
    ```
<br><br>

5. Field lookups
    - 특정 레코드에 대한 조건을 설정하는 방법<br>

    - QuerySet 메서드 `filter()`, `exclude()`, 및 `get()`에 대한 키워드 인자로 지정됨
    
    ```python
    # Field lookups 예시
    
    # "content 컬럼에 'dj'가 포함된 모든 데이터 조회"
    Article.objects.filter(content__contains='dj')
    ```
<br><br>    
---

### **6. UPDATE**

- Update 과정
    1. 수정하고자 하는 article 인스턴스 객체를 조회 후 반환 값을 저장<br>

    2. article 인스턴스  객체의 인스턴스 변수 값을 새로운 값으로 할당
    3. save() 인스턴스 메서드 호출

```python
>>> article = Article.objects.get(pk=1)

# 인스턴스 변수를 변경
>>> article.title = 'byebye'

# 저장
>>> article.save()

# 정상적으로 변경된 것을 확인
>>> article.title
'byebye'
```
<br><br>
---

### **7. DELETE**

- Delete 과정
    1. 삭제하고자 하는 article 인스턴스 객체를 조회 후 반환 값을 저장<br>

    2. delete() 인스턴스 메서드 호출

```python
>>> article = Article.objects.get(pk=1)

# delete 메서드 호출
>>> article.delete()
(1. {'articles.Article': 1})

# 1번 데이터는 이제 조회할 수 없음
>>> Article.objects.get(pk=1)
DoesNotExist: Article matching query does not exist.
```
<br><br>

- \[참고] `__str__()`
    - 표준 파이썬 클래스의 메서드인 `str()`을 정의하여 각각의 object가 사람이 읽을 수 있는 문자열을 반환(return)하도록 할 수 있음<br>

        
        ```python
        # models.py
        
        class Article(models.Model):
        		title = models.CharField(max_length=10)
        		content = models.TextField()
        		created_at = models.DateTimeField(auto_now_add=True)
        		updated_at = models.DateTimeField(auto_now=True)
        
        def __str__(self):
        		return self.title
        ```
        
    - Django shell에서 변화된 출력 확인
        - `__str__()` 작성 후 반드시 shell을 재시작해야 반영됨
        
        ```python
        >>> article = Article.objects.get(pk=1)
        >>> article
        <QuerySet [<Article: 'first'>]>
        ```
<br><br>        

---
