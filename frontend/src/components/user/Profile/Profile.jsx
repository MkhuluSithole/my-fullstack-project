import React, { useEffect, useState } from 'react';
import { useUser } from '../../../context/UserContext';
import { getUser, updateUser, deleteUser } from '../../../utils/api';
import { useNavigate } from 'react-router-dom';
import './Profile.css';
import Header from '../../common/Header/Header';

const Profile = () => {
  const { user, logout } = useUser();
  const [profile, setProfile] = useState(null);
  const [editMode, setEditMode] = useState(false);
  const [form, setForm] = useState({});
  const [error, setError] = useState('');
  const navigate = useNavigate();

  useEffect(() => {
    if (user && user.id) {
      getUser(user.id)
        .then(setProfile)
        .catch(err => setError(err.message));
    }
  }, [user]);

  const handleEdit = () => {
    setForm({
      name: profile.name || '',
      lastName: profile.lastName || '',
      email: profile.contact?.email || '',
      number: profile.contact?.number || '',
      street: profile.address?.street || '',
      city: profile.address?.city || '',
      postalCode: profile.address?.postalCode || '',
      country: profile.address?.country || '',
      role: profile.role || 'CUSTOMER',
    });
    setEditMode(true);
  };

  const handleChange = e => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleUpdate = async e => {
    e.preventDefault();
    try {
      const updated = await updateUser(user.id, form);
      setProfile(updated);
      setEditMode(false);
      setError('');
    } catch (err) {
      setError(err.message);
    }
  };

  const handleDelete = async () => {
    if (window.confirm('Are you sure you want to delete your account?')) {
      try {
        await deleteUser(user.id);
        logout();
        navigate('/');
      } catch (err) {
        setError(err.message);
      }
    }
  };

  if (!user) {
    return <div className="profile-container">Please sign in to view your profile.</div>;
  }

  if (!profile) {
    return <div className="profile-container">Loading profile...</div>;
  }

  return (
    <>
      <Header />
      <div className="profile-container">
        <h2>Profile</h2>
        {error && <div className="error">{error}</div>}
        {!editMode ? (
          <div className="profile-details">
            <p><strong>Name:</strong> {profile.name} {profile.lastName}</p>
            <p><strong>Email:</strong> {profile.contact?.email}</p>
            <p><strong>Phone:</strong> {profile.contact?.number}</p>
            <p><strong>Address:</strong> {profile.address?.street}, {profile.address?.city}, {profile.address?.postalCode}, {profile.address?.country}</p>
            <p><strong>Role:</strong> {profile.role}</p>
            <button onClick={handleEdit}>Edit Profile</button>
            <button className="delete-btn" onClick={handleDelete}>Delete Account</button>
          </div>
        ) : (
          <form className="profile-form" onSubmit={handleUpdate}>
            <input name="name" value={form.name} onChange={handleChange} placeholder="First Name" required />
            <input name="lastName" value={form.lastName} onChange={handleChange} placeholder="Last Name" required />
            <input name="email" value={form.email} onChange={handleChange} placeholder="Email" required />
            <input name="number" value={form.number} onChange={handleChange} placeholder="Phone Number" />
            <input name="street" value={form.street} onChange={handleChange} placeholder="Street" />
            <input name="city" value={form.city} onChange={handleChange} placeholder="City" />
            <input name="postalCode" value={form.postalCode} onChange={handleChange} placeholder="Postal Code" />
            <input name="country" value={form.country} onChange={handleChange} placeholder="Country" />
            <button type="submit">Save</button>
            <button type="button" onClick={() => setEditMode(false)}>Cancel</button>
          </form>
        )}
      </div>
    </>
  );
};

export default Profile;
