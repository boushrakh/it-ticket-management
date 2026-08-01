import React from 'react'
import { CCard, CCardBody, CCardHeader } from '@coreui/react'

const Profile = () => (
  <CCard className="mb-4">
    <CCardHeader>Profil</CCardHeader>
    <CCardBody>
      Informations du profil utilisateur avec les paramètres de compte, le rôle et les préférences.
    </CCardBody>
  </CCard>
)

export default Profile
