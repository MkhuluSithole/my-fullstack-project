import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { loginUser } from '../../../utils/api';
import { useUser } from '../../../context/UserContext';
import './SignIn.css';

const SignIn = () => {
  const [email, setEmail] = useState('');   // changed from username
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');
  const { login } = useUser();
  const navigate = useNavigate();

  const handleSubmit = async e => {
    e.preventDefault();
    try {
      const res = await loginUser({ email, password }); // send email, password
      if (res && res.id) {
        login(res); // Save user in context/localStorage
        navigate('/'); // Redirect to home
      } else {
        setError('Invalid credentials.');
      }
    } catch (err) {
      setError('Invalid credentials.');
    }
  };

  return (
    <div className="auth-container">
      <button className="back-btn" type="button" onClick={() => navigate(-1)}>
        &larr; Back
      </button>
      <h2>Sign In</h2>
      <form onSubmit={handleSubmit}>
        <input
          type="email"  // email instead of username
          placeholder="Email"
          value={email}
          onChange={e => setEmail(e.target.value)}
          required
        />
        <input
          type="password"
          placeholder="Password"
          value={password}
          onChange={e => setPassword(e.target.value)}
          required
        />
        <button type="submit">Sign In</button>
        {error && <div className="error">{error}</div>}
      </form>
      <div className="auth-links">
        <a href="/forgot-password">Forgot password?</a>
        <span>
          or{' '}
          <button
            className="link-btn"
            type="button"
            onClick={() => navigate('/signup')}
          >
            Sign Up
          </button>
        </span>
      </div>
    </div>
  );
};

export default SignIn;
