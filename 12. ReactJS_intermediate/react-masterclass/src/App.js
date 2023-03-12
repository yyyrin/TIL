import styled from "styled-components";

const Father = styled.div`
  display: flex;
`;

// 2) prop 받기
const Box = styled.div`
  background-color: ${(props) => props.bgColor};
  width: 100px;
  height: 100px;
`;

// const Circle = styled.div`
//   background-color: ${(props) => props.bgColor};
//   width: 100px;
//   height: 100px;
//   border-radius: 50px;
// `;
// -> 코드 중복되는 문제 발생

// Box의 모든 속성을 들고와서 추가로 코드를 더할 것이다.
const Circle = styled(Box)`
  border-radius: 50px;
`;

function App() {
  return (
    <Father>
      {/* 1) prop 보내기 */}
      <Box bgColor="teal" />
      <Circle bgColor="whitesmoke" />
      {/* Circle은 bgColor를 통해 설정 변경도 가능 */}
    </Father>
  );
}

export default App;
