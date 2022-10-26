import './App.css';
import AppBar from './components/Appbar';
import Team from './components/Team';
import Coll from './components/Coll';


function App() {
  return (
    <div className="App">
    <AppBar/>  
    <Coll/>
    <Team/>
    </div>
  );
}

export default App;
