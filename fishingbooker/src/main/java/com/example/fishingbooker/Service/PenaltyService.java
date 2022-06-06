package com.example.fishingbooker.Service;

import com.example.fishingbooker.IRepository.IPenaltyRepository;
import com.example.fishingbooker.IRepository.IReservationRepository;
import com.example.fishingbooker.IService.IPenaltyService;
import com.example.fishingbooker.Model.Penalty;
import com.example.fishingbooker.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.dialect.lock.OptimisticEntityLockException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
public class PenaltyService implements IPenaltyService {

    @Autowired
    IPenaltyRepository penaltyRepository;

    @Autowired
    IReservationRepository reservationRepository;

    Integer SKIPPED_RESERVATION_PENALTY = 1;

    protected final Log logger = LogFactory.getLog(getClass());

    @Override
    @Transactional
    public void addSkippedReservationPenalty(Integer reservationId) {
        try {
            User client = reservationRepository.findReservationById(reservationId).getClient();
            Penalty penalty = penaltyRepository.findClientPenalties(client.getId());
            if(penalty == null){
                penaltyRepository.save(new Penalty(client, SKIPPED_RESERVATION_PENALTY));
            } else {
                Integer penalties = penalty.getPenalties() + SKIPPED_RESERVATION_PENALTY;
                penaltyRepository.updatePenalty(penalties, client.getId());
            }
        } catch (OptimisticEntityLockException e) {
            logger.debug("Optimistic lock exception");
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
