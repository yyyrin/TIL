import styled from "styled-components";

// 1) style
// ``(back tick) 내부에 CSS 코드 쓰기
const Father = styled.div`
  display: flex;
`;

const BoxOne = styled.div`
  background-color: teal;
  width: 100px;
  height: 100px;
`;

const BoxTwo = styled.div`
  background-color: tomato;
  width: 100px;
  height: 100px;
`;

//  컴포넌트의 이름 정할 수 있음
const Text = styled.h1`
  color: white;
`;

// 2) 구현 부분 - style 없음
function App() {
  return (
    <Father>
      <BoxOne>
        <Text>Hello</Text>
      </BoxOne>
      <BoxTwo />
    </Father>
  );
}

export default App;
