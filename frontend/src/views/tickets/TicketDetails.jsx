import React, { useEffect, useState } from 'react'
import { useNavigate, useParams } from 'react-router-dom'
import {
  CBadge,
  CButton,
  CCard,
  CCardBody,
  CCardHeader,
  CCol,
  CListGroup,
  CListGroupItem,
  CRow,
  CSpinner,
} from '@coreui/react'

import { getTicket } from '../../services/api'

const TicketDetails = () => {
  const navigate = useNavigate()
  const { id } = useParams()
  const [ticket, setTicket] = useState(null)
  const [loading, setLoading] = useState(true)

  useEffect(() => {
    const loadTicket = async () => {
      try {
        const data = await getTicket(id)
        setTicket(data)
      } catch {
        setTicket(null)
      } finally {
        setLoading(false)
      }
    }

    loadTicket()
  }, [id])

  if (loading) {
    return (
      <CCard className="mb-4 shadow-sm border-0">
        <CCardBody className="text-center py-5">
          <CSpinner color="primary" />
        </CCardBody>
      </CCard>
    )
  }

  if (!ticket) {
    return (
      <CCard className="mb-4 shadow-sm border-0">
        <CCardHeader>Ticket introuvable</CCardHeader>
        <CCardBody>
          Ce ticket n’existe pas ou a été supprimé.
          <div className="mt-3">
            <CButton color="primary" onClick={() => navigate('/tickets')}>
              Retour à la liste
            </CButton>
          </div>
        </CCardBody>
      </CCard>
    )
  }

  return (
    <CCard className="mb-4 shadow-sm border-0">
      <CCardHeader>Détails du ticket #{ticket.id}</CCardHeader>
      <CCardBody>
        <CRow className="g-3">
          <CCol md={6}>
            <CListGroup flush>
              <CListGroupItem><strong>Sujet :</strong> {ticket.title}</CListGroupItem>
              <CListGroupItem><strong>Demandeur :</strong> {ticket.requester}</CListGroupItem>
              <CListGroupItem><strong>Assigné à :</strong> {ticket.assignee || '—'}</CListGroupItem>
            </CListGroup>
          </CCol>
          <CCol md={6}>
            <CListGroup flush>
              <CListGroupItem>
                <strong>Statut :</strong> <CBadge color="primary">{ticket.status}</CBadge>
              </CListGroupItem>
              <CListGroupItem>
                <strong>Priorité :</strong> <CBadge color="warning">{ticket.priority}</CBadge>
              </CListGroupItem>
              <CListGroupItem><strong>Créé le :</strong> {new Date(ticket.createdAt).toLocaleDateString('fr-FR')}</CListGroupItem>
            </CListGroup>
          </CCol>
        </CRow>

        <div className="mt-4">
          <h6>Description</h6>
          <p className="mb-0">{ticket.description}</p>
        </div>

        <div className="mt-4 d-flex gap-2">
          <CButton color="primary" onClick={() => navigate('/tickets')}>
            Retour à la liste
          </CButton>
          <CButton color="warning" variant="outline" onClick={() => navigate('/tickets/create', { state: { ticket } })}>
            Modifier
          </CButton>
        </div>
      </CCardBody>
    </CCard>
  )
}

export default TicketDetails
