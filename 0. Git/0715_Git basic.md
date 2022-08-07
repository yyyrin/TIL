# GIT

### 1. Git이란?

- **분산 버전 관리 프로그램**
- 버전 : 컴퓨터 소프트웨어의 특정 상태
- 관리 : 어떤 일의 사무, 시설이나 물건의 유지, 개량
- 프로그램 : 컴퓨터에서 실행될 때 특정 작업을 수행하는 일련의 명령어들의 집합
- 코드의 히스토리(버전)을 관리하는 도구
- 개발되어온 과정 파악 가능
- 이전 버전과 변경 사항 비교 및 분석
- ex) GitLab / GitHub / Bitbucket => git기반의 저장소 서비스
- git != github

### 2. GUI & CLI

- GUI : 그래픽을 통해 사용자와 컴퓨터가 상호 작용하는 방식

- CLI : 명령어를 통해 사용자와 컴퓨터가 상호 작용하는 방식

- GUI는 CLI에 비해 사용하기 쉽지만 단계가 많고 컴퓨터의 성능을 더 많이 소보

- 수 많은 서버 / 개발 시스템이 CLI를 통한 조작 환경을 제공

### 3.  CLI 기본적인 명령어

###### git bash 열어서 사용

- touch : 파일을 생성하는 명령어 => touch a.txt
- ls : 현재 작업 중인 디렉토리의 폴더/파일 목록을 보여주는 명령어
- mkdir : 새 폴더를 생성하는 명령어 => mkdir fd
- cd : 현재 작업 중인 디렉토리(하위폴더)를 변경하는 명령어 => cd fd
  (path 정보 주의깊게 보기!!!!)
- cd .. : 상위 폴더로 올라오기
- start : 폴더/파일을 여는 명령어 (windows에서..!) => start a.txt
- code 파일명 : vscode로 파일 엶 (vs코드 설치되어 있을 때 사용 가능) => code a.txt
- rm 파일명 : 파일 삭제 (폴더x)
- rm -rf 폴더명 : 폴더 삭제 (주의 필요!)

### 4. 절대경로 vs 상대경로

1) **절대경로**
- 전체적인 위치, 고정적
- 루트 디렉토리부터 목적 지점까지 거치는 모든 경로를 전부 작성한 것
- 윈도우 바탕 화면의 절대 경로 = C:/Users/ssafy/Desktop
2) **상대경로**
- 본인 기준으로 위치 정함
- 현재 작업하고 있는 디렉토리를 기준으로 계산된 상대적 위치를 작성한 것
- 현재 작업하고 있는 디렉토리가 C:/Users일 때 윈도우 바탕화면으로 상대경로는 ssafy/Desktop
  => 경로의 시작점 확인!! 'C'가 있으면 절대 경로, 없으면 상대경로!
- ./ : 현재 작업하고 있는 폴더
- ../ : 현재 작업하고 있는 폴더의 부모 폴더

### 5. 마크다운(Markdown)

- 텍스트 기반의 가벼운 마크업(markup) 언어
- 문서의 구조와 내용을 같이 쉽고 빠르게 적고자 탄생

### 6. 마크다운 사용방법

- 'tap' : 들여쓰기
- 'shift + tap' : 내어쓰기
- '#' : 제목, 1~6개 쓸 수 있고 중요도 나타냄
- '**글자글자**' : bold체
- '==글자글자==' : 형광색 배경
- '>' : 인용문 작성, 안에 반복해서 사용 가능
- '*' : 순서가 없는 리스트 (= -)
- '숫자.' : 순서가 있는 리스트
- '```언어 + enter' : 코드 블럭(여러 줄) -> ~밑에 있는 백틱임!
- '`코드`' : 코드 한 줄일 때 사용!
- '마우스 우측클릭 + 삽입 > 표' : 표 만들기
- '-------- + enter' : 수평선 그리기
- '[링크 이름](링크 주소)' : 링크 달기
- '사진 끌어다가 놓기' : 상대주소로 위치, 이미지 추가시 임의로 이미지 위치 변경하지 말기
- '![]()' : 이미지 삽입
- '*글자글자*' : italic
- '~~글자글자~~' : 취소선
- html 사용가능

- 마우스 우측클릭 : 작업목록 쉽게 볼 수 있음

https://support.typora.io/Markdown-Reference/
-> 마크다운 레퍼런스 링크

------------------

### 7. README.md

- "README.md" 파일을 통해 오픈 소스의 공식 문서 작성 (반드시 대문자로 작성!)
- 개인 프로젝트의 소개 문서 작성
- 매일 학습한 내용 정리
- 마크다운을 이용한 블로그 운영

https://natics.tistory.com/24
-> 노션 예쁘게 꾸밀 수 있는 위젯 사이트

https://www.markdownguide.org/cheat-sheet/
-> 마크다운 cheat sheet!!!!

-------------------------------

### 8. Git 기본기

1. **README.md**
- 프로젝트에 대한 설명 문서
- Github 프로젝트에서 가장 먼저 보는 문서
- 일반적으로 소프트웨어와 함께 배포
- 작성 형식은 따로 없으나, 일반적으로 마크다운을 이용해 작성
2. **Repository**
- 특정 디렉토리(폴더)를 버전 관리하는 저장소

- git init 명령어로 로컬 저장소를 생성, 폴더 내 모든 내용이 깃 관리 대상이다!

- git 디렉토리에 버전 관리에 필요한 모든 것이 들어있음

- local : 지금 사용중인 컴퓨터

- git init

- '경로 (master)' 표시된다!

- 폴더 내 숨긴 항목 표시 -> .git 파일 표시된다!

- rm -rf .git -> .git 파일 삭제 -> (master) 표시 사라짐

- Readme.md 파일 생성 -> touch readme.md
3. **커밋(commit)은 이 3가지 영역을 바탕으로 동작**
   
   * working directory
     - 내가 작업하고 있는 실제 디렉토리
     - 참고 자료, 버전 관리 x
   * staging area
     - 커밋(commit)으로 남기고 싶은, 특정 버전으로 관리하고 싶은 파일이 있는 곳
       - 중간 확인 공간
     - git으로 관리할 것인지 확인하는 공간
   * repository
     - 커밋(commit)들이 저장되는 곳

4. **싸이클**
   
   1) untracked : working directory에 있는 파일, 처음 생성된 파일
      -- [git add : working directory에 있던 파일을 staging area로 이동]  ->
   
   2) staged : staging area에 있는 파일
      -- [git commit : staging area에 있던 파일을 repository에 저장]  -->
   
   3) committed : 버전관리가 되고 있는 파일!
   
   4) modified : 수정된 파일 -> 2) -> 3)
      
      => 변경된 커밋이 하나하나 쌓이게 된다!
- git status : git의 상태 확인 명령어
- git add a.txt : a.txt 파일을 staging area로 이동
- git add . : 현재 폴더 내 모든 파일을 add 하겠다!!
- git commit : commit하여 저장
5. **git 사용방법**
   git config --global user.email "qsc130@gmail.com"
   git config --global user.name "yyyrin"
   git config --global --list
   => 오타 없는지 꼭꼭 확인하기!!! 오타있으면 잔디 안 심긴다ㅠ
- git commit
- inset 나가기 : esc
- 저장하고 나가기 : :wq
- git log : git의 commit history 볼 수 있는 명령어 (!= git status)
- git diff : 두 commit 간 차이 보기

--------------------------------

### 9. 커밋

- 이미 커밋에 등록된 파일을 수정하게 되면 untracked가 아닌 modified로 뜨게 된다.

- git add . : 전체 add!!

- git commit -m "commit_message" : 수정했다고 commit!, 커밋 메세지는 의미없는 것 사용x, 자세하게 작성, 메세지 같이 등록하는 옵션!

- git diff commit앞에6자리 commit앞에6자리 : 두 commit 간 차이 보기

- q : 빠져나오기

- git log --oneline : 한 줄씩 보기

- local repository : 개인 pc

- remote repository : 원격저장소(깃허브,깃랩)

- git remote add origin(레포별명) 레포주소 : remote repository 등록

- git remote -v : 등록 잘 됐는지 확인!

- git push origin(레포별명) 브랜치명(master) : 깃허브 등록!! 

- git clone 주소 : 깃 파일 내려받기

- 경로 뒤에 (master) 있는지 꼭 확인하기!!!

- 무조건 '기준 버전'은 깃허브! 강의장ㄴㄴ, 집ㄴㄴ

- 지키지 않을 시, 충돌 발생

- push : 업로드!

- pull : 내려받기

- git pull origin master : 깃허브 버전 내려받기!

### 10. 과정

##### (사이클 잘 지키기!! 매우 중요중요!!!)

1) pull 먼저 받기 -> 깃허브 버전과 강의장/집 버전을 일치시키기(최초 1회는 clone 이용!)
2) add -> staging area에 공부한 내용 올리기
3) commit
4) push : local commit 정보를 업로드

### 11. 기타

- git remote add origin {remote_repo}

- git push origin master
  -> 최신 내용으로 update

- git remote -v : remote에 대한 정보 확인 

### README.md 수정하고 push 하기

- local repo에 github repo 를 연결
- README.md를 수정하고 commit을 생성
- github repo에 push
1. clone
- git clone {remote repo}
- remote repo를 local로 복사
- local로 복제
- git(git 설정이 있는 폴더, 즉 remote 주소도 같이 복제) 같이 복제
- git init x
- git remote add ~ x
2. pull
- remote repository 동일한 버전으로 다운받는 것
- remote 정보 필요
- git으로 관리되고 있어야 함
3. push
- git push origin master
- local repo의 최신 커밋을 remote repo로 push

----------------------

- clone = git init + git remote + git pull
- clone은 최초 한 번만 하면 된다!!!
- 그 이후에는 push와 pull만 이용