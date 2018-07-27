import React from "react";
import ReactDOM from "react-dom";
import { BrowserRouter, Route, Redirect, Switch } from "react-router-dom";
import StartPage from "./mainpage/startPage";

class Index extends React.Component {
	constructor(props) {
		super(props);
	}

	render() {
		return (
			<BrowserRouter>
				<Switch>
					<Route exact path="/" component={StartPage} />
				</Switch>
			</BrowserRouter>
		);
	}
}

ReactDOM.render(
	<Index />,
	document.getElementById("application")
);
