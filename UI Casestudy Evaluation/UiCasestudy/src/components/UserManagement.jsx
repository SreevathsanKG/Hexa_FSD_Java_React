import axios from "axios"
import { useEffect, useState } from "react"
import { Button } from "react-bootstrap"
import { useNavigate } from "react-router-dom"

function UserManagement() {

    const navigate = useNavigate()
    let [users, setUsers] = useState([])
    let [msg, setMsg] = useState("")
    
    useEffect(() => {
        const fetchUsers = async () => {
            try {
                const resp = await axios.get("https://gorest.co.in/public/v2/users")
                setUsers(resp.data)
            } catch (error) {
                console.log(error)
            }
        }
        fetchUsers()
    },[])

    const deleteUsers = async (id) => {
        try {
            await axios.delete("https://gorest.co.in/public/v2/users/"+id,{
                headers:{
                    "Authorization":"Bearer "+"1d3cdb53ad474784cde28e65223831b36415970c6aebe4172a06fbbb9680a702"}
            })
            let temp = [...users]
            temp = temp.filter(u => u.id != id)
            setUsers(temp)
            setMsg("User deleted successfully")
        } catch (error) {
            setMsg("Something went wrong while deleting user")
        }
    }
    return(
        <div className="container">
            <div className="row" >
                {
                    msg!=""?<div>
                        <div className="alert alert-success" role="alert">
                            {msg}
                        </div>
                    </div>:""
                }
                <div className="col-md-12 justify-content-center">
                    <div className="mt-3 mb-3">
                        <h1>User Management</h1>
                    </div>
                    <Button onClick={() => navigate("/adduser")}>Add User</Button>
                    <table className="table">
                        <thead>
                            <tr>
                                <th scope="col">#</th>
                                <th scope="col">ID</th>
                                <th scope="col">Name</th>
                                <th scope="col">Email</th>
                                <th scope="col">Gender</th>
                                <th scope="col">Status</th>
                                <th scope="col">Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                users.map((u, index) => (
                                    <tr key={index}>
                                        <th scope="row">{index + 1}</th>
                                        <td>{u.id}</td>
                                        <td>{u.name}</td>
                                        <td>{u.email}</td>
                                        <td>{u.gender}</td>
                                        <td>{u.status}</td>
                                        <td>
                                            <Button onClick={() => navigate(`/edituser/${u.id}`)}>Edit</Button>
                                            <Button onClick={() => deleteUsers(u.id)} className="ms-2">Delete</Button>
                                        </td>
                                    </tr>
                                ))
                            }
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    )
}

export default UserManagement

// 1d3cdb53ad474784cde28e65223831b36415970c6aebe4172a06fbbb9680a702