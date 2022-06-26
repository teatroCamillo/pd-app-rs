import React from 'react';
import { useLocation } from "react-router-dom";
import Container from 'react-bootstrap/Container';
import Navbar from 'react-bootstrap/Navbar';
import CustomLink from './CustomLink';


const Header = () => {

    const location = useLocation('');

  return (
    <Container className="h-25">
        <header>
            <Navbar
                style={{
                borderBottom: "solid 1px",
                paddingBottom: "1rem",
                }}
                >
                <Container className="justify-content-center">
                    {location.pathname === "/" ?
                    <nav>
                        <ul>
                            <CustomLink to="/login" >Login</CustomLink>
                            <CustomLink to="/signup" >Sign up</CustomLink>
                        </ul>
                    </nav>
                        :
                    <nav>
                        <ul>
                            <CustomLink to="/" >Root</CustomLink>
                            <CustomLink to="/home" >Home</CustomLink>
                            <CustomLink to="/risk" >FormRisk</CustomLink>
                        </ul>
                        {/*<Nav.Link ><Link to="/">Root</Link></Nav.Link>
                        <Nav.Link ><Link to="/home">Home</Link></Nav.Link>
                        <Nav.Link ><Link to="/risk">FormRisk</Link></Nav.Link>*/}
                    </nav>}
                </Container>
            </Navbar>
        </header>
    </Container>
  )
}

export default Header