package com.app.Challan;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.app.Book.BookService;
import com.app.User.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/challan") 
public class ChallanController {

	@Autowired
	ChallanService challanService;
	
	@Autowired
	BookService bookService;
	
	@Autowired
	UserService userService;
	
	
	
	ObjectMapper mapper = new ObjectMapper();


	@RequestMapping(value = "/", method = RequestMethod.GET)
	public List<Challan> getAllChallans() {
		return challanService.getall();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Challan getChallan(@PathVariable Integer id) {
		return challanService.find(id);
	}
	
	
	@RolesAllowed("ROLE_ADMIN")
	@RequestMapping(value = "/", method = RequestMethod.PUT)
	public Challan addChallan(@RequestBody Challan challan) {
		return challanService.create(challan);
	}
	
	
	@RolesAllowed("ROLE_ADMIN")
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public Challan updateChallan(@PathVariable Integer id,@RequestBody Challan challan) {
		return challanService.update(challan);
	}
	
	@RolesAllowed("ROLE_ADMIN")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE,produces = "application/json")
	public void deletChallan(@PathVariable Integer id) {
		challanService.delet(id);
	}
	
	
	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public List<Challan> getUserChallans(@PathVariable Integer id) {
		return challanService.findByUserByIssuedTo(id);
	}
	
	@RequestMapping(value = "/detailed/{id}", method = RequestMethod.GET,produces = "application/json")
	public String getDetailedChallan(@PathVariable Integer id) throws IOException {
		return challanService.getDetailedChallan(id);
	}
	
	@RolesAllowed("ROLE_ADMIN")
	@RequestMapping(value = "/detailedChallnListForUsersCenter", method = RequestMethod.GET,produces = "application/json")
	public Challan[] getDetailedChallanOfCenter(Principal principal,Authentication authentication) throws IOException {
		System.out.println(principal.toString()+ " "+authentication.toString());
		return challanService.getAllOfUsersCenter(principal.getName());
	}

	
	@RequestMapping(value = "/just/try", method = RequestMethod.GET,produces = "application/json")
	public Challan[] getJustTry(Principal principal,Authentication authentication) throws IOException {
		System.out.println(principal.toString()+ " "+authentication.toString());
		return challanService.justTry(principal.getName());
	}
}
