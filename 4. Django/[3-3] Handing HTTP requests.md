# **Handing HTTP requests**

1. 개요
    - “HTTP requests 처리에 따른 view 함수 구조 변화”<br>

    - new-create, edit-update의 view 함수 역할을 잘 살펴보면 하나의 공통점과 하나의 차이점이 있음
    - **공통점**
        - new-create는 모두 CREATE 로직을 구현하기 위한 공통 목적<br>

        - edit-update는 모두 UPDATE 로직을 구현하기 위한 공통 목적
    - **차이점**
        - new와 edit는 GET 요청에 대한 처리만을,<br>

        - create와 update는 POST 요청에 대한 처리만을 진행
    - 이 공통점과 차이점을 기반으로, 하나의 view 함수에서 method에 따라 로직이 분리되도록 변경
<br><br><br>

2. CREATE
    - new와 create view 함수를 합침<br>

    - 각각의 역할은 `request.method` 값을 기준으로 나뉨
        
        ```python
        # articles/views.py
        
        def create(request):
        		if request.method = 'POST':
        				form = ArticleForm(reequest.POST)
        				if form.is_valid():
        						article = form.save()
        						return redirect('articles:detail', article.pk)
        		else:
        				form = ArticleForm()
        		context = {
        				'form': form,
        		}
        		return render(request, 'articles/new.html', context)
        ```
       <br> 
    - 이제 불필요해진 new의 view 함수와 url path를 삭제
        
        ```python
        # articles/views.py
        
        ~~def new(request):
        		form = ArticleForm()
        		context = {
        				'form': form,
        		}
        		return render(request, 'articles/new.html', context)~~
        ```
        
        ```python
        # articles/urls.py
        
        app_name = 'articles'
        urlpatterns = [
        		path('', views.index, name='index'),
        		~~path('new/', views.new, name='new'),~~
        		path('create/', views.create, name='create'),
        		path('<int:pk>/', views.detail, name='detail'),
        		path('<int:pk>/delete/', views.delete, name='delete'),
        		path('<int:pk>/edit/', views.edit, name='edit'),
        		path('<int:pk>/update/', views.update, name='update'),
        ]
        ```
      <br>  
    - new.html → create.html 이름변경 및 action 속성 값 수정
        
        ```html
        <!-- articles/create.html -->
        
        {% extends 'base.html' %}
        
        {% block content %}
        	<h1>**CREATE**</h1>
        	<form action='**{% url 'articles:create' %}**' method="POST">
        		{% csrf_token %}
        		{{ form.as_p }}
        		<input type="submit">
        	</form>
        	<hr>
        	<a href="{% url 'articles:index' %}">[back]</a>
        {% endblock content %}
        ```
       <br> 
    - new.html → create.html 이름변경으로 인한 템플릿 경로 수정
        
        ```python
        # articles/views.py
        
        def create(request):
        		if request.method = 'POST':
        				form = ArticleForm(reequest.POST)
        				if form.is_valid():
        						article = form.save()
        						return redirect('articles:detail', article.pk)
        		else:
        				form = ArticleForm()
        		context = {
        				'form': form,
        		}
        		return render(request, **'articles/create.html'**, context)
        ```
       <br> 
    - index 페이지에 있던 new 관련 링크 수정
        
        ```html
        <!-- articles/index.html -->
        
        {% extends 'base.html' %}
        
        {% block content %}
        	<h1>Articles</h1>
        	**<a href="{% url 'articles:create' %}">CREATE</a>**
        	<hr>
        	...
        {% endblock content %}
        ```
<br><br>

3. context의 들여쓰기 위치
    - 이렇게 작성하면 if form.is_valid(): 에서 false로 평가 받았을 때 이어질 코드가 없음
        
        ```python
        # articles/views.py
        
        def create(request):
        		if request.method = 'POST':
        				form = ArticleForm(reequest.POST)
        				if form.is_valid():
        						article = form.save()
        						return redirect('articles:detail', article.pk)
        		else:
        				form = ArticleForm()
        			**context = {
        					'form': form,
        			}
        			return render(request, 'articles/create.html', context)**
        ```
      <br>    
    - 반면 다음과 같이 작성하면 if form.is_valid(): 에서 false로 평가 받았을 때 에러 정보가 담긴 form 인스턴스가 context로 넘어갈 수 있음
        
        ```python
        # articles/views.py
        
        def create(request):
        		if request.method = 'POST':
        				form = ArticleForm(reequest.POST)
        				if form.is_valid():
        						article = form.save()
        						return redirect('articles:detail', article.pk)
        		else:
        				form = ArticleForm()
        		**context = {
        				'form': form,
        		}
        		return render(request, 'articles/create.html', context)**
        ```
<br><br>

    
4. UPDATE
    - edit와 update view 함수를 합침
        
        ```python
        # articles/views.py
        
        def update(request, pk):
        		article = Article.objects.get(pk=pk)
        		if request.method = 'POST':
        				form = ArticleForm(reequest.POST, instance=article)
        				if form.is_valid():
        						form.save()
        						return redirect('articles:detail', article.pk)
        		else:
        				form = ArticleForm(instance=article)
        		context = {
        				'form': form,
        				'article': article,
        		}
        		return render(request, 'articles/update.html', context)
        ```
      <br>    
    - new와 마찬가지로 불필요해진 edit의 view 함수와 url path를 삭제
        
        ```python
        # articles/views.py
        
        ~~def edit(request, pk):
        		article = Article.objects.get(pk=pk)
        		form = ArticleForm(instance=article)
        		context = {
        				'article': article,
        				'form': form,
        		}
        		return render(request, 'articles/edit.html', context)~~
        ```
        
        ```python
        # articles/urls.py
        
        app_name = 'articles'
        urlpatterns = [
        		path('', views.index, name='index'),
        		path('create/', views.create, name='create'),
        		path('<int:pk>/', views.detail, name='detail'),
        		path('<int:pk>/delete/', views.delete, name='delete'),
        		~~path('<int:pk>/edit/', views.edit, name='edit'),~~
        		path('<int:pk>/update/', views.update, name='update'),
        ]
        ```
       <br> 
    - edit.html → update.html 이름변경으로 인한 관련 정보 수정
        
        ```html
        <!-- articles/update.html -->
        
        {% extends 'base.html' %}
        
        {% block content %}
        	**<h1>UPDATE</h1>**
        	<form action='{% url 'articles:update' article,pk %}' method="POST">
        		{% csrf_token %}
        		{{ form.as_p }}
        		<input type="submit">
        	</form>
        	<hr>
        	<a href="{% url 'articles:detail' article.pk %}">[back]</a>
        {% endblock content %}
        ```
        
        ```html
        <!-- articles/detail.html -->
        
        <a href="{% url 'articles:update' article.pk %}">UPDATE</a>
        ```
      <br>  
    - POST 요청에 대해서만 삭제가 가능하도록 수정
        
        ```python
        # articles/views.py
        
        def delete(request, pk):
        		article = Article.objects.get(pk=pk)
        		**if request.method == 'POST':**
        				article.delete()
        				return redirect('articles:index')
        		**return redirect('articles:detail', article.pk)**
        ```
<br><br>        

---
