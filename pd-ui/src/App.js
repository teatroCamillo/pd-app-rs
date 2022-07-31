import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap-icons/font/bootstrap-icons.css';
import { Routes, Route } from "react-router-dom";
import Root from './components/Root';
import FormRisk from './components/FormRisk';
import Home from './components/Home';
import Login from './components/Login';
import Registration from './components/Registration';

function App() {
  return (
    <Routes>
      <Route path='/' element={<Root />} />
      <Route path='/home' element={<Home />} />
      <Route path='/login' element={<Login />} />
      <Route path='/signup' element={<Registration />} />
      <Route path='/risk' element={<FormRisk />} />
    </Routes>
  );
}

export default App;
