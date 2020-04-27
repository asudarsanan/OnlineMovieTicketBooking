package com.moviebooking.dao;



import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.moviebooking.entity.SeatAvailabilityEntity;
import com.moviebooking.entity.ShowEntity;
import com.moviebooking.entity.TheatreEntity;

import com.moviebooking.model.Theatre;

@Repository(value="theatreDAO")
public class TheatreDAOImpl implements TheatreDAO{
	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public List<Theatre> getAllTheatres(){
		Query query=entityManager.createQuery("Select t from TheatreEntity t");
		List<TheatreEntity> theatreEntityList=query.getResultList();
		if(theatreEntityList.size()==0) {
			return null;
		}
		List<Theatre> theatreList=new ArrayList<Theatre>();
		for(TheatreEntity theatreEntity: theatreEntityList) {
			Theatre theatre=new Theatre();
			theatre.setTheatreId(theatreEntity.getTheatreId());
			theatre.setTheatreName(theatreEntity.getTheatreName());
			theatre.setTheatreLocation(theatreEntity.getTheatreLocation());
			theatre.setTheatreCapacity(theatreEntity.getTheatreCapacity());
			theatre.setTheatrePrice(theatreEntity.getTheatrePrice());
			theatreList.add(theatre);			
		}
		return theatreList;
	} 
	
	@Override
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
	@Override
	public Integer deleteTheatre(Integer theatreId) {
		TheatreEntity theatreEntity=entityManager.find(TheatreEntity.class,theatreId);
		if(theatreEntity!=null) {
			entityManager.remove(theatreEntity);
			return theatreId;
		}
		return null;
	}
	
	@Override
	public Integer updateTheatre(Theatre theatre) {
		Integer id=theatre.getTheatreId();
		TheatreEntity tEntity=entityManager.find(TheatreEntity.class, id);
		
		if(tEntity!=null) {
			tEntity.setTheatreName(theatre.getTheatreName());
			tEntity.setTheatreLocation(theatre.getTheatreLocation());
			tEntity.setTheatrePrice(theatre.getTheatrePrice());
			tEntity.setTheatreCapacity(theatre.getTheatreCapacity());
			entityManager.persist(tEntity);
			return tEntity.getTheatreId();
		}
		return null;
	}
	@Override
	public List<Theatre> viewTheatresWithMovie(Integer movieId,String userDate) {
		Query query=entityManager.createQuery("select s from ShowEntity s where s.movieEntity.movieId=?1");
		query.setParameter(1, movieId);
		List<ShowEntity> showEntityList=query.getResultList();
		if(showEntityList.size()!=0) {
			List<Theatre> theatreList=new ArrayList<Theatre>();
			DateTimeFormatter formatter=DateTimeFormatter.ofPattern("dd-MM-yyyy");
			LocalDate date1=LocalDate.parse(userDate, formatter);
			for(ShowEntity showEntity: showEntityList) {
				Boolean flag=false;
				if(date1.isAfter(LocalDate.now().minusDays(1)) && date1.isAfter(showEntity.getStartDate().minusDays(1)) && date1.isBefore(showEntity.getEndDate().plusDays(1))) {
					for(Theatre t:theatreList) {
						if(t.getTheatreName()==showEntity.getTheatreEntity().getTheatreName()) {
							flag=true;
						}
					}
					if(flag==false) {
						Theatre theatre=new Theatre();
						theatre.setTheatreId(showEntity.getTheatreEntity().getTheatreId());
						theatre.setTheatreName(showEntity.getTheatreEntity().getTheatreName());
						theatre.setTheatreLocation(showEntity.getTheatreEntity().getTheatreLocation());
						theatre.setTheatreCapacity(showEntity.getTheatreEntity().getTheatreId());
						theatre.setTheatrePrice(showEntity.getTheatreEntity().getTheatrePrice());
						theatreList.add(theatre);
					}
					
				}						
			}
			return theatreList;
		}
		
		return null;
	}
	@Override
	public List<String> viewShowTime(Integer movieId,Integer theatreId){
		Query query=entityManager.createQuery("select s from ShowEntity s where s.movieEntity.movieId=?1 and s.theatreEntity.theatreId=?2");
		query.setParameter(1, movieId);
		query.setParameter(2, theatreId);
		List<ShowEntity> showEntityList=query.getResultList();
		List<String> showTimeList=new ArrayList<>();
		if(showEntityList.size()!=0) {
			for(ShowEntity showEntity: showEntityList) {
				String s=showEntity.getShowTime();
				showTimeList.add(s);
			}
			return showTimeList;
		}
		return null;
	}
	
	@Override
	public Integer showRemainingSeats(Integer showId) {
		Query q=entityManager.createQuery("Select sa from SeatAvailabilityEntity sa where sa.showEntity.showId=?1");
		q.setParameter(1, showId);
		List<SeatAvailabilityEntity> saEntityList=q.getResultList();
		if(saEntityList.size()!=0) {
			SeatAvailabilityEntity saEntity=saEntityList.get(0);
			return saEntity.getSeatingCapacity();
		}
		return null;
	}

}
