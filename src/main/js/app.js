import React from "react";
import ReactDOM from "react-dom";
import { BrowserRouter, Route } from "react-router-dom";
import {Home} from "./Home";

class App extends React.Component {
    constructor(props) {
        super(props);
    }

    render() {
        return (
            <BrowserRouter >
                <Route exact path="/" component={Home}/>
            </BrowserRouter>
        );
    }
}

ReactDOM.render(<App />, document.getElementById("root"));