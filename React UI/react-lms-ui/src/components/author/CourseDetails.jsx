import axios from "axios"
import { useEffect, useState } from "react"
import { Link, useParams } from "react-router-dom"

function CourseDetails() {

    const params = useParams()
    let [course, setCourse] = useState([])
    let [videos, setVideos] = useState([])
    let [modules, setModules] = useState([])
    let [author, setAuthor] = useState([])
    let [reviews, setReviews] = useState([])

    useEffect(() => {
        const getCourseById = async () => {
            try {
                let response = await axios.get(`http://localhost:8080/api/course/get-by/id/${params.cid}`, {
                    headers: { 'Authorization': 'Bearer ' + localStorage.getItem('token') }
                })
                setCourse(response.data)
                setAuthor(response.data.author)
            } catch (error) {
                console.log(error)
            }
        }
        getCourseById()
        const getVideosByCourseId = async () => {
            try {
                let respVideo = await axios.get(`http://localhost:8080/api/video/get-by/courseId/${params.cid}`,{
                    headers: { 'Authorization': 'Bearer ' + localStorage.getItem('token') }
                })
                setVideos(respVideo.data)

                const fetchModule = new Map();
                respVideo.data.forEach(v => {
                    const m = v.cmodule;
                    if(!fetchModule.has(m.id))
                        fetchModule.set(m.id, m)
                })
                setModules(Array.from(fetchModule.values()))
            } catch (error) {
                console.log(error)
            }
        }
        getVideosByCourseId()
        const getReviewByCourseID = async () => {
            try {
                let respReview = await axios.get(`http://localhost:8080/api/review/get-by/courseId/${params.cid}`,{
                    headers: { 'Authorization': 'Bearer ' + localStorage.getItem('token') }
                })
                setReviews(respReview.data)
            } catch (error) {
                console.log(error)
            }
        }
        getReviewByCourseID()
    }, [])

    return (
        <div className="container-fluid">
            <div className="row">
                <div className="col-lg-12">
                    <nav aria-label="breadcrumb">
                        <ol className="breadcrumb">
                            <li className="breadcrumb-item"><a href="#">Author Dashboard</a></li>
                            <li className="breadcrumb-item"><a href="#">Courses</a></li>
                            <li className="breadcrumb-item active" aria-current="page">Course Details</li>
                        </ol>
                    </nav>
                </div>
            </div>
            <div className="col-lg-12">
                <h3>{course.title}</h3>
                <div className="card mt-2">
                    
                    <div className="card-body">
                        <h4>Credits: {course.credits}</h4>
                        
                        Display some basic info about the course here.
                        <br />
                        Like a short description, final outcome,
                        <br /> Teaching technique <br /> Pre requites and Learner mindset
                    </div>
                </div>
            </div>
            <div className="col-lg-12 mt-2">
                <div>
                    <span style={{ padding: '10px' }}>
                        <button className="btn btn-primary" data-toggle="collapse" href="#module" aria-expanded="false" aria-controls="collapseExample">
                        Modules
                        </button>
                    </span>
                    &nbsp;&nbsp;
                    <span style={{ padding: '10px' }}>
                        <button className="btn btn-info" data-toggle="collapse" data-target="#video" aria-expanded="false" aria-controls="collapseExample">
                            Videos
                        </button></span>
                    <span style={{ padding: '10px' }}>
                        <button className="btn btn-secondary" data-toggle="collapse" data-target="#author" aria-expanded="false" aria-controls="collapseExample">
                        Author Profile
                        </button>
                    </span>
                    <span style={{ padding: '10px' }}>
                        <button className="btn btn-warning" data-toggle="collapse" data-target="#review" aria-expanded="false" aria-controls="collapseExample">
                        Reviews
                        </button>
                    </span>




                </div>
                <div className="collapse" id="module">
                    <div className="card card-body">
                        Display list of all modules
                        {
                            modules.map((m, index) => (
                                <div key={index}>
                                    <li>{m.moduleTitle}</li>
                                </div>
                            ))
                        }
                    </div>
                </div>

                <div className="collapse" id="video">
                    <div className="card card-body">
                        Display a list of All videos along with modules
                        {
                            modules.map((m, index) => (
                                <div key={index}>
                                    <h6>{m.moduleTitle}</h6>
                                    {
                                        videos.map((v, index) => (
                                            <div key={index}>
                                                {m.id==v.cmodule.id?<li>{v.videoTitle} &nbsp; {v.playTime}</li>:""}
                                            </div>
                                        ))
                                    }
                                </div>
                            ))
                        }
                    </div>
                </div>

                <div className="collapse" id="author">
                    <div className="card card-body" >
                        <h5>Author Details:</h5>
                        <li>Name: {author.name}</li>
                        <li>Contact: {author.contact}</li>
                        <li>Website: {author.website}</li>
                    </div>
                </div>

                <div className="collapse" id="review">
                    <div className="card card-body">
                        {
                            reviews.map((r, index) => (
                                <div key={index}>
                                    <h6 class="card-title m-0">{r.learnerCourse.learner.name}</h6>
                                    Rating: {r.rating}
                                    <p class="card-text ">
                                        Comment: {r.comment}
                                    </p>
                                    <br />
                                </div>
                            ))
                        }
                    </div>
                </div>
            </div>
        </div>
    )
}

export default CourseDetails