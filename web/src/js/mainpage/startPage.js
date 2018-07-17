import React from 'react';
import StartHeader from './startpageHeader.js'
import StartPromo from './startpagePromo.js'

import 'bulma/css/bulma.css'

class StartPage extends React.Component {
    constructor(props) {
        super(props);
    }

    render() {
        return (
            <React.Fragment>
                <StartHeader />
                <StartPromo />
            </React.Fragment>
        );
    }
}

export default StartPage;
