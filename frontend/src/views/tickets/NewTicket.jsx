import React, { useState } from 'react'
import { useLocation, useNavigate } from 'react-router-dom'
import {
  CButton,
  CCard,
  CCardBody,
  CCardHeader,
  CCol,
  CForm,
  CFormInput,
  CFormSelect,
  CFormTextarea,
  CRow,
} from '@coreui/react'

const NewTicket = () => {
  const navigate = useNavigate()
  const location = useLocation()
  const ticket = location.state?.ticket

  const [formData, setFormData] = useState({
    title: ticket?.title ?? '',
    requester: ticket?.requester ?? '',
    priority: ticket?.priority ?? 'Moyenne',
    status: ticket?.status ?? 'Ouvert',
    assignee: ticket?.assignee ?? '',
    description: ticket?.description ?? '',
  })

  const handleChange = (event) => {
    const { name, value } = event.target
    setFormData((current) => ({ ...current, [name]: value }))
  }

  const handleSubmit = (event) => {
    event.preventDefault()
    navigate('/tickets')
  }

  return (
    <CCard className="mb-4">
      <CCardHeader>{ticket ? 'Modifier le ticket' : 'Nouveau ticket'}</CCardHeader>
      <CCardBody>
        <CForm onSubmit={handleSubmit}>
          <CRow className="g-3">
            <CCol md={6}>
              <CFormInput
                name="title"
                label="Sujet"
                value={formData.title}
                onChange={handleChange}
                required
              />
            </CCol>
            <CCol md={6}>
              <CFormInput
                name="requester"
                label="Demandeur"
                value={formData.requester}
                onChange={handleChange}
                required
              />
            </CCol>
            <CCol md={4}>
              <CFormSelect
                name="priority"
                label="Priorité"
                value={formData.priority}
                onChange={handleChange}
              >
                <option value="Basse">Basse</option>
                <option value="Moyenne">Moyenne</option>
                <option value="Haute">Haute</option>
                <option value="Critique">Critique</option>
              </CFormSelect>
            </CCol>
            <CCol md={4}>
              <CFormSelect
                name="status"
                label="Statut"
                value={formData.status}
                onChange={handleChange}
              >
                <option value="Ouvert">Ouvert</option>
                <option value="En cours">En cours</option>
                <option value="Résolu">Résolu</option>
                <option value="Fermé">Fermé</option>
              </CFormSelect>
            </CCol>
            <CCol md={4}>
              <CFormInput
                name="assignee"
                label="Assigné à"
                value={formData.assignee}
                onChange={handleChange}
              />
            </CCol>
            <CCol xs={12}>
              <CFormTextarea
                name="description"
                label="Description"
                rows={5}
                value={formData.description}
                onChange={handleChange}
              />
            </CCol>
          </CRow>

          <div className="d-flex gap-2 mt-4">
            <CButton type="submit" color="primary">
              {ticket ? 'Enregistrer les modifications' : 'Créer le ticket'}
            </CButton>
            <CButton type="button" color="secondary" variant="outline" onClick={() => navigate('/tickets')}>
              Annuler
            </CButton>
          </div>
        </CForm>
      </CCardBody>
    </CCard>
  )
}

export default NewTicket
