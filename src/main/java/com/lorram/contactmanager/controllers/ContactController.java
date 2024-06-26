package com.lorram.contactmanager.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.lorram.contactmanager.dto.ContactDTO;
import com.lorram.contactmanager.services.ContactService;

@RestController
@RequestMapping(value = "/contacts")
public class ContactController {

	@Autowired	
	private ContactService service;
	
	@GetMapping
	public ResponseEntity<Page<ContactDTO>> findAll(
			@RequestParam(value = "name", defaultValue = "") String name,
			Pageable pageable) {
		
		Page<ContactDTO> list = service.findAll(name.trim(), pageable);		
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ContactDTO> findById(@PathVariable Long id) {
		ContactDTO contact = service.findById(id);
		return ResponseEntity.ok().body(contact);
	}
	
	@PostMapping
	public ResponseEntity<ContactDTO> insert(@RequestBody ContactDTO dto) {
		ContactDTO contact = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(contact.getId()).toUri();
		return ResponseEntity.created(uri).body(contact);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<ContactDTO> update(@PathVariable Long id, @RequestBody ContactDTO dto) {
		ContactDTO contact = service.update(dto, id);
		return ResponseEntity.ok().body(contact);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
