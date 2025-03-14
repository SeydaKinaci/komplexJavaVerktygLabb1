package org.example.komplexJavaLabb1.persistence;

import jakarta.annotation.Generated;
import jakarta.annotation.Nonnull;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.data.Order;
import jakarta.data.exceptions.DataException;
import jakarta.data.exceptions.EmptyResultException;
import jakarta.data.exceptions.EntityExistsException;
import jakarta.data.exceptions.OptimisticLockingFailureException;
import jakarta.data.page.Page;
import jakarta.data.page.PageRequest;
import jakarta.data.page.impl.PageRecord;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.PersistenceUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static java.util.Optional.ofNullable;
import java.util.stream.Stream;
import org.example.komplexJavaLabb1.entity.Movie;
import org.example.komplexJavaLabb1.entity.Movie_;
import org.hibernate.SessionFactory;
import org.hibernate.StaleStateException;
import org.hibernate.StatelessSession;
import org.hibernate.exception.ConstraintViolationException;
import static org.hibernate.query.Order.by;
import static org.hibernate.query.SortDirection.*;

@RequestScoped
@Generated("org.hibernate.processor.HibernateProcessor")
public class MovieRepository_ implements MovieRepository {

	static final String FIND_BY_RELEASE_YEAR_GREATER_THAN_int = "select m from Movie m where releaseYear > :releaseYear";
	static final String FIND_BY_DIRECTOR_String = "select m from Movie m where m.director = :director";
	static final String FIND_BY_GENRE_String = "select m from Movie m where m.genre = :genre";
	static final String FIND_BY_DURATION_RANGE_int_int = "select m from Movie m where m.duration between :min and :max";

	
	/**
	 * Execute the query {@value #FIND_BY_RELEASE_YEAR_GREATER_THAN_int}.
	 *
	 * @see org.example.komplexJavaLabb1.persistence.MovieRepository#findByReleaseYearGreaterThan(int)
	 **/
	@Override
	public Stream<Movie> findByReleaseYearGreaterThan(int releaseYear) {
		try {
			return session.createSelectionQuery(FIND_BY_RELEASE_YEAR_GREATER_THAN_int, Movie.class)
				.setParameter("releaseYear", releaseYear)
				.getResultStream();
		}
		catch (PersistenceException exception) {
			throw new DataException(exception.getMessage(), exception);
		}
	}
	
	/**
	 * Find {@link Movie} by {@link Movie#id id}.
	 *
	 * @see org.example.komplexJavaLabb1.persistence.MovieRepository#deleteById(Long)
	 **/
	@Override
	public void deleteById(@Nonnull Long id) {
		if (id == null) throw new IllegalArgumentException("Null id");
		var _builder = session.getFactory().getCriteriaBuilder();
		var _query = _builder.createCriteriaDelete(Movie.class);
		var _entity = _query.from(Movie.class);
		_query.where(
				_builder.equal(_entity.get(Movie_.id), id)
		);
		try {
			session.createMutationQuery(_query)
				.executeUpdate();
		}
		catch (NoResultException exception) {
			throw new EmptyResultException(exception.getMessage(), exception);
		}
		catch (NonUniqueResultException exception) {
			throw new jakarta.data.exceptions.NonUniqueResultException(exception.getMessage(), exception);
		}
		catch (PersistenceException exception) {
			throw new DataException(exception.getMessage(), exception);
		}
	}
	
	@Override
	public Movie save(@Nonnull Movie entity) {
		if (entity == null) throw new IllegalArgumentException("Null entity");
		try {
			if (session.getIdentifier(entity) == null)
				session.insert(entity);
			else
				session.upsert(entity);
			return entity;
		}
		catch (StaleStateException exception) {
			throw new OptimisticLockingFailureException(exception.getMessage(), exception);
		}
		catch (PersistenceException exception) {
			throw new DataException(exception.getMessage(), exception);
		}
	}
	
	protected @Nonnull StatelessSession session;
	
	public MovieRepository_(@Nonnull StatelessSession session) {
		this.session = session;
	}
	
	public @Nonnull StatelessSession session() {
		return session;
	}
	
	/**
	 * Find {@link Movie} by {@link Movie#title title}.
	 *
	 * @see org.example.komplexJavaLabb1.persistence.MovieRepository#findByTitle(String)
	 **/
	@Override
	public Movie findByTitle(String title) {
		var _builder = session.getFactory().getCriteriaBuilder();
		var _query = _builder.createQuery(Movie.class);
		var _entity = _query.from(Movie.class);
		_query.where(
				title==null
					? _entity.get(Movie_.title).isNull()
					: _builder.equal(_entity.get(Movie_.title), title)
		);
		try {
			return session.createSelectionQuery(_query)
				.getSingleResult();
		}
		catch (NoResultException exception) {
			throw new EmptyResultException(exception.getMessage(), exception);
		}
		catch (NonUniqueResultException exception) {
			throw new jakarta.data.exceptions.NonUniqueResultException(exception.getMessage(), exception);
		}
		catch (PersistenceException exception) {
			throw new DataException(exception.getMessage(), exception);
		}
	}
	
	/**
	 * Execute the query {@value #FIND_BY_DIRECTOR_String}.
	 *
	 * @see org.example.komplexJavaLabb1.persistence.MovieRepository#findByDirector(String)
	 **/
	@Override
	public Stream<Movie> findByDirector(String director) {
		try {
			return session.createSelectionQuery(FIND_BY_DIRECTOR_String, Movie.class)
				.setParameter("director", director)
				.getResultStream();
		}
		catch (PersistenceException exception) {
			throw new DataException(exception.getMessage(), exception);
		}
	}
	
	/**
	 * Find {@link Movie} by {@link Movie#id id}.
	 *
	 * @see org.example.komplexJavaLabb1.persistence.MovieRepository#deleteMovieById(Long)
	 **/
	@Override
	public void deleteMovieById(@Nonnull Long id) {
		if (id == null) throw new IllegalArgumentException("Null id");
		var _builder = session.getFactory().getCriteriaBuilder();
		var _query = _builder.createCriteriaDelete(Movie.class);
		var _entity = _query.from(Movie.class);
		_query.where(
				_builder.equal(_entity.get(Movie_.id), id)
		);
		try {
			session.createMutationQuery(_query)
				.executeUpdate();
		}
		catch (NoResultException exception) {
			throw new EmptyResultException(exception.getMessage(), exception);
		}
		catch (NonUniqueResultException exception) {
			throw new jakarta.data.exceptions.NonUniqueResultException(exception.getMessage(), exception);
		}
		catch (PersistenceException exception) {
			throw new DataException(exception.getMessage(), exception);
		}
	}
	
	/**
	 * Find {@link Movie}.
	 *
	 * @see org.example.komplexJavaLabb1.persistence.MovieRepository#findAll()
	 **/
	@Override
	public Stream<Movie> findAll() {
		var _builder = session.getFactory().getCriteriaBuilder();
		var _query = _builder.createQuery(Movie.class);
		var _entity = _query.from(Movie.class);
		_query.where(
		);
		try {
			return session.createSelectionQuery(_query)
				.getResultStream();
		}
		catch (PersistenceException exception) {
			throw new DataException(exception.getMessage(), exception);
		}
	}
	
	@Override
	public List updateAll(@Nonnull List entities) {
		if (entities == null) throw new IllegalArgumentException("Null entities");
		try {
			for (var _entity : entities) {
				session.update(_entity);
			}
			return entities;
		}
		catch (StaleStateException exception) {
			throw new OptimisticLockingFailureException(exception.getMessage(), exception);
		}
		catch (PersistenceException exception) {
			throw new DataException(exception.getMessage(), exception);
		}
	}
	
	@Override
	public void delete(@Nonnull Movie entity) {
		if (entity == null) throw new IllegalArgumentException("Null entity");
		try {
			session.delete(entity);
		}
		catch (StaleStateException exception) {
			throw new OptimisticLockingFailureException(exception.getMessage(), exception);
		}
		catch (PersistenceException exception) {
			throw new DataException(exception.getMessage(), exception);
		}
	}
	
	/**
	 * Execute the query {@value #FIND_BY_GENRE_String}.
	 *
	 * @see org.example.komplexJavaLabb1.persistence.MovieRepository#findByGenre(String)
	 **/
	@Override
	public Stream<Movie> findByGenre(String genre) {
		try {
			return session.createSelectionQuery(FIND_BY_GENRE_String, Movie.class)
				.setParameter("genre", genre)
				.getResultStream();
		}
		catch (PersistenceException exception) {
			throw new DataException(exception.getMessage(), exception);
		}
	}
	
	@Override
	public Movie update(@Nonnull Movie entity) {
		if (entity == null) throw new IllegalArgumentException("Null entity");
		try {
			session.update(entity);
			return entity;
		}
		catch (StaleStateException exception) {
			throw new OptimisticLockingFailureException(exception.getMessage(), exception);
		}
		catch (PersistenceException exception) {
			throw new DataException(exception.getMessage(), exception);
		}
	}
	
	@Override
	public Movie insert(@Nonnull Movie entity) {
		if (entity == null) throw new IllegalArgumentException("Null entity");
		try {
			session.insert(entity);
			return entity;
		}
		catch (ConstraintViolationException exception) {
			throw new EntityExistsException(exception.getMessage(), exception);
		}
		catch (PersistenceException exception) {
			throw new DataException(exception.getMessage(), exception);
		}
	}
	
	@Override
	public List insertAll(@Nonnull List entities) {
		if (entities == null) throw new IllegalArgumentException("Null entities");
		try {
			for (var _entity : entities) {
				session.insert(_entity);
			}
			return entities;
		}
		catch (ConstraintViolationException exception) {
			throw new EntityExistsException(exception.getMessage(), exception);
		}
		catch (PersistenceException exception) {
			throw new DataException(exception.getMessage(), exception);
		}
	}
	
	@Override
	public List saveAll(@Nonnull List entities) {
		if (entities == null) throw new IllegalArgumentException("Null entities");
		try {
			for (var _entity : entities) {
				session.upsert(_entity);
			}
			return entities;
		}
		catch (StaleStateException exception) {
			throw new OptimisticLockingFailureException(exception.getMessage(), exception);
		}
		catch (PersistenceException exception) {
			throw new DataException(exception.getMessage(), exception);
		}
	}
	
	/**
	 * Find {@link Movie}.
	 *
	 * @see org.example.komplexJavaLabb1.persistence.MovieRepository#findAll(PageRequest,Order)
	 **/
	@Override
	public Page<Movie> findAll(PageRequest pageRequest, Order<Movie> sortBy) {
		var _builder = session.getFactory().getCriteriaBuilder();
		var _query = _builder.createQuery(Movie.class);
		var _entity = _query.from(Movie.class);
		_query.where(
		);
		var _orders = new ArrayList<org.hibernate.query.Order<? super Movie>>();
		for (var _sort : sortBy.sorts()) {
			_orders.add(by(Movie.class, _sort.property(),
							_sort.isAscending() ? ASCENDING : DESCENDING,
							_sort.ignoreCase()));
		}
		try {
			long _totalResults = 
					pageRequest.requestTotal()
							? session.createSelectionQuery(_query)
									.getResultCount()
							: -1;
			var _results = session.createSelectionQuery(_query)
				.setFirstResult((int) (pageRequest.page()-1) * pageRequest.size())
				.setMaxResults(pageRequest.size())
				.setOrder(_orders)
				.getResultList();
			return new PageRecord(pageRequest, _results, _totalResults);
		}
		catch (PersistenceException exception) {
			throw new DataException(exception.getMessage(), exception);
		}
	}
	
	@Override
	public void deleteAll(@Nonnull List<? extends Movie> entities) {
		if (entities == null) throw new IllegalArgumentException("Null entities");
		try {
			for (var _entity : entities) {
				session.delete(_entity);
			}
		}
		catch (StaleStateException exception) {
			throw new OptimisticLockingFailureException(exception.getMessage(), exception);
		}
		catch (PersistenceException exception) {
			throw new DataException(exception.getMessage(), exception);
		}
	}
	
	/**
	 * Find {@link Movie} by {@link Movie#id id}.
	 *
	 * @see org.example.komplexJavaLabb1.persistence.MovieRepository#findById(Long)
	 **/
	@Override
	public Optional<Movie> findById(@Nonnull Long id) {
		if (id == null) throw new IllegalArgumentException("Null id");
		try {
			return ofNullable(session.get(Movie.class, id));
		}
		catch (PersistenceException exception) {
			throw new DataException(exception.getMessage(), exception);
		}
	}
	
	/**
	 * Execute the query {@value #FIND_BY_DURATION_RANGE_int_int}.
	 *
	 * @see org.example.komplexJavaLabb1.persistence.MovieRepository#findByDurationRange(int,int)
	 **/
	@Override
	public Stream<Movie> findByDurationRange(int min, int max) {
		try {
			return session.createSelectionQuery(FIND_BY_DURATION_RANGE_int_int, Movie.class)
				.setParameter("min", min)
				.setParameter("max", max)
				.getResultStream();
		}
		catch (PersistenceException exception) {
			throw new DataException(exception.getMessage(), exception);
		}
	}
	
	@PersistenceUnit
	private EntityManagerFactory sessionFactory;
	
	@PostConstruct
	private void openSession() {
		session = sessionFactory.unwrap(SessionFactory.class).openStatelessSession();
	}
	
	@PreDestroy
	private void closeSession() {
		session.close();
	}
	
	@Inject
	MovieRepository_() {
	}

}

