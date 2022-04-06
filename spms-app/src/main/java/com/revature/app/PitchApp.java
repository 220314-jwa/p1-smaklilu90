package com.revature.app;

import java.util.List;
import java.util.Map;
import com.revature.services.PersonServiceImpl;
import com.revature.data.DaoFactory;
import com.revature.data.PitchDAO;
import com.revature.exceptions.AlreadyAdoptedException;
import com.revature.exceptions.IncorrectCredentialsException;
import com.revature.exceptions.UsernameAlreadyExistsException;
import com.revature.models.Pitch;
import com.revature.models.Person;
import com.revature.services.PersonService;

import io.javalin.Javalin;
import io.javalin.http.HttpCode;

public class PitchApp {
private static PersonService personServ = new PersonServiceImpl();
	
	public static void main (String[] args) {
		// This file/class/method is where our Javalin logic/code is going to go
		// set up endpoints
		Javalin app = Javalin.create();
		// pass in a port to have it run on that port:
		app.start(8081);

		// just to test, creating a pitch:
		// post method, takes in a lambda function
		/*
		We can test this out in postman by making a post request with this url: http://localhost:8080/pitchs
		and pass in the pitch object in the body
		Postman -> Javalin -> DAO -> Database
		 */
		app.post("/pitchs", ctx -> {
			// because this a function, we actually write code in here:
			// create a Pitch object, we get this data from the body of the HTTP request
			Pitch pitch = ctx.bodyAsClass(com.revature.models.Pitch.class);
			// get out pitch dao from the dao factory:
			PitchDAO pitchDAO = DaoFactory.getPitchDAO();
			// try to insert pitch object into database
			int id = pitchDAO.create(pitch);
			// return the id back to the person:
			ctx.result("The pitch id is: " + id );
		});
		// get pitch by id:
		app.get("/pitchs/{id}", ctx -> {
			// Integer is a wrapper class that allows us to call methods:
			int id = Integer.parseInt(ctx.pathParam("id"));
			// get our pitch dao from the dao factory:
			PitchDAO pitchDAO = DaoFactory.getPitchDAO();
			Pitch resultPitch = pitchDAO.getById(id);
			ctx.json(resultPitch);
		});

		// get all pitchs from db:
		app.get("/all_pitchs", ctx -> {
			PitchDAO pitchDAO = DaoFactory.getPitchDAO();
			List<Pitch> pitchs = pitchDAO.getAll();
			ctx.json(pitchs);
		});
		
		// update pitch:
		app.put("/pitchs", ctx -> {
			Pitch pitch = ctx.bodyAsClass(com.revature.models.Pitch.class);
			PitchDAO pitchDAO = DaoFactory.getPitchDAO();
			pitchDAO.update(pitch);
		});
		
		// delete pitch:
		app.delete("/pitchs", ctx -> {
			int deleteId = Integer.parseInt(ctx.queryParam("id"));
			
			PitchDAO pitchDAO = DaoFactory.getPitchDAO();
			pitchDAO.deleteById(deleteId);
		});
		
		// GET to /pitchs: get available pitchs
		app.get("/pitchs", ctx -> {
			// .json() is an alternative to .result() that
			// sets the data type as JSON, the format that we
			// will be sending/receiving data in!
			ctx.json(personServ.viewAvailablePitchs());
		});
		
		// POST to /persons: register a new person
		
//		app.post("/persons", ctx -> {
//			// context bodyAsClass method will transform JSON data into
//			// a Java object as long as the JSON keys match the Java fields
//			Person newPerson = ctx.bodyAsClass(Person.class);
//			
//			try {
//				newPerson = personServ.register(newPerson);
//				ctx.json(newPerson);
//			} catch (UsernameAlreadyExistsException e) {
//				ctx.status(HttpCode.CONFLICT); // 409 conflict
//			}
//		});
		
		// POST to /auth: log in
		app.post("/auth", ctx -> {
			// if the keys in JSON data don't exactly match a class,
			// we can just use a Map!
			Map<String,String> credentials = ctx.bodyAsClass(Map.class);
			String personname = credentials.get("personname");
			String password = credentials.get("password");
			
			try {
				Person person = personServ.logIn(personname, password);
				ctx.json(person);
			} catch (IncorrectCredentialsException e) {
				ctx.status(HttpCode.UNAUTHORIZED); // 401 unauthorized
			}
		});
		
		// PUT to /pitchs/{id}/adopt where {id} will be a number (a pitch ID): adopt pitch
		app.put("/pitchs/{id}/adopt", ctx -> {
			// first we can grab the ID from the URI
			// since it is part of the path (URI) it is a path parameter
			// so we use ctx.pathParam and use the name we specified in
			// the path above
			int pitchId = Integer.parseInt(ctx.pathParam("id"));
			Pitch pitchToAdopt = personServ.getPitchById(pitchId);
			
			// now we need to get the Person from the request body
			Person person = ctx.bodyAsClass(Person.class);
			
			try {
				// now we have everything we need to adopt the pitch
				person = personServ.adoptPitch(person, pitchToAdopt);
				
				// then we can return the updated person
				ctx.json(person);
			} catch (AlreadyAdoptedException e) {
				ctx.status(HttpCode.CONFLICT); // 409 conflict
			}
		});
		
	}
}
