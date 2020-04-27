package com.moviebooking.dao;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import com.moviebooking.entity.RoleEntity;

import com.moviebooking.entity.UserEntity;

import com.moviebooking.model.Role;

import com.moviebooking.model.User;

@Repository(value="userDAO")
public class UserDAOImpl implements UserDAO{
	@Autowired
	private EntityManager entityManager;
	
	@Override
	public User loginUser(String emailId, String password, Role role){
		Query query = entityManager.createQuery("select u from UserEntity u where u.userEmailid =?1 and u.userPassword =?2 and u.roleEntity.roleName=?3");
		query.setParameter(1, emailId);
		query.setParameter(2, password);
		query.setParameter(3, role.getRoleName());	
		List<UserEntity> userEntities = query.getResultList();
		if(userEntities.isEmpty())
			return null;
		UserEntity userEntity=userEntities.get(0);
		if(userEntity!=null) {
			User user=new User();
			user.setUserEmailid(userEntity.getUserEmailid());
			user.setUserId(userEntity.getUserId());
			user.setUserName(userEntity.getUserName());
			user.setUserPassword(userEntity.getUserPassword());
			user.setUserPhoneNumber(userEntity.getUserPhoneNumber());
			Role r=new Role();
			r.setRoleName(userEntity.getRoleEntity().getRoleName());
			user.setRole(r);
			return user;
		}
		return null;
	}
	
	
	@Override
	public Integer registerNewUser(User user) {
		Integer id=null;
		Query q=entityManager.createQuery("select u from UserEntity u where u.userEmailid=?1");
		q.setParameter(1, user.getUserEmailid());
		List<UserEntity> uEntityList=q.getResultList();
		if(uEntityList.size()==0) {
			UserEntity userEntity=new UserEntity();
			userEntity.setUserEmailid(user.getUserEmailid());
			userEntity.setUserName(user.getUserName());
			userEntity.setUserPassword(user.getUserPassword());
			userEntity.setUserPhoneNumber(user.getUserPhoneNumber());
			RoleEntity rEntity=entityManager.find(RoleEntity.class, "User");
			userEntity.setRoleEntity(rEntity);
			entityManager.persist(userEntity);
			
			id=userEntity.getUserId();
			return id;
		}
		return null;
		
	}
	
	@Override
	public Integer updateProfile(User user) {
		Integer id=user.getUserId();
		UserEntity uEntity=entityManager.find(UserEntity.class, id);
		if(uEntity!=null) {
			uEntity.setUserName(user.getUserName());
			uEntity.setUserPhoneNumber(user.getUserPhoneNumber());
			entityManager.persist(uEntity);
			return uEntity.getUserId();
		}
		return null;
	}
	
	

}
