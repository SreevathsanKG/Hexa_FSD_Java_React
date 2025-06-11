import { useState } from "react"

function POne() {

    let [name, setName] = useState("")
    let [email, setEmail] = useState("")
    let [contact, setContact] = useState(undefined)

    return (
        <div className="conatiner">
            <div className="row">
                <div className="col-md-6">
                    <div className="card">
                        <div className="card-header">
                            <h5 className="text-center">Add User</h5>
                        </div>
                        <div className="card-body">
                            <div>
                                <label>Enter Name:</label>
                                <input type="text" className="form-control" onChange={($e) => setName($e.target.value)}/>
                            </div>
                        </div>
                        <div className="card-footer">
                            <button className="btn btn-primary">Add</button>
                        </div>
                    </div>
                </div>
                <div className="col-md-6">
                    <table className="table table-bordered">
                        <thead>
                            <tr>
                                <th scope="col">#</th>
                                <th scope="col">Name</th>
                                <th scope="col">Email</th>
                                <th scope="col">Contact</th>
                                <th scope="col">Action</th>
                            </tr>
                        </thead>
                        <tbody>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    )
}

export default POne