import React from 'react';
import './App.css';
import { BrowserRouter as Router, Route, Routes, Link } from 'react-router-dom';
import Collapsible from './components/Collapsible';
import Appbar from './components/Appbar';
import HomePage from './pages/HomePage';
import TeamPage from './pages/TeamPage';
import TimetablePage from './pages/TimetablePage';

const App = () => {
  return (
    <Router>
      <div>
        <Appbar/>
        <Collapsible trigger={"Toggle navigation"}>
          <nav>
            <ul>
              <li>
                <Link to="/">Home</Link>
              </li>
              <li>
                <Link to="/TeamPage">Team</Link>
              </li>
              <li>
                <Link to="/TimetablePage">Timetable</Link>
              </li>
            </ul>
          </nav>
        </Collapsible> 
        <Routes>
          <Route path="/" element={<HomePage/>} />
          <Route path="/TeamPage" element={<TeamPage/>} />
          <Route path="/TimetablePage" element={<TimetablePage/>} />
        </Routes>        
      </div>  
    </Router>
  );
}

export default App;
