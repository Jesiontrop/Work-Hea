import React from "react";
import styles from "./Button.module.scss";

const Button = (props, { text = "Text", width = "155px", height = "65px" }) => {
    const style = {width: width, height: height};
    Object.assign(style, props.style);
    return (
        <div className={styles.button} style={style}>
            <p className={styles.text}>{text}</p>
        </div>
    );
};

export default Button;