//package com.example.fishingbooker.service;
//
//import com.example.fishingbooker.IRepository.ILocationRepository;
//import com.example.fishingbooker.Model.Location;
//import com.example.fishingbooker.Service.LocationService;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import java.util.List;
//import java.util.Objects;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.Mockito.when;
//
//@SpringBootTest
//@ExtendWith(SpringExtension.class)
//public class LocationServiceTest {
//
//    @Mock
//    private ILocationRepository locationRepository;
//
//    @Mock
//    private Location locationMock;
//
//    @InjectMocks
//    private LocationService locationService;
//
//    @Test
//    public void getByIdTest() {
//        when(locationRepository.findLocationById(1)).thenReturn(locationMock);
//
//        Location location = locationService.getLocationById(1);
//
//        Assertions.assertEquals(location, locationMock);
//    }
//
//    @Test
//    public void findByAddressTest() {
//        Location expectedLocation = new Location(1, 40.5, 36.99, "Temerinska 30", "Novi Sad", "Srbija");
//        String address = "Temerinska 30";
//        String city = "Novi Sad";
//        String country = "Srbija";
//
//        when(locationRepository.findByAddress(address, city, country))
//                .thenReturn(expectedLocation);
//
//        Location location = locationService.findByAddress(address, city, country);
//
//        Assertions.assertEquals(location, expectedLocation);
//    }
//
//    private Location getLocationByAddress(String address, String city, String country) {
//        Location location1 = new Location(1, 40.5, 36.99, "Temerinska 30", "Novi Sad", "Srbija");
//        Location location2 = new Location(2, 15.2, 23.55, "Kralja Petra 12", "Novi Sad", "Srbija");
//        List<Location> locations = List.of(location1, location2);
//
//        for(Location l : locations){
//            if(Objects.equals(l.getAddress(), address) && Objects.equals(l.getCity(), city) && Objects.equals(l.getCountry(), country)){
//                return l;
//            }
//        }
//        return null;
//    }
//}
