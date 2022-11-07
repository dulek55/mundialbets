import React from 'react';
import Collapsible from 'react-collapsible';
import { Button } from '@mui/material';

export default function Coll() {
  const[teams, setTeams] = React.useState([])

  React.useEffect(()=>{
    fetch("http://207.154.207.233:8080/api/team")
    .then(res=>res.json())
    .then((result)=>{
        setTeams(result);
    }
)
},[])

  const Colls = ({ team }) => {
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

  return (
    <div className="tracker_master">
      {teams.map((team) => (
        <Colls team={team} key={team.id} />
      ))}
    </div>
  );
}