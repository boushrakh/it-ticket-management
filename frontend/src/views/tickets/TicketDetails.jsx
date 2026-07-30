import React from 'react'
import { useNavigate, useParams } from 'react-router-dom'
import {
  CButton,
  CCard,
  CCardBody,
  CCardHeader,
  CCol,
  CRow,
  CListGroup,
  CListGroupItem,
} from '@coreui/react'

const ticketDetails = {
  1001: {
    id: 1001,
    title: 'Erreur d’accès au portail RH',
    requester: 'Sofia Benali',
    status: 'Ouvert',
    priority: 'Haute',
    assignee: 'Youssef Idrissi',
    createdAt: '2026-07-28',
    description: 'Le portail RH ne répond plus pour l’ensemble des utilisateurs de la filiale.',
  },
  1002: {
    id: 1002,
    title: 'Imprimante réseau hors ligne',
    requester: 'Karim Rami',
    status: 'En cours',
    priority: 'Moyenne',
    assignee: 'Nadia El Yacoubi',
    createdAt: '2026-07-29',
    description: 'L’imprimante du service comptabilité ne reçoit plus les jobs depuis hier.',
  },
  1003: {
    id: 1003,
    title: 'Demande de réinitialisation VPN',
    requester: 'Lina Chraibi',
    status: 'Résolu',
    priority: 'Basse',
    assignee: 'Hassan Mounir',
    createdAt: '2026-07-30',
    description: 'Le compte VPN a été réinitialisé et les accès ont été restitués.',
  },
}

const TicketDetails = () => {
  const navigate = useNavigate()
  const { id } = useParams()
  const ticket = ticketDetails[id]

  if (!ticket) {
    return (
      <CCard className="mb-4">
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
    <CCard className="mb-4">
      <CCardHeader>Détails du ticket #{ticket.id}</CCardHeader>
      <CCardBody>
        <CRow className="g-3">
          <CCol md={6}>
            <CListGroup flush>
              <CListGroupItem><strong>Sujet :</strong> {ticket.title}</CListGroupItem>
              <CListGroupItem><strong>Demandeur :</strong> {ticket.requester}</CListGroupItem>
              <CListGroupItem><strong>Assigné à :</strong> {ticket.assignee}</CListGroupItem>
            </CListGroup>
          </CCol>
          <CCol md={6}>
            <CListGroup flush>
              <CListGroupItem><strong>Statut :</strong> {ticket.status}</CListGroupItem>
              <CListGroupItem><strong>Priorité :</strong> {ticket.priority}</CListGroupItem>
              <CListGroupItem><strong>Créé le :</strong> {ticket.createdAt}</CListGroupItem>
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
