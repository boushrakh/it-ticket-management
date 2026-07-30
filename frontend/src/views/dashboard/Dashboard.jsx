import React from 'react'
import { CCard, CCardBody, CCol, CRow } from '@coreui/react'
import CIcon from '@coreui/icons-react'
import { cilList, cilFolderOpen, cilCheckCircle, cilWarning } from '@coreui/icons'

const stats = [
  {
    label: 'Nombre total des tickets',
    value: 124,
    color: 'info',
    icon: cilList,
    description: 'Vue globale des tickets',
  },
  {
    label: 'Tickets ouverts',
    value: 35,
    color: 'info',
    icon: cilFolderOpen,
    description: 'Tickets en attente de traitement',
  },
  
  {
    label: 'Tickets résolus',
    value: 40,
    color: 'success',
    icon: cilCheckCircle,
    description: 'Tickets fermés avec succès',
  },
  {
    label: 'Tickets critiques',
    value: 7,
    color: 'danger',
    icon: cilWarning,
    description: 'Tickets prioritaires à traiter',
  },
]

const Dashboard = () => {
  return (
    <>
      <CRow className="g-4">
        {stats.map((stat) => (
          <CCol key={stat.label} xs={12} sm={6} xl={4} xxl={2}>
            <CCard className={`border-start border-start-4 border-start-${stat.color} h-100`}>
              <CCardBody>
                <div className="d-flex align-items-center justify-content-between mb-3">
                  <div>
                    <div className="text-medium-emphasis text-uppercase fw-semibold small">
                      {stat.label}
                    </div>
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
    </>
  )
}

export default Dashboard
