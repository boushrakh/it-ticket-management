import React from 'react'
import { CCard, CCardBody, CCardHeader } from '@coreui/react'

const Users = () => (
  <CCard className="mb-4">
    <CCardHeader>Utilisateurs</CCardHeader>
    <CCardBody>
      Liste des utilisateurs et techniciens. Cette page pourra gérer les rôles, les équipes
      et les contacts du support.
    </CCardBody>
  </CCard>
)

export default Users
