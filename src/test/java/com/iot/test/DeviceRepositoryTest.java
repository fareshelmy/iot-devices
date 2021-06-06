package com.iot.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.iot.dal.dao.DeviceDAO;
import com.iot.dal.entity.Device;

@RunWith(SpringRunner.class)
@TestPropertySource(locations = "classpath:application-test.properties")
@DataJpaTest
@ActiveProfiles("test")
class DeviceRepositoryTest {

	static final String ACTIVE = "active";
	static final String WAITING = "waiting";
	static final String BLOCKED = "blocked";
	
	@Autowired
	private DeviceDAO deviceDAO;
	
	@Test
	void testGetWaitingDevices() {
		List<Device> waitingDevices = deviceDAO.findBySimStatus(WAITING);
		assertEquals("devices list size doesn't match", 2, waitingDevices.size());
		for (Device device : waitingDevices) {
			assertEquals("Device status is not as expected.", WAITING, device.getSim().getStatus());
		}
	}
	
	@Test
	void testGetAvailableDevicesOrderedByCountryName() {
		List<Device> availableDevices = deviceDAO.findBySimStatusOrderBySimCountryName(ACTIVE);
		assertEquals("devices list size doesn't match", 2, availableDevices.size());
		
		List<String> countries = new ArrayList<String>();
		for (Device device : availableDevices) {
			assertEquals("Device status is not as expected.", ACTIVE, device.getSim().getStatus());
			countries.add(device.getSim().getCountryName());
		}
		
		assertEquals("Devices are not sorted correctly.", countries.stream().sorted().collect(Collectors.toList()), countries);
	}
}
