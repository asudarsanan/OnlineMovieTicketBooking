package com.moviebooking.dao;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.moviebooking.entity.BookingEntity;

import com.moviebooking.entity.MovieEntity;
import com.moviebooking.entity.ReportEntity;
import com.moviebooking.entity.SeatAvailabilityEntity;
import com.moviebooking.entity.ShowEntity;
import com.moviebooking.entity.TheatreEntity;
import com.moviebooking.entity.UserEntity;
import com.moviebooking.model.Booking;

import com.moviebooking.model.Movie;
import com.moviebooking.model.Report;
import com.moviebooking.model.SeatAvailability;
import com.moviebooking.model.Show;
import com.moviebooking.model.Theatre;
import com.moviebooking.model.User;


@Repository(value="bookingDAO")
public class BookingDAOImpl implements BookingDAO{
	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public Integer bookTicket(Booking booking) {
		Query query=entityManager.createQuery("Select s from ShowEntity s where s.theatreEntity.theatreName=?1 and s.movieEntity.movieName=?2 and s.showTime=?3");
		query.setParameter(1, booking.getTheatre().getTheatreName());
		query.setParameter(2, booking.getMovie().getMovieName());
		query.setParameter(3, booking.getBookingTime());
		List<ShowEntity> showEntityList=query.getResultList();
		Query tQuery=entityManager.createQuery("select t from TheatreEntity t where t.theatreName=?1");
		tQuery.setParameter(1, booking.getTheatre().getTheatreName());
		List<TheatreEntity> tEntityList=tQuery.getResultList();
		TheatreEntity theatreEntity=tEntityList.get(0);
		if(showEntityList.size()!=0 && booking.getBookingDate().isAfter(LocalDate.now().minusDays(1)) && 
				booking.getBookingDate().isAfter(showEntityList.get(0).getStartDate().minusDays(1)) && 
				booking.getBookingDate().isBefore(showEntityList.get(0).getEndDate().plusDays(1))) {
			ShowEntity showEntity=showEntityList.get(0);
			Integer showId=showEntity.getShowId();
			Query q=entityManager.createQuery("select sa from SeatAvailabilityEntity sa where sa.showEntity.showId=?1 and sa.userDate=?2");
			q.setParameter(1, showId);
			q.setParameter(2, booking.getBookingDate());
			List<SeatAvailabilityEntity> seatEntityList=q.getResultList();
			SeatAvailabilityEntity seatAvailEntity=null;
			if(seatEntityList.size()!=0) {
				seatAvailEntity=seatEntityList.get(0);
				Integer seatsRemaining=seatAvailEntity.getSeatingCapacity();
				if(seatsRemaining>=booking.getNoOfTickets()) {
					seatAvailEntity.setSeatingCapacity(null);
					seatsRemaining-=booking.getNoOfTickets();
					seatAvailEntity.setSeatingCapacity(seatsRemaining);
				}
				//else {					
				//}
			}
			else {
				seatAvailEntity=new SeatAvailabilityEntity();
				Integer remSeats=theatreEntity.getTheatreCapacity()-booking.getNoOfTickets();
				seatAvailEntity.setSeatingCapacity(remSeats);
				seatAvailEntity.setUserDate(booking.getBookingDate());
				seatAvailEntity.setShowEntity(showEntity);
				entityManager.persist(seatAvailEntity);
			}
			BookingEntity bEntity=new BookingEntity();
			bEntity.setBookingDate(booking.getBookingDate());
			bEntity.setBookingTime(booking.getBookingTime());
			
			Query mQuery=entityManager.createQuery("select m from MovieEntity m where m.movieName=?1");
			mQuery.setParameter(1, booking.getMovie().getMovieName());
			List<MovieEntity> mEntityList=mQuery.getResultList();
			MovieEntity movieEntity=mEntityList.get(0);
			bEntity.setMovieEntity(movieEntity);

			bEntity.setTheatreEntity(theatreEntity);
			
			bEntity.setNoOfTickets(booking.getNoOfTickets());
			bEntity.setSeatAvailabilityEntity(seatAvailEntity);
			Integer totRate=booking.getNoOfTickets()*theatreEntity.getTheatrePrice();
			bEntity.setTotalRate(totRate);
			
			Query uQuery=entityManager.createQuery("select u from UserEntity u where u.userEmailid=?1");
			uQuery.setParameter(1, booking.getUser().getUserEmailid());
			List<UserEntity> uEntityList=uQuery.getResultList();
			UserEntity userEntity=uEntityList.get(0);
			bEntity.setUserEntity(userEntity);
			entityManager.persist(bEntity);
			
			Boolean flag=false;
			Query qbr=entityManager.createQuery("select r from ReportEntity r where r.status=?1");
			qbr.setParameter(1, "Booked");
			List<ReportEntity> reportEntityList=qbr.getResultList();
			if(reportEntityList.size()!=0) {
				System.out.println("NOT ZEROOOO BOOKING");
				for (ReportEntity reportEntity : reportEntityList) {
					if(reportEntity.getShowEntity().getShowId()==seatAvailEntity.getShowEntity().getShowId()) {
						System.out.println("PRESENTTTTTTTTTTT");
						Integer totBookedTickets=reportEntity.getNoOfTicketsBookedOrCancelled()+bEntity.getNoOfTickets();
						reportEntity.setNoOfTicketsBookedOrCancelled(totBookedTickets);
						flag=true;
						break;
					}
				}
				if(flag==false) {
					ReportEntity bookingReportEntity=new ReportEntity();
					ShowEntity shEntity=seatAvailEntity.getShowEntity();
					bookingReportEntity.setShowEntity(shEntity);
					bookingReportEntity.setNoOfTicketsBookedOrCancelled(bEntity.getNoOfTickets());
					bookingReportEntity.setUserDate(bEntity.getBookingDate());
					bookingReportEntity.setStatus("Booked");
					entityManager.persist(bookingReportEntity);	
				}
				
			}
			else if(reportEntityList.size()==0) {
				ReportEntity bookingReportEntity=new ReportEntity();
				ShowEntity shEntity=seatAvailEntity.getShowEntity();
				bookingReportEntity.setShowEntity(shEntity);
				bookingReportEntity.setNoOfTicketsBookedOrCancelled(bEntity.getNoOfTickets());
				bookingReportEntity.setUserDate(bEntity.getBookingDate());
				bookingReportEntity.setStatus("Booked");
				entityManager.persist(bookingReportEntity);
			}
			
			return bEntity.getBookingId();
		}
		return null;
	}
	
	@Override
	public List<Booking> getMyBooking(String userEmailid) {
		Query query=entityManager.createQuery("select u from UserEntity u where u.userEmailid=?1");
		query.setParameter(1, userEmailid);
		List<UserEntity> uEntityList=query.getResultList();
		UserEntity uEntity=uEntityList.get(0);
		Query bQuery=entityManager.createQuery("select b from BookingEntity b where b.userEntity.userId=?1");
		bQuery.setParameter(1, uEntity.getUserId());
		List<BookingEntity> bEntityList=bQuery.getResultList();
		if(bEntityList.size()!=0) {
			List<Booking> bookingList=new ArrayList<>();
			for(BookingEntity bEntity:bEntityList) {
				Booking booking=new Booking();
				booking.setBookingId(bEntity.getBookingId());
				booking.setBookingDate(bEntity.getBookingDate());
				booking.setBookingTime(bEntity.getBookingTime());
				booking.setNoOfTickets(bEntity.getNoOfTickets());
				booking.setTotalRate(bEntity.getTotalRate());
				
				Movie movie=new Movie();
				movie.setMovieDirector(bEntity.getMovieEntity().getMovieDirector());
				movie.setMovieId(bEntity.getMovieEntity().getMovieId());
				movie.setMovieLang(bEntity.getMovieEntity().getMovieLang());
				movie.setMovieName(bEntity.getMovieEntity().getMovieName());
				movie.setMovieType(bEntity.getMovieEntity().getMovieType());
				booking.setMovie(movie);
				
				Theatre theatre=new Theatre();
				theatre.setTheatreId(bEntity.getTheatreEntity().getTheatreId());
				theatre.setTheatreCapacity(bEntity.getTheatreEntity().getTheatreCapacity());
				theatre.setTheatreLocation(bEntity.getTheatreEntity().getTheatreLocation());
				theatre.setTheatreName(bEntity.getTheatreEntity().getTheatreName());
				theatre.setTheatrePrice(bEntity.getTheatreEntity().getTheatrePrice());
				booking.setTheatre(theatre);
				
				SeatAvailability seatAvail=new SeatAvailability();
				seatAvail.setSeatingCapacity(bEntity.getSeatAvailabilityEntity().getSeatingCapacity());
				seatAvail.setSeatAvailId(bEntity.getSeatAvailabilityEntity().getSeatAvailId());
				seatAvail.setUserDate(bEntity.getSeatAvailabilityEntity().getUserDate());
				Show show=new Show();
				show.setEndDate(bEntity.getSeatAvailabilityEntity().getShowEntity().getEndDate());
				show.setStartDate(bEntity.getSeatAvailabilityEntity().getShowEntity().getStartDate());
				show.setShowId(bEntity.getSeatAvailabilityEntity().getShowEntity().getShowId());
				show.setShowTime(bEntity.getSeatAvailabilityEntity().getShowEntity().getShowTime());
				show.setMovie(movie);
				show.setTheatre(theatre);
				seatAvail.setShow(show);
				booking.setSeatAvailability(seatAvail);
				
				User user=new User();
				user.setUserEmailid(uEntity.getUserEmailid());
				user.setUserName(uEntity.getUserName());
				user.setUserId(uEntity.getUserId());
				booking.setUser(user);
				bookingList.add(booking);
			}
			return bookingList;
		}
		return null;
	}
	@Override
	public Integer cancelBooking(Integer bookingId) {
		BookingEntity  bEntity=entityManager.find(BookingEntity.class, bookingId);
		SeatAvailabilityEntity seatAvailEntity=entityManager.find(SeatAvailabilityEntity.class, bEntity.getSeatAvailabilityEntity().getSeatAvailId());
		
		Query q=entityManager.createQuery("select r from ReportEntity r where r.status=?1");
		q.setParameter(1, "Cancelled");
		List<ReportEntity> reportEntityList=q.getResultList();
		if(reportEntityList.size()!=0) {
			Boolean flag=false;
			for (ReportEntity reportEntity : reportEntityList) {
				if(reportEntity.getShowEntity().getShowId()==seatAvailEntity.getShowEntity().getShowId()) {
					Integer totCancelledTickets=reportEntity.getNoOfTicketsBookedOrCancelled()+bEntity.getNoOfTickets();
					reportEntity.setNoOfTicketsBookedOrCancelled(totCancelledTickets);
					flag=true;
					break;
				}
			}
			if(flag==false) {
				ReportEntity cancelReportEntity=new ReportEntity();
				ShowEntity showEntity=seatAvailEntity.getShowEntity();
				cancelReportEntity.setShowEntity(showEntity);
				cancelReportEntity.setNoOfTicketsBookedOrCancelled(bEntity.getNoOfTickets());
				cancelReportEntity.setUserDate(bEntity.getBookingDate());
				cancelReportEntity.setStatus("Cancelled");
				entityManager.persist(cancelReportEntity);	
			}
			
		}
		else if(reportEntityList.size()==0) {
			ReportEntity cancelReportEntity=new ReportEntity();
			ShowEntity showEntity=seatAvailEntity.getShowEntity();
			cancelReportEntity.setShowEntity(showEntity);
			cancelReportEntity.setNoOfTicketsBookedOrCancelled(bEntity.getNoOfTickets());
			cancelReportEntity.setUserDate(bEntity.getBookingDate());
			cancelReportEntity.setStatus("Cancelled");
			entityManager.persist(cancelReportEntity);
		}
		Query q1=entityManager.createQuery("select r from ReportEntity r where r.status=?1 and r.showEntity.showId=?2");
		q1.setParameter(1, "Booked");
		q1.setParameter(2, seatAvailEntity.getShowEntity().getShowId());
		List<ReportEntity> bookEntityList=q1.getResultList();
		if(bookEntityList.size()!=0) {
			ReportEntity brEntity=bookEntityList.get(0);
			Integer updatedSeats=brEntity.getNoOfTicketsBookedOrCancelled()-bEntity.getNoOfTickets();
			brEntity.setNoOfTicketsBookedOrCancelled(updatedSeats);
		}
			
		Integer remSeats=seatAvailEntity.getSeatingCapacity()+bEntity.getNoOfTickets();
		seatAvailEntity.setSeatingCapacity(remSeats);
		bEntity.setUserEntity(null);
		bEntity.setSeatAvailabilityEntity(null);
		bEntity.setMovieEntity(null);
		bEntity.setTheatreEntity(null);
		entityManager.remove(bEntity);
		return bookingId;
	}
	
	@Override 
	public List<Report> generateBookingRecord(String startDate,String endDate){
		
		DateTimeFormatter formatter1=DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate date1=LocalDate.parse(startDate, formatter1);
		DateTimeFormatter formatter2=DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate date2=LocalDate.parse(endDate, formatter2);
		Query query=entityManager.createQuery("Select r from ReportEntity r where r.userDate>=?1 and r.userDate<=?2 and r.status=?3");
		query.setParameter(1, date1);
		query.setParameter(2, date2);
		query.setParameter(3, "Booked");
		List<ReportEntity> bookedEntityList=query.getResultList();
		if(bookedEntityList.size()!=0) {
			List<Report> brList=new ArrayList<Report>();
			for (ReportEntity bookingReportEntity : bookedEntityList) {
				Report br=new Report();
				br.setReportId(bookingReportEntity.getReportId());
				br.setNoOfTicketsBookedOrCancelled(bookingReportEntity.getNoOfTicketsBookedOrCancelled());
				br.setUserDate(bookingReportEntity.getUserDate());
				Show show=new Show();
				show.setEndDate(bookingReportEntity.getShowEntity().getEndDate());
				show.setStartDate(bookingReportEntity.getShowEntity().getStartDate());
				show.setShowId(bookingReportEntity.getShowEntity().getShowId());
				show.setShowTime(bookingReportEntity.getShowEntity().getShowTime());
				Movie movie=new Movie();
				movie.setMovieDirector(bookingReportEntity.getShowEntity().getMovieEntity().getMovieDirector());
				movie.setMovieId(bookingReportEntity.getShowEntity().getMovieEntity().getMovieId());
				movie.setMovieLang(bookingReportEntity.getShowEntity().getMovieEntity().getMovieLang());
				movie.setMovieName(bookingReportEntity.getShowEntity().getMovieEntity().getMovieName());
				movie.setMovieType(bookingReportEntity.getShowEntity().getMovieEntity().getMovieType());
				show.setMovie(movie);
				Theatre theatre=new Theatre();
				theatre.setTheatreId(bookingReportEntity.getShowEntity().getTheatreEntity().getTheatreId());
				theatre.setTheatreCapacity(bookingReportEntity.getShowEntity().getTheatreEntity().getTheatreCapacity());
				theatre.setTheatreLocation(bookingReportEntity.getShowEntity().getTheatreEntity().getTheatreLocation());
				theatre.setTheatreName(bookingReportEntity.getShowEntity().getTheatreEntity().getTheatreName());
				theatre.setTheatrePrice(bookingReportEntity.getShowEntity().getTheatreEntity().getTheatrePrice());
				show.setTheatre(theatre);			
				br.setShow(show);
				br.setStatus(bookingReportEntity.getStatus());
				br.setUserDate(bookingReportEntity.getUserDate());
				brList.add(br);
			}
			return brList;
		}
		return null;
	}
	
	@Override 
	public List<Report> generateCancelRecord(String startDate,String endDate){
		DateTimeFormatter formatter1=DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate date1=LocalDate.parse(startDate, formatter1);
		DateTimeFormatter formatter2=DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate date2=LocalDate.parse(endDate, formatter2);
		Query query=entityManager.createQuery("Select cr from ReportEntity cr where cr.userDate>=?1 and cr.userDate<=?2 and cr.status=?3");
		query.setParameter(1, date1);
		query.setParameter(2, date2);
		query.setParameter(3, "Cancelled");
		List<ReportEntity> crEntityList=query.getResultList();
		if(crEntityList.size()!=0) {
			List<Report> crList=new ArrayList<Report>();
			for (ReportEntity cancelReportEntity : crEntityList) {
				Report cr=new Report();
				cr.setReportId(cancelReportEntity.getReportId());
				cr.setNoOfTicketsBookedOrCancelled(cancelReportEntity.getNoOfTicketsBookedOrCancelled());
				cr.setUserDate(cancelReportEntity.getUserDate());
				Show show=new Show();
				show.setEndDate(cancelReportEntity.getShowEntity().getEndDate());
				show.setStartDate(cancelReportEntity.getShowEntity().getStartDate());
				show.setShowId(cancelReportEntity.getShowEntity().getShowId());
				show.setShowTime(cancelReportEntity.getShowEntity().getShowTime());
				Movie movie=new Movie();
				movie.setMovieDirector(cancelReportEntity.getShowEntity().getMovieEntity().getMovieDirector());
				movie.setMovieId(cancelReportEntity.getShowEntity().getMovieEntity().getMovieId());
				movie.setMovieLang(cancelReportEntity.getShowEntity().getMovieEntity().getMovieLang());
				movie.setMovieName(cancelReportEntity.getShowEntity().getMovieEntity().getMovieName());
				movie.setMovieType(cancelReportEntity.getShowEntity().getMovieEntity().getMovieType());
				show.setMovie(movie);
				Theatre theatre=new Theatre();
				theatre.setTheatreId(cancelReportEntity.getShowEntity().getTheatreEntity().getTheatreId());
				theatre.setTheatreCapacity(cancelReportEntity.getShowEntity().getTheatreEntity().getTheatreCapacity());
				theatre.setTheatreLocation(cancelReportEntity.getShowEntity().getTheatreEntity().getTheatreLocation());
				theatre.setTheatreName(cancelReportEntity.getShowEntity().getTheatreEntity().getTheatreName());
				theatre.setTheatrePrice(cancelReportEntity.getShowEntity().getTheatreEntity().getTheatrePrice());
				show.setTheatre(theatre);			
				cr.setShow(show);
				cr.setStatus(cancelReportEntity.getStatus());
				cr.setUserDate(cancelReportEntity.getUserDate());
				crList.add(cr);
			}
			return crList;
		}
		return null;
	}

}
