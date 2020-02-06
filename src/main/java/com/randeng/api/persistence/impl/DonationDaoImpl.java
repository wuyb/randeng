package com.randeng.api.persistence.impl;

import com.randeng.api.model.Donation;
import com.randeng.api.persistence.DonationDao;
import org.springframework.stereotype.Repository;

@Repository("donationDaoImpl")
public class DonationDaoImpl extends BaseDaoImpl<Donation, Long> implements DonationDao {
}
