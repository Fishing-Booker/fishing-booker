package com.example.fishingbooker.service;

import com.example.fishingbooker.DTO.DeleteAccountRequestDTO;
import com.example.fishingbooker.DTO.RatingDTO;
import com.example.fishingbooker.DTO.RatingInfoDTO;
import com.example.fishingbooker.IRepository.IRatingRepository;
import com.example.fishingbooker.Model.*;
import com.example.fishingbooker.Service.RatingService;
import com.example.fishingbooker.Service.ReservationEntityService;
import com.example.fishingbooker.Service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.example.fishingbooker.constants.AccountRequestConstants.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class RatingServiceTest {
    /*@Mock
    private IRatingRepository ratingRepositoryMock;

    @Mock
    private UserService userServiceMock;

    @Mock
    private ReservationEntityService reservationEntityServiceMock;

    @InjectMocks
    private RatingService ratingService;

    @Test
    public void disapproveRatingTest(){
        Rating rating = new Rating(1,5, "", false, false);

        doNothing().when(ratingRepositoryMock).disapproveRating(rating.getId());
        when(ratingRepositoryMock.findById(rating.getId())).thenReturn(java.util.Optional.of(new Rating(1, 5, "", false, true)));

        Rating r = ratingService.disapproveRating(rating.getId());

        assertThat(r.isDispproved()).isTrue();

    }

    @Test
    public void approveRatingTest(){
        User owner = new User(1, "user", "user123",
                "User", "User", "user@gmail.com", "address 123",
                "city", "county", "phone",
                Arrays.asList(new Role(1, "INSTRUCTOR")));

        User client = new User(2, "user", "user123",
                "User", "User", "user@gmail.com", "address 123",
                "city", "county", "phone",
                Arrays.asList(new Role(1, "CLIENT")));

        ReservationEntity adventure = new ReservationEntity(1, owner, "Adventure",
                new Location(1, 253.5, 12.0, "address", "city", "country"),
                "description", "rules", 2, 4.5,
                10);

        Rating rating = new Rating(1, 1, adventure, 5, "", false, false, client);
        RatingInfoDTO dto = new RatingInfoDTO(rating.getId(), rating.getComment(), rating.getGrade(), rating.getReservationEntity().getName(), client.getName());

        doNothing().when(ratingRepositoryMock).approveRating(rating.getId());
        when(ratingRepositoryMock.findById(rating.getId())).thenReturn(java.util.Optional.of(new Rating(1, 1, adventure, 5, "", true, false, client)));
        doNothing().when(reservationEntityServiceMock).updateEntityAverageGrade(rating.getReservationEntity().getId(), 0);
        when(ratingRepositoryMock.findAll()).thenReturn(Arrays.asList(rating));

        Rating r = ratingService.approveRating(dto);

        assertThat(r.isApproved()).isTrue();
    }

    @Test
    public void testFindAll(){
        User owner = new User(1, "user", "user123",
                "User", "User", "user@gmail.com", "address 123",
                "city", "county", "phone",
                Arrays.asList(new Role(1, "INSTRUCTOR")));

        User client = new User(2, "user", "user123",
                "User", "User", "user@gmail.com", "address 123",
                "city", "county", "phone",
                Arrays.asList(new Role(1, "CLIENT")));

        ReservationEntity adventure = new ReservationEntity(1, owner, "Adventure",
                new Location(1, 253.5, 12.0, "address", "city", "country"),
                "description", "rules", 2, 4.5,
                10);

        Rating rating = new Rating(1, 1, adventure, 5, "", false, false, client);
        when(ratingRepositoryMock.findAll()).thenReturn(Arrays.asList(rating));

        List<RatingInfoDTO> ratings = ratingService.findAll();

        assertThat(ratings).hasSize(1);

    }

    @Test
    public void addRatingTest() {
        User client = new User(1, "petar", "petar123",
                "Petar", "Petrovic", "petar@gmail.com", "Bulvear oslobodjenja 123",
                "Novi Sad", "Srbija", "0612345678",
                Arrays.asList(new Role(1, "CLIENT")));

        ReservationEntity adventure = new ReservationEntity(1, new User(), "Adventure",
                new Location(1, 253.5, 12.0, "address", "city", "country"),
                "description", "rules", 2, 4.5,
                10);

        RatingDTO ratingDTO = new RatingDTO(1, "Sve je super!", 5,  1, 2);
        Rating rating = new Rating(1, 1, adventure, 5, ratingDTO.getComment(), false, false, client);

        when(ratingRepositoryMock.save(rating)).thenReturn(rating);

        //assertThat(ratingService.findAll()).hasSize(1);
        assertThat(ratingService.addRating(ratingDTO)).isNull();
    }

    @Test
    public void calculateGradeTest() {
        ReservationEntity adventure = new ReservationEntity(1, new User(), "Adventure",
                new Location(1, 253.5, 12.0, "address", "city", "country"),
                "description", "rules", 2, 4.5,
                10);

        Rating rating1 = new Rating(1, 1, adventure, 5, "Sve je super!", false, false, new User());
        Rating rating2 = new Rating(2, 1, adventure, 4, "OK", false, false, new User());
        Rating rating3 = new Rating(3, 1, adventure, 3, "Moze to i bolje", false, false, new User());

        when(ratingRepositoryMock.findAll()).thenReturn(Arrays.asList(rating1, rating2, rating3));

        assertThat(ratingService.calculateGrade(adventure.getId())).isEqualTo(4);

    }*/
}
