# Django Form

1. 개요
    - 우리는 지금까지 HTML form, input 태그를 통해서 사용자로부터 데이터를 받았음<br>

    - 현재 우리 Django 서버는 들어오는 요청을 모두 수용하고 있는데, 이러한 요청 중에는 비정상적인 혹은 악의적인 요청이 있다는 것을 생각해야 함
    - 이처럼 사용자가 입력한 데이터가 우리가 원하는 데이터 형식이 맞는지에 대한 **유효성 검증**이 반드시 필요
        - 이러한 유효성 검증은 많은 부가적인 것들을 고려해서 구현해야 하는데, 이는 개발 생산성을 늦출뿐더러 쉽지 않은 작업임
    - **Django Form**은 이 과정에서 과중한 작업과 반복 코드를 줄여줌으로써 훨씬 쉽게 유효성 검증을 진행할 수 있도록 만들어 줌
<br><br><br>

2. Form에 대한 Django의 역할
    - Form은 Django의 유효성 검사 도구 중 하나로 외부의 악의적 공격 및 데이터 손상에 대한 중요한 방어 수단<br>

    - Django는 Form과 관련한 유효성 검사를 **단순화하고 자동화**할 수 있는 기능을 제공하여, 개발자가 직접 작성하는 코드보다 더 안전하고 빠르게 수행하는 코드를 작성할 수 있다.
        - 개발자가 필요한 핵심 부분만 집중할 수 있도록 돕는 프레임워크 특성
<br><br><br>

3. Django는 Form에 관련된 작업의 세 부분을 처리
    - 렌더링을 위한 데이터 준비 및 재구성<br>

    - 데이터에 대한 HTML forms 생성
    - 클라이언트로부터 받은 데이터 수신 및 처리
<br><br><br>
---

### **1. The Django Form Class**

1. 개요
    - Form Class<br>

        - Django form 관리 시스템의 핵심
<br><br><br>

2. Form Class 선언
    - Form Class를 선언하는 것은 Model Class를 선언하는 것과 비슷하다.<br>

    - 비슷한 이름의 필드 타입을 많이 가지고 있다. (다만 이름만 같을 뿐 같은 필드는 아님)
    - Model과 마찬가지로 상속을 통해 선언 (forms 라이브러리의 Form 클래스를 상속받음)
    - 앱 폴더에 forms.py를 생성 후 ArticleForm Class 선언
        
        ```python
        # articles/forms.py
        
        from django import forms
        
        class ArticleForm(forms.Form):
        		title = forms.CharField(max_length=10)
        		content = forms.CharField()
        ```
        
    - form에는 model field와 달리 TextField가 존재하지 않음
    - 모델의 TextField처럼 사용하려면 어떻게 작성할 수 있을지는 곧 알아볼 예정
        - “Form Class를 forms.py에 작성하는 것은 규약이 아니다.”<br>

        - 파일 이름이 달라도 되고 models.py나 다른 어디에도 작성 가능
        - 다만 더 나은 유지보수의 관점 그리고 관행적으로 `forms.py` 파일 안에 작성하는 것을 권장
<br><br><br>

3. ‘new’ view 함수 업데이트
    
    ```python
    # articles/views.py
    
    from .forms import ArticleForm
    
    def new(request):
    		form = ArticleForm()
    		context = {
    				'form': form,
    		}
    		return render(request, 'articles/new.html', context)
    ```
<br><br>

4. ‘new’ 템플릿 업데이트
    
    ```html
    <!-- articles/new.html -->
    
    {% extends 'base.html' %}
    
    {% block content %}
    	<h1>NEW</h1>
    	<form action="{% url 'articles:create' %}" method="POST">
    		{% csrf token %}
    		{{ form.as_p }}
    		<input type="submit">
    	</form>
    	<hr>
    	<a href="{% url 'articles:index' %}">[back]</a>
    {% endblock content %}
    ```
<br><br>

5. 업데이트 후 출력 확인
    - view 함수에서 정의한 ArticleForm의 인스턴스(form) 하나로 input과 label 태그가 렌더링 되는 것을 확인하기<br>

    - 각 태그의 속성 값들 또한 자동으로 설정되어 있음
<br><br><br>

6. From rendering options
    - \<label> & \<input> 쌍에 대한 3가지 출력 옵션<br>
    - `as_p()`
        - 각 필드가 단락(\<p> 태그)으로 감싸져서 렌더링
    - `as_ul()`
        - 각 필드가 목록 항목(\<li> 태그)으로 감싸져서 렌더링<br>

        - \<ul> 태그는 직접 작성해야 한다.
    - `as_table()`
        - 각 필드가 테이블(\<tr> 태그) 행으로 감싸져서 렌더링<br>

    - 우리는 특별한 상황이 아니면 `as_p()`만 사용할 것
<br><br><br>

7. Django의 2가지 HTML input 요소 표현
    - Form fields
        
        ```python
        # 예시
        
        forms.CharField()
        ```
        
        - 입력에 대한 유효성 검사 로직을 처리<br>

        - 템플릿에서 직접 사용됨
    - Widgets
        
        ```python
        # 예시
        
        forms.CharField(width=forms.Textarea)
        ```
        
        - 웹 페이지의 HTML input 요소 렌더링을 담당<br>

            - input 요소의 단순한 출력 부분을 담당<br>

        - Widgets은 반드시 form fields 할당됨
<br><br><br>
---

### **2. Widgets**

1. 개요
    - Django의 HTML input element의 표현을 담당<br>

    - 단순히 HTML 렌더링을 처리하는 것이며 유효성 검증과 아무런 관계가 없음
        - “웹 페이지에서 input element의 단순 raw한 렌더링만을 처리하는 것일 뿐”
<br><br><br>

2. Textarea 위젯 적용하기
    
    ```python
    # articles/forms.py
    
    class ArticleForm(forms.Form):
    		title = forms.CharField(max_length=10)
    		content = forms.CharField(width=forms.Textarea)
    ```
    
    - 출력 결과 확인하기<br>
    
    - 다양한 built-in 위젯
        
        [https://docs.djangoproject.com/ko/3.2/ref/forms/widgets/#built-in-widgets](https://docs.djangoproject.com/ko/3.2/ref/forms/widgets/#built-in-widgets)
<br><br><br>

    
3. Form fields와 widget 응용하기
    
    ```python
    # articles/forms.py
    
    class ArticleForm(forms.Form):
    		NATION_A = 'kr'
    		NATION_B = 'ch'
    		NATION_C = 'jp'
    		NATIONS_CHOICES = [
    				(NATION_A, '한국'),
    				(NATION_B, '중국'),
    				(NATION_C, '일본'),
    		]
    		title = forms.CharField(max_length=10)
    		content = forms.CharField(width=forms.Textarea())
    		nation = forms.Choicefield(choices=NATIONS_CHOICES)
    		# 다른 위젯도 찾아서 적용해보기
    		# nation = forms.ChoiceField(choices=NATIONS_CHOICES, widget=forms.RadioSelect())
    ```
    
    - 출력 결과를 확인하고 앞으로 어떻게 조합해서 사용할 수 있는지는 form field와 widgets 공석문서를 찾아보며 사용하도록 함
<br><br><br>
---
