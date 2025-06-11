import { useEffect, useState } from "react"
import axios from "axios"

function Example1() {

    let [postNo, setPostNo] = useState(undefined)
    let [postArray, setPostArray] = useState([])

    const getPosts = async (userId) => {
        try {
            const respo = await axios.get(`https://jsonplaceholder.typicode.com/users/${userId}/posts`)
            setPostArray(respo.data)
        } catch (error) {
            console.log("Error")
        }
    }
    return (
        <div className="container">
            <div className="row">
                <div className="col-md-12">
                    <div className="card">
                        <div className="card-header">
                            <label>Enter User ID: </label> &nbsp;&nbsp;
                            <input type="number" onChange={($e) => { setPostNo($e.target.value) }} /> &nbsp;&nbsp;
                            <button className="btn btn-primary" onClick={() => getPosts(postNo)}>Show Post</button>
                        </div>
                        <div className="card-body">
                                {
                                    postArray != [] ? postArray.map((post) => (
                                        <div className="card">
                                            <div>ID : {post.id}</div>
                                            <div className="card-title">{post.title}</div>
                                            <div className="card-text">{post.body}</div>
                                        </div>
                                    )) : ""
                                }
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default Example1