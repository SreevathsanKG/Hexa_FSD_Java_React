import axios from "axios"
import { useEffect, useState } from "react"
import { Link, useParams } from "react-router-dom"

function EditUser() {

    const id = useParams().id
    let [name, setName] = useState("")
    let [email, setEmail] = useState("")
    let [gender, setGender] = useState("")
    let [status, setStatus] = useState("")
    let [msg, setMsg] = useState("")

    useEffect(() => {
        const fetchUser = async () => {
            try {
                const resp = await axios.get(`https://gorest.co.in/public/v2/users/${id}`,{
                    headers:{
                        "Authorization":"Bearer "+"1d3cdb53ad474784cde28e65223831b36415970c6aebe4172a06fbbb9680a702"}
                })
                setName(resp.data.name)
                setEmail(resp.data.email)
                setGender(resp.data.gender)
                setStatus(resp.data.status)
            } catch (error) {
                console.log(error)
            }
        }
        fetchUser()
    }, [])

    const updateUser = async () => {
        try {
            const res =await axios.put(`https://gorest.co.in/public/v2/users/${id}`,{
                name,
                email,
                gender,
                status
            }, {
                headers: {
                    "Authorization": "Bearer " + "1d3cdb53ad474784cde28e65223831b36415970c6aebe4172a06fbbb9680a702"
                }
            })
            setMsg("User updated successfully")
        } catch (error) {
            setMsg("Something went wrong while updating user")
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
                            <h1>Edit User</h1>
                        </div>
                        <div className="card-body">
                            <div className="mb-3">
                                <label>User ID: {id}</label>
                            </div>
                            <div className="mb-3">
                                <label>Enter Name: </label>
                                <input type="text" value={name} onChange={(e) => setName(e.target.value)} className="form-control" />
                            </div>
                            <div className="mb-3">
                                <label>Enter Email: </label>
                                <input type="text" value={email} onChange={(e) => setEmail(e.target.value)} className="form-control" />
                            </div>
                            <div className="mb-3">
                                <label>Enter Gender: </label>
                                <div >
                                    <input className="form-check-input" type="radio" name="gender" value="male" checked={gender == "male"}
                                        onChange={(e) => setGender(e.target.value)} />
                                    <label className="form-check-label" >Male</label>
                                </div>
                                <div>
                                    <input className="form-check-input" type="radio" name="gender" value="female" checked={gender=="female"}
                                        onChange={(e) => setGender(e.target.value)} />
                                    <label className="form-check-label" >Female</label>
                                </div>
                            </div>
                            <div className="mb-3">
                                <label>Enter Status: </label>
                                <input type="text" value={status} onChange={(e) => setStatus(e.target.value)} className="form-control" />
                            </div>
                        </div>
                        <div className="card-footer">
                            <button className="btn btn-primary" onClick={updateUser}>Update User</button>
                            <label className="ms-4">Back to </label>
                            <Link to="/" className="ms-2">User Management</Link>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default EditUser