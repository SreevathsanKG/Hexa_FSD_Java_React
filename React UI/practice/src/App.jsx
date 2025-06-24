import { BrowserRouter, Route, Routes } from "react-router-dom"
import POne from "./components/POne"

function App() {

  return (
    <div>
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<POne/>}/>
        </Routes>
      </BrowserRouter>
    </div>
  )
}

export default App
