const Letters = ({letters, parentCallback}) => {
    const alphabet  = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O',
    'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Z']

    function check(letter) {
        if (letters.includes(letter)) {
            return false;
        } else {
            return true;
        }
    }

    const handleFilter = (param) => {
        parentCallback(param);
        console.log(param)
    }

    const handleReset = (param) => {
        parentCallback(param)
    }

    const allLetters = alphabet.map((a, index) => {
        return (
            <li key={index} className={check(a) ? "disabled" : null}><a onClick={() => handleFilter(a)}>{a}</a></li>
        )
    })

    return (
        <div className="filter-letters">
            <div className="alpha">
				<ul>
                    {allLetters}
                    <li><a className="reset" onClick={() => handleReset('')}>reset</a></li>
				</ul>
			</div>
        </div>
    )
}

export default Letters;