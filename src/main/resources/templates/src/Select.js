import React from "react";
import styles from "./Select.module.scss";

const Select = (props, { text = "Text", version="main"}) => {
    const styleVersion = "select-" + version;
    return (
        <div className={styles[styleVersion]}>
            <p className={styles.text}>{text}</p>
        </div>
    );
};

export default Select;