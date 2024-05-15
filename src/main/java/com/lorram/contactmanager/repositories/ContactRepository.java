package com.lorram.contactmanager.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lorram.contactmanager.entities.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long>{

	@Query("SELECT DISTINCT obj FROM Contact obj WHERE "
			+ "(LOWER(obj.name) LIKE LOWER(CONCAT('%',:name,'%')))")
	Page<Contact> find(String name, Pageable pageable);
	
}
