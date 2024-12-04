package cuoiki.ltweb.dao;

import java.util.List;

import cuoiki.ltweb.models.UserModel;

public interface IUserDAO {
	List<UserModel> findAll();

	UserModel findById(long id);

	void insert(UserModel user);
	
	UserModel findByUserName(String username); //khi user dùng username đăng nhập
	
	UserModel findByEmail(String email); // khi user dùng email đăng nhập 
	
	boolean checkExistEmail(String email);
	
	boolean checkExistUsername(String username);
	
	void update(UserModel user);

	boolean checkExistPhoneNumber(String phonenumber);

	void delete(long id);

}
