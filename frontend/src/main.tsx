import { StrictMode } from 'react';
import { createRoot } from 'react-dom/client';
import { createBrowserRouter, RouterProvider } from 'react-router-dom';
import LoginPage from './pages/LoginPage'
import SignUpPage from './pages/SignUpPage'
import HomePage from './pages/HomePage';
import GameDetailPage from "./pages/GameDetailPage.tsx";
import AllGames from './pages/AllGames.tsx';
// import './index.css'
// import App from './App.tsx'

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
    path: '/game',
    element: <GameDetailPage />,
    errorElement: <LoginPage />
  },
  {
    path: '/allGames',
    element: <AllGames />,
    errorElement: <LoginPage />
  }
]);

createRoot(document.getElementById('root')!).render(
  <StrictMode>
    {/* <App /> */}
    <RouterProvider router={router} />
  </StrictMode>,
)
