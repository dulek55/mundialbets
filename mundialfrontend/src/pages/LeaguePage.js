// src/pages/LeaguePage.js
import React from 'react';
import { Container, Paper, Typography, Box, Link } from '@mui/material';
import { Link as RouterLink } from 'react-router-dom';

const paperStyle = {
    padding: '20px',
    width: {
        xs: '80%', // width for extra-small screens
        sm: '60%', // width for small screens
        md: '40%', // width for medium screens and above
    },
    margin: "20px auto"
};

const LeaguePage = () => {
    const leagues = [
        { id: 1, name: 'Euro 24', points: 44, rank: 4 },
        { id: 2, name: 'Rodzinne Obstawianie', points: 44, rank: 2 },
        { id: 3, name: 'Nokia League', points: 44, rank: 8 }
    ];

    return (
        <Container>
            <Paper elevation={3} style={paperStyle}>
                <Typography variant="h4" component="h1" gutterBottom>
                    Moje Ligi
                </Typography>
                {leagues.map((league, index) => (
                    <Paper 
                        key={index} 
                        elevation={6} 
                        style={{ 
                            margin: "10px", 
                            padding: "15px", 
                            textAlign: "left"   
                        }}
                    >
                        <Link 
                            component={RouterLink} 
                            to={`/leagues/${league.id}/${league.name}`}
                            sx={{ textDecoration: 'none', color: 'inherit' }}
                        >
                            <Box sx={{ display: 'flex', justifyContent: 'space-between' }}>
                                <Typography variant="h6" component="h2">
                                    {league.name}
                                </Typography>
                                <Box sx={{ display: 'flex', flexDirection: 'column', alignItems: 'flex-end' }}>
                                    <Typography variant="body1" component="span">
                                        Points: {league.points}
                                    </Typography>
                                    <Typography variant="body1" component="span">
                                        Rank: {league.rank}
                                    </Typography>  
                                </Box>
                            </Box>
                        </Link>
                    </Paper>
                ))}
            </Paper>
        </Container>
    );
};

export default LeaguePage;
