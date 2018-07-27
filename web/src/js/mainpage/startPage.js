import React from "react";
import $ from "jquery";
import Progress from "react-progress-2";

import StartHeader from "./header/startpageHeader.js";
import StartPromo from "./promo/startpagePromo.js";
import StartContent from "./content/startpageContent";

import favicon from "../../media/images/sword-icon-20.png";

class StartPage extends React.Component {
	constructor(props) {
		super(props);
	}

	componentDidMount() {
		$("link[rel*='icon']").attr("href", favicon);
	}

	render() {
		return (
			<React.Fragment>
				<Progress.Component />
				<StartHeader />
				<StartPromo />
				<StartContent/>
			</React.Fragment>
		);
	}
}

export default StartPage;
