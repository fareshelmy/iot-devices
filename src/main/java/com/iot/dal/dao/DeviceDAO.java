package com.iot.dal.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iot.dal.entity.Device;

@Repository
public interface DeviceDAO extends JpaRepository<Device, Integer>{

	List<Device> findBySimStatus(String status);

	List<Device> findBySimStatusOrderBySimCountryName(String status);
}
