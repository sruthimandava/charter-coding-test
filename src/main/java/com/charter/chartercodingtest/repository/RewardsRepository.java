package com.charter.chartercodingtest.repository;

import com.charter.chartercodingtest.model.Rewards;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RewardsRepository extends CrudRepository<Rewards, Long> {
    List<Rewards> findByCustomerName(String customerName);
}
