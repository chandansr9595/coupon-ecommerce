import React from 'react';
import Countdown from 'react-countdown';

var timeOut: any;

const Completionist = (timeOut: any) => {
    timeOut = setTimeout(() => {
        window.location.reload();
    }, 1000);
    return <span className="timerCountDownText">Deals are gone!</span>
};

const renderer = (props: {
    hours: any,
    minutes: any,
    seconds: any,
    completed: boolean
}) => {
    if (props.completed) {
        return <Completionist />;
    } else {
        return <span className="timerCountDown">{props.hours}:{props.minutes}:{props.seconds}</span>;
    }
};

const CountDownTimer = (props: {
    timeStamp: number,
    timer: any
}) => {
    props.timer = timeOut;
    return <Countdown 
        date={Date.now() + 5000} 
        renderer={renderer}
    />
}

export default CountDownTimer;