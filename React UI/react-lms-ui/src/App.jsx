import { Route } from "react-router-dom"
import { BrowserRouter } from "react-router-dom"
import { Routes } from "react-router-dom"
import LearnerSignup from "./components/LearnerSignup"
import Login from "./components/Login"
import AuthorDashboard from "./components/author/AuthorDashboard.jsx"
import LearnerDashboard from "./components/LearnerDashboard.jsx"
import Stats from "./components/author/Stats.jsx"
import Courses from "./components/author/Courses";
import Enrollments from "./components/author/Enrollments";
import Profile from "./components/author/Profile";
import CourseDetails from "./components/author/CourseDetails.jsx"
import UploadProfilePic from "./components/author/UploadProfilePic.jsx"

function App() {


  return (
    <div>
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<Login/>}></Route>
          <Route path="/signup" element={<LearnerSignup/>}></Route>
          <Route path="/author" element={<AuthorDashboard/>}>
            <Route index element={<Stats/>}/>
            <Route path="courses" element={<Courses />} />
            <Route path="enrollments" element={<Enrollments />} />
            <Route path="profile" element={<Profile />} />
            <Route path="course-details/:cid" element={<CourseDetails/>}/>
            <Route path="upload-profile-pic" element={<UploadProfilePic />} />
          </Route>
          <Route path="/Learner" element={<LearnerDashboard/>}></Route>
        </Routes>
      </BrowserRouter>
    </div>
  )
}

export default App