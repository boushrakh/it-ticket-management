import React from 'react'
import { CCard, CCardBody, CCardHeader } from '@coreui/react'

const NewTicket = () => (
  <CCard className="mb-4">
    <CCardHeader>Nouveau ticket</CCardHeader>
    <CCardBody>
      Formulaire de création de ticket pour signaler un incident ou une demande de support.
    </CCardBody>
  </CCard>
)

export default NewTicket
