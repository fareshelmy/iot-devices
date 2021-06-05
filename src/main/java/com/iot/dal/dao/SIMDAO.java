package com.iot.dal.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iot.dal.entity.Sim;

@Repository
public interface SIMDAO extends JpaRepository<Sim, Integer>{

	List<Sim> findByStatus(String status);

	List<Sim> findByCountryName(String countryName);
}
