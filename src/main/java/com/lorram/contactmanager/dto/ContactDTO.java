package com.lorram.contactmanager.dto;

import java.io.Serializable;

import com.lorram.contactmanager.entities.Contact;

public class ContactDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	private Integer number;
	
	public ContactDTO() {
	}

	public ContactDTO(Long id, String name, Integer number) {
		this.id = id;
		this.name = name;
		this.number = number;
	}
	
	public ContactDTO(Contact contact) {
		id = contact.getId();
		name = contact.getName();
		number = contact.getNumber();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}
}
