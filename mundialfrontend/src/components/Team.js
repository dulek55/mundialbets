import * as React from 'react';
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import { Container } from '@mui/system';
import { Button, Paper } from '@mui/material';

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
                <h1 style={{color:"blue"}}><u>Add team</u></h1>
                <Box
                component="form"
                noValidate
                autoComplete="off"
                >
                    <TextField id="outlined-basic" label="Team name" variant="outlined" fullWidth 
                    value={countryName}
                    onChange={(e)=>setName(e.target.value)}
                    sx={{ m:1 }}
                    />
                    <TextField id="outlined-basic" label="Team code" variant="outlined" fullWidth
                    value={countryCode}
                    onChange={(e)=>setCode(e.target.value)}
                    sx={{ m:1 }}
                    />
                    <Button id='buttonSubmit' variant="contained" color="secondary" onClick={handleClick}>SUBMIT</Button> <br/>
                </Box>
            </Paper>

            <h1>Teams</h1>
            <Paper elevation={3} style={paperStyle}>
                {teams.map(team=>(
                    <Paper elevation={6} style={{margin:"10px", padding:"15px", textAlign:"left"}} key={team.id}>
                        ID: {team.id} <br/>
                        Country Name: {team.countryName} <br/>
                        Country Code: {team.countryCode}
                    </Paper>
                ))}
                <Button id='buttonRefresh' variant="contained" color="secondary" onClick={handleRefresh}>Refresh</Button> <br/>
            </Paper>
        </Container>
    );
}
