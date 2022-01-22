package com.example.fishingbooker.Service;

import com.example.fishingbooker.DTO.SubscriberDTO;
import com.example.fishingbooker.DTO.SubscriptionDTO;
import com.example.fishingbooker.DTO.reservationAction.AddReservationActionDTO;
import com.example.fishingbooker.IRepository.ISubscriberRepository;
import com.example.fishingbooker.IRepository.IUserRepository;
import com.example.fishingbooker.IService.IReservationEntityService;
import com.example.fishingbooker.IService.ISubscriberService;
import com.example.fishingbooker.IService.IUserService;
import com.example.fishingbooker.Mapper.SubscriptionMapper;
import com.example.fishingbooker.Model.ReservationEntity;
import com.example.fishingbooker.Model.Subscriber;
import com.example.fishingbooker.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class SubscriberService implements ISubscriberService {

    @Autowired
    private ISubscriberRepository subscriberRepository;

    @Autowired
    private IUserService userService;

    @Autowired
    private IReservationEntityService reservationEntityService;

    @Autowired
    JavaMailSender mailSender;

    @Override
    public void addSubscriber(SubscriberDTO dto) {
        User user = userService.findUserById(dto.getUserId());
        ReservationEntity entity = reservationEntityService.getEntityById(dto.getEntityId());
        Subscriber subscriber = new Subscriber(user, entity);
        subscriberRepository.save(subscriber);
    }

    @Override
    public Boolean isSubscribed(SubscriberDTO dto) {
        return subscriberRepository.getSubscriber(dto.getEntityId(), dto.getUserId()) != null;
    }

    @Override
    public Boolean unsubscribe(Integer entityId, Integer userId) {
        if (subscriberRepository.getSubscriber(entityId, userId) != null) {
            subscriberRepository.delete(subscriberRepository.getSubscriber(entityId, userId));
            return true;
        } else return false;
    }

    @Override
    public List<SubscriptionDTO> getSubscriptions(Integer id) {
        List<ReservationEntity> entities = subscriberRepository.getSubscriptions(id);
        List<SubscriptionDTO> subscriptions = new ArrayList<>();
        for (ReservationEntity entity : entities) {
            subscriptions.add(SubscriptionMapper.mapToDTO(entity));
        }
        return subscriptions;
    }

    @Override
    public void sendEmailWithActionInfo(AddReservationActionDTO action) {

        for (User client : subscriberRepository.getSubscribers(action.getEntityId())) {
            String subject = "Check our new action!";
            String sender = "Fishing Booker";

            String content ="<p>Dear " + client.getName() + " " + client.getSurname() + ", </p>";
            content += "<p>There is a new action you may want to check:</p>";

            ReservationEntity entity = reservationEntityService.getEntityById(action.getEntityId());
            content += "<p><b>Entity:</b> " + entity.getName() + "<br>";

            String start = new SimpleDateFormat("dd.MM.yyyy.").format(action.getStartDate());
            String end = new SimpleDateFormat("dd.MM.yyyy.").format(action.getEndDate());
            content += "<b>Action period:</b> " + start + " - " + end + "<br>";

            content += "<b>Max persons:</b> " + action.getMaxPersons() + "<br>";

            content += "<b>Regular service:</b> " + action.getRegularService() + "<br>";

            String[] services = action.getAdditionalServices().split("#");
            String additionalServices = "";
            for (String service : services) {
                additionalServices += service;
                additionalServices += ", ";
            }
            additionalServices = additionalServices.substring(0, additionalServices.length()-2);
            content += "<b>Additional services:</b> " + additionalServices + "<br>";

            content += "<b>Price:</b> " + action.getPrice() + "$<br></p>";
            content += "<p>We are waiting for you!<br>Fishing Booker</p>";

            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);

            try {
                helper.setFrom("fishingbookernsm@gmail.com", sender);
                helper.setTo(client.getEmail());
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

    @Override
    public void sendEmailWithActionReservationInfo(Integer clientId) {
        User client = subscriberRepository.getSubscriber(clientId);
        String subject = "Action reservation!";
        String sender = "Fishing Booker";

        String content ="<p>Dear " + client.getName() + " " + client.getSurname() + ", </p>";
        content += "<p>Thank you for choosing us to make a reservation!</p>";
        content += "<p>We are looking forward to see you!<br>Fishing Booker</p>";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        try {
            helper.setFrom("fishingbookernsm@gmail.com", sender);
            helper.setTo(client.getEmail());
            helper.setSubject(subject);
            helper.setText(content, true);

        } catch (MessagingException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        mailSender.send(message);
    }
}
