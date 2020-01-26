package com.charter.chartercodingtest.controller;

import com.charter.chartercodingtest.model.CustomerRewards;
import com.charter.chartercodingtest.model.Rewards;
import com.charter.chartercodingtest.service.RewardsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@RestController("/rewards")
public class RewardsCalculator {

    private RewardsService rewardsService;

    @Autowired
    public RewardsCalculator(RewardsService rewardsService) {
        this.rewardsService = rewardsService;
    }

    @GetMapping("/createDataSet")
    public void createDateSet() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Rewards r1 = new Rewards();
        r1.setCustomerName("cust1");
        r1.setPurchaseAmount(120);
        r1.setCreatedDate(sdf.parse("10/06/2019"));

        Rewards r2 = new Rewards();
        r2.setCustomerName("cust1");
        r2.setPurchaseAmount(80);
        r2.setCreatedDate(sdf.parse("12/07/2019"));

        Rewards r3 = new Rewards();
        r3.setCustomerName("cust1");
        r3.setPurchaseAmount(120);
        r3.setCreatedDate(sdf.parse("10/08/2019"));

        Rewards r4 = new Rewards();
        r4.setCustomerName("cust1");
        r4.setPurchaseAmount(90);
        r4.setCreatedDate(sdf.parse("11/08/2019"));

        // 2nd Customer
        Rewards c1 = new Rewards();
        c1.setCustomerName("cust2");
        c1.setPurchaseAmount(120);
        c1.setCreatedDate(sdf.parse("13/07/2019"));

        Rewards c2 = new Rewards();
        c2.setCustomerName("cust2");
        c2.setPurchaseAmount(110);
        c2.setCreatedDate(sdf.parse("12/08/2019"));

        List<Rewards> list = new ArrayList<>();
        list.add(r1);
        list.add(r2);
        list.add(r3);
        list.add(r4);
        list.add(c1);
        list.add(c2);

        rewardsService.calculateRewardPoints(list);
    }

    @GetMapping("/getRewardPoints")
    public CustomerRewards getRewardPoints(String customerName) {
        return rewardsService.getCustomerRewardPoints(customerName);
    }
}
