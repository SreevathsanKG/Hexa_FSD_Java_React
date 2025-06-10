import { useState } from "react";
import { userData } from "../sample data/userData";
function UserList() {
    let [userArray, setUserArray] = useState(userData);

    return (
        <div>
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Name</th>
                        <th scope="col">Username</th>
                        <th scope="col">Email</th>
                        <th scope="col">City</th>
                        <th scope="col">Company Name</th>
                    </tr>
                </thead>
                <tbody>
                    {
                        userArray.map((user, index) => (
                            <tr key={user.id}>
                                <th scope="row">{index + 1}</th>
                                <td>{user.name}</td>
                                <td>{user.username} </td>
                                <td>{user.email}</td>
                                <td>{user.address.city}</td>
                                <td>{user.company.name}</td>
                            </tr>
                        ))
                    }
                </tbody>
            </table>
        </div>
    )
}

export default UserList;