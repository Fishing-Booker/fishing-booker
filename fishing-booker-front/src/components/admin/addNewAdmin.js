const AddNewAdminForm = () => {
    const SERVER_URL = process.env.REACT_APP_API; 
  const { addToast } = useToasts();
  const history = useHistory();
  const url = window.location.href;
  const [role, setRole] = useState("ROLE_DEFADMIN");
  const [name, setName] = useState("")
  const [surname, setSurname] = useState("")
  const [username, setUsername] = useState("")
  const [email, setEmail] = useState("")
  const [password, setPassword] = useState("")
  const [address, setAddress] = useState("")
  const [city, setCity] = useState("")
  const [country, setCountry] = useState("")
  const [phoneNumber, setPhoneNumber] = useState("")
  const [isDeleted, setIsDeleted] = useState(false)
  const [isApproved, setIsApproved] = useState(false)
  const [isFirstLogin, setIsFirstLogin] = useState(true);
  const [registrationReason, setRegistrationReason] = useState("")
  const [confirmationPassword, setConfirmationPassword] = useState("")

  const values = {
    name, 
    surname,
    username,
    email,
    registrationReason,
    password,
    address, 
    city,
    country,
    phoneNumber,
    isDeleted,
    isApproved,
    isFirstLogin,
    role
  }
    return (
    <div className="container-reg">
    <div className="title">Registration</div>
    <div className="content">
      <form onSubmit={handleSubmit}>
        <div className="user-details">
          <div className="input-box">
            <span className="details">Name</span>
            <input type="text" placeholder="Enter your name" required onChange={(e) => {setName(e.target.value)}} value={name}/>
          </div>
          <div className="input-box">
            <span className="details">Phone Number</span>
            <input type="text" placeholder="Enter your phone number" required onChange={(e) => setPhoneNumber(e.target.value)} value={phoneNumber}/>
          </div>
          <div className="input-box">
            <span className="details">Surname</span>
            <input type="text" placeholder="Enter your surname" required onChange={(e) => setSurname(e.target.value)} value={surname}/>
          </div>
          <div className="input-box">
            <span className="details">Email</span>
            <input type="text" placeholder="Enter your email" required onChange={(e) => setEmail(e.target.value)} value={email}/>
          </div>
          <div className="input-box">
            <span className="details">Address</span>
            <input type="text" placeholder="Enter your address" required onChange={(e) => setAddress(e.target.value)} value={address}/>
          </div>
          <div className="input-box">
            <span className="details">Username</span>
            <input type="text" placeholder="Choose your username" required onChange={(e) => setUsername(e.target.value)} value={username}/>
          </div>
          <div className="input-box">
            <span className="details">City</span>
            <input type="text" placeholder="Enter your city" required onChange={(e) => setCity(e.target.value)} value={city}/>
          </div>
          <div className="input-box">
            <span className="details">Password</span>
            <input type="password" placeholder="Enter your password" required onChange={(e) => setPassword(e.target.value)} value={password}/>
          </div>
          <div className="input-box">
            <span className="details">Country</span>
            <input type="text" placeholder="Enter your country" required onChange={(e) => setCountry(e.target.value)} value={country}/>
          </div>
          <div className="input-box">
            <span className="details">Confirm Password</span>
            <input type="password" placeholder="Confirm your password" required onChange={(e) => setConfirmationPassword(e.target.value)} value={confirmationPassword}/>
          </div>
          <div className="input-box-reasons">
            <span className="details">Registration reason</span>
            <textarea type="text" placeholder="Registration reason" required onChange={(e) => setRegistrationReason(e.target.value)} value={registrationReason}/>
          </div>
        </div>
        <p className="reg-message">Alredy have an account? <Link className="link" to="/login">Log in</Link></p>
        <div className="button">
          <input type="submit" value="Register"/>
        </div>
      </form>
    </div>
  </div>
    )
}

export default AddNewAdminForm;