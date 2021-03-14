import React from "react";
import styles from "./Button.module.scss";

const Button = (props, { text = "Text"}) => {
    let version = "main";
    if (props.version)
        version = props.version;
    const styleVersion = "button-" + version;

    let onCustomClick = function () {};
    if (props.onClick)
        onCustomClick = props.onClick;

    return (
        <button className={styles[styleVersion]} onClick={onCustomClick}>
            <p className={styles.text}>{text}</p>
        </button>
    );
};

export default Button;