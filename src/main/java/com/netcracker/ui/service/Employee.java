/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service;

import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Artem
 */
public class Employee {
	private Company company;
	public Company getCompany() {
		return company;
	}
	@Autowired
	void setCompany(Company company) {
		this.company = company;
	}
} 