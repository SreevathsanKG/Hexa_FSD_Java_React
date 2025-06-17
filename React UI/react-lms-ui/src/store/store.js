// v vl config here
// src/store/store.js

import { configureStore } from "@reduxjs/toolkit";
import UserReducer from "./reduces/UserReducer";

// register all your reducers
const store = configureStore({
    reducer: {
        user: UserReducer
    }
})

export default store