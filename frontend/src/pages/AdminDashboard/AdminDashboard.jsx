import React, { useEffect, useState } from 'react';
import { getAllProducts, createProductWithImage, deleteProduct } from '../../utils/api';

const AdminDashboard = () => {
  const [products, setProducts] = useState([]);
  const [form, setForm] = useState({ name: '', description: '', price: '', categoryId: '', available: true });
  const [image, setImage] = useState(null);
  const [error, setError] = useState('');

  useEffect(() => {
    fetchProducts();
  }, []);

  const fetchProducts = async () => {
    try {
      const data = await getAllProducts();
      setProducts(data);
    } catch (err) {
      setError('Failed to fetch products');
    }
  };

  const handleChange = e => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleAddProduct = async e => {
    e.preventDefault();
    try {
      await createProductWithImage({ ...form, image });
      setForm({ name: '', description: '', price: '', categoryId: '', available: true });
      setImage(null);
      fetchProducts();
    } catch (err) {
      setError('Failed to add product');
    }
  };

  const handleDelete = async id => {
    try {
      await deleteProduct(id);
      fetchProducts();
    } catch (err) {
      setError('Failed to delete product');
    }
  };

  return (
    <div>
      <h2>Admin Dashboard</h2>
      <form onSubmit={handleAddProduct}>
        <input name="name" value={form.name} onChange={handleChange} placeholder="Product Name" required />
        <input name="description" value={form.description} onChange={handleChange} placeholder="Description" required />
        <input name="price" value={form.price} onChange={handleChange} placeholder="Price" required type="number" />
        <input name="categoryId" value={form.categoryId} onChange={handleChange} placeholder="Category ID" required />
        <input type="checkbox" name="available" checked={form.available} onChange={e => setForm({ ...form, available: e.target.checked })} /> Available
        <input type="file" accept="image/*" onChange={e => setImage(e.target.files[0])} required />
        <button type="submit">Add Product</button>
      </form>
      {error && <div style={{color: 'red'}}>{error}</div>}
      <h3>Products</h3>
      <ul>
        {products.map(p => (
          <li key={p.id}>
            {p.name} - {p.price}
            <button onClick={() => handleDelete(p.id)}>Delete</button>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default AdminDashboard;
