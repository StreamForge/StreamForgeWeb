import React from "react";
import anime from "animejs";
import DOMPurify from "dompurify";
import Optional from "optional-js";
import equals from "object-equal";

class MassiveText extends React.Component {
	constructor(props) {
		super(props);
		this.state = {
			currentElement: 0
		};

		this.runAnimation = this.runAnimation.bind(this);
	}

	runAnimation() {
		let component = this;

		anime.timeline({ loop: true })
			.add({
				delay: function(el, i) {
					return 500 + 30 * i;
				},
				duration: 1200,
				easing: "easeOutExpo",
				opacity: [0, 1],
				targets: ".wrapper .letter",
				translateX: [40, 0],
				translateZ: 0
			}).add({
				complete: function() {
					let nextElement = component.state.currentElement + 1;
					if (!Optional.ofNullable(component.props.phrases[nextElement]).isPresent()) {
						nextElement = 0;
					}
					component.setState({ currentElement: nextElement });
				},
				delay: function(el, i) {
					return 100 + 30 * i;
				},
				duration: 1100,
				easing: "easeInExpo",
				opacity: [1, 0],
				targets: ".wrapper .letter",
				translateX: [0, -30]
			});
	}

	componentDidUpdate() {
		this.runAnimation();
	}

	componentDidMount() {
		this.runAnimation();
	}

	shouldComponentUpdate(nextProps, nextState) {
		return !equals(this.state, nextState);
	}

	render() {
		let content = DOMPurify.sanitize(this.props.phrases[this.state.currentElement] // eslint-disable-next-line no-control-regex
			.replace(/([^\x00-\x80]|\w)/g, "<span class='letter'>$&</span>"));

		return (
			<h1 className="wrapper" style={this.props.style} dangerouslySetInnerHTML={{ __html: content }} />
		);
	}
}

export default MassiveText;