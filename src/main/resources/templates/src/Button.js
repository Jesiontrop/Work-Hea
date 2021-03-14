import React from "react";
import styles from "./Button.module.scss";

const Button = (props, { text = "Text", version="main"}) => {
    const styleVersion = "button-" + version;
    let onCustomClick;
    if (props.onClick)
        onCustomClick = props.onClick;
    else
        onCustomClick = function () {};
    return (
        <button className={styles[styleVersion]} onClick={onCustomClick}>
            <p className={styles.text}>{text}</p>
        </button>
    );
};

export default Button;