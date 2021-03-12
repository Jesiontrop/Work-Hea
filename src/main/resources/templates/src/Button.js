import React from "react";
import styles from "./Button.module.scss";

const Button = (props, { text = "Text", version="main"}) => {
    const styleVersion = "button-" + version;
    return (
        <div className={styles[styleVersion]}>
            <p className={styles.text}>{text}</p>
        </div>
    );
};

export default Button;