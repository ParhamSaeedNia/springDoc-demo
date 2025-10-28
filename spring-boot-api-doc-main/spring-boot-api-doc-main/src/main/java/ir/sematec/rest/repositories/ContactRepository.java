package ir.sematec.rest.repositories;

import java.util.List;

import ir.sematec.rest.entities.Contact;

public interface ContactRepository {
	List<Contact> getAll();
	Contact getById(Long id);
	Contact save(Contact newContact);
	Contact update(Contact newContact);
	void delete(Long id);
}