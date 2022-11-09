import React from 'react';
import Collapsible from 'react-collapsible';
import { Button, Paper } from '@mui/material';
import { Container } from '@mui/system';

export default function Coll() {
  const[teams, setTeams] = React.useState([])
  const[games, setGames] = React.useState([])
  const paperStyle = {padding:'20px 10px', width:600,margin:"20px auto"}
  const buttonStyle = {margin:'10px 10px'}

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
      <div onClick={() => setOpen(!open)} key={game.gameId}>
        <Button variant="contained">{game.homeTeamCode} VS {game.awayTeamCode}</Button>
        <Collapsible open={open}>
          <Button style={buttonStyle} variant="contained">1</Button>
          <Button style={buttonStyle} variant="contained">1X</Button>
          <Button style={buttonStyle} variant="contained">X</Button>
          <Button style={buttonStyle} variant="contained">X2</Button>
          <Button style={buttonStyle} variant="contained">2</Button>
        </Collapsible>
      </div>
    );
  };
  
  return (
    <Container>
      <Paper elevation={3} style={paperStyle}>
      <Button id='getTeamsButton' variant="contained" color="secondary" onClick={getTeams} sx={{ m:1 }}>Get teams</Button><br/>
      {teams.map((theTeam) => (
        <CollsTeam team={theTeam} key={theTeam.id} />
      ))}
      </Paper>
      <Paper elevation={3} style={paperStyle}>
      <Button id='getGamesButton' variant="contained" color="secondary" onClick={getGames} sx={{ m:1 }}>Get games</Button><br/>
      {games.map((theGame) => (
        <CollsGame game={theGame} key={theGame.gameId}/>
      ))}
      </Paper>
    </Container>
  );
}