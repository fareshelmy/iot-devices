package com.iot.test;

import static com.iot.test.DeviceRepositoryTest.ACTIVE;
import static com.iot.test.DeviceRepositoryTest.BLOCKED;
import static com.iot.test.DeviceRepositoryTest.WAITING;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.iot.dal.dao.DeviceDAO;
import com.iot.dal.entity.Device;
import com.iot.dal.entity.Sim;
import com.iot.dto.DeviceDTO;
import com.iot.dto.Status;
import com.iot.service.DeviceService;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
class DeviceServiceTest {
	
	@Autowired
	DeviceService deviceService;
	
	@MockBean
	DeviceDAO deviceDAO;
	
	@Test
	@SuppressWarnings("serial")
	void testGetWaitingDevices() {
		Device device1 = new Device(1, "device1", new Sim(1,"01", "country1", WAITING));
		Device device2 = new Device(2, "device2", new Sim(2,"02", "country2", WAITING));
		
		when(deviceDAO.findBySimStatus(WAITING))
		.thenReturn(new ArrayList<Device>() {
			{
				add(device1);
				add(device2);
			}
		});
		
		List<DeviceDTO> waitingDevices = deviceService.getDevicesByStatus(Status.WAITING_FOR_ACTIVATION);
		assertEquals("devices list size doesn't match", 2, waitingDevices.size());
		for (DeviceDTO device : waitingDevices) {
			assertEquals("Device status is not as expected.", Status.getStatus(WAITING), device.getSim().getStatus());
		}
	}
	
	@Test
	void testUpdateDeviceStatus() {
		Device device = new Device(1, "device1", new Sim(1,"01", "country1", BLOCKED));
		when(deviceDAO.findById(anyInt())).thenReturn(Optional.of(device));
		
		Device updatedDevice = new Device(1, "device1", new Sim(1,"01", "country1", ACTIVE));
		when(deviceDAO.save(updatedDevice)).thenReturn(updatedDevice);
		
		DeviceDTO updatedDeviceDTO = deviceService.updateDeviceStatus(1, Status.ACTIVE);
		assertEquals("Device status is not as expected.", Status.getStatus(ACTIVE), updatedDeviceDTO.getSim().getStatus());
	}
	
	@Test
	@SuppressWarnings("serial")
	void testGetAvailableDevices() {
		Device device1 = new Device(1, "device1", new Sim(1,"01", "country1", ACTIVE));
		Device device2 = new Device(2, "device4", new Sim(2,"02", "country2", ACTIVE));
		
		when(deviceDAO.findBySimStatusOrderBySimCountryName(ACTIVE))
		.thenReturn(new ArrayList<Device>() {
			{
				add(device1);
				add(device2);
			}
		});
		
		List<DeviceDTO> availableDevices = deviceService.getAvailableDevicesOrdered();
		assertEquals("devices list size doesn't match", 2, availableDevices.size());
		
		List<String> countries = new ArrayList<String>();
		for (DeviceDTO device : availableDevices) {
			assertEquals("Device status is not as expected.", Status.getStatus(ACTIVE), device.getSim().getStatus());
			countries.add(device.getSim().getCountryName());
		}
		
		assertEquals("Devices are not sorted correctly.", countries.stream().sorted().collect(Collectors.toList()), countries);
	}
}
