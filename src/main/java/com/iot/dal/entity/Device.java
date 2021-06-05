package com.iot.dal.entity;

import java.io.Serializable;
import javax.persistence.*;

@Entity
public class Device implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String name;

	@OneToOne
	private Sim sim;

	public Device() {
	}

	public Device(int id, String name, Sim sim) {
		this.id = id;
		this.name = name;
		this.sim = sim;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Sim getSim() {
		return this.sim;
	}

	public void setSim(Sim sim) {
		this.sim = sim;
	}
}