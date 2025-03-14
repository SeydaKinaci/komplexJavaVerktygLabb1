package org.example.komplexJavaLabb1.entity;

import jakarta.annotation.Generated;
import jakarta.persistence.metamodel.EntityType;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Movie.class)
@Generated("org.hibernate.processor.HibernateProcessor")
public abstract class Movie_ {

	public static final String DURATION = "duration";
	public static final String DIRECTOR = "director";
	public static final String GENRE = "genre";
	public static final String DESCRIPTION = "description";
	public static final String ID = "id";
	public static final String TITLE = "title";
	public static final String RELEASE_YEAR = "releaseYear";

	
	/**
	 * @see org.example.komplexJavaLabb1.entity.Movie#duration
	 **/
	public static volatile SingularAttribute<Movie, Integer> duration;
	
	/**
	 * @see org.example.komplexJavaLabb1.entity.Movie#director
	 **/
	public static volatile SingularAttribute<Movie, String> director;
	
	/**
	 * @see org.example.komplexJavaLabb1.entity.Movie#genre
	 **/
	public static volatile SingularAttribute<Movie, String> genre;
	
	/**
	 * @see org.example.komplexJavaLabb1.entity.Movie#description
	 **/
	public static volatile SingularAttribute<Movie, String> description;
	
	/**
	 * @see org.example.komplexJavaLabb1.entity.Movie#id
	 **/
	public static volatile SingularAttribute<Movie, Long> id;
	
	/**
	 * @see org.example.komplexJavaLabb1.entity.Movie#title
	 **/
	public static volatile SingularAttribute<Movie, String> title;
	
	/**
	 * @see org.example.komplexJavaLabb1.entity.Movie
	 **/
	public static volatile EntityType<Movie> class_;
	
	/**
	 * @see org.example.komplexJavaLabb1.entity.Movie#releaseYear
	 **/
	public static volatile SingularAttribute<Movie, Integer> releaseYear;

}

