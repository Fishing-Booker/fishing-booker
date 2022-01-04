import { useState } from "react";

const Search = () => {
    const [name, setName] = useState('');
    const [url, setUrl] = useState('');

    return(
        <div>
            <div className="card search">
                <input className="search-input" type="search" placeholder="  Enter entity name" value={name} 
                    onChange={(e) => {
                    setName(e.target.value);
                    setUrl()
                    }}></input>
                <input className="search-input" type="text" placeholder="  Enter entity location"></input>
                <input className="search-input" type="date"></input>
                <input className="search-input btn" type="submit" value="Search"></input>
            </div>
        </div>
    )
}

export default Search;