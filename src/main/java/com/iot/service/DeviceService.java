package com.iot.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iot.dal.dao.DeviceDAO;
import com.iot.dal.entity.Device;
import com.iot.dto.DeviceDTO;
import com.iot.dto.Status;
import com.iot.service.adapter.DeviceAdapter;

@Service
public class DeviceService {

	@Autowired
	private DeviceDAO deviceDAO;
	@Autowired
	private DeviceAdapter deviceAdapter;
	
	public List<DeviceDTO> getDevicesByStatus(Status status) {
		List<Device> devices = deviceDAO.findBySimStatus(status.getStatus());
		return devices.stream().map(device -> deviceAdapter.toDTO(device)).collect(Collectors.toList());
	}

	public DeviceDTO updateDeviceStatus(Integer id, Status status) {
		Optional<Device> optionalDevice = deviceDAO.findById(id);
		if (!optionalDevice.isPresent()) throw new IllegalArgumentException("Device id is not found!");
		
		Device device = optionalDevice.get();
		device.getSim().setStatus(status.getStatus());
		deviceDAO.save(device);
		return deviceAdapter.toDTO(device);
	}

	public List<DeviceDTO> getAvailableDevicesOrdered() {
		List<Device> devices = deviceDAO.findBySimStatusOrderBySimCountryName(Status.ACTIVE.getStatus());
		return devices.stream().map(device -> deviceAdapter.toDTO(device)).collect(Collectors.toList());
	}
}
