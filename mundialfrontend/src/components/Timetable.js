import * as React from 'react';
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import { Container } from '@mui/system';
import { Button, Paper } from '@mui/material';
import Collapsible from 'react-collapsible';

export default function Team() {
    const paperStyle = {padding:'50px 20px', width:600,margin:"20px auto"}
    const[countryName, setName] = React.useState('')
    const[countryCode, setCode] = React.useState('')
    const[teams, setTeams] = React.useState([])

    const handleClick=(e)=>{
        e.preventDefault()
        const team={countryName,countryCode}
        console.log(team)
        const requestOptions = {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(team)
        };
        fetch('http://207.154.207.233:8080/api/team', requestOptions)
            .then(async response => {
                const isJson = response.headers.get('content-type')?.includes('application/json');
                const data = isJson && await response.json();
                var elem = document.getElementById("buttonSubmit")
                elem.innerText = "Team added!"
    
                // check for error response
                if (!response.ok) {
                    // get error message from body or default to response status
                    elem.innerText = "Error!"
                    const error = (data && data.message) || response.status;
                    return Promise.reject(error);
                }
    
            })
            .catch(error => {
                console.error('There was an error!', error);
            });
    
    }
    const handleRefresh=(e)=>{
        fetch('http://207.154.207.233:8080/api/team')
            .then(res=>res.json())
            .then((result)=>{
                setTeams(result);
            });
    
    }

    React.useEffect(()=>{
        fetch("http://207.154.207.233:8080/api/team")
        .then(res=>res.json())
        .then((result)=>{
            setTeams(result);
        }
    )
    },[])

    return (
        <Container>         
            
            <Paper elevation={3} style={paperStyle}>
                <h1>Timetable</h1><br></br>
                {teams.map(team=>(
                        <Paper class="background" elevation={6} style={{margin:"10px", padding:"15px", textAlign:"center"}} key={team.id}>
                            <Collapsible trigger = {team.countryName}><br></br>
                            <Button color="success" variant="contained">&nbsp;WIN&nbsp;</Button>&nbsp;&nbsp;&nbsp;
                            <Button color="warning" variant="contained">DRAW</Button>&nbsp;&nbsp;&nbsp;
                            <Button color="error" variant="contained">LOSE</Button>&nbsp;&nbsp;&nbsp;
                            </Collapsible>
                        </Paper>            
                ))}
                <Button id='buttonRefresh' variant="contained" color="secondary" onClick={handleRefresh}>Refresh</Button> <br/>
            </Paper>
        </Container>
    );
}
