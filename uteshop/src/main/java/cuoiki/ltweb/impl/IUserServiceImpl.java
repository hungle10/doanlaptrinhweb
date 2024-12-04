package cuoiki.ltweb.impl;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import cuoiki.ltweb.dao.*;
import cuoiki.ltweb.models.UserModel;
import cuoiki.ltweb.services.*;

public class IUserServiceImpl implements IUserService {
	IUserDAO userDao = new UserDAOImpl();

	@Override
	public UserModel login(String username, String password) {
		UserModel user = this.get(username);
		if (user != null && password.equals(user.getPassword())) {
			return user;
		}
		return null;
	}

	
	@Override
	public boolean register(String username, String fullname, String phoneNumber, String address, String email,String password, Boolean isActive, Date dateOfBirth, String image,int roleId) {
		if (userDao.checkExistUsername(username)) {
			System.out.print("that bai da ton tai username nay ");
			return false;
		}
		long millis = System.currentTimeMillis();
		java.sql.Timestamp timestamp = new java.sql.Timestamp(millis);
        System.out.println("check check");
		// Khi gọi hàm insert
		userDao.insert(new UserModel(username,fullname,phoneNumber,address,email,password,isActive,dateOfBirth,image,roleId,timestamp,timestamp));
		System.out.println("check check check");
		return true;
	}

	@Override
	public boolean checkExistEmail(String email) {
		return userDao.checkExistEmail(email);
	}

	@Override
	public boolean checkExistUsername(String username) {
		return userDao.checkExistUsername(username);
	}

	@Override
	public UserModel get(String username) {
		return userDao.findByUserName(username);
	}
	@Override
	public UserModel findById(long id) {
		return userDao.findById(id);
	}
	@Override
	public UserModel findByEmail(String id) {
		return userDao.findByEmail(id);
	}
	@Override
	public List<UserModel> findAll(){
		return userDao.findAll();
	}
	@Override
	public void update(UserModel user) {
	    userDao.update(user);
	}
	@Override
	public boolean checkExistPhoneNumber(String phonenumber) {
		return userDao.checkExistPhoneNumber(phonenumber);
	}
	@Override
	public void delete(long id) {
		userDao.delete(id);
	}

}
