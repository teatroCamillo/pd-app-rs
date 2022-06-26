import React from 'react'
import Container from 'react-bootstrap/Container';
import Footer from './Footer';
import Header from './Header';


const Root = () => {
  return (
    <Container className='h-100'>
      <Header/>
      <h2>Root</h2>
      <Footer/>
    </Container>
  )
}

export default Root;