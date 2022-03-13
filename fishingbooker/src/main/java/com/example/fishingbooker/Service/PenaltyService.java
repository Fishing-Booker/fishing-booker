package com.example.fishingbooker.Service;

import com.example.fishingbooker.IRepository.IPenaltyRepository;
import com.example.fishingbooker.IRepository.IReservationRepository;
import com.example.fishingbooker.IService.IPenaltyService;
import com.example.fishingbooker.Model.Penalty;
import com.example.fishingbooker.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class PenaltyService implements IPenaltyService {

    @Autowired
    IPenaltyRepository penaltyRepository;

    @Autowired
    IReservationRepository reservationRepository;

    Integer SKIPPED_RESERVATION_PENALTY = 1;

    @Override
    public void addSkippedReservationPenalty(Integer reservationId) {
        User client = reservationRepository.findReservationById(reservationId).getClient();
        Penalty penalty = penaltyRepository.findClientPenalties(client.getId());
        if(penalty == null){
            penaltyRepository.save(new Penalty(client, SKIPPED_RESERVATION_PENALTY));
        } else {
            Integer penalties = penalty.getPenalties() + SKIPPED_RESERVATION_PENALTY;
            penaltyRepository.updatePenalty(penalties, client.getId());
        }
    }

    @Override
    public Integer findClientPenalties(Integer clientId) {
        List<Penalty> penalties = penaltyRepository.findAll();
        for (Penalty penalty : penalties) {
            if (Objects.equals(penalty.getClient().getId(), clientId)) {
                return penaltyRepository.findClientPenalties(clientId).getPenalties();
            }
        }
        return 0;
    }

    @Override
    public void annulPenalties(Integer clientId) {
        penaltyRepository.annulPenalties(clientId);
    }
}
