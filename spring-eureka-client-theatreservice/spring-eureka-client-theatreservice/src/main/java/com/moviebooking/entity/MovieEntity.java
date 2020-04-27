package com.moviebooking.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="MOVIE")
public class MovieEntity {
	@Id
	@Column(name="MOVIE_ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer movieId;
	@Column(name="MOVIE_NAME")
	private String movieName;
	@Column(name="MOVIE_LANG")
	private String movieLang;
	@Column(name="MOVIE_DIRECTOR")
	private String movieDirector;
	@Column(name="MOVIE_TYPE")
	private String movieType;
	
	
	public Integer getMovieId() {
		return movieId;
	}
	public void setMovieId(Integer movieId) {
		this.movieId = movieId;
	}
	public String getMovieName() {
		return movieName;
	}
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	public String getMovieLang() {
		return movieLang;
	}
	public void setMovieLang(String movieLang) {
		this.movieLang = movieLang;
	}
	public String getMovieDirector() {
		return movieDirector;
	}
	public void setMovieDirector(String movieDirector) {
		this.movieDirector = movieDirector;
	}
	public String getMovieType() {
		return movieType;
	}
	public void setMovieType(String movieType) {
		this.movieType = movieType;
	}
	
	
	
}

