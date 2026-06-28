import React from 'react';
import './OrderTracking.css';

const OrderTracking = ({ status }) => {
  // status prop can be "Pending", "Ready to collect", etc.
  return (
    <div className="order-tracking-container">
      <h2>Order Tracking</h2>
      <div className={`order-status ${status === 'Ready to collect' ? 'ready' : 'pending'}`}>
        Status: <strong>{status}</strong>
      </div>
      {status === 'Pending' && <p>Your order is being prepared. Please wait...</p>}
      {status === 'Ready to collect' && <p>Your order is ready! Please collect it at the counter.</p>}
    </div>
  );
};

export default OrderTracking;