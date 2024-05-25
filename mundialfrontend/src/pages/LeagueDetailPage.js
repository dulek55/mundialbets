// src/pages/LeagueDetailPage.js
import React from 'react';
import { useParams, Link as RouterLink } from 'react-router-dom';
import { Container, Paper, Typography, Box, Link } from '@mui/material';

const paperStyle = {
    padding: '20px',
    width: '80%',
    margin: '20px auto'
};

const betStyle = {
    margin: '10px',
    padding: '15px',
    textAlign: 'left',
    display: 'flex',
    justifyContent: 'space-between',
    alignItems: 'center'
};

const LeagueDetailPage = () => {
    const { id, name } = useParams();
    const participants = [
        { id: 1, name: 'User1', points: 50, rank: 1 },
        { id: 2, name: 'User2', points: 44, rank: 2 },
        { id: 3, name: 'User3', points: 38, rank: 3 },
    ];

    return (
        <Container>
            <Paper elevation={3} style={paperStyle}>
                <Typography variant="h4" component="h1" gutterBottom>
                    {name}
                </Typography>
                <Typography variant="h5" component="h2" gutterBottom>
                    Participants
                </Typography>
                {participants.map((participant) => (
                    <Paper key={participant.id} elevation={6} style={betStyle}>
                        <Link 
                            component={RouterLink} 
                            to={`/bets/${participant.id}`} 
                            sx={{ textDecoration: 'none', color: 'inherit', width: '100%' }}
                        >
                            <Box sx={{ display: 'flex', justifyContent: 'space-between' }}>
                                <Typography variant="h6" component="h2" sx={{ flexGrow: 1 }}>
                                    {participant.name}
                                </Typography>
                                <Box sx={{ display: 'flex', flexDirection: 'column', alignItems: 'flex-end' }}>
                                    <Typography variant="body1" component="span">
                                        Points: {participant.points}
                                    </Typography>
                                    <Typography variant="body1" component="span">
                                        Rank: {participant.rank}
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

export default LeagueDetailPage;
