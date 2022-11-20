# **Vue advanced**

1. computed
    - Vue instance가 가진 options 중 하나
    - computed 객체에 정의한 함수를 페이지가 최초로 렌더링 될 때 호출하여 계산
        - 계산 결과가 변하기 전까지 함수를 재호출하는 것이 아닌 계산된 값을 반환
    - `10_computed.html`에서 methods와의 차이 확인
    
2. method VS computed
    - `method`
        - 호출 될 때마다 함수를 실행
        - 같은 결과여도 매번 새롭게 계산
    - `computed`
        - 함수의 종속 대상의 변화에 따라 계산 여부가 결정됨
        - 종속 대상이 변하지 않으면 항상 저장(캐싱)된 값을 반환
    
3. watch  → 감시자
    
    
    - `11_watch.html`에서 결과 확인
    - 특정 데이터의 변화를 감지하는 기능
        - 1) watch 객체를 정의
        - 2) 감시할 대상 data를 지정
        - 3) data가 변할 시 실행 할 함수를 정의
    - 첫 번째 인자를 변동 전 data
    - 두 번째 인자를 변동 후 data
    - 디버깅 할 때 사용
    
    ```jsx
    <button @click="number++"></button>
    
    <script>
      const app = new Vue({
        data: {
          number: 0.
        },
        watch: {
          number: function(val, oldVal) {
            console.log(val, oldVal)
          },
        },
      })
    </script>
    ```
    
    - 실행 함수를 Vue method로 대체 가능
        - 1) 감시 대상 data의 이름으로 객체 생성
        - 2) 실행하고자 하는 method를 handler에 문자열 형태로 할당
    - Array, Object의 내부 요소 변경을 감지를 위해서는 **deep** 속성 추가 필요
    
4. filters
    - 텍스트 형식화를 적용할 수 있는 필터
    - interpolation 혹은 v-bind를 이용할 때 사용 가능
    - 필터는 자바스크립트 표현식 마지막에 **‘ | ’** (파이프)와 함께 추가되어야 함
    - 이어서 사용(chaining) 가능
    - `12_filters.html`에서 결과 확인

---
