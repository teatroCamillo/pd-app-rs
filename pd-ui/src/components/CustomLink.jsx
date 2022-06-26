import { Link } from "react-router-dom";

const CustomLink = ({to, children, ...props}) => {

    return (
    <li className="d-inline mx-3" style={{listStyleType: "none"}}>
        <Link to={to} {...props} >{children}</Link>
    </li>
  )
}

export default CustomLink;