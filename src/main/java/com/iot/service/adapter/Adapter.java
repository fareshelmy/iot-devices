package com.iot.service.adapter;

public interface Adapter<Entity, DTO> {

	DTO toDTO(Entity entity);

	Entity toEntity(DTO dto);
}