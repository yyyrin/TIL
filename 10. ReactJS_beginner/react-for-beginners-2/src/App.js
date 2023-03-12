import { useState } from "react";

function App() {
  // input 값을 어떻게 control할 수 있을까? -> useState 이용
  const [toDo, setToDo] = useState("");

  return (
    <div>
      <input value={toDo} type="text" placeholder="Write your to do..." />
    </div>
  );
}

export default App;
