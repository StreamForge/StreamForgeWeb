import React from "react";

import MassiveText from "../../animated-elements/massive-text/massiveText";

import "./startpage-promo.css";
import text_style from "./massive-text.css";

class StartPromo extends React.Component {
	constructor(props) {
		super(props);
	}

	render() {
		return (
			<div className="promo">
				<div className="promo-content">
					<MassiveText
						style={text_style}
						phrases={["Join a new era of streaming", "with StreamForge"]}
					/>
				</div>
			</div>
		);
	}
}

export default StartPromo;