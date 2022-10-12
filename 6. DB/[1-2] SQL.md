# **SQL**

1. SQL 이란
    - “Structured Query Language”<br>

    - RDBMS의 데이터를 관리하기 위해 설계된 **특수 목적의 프로그래밍 언어**
    - RDBMS에서 데이터베이스 스키마를 생성 및 수정할 수 있으며, 테이블에서의 자료 검색 및 관리도 할 수 있음
    - 데이터베이스 객체에 대한 처리를 관리하거나 접근 권한을 설정하여 허가된 사용자만 RDBMS를 관리할 수 있도록 할 수 있음
    - 많은 데이터베이스 관련 프로그램들이 SQL을 표준으로 채택하고 있음
<br><br><br>

2. SQL 정리
    - SQL은 데이터베이스와 상호작용하는 방법<br>

    - 따라서 SQL을 배우면서 데이터베이스의 동작원리 또한 익힐 수 있음
<br><br><br>

---

## **1. SQL Commands**

1. SQL Commands 종류
    - 명령어는 특성에 따라 다음 세 가지 그룹으로 분류<br>

        - 1) DDL (Data Definition Language)<br>

        - 2) DML (Data Manipulation Language)
        - 3) DCL (Data Control Language)
    
    <br>

    | 분류 | 개념 | SQL 키워드 |
    | --- | --- | --- |
    | DDL - 데이터 정의 언어 (Data Definition Language) | 관계형 데이터베이스 구조(테이블, 스키마)를 정의(생성, 수정 및 삭제)하기 위한 명령어 | CREATE / DROP / ALTER |
    | DML - 데이터 조작 언어 (Data Manipulation Language) | 데이터를 조작(추가, 조회, 변경, 삭제)하기 위한 명령어 | INSERT / SELECT / UPDATE / DELETE |
    | DCL - 데이터 제어 언어 (Data Control Language) | 데이터의 보안, 수행제어, 사용자 권한 부여 등을 정의하기 위한 명령어 | GRANT / REVOKE / COMMIT / ROLLBACK |
    
    <br>

    → SQLite는 파일로 관리되는 DB이기 때문에 SQL을 이용한 접근 제한이 아닌 운영 체제의 파일 접근 권한으로만 제어 가능
    
    → 그래서 SQLite에는 권한 설정을 담당하는 GRANT(권한부여)와 REVOKE(권한회수)는 지원하지 않아 DCL 부분은 생략
<br><br><br>    

---

## **2. SQL Syntax**

1. SQL Syntax
    
    ```sql
    -- SQL Syntax 예시
    
    SELECT column_name FROM table_name;
    ```
    
    - 모든 SQL 문(statement)는 SELECT, INSERT, UPDATE 등과 같은 키워드로 시작하고, 하나의 statement는 **세미콜론**(;)으로 끝남<br>

        - 세미콜론은 각 SQL 문을 구분하는 표준 방법
    - SQL 키워드는 대소문자를 구분하지 않음
        - 즉, SELECT와 select는 SQL 문에서 동일한 의미<br>

        - 하지만 대문자로 작성하는 것을 권장
    - SQL에 대한 세부적인 문법 사항들은 이어지는 DDL, DML을 진행하며 익혀볼 것
<br><br><br>

2. [참고] Statement & Clause
    - Statement (문)<br>

        - 독립적으로 실행할 수 있는 완전한 코드 조각<br>

        - statement는 clause로 구성됨
    - Clause (절)
        - statement의 하위 단위
    
    ```sql
    SELECT column_name FROM table_name;
    ```
    
    - SELECT statement라 부름<br>

    - 이 statement는 다음과 같이 2개의 clause로 구성 됨
        - `SELECT column_name`<br>
        
        - `FROM table_name`
<br><br><br>

---