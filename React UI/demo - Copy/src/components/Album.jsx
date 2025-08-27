import { useEffect, useState } from "react"

function Album() {

    let [albumArray, setAlbumArray] = useState([])
    useEffect(() => {
        fetch('https://jsonplaceholder.typicode.com/albums')
        .then(resp => resp.json())
        .then(data => setAlbumArray(data))
    },[])
    return (
        <div className="row">
            {
                albumArray.map((album) => (
                    <div className="col-md-3">
                        <div class="" key={album.id}>
                            <div class="card-header">ID : {album.id}</div>
                            <div class="card-body">
                                <h5 class="card-title">{album.userId}</h5>
                                <p class="card-text">{album.title}</p>
                                <a href="#" class="btn btn-primary">View Comments</a>
                            </div>
                        </div>
                    </div>
                ))
            }
        </div>
    )
}

export default Album