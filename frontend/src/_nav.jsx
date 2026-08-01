/**
 * Sidebar Navigation Configuration
 *
 * Defines the structure and content of the sidebar navigation menu.
 * Supports multiple navigation component types from CoreUI React:
 * - CNavItem: Single navigation link
 * - CNavGroup: Collapsible group of links
 * - CNavTitle: Section title/divider
 *
 * @module _nav
 */

import React from 'react'
import CIcon from '@coreui/icons-react'
import { cilBell, cilNotes, cilPencil, cilSpeedometer, cilUser } from '@coreui/icons'
import { CNavItem, CNavTitle } from '@coreui/react'

const _nav = [
  {
    component: CNavTitle,
    name: 'IT Ticket Management',
  },
  {
    component: CNavItem,
    name: 'Dashboard',
    to: '/dashboard',
    icon: <CIcon icon={cilSpeedometer} customClassName="nav-icon" />,
  },
  {
    component: CNavItem,
    name: 'Tickets',
    to: '/tickets',
    icon: <CIcon icon={cilNotes} customClassName="nav-icon" />,
  },
  {
    component: CNavItem,
    name: 'Créer un ticket',
    to: '/tickets/create',
    icon: <CIcon icon={cilPencil} customClassName="nav-icon" />,
  },
  {
    component: CNavItem,
    name: 'Utilisateurs',
    to: '/users',
    icon: <CIcon icon={cilUser} customClassName="nav-icon" />,
  },
  {
    component: CNavItem,
    name: 'Notifications',
    to: '/notifications',
    icon: <CIcon icon={cilBell} customClassName="nav-icon" />,
  },
  {
    component: CNavItem,
    name: 'Profil',
    to: '/profile',
    icon: <CIcon icon={cilUser} customClassName="nav-icon" />,
  },
]

export default _nav
