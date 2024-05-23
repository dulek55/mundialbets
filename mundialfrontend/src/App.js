import React from 'react';
import './App.css';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Appbar from './components/Appbar';
import HomePage from './pages/HomePage';
import TeamPage from './pages/TeamPage';
import TimetablePage from './pages/TimetablePage';

const App = () => {
  return (
    <Router>
      <div>
      <Appbar title="MundialApp" />
      <Routes>
        <Route exact path="/" element={<HomePage/>} />
        <Route path="/TeamPage" element={<TeamPage/>} />
        <Route path="/TimetablePage" element={<TimetablePage/>} />
      </Routes>        
      </div>  
    </Router>
  );
}

export default App;