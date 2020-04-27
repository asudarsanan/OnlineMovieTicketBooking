# MovieTicketBookingApplication
Online Movie Ticket Booking Project

## Table of contents
* [General info](#general-info)
* [Technologies](#technologies)
* [Sample code](#sample-code)
* [Setup](#setup)
* [Features](#features)
* [Status](#status)
* [Contact](#contact)

## General info
This project is an online movie ticket booking system in which users can book or cancel tickets. They can view movie and theatre details. The admin is able to add, delete and update movies and theatres.


## Technologies
* Technology: Java 1.8 
* Database: MySQL 
* Discovery Server: Eureka 
* Framework: Spring Boot Application 
* API gateway: Zuul server 
* Registry: Eureka Server 


## Sample code
* To add a new Theatre into the application: 
  The API example is shown:
	'@PostMapping(value="/addTheatre")
	public ResponseEntity<String> addTheatre( @RequestBody Theatre theatre) throws Exception{
		try{
			Integer id=theatreService.addTheatre(theatre);			
			String str=environment.getProperty("TheatreAPI.ADDED_SUCCESSFULLY")+id;
			ResponseEntity<String> response=new ResponseEntity<String>(str, HttpStatus.OK);
			return response;
		}
		catch(Exception e){
			throw new ResponseStatusException(HttpStatus.CONFLICT, environment.getProperty(e.getMessage()), e);
		}
	}'
*  The Service Implementation will be as follows:
 	public Integer addTheatre(Theatre theatre) throws Exception{
		Integer theatreId=theatreDAO.addTheatre(theatre);
		if(theatreId==null) {
			throw new Exception("TheatreService.ADD_THEATRE_FAILED");
		}
		return theatreId;
	}
*  The DAO implementation will be as follows:

	public Integer addTheatre(Theatre theatre) {
		Query query=entityManager.createQuery("select t from TheatreEntity t where t.theatreName=?1");
		query.setParameter(1, theatre.getTheatreName());
		List<TheatreEntity> theatreList=query.getResultList();
		if(theatreList.size()==0) {
			TheatreEntity theatreEntity=new TheatreEntity();
			theatreEntity.setTheatreName(theatre.getTheatreName());
			theatreEntity.setTheatreLocation(theatre.getTheatreLocation());
			theatreEntity.setTheatreCapacity(theatre.getTheatreCapacity());
			theatreEntity.setTheatrePrice(theatre.getTheatrePrice());			
			entityManager.persist(theatreEntity);
			return theatreEntity.getTheatreId();
		}
		return null;
	}

## Features
List of features ready aare as follows:
* User can register with the application and can login with their registered emailid and password.
* Users can view various movies and theaters.Also they can book their tickets with desired number of seats and can cancel their booking.
* Admin can add/delete/update new movies, shows and theaters.


## Status
Project is in progress. The backend is completed.


## Contact
Created by Karthika Krishna(@karthikakrishna).
