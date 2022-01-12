package com.example.fishingbooker.IRepository;

import com.example.fishingbooker.Model.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IReportRepository extends JpaRepository<Report, Integer> {
}
