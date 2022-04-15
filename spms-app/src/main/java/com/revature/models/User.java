package com.revature.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User {
	

int user_id ;
int role_id;
String full_name;
String email;
String password;
String phone;


public User() {
	
	
	user_id = 0 ;
	role_id = 0;
	full_name = "";
	email= " ";
	password=" ";
	phone=" ";
	
}


public int getUser_id() {
	return user_id;
}


public void setUser_id(int user_id) {
	this.user_id = user_id;
}


public int getRole_id() {
	return role_id;
}


public void setRole_id(int role_id) {
	this.role_id = role_id;
}


public String getFull_name() {
	return full_name;
}


public void setFull_name(String full_name) {
	this.full_name = full_name;
}


public String getEmail() {
	return email;
}


public void setEmail(String email) {
	this.email = email;
}


public String getPassword() {
	return password;
}


public void setPassword(String password) {
	this.password = password;
}


public String getPhone() {
	return phone;
}


public void setPhone(String phone) {
	this.phone = phone;
}


@Override
public String toString() {
	return "User [user_id=" + user_id + ", role_id=" + role_id + ", full_name=" + full_name + ", email=" + email
			+ ", password=" + password + ", phone=" + phone + "]";
}


@Override
public int hashCode() {
	return Objects.hash(email, full_name, password, phone, role_id, user_id);
}


@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	User other = (User) obj;
	return Objects.equals(email, other.email) && Objects.equals(full_name, other.full_name)
			&& Objects.equals(password, other.password) && Objects.equals(phone, other.phone)
			&& role_id == other.role_id && user_id == other.user_id;
}



}
