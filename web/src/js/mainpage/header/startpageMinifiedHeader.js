import React from "react";
import Config from "../../config/config.js";
import Button from "../../commons/thin-button/thinButton.js";

import "./minified-header.css";
import twitch_style from "./twitch-button-style.css";

import twitchIcon from "../../../media/images/twitch-logo.png";

class MinifiedHeader extends React.Component {
	constructor(props) {
		super(props);
	}

	render() {
		return (
			<header className="nav-min">
				<div className="auth-bar-min no-select">
					<Button
						text='LOG IN WITH TWITCH'
						icon={twitchIcon}
						onClick={() => window.location.replace(Config.BASE_WEB_URL + Config.AUTH_PATH)}
						style={twitch_style.twitch_button_style}
					/>
				</div>
			</header>
		);
	}
}

export default MinifiedHeader;
