import axios from "axios"
import { useState } from "react"
import { Link } from "react-router-dom"

function AddUser() {

    let [name, setName] = useState("")
    let [email, setEmail] = useState("")
    let [gender, setGender] = useState("")
    let [status, setStatus] = useState("")
    let [msg, setMsg] = useState("")

    const addUsers = async () => {
        try {
            if(!name||!email||!gender||!status){
                setMsg("Please fill all the fields")
                return
            }
            const resp = await axios.post("https://gorest.co.in/public/v2/users", {
                "name": name,
                "email": email,
                "gender": gender,
                "status": status
            }, {
                headers: {
                    "Authorization": "Bearer " + "1d3cdb53ad474784cde28e65223831b36415970c6aebe4172a06fbbb9680a702"
                }
            })
            setMsg("User added successfully")
        } catch (error) {
            setMsg("Something went wrong while adding user")
        }
    }

    return (
        <div className="container">
            <div className="row">
                {
                    msg != "" ? <div>
                        <div className="alert alert-success" role="alert">
                            {msg}
                        </div>
                    </div> : ""
                }
                <div className="col-md-12">
                    <div className="card text-start">
                        <div className="card-header text-center">
                            <h1>Add User</h1>
                        </div>
                        <div className="card-body">
                            <div className="mb-3">
                                <label>Enter Name: </label>
                                <input type="text" onChange={(e) => setName(e.target.value)} className="form-control"/>
                            </div>
                            <div className="mb-3">
                                <label>Enter Email: </label>
                                <input type="text" onChange={(e) => setEmail(e.target.value)} className="form-control"/>
                            </div>
                            <div className="mb-3">
                                <label>Enter Gender: </label>
                                <div >
                                    <input className="form-check-input" type="radio" name="gender" value={"MALE"}
                                        onChange={(e) => setGender(e.target.value)} />
                                    <label className="form-check-label" >Male</label>
                                </div>
                                <div>
                                    <input className="form-check-input" type="radio" name="gender" value={"FEMALE"}
                                        onChange={(e) => setGender(e.target.value)} />
                                    <label className="form-check-label" >Female</label>
                                </div>
                            </div>
                            <div className="mb-3">
                                <label>Enter Status: </label>
                                <input type="text" onChange={(e) => setStatus(e.target.value)} className="form-control"/>
                            </div>
                        </div>
                        <div className="card-footer">
                            <button className="btn btn-primary" onClick={addUsers}>Add User</button>
                            <label className="ms-4">Back to </label>
                            <Link to="/" className="ms-2">User Management</Link>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default AddUser