import React, { useContext } from 'react';
import { CartContext } from '../../../context/CartContext';
import './MenuItem.css';

const MenuItem = ({ item }) => {
  const { addToCart } = useContext(CartContext);

  return (
    <div className="menu-item">
      {item.imageBlob ? (
        <img src={`data:image/jpeg;base64,${item.imageBlob}`} alt={item.name} />
      ) : (
        <div className="no-image">No Image</div>
      )}
      <h3>{item.name}</h3>
      <p>{item.description}</p>
      <div className="menu-item-footer">
        <span className="price">R{item.price}</span>
        <button className="add-cart-btn" onClick={() => addToCart(item)}>
          Add to Cart
        </button>
      </div>
    </div>
  );
};

export default MenuItem;