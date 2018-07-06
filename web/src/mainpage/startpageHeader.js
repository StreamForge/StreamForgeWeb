import React from 'react';
import 'bulma/css/bulma.css'

class StartHeader extends React.Component {
    constructor(props) {
        super(props);
    }

    render() {
        return (
            <section className="hero is-primary">
                <div className="hero-body">
                    <div className="container">
                        <h1 className="title">StreamForge Header</h1>
                        <h2 className="subtitle">Forge</h2>
                    </div>
                </div>
            </section>
        );
    }
}
export default StartHeader;