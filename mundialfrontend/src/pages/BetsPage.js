// src/pages/BetsPage.js
import React, { useState } from 'react';
import { useParams } from 'react-router-dom';
import { Container, Paper, Typography, Box, Button, Modal, TextField } from '@mui/material';
import Flag from 'react-world-flags';

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

const modalStyle = {
    position: 'absolute',
    top: '50%',
    left: '50%',
    transform: 'translate(-50%, -50%)',
    width: 400,
    bgcolor: 'background.paper',
    border: '2px solid #000',
    boxShadow: 24,
    p: 4,
};

const BetsPage = () => {
    const { userId } = useParams();
    const [open, setOpen] = useState(false);
    const [currentBet, setCurrentBet] = useState(null);

    const handleOpen = (bet) => {
        setCurrentBet(bet);
        setOpen(true);
    };

    const handleClose = () => {
        setOpen(false);
        setCurrentBet(null);
    };

    const handleSave = () => {
        // Save the entered scores
        handleClose();
    };

    const isOwnBets = !userId;

    const bets = [
        [
            { id: 1, homeTeam: 'PL', awayTeam: 'FR', score: '10:0' },
            { id: 2, homeTeam: 'DE', awayTeam: 'ES', score: '2:2' },
            { id: 3, homeTeam: 'IT', awayTeam: 'GB', score: '1:1' }
        ],
        [
            { id: 4, homeTeam: 'PT', awayTeam: 'NL', score: '1:0' },
            { id: 5, homeTeam: 'BE', awayTeam: 'CH', score: '0:0' },
            { id: 6, homeTeam: 'SE', awayTeam: 'FI', score: '3:2' }
        ],
        [
            { id: 7, homeTeam: 'NO', awayTeam: 'DK', score: '' },
            { id: 8, homeTeam: 'AT', awayTeam: 'CZ', score: '' },
            { id: 9, homeTeam: 'HU', awayTeam: 'SK', score: '' }
        ]
    ];

    return (
        <Container>
            <Paper elevation={3} style={paperStyle}>
                <Typography variant="h4" component="h1" gutterBottom>
                    {isOwnBets ? 'My Bets' : `User ${userId} Bets`}
                </Typography>
                {bets.map((matchday, index) => (
                    <Box key={index} sx={{ marginBottom: '20px' }}>
                        <Typography variant="h5" component="h2" gutterBottom>
                            Kolejka {index + 1}
                        </Typography>
                        {matchday.map((bet) => (
                            <Paper key={bet.id} elevation={6} style={betStyle}>
                                <Box sx={{ display: 'flex', alignItems: 'center' }}>
                                    <Flag code={bet.homeTeam} style={{ width: '32px', marginRight: '10px' }} />
                                    <Typography variant="body1" component="span">
                                        {bet.homeTeam}
                                    </Typography>
                                </Box>
                                <Typography variant="body1" component="span">
                                    {bet.score || (
                                        isOwnBets && (
                                            <Button variant="contained" onClick={() => handleOpen(bet)}>
                                                Obstaw
                                            </Button>
                                        )
                                    )}
                                </Typography>
                                <Box sx={{ display: 'flex', alignItems: 'center' }}>
                                    <Typography variant="body1" component="span">
                                        {bet.awayTeam}
                                    </Typography>
                                    <Flag code={bet.awayTeam} style={{ width: '32px', marginLeft: '10px' }} />
                                </Box>
                            </Paper>
                        ))}
                    </Box>
                ))}
            </Paper>
            <Modal open={open} onClose={handleClose}>
                <Box sx={modalStyle}>
                    <Typography variant="h6" component="h2">
                        Wprowadź Wynik
                    </Typography>
                    <Box component="form" sx={{ mt: 2 }}>
                        <TextField
                            label={`${currentBet?.homeTeam} Score`}
                            fullWidth
                            margin="normal"
                        />
                        <TextField
                            label={`${currentBet?.awayTeam} Score`}
                            fullWidth
                            margin="normal"
                        />
                        <Button variant="contained" onClick={handleSave} sx={{ mt: 2 }}>
                            Zapisz
                        </Button>
                    </Box>
                </Box>
            </Modal>
        </Container>
    );
};

export default BetsPage;
