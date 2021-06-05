package com.iot.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iot.dto.DeviceDTO;
import com.iot.dto.Status;
import com.iot.service.DeviceService;

/**
 * Rest Controller for IOT Devices
 */
@RestController
@RequestMapping("/devices")
public class DeviceAPI {

	@Autowired
	DeviceService deviceService;
	
	/**
	 * @return List<DeviceDTO> list of devices waiting for activation
	 */
    @GetMapping("/waiting")
    public List<DeviceDTO> getWaitingForActivationDevices() {
        return deviceService.getDevicesByStatus(Status.WAITING_FOR_ACTIVATION);
    }
    
    /**
     * 
     * @param deviceId for the device to be updated
     * @param deviceStatus that to be changed for this device
     * @return the device after being updated
     */
    @PutMapping("{deviceId}/updateStatus/{deviceStatus}")
    public DeviceDTO updateDeviceStatus(@PathVariable Integer deviceId, @PathVariable String deviceStatus) {
        return deviceService.updateDeviceStatus(deviceId, Status.getStatus(deviceStatus));
    }
    
    /**
     * 
     * @return List<DeviceDTO> list of available devices for sale
     */
    @GetMapping("/available")
    public List<DeviceDTO> getAvailableDevices() {
        return deviceService.getAvailableDevicesOrdered();
    }
}
