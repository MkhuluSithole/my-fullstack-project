import React, { useContext } from 'react';
import './Header.css';
import { CartContext } from '../../../context/CartContext';
import { useUser } from '../../../context/UserContext';
import { FaHome } from 'react-icons/fa'; // Import home icon
import { useNavigate } from 'react-router-dom';

const Header = () => {
  const { cartCount } = useContext(CartContext);
  const navigate = useNavigate();
  const { user, logout } = useUser();

  return (
    <header className="header">
      <button className="home-icon-btn" onClick={() => navigate('/')}> <FaHome size={24} /> </button>
      <div className="logo">CampusEats</div>
      <nav>
        <ul>
          <li><a href="/">Home</a></li>
          <li><a href="/profile" className="profile-link">Profile</a></li>
          <li><a href="/about">About</a></li>
          <li><a href="/menu">Menu</a></li>
          <li>
            <a href="/cart" className="cart-link">
              Cart
              {cartCount > 0 && <span className="cart-count">{cartCount}</span>}
            </a>
          </li>
          {user ? (
            <>
              <li className="user-greeting">Hi, {user.name || user.email}</li>
              <li>
                <button className="logout-btn" onClick={logout}>Logout</button>
              </li>
            </>
          ) : (
            <>
              <li><a href="/login">Sign In</a></li>
              <li><a href="/signup">Sign Up</a></li>
            </>
          )}
        </ul>
      </nav>
    </header>
  );
};

export default Header;