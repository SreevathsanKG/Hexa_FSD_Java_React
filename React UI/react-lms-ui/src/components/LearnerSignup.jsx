import { useState } from "react"
import axios from "axios"

function LearnerSignup() {

    let [msg, setMsg] = useState("")
    let [name, setName] = useState("")
    let [contact, setContact] = useState("")
    let [username, setUsername] = useState("")
    let [password, setPassword] = useState("")

    const postLearner = async () => {
        try{
            await axios.post("http://localhost:8080/api/learner/add",{
                'name':name,
                'contact':contact,
                'user':{
                    'username':username,
                    'password':password
                }
            })
            setMsg("Post created successfully!!")
        }catch(err){
            setMsg("Operation Failed, Try again!!")
        }
    }

    return (
        <div className="container-fluid">
            <div className="row">
                <div className="col-md-12"> <br /><br /><br /><br /><br /></div>
            </div>
            <div className="row">
                <div className="col-md-2"></div>
                <div className="col-md-8">
                    <div className="card">
                        {/* <div className="card-header">
                            <div className="text-center">REGISTRATION</div>
                        </div> */}
                        <div className="card-body px-4">
                            {
                                msg!=""?<div>
                                    <div className="alert alert-info">{msg}</div>
                                </div>:""
                            }
                            <div className="mb-2">
                                <label>Enter Name: </label>
                                <input type="text" className="form-control" onChange = {($e) => setName($e.target.value)}/>
                            </div>
                            <div className="mb-2">
                                <label>Enter Contact: </label>
                                <input type="text" className="form-control" onChange = {($e) => setContact($e.target.value)}/>
                            </div>
                            <div className="mb-2">
                                <label>Enter Username: </label>
                                <input type="text" className="form-control" onChange = {($e) => setUsername($e.target.value)}/>
                            </div>
                            <div className="mb-2">
                                <label>Enter Password: </label>
                                <input type="text" className="form-control" onChange = {($e) => setPassword($e.target.value)}/>
                            </div>
                            <div className="mb-2">
                                <button className="btn btn-primary" onClick = {() => postLearner()}>Submit</button>
                            </div>
                        </div>
                        {/* <div className="card-footer">
                            Already have an account? Login
                        </div> */}
                    </div>
                </div>
                <div className="col-ms-2"></div>
            </div>
        </div>
    )
}

export default LearnerSignup