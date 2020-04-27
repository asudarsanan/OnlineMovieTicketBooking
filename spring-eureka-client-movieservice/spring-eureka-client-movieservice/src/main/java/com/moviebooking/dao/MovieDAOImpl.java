package com.moviebooking.dao;

import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import com.moviebooking.entity.MovieEntity;
import com.moviebooking.entity.ShowEntity;
import com.moviebooking.entity.TheatreEntity;
import com.moviebooking.model.Movie;
import com.moviebooking.model.Show;

@Repository(value="movieDAO")
public class MovieDAOImpl implements MovieDAO{
	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public List<Movie> getAllMovies(){
		Query query=entityManager.createQuery("Select m from MovieEntity m");
		List<MovieEntity> movieEntityList=query.getResultList();
		
		List<Movie> movieList=new ArrayList<Movie>();
		if(movieEntityList.size()==0) {
			return null;
		}
		for (MovieEntity movieEntity: movieEntityList) {
			Movie movie=new Movie();
			movie.setMovieId(movieEntity.getMovieId());
			movie.setMovieName(movieEntity.getMovieName());
			movie.setMovieLang(movieEntity.getMovieLang());
			movie.setMovieType(movieEntity.getMovieType());
			movie.setMovieDirector(movieEntity.getMovieDirector());
			
			movieList.add(movie);			
		}
		return movieList;		
	}
	
	@Override
	public Integer addMovie(Movie movie){
		Query query=entityManager.createQuery("Select movie from MovieEntity movie where movie.movieName=?1");
		query.setParameter(1, movie.getMovieName());
		List<MovieEntity> movieList=query.getResultList();
		if(movieList.size()==0) {
			MovieEntity movieEntity=new MovieEntity();
			movieEntity.setMovieName(movie.getMovieName());
			movieEntity.setMovieLang(movie.getMovieLang());
			movieEntity.setMovieDirector(movie.getMovieDirector());
			movieEntity.setMovieType(movie.getMovieType());
			entityManager.persist(movieEntity);
			return movieEntity.getMovieId();
			}
		return null;
	}
	
	@Override
	public Integer deleteMovie(Integer movieId) {
		Query query=entityManager.createQuery("select m from MovieEntity m where m.movieId=?1");
		query.setParameter(1, movieId);
		List<MovieEntity> movieList=query.getResultList();
		if(movieList.size()!=0) {
			MovieEntity movieEntity=movieList.get(0);
			entityManager.remove(movieEntity);
			return movieId;
		}
		return null;		
	}
	@Override
	public Integer updateMovie(Movie movie) {
		Integer id=movie.getMovieId();
		MovieEntity movieEntity=entityManager.find(MovieEntity.class, id);
		
		if(movieEntity!=null) {
			movieEntity.setMovieName(movie.getMovieName());
			movieEntity.setMovieDirector(movie.getMovieDirector());
			movieEntity.setMovieLang(movie.getMovieLang());
			movieEntity.setMovieType(movie.getMovieType());
			entityManager.persist(movieEntity);
			return movieEntity.getMovieId();
		}
		return null;
	}
	@Override
	public Integer deleteMovieFromTheatre(Integer theatreId,Integer movieId) {
		Query tQuery=entityManager.createQuery("select s from ShowEntity s where s.theatreEntity.theatreId=?1 and s.movieEntity.movieId=?2");
		tQuery.setParameter(1, theatreId);
		tQuery.setParameter(2, movieId);
		List<ShowEntity> sEntityList=tQuery.getResultList();
		if(sEntityList.size()!=0) {
			for(ShowEntity sEntity:sEntityList) {
				sEntity.setMovieEntity(null);
				sEntity.setTheatreEntity(null);
				entityManager.remove(sEntity);
			}
			return movieId;
		}
		return null;
	}
	
	@Override
	public Integer addMovieToTheatre(Show show) {
		Query query=entityManager.createQuery("select s from ShowEntity s where s.theatreEntity.theatreId=?1 and s.movieEntity.movieId=?2 and s.showTime=?3");
		query.setParameter(1, show.getTheatre().getTheatreId());
		query.setParameter(2, show.getMovie().getMovieId());
		query.setParameter(3, show.getShowTime());
		List<ShowEntity> showEntityList=query.getResultList();
		MovieEntity mEntity=entityManager.find(MovieEntity.class, show.getMovie().getMovieId());
		Query tQuery=entityManager.createQuery("select t from TheatreEntity t where t.theatreName=?1");
		tQuery.setParameter(1, show.getTheatre().getTheatreName());
		List<TheatreEntity> tEntityList=tQuery.getResultList();
		if(tEntityList.size()==0 || showEntityList.size()==0) {
			return null;
		}
		TheatreEntity tEntity=tEntityList.get(0);
		List<ShowEntity> sEntityList=query.getResultList();
		if(sEntityList.size()==0) {
			if(tEntity!=null && mEntity!=null) {
				ShowEntity sEntity=new ShowEntity();
				sEntity.setShowTime(show.getShowTime());
				sEntity.setEndDate(show.getEndDate());
				sEntity.setStartDate(show.getStartDate());
				sEntity.setMovieEntity(mEntity);
				sEntity.setTheatreEntity(tEntity);
				entityManager.persist(sEntity);
				return sEntity.getMovieEntity().getMovieId();
			}
			return null;
			
		}
		else {
			ShowEntity sEntity=sEntityList.get(0);
			sEntity.setMovieEntity(null);
			sEntity.setMovieEntity(mEntity);
			return sEntity.getMovieEntity().getMovieId();
		}
	}


}
