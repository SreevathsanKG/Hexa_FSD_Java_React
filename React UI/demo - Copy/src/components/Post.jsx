import axios from "axios"
import { useEffect, useState } from "react"

function Post() {

    let [postArry, setPostArry] = useState([])
    let [msg, setMsg] = useState("")


    useEffect(() => {
        // call the API : axios
        const getPosts = async () => {
            try{
                const response =await axios.get("https://jsonplaceholder.typicode.com/posts")
                // console.log(response) // i get the response structure from here
                setPostArry(response.data)
            }catch(err){
                console.log(err)
            }
        }
        getPosts() // calling function to ensure API is called
    },[]) // [] avoids infite loop of fetching the API

    const onDelete = async (postId) => {
        try {
            // call api
            //await axios.delete("https://jsonplaceholder.typicode.com/posts/"+postId)
            await axios.delete(`https://jsonplaceholder.typicode.com/posts/${postId}`)
            // remove element from array
            let temp = [...postArry]
            temp = temp.filter(p => p.id != postId)
            // if condition is truw the element stay else deleted
            setPostArry(temp)
            setMsg("Post with id "+postId+" deleted!!")
        } catch (error) {
            setMsg("Could not delete resource")            
        }
    }
    return (
        <div className="container-fluid">
            <nav className="navbar navbar-expand-lg navbar-light bg-light mb-4">
                <div className="container-fluid">
                    <a className="navbar-brand" href="#">Navbar</a>
                    <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                        <span className="navbar-toggler-icon"></span>
                    </button>
                    <div className="collapse navbar-collapse" id="navbarNav">
                        <ul className="navbar-nav">
                            <li className="nav-item">
                                <a className="nav-link active" aria-current="page" href="#">Home</a>
                            </li>
                            <li className="nav-item">
                                <a className="nav-link" href="#">Features</a>
                            </li>
                            <li className="nav-item">
                                <a className="nav-link" href="#">Pricing</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>
            <div><h3 className="display-3">All Post</h3></div>
            {
                msg!= ""? <div className="row">
                    <div className="col-md-12">{msg}</div>
                </div> : ""
            }
            <div className="row">
                {
                        postArry.map((post) => (
                            <div className="col-md-6 md-2" key={post.id}>
                                <div className="card">
                                    <div className="className-header">User ID : {post.userId}</div>
                                    <div className="card-body">
                                        <h5 className="card-title">{post.title}</h5>
                                        <p className="card-text">{post.body}</p>
                                        <a href="#" className="btn btn-primary">View Comments</a> &nbsp;&nbsp;
                                        <button className="btn btn-danger" onClick={() =>{ onDelete(post.id)}}>Delete</button>
                                    </div>
                                </div>
                            </div>
                        ))
                }
            </div>
        </div>
    )
}

export default Post