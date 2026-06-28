import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Home from './pages/Home/Home';
import SignIn from './components/auth/Login/SignIn';
import SignUp from './components/auth/Login/SignUp';
import About from './pages/About/About';
import MenuList from './components/menu/MenuList/MenuList';
import Cart from './components/order/Cart/Cart';
import Payment from './components/order/payment/Payment';
import AdminDashboard from './pages/AdminDashboard/AdminDashboard';
import Profile from './components/user/Profile/Profile';
import { UserProvider } from './context/UserContext';

function App() {
  return (
    <UserProvider>
    <Router>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/login" element={<SignIn />} />
        <Route path="/signup" element={<SignUp />} />
        <Route path="/about" element={<About />} />
        <Route path="/menu" element={<MenuList />} />
        <Route path="/cart" element={<Cart />} />
        <Route path="/payment" element={<Payment />} />
        <Route path="/admin" element={<AdminDashboard />} />
        <Route path="/profile" element={<Profile />} />
      </Routes>
    </Router>
    </UserProvider>
  );
}

export default App;