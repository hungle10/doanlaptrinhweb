package cuoiki.ltweb.impl;

import java.sql.Date;
import java.sql.Timestamp;

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
	public boolean register(String username, String fullname, String phoneNumber, String address, String email,String password, Boolean isActive, Date dateOfBirth, String image, long facebookAccountId,long googleAccountId, int roleId) {
		if (userDao.checkExistUsername(username)) {
			System.out.print("that bai da ton tai username nay ");
			return false;
		}
		long millis = System.currentTimeMillis();
		java.sql.Timestamp timestamp = new java.sql.Timestamp(millis);
        System.out.println("check check");
		// Khi gọi hàm insert
		userDao.insert(new UserModel(username,fullname,phoneNumber,address,email,password,isActive,dateOfBirth,image,facebookAccountId,googleAccountId,roleId,timestamp,timestamp));
		System.out.println("check check check");
		return true;
	}

	@Override
    //dang ki nguoi dung fb
	public boolean registerByFb(String username, String fullname, String phoneNumber, String address, String email,String password, Boolean isActive, Date dateOfBirth, String image, long facebookAccountId,long googleAccountId, int roleId) {
		
		
		if (userDao.checkExistFbId(facebookAccountId)) {
			System.out.print("that bai da ton tai tai khoan fb nay ");
			return false;
		}
		long millis = System.currentTimeMillis();
		java.sql.Timestamp timestamp = new java.sql.Timestamp(millis);

		// Khi gọi hàm insert
		userDao.insert(new UserModel(username,fullname,phoneNumber,address,email,password,isActive,dateOfBirth,image,facebookAccountId,googleAccountId,roleId,timestamp,timestamp));
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

}