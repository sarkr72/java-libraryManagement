package model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.PriorityQueue;

public class AdminBag implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HashMap<String, Admin> adminMap;
	private int nElems;
	public AdminBag() {
		adminMap = new HashMap<>(50000);
		nElems = 0;
	}
	
	
	public void insertAdmin(Admin admin) {
		adminMap.put(admin.getUserName(), admin);
	}
	
	public Admin searchAdmin(String userName) {
		Admin admin = null;
		admin = adminMap.get(userName);
		return admin;
	}
	
	public Admin deleteAdmin(String userName) {
		Admin admin = null;
		admin = adminMap.remove(userName);
		return admin;
	}
	
	public HashMap<String, Admin> getAdminMap(){
		return adminMap;
	}
}
