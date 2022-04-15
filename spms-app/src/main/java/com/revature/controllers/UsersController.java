package com.revature.controllers;


import java.util.Map;

import com.revature.exceptions.IncorrectCredentialsException;
import com.revature.exceptions.EmailAlreadyExistsException;
import com.revature.models.User;
import com.revature.services.UserService;
import com.revature.services.UserServiceImpl;

import io.javalin.http.Context;
import io.javalin.http.HttpCode;


public class UsersController {
	
	private static UserService userServ = new UserServiceImpl();

	// POST to /users
	public static void registerUser(Context ctx) {
		User newUser = ctx.bodyAsClass(User.class);
		
		try {
			newUser = userServ.register(newUser);
			ctx.json(newUser);
		} catch (EmailAlreadyExistsException e) {
			ctx.status(HttpCode.CONFLICT); // 409 conflict
		}
	}

	
	// POST to /auth
		public static void logIn(Context ctx) {
			Map<String,String> credentials = ctx.bodyAsClass(Map.class);
			String username = credentials.get("email");
			String password = credentials.get("password");
			
			try {
				User user = userServ.logIn(username, password);
				ctx.json(user);
	
				
			} catch (IncorrectCredentialsException e) {
				ctx.status(HttpCode.UNAUTHORIZED); // 401 unauthorized
			}
		}
		
		// GET to /users/{id} where {id} is the user's id
		public static void getUserById(Context ctx) {
			String pathParam = ctx.pathParam("id");
			if (pathParam != null && !pathParam.equals("undefined") && !pathParam.equals("null")) {
				int id = Integer.parseInt(pathParam);
				
				User user = userServ.getUserById(id);
				if (user != null)
					ctx.json(user);
				else
					ctx.status(HttpCode.NOT_FOUND); // 404 not found
			} else {
				ctx.status(HttpCode.BAD_REQUEST); // 400 bad request
			}
	
	
		}
}
