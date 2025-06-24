import axios from "axios"
import { useEffect, useState } from "react"

function POne() {

    let [userId, setUserId] = useState(undefined)
    let [id, setId] = useState(undefined)
    let [title, setTitle] = useState("")
    let [users, setUsers] = useState([])
    let [msg, setMsg] = useState("")

    useEffect(() => {
        const fetchData =async () => {
        try {
            const resp = await axios.get("https://jsonplaceholder.typicode.com/albums")
            console.log(resp)
            setUsers(resp.data)
        } catch (error) {
            
        }
    }
    fetchData()
    },[])

    const handleAdd = async () => {
        try {
            const resp = await axios.post("https://jsonplaceholder.typicode.com/albums", {
                userId,
                id,
                title
            })
            let temp = [...users]
            temp.push(resp.data)
            setUsers(temp)
            setMsg("user added successfully")
        } catch (error) {
            setMsg("Something went wrong in add user")
        }
    }

    const handleDelete = async (id) =>{
        try {
            await axios.delete(`https://jsonplaceholder.typicode.com/albums/${id}`)
            let temp = [...users]
            temp = temp.filter(u => u.id != id)
            setUsers(temp)
            setMsg("user deleted successfully")
        } catch (error) {
            setMsg("Something went wrong in delete user")
        }
    }

    return (
        <div className="conatiner">
            <div className="row">
                {
                    msg!=""?<div>
                        <div className="alert alert-success" role="alert">
                            {msg}
                        </div>
                    </div>:""
                }
                <div className="col-md-6">
                    <div className="card">
                        <div className="card-header">
                            <h5 className="text-center">Add User</h5>
                        </div>
                        <div className="card-body">
                            <div>
                                <label>Enter UserId:</label>
                                <input type="number" className="form-control" onChange={($e) => setUserId($e.target.value)}/>
                                <label>Enter Id:</label>
                                <input type="number" className="form-control" onChange={($e) => setId($e.target.value)}/>
                                <label>Enter Title:</label>
                                <input type="text" className="form-control" onChange={($e) => setTitle($e.target.value)}/>
                            </div>
                        </div>
                        <div className="card-footer">
                            <button className="btn btn-primary" onClick={handleAdd}>Add</button>
                        </div>
                    </div>
                </div>
                <div className="col-md-6">
                    <table className="table table-bordered">
                        <thead>
                            <tr>
                                <th scope="col">#</th>
                                <th scope="col">User ID</th>
                                <th scope="col">ID</th>
                                <th scope="col">Title</th>
                                <th scope="col">Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            {users.map((u, index) => (
                                <tr key={index}>
                                    <th scope="row">{index + 1}</th>
                                    <td>{u.userId}</td>
                                    <td>{u.id}</td>
                                    <td>{u.title}</td>
                                    <td>
                                        <button className="btn btn-danger" onClick={() => handleDelete(u.id)}>Delete</button>
                                    </td>
                                </tr>
                            ))}
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    )
}

export default POne