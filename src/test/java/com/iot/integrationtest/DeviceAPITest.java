package com.iot.integrationtest;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.iot.dto.DeviceDTO;
import com.iot.dto.Status;

class DeviceAPITest extends AbstractTest {

	@Test
	void testGetWaitingDevices() throws Exception {
		
		String uri = "/devices/waiting";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		
		String content = mvcResult.getResponse().getContentAsString();
		DeviceDTO[] waitingDevices = mapFromJson(content, DeviceDTO[].class);
		assertEquals("devices list size doesn't match", 2, waitingDevices.length);
		
		for (DeviceDTO device : waitingDevices) {
			assertEquals("Device status is not as expected.", Status.WAITING_FOR_ACTIVATION, device.getSim().getStatus());
		}
	}
	
	@Test
	void testUpdateDeviceStatus() throws Exception {
		
		String uri = "/devices/3/updateStatus/blocked";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		
		String content = mvcResult.getResponse().getContentAsString();
		DeviceDTO updatedDevice = mapFromJson(content, DeviceDTO.class);
		assertEquals("Device status is not as expected.", Status.BLOCKED, updatedDevice.getSim().getStatus());
	}

	@Test
	void testGetAvailableDevicesOrdered() throws Exception {
		
		String uri = "/devices/available";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		
		String content = mvcResult.getResponse().getContentAsString();
		DeviceDTO[] availableDevices = mapFromJson(content, DeviceDTO[].class);
		assertEquals("devices list size doesn't match", 2, availableDevices.length);

		List<String> countries = new ArrayList<String>();
		for (DeviceDTO device : availableDevices) {
			assertEquals("Device status is not as expected.", Status.ACTIVE, device.getSim().getStatus());
			countries.add(device.getSim().getCountryName());
		}
		
		assertEquals("Devices are not sorted correctly.", countries.stream().sorted().collect(Collectors.toList()), countries);
	}
}
