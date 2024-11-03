package cuoiki.ltweb.services;

import java.sql.Date;

import cuoiki.ltweb.models.UserModel;

public interface IUserService {
	public UserModel login(String username, String password);
	public UserModel get(String username); //find by user name
	public boolean register(String username, String fullname, String phoneNumber, String address, String email,String password, Boolean isActive, Date dateOfBirth, String image, long facebookAccountId,long googleAccountId, int roleId);
	boolean checkExistEmail(String email);
	boolean checkExistUsername(String username);
	boolean registerByFb(String username, String fullname, String phoneNumber, String address, String email,String password, Boolean isActive, Date dateOfBirth, String image, long facebookAccountId,long googleAccountId, int roleId);
	
}
