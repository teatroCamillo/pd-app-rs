import React from "react";
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap-icons/font/bootstrap-icons.css";
import { Routes, Route } from "react-router-dom";
import HomeComponent from "./components/HomeComponent";
import GettingStartedComponent from "./components/GettingStartedComponent";
import AboutAppComponent from "./components/AboutAppComponent";
import SignInComponent from "./components/SignInComponent";
import SignUpComponent from "./components/SignUpComponent";
import StartComponent from "./components/StartComponent";
import FormRiskComponent from "./components/FormRiskComponent";
import ErrorComponent from "./components/ErrorComponent";
import AuthenticatedRoute from "./components/AuthenticatedRoute";
import SignOutComponent from "./components/SignOutComponent";
import ForgottenPasswordComponent from "./components/ForgottenPasswordComponent";

import withNavigation from "./components/utils/withNavigation";

function App() {

  const LoginComponentWithNavigation = withNavigation(SignInComponent);

  return (
    <Routes>
      <Route path="/" element={<HomeComponent />} />
      <Route path="getting-started" element={<GettingStartedComponent />} />
      <Route path="about-app" element={<AboutAppComponent />} />
      <Route path="signin" element={<LoginComponentWithNavigation />} />
      <Route path="signout" element={<SignOutComponent/>} />
      <Route path="signup" element={<SignUpComponent />} />
      <Route path="forgotten-password" element={<ForgottenPasswordComponent />} />
      <Route path="start/:username" element={<AuthenticatedRoute><StartComponent/></AuthenticatedRoute>} />
      <Route path="risk" element={<AuthenticatedRoute><FormRiskComponent /></AuthenticatedRoute>} />
      <Route path="*" element={<ErrorComponent />} />
    </Routes>
  );
}

export default App;
