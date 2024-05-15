package com.lorram.contactmanager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lorram.contactmanager.entities.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long>{

}
