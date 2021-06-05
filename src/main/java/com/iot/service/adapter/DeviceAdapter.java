package com.iot.service.adapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iot.dal.entity.Device;
import com.iot.dal.entity.Sim;
import com.iot.dto.DeviceDTO;
import com.iot.dto.SIMDTO;

@Service
public class DeviceAdapter implements Adapter<Device, DeviceDTO>{

	@Autowired
	private SIMAdapter simAdapter;
	
	public DeviceDTO toDTO(Device device) {
		SIMDTO simDTO = simAdapter.toDTO(device.getSim());
		return new DeviceDTO(device.getId(), device.getName(), simDTO);
	}

	@Override
	public Device toEntity(DeviceDTO device) {
		Sim sim = simAdapter.toEntity(device.getSim());
		return new Device(device.getId(), device.getName(), sim);
	}
}
