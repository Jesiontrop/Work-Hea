import React from "react";
import styles from "../styles/Button.module.scss";

const Button = (props) => {
    let text = "Text";
    if (props.text)
        text = props.text;

    let version = "main";
    if (props.version)
        version = props.version;
    const styleVersion = "button-" + version;

    let onCustomClick = function () {};
    if (props.onClick)
        onCustomClick = props.onClick;

    return (
        <button className={styles[styleVersion]} style={props.style} onClick={onCustomClick}>
            <p className={styles.text}>{text}</p>
        </button>
    );
};

export {Button};