package com.lorram.contactmanager.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.lorram.contactmanager.dto.ContactDTO;
import com.lorram.contactmanager.entities.Contact;
import com.lorram.contactmanager.repositories.ContactRepository;
import com.lorram.contactmanager.services.exceptions.DatabaseException;
import com.lorram.contactmanager.services.exceptions.ResourceNotFoundException;

@Service
public class ContactService {

	@Autowired
	private ContactRepository repository;

	public Page<ContactDTO> findAll(String name, Pageable pageable) {
		Page<Contact> list = repository.find(name, pageable);
		return list.map(x -> new ContactDTO(x));
	}

	public ContactDTO findById(Long id) {
		Optional<Contact> contact = repository.findById(id);
		Contact entity = contact.orElseThrow(() -> new ResourceNotFoundException(id));
		return new ContactDTO(entity);
	}

	public ContactDTO update(ContactDTO dto, Long id) {
		Contact entity = repository.getReferenceById(id);
		fromDto(dto, entity);
		entity = repository.save(entity);
		return new ContactDTO(entity);
	}

	public ContactDTO insert(ContactDTO dto) {
		Contact entity = new Contact();
		try {
			fromDto(dto, entity);
			entity = repository.save(entity);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Integrity violation");
		}
		return new ContactDTO(entity);
	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Integrity violation");
		}
	}

	private void fromDto(ContactDTO dto, Contact entity) {
		entity.setName(dto.getName());
		entity.setNumber(dto.getNumber());
	}
}
