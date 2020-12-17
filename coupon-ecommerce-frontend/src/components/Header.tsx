import React, { useEffect } from 'react';
import { makeStyles } from '@material-ui/core/styles';


const useStyles = makeStyles(theme => ({
    main:{
        display: 'flex',
        justifyContent: 'center',
        height: 80
    },
    container:{
        margin: 'auto',
        width: '100%',
        overflow: 'hidden',
        whiteSpace: 'nowrap',
    },
    inline: {
      display: 'inline-block',
    },
    icon:{
        display: 'inline-block',
        float: 'left'
    },
    links:{
        display: 'inline-block',
        width: '80%',
    },
    linksContainer: {
        display: 'flex',
        justifyContent: 'center',
    },
    eachLink:{
        "&:hover, &:focus": {
            boxShadow: "0px 3px 3px -2px rgba(0,0,0,0.2), 0px 3px 4px 0px rgba(0,0,0,0.14), 0px 1px 8px 0px rgba(0,0,0,0.12)"
        },
        padding: '0 40px 0 40px',
        height: 40,
        cursor: 'pointer',
        verticalAlign: 'center',
        lineHeight: '40px'
    },
}));

const Header = () => {

    const classes = useStyles();

    useEffect(() => {
        console.log("useEffect")
    }, []);

    return (
        <div className={classes.main}>
            <div className={classes.container}>
                <div className={classes.inline}>Icons</div>
                <div className={classes.links}>
                    <div className={classes.linksContainer}>
                        <div className={classes.eachLink}>Products</div>
                        <div className={classes.eachLink}>Add products</div>
                        <div className={classes.eachLink}>Categories</div>
                    </div>
                </div>
                <div className={classes.inline}>Profile</div>
            </div>
        </div>
    )
}

export default Header;