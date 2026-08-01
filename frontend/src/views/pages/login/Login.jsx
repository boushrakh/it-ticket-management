import React, { useState } from 'react'
import { Link, useNavigate } from 'react-router-dom'
import {
  CAlert,
  CButton,
  CCard,
  CCardBody,
  CCol,
  CContainer,
  CForm,
  CFormInput,
  CInputGroup,
  CInputGroupText,
  CRow,
} from '@coreui/react'
import CIcon from '@coreui/icons-react'
import { cilLockLocked, cilUser } from '@coreui/icons'

import { loginUser, setAuthSession } from '../../../services/api'

const Login = () => {
  const navigate = useNavigate()
  const [form, setForm] = useState({ username: 'admin', password: 'admin123' })
  const [loading, setLoading] = useState(false)
  const [error, setError] = useState('')

  const handleChange = (event) => {
    const { name, value } = event.target
    setForm((current) => ({ ...current, [name]: value }))
  }

  const handleLogin = async (event) => {
    event.preventDefault()
    setLoading(true)
    setError('')

    try {
      const data = await loginUser({ username: form.username, password: form.password })
      setAuthSession(data)
      navigate('/dashboard')
    } catch (err) {
      setError(err.message || 'Connexion impossible')
    } finally {
      setLoading(false)
    }
  }

  return (
    <div className="bg-body-tertiary min-vh-100 d-flex align-items-center">
      <CContainer>
        <CRow className="justify-content-center">
          <CCol md={8} lg={7} xl={6}>
            <CCard className="shadow border-0 overflow-hidden">
              <div className="row g-0">
                <div className="col-lg-7 p-4 p-lg-5">
                  <CCardBody>
                    <div className="mb-4">
                      <div className="text-primary fw-semibold text-uppercase small">IT Ticket Management</div>
                      <h1 className="h3 mt-2 mb-2">Connexion au tableau de bord</h1>
                      <p className="text-body-secondary mb-0">
                        Accédez à la plateforme de suivi de tickets et à l’assistance IA.
                      </p>
                    </div>

                    {error && <CAlert color="danger">{error}</CAlert>}

                    <CForm onSubmit={handleLogin}>
                      <CInputGroup className="mb-3">
                        <CInputGroupText>
                          <CIcon icon={cilUser} />
                        </CInputGroupText>
                        <CFormInput
                          name="username"
                          placeholder="Nom d’utilisateur"
                          autoComplete="username"
                          value={form.username}
                          onChange={handleChange}
                          required
                        />
                      </CInputGroup>

                      <CInputGroup className="mb-4">
                        <CInputGroupText>
                          <CIcon icon={cilLockLocked} />
                        </CInputGroupText>
                        <CFormInput
                          name="password"
                          type="password"
                          placeholder="Mot de passe"
                          autoComplete="current-password"
                          value={form.password}
                          onChange={handleChange}
                          required
                        />
                      </CInputGroup>

                      <div className="d-grid gap-2">
                        <CButton type="submit" color="primary" disabled={loading}>
                          {loading ? 'Connexion...' : 'Se connecter'}
                        </CButton>
                        <Link to="/register" className="text-decoration-none">
                          <CButton color="secondary" variant="outline" className="w-100">
                            Créer un compte
                          </CButton>
                        </Link>
                      </div>
                    </CForm>
                  </CCardBody>
                </div>
                <div className="col-lg-5 bg-primary text-white p-4 p-lg-5 d-flex flex-column justify-content-center">
                  <h2 className="h4">Plateforme IT moderne</h2>
                  <p className="mb-4">
                    Gérez vos incidents, priorités et analyse IA depuis une seule interface professionnelle.
                  </p>
                  <ul className="list-unstyled small mb-0">
                    <li>• Tableau de bord temps réel</li>
                    <li>• Tickets triés par priorité</li>
                    <li>• Suggestions d’IA intégrées</li>
                  </ul>
                </div>
              </div>
            </CCard>
          </CCol>
        </CRow>
      </CContainer>
    </div>
  )
}

export default Login