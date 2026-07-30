import React from 'react'
import { useParams } from 'react-router-dom'
import { CCard, CCardBody, CCardHeader } from '@coreui/react'

const TicketDetails = () => {
  const { id } = useParams()

  return (
    <CCard className="mb-4">
      <CCardHeader>Détails du ticket {id}</CCardHeader>
      <CCardBody>
        Informations détaillées du ticket {id}. Cette page pourra contenir l’historique,
        les commentaires, le statut et les actions de l’incident.
      </CCardBody>
    </CCard>
  )
}

export default TicketDetails
