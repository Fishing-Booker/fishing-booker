package com.example.fishingbooker.Service;

import com.example.fishingbooker.DTO.UserDTO;
import com.example.fishingbooker.IRepository.IUserRepository;
import com.example.fishingbooker.IService.IRoleService;
import com.example.fishingbooker.IService.IUserService;
import com.example.fishingbooker.Model.Role;
import com.example.fishingbooker.Model.User;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService, UserDetailsService {

    @Autowired
    IUserRepository userRepository;

    @Autowired
    IRoleService roleService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public Optional<User> findById(Integer id) {
        Optional<User> user = this.userRepository.findById(id);
        return user;
    }

    @Override
    public User findByUsername(String username) {
        User user = this.userRepository.findByUsername(username);
        return user;
    }

    @Override
    public List<User> findAll() {
        List<User> users = this.userRepository.findAll();
        return users;
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
            helper.setFrom("isafishingbooker@gmail.com", sender);
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

    public boolean verify(String verificationCode) {
        User user = userRepository.findByVerificationCode(verificationCode);
        if (user == null) {
            return false;
        } else {
            userRepository.enable(user.getId());
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
            helper.setFrom("isafishingbooker@gmail.com", sender);
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
        String content ="<p>Dear " + user.getName() + " " + user.getSurname() + ", your request for registration is rejected. We don't need more people in this moment,sorry<p>";
        content += "<p>Thank you for your time<br>Fishing Booker</p>";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        try {
            helper.setFrom("isafishingbooker@gmail.com", sender);
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
}
