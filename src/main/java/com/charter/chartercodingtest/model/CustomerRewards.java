package com.charter.chartercodingtest.model;

import java.util.List;

public class CustomerRewards {
    private Long totalPoints;
    private List<RewardsDTO> rewards;

    public Long getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(Long totalPoints) {
        this.totalPoints = totalPoints;
    }

    public List<RewardsDTO> getRewards() {
        return rewards;
    }

    public void setRewards(List<RewardsDTO> rewards) {
        this.rewards = rewards;
    }
}
