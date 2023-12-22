package com.example.demo.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.demo.ModelJason.Acthor;
import com.example.demo.ModelJason.Genre;
import com.example.demo.ModelJason.GenreResponse;
import com.example.demo.ModelJason.Movie;
import com.example.demo.ModelJason.MovieCredits;
import com.example.demo.ModelJason.MovieResponse;
import com.example.demo.ModelJason.Review;
import com.example.demo.ModelJason.ReviewResponse;
import com.example.demo.ModelJason.Trailer;
import com.example.demo.ModelJason.TrailerResults;
import com.example.demo.entities.Favorite;
import com.example.demo.entities.FavoriteCle;
import com.example.demo.entities.MovieEntity;
import com.example.demo.entities.ReviewCle;
import com.example.demo.entities.ReviewEntity;
import com.example.demo.entities.UserEntity;
import com.example.demo.entities.WatchedCle;
import com.example.demo.entities.WatchedList;
import com.example.demo.entities.Wishes;
import com.example.demo.entities.WishesCle;
import com.example.demo.repositories.FavoriteRepository;
import com.example.demo.repositories.MovieRepository;
import com.example.demo.repositories.ReviewRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.repositories.WatchedRepository;
import com.example.demo.repositories.WishesRepository;

@Service
public class MovieService {
    private final WebClient webClient;

    @Value("${tmdb.api.key}")
    private String apiKey; // Clé d'API TMDB (récupérée depuis application.properties ou application.yml)

    public MovieService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://api.themoviedb.org/3").build();
    }

    public List<Movie> getPopularMovies() {
        MovieResponse movieResponse = webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/movie/popular")
                        .queryParam("api_key", apiKey)
                        .build())
                .retrieve()
                .bodyToMono(MovieResponse.class)
                .block();

        if (movieResponse != null) {
            return movieResponse.getResults();
        }

        return null;
    }
    
    public Movie getMovieDetails(Long movieId) {
        Movie movieDetails = webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/movie/{movieId}")
                        .queryParam("api_key", apiKey)
                        .build(movieId))
                .retrieve()
                .bodyToMono(Movie.class)
                .block();

        return movieDetails;
    }
    
    //search Movie
    public List<Movie> searchMovies(String query) {
    	MovieResponse mr = webClient.get()
    			.uri(uriBuilder -> uriBuilder.path("/search/movie")
    					 .queryParam("api_key", apiKey)
    					 .queryParam("query", query)
    					 .build())
    			.retrieve()
    			.bodyToMono(MovieResponse.class)
    			.block();
    	if(mr != null) {
    		return mr.getResults();
    	}
    	return Collections.emptyList();
       
    }
    
    //get Genre
    public List<Genre> getGenres() {
        GenreResponse genreResponse = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/genre/movie/list")
                        .queryParam("api_key", apiKey)
                        .build())
                .retrieve()
                .bodyToMono(GenreResponse.class)
                .block();
        if (genreResponse != null) {
            return genreResponse.getGenres();
        }
        return Collections.emptyList();
    }

    
    // get Movie by genre
    public List<Movie> getMoviesByGenre(int genreId) {
        MovieResponse movieResponse = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/discover/movie")
                        .queryParam("api_key", apiKey)
                        .queryParam("with_genres", genreId)
                        .build())
                .retrieve()
                .bodyToMono(MovieResponse.class)
                .block();
        if (movieResponse != null) {
            return movieResponse.getResults();
        }
        return Collections.emptyList();
    }
    
    
    /*
    // get genre by movie Id
    public List<Genre> getGenresForMovie(Long movieId) {
        GenreResponse genreResponse = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/movie/{id}/genres")
                        .queryParam("api_key", apiKey)
                        .build(movieId))
                .retrieve()
                .bodyToMono(GenreResponse.class)
                .block();
        if (genreResponse != null) {
            return genreResponse.getGenres();
        }
        return Collections.emptyList();
    }*/
    
    //get Actors
    public List<Acthor> getMovieActors(Long movieId) {
        MovieCredits movieCredits = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/movie/{id}/credits")
                        .queryParam("api_key", apiKey)
                        .build(movieId))
                .retrieve()
                .bodyToMono(MovieCredits.class)
                .block();
        
        if (movieCredits != null) {
            return movieCredits.getCast();
        }
        
        return Collections.emptyList();
    }
    
    //get Video or trailer 
    public String getMovieTrailer(Long movieId) {
        TrailerResults response = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/movie/{movieId}/videos")
                        .queryParam("api_key", apiKey)
                        .build(movieId))
                .retrieve()
                .bodyToMono(TrailerResults.class)
                .block();

        if (response != null) {
            List<Trailer> videos = response.getTrailers();
            if (!videos.isEmpty()) {
                Trailer firstVideo = videos.get(0);
                return "https://www.youtube.com/watch?v=" + firstVideo.getKey();
            }
        }

        return "Aucune bande-annonce disponible";
    }
    
    // get similar moivies
    public List<Movie> getSimilarMovies(Long movieId) {
        String url = "/movie/{movieId}/similar";
        MovieResponse response = webClient.get()
                .uri(uriBuilder -> uriBuilder.path(url)
                        .queryParam("api_key", apiKey)
                        .build(movieId))
                .retrieve()
                .bodyToMono(MovieResponse.class)
                .block();

        if (response != null) {
            return response.getResults();
        }

        return Collections.emptyList();
    }


    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private WishesRepository wishesRepository;
    @Autowired
    private FavoriteRepository favoriteRepository;
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private WatchedRepository watchedRepository;
    
    
    public void addToWishlist(long userId, long movieId) {
        UserEntity user = userRepository.findById(userId).get();
        MovieEntity movie = movieRepository.findById(movieId).orElseGet(() -> {
            MovieEntity newMovie = new MovieEntity();
            newMovie.setMovieId(movieId);
            return movieRepository.save(newMovie);
        });


        WishesCle wishesCle = new WishesCle();
        wishesCle.setId(userId);
        wishesCle.setMovieId(movieId);

        Wishes wishes = new Wishes();
        wishes.setId(wishesCle);
        wishes.setUser(user);
        wishes.setMovie(movie);

        wishesRepository.save(wishes);
    }
    
    public boolean isMovieInWishlist(long userId, long movieId) {
        return wishesRepository.existsById(new WishesCle(userId, movieId));
    }
    
    // remove wish List
    public void removeFromWishlist(long userId, long movieId) {
        WishesCle wishesCle = new WishesCle();
        wishesCle.setId(userId);
        wishesCle.setMovieId(movieId);

        wishesRepository.deleteById(wishesCle);
    }
    
    // get List Wishes 
    public List<Movie> getWishesList(Long idUser){
    	List<Movie> movies = new ArrayList<>();
    	List<Wishes> wishes = wishesRepository.findAll();
    	for(int i =0;i<wishes.size();i++) {
    		Long id_Movie = wishes.get(i).getId().getMovieId();
    		Movie m = getMovieDetails(id_Movie);
    		movies.add(m);
    	}return movies;
    	/*
    	List<Movie> movies = new ArrayList<>();
    	UserEntity user = userRepository.findById(idUser).get();
    	Set<Wishes> w = user.getWishes();
    	for(int i =0 ;i<w.size();i++) {
    		
    		Long id_movie = w.
    	}*/
    	
    	
    }
    
    public void addToFavoritelist(long userId, long movieId) {
        UserEntity user = userRepository.findById(userId).get();
        MovieEntity movie = movieRepository.findById(movieId).orElseGet(() -> {
            MovieEntity newMovie = new MovieEntity();
            newMovie.setMovieId(movieId);
            return movieRepository.save(newMovie);
        });


        FavoriteCle favoriteCle = new FavoriteCle();
        favoriteCle.setId(userId);
        favoriteCle.setMovieId(movieId);

        Favorite favorite = new Favorite();
        favorite.setId(favoriteCle);
        favorite.setUser(user);
        favorite.setMovie(movie);

        favoriteRepository.save(favorite);
    }
    
    public boolean isMovieInFavorislist(long userId, long movieId) {
        return favoriteRepository.existsById(new FavoriteCle(userId, movieId));
    }
    
    // remove favoris List
    public void removeFromFavorislist(long userId, long movieId) {
        FavoriteCle favorisCle = new FavoriteCle();
        favorisCle.setId(userId);
        favorisCle.setMovieId(movieId);
        
        favoriteRepository.deleteById(favorisCle);
    }
    
 // get List favoris 
    public List<Movie> getFavorisList(Long idUser){
    	List<Movie> movies = new ArrayList<>();
    	List<Favorite> favoris = favoriteRepository.findAll();
    	for(int i =0;i<favoris.size();i++) {
    		Long id_Movie = favoris.get(i).getId().getMovieId();
    		Movie m = getMovieDetails(id_Movie);
    		movies.add(m);
    	}return movies;
    	
    }
    
    // get all Review 
    public List<Review> getMovieReviews(Long movieId) {
        String url = "/movie/{movie_id}/reviews";
        ReviewResponse response = webClient.get()
                .uri(uriBuilder -> uriBuilder.path(url)
                        .queryParam("api_key", apiKey)
                        .build(movieId))
                .retrieve()
                .bodyToMono(ReviewResponse.class)
                .block();

        if (response != null) {
            return response.getResults();
        }

        return Collections.emptyList();
    }
    
    
    //add review 
    public void addReview(long userId, long movieId, int rating, String comment, String avatar) {
        UserEntity user = userRepository.findById(userId).get();
        MovieEntity movie = movieRepository.findById(movieId).orElseGet(() -> {
            MovieEntity newMovie = new MovieEntity();
            newMovie.setMovieId(movieId);
            return movieRepository.save(newMovie);
        });


        ReviewCle reviewCle = new ReviewCle();
        reviewCle.setId(userId);
        reviewCle.setMovieId(movieId);

        ReviewEntity review = new ReviewEntity();
        review.setId_review(reviewCle);
        review.setUser(user);
        review.setMovie(movie);
        review.setRating(rating);
        review.setComment(comment);
        review.setAvatar(avatar);

        reviewRepository.save(review);
    }
    
    
    //get All review added by user 
    public List<ReviewEntity> getReviews(Long idUser){
    	/* ReviewCle reviewCle = new ReviewCle();
    	    reviewCle.setId(idUser);
    	    reviewCle.setMovieId(idMovie);
    	    
    	    ReviewEntity reviewEntity = new ReviewEntity();
    	    reviewEntity.setId_review(reviewCle);
    	    
    	    return reviewRepository.findAllById(Collections.singleton(reviewEntity));
    	ReviewEntity reviewEntity = new ReviewEntity();
    	ReviewCle reviewCle = new ReviewCle();
    	reviewCle.setId(idUser);
    	reviewCle.setMovieId(idMovie);
    	
    	return reviewRepository.findAllById(reviewEntity.setId_review(reviewCle));  */
    	return reviewRepository.findAll();
    }
    
    
      public void WatchedMovies(long userId, long movieId) {
        UserEntity user = userRepository.findById(userId).get();
        MovieEntity movie = movieRepository.findById(movieId).get();


        WatchedCle watchedCle = new WatchedCle();
        watchedCle.setId(userId);
        watchedCle.setMovieId(movieId);

        WatchedList watched = new WatchedList();
        watched.setId(watchedCle);
        watched.setUser(user);
        watched.setMovie(movie);

        watchedRepository.save(watched);
    }
      
      public boolean isMovieWatched(long userId, long movieId) {
    	  /*List<WatchedList> watchedList = watchedRepository.findAll();
    	  for(int i =0;i<watchedList.size();i++) {
    		  Long ideUser = watchedList.get(i).getUser().getId();
    		  if(ideUser == userId) return true;
    	  }
          return false;*/
    	  
    	  return watchedRepository.existsById(new WatchedCle(userId, movieId));
    	  
      }
      
      //get  now Playing Movies 
      public List<Movie> getNowPlayingMovies() {
        MovieResponse movieResponse = webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/movie/now_playing")
                        .queryParam("api_key", apiKey)
                        .build())
                .retrieve()
                .bodyToMono(MovieResponse.class)
                .block();

        if (movieResponse != null) {
            return movieResponse.getResults();
        }

        return null;
    }
      
      
    //get  now Playing Movies 
      public List<Movie> getTopRatedMovies() {
        MovieResponse movieResponse = webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/movie/top_rated")
                        .queryParam("api_key", apiKey)
                        .build())
                .retrieve()
                .bodyToMono(MovieResponse.class)
                .block();

        if (movieResponse != null) {
            return movieResponse.getResults();
        }

        return null;
    }
      
    //get  now UpCaming Movies 
      public List<Movie> getUpComingMovies() {
        MovieResponse movieResponse = webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/movie/upcoming")
                        .queryParam("api_key", apiKey)
                        .build())
                .retrieve()
                .bodyToMono(MovieResponse.class)
                .block();

        if (movieResponse != null) {
            return movieResponse.getResults();
        }

        return null;
    }



    
    
    
    
    
    
}