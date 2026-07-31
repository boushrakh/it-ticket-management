import React, { useEffect, useMemo, useState } from 'react'
import { useNavigate } from 'react-router-dom'
import {
  CBadge,
  CButton,
  CButtonGroup,
  CCard,
  CCardBody,
  CCardHeader,
  CCol,
  CFormInput,
  CFormSelect,
  CRow,
  CSpinner,
  CTable,
  CTableBody,
  CTableDataCell,
  CTableHead,
  CTableHeaderCell,
  CTableRow,
} from '@coreui/react'

import { deleteTicket, listTickets } from '../../services/api'

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
  const [tickets, setTickets] = useState([])
  const [loading, setLoading] = useState(true)
  const [search, setSearch] = useState('')
  const [statusFilter, setStatusFilter] = useState('all')
  const [priorityFilter, setPriorityFilter] = useState('all')

  useEffect(() => {
    const loadTickets = async () => {
      try {
        const data = await listTickets()
        setTickets(data)
      } catch {
        setTickets([])
      } finally {
        setLoading(false)
      }
    }

    loadTickets()
  }, [])

  const filteredTickets = useMemo(() => {
    return tickets.filter((ticket) => {
      const matchesSearch = [ticket.title, ticket.requester, ticket.assignee, ticket.description]
        .join(' ')
        .toLowerCase()
        .includes(search.toLowerCase())
      const matchesStatus = statusFilter === 'all' || ticket.status === statusFilter
      const matchesPriority = priorityFilter === 'all' || ticket.priority === priorityFilter
      return matchesSearch && matchesStatus && matchesPriority
    })
  }, [tickets, search, statusFilter, priorityFilter])

  const handleDelete = async (ticketId) => {
    if (!window.confirm('Supprimer ce ticket ?')) {
      return
    }

    try {
      await deleteTicket(ticketId)
      setTickets((current) => current.filter((ticket) => ticket.id !== ticketId))
    } catch (error) {
      console.error(error)
    }
  }

  const handleEdit = (ticket) => {
    navigate('/tickets/create', { state: { ticket } })
  }

  const handleDetails = (ticketId) => {
    navigate(`/tickets/${ticketId}`)
  }

  const formatDate = (value) => {
    if (!value) return '—'
    const date = new Date(value)
    return Number.isNaN(date.getTime()) ? value : date.toLocaleDateString('fr-FR')
  }

  return (
    <CCard className="mb-4 shadow-sm border-0">
      <CCardHeader className="d-flex justify-content-between align-items-center">
        <div>
          <h2 className="h5 mb-0">Gestion des tickets</h2>
          <div className="text-body-secondary small">Recherche, filtres et actions rapides</div>
        </div>
        <CButton color="primary" onClick={() => navigate('/tickets/create')}>
          Créer un ticket
        </CButton>
      </CCardHeader>
      <CCardBody>
        <CRow className="g-3 mb-4">
          <CCol md={5}>
            <CFormInput placeholder="Rechercher un ticket" value={search} onChange={(event) => setSearch(event.target.value)} />
          </CCol>
          <CCol md={3}>
            <CFormSelect value={statusFilter} onChange={(event) => setStatusFilter(event.target.value)}>
              <option value="all">Tous les statuts</option>
              <option value="Ouvert">Ouvert</option>
              <option value="En cours">En cours</option>
              <option value="Résolu">Résolu</option>
              <option value="Fermé">Fermé</option>
            </CFormSelect>
          </CCol>
          <CCol md={3}>
            <CFormSelect value={priorityFilter} onChange={(event) => setPriorityFilter(event.target.value)}>
              <option value="all">Toutes les priorités</option>
              <option value="Basse">Basse</option>
              <option value="Moyenne">Moyenne</option>
              <option value="Haute">Haute</option>
              <option value="Critique">Critique</option>
            </CFormSelect>
          </CCol>
        </CRow>

        <div className="d-flex justify-content-between align-items-center mb-3">
          <strong>{filteredTickets.length} ticket(s) affiché(s)</strong>
        </div>

        {loading ? (
          <div className="text-center py-5">
            <CSpinner color="primary" />
          </div>
        ) : (
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
              {filteredTickets.map((ticket) => (
                <CTableRow key={ticket.id}>
                  <CTableDataCell>{ticket.id}</CTableDataCell>
                  <CTableDataCell>{ticket.title}</CTableDataCell>
                  <CTableDataCell>{ticket.requester}</CTableDataCell>
                  <CTableDataCell>
                    <CBadge color={statusVariant[ticket.status] || 'secondary'}>{ticket.status}</CBadge>
                  </CTableDataCell>
                  <CTableDataCell>
                    <CBadge color={priorityVariant[ticket.priority] || 'secondary'}>{ticket.priority}</CBadge>
                  </CTableDataCell>
                  <CTableDataCell>{ticket.assignee || '—'}</CTableDataCell>
                  <CTableDataCell>{formatDate(ticket.createdAt)}</CTableDataCell>
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
        )}
      </CCardBody>
    </CCard>
  )
}

export default Tickets
