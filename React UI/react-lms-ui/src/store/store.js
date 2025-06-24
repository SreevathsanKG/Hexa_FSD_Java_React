// v vl config here
// src/store/store.js

import { configureStore } from "@reduxjs/toolkit";
import UserReducer from "./reduces/UserReducer";
import CourseReducer from "./reduces/CourseReducer";

// register all your reducers
const store = configureStore({
    reducer: {
        user: UserReducer,
        courses: CourseReducer
    }
})

export default store