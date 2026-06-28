import React, { useEffect, useState } from 'react';
import MenuItem from '../MenuItem/MenuItem';
import Cart from '../../order/Cart/Cart';
import Header from '../../common/Header/Header';
import { getAllProducts } from '../../../utils/api';
import './MenuList.css';

const MenuList = () => {
  const [products, setProducts] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');

  useEffect(() => {
    const fetchProducts = async () => {
      try {
        const data = await getAllProducts();
        setProducts(data);
      } catch (err) {
        setError('Failed to load products');
      } finally {
        setLoading(false);
      }
    };
    fetchProducts();
  }, []);

  return (
    <div className="menu-page">
      <Header />
      <Cart />
      <div className="menu-list">
        {loading ? (
          <p>Loading products...</p>
        ) : error ? (
          <p>{error}</p>
        ) : products.length === 0 ? (
          <p>No menu items available.</p>
        ) : (
          products.map(item => (
            <MenuItem key={item.id} item={item} />
          ))
        )}
      </div>
    </div>
  );
};

export default MenuList;