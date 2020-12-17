import React from 'react';
import { makeStyles } from '@material-ui/core/styles';

const useStyles = makeStyles(theme => ({
    contianer: {
        display: 'flex',
        justifyContent: 'center',
    },
    typography:{
        width: '95%',
        backgroundColor: '#802E6E',
        borderRadius: 15,
        height: 300,
    },
    countDownContainer:{
        fontSize: 30,
        fontWeight: 'bold',
        color: 'white',
        display: 'flex',
        justifyContent: 'center',
    },
    countDownHeader: {
        width: 420,
        textAlign: 'left',
    },
    counterContainer: {
        display: 'flex',
        justifyContent: 'center',
        
    },
    counter: {
        border: '1px solid white',
        width: 400,
        height: 150,
        borderRadius: 10
    }
}));


const TimerComponent = () => {
    const classes = useStyles();
    return (
        <div className={classes.contianer}>
            <div className={classes.typography}>
                <div className={classes.countDownContainer}>
                    <div className={classes.countDownHeader}>
                        <p>Countdown Has Began</p>
                    </div>
                </div>
                <div className={classes.counterContainer}>
                    <div className={classes.counter}>
                        Hello
                    </div>
                </div>
            </div>
        </div>
    );
}

export default TimerComponent;