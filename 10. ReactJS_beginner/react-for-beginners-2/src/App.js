import { useState, useEffect } from "react";

function Hello() {
  // function byFn() {
  //   console.log("bye :(");
  // }
  // function hiFn() {
    // 2) 이 코드 실행
    // console.log("created :)");
    // 3) component가 언제 파괴될지 알고 싶다면
  //   return byFn;
  // }
  // 1) component가 생성될 때, hiFn이 실행되고
  // useEffect(hiFn, []);
  useEffect(() => {
    console.log("hi :)")
    return () => console.log("bye :(")
  }, [])
  return <h1>Hello</h1>;

  /*
  // component가 처음 생성될 때만 실행
  useEffect(() => {
    console.log("created :)");
    // component가 destroy될 떄 실행 될 function 만들기
    return () => console.log("destroyed :(")
  }, []);
  
  return <h1>Hello</h1>;
  */
}

function App() {
  const [showing, setShowing] = useState(false);
  // setShowing을 통해 이전 value를 받아온 다음, 그 value의 반댓값을 return
  const onClick = () => setShowing((prev) => !prev);

  return (
    <div>
      {/* JS 쓸 때 {}중괄호 쓰기!!! */}
      {showing ? <Hello /> : null}
      <button onClick={onClick}>{showing ? "Hide" : "Show"}</button>
    </div>
  );
}

export default App;
