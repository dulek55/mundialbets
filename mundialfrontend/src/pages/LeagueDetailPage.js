// src/pages/LeagueDetailPage.js
import React from 'react';
import { useParams, Link as RouterLink } from 'react-router-dom';
import { Container, Paper, Typography, Box } from '@mui/material';

const paperStyle = {
    padding: '20px',
    width: '80%',
    margin: '20px auto'
};

const participantStyle = {
    margin: '10px',
    padding: '15px',
    textAlign: 'left'
};

const LeagueDetailPage = () => {
    const { name } = useParams();

    const participants = [
        { id: 1, name: 'User 1', points: 50, rank: 1 },
        { id: 2, name: 'User 2', points: 45, rank: 2 },
        { id: 3, name: 'User 3', points: 40, rank: 3 },
    ];

    return (
        <Container>
            <Paper elevation={3} style={paperStyle}>
                <Typography variant="h4" component="h1" gutterBottom>
                    League: {name}
                </Typography>
                {participants.map((participant) => (
                    <Paper key={participant.id} elevation={6} style={participantStyle}>
                        <RouterLink to={`/bets/${participant.id}`} style={{ textDecoration: 'none', color: 'inherit' }}>
                            <Box sx={{ display: 'flex', justifyContent: 'space-between' }}>
                                <Typography variant="h6" component="h2">
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
                        </RouterLink>
                    </Paper>
                ))}
            </Paper>
        </Container>
    );
};

export default LeagueDetailPage;
