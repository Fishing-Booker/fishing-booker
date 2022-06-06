package com.example.fishingbooker.Service;

import com.example.fishingbooker.DTO.EntityDTO;
import com.example.fishingbooker.DTO.RatingInfoDTO;
import com.example.fishingbooker.DTO.UserDTO;
import com.example.fishingbooker.Enum.CategoryType;
import com.example.fishingbooker.IRepository.*;
import com.example.fishingbooker.IService.*;
import com.example.fishingbooker.Model.*;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService, UserDetailsService {

    //@Autowired
    private final IUserRepository userRepository;

    @Autowired
    IRoleService roleService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    IAdventureRepository adventureRepository;

    @Autowired
    ILodgeRepository lodgeRepository;

    @Autowired
    IShipRepository shipRepository;

    @Autowired
    ILocationRepository locationRepository;

    @Autowired
    IImageService imageService;

    @Autowired
    IUserCategoryService userCategoryService;

    @Autowired
    IOwnerIncomeService ownerIncomeService;

    @Autowired
    private JavaMailSender mailSender;

    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> findById(Integer id) {
        return this.userRepository.findById(id);
    }

    @Override
    public User findByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }

    @Override
    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    @Override
    public List<UserDTO> getUserList(String adminUsername) {
        List<UserDTO> userDTOS = new ArrayList<>();
        List<User> users = findAll();
        Integer index = 1;
        for (User u: users) {
            if(!u.getUsername().equals(adminUsername) && u.isApproved()) {
                String role = findUserRolename(u.getId());
                UserDTO dto = new UserDTO(index++, u.getId(), u.getUsername(), role, u.getName(), u.getSurname(), u.getAddress(), u.getCity(), u.getCountry(), u.getPhoneNumber(), u.getEmail());
                userDTOS.add(dto);
            }
        }
        return userDTOS;
    }

    @Override
    public User save(UserDTO userDTO) {
        User u = new User();
        u.setUsername(userDTO.getUsername());
        u.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        u.setEmail(userDTO.getEmail());
        u.setName(userDTO.getName());
        u.setSurname(userDTO.getSurname());
        u.setAddress(userDTO.getAddress());
        u.setApproved(false);
        u.setFirstLogin(true);
        u.setCity(userDTO.getCity());
        u.setCountry(userDTO.getCountry());
        u.setDeleted(false);
        u.setPhoneNumber(userDTO.getPhoneNumber());
        List<Role> roles = roleService.findByName(userDTO.getRole());
        u.setRoles(roles);

        String verificationCode = RandomString.make(64);
        u.setVerificationCode(verificationCode);
        this.userRepository.save(u);
        System.out.println(userDTO.getRole());
        addCategory(u.getUsername());
        if(userDTO.getRole().equals("ROLE_INSTRUCTOR") ||userDTO.getRole().equals("ROLE_SHIPOWNER") || userDTO.getRole().equals("ROLE_LODGEOWNER"))ownerIncomeService.initializeOwnerIncome(u.getUsername());
        return u;
    }

    private void addCategory(String userUsername){
        User user = userRepository.findByUsername(userUsername);
        UserCategory userCategory = new UserCategory();
        userCategory.setCategoryType(CategoryType.wood);
        userCategory.setUser(user);
        userCategory.setPoints(0);
        userCategoryService.add(userCategory);
    }


    @Override
    public User saveAdmin(UserDTO userDTO) {
        User u = new User();
        u.setUsername(userDTO.getUsername());
        u.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        u.setEmail(userDTO.getEmail());
        u.setName(userDTO.getName());
        u.setSurname(userDTO.getSurname());
        u.setAddress(userDTO.getAddress());
        u.setApproved(true);
        u.setFirstLogin(true);
        u.setCity(userDTO.getCity());
        u.setCountry(userDTO.getCountry());
        u.setDeleted(false);
        u.setPhoneNumber(userDTO.getPhoneNumber());
        List<Role> roles = roleService.findByName(userDTO.getRole());
        u.setRoles(roles);

        String verificationCode = RandomString.make(64);
        u.setVerificationCode(verificationCode);

        return this.userRepository.save(u);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findByUsername(username);
        if(user == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else {
            return user;
        }
    }

    @Override
    public void sendVerificationEmail(User user) {
        String subject = "Please verify your registration!";
        String sender = "Fishing Booker";
        String content ="<p>Dear " + user.getName() + " " + user.getSurname() + ", <p>";
        content += "<p>Please click on the link below to verify your account on our site:</p>";
        String verifyURL = "http://localhost:3000/verify/code=" + user.getVerificationCode();
        content += "<h3><a href=\"" + verifyURL + "\">VERIFY ACCOUNT</a></h3>";
        content += "<p>Thank you<br>Fishing Booker</p>";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        try {
            helper.setFrom("fishingbookernsm@hotmail.com", sender);
            helper.setTo(user.getEmail());
            helper.setSubject(subject);
            helper.setText(content, true);

        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        mailSender.send(message);
    }

    @Override
    public boolean verify(String verificationCode) {
        User user = userRepository.findByVerificationCode(verificationCode);
        if (user == null) {
            return false;
        } else {
            userRepository.approve(user.getId());
            return true;
        }
    }

    public String findUserRolename(Integer id) {
        String role = "";
        Optional<User> user = this.userRepository.findById(id);
        List<Role> roles = user.get().getRoles();
        role = roles.get(0).getName();
        return role;
    }

    @Override
    public List<User> findUnapprovedUsers() {
        return userRepository.findUnapprovedUsers();
    }

    @Override
    public void sendVerificationEmailToOwnersAndInstructors(User user) {
        String subject = "Registration request approval";
        String sender = "Fishing Booker";
        String content ="<p>Dear " + user.getName() + " " + user.getSurname() + ", your account is approved, you can register<p>";
        content += "<p>Thank you<br>Fishing Booker</p>";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        try {
            helper.setFrom("fishingbookernsm@hotmail.com", sender);
            helper.setTo(user.getEmail());
            helper.setSubject(subject);
            helper.setText(content, true);

        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        mailSender.send(message);
    }

    @Override
    public void sendRejectingEmail(User user) {
        String subject = "Registration request rejection";
        String sender = "Fishing Booker";
        String content = "<p>Dear " + user.getName() + " " + user.getSurname() + ", your request for registration is rejected. We don't need more people in this moment,sorry<p>";
        content += "<p>Thank you for your time<br>Fishing Booker</p>";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        try {
            helper.setFrom("fishingbookernsm@hotmail.com", sender);
            helper.setTo(user.getEmail());
            helper.setSubject(subject);
            helper.setText(content, true);

        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        mailSender.send(message);
    }

    @Override
    public void changePassword(String password, Integer id) {
        userRepository.changePassword(passwordEncoder.encode(password), id);
    }

    @Override
    public void adminFirstLogin(String password, Integer id) {
        userRepository.changePassword(passwordEncoder.encode(password), id);
        userRepository.adminsFirstLogin(id);
    }

    @Override
    public User update(UserDTO userDTO, Integer id) {
        User updated = userRepository.getById(id);
        updated.setName(userDTO.getName());
        updated.setSurname(userDTO.getSurname());
        updated.setUsername(userDTO.getUsername());
        updated.setAddress(userDTO.getAddress());
        updated.setCity((userDTO.getCity()));
        updated.setPhoneNumber(userDTO.getPhoneNumber());
        updated.setCountry(userDTO.getCountry());
        updated.setEmail(userDTO.getEmail());
        return userRepository.save(updated);
    }

    @Override
    public void sendEmailResponseDeleteReq(User user, String response) {
        String subject = "Response on delete request";
        String sender = "Fishing Booker";
        String content = response;

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        try {
            helper.setFrom("fishingbookernsm@hotmail.com", sender);
            helper.setTo(user.getEmail());
            helper.setSubject(subject);
            helper.setText(content, true);

        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        mailSender.send(message);
    }

    public User findUserById(Integer id) {
        User user = userRepository.getById(id);return user;
    }

    @Override
    public UserDTO findUserByIdDto(Integer id) {
        User user = userRepository.getById(id);
        UserDTO userDTO = new UserDTO(0,user.getId(), user.getUsername(), findUserRolename(id), user.getName(), user.getSurname(), user.getAddress(), user.getCity(), user.getCountry(), user.getPhoneNumber(), user.getEmail());
        return userDTO;
    }

    @Override
    public List<EntityDTO> findUserEntities(Integer userId) throws IOException {
        List<EntityDTO> dtos = new ArrayList<>();
        String role = findUserRolename(userId);
        if (role.equals("ROLE_INSTRUCTOR")) {
            dtos = setAdventures(userId);
        } else if(role.equals("ROLE_SHIPOWNER")) {
            dtos = setShips(userId);
        } else if(role.equals("ROLE_LODGEOWNER")) {
            dtos = setLodges(userId);
        }
        return dtos;
    }

    private List<EntityDTO> setAdventures(Integer userId) throws IOException {
        List<EntityDTO> dtos = new ArrayList<>();
        List<Adventure> adventures = adventureRepository.findInstructorAdventures(userId);
        Integer index = 1;
        for (Adventure a: adventures) {
            Location location = locationRepository.getById(a.getLocation().getId());
            EntityDTO dto = new EntityDTO(index++, a.getId(), userId, a.getName(),
                    location.getAddress() + ", " + location.getCity() + ", " + location.getCountry(),
                    a.getDescription(), a.getCancelConditions(), a.getAverageGrade(), a.getMaxPersons(), imageService.getEntityProfileImage(a.getId()));
            dtos.add(dto);
        }
        return dtos;
    }

    private List<EntityDTO> setLodges(Integer userId) throws IOException {
        List<EntityDTO> dtos = new ArrayList<>();
        List<Lodge> lodges = lodgeRepository.findOwnerLodges(userId);
        Integer index = 1;
        for (Lodge l : lodges) {
            Location location = locationRepository.getById(l.getLocation().getId());
            EntityDTO dto = new EntityDTO(index++, l.getId(), userId, l.getName(), location.getAddress() + ", " + location.getCity() + ", " + location.getCountry(), l.getDescription(), l.getCancelConditions(), l.getAverageGrade(), l.getMaxPersons(), imageService.getEntityProfileImage(l.getId()));
            dtos.add(dto);
        }
        return dtos;
    }

    private List<EntityDTO> setShips(Integer userId) throws IOException {
        List<EntityDTO> dtos = new ArrayList<>();
        List<Ship> ships = shipRepository.findOwnerShips(userId);
        Integer index = 1;
        for (Ship s : ships) {
            Location location = locationRepository.getById(s.getLocation().getId());
            EntityDTO dto = new EntityDTO(index++, s.getId(), userId, s.getName(), location.getAddress() + ", " + location.getCity() + ", " + location.getCountry(), s.getDescription(), s.getCancelConditions(), s.getAverageGrade(), s.getMaxPersons(), imageService.getEntityProfileImage(s.getId()));
            dtos.add(dto);
        }
        return dtos;
    }

    @Override
    public void deleteUser(Integer userId) {
        deleteUserEntities(userId);
        userRepository.deleteById(userId);
    }

    private void deleteUserEntities(Integer userId) {
        String rolename = findUserRolename(userId);
        if (rolename.equals("ROLE_INSTRUCTOR")) {
            List<Adventure> adventures = adventureRepository.findInstructorAdventures(userId);
            for (Adventure a: adventures) {
                adventureRepository.deleteAdventure(a.getId());
            }
        } else if(rolename.equals("ROLE_SHIPOWNER")) {
            List<Ship> ships = shipRepository.findOwnerShips(userId);
            for (Ship s: ships) {
                shipRepository.deleteShip(s.getId());
            }
        } else if (rolename.equals("ROLE_LODGEOWNER")) {
            List<Lodge> lodges = lodgeRepository.findOwnerLodges(userId);
            for (Lodge l : lodges) {
                lodgeRepository.deleteLodge(l.getId());
            }
        }
    }

    @Override
    public void deleteUserEntity(Integer entityId, Integer userId) {
        String rolename = findUserRolename(userId);
        if (rolename.equals("ROLE_INSTRUCTOR")) {
            adventureRepository.deleteAdventure(entityId);
        } else if(rolename.equals("ROLE_SHIPOWNER")) {
            shipRepository.deleteShip(entityId);
        } else if (rolename.equals("ROLE_LODGEOWNER")) {
            lodgeRepository.deleteLodge(entityId);
        }
    }

    @Override
    public void sendEmailCompliantResponse(User user, String response) {
        String subject = "Response on compliant";
        String sender = "Fishing Booker";
        String content = response;

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        try {
            helper.setFrom("fishingbookernsm@hotmail.com", sender);
            helper.setTo(user.getEmail());
            helper.setSubject(subject);
            helper.setText(content, true);

        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        mailSender.send(message);
    }

    @Override
    public void sendEmailApprovedComment(User user, RatingInfoDTO response) {
        String subject = "Comment approval";
        String sender = "Fishing Booker";
        String content ="<p>Dear " + user.getName() + " " + user.getSurname() + ", <p>";
        content += "<p>We are sending you comment that we approved for you entity: </p>";
        content += "<p>------------------------------------------------------</p>";
        content += "<p><b>Client: </b>" + response.getClientName() + "</p>";
        content += "<p><b>Your entity: </b>" + response.getEntityName() + "</p>";
        content += "<p><b>Content: </b>" + response.getComment() + "</p>";
        content += "<p>------------------------------------------------------</p>";
        content += "<p>If you have any complaints, please contact us! <br> Your,<br>Fishing Booker</p>";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        try {
            helper.setFrom("fishingbookernsm@hotmail.com", sender);
            helper.setTo(user.getEmail());
            helper.setSubject(subject);
            helper.setText(content, true);

        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        mailSender.send(message);
    }

    @Override
    public void sendEmailPenaltyGiven(User user, Report report) {
        String subject = "Notification about penalty approval";
        String sender = "Fishing Booker";
        String content ="<p>Dear users, <p>";
        content += "<p>We inform you that penalty request is approved. </p>";
        content += "<p>"+ report.getReservation().getClient().getName() + " " + report.getReservation().getClient().getSurname() + " is assigned with penalty.</p>";
        content += "<p>----------------------------------------------------------------------------------------------------------------------</p>";
        content += "<p><b>Demanded by: </b>" + report.getReservation().getReservationEntity().getOwner().getName() + "(owner)</p>";
        content += "<p>----------------------------------------------------------------------------------------------------------------------</p>";
        content += "<p>If you have any complaints, please contact us! <br> Your,<br>Fishing Booker</p>";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        try {
            helper.setFrom("fishingbookernsm@hotmail.com", sender);
            helper.setTo(user.getEmail());
            helper.setSubject(subject);
            helper.setText(content, true);

        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        mailSender.send(message);
    }

    @Override
    public User doesExist(Integer id) {
        User found = userRepository.getById(id);
        return found;
    }
}
