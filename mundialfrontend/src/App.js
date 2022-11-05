import './App.css';
import AppBar from './components/Appbar';
import Team from './components/Team';
import Coll from './components/Coll';
import Timetable from './components/Timetable';


function App() {
  return (
    <div className="App">
    <AppBar/>  
    <Team/>
    <Timetable/>
    </div>
  );
}

export default App;
