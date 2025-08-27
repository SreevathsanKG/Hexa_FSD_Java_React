import { useState } from "react"
import axios from "axios"

function AddPost() {

    let [title, setTitle] = useState("")
    let [postText, setPostText] = useState("")
    let [userId, setUserId] = useState(undefined)
    let [msg, setMsg] = useState("")

    const addPost = async () => {
        try {
            await axios.post("https://jsonplaceholder.typicode.com/posts",{
                'title': title,
                'body': postText,
                'userId': userId
            })
            setMsg("Post created Successfully")
        } catch (err) {
            setMsg("Operation Failed, Try again")
        }
    }

    return (
        <div className="container">
            <div className="row">
                <div className="col-md-12">
                    <div className="card">
                        <div className="card-header">Add Post</div>
                        <div className="card-body">
                            {
                                msg != "" ? <div className="mb-4">
                                    <div className="alert alert-primary">{msg}</div>
                                </div> : ""
                            }
                            <div className="mb-4">
                                <label>Enter Title</label>
                                <input type="text" className="form-control"/>
                            </div>
                            <div className="mb-4">
                                <label>Enter Text</label>
                                <textarea className="form-control"/>
                            </div>
                            <div className="mb-4">
                                <label>Enter User ID</label>
                                <input type="number" className="form-control"/>
                            </div>
                            <div className="mb-4">
                                <button className="btn btn-primary" onClick={() => addPost()}>Add Post</button>
                            </div>
                        </div>
                        <div className="card-footer">View Post</div>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default AddPost