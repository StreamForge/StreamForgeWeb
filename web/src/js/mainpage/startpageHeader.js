import React from 'react';
import Config from '../config/config.js'

import 'bulma/css/bulma.css'
import '../../css/style.css'

import twitchIcon from '../../media/images/twitch-logo.png'

class StartHeader extends React.Component {
    constructor(props) {
        super(props);
        this.handleClick = this.handleClick.bind(this);
    }

    handleClick() {
        window.location.replace(Config.baseWebURL + '/auth');
    }

    render() {
        return (
            <div className="nav">
                <div className="nav-logo"/>
                <div className="auth-bar no-select">
                    <div className="auth-action twitch-auth" onClick={this.handleClick}>
                        <img className="auth-logo" src={twitchIcon} />
                            <p>LOG IN WITH TWITCH</p>
                    </div>
                </div>
            </div>
        );
    }
}

export default StartHeader;
