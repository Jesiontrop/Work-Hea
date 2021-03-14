import React from "react";
import Button from "./Button";
import Select from "./Select";
import styles from "./SearchField.module.scss";

class SearchField extends React.Component {
    constructor(props) {
        super(props);
        if (props.text)
            this.text = props.text;
        else
            this.text = "Search";
        this.data = props.data;
        if (props.onButtonClick)
            this.onButtonClick = props.onButtonClick;
        else
            this.onButtonClick = function () {};

        this.onButtonClick = this.onButtonClick.bind(this);
    }

    render() {
        return (
            <div className={styles.search}>
                <div className={styles.flexWrapperOne}>
                    <input className={styles.field} />
                    <Button onClick={this.onButtonClick} text={this.text} version="main-bl"/>
                </div>
                <Select data={this.props.data}/>
            </div>
        );
    }
}

export default SearchField;