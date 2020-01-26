package com.charter.chartercodingtest.service;

import com.charter.chartercodingtest.model.CustomerRewards;
import com.charter.chartercodingtest.model.Rewards;

import java.util.List;

public interface RewardsService {
    void calculateRewardPoints(List<Rewards> list);
    CustomerRewards getCustomerRewardPoints(String customerName);
}
