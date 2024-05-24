import * as React from 'react';
import { useState } from 'react';
import {  Link } from 'react-router-dom';
import Drawer from '@mui/material/Drawer';
import List from '@mui/material/List';
import ListItem from '@mui/material/ListItem';
import ListItemText from '@mui/material/ListItemText';
import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';
import IconButton from '@mui/material/IconButton';
import MenuIcon from '@mui/icons-material/Menu';

export default function Appbar({title}) {
  const [drawerOpen, setDrawerOpen] = useState(false);

  const toggleDrawer = (open) => (event) => {
    if (event.type === 'keydown' && (event.key === 'Tab' || event.key === 'Shift')) {
      return;
    }
    setDrawerOpen(open);
  };

  return (
    <Box sx={{ flexGrow: 1 }}>
      <AppBar position="static">
        <Toolbar>
          <IconButton
            size="large"
            edge="start"
            color="inherit"
            aria-label="menu"
            sx={{ mr: 2 }}
            onClick={toggleDrawer(true)}
          >
            <MenuIcon />
          </IconButton>
          <Typography 
          variant="h6" 
          component={Link} 
          to="/"
          sx={{ 
            flexGrow: 1,
            textDecoration: 'none',
            color: 'inherit',
          }}>
          {title}
          </Typography>
        </Toolbar>
      </AppBar>
      <Drawer 
      anchor="left" 
      open={drawerOpen} 
      onClose={toggleDrawer(false)}
        sx={{
          '& .MuiDrawer-paper': {
            width: {
              xs: '50%', //  width for extra-small screens
              sm: '30%', //  width for small screens
              md: '20%', //  width for medium screens and above
            },
            display: 'flex',
            alignItems: 'center', // Center items horizontally
            //justifyContent: 'center', // Center items vertically
          },
        }}
        >
        <List>
        <ListItem button component={Link} to="/" onClick={toggleDrawer(false)}>
            <ListItemText primary="Home" />
          </ListItem>
          <ListItem button component={Link} to="/TeamPage" onClick={toggleDrawer(false)}>
            <ListItemText primary="Team" />
          </ListItem>
          <ListItem button component={Link} to="/leagues" onClick={toggleDrawer(false)}>
            <ListItemText primary="Leagues" />
          </ListItem>
          <ListItem button component={Link} to="/TimetablePage" onClick={toggleDrawer(false)}>
            <ListItemText primary="Timetable" />
          </ListItem>
          <ListItem button component={Link} to="/login" onClick={toggleDrawer(false)}>
            <ListItemText primary="Login" />
          </ListItem>
          <ListItem button component={Link} to="/register" onClick={toggleDrawer(false)}>
            <ListItemText primary="Register" />
          </ListItem>
        </List>
      </Drawer>
    </Box>
  );
}