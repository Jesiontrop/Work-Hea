import React from "react";
import styles from "./Select.module.scss";

const Select = (props, { version="main" }) => {
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