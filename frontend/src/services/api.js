const API_BASE_URL = import.meta.env.VITE_API_URL || 'http://localhost:8080/api'

function buildHeaders(extraHeaders = {}) {
  const token = localStorage.getItem('token')
  const headers = {
    'Content-Type': 'application/json',
    ...extraHeaders,
  }

  if (token) {
    headers.Authorization = `Bearer ${token}`
  }

  return headers
}

async function apiRequest(path, options = {}) {
  const response = await fetch(`${API_BASE_URL}${path.startsWith('/') ? path : `/${path}`}`, {
    ...options,
    headers: buildHeaders(options.headers),
  })

  const content = await response.text()
  let payload = null
  if (content) {
    try {
      payload = JSON.parse(content)
    } catch {
      payload = content
    }
  }

  if (!response.ok) {
    throw new Error(payload?.message || payload?.error || 'Request failed')
  }

  return payload
}

export function setAuthSession(authPayload) {
  localStorage.setItem('token', authPayload.token)
  localStorage.setItem('user', JSON.stringify({
    id: authPayload.id,
    username: authPayload.username,
    email: authPayload.email,
    role: authPayload.role,
  }))
}

export function getStoredAuthUser() {
  const rawUser = localStorage.getItem('user')
  return rawUser ? JSON.parse(rawUser) : null
}

export function clearAuthSession() {
  localStorage.removeItem('token')
  localStorage.removeItem('user')
}

export function loginUser(credentials) {
  return apiRequest('/auth/login', {
    method: 'POST',
    body: JSON.stringify(credentials),
  })
}

export function registerUser(payload) {
  return apiRequest('/auth/register', {
    method: 'POST',
    body: JSON.stringify(payload),
  })
}

export function getCurrentUser() {
  return apiRequest('/auth/me')
}

export function listTickets() {
  return apiRequest('/tickets')
}

export function createTicket(payload) {
  return apiRequest('/tickets', {
    method: 'POST',
    body: JSON.stringify(payload),
  })
}

export function updateTicket(id, payload) {
  return apiRequest(`/tickets/${id}`, {
    method: 'PUT',
    body: JSON.stringify(payload),
  })
}

export function deleteTicket(id) {
  return apiRequest(`/tickets/${id}`, {
    method: 'DELETE',
  })
}

export function getTicket(id) {
  return apiRequest(`/tickets/${id}`)
}

export function getTicketStatistics() {
  return apiRequest('/tickets/statistics')
}

export function analyzeTicket(description) {
  return apiRequest('/ai/analyze', {
    method: 'POST',
    body: JSON.stringify({ description }),
  })
}

export default apiRequest