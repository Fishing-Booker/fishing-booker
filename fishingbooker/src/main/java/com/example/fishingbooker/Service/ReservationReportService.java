package com.example.fishingbooker.Service;

import com.example.fishingbooker.IRepository.IReservationRepository;
import com.example.fishingbooker.IService.IReservationReportService;
import com.example.fishingbooker.Model.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ReservationReportService implements IReservationReportService {

    @Autowired
    private IReservationRepository reservationRepository;

    @Override
    public int[] getWeeklyReport(int year, int ownerId) {   //months of year
        int[] monthlyReservations = new int[12];
        for(int i = 1; i <= 12; i++){
            int reservationNumber = reservationRepository.getReservationNumberByMonth(i, year, ownerId);
            monthlyReservations[i-1] = reservationNumber;
        }
        return monthlyReservations;
    }

    @Override
    public int[] getReportForLastFiveYears(int year, int ownerId) {  //last 5 years
        int[] yearlyReservations = new int[5];
        int index = 0;
        int startYear = year - 4;
        for (int i = startYear; i <= year; i++) {
            yearlyReservations[index] = reservationRepository.getReservationNumberByYear(i, ownerId);
            index++;
        }
        return yearlyReservations;
    }

    @Override
    public int[] getWeeklyReport(int month, int year, Integer ownerId) {
        int[] reservations = new int[6];
        int startDate = 1;
        int endDate = 1;
        for(int i = 1; i <= 6; i++) {
            if(i == 1) {    //first week
                endDate = getEndDateForFirstWeek(month, year);
            } else {
                if(i == 5 && endDate + 7 > 29) {    //last week
                    endDate = YearMonth.of(year, month).lengthOfMonth();
                    continue;
                } else if(i == 6) {
                    endDate = YearMonth.of(year, month).lengthOfMonth();
                } else {
                    startDate = endDate + 1;
                    endDate = startDate + 6;
                }
            }
            reservations[i-1] = reservationRepository.getReservationNumberBetweenDates(startDate, endDate, month, year, ownerId);
        }

        return reservations;
    }

    private int getEndDateForFirstWeek(int month, int year) {
        int firstDayOfMonth = new Date(year, month-1, 1).getDay();
        if(firstDayOfMonth == 1)   //if Sunday
            return 1;
        if(firstDayOfMonth == 2)    //if Monday
            return 7;
        if(firstDayOfMonth == 3)     //if Tuesday
            return 6;
        if(firstDayOfMonth == 4)    //if Wednesday
            return 5;
        if(firstDayOfMonth == 5)    //if Thursday
            return 4;
        if(firstDayOfMonth == 6)    //if Friday
            return 3;

        return 2;   //if Saturday
    }

    public double[] getSalaryYearReport(int year, Integer ownerId) {    //for last 5 years
        double[] yearlySalaries = new double[5];
        int index = 0;
        int startYear = year - 4;
        for (int i = startYear; i <= year; i++) {
            yearlySalaries[index] = reservationRepository.getSalaryByYear(i, ownerId);
            index++;
        }
        return yearlySalaries;
    }

}
