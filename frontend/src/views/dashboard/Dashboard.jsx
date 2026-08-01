import React, { useEffect, useState } from 'react'
import { CCard, CCardBody, CCol, CRow, CSpinner } from '@coreui/react'
import CIcon from '@coreui/icons-react'
import { cilCheckCircle, cilFolderOpen, cilList, cilWarning } from '@coreui/icons'

import { getTicketStatistics } from '../../services/api'

const Dashboard = () => {
  const [stats, setStats] = useState(null)
  const [loading, setLoading] = useState(true)

  useEffect(() => {
    const loadStats = async () => {
      try {
        const data = await getTicketStatistics()
        setStats(data)
      } catch {
        setStats({ total: 0, open: 0, inProgress: 0, resolved: 0, critical: 0 })
      } finally {
        setLoading(false)
      }
    }

    loadStats()
  }, [])

  const cards = [
    { label: 'Tickets total', value: stats?.total ?? 0, color: 'info', icon: cilList, description: 'Vue globale du parc IT' },
    { label: 'Ouverts', value: stats?.open ?? 0, color: 'warning', icon: cilFolderOpen, description: 'En attente de traitement' },
    { label: 'Résolus', value: stats?.resolved ?? 0, color: 'success', icon: cilCheckCircle, description: 'Incidents clôturés' },
    { label: 'Critiques', value: stats?.critical ?? 0, color: 'danger', icon: cilWarning, description: 'Priorité élevée' },
  ]

  return (
    <>
      <CCard className="mb-4 border-0 shadow-sm">
        <CCardBody>
          <div className="d-flex justify-content-between align-items-center">
            <div>
              <div className="text-primary fw-semibold text-uppercase small">Bienvenue</div>
              <h2 className="h4 mb-1">Suivi IT en temps réel</h2>
              <p className="text-body-secondary mb-0">Votre console centralisée pour traiter les incidents et les tickets.</p>
            </div>
          </div>
        </CCardBody>
      </CCard>

      {loading ? (
        <div className="text-center py-5">
          <CSpinner color="primary" />
        </div>
      ) : (
        <CRow className="g-4">
          {cards.map((stat) => (
            <CCol key={stat.label} xs={12} sm={6} xl={3}>
              <CCard className={`border-start border-start-4 border-start-${stat.color} h-100 shadow-sm`}>
                <CCardBody>
                  <div className="d-flex align-items-center justify-content-between mb-3">
                    <div>
                      <div className="text-medium-emphasis text-uppercase fw-semibold small">{stat.label}</div>
                      <div className="fs-2 fw-bold">{stat.value}</div>
                    </div>
                    <div className={`bg-${stat.color} bg-opacity-10 rounded-circle p-3`}>
                      <CIcon icon={stat.icon} size="xl" className={`text-${stat.color}`} />
                    </div>
                  </div>
                  <div className="text-medium-emphasis small">{stat.description}</div>
                </CCardBody>
              </CCard>
            </CCol>
          ))}
        </CRow>
      )}
    </>
  )
}

export default Dashboard
