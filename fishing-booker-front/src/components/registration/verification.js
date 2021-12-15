import { useParams } from "react-router";
import { useEffect, useState } from "react";
import axios from "axios";

const Verification = () => {
    const SERVER_URL = process.env.REACT_APP_API;
    //const { code } = useParams();
    const url = window.location.href;
    const [message, setMessage] = useState("");
    const [code, setCode] = useState("");

    console.log(code)
    
    useEffect(() => {
        const splitted = url.split("/");
        setCode(splitted[splitted.length-1]);
        axios.get(SERVER_URL + "/auth/verify?" + code)
             .then(response => {
                setMessage(response.data);
                console.log(response.data);
            });
    })

    return (
        <div>
            <h1>{message}</h1>
        </div>
    )
}

export default Verification;