import React from 'react';
import { Container, Paper, Box, TextField, Button } from '@mui/material';
import { useState } from 'react';

const paperStyle = {
    padding: '50px 20px',
    width: {
        xs: '50%', //  width for extra-small screens
        sm: '30%', //  width for small screens
        md: '20%', //  width for medium screens and above
    },
    margin: "20px auto"
};

const LoginPage = () => {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');

    const handleSubmit = (e) => {
        e.preventDefault();
        // logika logowania
        console.log('Logging in with', email, password);
    };

    return (
        <Container>
            <Paper elevation={3} style={paperStyle}>
                <h1>Login</h1>
                <Box component="form" noValidate autoComplete="off" onSubmit={handleSubmit}>
                    <TextField
                        id="email"
                        label="Email"
                        variant="outlined"
                        fullWidth
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                        sx={{ m: 1 }}
                    />
                    <TextField
                        id="password"
                        label="Password"
                        type="password"
                        variant="outlined"
                        fullWidth
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        sx={{ m: 1 }}
                    />
                    <Button variant="contained" color="secondary" type="submit">Login</Button>
                </Box>
            </Paper>
        </Container>
    );
};

export default LoginPage;