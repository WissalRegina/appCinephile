package com.example.demo.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.ModelJason.Acthor;
import com.example.demo.ModelJason.Genre;
import com.example.demo.ModelJason.Movie;
import com.example.demo.ModelJason.Review;
import com.example.demo.entities.ReviewEntity;
import com.example.demo.entities.UserEntity;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.MovieService;

@Controller
@RequestMapping("/user")
public class UserController {
	private final MovieService movieService;
	
	public UserController(MovieService movieService) {
        this.movieService = movieService;
    }
	
	@Autowired
	private UserRepository userRepo;

	@ModelAttribute
	private void userDetails(Model m, Principal p) {
		String email = p.getName();
		UserEntity user = userRepo.findByEmail(email);

		m.addAttribute("user", user);

	}
	
	
	@GetMapping("/")
	public String home(Model model) {
		List<Movie> movies = movieService.getPopularMovies();
        model.addAttribute("movies", movies);
        List<Genre> genres = movieService.getGenres();
	     model.addAttribute("genres", genres);
		return "user/home";
	}
	
	 @GetMapping("/movie/{id}")
	    public String getMovieDetails(@PathVariable("id") Long id, Model model, Principal p) {
	        Movie movieDetails = movieService.getMovieDetails(id);
	        model.addAttribute("movieDetails", movieDetails);
	       // List<Genre> genres = movieService.getGenresForMovie(id);
	        //model.addAttribute("genres", genres);
	        
	        List<Genre> genres = movieService.getGenres();
		     model.addAttribute("genres", genres);
		     
	        List<Acthor> acteurs = movieService.getMovieActors(id);
	        model.addAttribute("actors", acteurs);
	        
	        String trailerLink = movieService.getMovieTrailer(id);
	        model.addAttribute("trailerLink", trailerLink);
	        
	        //Recupérer similar movies
	        List<Movie> similarMovies = movieService.getSimilarMovies(id);
	        model.addAttribute("similarMovies", similarMovies);
	        
	        //récuperer l 'id de l'utilisateur 
	        String email = p.getName();
	        UserEntity user = userRepo.findByEmail(email);
	        Long idUser = user.getId();
	        String nomUser = user.getFullName();
	        model.addAttribute("idUser", idUser);
	        model.addAttribute("nomUser", nomUser);
	        
	     // Vérifier si le film est déjà dans la liste des envies de l'utilisateur
	        boolean isMovieInWishlist = movieService.isMovieInWishlist(idUser, id);
	        model.addAttribute("isMovieInWishlist", isMovieInWishlist);
	        
	     // Vérifier si le film est déjà dans la liste des favoris de l'utilisateur
	        boolean isMovieInFavorislist = movieService.isMovieInFavorislist(idUser, id);
	        model.addAttribute("isMovieInFavorislist", isMovieInFavorislist);   
	        
	      //Recupérer Review movies from tmdb
	        List<Review> reviews = movieService.getMovieReviews(id);
	        model.addAttribute("reviews", reviews);
	        
	      // recuperer Review juste ajouté par l'utilisateur  
	        List<ReviewEntity> r = movieService.getReviews(idUser);
	        model.addAttribute("reviewsAjoute", r);
	        
	        
	        
	        return "user/moviedetails";
	    }
	 
	 //search 
	 @GetMapping("/search")
	    public String searchMovies(@RequestParam("query") String query, Model model) {
	        List<Movie> movies = movieService.searchMovies(query);
	        model.addAttribute("movies", movies);
	        //model.addAttribute("lastQuery", query);
	        return "user/searchresults";
	    }
	 /*
	 //fetch all genres
	 @GetMapping("/genres")
	 public String showGenres(Model model) {
	     List<Genre> genres = movieService.getGenres();
	     model.addAttribute("genres", genres);
	     return "user/home";
	 }  */
	 
	 // get all movies by genre 
	 @GetMapping("/movies-by-genre")
	 public String getMoviesByGenre(@RequestParam("genreId") int genreId, Model model) {
	     List<Movie> movies = movieService.getMoviesByGenre(genreId);
	     model.addAttribute("movies", movies);
	     model.addAttribute("lastQuery", "Genre: " + genreId);
	     return "user/searchResultsGenre";
	 }
	 
	 
	 // add to wachList 
	 @PostMapping("/{userId}/add/{movieId}")
	    public ResponseEntity<String> addToWishlist(@PathVariable("userId") long userId, @PathVariable("movieId") long movieId) {
	        movieService.addToWishlist(userId, movieId);
	        return ResponseEntity.ok("Film ajouté à la liste d'envies");
	    }
	 
	 // delete from watchList
	 @DeleteMapping("/{userId}/remove/{movieId}")
	    public ResponseEntity<String> removeFromWishlist(@PathVariable("userId") long userId, @PathVariable("movieId") long movieId) {
		 movieService.removeFromWishlist(userId, movieId);
	        return ResponseEntity.ok("Film supprimé de la liste d'envies");
	    }
	 
	 @GetMapping("/watchList")
	 public String getWishList( Model model, Principal p) {
		 String email = p.getName();
		 UserEntity user = userRepo.findByEmail(email);
	     Long idUser = user.getId();
	     List<Movie> movies = movieService.getWishesList(idUser);
	     model.addAttribute("watchlist", movies);
	     model.addAttribute("idUser", idUser);
	     
	     List<Genre> genres = movieService.getGenres();
	     model.addAttribute("genres", genres);
	     
	  // Vérifier si le film est déjà visualisee par l'utilisateur
	   //  boolean isWatched = movieService.isMovieWatched(idUser,idMovie);
	   // model.addAttribute("isWatched", isWatched);
	     
	     return "user/watchlist";
	 }
	 
	
	  // add to watchedList 
	   @PostMapping("/{userId}/watched/add/{movieId}")
	    public ResponseEntity<String> addWatched(@PathVariable("userId") long userId, @PathVariable("movieId") long movieId) {
	        movieService.WatchedMovies(userId, movieId);
	        return ResponseEntity.ok("Movie already Watched");
	    }
	 
	 
	 // add to FavorisList 
	 @PostMapping("/{userId}/favoris/add/{movieId}")
	    public ResponseEntity<String> addToFavorislist(@PathVariable("userId") long userId, @PathVariable("movieId") long movieId) {
	        movieService.addToFavoritelist(userId, movieId);
	        return ResponseEntity.ok("Film ajouté à la liste des favoris");
	    }
	 
	// delete from FavorisList
		 @DeleteMapping("/{userId}/favoris/remove/{movieId}")
		    public ResponseEntity<String> removeFromFavorislist(@PathVariable("userId") long userId, @PathVariable("movieId") long movieId) {
			 movieService.removeFromFavorislist(userId, movieId);
		        return ResponseEntity.ok("Film supprimé de la liste des favoris");
		    }
	
		 @GetMapping("/favoris")
		 public String getfavorsList( Model model, Principal p) {
			 String email = p.getName();
			 UserEntity user = userRepo.findByEmail(email);
		     Long idUser = user.getId();
		     List<Movie> movies = movieService.getFavorisList(idUser);
		     model.addAttribute("favoris", movies);
		     
		     List<Genre> genres = movieService.getGenres();
		     model.addAttribute("genres", genres);
		     return "user/favoris";
		 }
		 
		  // add to Review 
	 @PostMapping("/{userId}/reviews/add/{movieId}")
	    public ResponseEntity<String> addReview(@PathVariable("userId") long userId, @PathVariable("movieId") long movieId,
	    		 @RequestParam("rating") int rating, @RequestParam("comment") String comment,
                 @RequestParam("avatar") String avatar) {
	        movieService.addReview(userId, movieId, rating, comment, avatar);
	        return ResponseEntity.ok("Review ajouté");
	    }
	 
	 //get Now Playing
	 @GetMapping("/nowPlaying")
	 public String getnowPlayingMovies(Model model) {
		 List<Movie> moovies = movieService.getNowPlayingMovies();
		 model.addAttribute("movies", moovies);
		 List<Genre> genres = movieService.getGenres();
	     model.addAttribute("genres", genres);
		 return "user/nowPlaying";
	 }
	 
	 //get top rated
	 @GetMapping("/topRated")
	 public String gettopRatedMovies(Model model) {
		 List<Movie> moovies = movieService.getTopRatedMovies();
		 model.addAttribute("movies", moovies);
		 List<Genre> genres = movieService.getGenres();
	     model.addAttribute("genres", genres);
		 return "user/topRated";
	 }
	 
	//get up coming
		 @GetMapping("/upComing")
		 public String getUpcoming(Model model) {
			 List<Movie> moovies = movieService.getUpComingMovies();
			 model.addAttribute("movies", moovies);
			 List<Genre> genres = movieService.getGenres();
		     model.addAttribute("genres", genres);
			 return "user/upComing";
		 }
	 
	 
		 
		 

	 
	 


}
