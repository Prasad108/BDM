package com.app.Service;

import java.util.List;

import com.app.pojo.Roles;

public interface RolesService {
	public void create(Roles role);
	public void update(Roles role);
	public Roles edit(int id);
	public void delet(Roles role);
	public Roles find(int id);
	public List<Roles> getall();

}
