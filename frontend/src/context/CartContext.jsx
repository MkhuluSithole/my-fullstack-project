import React, { createContext, useState } from 'react';

export const CartContext = createContext();

export const CartProvider = ({ children }) => {
  const [cart, setCart] = useState([]);

  const addToCart = (item) => {
    setCart(prev => {
      const found = prev.find(i => i.id === item.id);
      if (found) {
        return prev.map(i =>
          i.id === item.id ? { ...i, quantity: i.quantity + 1 } : i
        );
      }
      return [...prev, { ...item, quantity: 1 }];
    });
  };

  const updateQuantity = (id, quantity) => {
    setCart(prev =>
      prev.map(i =>
        i.id === id ? { ...i, quantity: Math.max(1, quantity) } : i
      )
    );
  };

  const cartCount = cart.reduce((sum, i) => sum + i.quantity, 0);

  const removeFromCart = (id) => {
  setCart(prev => prev.filter(item => item.id !== id));
};

  return (
  <CartContext.Provider value={{ cart, addToCart, updateQuantity, removeFromCart, cartCount }}>
    {children}
  </CartContext.Provider>
);
};