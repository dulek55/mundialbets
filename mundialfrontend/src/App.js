import React, {useState} from 'react';
import './App.css';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Appbar from './components/Appbar';
import HomePage from './pages/HomePage';
import TeamPage from './pages/TeamPage';
import TimetablePage from './pages/TimetablePage';
import RegisterPage from './pages/RegisterPage';
import LoginPage from './pages/LoginPage';
import LeaguePage from './pages/LeaguePage';
import LeagueDetailPage from './pages/LeagueDetailPage';
import BetsPage from './pages/BetsPage';

const App = () => {
  // Zakładamy, że id zalogowanego użytkownika jest 1. W rzeczywistości będzie pochodziło np. z mechanizmu logowania.
  const [currentUserId] = useState(1);
  return (
    <Router>
      <div>
      <Appbar title="MundialApp" />
      <Routes>
        <Route exact path="/" element={<HomePage/>} />
        <Route path="/TeamPage" element={<TeamPage/>} />
        <Route path="/TimetablePage" element={<TimetablePage/>} />
        <Route path="/login" element={<LoginPage />} />
        <Route path="/register" element={<RegisterPage />} />
        <Route path="/leagues" element={<LeaguePage />} />
        <Route path="/leagues/:id/:name" element={<LeagueDetailPage />} />
        <Route path="/bets/:userId" element={<BetsPage currentUserId={currentUserId} />} />
      </Routes>        
      </div>  
    </Router>
  );
}

export default App;