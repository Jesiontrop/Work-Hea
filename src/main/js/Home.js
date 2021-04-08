import React from "react";
import {Button} from "./modules/Button";
import {SearchField} from "./modules/SearchField";
import styles from "./styles/Index.module.scss";

class Home extends React.Component {
    constructor(props) {
        super(props);
        this.options = ["organization", "offer"];
        this.grayColor = {background: "#BDBDBD"}
    }

    render() {
        return (
            <div className={styles.main}>
                <div className={styles.head}>
                    <div className={styles.login}>
                        <div className={styles.button}>
                            <Button version="second" text="Sign In"/>
                        </div>
                        <div className={styles.button}>
                            <Button style={this.grayColor} version="second" text="Sign Up"/>
                        </div>
                    </div>
                </div>
                <div className={styles.body}>
                    <p className={styles.workHea}>Work Hea</p>
                    <SearchField options={this.options}/>
                </div>
            </div>
        );
    }
}

export {Home};