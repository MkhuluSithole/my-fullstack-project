import React from 'react';
import Header from '../../components/common/Header/Header';
import Footer from '../../components/common/Footer/Footer';
import './About.css';

const About = () => (
  <div className="about-page">
    <Header />
    <main className="about-content">
      <h1>About CampusEats</h1>
      <p>
  CampusEats is an online food ordering platform designed for everyone. 
        Our mission is to make campus dining fast, convenient, and enjoyable by allowing you to order your favorite meals online and skip the line.
      </p>
      <p>
  Whether you are busy or on the go, CampusEats helps you save time and enjoy fresh meals from campus vendors. 
        We are committed to providing a seamless experience, secure payments, and reliable order tracking.
      </p>
      <p>
        Thank you for choosing CampusEats. We are here to make your campus life tastier and easier!
      </p>
    </main>
    <Footer />
  </div>
);

export default About;