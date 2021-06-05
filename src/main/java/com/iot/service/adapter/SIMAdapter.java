package com.iot.service.adapter;

import org.springframework.stereotype.Service;

import com.iot.dal.entity.Sim;
import com.iot.dto.SIMDTO;
import com.iot.dto.Status;

@Service
public class SIMAdapter implements Adapter<Sim, SIMDTO> {

	@Override
	public SIMDTO toDTO(Sim sim) {
		return new SIMDTO(sim.getId(), sim.getOperatorCode(), sim.getCountryName(), Status.getStatus(sim.getStatus()));
	}
	
	@Override
	public Sim toEntity(SIMDTO sim) {
		return new Sim(sim.getId(), sim.getCountryName(), sim.getOperatorCode(), sim.getStatus().getStatus());
	}
}
