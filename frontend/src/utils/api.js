// Create order
export const createOrder = async (order) => {
  const res = await fetch('http://localhost:8080/api/orders', {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(order),
  });
  return await res.json();
};
// Create product with image (multipart)
export const createProductWithImage = async ({ name, description, price, categoryId, available, image }) => {
  const formData = new FormData();
  formData.append('name', name);
  formData.append('description', description);
  formData.append('price', price);
  formData.append('categoryId', categoryId);
  formData.append('available', available);
  formData.append('image', image);
  const res = await fetch('http://localhost:8080/api/products/with-image', {
    method: 'POST',
    body: formData,
  });
  return await res.json();
};
// Product management APIs
export const getAllProducts = async () => {
  const res = await fetch('http://localhost:8080/api/products');
  return await res.json();
};

export const createProduct = async (product) => {
  const res = await fetch('http://localhost:8080/api/products', {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(product),
  });
  return await res.json();
};

export const deleteProduct = async (id) => {
  await fetch(`http://localhost:8080/api/products/${id}`, { method: 'DELETE' });
};
const API_BASE_URL = 'http://localhost:8080/api/auth';

// Login user
export async function loginUser(userData) {
  try {
    const response = await fetch(`${API_BASE_URL}/login`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(userData)
    });
    const rawBody = await response.text();
    let data;
    try {
      data = JSON.parse(rawBody);
    } catch (jsonError) {
      data = rawBody;
    }
    if (!response.ok) {
      throw new Error((data && data.message) ? data.message : (typeof data === 'string' ? data : 'Login failed'));
    }
    return data;
  } catch (error) {
    throw new Error('Login error: ' + error.message);
  }
}

// Register user
export const registerUser = async (userData) => {
  try {
    const response = await fetch(`${API_BASE_URL}/register`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(userData)
    });
    const rawBody = await response.text();
    let data;
    try {
      data = JSON.parse(rawBody);
    } catch (jsonError) {
      data = rawBody;
    }
    if (!response.ok) {
      throw new Error((data && data.message) ? data.message : (typeof data === 'string' ? data : 'Registration failed'));
    }
    return data;
  } catch (error) {
    throw new Error('Registration error: ' + error.message);
  }
}

export const getUser = async (id) => {
  try {
    const response = await fetch(`http://localhost:8080/api/auth/${id}`);
    const rawBody = await response.text();
    let data;
    try {
      data = JSON.parse(rawBody);
    } catch (jsonError) {
      data = rawBody;
    }
    if (!response.ok) {
      throw new Error((data && data.message) ? data.message : (typeof data === 'string' ? data : 'Failed to fetch user'));
    }
    return data;
  } catch (error) {
    throw new Error('Fetch user error: ' + error.message);
  }
};

export const updateUser = async (id, userData) => {
  try {
    const response = await fetch(`http://localhost:8080/api/auth/${id}`, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(userData)
    });
    const rawBody = await response.text();
    let data;
    try {
      data = JSON.parse(rawBody);
    } catch (jsonError) {
      data = rawBody;
    }
    if (!response.ok) {
      throw new Error((data && data.message) ? data.message : (typeof data === 'string' ? data : 'Failed to update user'));
    }
    return data;
  } catch (error) {
    throw new Error('Update user error: ' + error.message);
  }
};

export const deleteUser = async (id) => {
  try {
    const response = await fetch(`http://localhost:8080/api/auth/${id}`, {
      method: 'DELETE'
    });
    if (!response.ok) {
      const rawBody = await response.text();
      throw new Error(rawBody || 'Failed to delete user');
    }
    return true;
  } catch (error) {
    throw new Error('Delete user error: ' + error.message);
  }
};