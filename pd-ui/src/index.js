import React from 'react';
import ReactDOM from 'react-dom/client';
import App from './App';
import { BrowserRouter } from "react-router-dom";
import HeaderComponent from './components/HeaderComponent';
import FooterComponent from './components/FooterComponent'
import './App.css';

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <BrowserRouter>
      <HeaderComponent/>
      <App/>
      <FooterComponent/>
    </BrowserRouter>
  </React.StrictMode>
);

