import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { registerUser } from '../../../utils/api'; 
import './SignUp.css';

const SignUp = () => {
  const [form, setForm] = useState({
    name: '',
    lastName: '',
    email: '',
    password: '',
    number: '',
    street: '',
    city: '',
    postalCode: '',
    country: ''
  });
  const [error, setError] = useState('');
  const navigate = useNavigate();

  const handleChange = e => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = async e => {
    e.preventDefault();
    try {
      const res = await registerUser(form);
      if (res && res.id) {
        navigate('/login');
      } else {
        setError('Registration failed. Try again.');
      }
    } catch (err) {
      setError('Registration failed. Try again.');
    }
  };
  return (
    <div className="signup-container">
      <button className="back-btn" type="button" onClick={() => navigate(-1)}>
        &larr; Back
      </button>
      <h2>Sign Up</h2>
      <form onSubmit={handleSubmit}>
        <input
          type="text"
          name="name"
          placeholder="First Name"
          value={form.name}
          onChange={handleChange}
          required
        />
        <input
          type="text"
          name="lastName"
          placeholder="Last Name"
          value={form.lastName}
          onChange={handleChange}
          required
        />
        <input
          type="email"
          name="email"
          placeholder="Email"
          value={form.email}
          onChange={handleChange}
          required
        />
        <input
          type="password"
          name="password"
          placeholder="Password"
          value={form.password}
          onChange={handleChange}
          required
        />
        <input
          type="text"
          name="number"
          placeholder="Contact Number"
          value={form.number}
          onChange={handleChange}
          required
        />
  {/* role selection removed */}
        <input
          type="text"
          name="street"
          placeholder="Street Address"
          value={form.street}
          onChange={handleChange}
          required
        />
        <input
          type="text"
          name="city"
          placeholder="City"
          value={form.city}
          onChange={handleChange}
          required
        />
        <input
          type="text"
          name="postalCode"
          placeholder="Postal Code"
          value={form.postalCode}
          onChange={handleChange}
          required
        />
        <input
          type="text"
          name="country"
          placeholder="Country"
          value={form.country}
          onChange={handleChange}
          required
        />
        <button type="submit">Sign Up</button>
        {error && <div className="error">{error}</div>}
      </form>
      <div className="auth-links">
        <span>
          Already have an account?{' '}
          <button
            className="link-btn"
            type="button"
            onClick={() => navigate('/login')}
          >
            Sign In
          </button>
        </span>
      </div>
    </div>
  );
};

export default SignUp;