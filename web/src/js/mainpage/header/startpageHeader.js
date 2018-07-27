import React from "react";
import Config from "../../config/config.js";
import Button from "../../commons/thin-button/thinButton.js";

import "./startpage-header.css";
import style from "./twitch-button-style.css";

import twitchIcon from "../../../media/images/twitch-logo.png";

class StartHeader extends React.Component {
	constructor(props) {
		super(props);
	}

	render() {
		return (
			<header className="nav">
				<div className="nav-logo" />
				<div className="auth-bar no-select">
					<Button
						text='LOG IN WITH TWITCH'
						icon={twitchIcon}
						onClick={() => window.location.replace(Config.BASE_WEB_URL + Config.AUTH_PATH)}
						style={style}
						className={style.button}
					/>
				</div>
			</header>
		);
	}
}

export default StartHeader;
