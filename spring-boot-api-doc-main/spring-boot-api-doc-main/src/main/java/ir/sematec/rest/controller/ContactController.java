package ir.sematec.rest.controller;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ir.sematec.rest.entities.Contact;
import ir.sematec.rest.repositories.ContactRepository;

@RestController
public class ContactController {
	
	@Autowired
	private ContactRepository contactRepository;

	@Operation(summary = "Get all contacts", description = "Returns a list of all contacts", operationId = "getAllContacts")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Successfully retrieved all contacts"),
		@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	@GetMapping("/contacts")
	public List<Contact> getAll() {
		return contactRepository.getAll();
	}

	@Operation(summary = "Get contact by ID", description = "Returns a single contact by its ID", operationId = "getContactById")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Successfully retrieved contact"),
		@ApiResponse(responseCode = "404", description = "Contact not found"),
		@ApiResponse(responseCode = "400", description = "Invalid ID format"),
		@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	@GetMapping("/contacts/{id}")
	public Contact getContactById(@PathVariable Long id) {
		return contactRepository.getById(id);
	}
	
	@Operation(summary = "Create a new contact", description = "Creates a new contact and returns the created contact", operationId = "createContact")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "201", description = "Contact successfully created"),
		@ApiResponse(responseCode = "400", description = "Invalid input data"),
		@ApiResponse(responseCode = "422", description = "Validation error"),
		@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	@PostMapping("/contacts")
	public Contact createNewContact(@RequestBody Contact newContact) {
		return contactRepository.save(newContact);
	}
	
	@Operation(summary = "Update an existing contact", description = "Updates a contact by ID and returns the updated contact", operationId = "updateContact")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Contact successfully updated"),
		@ApiResponse(responseCode = "404", description = "Contact not found"),
		@ApiResponse(responseCode = "400", description = "Invalid input data or ID format"),
		@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	@PutMapping("/contacts/{id}")
	public Contact updateContact(@RequestBody Contact newContact, @PathVariable Long id) {
		newContact.setId(id);
		return contactRepository.update(newContact);
	}
	
	@Operation(summary = "Delete a contact", description = "Deletes a contact by its ID", operationId = "deleteContact")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "204", description = "Contact successfully deleted"),
		@ApiResponse(responseCode = "404", description = "Contact not found"),
		@ApiResponse(responseCode = "400", description = "Invalid ID format"),
		@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	@DeleteMapping("/contacts/{id}")
	public void deleteContact(@PathVariable Long id) {
		contactRepository.delete(id);
	}
}