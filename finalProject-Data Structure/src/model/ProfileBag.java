package model;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ProfileBag implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HashMap<String, Profile> profileMap;
	private int nElems;
	private LinkedList<Book> historyList;
	private TreeMap<String, Book> historyMap;

	public ProfileBag() throws IOException{
		profileMap = new HashMap<>(50000);
		historyList = new LinkedList<>();
		historyMap = new TreeMap<>();
		nElems = 0;
	}

	public void addProfile(Profile profile) {
		profileMap.put(profile.getUserName(), profile);
//		profileList.add(profile);
		nElems++;
	}

	public Profile searchProfileByUserName(String username) {
		Profile profile = null;
		if(profileMap.containsKey(username)) { 
		profile = profileMap.get(username);
		}
		return profile;
	}

	public Profile deleteProfileByUserName(String username) {
		Profile profile = null;
		if(profileMap.containsKey(username)) {
			profile = profileMap.remove(username);
			}
		return profile;
	}
	
	public LinkedList<Book> getHistoryList(){
		return historyList;
	}
	public TreeMap<String, Book> getHistoryMap(){
		return historyMap;
	}
	public HashMap<String, Profile> getProfileMap(){
		return profileMap;
	}

//	public ObservableList<Profile> getProfileList() throws IOException{
//		return profileList;
//	}
}
