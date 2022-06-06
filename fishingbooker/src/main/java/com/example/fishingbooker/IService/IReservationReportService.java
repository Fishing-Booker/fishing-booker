package com.example.fishingbooker.IService;

public interface IReservationReportService {

    int[] getWeeklyReport(int year, int ownerId);

    int[] getReportForLastFiveYears(int year, int ownerId);

    int[] getWeeklyReport(int month, int year, Integer ownerId);

    double[] getSalaryYearReport(int year, Integer ownerId);

}
