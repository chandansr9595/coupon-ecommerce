import React from 'react';
import Countdown from 'react-countdown';
import './timer.css';
import { connect } from 'react-redux';
import {fetchProducts} from '../../actions';


interface ITimerComponentProps {
    fetchProducts: () => void,
    dealTimout: number,
}

class TimerComponent extends React.Component<ITimerComponentProps> {

    timeOut: any;

    componentWillUnmount() {
        clearTimeout(this.timeOut);
    }

    completionist = () => {
        this.timeOut = setTimeout(() => {
            window.location.reload();
        }, 1000);
        return <span className="timerCountDownText">Deals are gone!</span>
    };
    
    renderer = (props: {
        hours: any,
        minutes: any,
        seconds: any,
        completed: boolean
    }) => {
        if (props.completed) {
            return this.completionist()
        } else {
            return <span className="timerCountDown">{props.hours}:{props.minutes}:{props.seconds}</span>;
        }
    };

    rendererLoading = (props: {
        hours: any,
        minutes: any,
        seconds: any,
        completed: boolean
    }) => {
        if (props.completed) {
            return this.completionist()
        } else {
            return <span className="timerCountDownText">Loading....</span>;
        }
    };

    render() {
        return (
            <div className='contianer'>
                <div className='typography'>
                    <div className='countDownContainer'>
                        <div className='countDownHeader'>
                            <p>Countdown Has Began</p>
                        </div>
                    </div>
                    <div className='counterContainer'>
                        <div className='counter'>
                            <Countdown 
                                date={new Date(this.props.dealTimout)} 
                                renderer={this.props.dealTimout ? this.renderer : this.rendererLoading}
                            />
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

const mapStateToProps = (state: any) => {
    return {
        dealTimout: state.products.dealTimout
    }
}

export default connect(mapStateToProps, {
    fetchProducts
})(TimerComponent);