import axios from "axios"
import { useEffect, useState } from "react"

function Example2() {
    let [fName, setFName] = useState("")
    let [lName, setLName] = useState("")
    let [age, setAge] = useState(undefined)
    let [msg, setMsg] = useState("")

    let [userArray, setUserArray] = useState([])

    const post = async () => {
            try {
                const re = await axios.post("https://dummyjson.com/c/490a-b0c3-484d-ba0d", {
                    "fname": fName,
                    "lname": lName,
                    "age": age
                })
                let temp = [...userArray]
                temp.push(re.data)
                setUserArray(temp)
                setMsg("User Added Successfully")
            } catch (error) {
                setMsg("Operation failed, try again!!!")
            }
    }

    useEffect(() => {
        const getUserData =async () => {
            try {
                const response = await axios.get('https://dummyjson.com/c/4e67-0b66-4c8f-833a')
                setUserArray(response.data)
            } catch (error) {
                setMsg("Error")
            }
        }
        getUserData()
    }, [])

    return (
        <div className="container">
            <div className="row">
                <div className="col-md-6">
                    <div className="card">
                        <div className="card-header">Enter User Details</div>
                        <div className="card-body">
                            <div>
                                <label>Enter F_Name:</label>
                                <input type="text" onChange={($e) => setFName($e.target.value)} className="form-control"/>
                            </div>
                            <div>
                                <label>Enter L_Name:</label>
                                <input type="text" onChange={($e) => setLName($e.target.value)} className="form-control"/>
                            </div>
                            <div>
                                <label>Enter Age:</label>
                                <input type="number" onChange={($e) => setAge($e.target.value)} className="form-control"/>
                            </div>
                        </div>
                        <div className="card-footer">
                            <button className="btn btn-primary" onClick={() => post()}>Submit</button>
                        </div>
                    </div>
                </div>
                <div className="col-md-6">
                    <table className="table table-striped">
                        <thead>
                            <tr>
                                <th scope="col">#</th>
                                <th scope="col">F_Name</th>
                                <th scope="col">L_Name</th>
                                <th scope="col">Age</th>
                                <th scope="col">Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                userArray.map((user, index) => (
                                    <tr key={user.id}>
                                        <th scope="row">{index + 1}</th>
                                        <td>{user.fname}</td>
                                        <td>{user.lname}</td>
                                        <td>{user.age}</td>
                                        <td>
                                            <button className="btn btn-danger">Delete</button>
                                        </td>
                                    </tr>
                                )
                                )
                            }
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    )
}

export default Example2