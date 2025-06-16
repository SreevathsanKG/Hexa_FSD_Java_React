import { useState } from "react";
function Concepts() {

  let [count, setCount] = useState(0);
  let [name, setName] = useState("");
  let [welcome, setWelcome] = useState(false);
  let [arrayIpl, setArrayIpl] = useState(["CSK","MI","SRH"]);

  return (
    <div>
      <h1 style={{ color: "blue" }}>Count = {count}</h1>
      <button onClick={() => setCount(count + 1)}>Incr</button>
      <button onClick={() =>setCount(count - 1)}>Desc</button>
      <hr /><br /><br />
      <label>Enter the Name :</label>
      <input type="text" onChange={($e) => { setName($e.target.value) }} />
      <br />
      <button onClick={()=>setWelcome(true)}>Submit</button>
      <br />
      { welcome == true ? <span>Welcome { name }</span> : ""}
      <hr /><br /><br />
      <h4>IPL Teams</h4>
      {
        arrayIpl.map((e) =>
            <li key={e}>{e}</li>
        )
      }
   </div>
  )
}

export default Concepts;