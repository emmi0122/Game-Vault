import { StrictMode } from 'react';
import { createRoot } from 'react-dom/client';
import { createBrowserRouter, RouterProvider } from 'react-router-dom';
import LoginPage from './pages/LoginPage'
import SignUpPage from './pages/SignUpPage'
import HomePage from './pages/HomePage';
import GameDetailPage from "./pages/GameDetailPage.tsx";
import './index.css'

const router = createBrowserRouter([
  {
    path: '/',
    element: <HomePage />,
    errorElement: <LoginPage />
  },
  {
    path: '/create',
    element: <SignUpPage />,
    errorElement: <LoginPage />
  }, 
  {
    path: '/login',
    element: <LoginPage />,
    errorElement: <LoginPage />
  },
  {
    path: '/game/:id',
    element: <GameDetailPage />,
    errorElement: <LoginPage />
  }
]);

createRoot(document.getElementById('root')!).render(
  <StrictMode>
    {/* <App /> */}
    <RouterProvider router={router} />
  </StrictMode>,
)
