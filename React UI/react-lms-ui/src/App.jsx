import { Route } from "react-router-dom"
import { BrowserRouter } from "react-router-dom"
import { Routes } from "react-router-dom"
import AddPost from "./components/AddPost"
import Album from "./components/Album"
import Concepts from "./components/Concepts"
import CourseList from "./components/CourseList"
import Example1 from "./components/Example1"
import Example2 from "./components/Example2"
import LearnerSignup from "./components/LearnerSignup"
import Login from "./components/Login"
import Post from "./components/Post"
import Task from "./components/Task"
import TodoList from "./components/Todo"
import UserList from "./components/User"

function App() {


  return (
    <div>
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<Login/>}></Route>
          <Route path="/signup" element={<LearnerSignup/>}></Route>
          <Route path="/author" element={<Post/>}></Route>
          <Route path="/Learner" element={<CourseList/>}></Route>
        </Routes>
      </BrowserRouter>
    </div>
  )
}

export default App