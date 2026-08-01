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
  CFormSelect,
  CInputGroup,
  CInputGroupText,
  CRow,
} from '@coreui/react'
import CIcon from '@coreui/icons-react'
import { cilLockLocked, cilUser } from '@coreui/icons'

import { registerUser, setAuthSession } from '../../../services/api'

const Register = () => {
  const navigate = useNavigate()
  const [form, setForm] = useState({ username: '', email: '', password: '', role: 'USER' })
  const [loading, setLoading] = useState(false)
  const [error, setError] = useState('')

  const handleChange = (event) => {
    const { name, value } = event.target
    setForm((current) => ({ ...current, [name]: value }))
  }

  const handleSubmit = async (event) => {
    event.preventDefault()
    setLoading(true)
    setError('')

    try {
      const data = await registerUser(form)
      setAuthSession(data)
      navigate('/dashboard')
    } catch (err) {
      setError(err.message || 'Impossible de créer le compte')
    } finally {
      setLoading(false)
    }
  }

  return (
    <div className="bg-body-tertiary min-vh-100 d-flex align-items-center">
      <CContainer>
        <CRow className="justify-content-center">
          <CCol md={9} lg={7} xl={6}>
            <CCard className="shadow border-0">
              <CCardBody className="p-4 p-lg-5">
                <div className="mb-4">
                  <div className="text-primary fw-semibold text-uppercase small">Créer un compte</div>
                  <h1 className="h3 mt-2 mb-2">Rejoignez la plateforme IT</h1>
                  <p className="text-body-secondary mb-0">Créez votre accès pour gérer vos incidents efficacement.</p>
                </div>

                {error && <CAlert color="danger">{error}</CAlert>}

                <CForm onSubmit={handleSubmit}>
                  <CInputGroup className="mb-3">
                    <CInputGroupText>
                      <CIcon icon={cilUser} />
                    </CInputGroupText>
                    <CFormInput name="username" placeholder="Nom d’utilisateur" autoComplete="username" value={form.username} onChange={handleChange} required />
                  </CInputGroup>
                  <CInputGroup className="mb-3">
                    <CInputGroupText>@</CInputGroupText>
                    <CFormInput name="email" type="email" placeholder="Adresse e-mail" autoComplete="email" value={form.email} onChange={handleChange} required />
                  </CInputGroup>
                  <CInputGroup className="mb-3">
                    <CInputGroupText>
                      <CIcon icon={cilLockLocked} />
                    </CInputGroupText>
                    <CFormInput name="password" type="password" placeholder="Mot de passe" autoComplete="new-password" value={form.password} onChange={handleChange} required />
                  </CInputGroup>
                  <CFormSelect name="role" className="mb-4" value={form.role} onChange={handleChange}>
                    <option value="USER">Utilisateur</option>
                    <option value="TECHNICIAN">Technicien</option>
                    <option value="ADMIN">Administrateur</option>
                  </CFormSelect>
                  <div className="d-grid gap-2">
                    <CButton color="primary" type="submit" disabled={loading}>
                      {loading ? 'Création...' : 'Créer mon compte'}
                    </CButton>
                    <Link to="/login" className="text-decoration-none">
                      <CButton color="secondary" variant="outline" className="w-100">
                        Retour à la connexion
                      </CButton>
                    </Link>
                  </div>
                </CForm>
              </CCardBody>
            </CCard>
          </CCol>
        </CRow>
      </CContainer>
    </div>
  )
}

export default Register
