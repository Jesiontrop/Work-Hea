import React from "react";
import styles from "../styles/Select.module.scss";

const Select = (props) => {
    let version = "main";
    if (props.version)
        version = props.version;
    const styleVersion = "select-" + version;

    function MakeItem(x) {
        return <option key={x}>{x}</option>
    }

    return (
        <select className={styles[styleVersion]}>
            {props.options.map(MakeItem)}
        </select>
    );
};

export {Select};