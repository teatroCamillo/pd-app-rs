import React from 'react'
import Container from 'react-bootstrap/Container';
import Footer from './Footer';
import Header from './Header';


const Home = () => {
  return (
    <Container className='h-100'>
      <Header/>
      <h2>Home</h2>
      <Footer/>
    </Container>
  )
}

export default Home