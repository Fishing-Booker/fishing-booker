import search from '../images/loupe.png';

const Search = () => {
    return(
        <div>
            <div className="card search">
                <input className="search-input" type="text" placeholder="  Enter entity name"></input>
                <input className="search-input" type="text" placeholder="  Enter entity location"></input>
                <input className="search-input" type="date"></input>
                <input className="search-input btn" type="submit" value="Search"></input>
            </div>
        </div>
    )
}

export default Search;