package com.app.Challan;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.CbDetails.CbDetailsService;
import com.app.User.UserService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Service
@Transactional
public class ChallanService  {

	@Autowired
	ChallanRepository challanRepository;
	
	@Autowired
	UserService userService;
	
	@Autowired
	CbDetailsService cbDetailsService;
	
	ObjectMapper mapper = new ObjectMapper();
	
	final JsonNodeFactory jsonFactory = JsonNodeFactory.instance;
	
	public Challan create(Challan challan) {
		return challanRepository.save(challan);
	}

	public Challan update(Challan challan) {
		return challanRepository.save(challan);
	}

	public void delet(Integer id) {
		challanRepository.deleteById(id);
	}

	public Challan find(int id) {
		return challanRepository.findById(id).get();
	}

	public List<Challan> getall() {
		List<Challan> list = new ArrayList<>();
		challanRepository.findAll().forEach(list::add);
		return list;
	}

	public List<Challan> findByUserByIssuedTo(Integer id) {
		// TODO Auto-generated method stub
		return challanRepository.findByUserByIssuedTo_Id(id);
	}
	
	public String getDetailedChallan(int id) throws IOException {
		Challan ch=find(id);
		JsonNode rootNode = mapper.valueToTree(ch);
		((ObjectNode) rootNode).set("userByIssuedTo",mapper.valueToTree(userService.find(ch.getUserByIssuedTo().getId())));
		((ObjectNode) rootNode).set("userByIssuedBy",mapper.valueToTree(userService.find(ch.getUserByIssuedBy().getId())));
		JsonNode readCbDetailsesNode = rootNode.path("cbDetailses");
		ArrayNode cbDetailNodeArray=jsonFactory.arrayNode();
			for(JsonNode cbDetail : readCbDetailsesNode) {
			JsonNode  cb=	mapper.readTree(cbDetailsService.detaliedCbDetail(cbDetail.path("id").asInt()));
			cbDetailNodeArray.add(cb);
			//((ObjectNode) cbDetail).re
		}
		((ObjectNode) rootNode).replace("cbDetailses", cbDetailNodeArray);
		
		
		return rootNode.toString();
	}
	
	public List<Challan> getChallanOfInstitute(){
		
		return null;
	}

	public Challan[] getAllOfUsersCenter(String name) {
		// TODO Auto-generated method stub
		return challanRepository.getAllChallanOfUsersCenter(name);
	}
	
	public Challan[] justTry(String name) {
		return challanRepository.justTry(name);
	}

}
