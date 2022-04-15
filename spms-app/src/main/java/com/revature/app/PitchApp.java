package com.revature.app;

import com.revature.controllers.PitchController;
import com.revature.controllers.UsersController;
import com.revature.services.UserService;
import com.revature.services.UserServiceImpl;

import io.javalin.Javalin;
import static io.javalin.apibuilder.ApiBuilder.*;

public class PitchApp {
	private static UserService userServ = new UserServiceImpl();
	public static void main(String[] args) {
		
		// TODO Auto-generated method stub

		
			Javalin app = Javalin.create(config -> {
				config.enableCorsForAllOrigins();
			});
			app.start();
			
			// cleaning up my main method by switching to app.routes and moving logic to controllers
			app.routes(() -> {
				// all paths starting with /pitchs
				path("pitchs", () -> {
					get(PitchController::getPitchs);
					post(PitchController::registerPitch);
					// /pitchs/4
					path("{id}", () -> {
						get(PitchController::getById);
						// /pets/4/adopt
						put("update", PitchController::updatePitch);
					});
				});
				// all paths starting with /users
				path("users", () -> {
					post(UsersController::registerUser);
					path("{id}", () -> {
						get(UsersController::getUserById);
					});
				});
				// all paths starting with /auth
				path("auth", () -> {
					post(UsersController::logIn);
				});
			});
			
		
		}
	}