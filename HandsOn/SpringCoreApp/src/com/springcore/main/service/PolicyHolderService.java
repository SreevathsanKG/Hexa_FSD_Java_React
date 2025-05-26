package com.springcore.main.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springcore.main.dao.PolicyHolderDao;
import com.springcore.main.model.Address;
import com.springcore.main.model.PolicyHolder;

@Service
public class PolicyHolderService {
	
	@Autowired
	PolicyHolderDao policyHolderDao;

	public PolicyHolderService(PolicyHolderDao policyHolderDao) {
		this.policyHolderDao = policyHolderDao;
	}
	
	public void insert(PolicyHolder policyHolder, Address address) {
		int id = (int)(Math.random()*10000);
		address.setId(id);
		policyHolderDao.insertAddress(address);
		policyHolder.setAddress(address);
		policyHolderDao.insert(policyHolder);
	}
	
	public List<PolicyHolder> getAllWithAddress(){
		List<Map<String,Object>> list = policyHolderDao.getAllWithAddress();
		List<PolicyHolder> listP = new ArrayList<>();
		for(Map<String,Object> map : list) {
			PolicyHolder ph = new PolicyHolder();
			ph.setId((int)map.get("id"));
			ph.setName((String)map.get("name"));
			ph.setPanNo((String)map.get("panNo"));
			Address address = new Address();
			address.setStreet((String)map.get("street"));
			address.setCity((String)map.get("city"));
			address.setState((String)map.get("state"));
			ph.setAddress(address);
			listP.add(ph);
		}
		return listP;
	}
	
	public List<PolicyHolder> getAllWithAddressv2(){
		return policyHolderDao.getAllWithAddressv2();
	}
}
