package com.app.Type;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TypeService  {

	@Autowired
	TypeRepository typeRepository;
	
	public Type create(Type type) {
		return typeRepository.save(type);
	}

	public Type update(Type type) {
		return typeRepository.save(type);
	}

	public void delet(Integer id) {
		typeRepository.deleteById(id);
	}

	public Type find(int id) {
		return typeRepository.findById(id).get();
	}

	public List<Type> getall() {
		List<Type> list = new ArrayList<>();
		typeRepository.findAll().forEach(list::add);
		return list;
	}

	public List<Type> getAllTypesForBookNameAndLanguageInUsersInventory(Integer nameId, Integer langId, String name) {
		return typeRepository.getAllTypesForBookNameAndLanguageInUsersInventory(nameId,langId,name);
	
	}

	public List<Type> getAllTypesForBookNameAndLanguageFromAllBooks(Integer nameId, Integer langId) {
		// TODO Auto-generated method stub
		return typeRepository.getAllTypesForBookNameAndLanguageFromAllBooks(nameId, langId)
				.orElseThrow(() -> new RuntimeException("Error While Fetching all Book Language"));
	}

}
