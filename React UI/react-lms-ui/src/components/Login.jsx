import axios from "axios"
import { useState } from "react"
import { useNavigate } from "react-router-dom"

function Login() {

    let [username, setUsername] = useState("")
    let [password, setPassword] = useState("")
    let [msg, setMsg] = useState("")

    const navigate = useNavigate()

    const processLogin = async () => {
        // encode username and password using btoa
        let encodedString = window.btoa(username+":"+password)
        // console.log(encodedString)
        // console.log(window.atob(encodedString))
        try {
            const response = await axios.get("http://localhost:8080/api/user/token",{
                headers: {"Authorization":"Basic "+encodedString}
            })
            // console.log(response.data.token)
            let token = response.data.token  //<-- this is our access token, save it for later usage. (redux,localstorage)
            localStorage.setItem("token", token) //<-- saving token for future use in browsers local storage mem
            // get user details
            let details = await axios.get("http://localhost:8080/api/user/details",{
                headers: {"Authorization":"Bearer "+ token}
            })
            // console.log(details)

            let role = details.data.user.role
            switch (role) {
                case "LEARNER":
                    navigate("/learner")
                    break;
                case "AUTHOR":
                    navigate("/author")
                    break;
                case "EXECUTIVE":
                    navigate("/executive")
                    break;
                default:
                    setMsg("Login Disabled, contact admin at admin@example.com")
                    break;
            }
            setMsg("Login Success!!")
        } catch (error) {
            setMsg("Invalid Credentials!!")
        }
    }

    return (
        <div className="container">
            <div className="row">
                <div className="col-md-12">
                    <br /><br /><br /><br />
                </div>
            </div>
            <div className="row">
                <div className="col-md-3"></div>
                <div className="col-md-5">
                    <div className="card">
                        <div className="card-header">LOGIN</div>
                        <div className="card-body">
                            {
                                msg!=""?<div>
                                    <div className="alert alert-info">{msg}</div>
                                </div>:""
                            }
                            <div className="mb-2">
                                <label>Enter Username: </label>
                                <input type="text" onChange={($e) => setUsername($e.target.value)} className="form-control"/>
                            </div>
                            <div className="mb-2">
                                <label>Enter Password: </label>
                                <input type="text" onChange={($e) => setPassword($e.target.value)} className="form-control"/>
                            </div>
                            <div className="mb-2">
                                <button className="btn btn-primary" onClick={() => processLogin()}>LOGIN</button>
                            </div>
                        </div>
                        <div className="card-footer">
                            Don't have an account? Sign Up here.
                        </div>
                    </div>
                </div>
                <div className="col-md-3"></div>
            </div>
        </div>
    )
}

export default Login