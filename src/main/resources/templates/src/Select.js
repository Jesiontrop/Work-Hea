import React from "react";
import styles from "./Select.module.scss";

const Select = (props) => {
    let version = "main";
    if (props.version)
        version = props.version;
    const styleVersion = "select-" + version;

    function MakeItem(x) {
        return <option>{x}</option>
    }

    return (
        <select className={styles[styleVersion]}>
            {props.data.map(MakeItem)}
        </select>
    );
};

export default Select;