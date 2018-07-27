import React from "react";
import Optional from "optional-js";

import style from "./button.css";

class ThinButton extends React.Component {
	constructor(props) {
		super(props);
	}

	render() {
		const classes = ["sf-thin-button"];
		const propStyle = Optional.ofNullable(this.props.style)
			.orElse({});
		Optional.ofNullable(this.props.className)
			.ifPresent(className => classes.push(className));

		return (
			<div className={ classes.join(" ") } style={{ ...style, ...propStyle }} onClick={this.props.onClick}>
				{ this.props.icon ? <img src={this.props.icon} /> : null }
				<p>{this.props.text}</p>
			</div>
		);
	}
}

export default ThinButton;