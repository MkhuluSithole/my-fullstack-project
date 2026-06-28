import React from 'react';
import Header from '../../components/common/Header/Header';
import Banner from '../../components/common/Banner/Banner';
import Footer from '../../components/common/Footer/Footer';
import './Home.css';

const Home = () => (
  <div className="home-page">
    <Header />
    <Banner />
    
    {/* Add more landing content here if needed */}
    <Footer />
  </div>
);

export default Home;