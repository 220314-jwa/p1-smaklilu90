package com.revature.controllers;

import com.revature.exceptions.EmailAlreadyExistsException;
//import com.revature.exceptions.AlreadyRejectedException;
import com.revature.models.Pitch;
import com.revature.models.User;
import com.revature.services.UserService;
import com.revature.services.UserServiceImpl;

import io.javalin.http.Context;
import io.javalin.http.HttpCode;

public class PitchController {

	
	private static UserService userServ = new UserServiceImpl();

	// GET to /pitchs
	public static void getPitchs(Context ctx) {
		ctx.json(userServ.viewAllPitchs());
	}
	
	// GET to /Pitches/{id} where {id} is the ID of the pitch
	public static void getById(Context ctx) {
		int id = Integer.parseInt(ctx.pathParam("id"));
		Pitch pitch = userServ.getPitchById(id);
		if (pitch != null)
			ctx.json(pitch);
		else
			ctx.status(HttpCode.NOT_FOUND); // 404 not found
	}
	
	public static void updatePitch(Context ctx) {
		int id = Integer.parseInt(ctx.pathParam("id"));
		Pitch pitch = userServ.updatePitch(id);
		if (pitch != null)
			ctx.json(pitch);
		else
			ctx.status(HttpCode.NOT_FOUND);
	}
	
	public static void registerPitch(Context ctx) {
		Pitch newPitch = ctx.bodyAsClass(Pitch.class);
		
		try {
			newPitch = userServ.registerPitch(newPitch);
			ctx.json(newPitch);
		} catch (EmailAlreadyExistsException e) {
			ctx.status(HttpCode.CONFLICT); // 409 conflict
		}
	}

	
	
	
}
