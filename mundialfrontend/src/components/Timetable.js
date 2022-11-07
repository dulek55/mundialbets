import * as React from 'react';
import { Container } from '@mui/system';
import { Button, Paper } from '@mui/material';
import Collapsible from 'react-collapsible';

export default function Team() {
    const paperStyle = {padding:'50px 20px', width:600, margin:"20px auto"}
    const[teams, setTeams] = React.useState([])

    const handleRefresh=(e)=>{
        fetch('http://207.154.207.233:8080/api/team')
            .then(res=>res.json())
            .then((result)=>{
                setTeams(result);
            });
    
    }

    return (
        <Container>         
            <Paper elevation={3} style={paperStyle}>
                <h1>Timetable</h1><br/>
                {teams.map(team=>(
                        <Paper class="background" elevation={6} style={{margin:"10px", padding:"15px", textAlign:"center"}} key={team.id}>
                            <Collapsible label = {team.countryName}>
                            <Button style={{marginLeft: 15, marginRight: 15}} color="success" variant="contained">WIN</Button>
                            <Button style={{marginLeft: 15, marginRight: 15}} color="warning" variant="contained">DRAW</Button>
                            <Button style={{marginLeft: 15, marginRight: 15}} color="error" variant="contained">LOSE</Button>
                            </Collapsible>
                        </Paper>            
                ))}
                <Button id='buttonRefresh' variant="contained" color="secondary" onClick={handleRefresh}>Refresh</Button> <br/>
            </Paper>
        </Container>
    );
}
