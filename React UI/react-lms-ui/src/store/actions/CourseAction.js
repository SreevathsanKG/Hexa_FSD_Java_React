import axios from "axios"

export const fetchAllCourses = (dispatch) => () => {
    axios.get('http://localhost:8080/api/course/get/all')
        .then(function (response) {
            dispatch({
                'payload':response.data,
                'type': 'FETCH_ALL_COURSES'
            })
        })
}