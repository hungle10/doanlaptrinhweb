package cuoiki.ltweb.services;

import java.sql.Date;
import java.util.List;

import cuoiki.ltweb.models.UserModel;

public interface IUserService {
	public UserModel login(String username, String password);
	public UserModel get(String username); //find by user name
	public boolean register(String username, String fullname, String phoneNumber, String address, String email,String password, Boolean isActive, Date dateOfBirth, String image,int roleId);
	boolean checkExistEmail(String email);
	boolean checkExistUsername(String username);
	public UserModel findById(long id);
	public List<UserModel> findAll();
	UserModel findByEmail(String id);
	void update(UserModel user);
	boolean checkExistPhoneNumber(String phonenumber);
	void delete(long id);

}
