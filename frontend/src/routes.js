/**
 * Application Routes Configuration
 *
 * Defines the main routes for the IT Ticket Management platform.
 *
 * Each route object contains:
 * - path: URL path for the route
 * - name: Human-readable name for breadcrumbs
 * - element: Lazy-loaded React component
 *
 * @module routes
 */

import React from 'react'

const Dashboard = React.lazy(() => import('./views/dashboard/Dashboard'))
const Tickets = React.lazy(() => import('./views/tickets/Tickets'))
const NewTicket = React.lazy(() => import('./views/tickets/NewTicket'))
const TicketDetails = React.lazy(() => import('./views/tickets/TicketDetails'))
const Users = React.lazy(() => import('./views/users/Users'))
const Notifications = React.lazy(() => import('./views/notifications/Notifications'))
const Profile = React.lazy(() => import('./views/profile/Profile'))

export const routes = [
  { path: '/dashboard', name: 'Dashboard', element: Dashboard },
  { path: '/tickets', name: 'Tickets', element: Tickets, exact: true },
  { path: '/tickets/create', name: 'Nouveau ticket', element: NewTicket },
  { path: '/tickets/:id', name: 'Détails ticket', element: TicketDetails },
  { path: '/users', name: 'Utilisateurs', element: Users },
  { path: '/notifications', name: 'Notifications', element: Notifications },
  { path: '/profile', name: 'Profil', element: Profile },
]

export default routes
