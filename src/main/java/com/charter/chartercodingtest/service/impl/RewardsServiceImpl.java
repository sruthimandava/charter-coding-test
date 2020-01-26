package com.charter.chartercodingtest.service.impl;

import com.charter.chartercodingtest.model.CustomerRewards;
import com.charter.chartercodingtest.model.Rewards;
import com.charter.chartercodingtest.model.RewardsDTO;
import com.charter.chartercodingtest.repository.RewardsRepository;
import com.charter.chartercodingtest.service.RewardsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RewardsServiceImpl implements RewardsService {

    private RewardsRepository repository;

    @Autowired
    public RewardsServiceImpl(RewardsRepository repository) {
        this.repository = repository;
    }

    @Override
    public void calculateRewardPoints(List<Rewards> list) {
        for (Rewards r : list) {
            Integer points = calculatePoints(r);
            r.setPoints(points);
            repository.save(r);
        }
    }

    private Integer calculatePoints(Rewards r) {
        Integer points = 0;
        Integer amount = r.getPurchaseAmount();
        if (amount <= 50) {
            return points;
        }
        if (amount <= 100) {
            points += (amount - 50);
        } else {
            points += 50;
            points += (amount % 100) * 2;
        }
        return points;
    }

    @Override
    public CustomerRewards getCustomerRewardPoints(String customerName) {
        List<Rewards> rewards = repository.findByCustomerName(customerName);
        Map<Object, Long> months = rewards.stream()
                .collect(Collectors.groupingBy(element -> element.getCreatedDate().getMonth(), Collectors.summingLong(element -> element.getPoints())));

        List<RewardsDTO> rewardsDTO = new ArrayList<RewardsDTO>();
        Long total = 0L;
        for (Map.Entry<Object, Long> entry : months.entrySet()) {
            RewardsDTO dto = new RewardsDTO();
            dto.setMonth(((Integer) entry.getKey() + 1));
            Long monthTotal = entry.getValue();
            total += monthTotal;
            dto.setMonthPoints(monthTotal);

            rewardsDTO.add(dto);
        }

        CustomerRewards customerDetails = new CustomerRewards();
        customerDetails.setTotalPoints(total);
        customerDetails.setRewards(rewardsDTO);
        return customerDetails;
    }

}
