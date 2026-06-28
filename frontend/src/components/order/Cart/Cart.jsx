import React, { useContext, useState } from 'react';
import { CartContext } from '../../../context/CartContext';
import { useUser } from '../../../context/UserContext';
import { createOrder } from '../../../utils/api';
import { useNavigate } from 'react-router-dom';
import './Cart.css';

const Cart = () => {
  const { cart, updateQuantity, removeFromCart } = useContext(CartContext);
  const { user } = useUser();
  const navigate = useNavigate();
  const [orderError, setOrderError] = useState('');

  const total = cart.reduce((sum, item) => sum + item.price * item.quantity, 0);

  const handleCheckout = async () => {
    if (!user || !user.id) {
      setOrderError('You must be logged in to place an order.');
      return;
    }
    const orderPayload = {
      user: { id: user.id },
      items: cart.map(item => ({
        product: { id: item.id },
        quantity: item.quantity,
        price: item.price
      })),
      deliveryMethod: 'PICKUP', // or get from user selection
      paymentMethod: 'CARD', // or get from user selection
      totalAmount: total
    };
    try {
      await createOrder(orderPayload);
      navigate('/payment');
    } catch (err) {
      setOrderError('Failed to create order.');
    }
  };

  return (
    <div className="cart-container">
      <h2>Your Cart</h2>
      {cart.length === 0 ? (
        <p>Your cart is empty.</p>
      ) : (
        <ul>
          {cart.map(item => (
            <li key={item.id} className="cart-item">
              <img src={item.image} alt={item.name} />
              <div>
                <h4>{item.name}</h4>
                <p>R{item.price} each</p>
              </div>
              <div className="cart-qty">
                <button onClick={() => updateQuantity(item.id, item.quantity - 1)}>-</button>
                <span>{item.quantity}</span>
                <button onClick={() => updateQuantity(item.id, item.quantity + 1)}>+</button>
              </div>
              <div className="cart-item-total">
                R{item.price * item.quantity}
              </div>
              <button
                className="delete-btn"
                onClick={() => removeFromCart(item.id)}
                title="Remove item"
              >
                Delete
              </button>
            </li>
          ))}
        </ul>
      )}
      <div className="cart-total">
        <strong>Total: R{total}</strong>
      </div>
      {cart.length > 0 && (
        <>
          <button
            className="checkout-btn"
            onClick={handleCheckout}
          >
            Checkout
          </button>
          {orderError && <div className="error">{orderError}</div>}
        </>
      )}
    </div>
  );
};

export default Cart;