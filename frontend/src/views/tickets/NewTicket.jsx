import React, { useEffect, useState } from 'react'
import { useLocation, useNavigate } from 'react-router-dom'
import {
  CAlert,
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
  CSpinner,
} from '@coreui/react'

import { analyzeTicket, createTicket, updateTicket } from '../../services/api'

const NewTicket = () => {
  const navigate = useNavigate()
  const location = useLocation()
  const ticket = location.state?.ticket || null

  const [formData, setFormData] = useState({
    title: ticket?.title || '',
    requester: ticket?.requester || '',
    priority: ticket?.priority || 'Moyenne',
    status: ticket?.status || 'Ouvert',
    assignee: ticket?.assignee || '',
    description: ticket?.description || '',
    category: ticket?.category || '',
  })
  const [loading, setLoading] = useState(false)
  const [aiHint, setAiHint] = useState('')

  useEffect(() => {
    if (ticket) {
      setFormData((current) => ({ ...current, ...ticket }))
    }
  }, [ticket])

  const handleChange = (event) => {
    const { name, value } = event.target
    setFormData((current) => ({ ...current, [name]: value }))
  }

  const handleAnalyzeDescription = async () => {
    if (!formData.description || formData.description.trim().length < 5) {
      return
    }

    setLoading(true)
    try {
      const result = await analyzeTicket(formData.description)
      setAiHint(`Analyse IA : priorité ${result.priority} • catégorie ${result.category}`)
      setFormData((current) => ({ ...current, category: result.category, priority: result.priority }))
    } catch (error) {
      setAiHint('Analyse IA indisponible pour le moment.')
    } finally {
      setLoading(false)
    }
  }

  const handleSubmit = async (event) => {
    event.preventDefault()

    try {
      if (ticket?.id) {
        await updateTicket(ticket.id, formData)
      } else {
        await createTicket(formData)
      }
      navigate('/tickets')
    } catch (error) {
      console.error(error)
    }
  }

  return (
    <CCard className="mb-4 shadow-sm border-0">
      <CCardHeader>
        <h2 className="h5 mb-0">{ticket ? 'Modifier le ticket' : 'Nouveau ticket'}</h2>
      </CCardHeader>

      <CCardBody>
        {aiHint && <CAlert color="info" className="mb-4">{aiHint}</CAlert>}

        <CForm onSubmit={handleSubmit}>
          <CRow className="g-3">
            <CCol md={6}>
              <CFormInput name="title" label="Sujet" value={formData.title} onChange={handleChange} required />
            </CCol>

            <CCol md={6}>
              <CFormInput name="requester" label="Demandeur" value={formData.requester} onChange={handleChange} required />
            </CCol>

            <CCol md={4}>
              <CFormSelect name="priority" label="Priorité" value={formData.priority} onChange={handleChange}>
                <option value="Basse">Basse</option>
                <option value="Moyenne">Moyenne</option>
                <option value="Haute">Haute</option>
                <option value="Critique">Critique</option>
              </CFormSelect>
            </CCol>

            <CCol md={4}>
              <CFormSelect name="status" label="Statut" value={formData.status} onChange={handleChange}>
                <option value="Ouvert">Ouvert</option>
                <option value="En cours">En cours</option>
                <option value="Résolu">Résolu</option>
                <option value="Fermé">Fermé</option>
              </CFormSelect>
            </CCol>

            <CCol md={4}>
              <CFormInput name="assignee" label="Assigné à" value={formData.assignee} onChange={handleChange} />
            </CCol>

            <CCol xs={12}>
              <CFormTextarea name="description" label="Description" rows={5} value={formData.description} onChange={handleChange} />
            </CCol>
          </CRow>

          <div className="d-flex gap-2 mt-4 flex-wrap">
            <CButton type="button" color="info" variant="outline" onClick={handleAnalyzeDescription} disabled={loading}>
              {loading ? <><CSpinner size="sm" className="me-2" />Analyse IA...</> : 'Analyser avec l’IA'}
            </CButton>
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