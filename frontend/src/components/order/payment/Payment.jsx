import React, { useState } from 'react';
import './Payment.css';
import OrderTracking from '../OrderTracking/OrderTracking';

const Payment = () => {
  const [method, setMethod] = useState('cash');
  const [cardNumber, setCardNumber] = useState('');
  const [pin, setPin] = useState('');
  const [showTracking, setShowTracking] = useState(false);
  const [status, setStatus] = useState('');
  const [message, setMessage] = useState('');

  const handleSubmit = (e) => {
    e.preventDefault();
    if (method === 'card') {
      if (cardNumber.length < 8 || pin.length !== 3) {
        setMessage('Please enter a valid card number and 3-digit pin.');
        setShowTracking(false);
        return;
      }
      setMessage('Payment Successful!');
      setStatus('Ready to collect');
      setShowTracking(true);
    } else {
      setMessage('Congratulations! Your order is placed.');
      setStatus('Pending');
      setShowTracking(true);
    }
  };

  return (
    <div className="payment-container">
      <h2>Choose Payment Method</h2>
      {!showTracking && (
        <form onSubmit={handleSubmit}>
          <label>
            <input
              type="radio"
              name="payment"
              value="cash"
              checked={method === 'cash'}
              onChange={() => setMethod('cash')}
            />
            Cash
          </label>
          <label>
            <input
              type="radio"
              name="payment"
              value="card"
              checked={method === 'card'}
              onChange={() => setMethod('card')}
            />
            Card Payment
          </label>

          {method === 'card' && (
            <div className="card-fields">
              <input
                type="text"
                placeholder="Card Number"
                value={cardNumber}
                onChange={e => setCardNumber(e.target.value.replace(/\D/g, ''))}
                maxLength={16}
                minLength={8}
                required
              />
              <input
                type="password"
                placeholder="PIN (3 digits)"
                value={pin}
                onChange={e => setPin(e.target.value.replace(/\D/g, '').slice(0, 3))}
                maxLength={3}
                required
              />
              <button type="submit" className="pay-btn">
                Pay Now
              </button>
            </div>
          )}

          {method === 'cash' && (
            <button type="submit" className="pay-btn">
              Place Your Order
            </button>
          )}
        </form>
      )}
      {message && showTracking && (
        <div className="payment-message">
          <p>{message}</p>
        </div>
      )}
      {showTracking && <OrderTracking status={status} />}
    </div>
  );
};

export default Payment;