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
import UnauthenticatedRoute from "./components/UnauthenticatedRoute";

import withNavigation from "./components/utils/withNavigation";

function App() {

  const SignInComponentWithNavigation = withNavigation(SignInComponent);
  const SignUpComponentWithNavigation = withNavigation(SignUpComponent);

  return (
    <Routes>
      {/*public without unauth paths */}
      <Route path="getting-started" element={<GettingStartedComponent />} />
      <Route path="about-app" element={<AboutAppComponent />} />

      {/*public with unauth paths */}
      <Route path="/" element={<UnauthenticatedRoute><HomeComponent /></UnauthenticatedRoute>} />
      <Route path="signin" element={<UnauthenticatedRoute><SignInComponentWithNavigation /></UnauthenticatedRoute>} />
      <Route path="signout" element={<UnauthenticatedRoute><SignOutComponent/></UnauthenticatedRoute>} />
      <Route path="signup" element={<UnauthenticatedRoute><SignUpComponentWithNavigation /></UnauthenticatedRoute>} />
      <Route path="forgotten-password" element={<UnauthenticatedRoute><ForgottenPasswordComponent /></UnauthenticatedRoute>} />

      {/*required auth paths */}
      <Route path="start" element={<AuthenticatedRoute><StartComponent/></AuthenticatedRoute>} />
      <Route path="risk" element={<AuthenticatedRoute><FormRiskComponent /></AuthenticatedRoute>} />

      {/*unknown paths */}
      <Route path="*" element={<ErrorComponent />} />
    </Routes>
  );
}

export default App;
