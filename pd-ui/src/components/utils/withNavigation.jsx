import { useNavigate } from "react-router-dom";

/**
 * @description
 *    Decorate passed component with useNavigate() hook. Thanks that we don't have to
 *    provide every time and in each component a "useNavigate()" hook, if we want to perform navigation.
 *    Because we decorate wanted component here.
 *    Remember in the target component we should provide "props" as arguments and call
 *    "props.navigate("/path")" - example SingInComponent.jsx.
 *    And we need to create dedicated const/variable in the right place,
 *    often it's a routing place - here it's App.js.
 *    Example: const SignInComponentWithNavigation = withNavigation(SignInComponent);
 *
 * @param Component
 * @returns Component WithNavigation
 */
function withNavigation(Component) {
    return props => <Component {...props} navigate={useNavigate()} />;
  }

export default withNavigation;