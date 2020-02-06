package com.randeng.api.service.impl;

import com.randeng.api.model.Donation;
import com.randeng.api.model.Expense;
import com.randeng.api.service.DonationService;
import com.randeng.api.service.ExpenseService;
import org.springframework.stereotype.Service;

@Service("donationServiceImpl")
public class DonationServiceImpl extends BaseServiceImpl<Donation, Long> implements DonationService {
}
