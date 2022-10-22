# **Django REST framework - Single Model**

1. 개요
    - 단일 모델의 data를 Serialization하여 JSON으로 변환하는 방법에 대한 학습
    
2. 사전 준비
    - Postman 설치
        - [https://www.postman.com/downloads/](https://www.postman.com/downloads/)
    - Postman
        - API를 구축하고 사용하기 위한 플랫폼
        - API를 빠르게 만들 수 있는 여러 도구 및 기능을 제공
    
    - Postman 화면 구성
        
        ![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/cefb689b-d3de-470d-b5ab-68ec308de4d3/Untitled.png)
        
    
    1) 준비된 `02_drf` 프로젝트로 진행
    
    2) 가상환경 생성, 활성화 및 패키지 목록 설치
    
    3) Article 모델 주석 해제 및 Migraiton 진행
    
    ```python
    # articles/models.py
    
    class Article(models.Model):
        title = models.CharField(max_length=10)
        content = models.TextField()
        created_at = models.DateTimeField(auto_now_add=True)
        updated_at = models.DateTimeField(auto_now=True)
    ```
    
    `$ python manage.py migrate`
    
    - 준비된 fixtures 데이터 load
        
        `$ python manage.py loaddata articles.json`
        
    
    - DRF 설치, 등록 및 패키지 목록 업데이트
        
        `$ pip install djangorestframework`
        
        ```python
        # settings.py
        
        INSTALLED_APPS = [
            ...
            'rest_framework',
            ...
        ]
        ```
        
        `$ pip freeze > requirements.txt`
        

### **1. ModelSerializer**

1. ModelSerializer 작성
    - articles/serializers.py 생성
        - serializers.py의 위치나 파일명은 자유롭게 작성 가능
    - ModelSerializer 작성
        
        ```python
        # articles/serializers.py
        
        from rest_framework import serializers
        from .models import Article
        
        class ArticleListSerializer(serializers.ModelSerializer):
        
            class Meta:
                model = Article
                fields = ('id', 'title', 'content',)
        ```
        
    
2. ModelSerializer
    - ModelSerializer 클래스는 모델 필드에 해당하는 필드가 있는 Serializer 클래스를 자동으로 만들 수 있는 shortcut을 제공
        - 1) Model 정보에 맞춰 자동으로 필드를 생성
        - 2) serializer에 대한 유효성 검사기를 자동으로 생성
        - 3) `.create` 및 `.update`의 간단한 기본 구현이 포함됨
    
     
    
3. Serializer 연습하기
    - shell_plus 실행 및 ArticleListSerializer import
        - `$ python [manage.py](http://manage.py) shell_plus`
        - `>>> from articles.serializers import ArticleListSerializer`
    
    - 인스턴스 구조 확인
        
        ```python
        >>> serializer = ArticleListSerializer()
        
        >>> serializer
        ArticleListSerializer():
            id = IntegerField(label='ID', read_only=True)
            title = CharField(max_length=10)
            content = CharField(style={'base_template': 'textarea.html'})
        ```
        
    
    - Model instance 객체 serialize
        
        ```python
        >>> article = Article.objects.get(pk=1)
        
        >>> serializer = ArticleListSerializer(article)
        
        >>> serializer
        ArticleListSerializer(<Article: Article object (1)>):
            id = IntegerField(label='ID', read_only=True)
            title = CharField(max_length=10)
            content = CharField(style={'base_template': 'textarea.html'})
        
        # serialized data 조회
        >>> serializer.data
        {'id': 1, 'title': 'Site teconomic if two country science.' ...}
        ```
        
    
    - QuerySet 객체 serialize
        
        ```python
        article = Article.objects.all()
        
        # many=True 옵션 X
        >>> serializer = ArticleListSerializer(articles)
        >>> serializer.data
        AttributeError: Got AttributeError when attempting to get a value for field 'title' on serializer 'ArticleListSerializer'.
        The serializer field might be named incorrectly and not match any attribute or key on the 'QuerySey' instance.
        Original exception text was: 'QuerySet' object has no attribute 'title'.
        
        # many=Ture 옵션 O
        >>> serializer = ArticleListSerializer(articles, many=True)
        >>> serializer.data
        [OrderdDict([('id', 1), ('title', 'Live left resesasrch.'), ('content', 'Small drive until back board drive')...])]
        ```
        
    
4. ModelSerializer의 **many** option
    
    
5. ㅇ
6. ㄹㅇㄹ
7. ㅇㄹ
8. ㅇ
9. ㄹㅇ
10. ㄹㅇ
11. ㄹ

### **2. Build RESTful API - Article**

---