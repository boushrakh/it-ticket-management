import React, { useMemo, useState } from 'react'
import { useNavigate } from 'react-router-dom'
import {
  CBadge,
  CButton,
  CButtonGroup,
  CCard,
  CCardBody,
  CCardHeader,
  CTable,
  CTableBody,
  CTableDataCell,
  CTableHead,
  CTableHeaderCell,
  CTableRow,
} from '@coreui/react'

const initialTickets = [
  {
    id: 1001,
    title: 'Erreur d’accès au portail RH',
    requester: 'Sofia Benali',
    status: 'Ouvert',
    priority: 'Haute',
    assignee: 'Youssef Idrissi',
    createdAt: '2026-07-28',
  },
  {
    id: 1002,
    title: 'Imprimante réseau hors ligne',
    requester: 'Karim Rami',
    status: 'En cours',
    priority: 'Moyenne',
    assignee: 'Nadia El Yacoubi',
    createdAt: '2026-07-29',
  },
  {
    id: 1003,
    title: 'Demande de réinitialisation VPN',
    requester: 'Lina Chraibi',
    status: 'Résolu',
    priority: 'Basse',
    assignee: 'Hassan Mounir',
    createdAt: '2026-07-30',
  },
]

const statusVariant = {
  Ouvert: 'primary',
  'En cours': 'warning',
  Résolu: 'success',
  Fermé: 'secondary',
}

const priorityVariant = {
  Basse: 'success',
  Moyenne: 'warning',
  Haute: 'danger',
  Critique: 'dark',
}

const Tickets = () => {
  const navigate = useNavigate()
  const [tickets, setTickets] = useState(initialTickets)

  const ticketCount = useMemo(() => tickets.length, [tickets])

  const handleDelete = (ticketId) => {
    setTickets((current) => current.filter((ticket) => ticket.id !== ticketId))
  }

  const handleEdit = (ticket) => {
    navigate('/tickets/create', { state: { ticket } })
  }

  const handleDetails = (ticketId) => {
    navigate(`/tickets/${ticketId}`)
  }

  return (
    <CCard className="mb-4">
      <CCardHeader className="d-flex justify-content-between align-items-center">
        <span>Tickets</span>
        <CButton color="primary" onClick={() => navigate('/tickets/create')}>
          Créer un ticket
        </CButton>
      </CCardHeader>
      <CCardBody>
        <div className="d-flex justify-content-between align-items-center mb-3">
          <strong>{ticketCount} ticket(s) affiché(s)</strong>
        </div>

        <CTable align="middle" hover responsive>
          <CTableHead>
            <CTableRow>
              <CTableHeaderCell>#</CTableHeaderCell>
              <CTableHeaderCell>Sujet</CTableHeaderCell>
              <CTableHeaderCell>Demandeur</CTableHeaderCell>
              <CTableHeaderCell>Statut</CTableHeaderCell>
              <CTableHeaderCell>Priorité</CTableHeaderCell>
              <CTableHeaderCell>Assigné à</CTableHeaderCell>
              <CTableHeaderCell>Créé le</CTableHeaderCell>
              <CTableHeaderCell>Actions</CTableHeaderCell>
            </CTableRow>
          </CTableHead>
          <CTableBody>
            {tickets.map((ticket) => (
              <CTableRow key={ticket.id}>
                <CTableDataCell>{ticket.id}</CTableDataCell>
                <CTableDataCell>{ticket.title}</CTableDataCell>
                <CTableDataCell>{ticket.requester}</CTableDataCell>
                <CTableDataCell>
                  <CBadge color={statusVariant[ticket.status] || 'secondary'}>{ticket.status}</CBadge>
                </CTableDataCell>
                <CTableDataCell>
                  <CBadge color={priorityVariant[ticket.priority] || 'secondary'}>
                    {ticket.priority}
                  </CBadge>
                </CTableDataCell>
                <CTableDataCell>{ticket.assignee}</CTableDataCell>
                <CTableDataCell>{ticket.createdAt}</CTableDataCell>
                <CTableDataCell>
                  <CButtonGroup size="sm">
                    <CButton color="info" variant="outline" onClick={() => handleDetails(ticket.id)}>
                      Détails
                    </CButton>
                    <CButton color="warning" variant="outline" onClick={() => handleEdit(ticket)}>
                      Modifier
                    </CButton>
                    <CButton color="danger" variant="outline" onClick={() => handleDelete(ticket.id)}>
                      Supprimer
                    </CButton>
                  </CButtonGroup>
                </CTableDataCell>
              </CTableRow>
            ))}
          </CTableBody>
        </CTable>
      </CCardBody>
    </CCard>
  )
}

export default Tickets
