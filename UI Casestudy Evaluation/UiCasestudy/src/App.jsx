import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import { BrowserRouter, Route, Routes } from 'react-router-dom'
import UserManagement from './components/UserManagement'
import AddUser from './components/AddUser'
import EditUser from './components/EditUser'

function App() {

  return (
    <BrowserRouter>
      <Routes>
        <Route path='/' element={<UserManagement/>}/>
        <Route path='/adduser' element={<AddUser/>}/>
        <Route path='/edituser/:id' element={<EditUser/>}/>
      </Routes>
    </BrowserRouter>
  )
}

export default App
