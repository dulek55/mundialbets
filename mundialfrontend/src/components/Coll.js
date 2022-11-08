import React from 'react';
import Collapsible from 'react-collapsible';
import { Button } from '@mui/material';

export default function Coll() {
  const[teams, setTeams] = React.useState([])
  const[games, setGames] = React.useState([])

  // React.useEffect(()=>{
  //   fetch("http://localhost:8080/api/games")
  //   .then(res=>res.json())
  //   .then((result)=>{
  //       setGames(result);
  //   }
  // )
  // },[])

  const getTeams=(e)=>{
    fetch('http://localhost:8080/api/team')
        .then(res=>res.json())
        .then((result)=>{
            setTeams(result);
        });
  }
  const getGames=(e)=>{
    fetch('http://localhost:8080/api/games')
        .then(res=>res.json())
        .then((result)=>{
            setGames(result);
        });
  }

  const CollsTeam = ({ team }) => {
    const [open, setOpen] = React.useState(false);
    return (
      <div onClick={() => setOpen(!open)} key={team.id}>
        <Button color="success" variant="contained">Show team</Button>
        <Collapsible open={open}>
          <div>{team.countryName}</div>
          <div>{team.countryCode}</div>
        </Collapsible>
      </div>
    );
  };
  const CollsGame = ({ game }) => {
    const [open, setOpen] = React.useState(false);
    return (
      <div onClick={() => setOpen(!open)}>
        <Button color="success" variant="contained">Show game</Button>
        <Collapsible open={open}>
          <div>{game.homeTeamCode}</div>
        </Collapsible>
      </div>
    );
  };
  
  return (
    <div className="tracker_master">
      <Button id='getTeamsButton' variant="contained" color="secondary" onClick={getTeams}>Get teams</Button> <br/>
      {teams.map((theTeam) => (
        <CollsTeam team={theTeam} key={theTeam.id} />
      ))}
      <Button id='getGamesButton' variant="contained" color="secondary" onClick={getGames}>Get games</Button> <br/>
      {games.map((game) => (
        <CollsGame game={game}/>
      ))}
    </div>
  );
}