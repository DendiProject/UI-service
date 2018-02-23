/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netcracker.ui.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author Artem
 */
@Configuration
public class AppConfig {
	@Bean
	public Company getCompany() {
		Company company = new Company();
		company.setCompName("ABCD Ltd");
		company.setLocation("Varanasi");
		return company;
	}
	@Bean
	public Employee getEmployee() {
		return new Employee();
	}
} 