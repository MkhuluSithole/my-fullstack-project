import React from 'react';
import './Banner.css';
import background from '../../../assets/Background.jpg';

const Banner = () => (
  <section
    className="banner"
    style={{ backgroundImage: `url(${background})` }}
  >
    <div className="banner-content">
      <h1>Order Food Online, Skip the Line!</h1>
  <p>Fast, fresh, and convenient meals for everyone.</p>
      <a href="/menu" className="banner-btn">Order Now</a>
    </div>
  </section>
);

export default Banner;