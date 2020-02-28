package com.comicbook.controller;

import java.util.Arrays;
import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comicbook.exception.ResourceNotFoundException;
import com.comicbook.model.ComicBook;
import com.comicbook.model.ComicUserEnrollment;
import com.comicbook.model.User;
import com.comicbook.repository.ComicBookRepository;
import com.comicbook.repository.ComicUserRepository;
import com.comicbook.repository.UserRepository;

/**
 * @author Ashish.manjhi
 * 
 * This {@link UserController} class is used to get User details.
 *
 */
@RestController
@RequestMapping("api/user")
public class UserController {

	/*
	 *  User Repository
	 */
	@Autowired
	private UserRepository userRepository;

	/*
	 *  Comic Book Repository
	 */
	@Autowired
	private ComicBookRepository comicBookRepository;

	/*
	 *  Comic User Repository
	 */
	@Autowired
	private ComicUserRepository comicUserRepository;


	/**
	 * PostMapping creates a new User in the database.
	 * 
	 * @param User details
	 * @return User
	 */
	@PostMapping()
	public  ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		return new  ResponseEntity<User>(userRepository.save(user),HttpStatus.CREATED);
	}

	/**
	 * GetMapping  provides the list of all the users in the database.
	 * 
	 * @return List of User details
	 */
	@GetMapping()
	public ResponseEntity<List<User>> getAllUsers(){
		return new ResponseEntity<List<User>>(userRepository.findAll(),HttpStatus.OK);
	}

	/**
	 * GetMapping provides detail of an user with a particular id in the
	 * database.
	 * 
	 * @param User id
	 * @return User details
	 */
	@GetMapping("/{id}")
	public User getUserById(@PathVariable int id) {
		return userRepository.findById(id).orElseThrow(
				()-> new ResourceNotFoundException("User",id)
				);
	}

	/**
	 * PutMapping updates the details of an user with a particular id in
	 * the database.
	 * 
	 * @param user details
	 * @return updated user
	 */
	@PutMapping("/{id}")
	public User updateUserById(@PathVariable(value = "id") Integer id,@Valid @RequestBody User user) {
		// Finds user by id, maps it's content, updates new values and save. Throws an exception if not found.
		return  this.userRepository.findById(id).map(toUpdate -> {
			toUpdate.setUsername(user.getUsername());
			toUpdate.setPassword(user.getPassword());
			toUpdate.setAge(user.getAge());
			toUpdate.setSex(user.getSex());
			toUpdate.setCountry(user.getCountry());
			toUpdate.setEmail(user.getEmail());            
			return userRepository.save(toUpdate);
		}).orElseThrow(() -> new ResourceNotFoundException("User", user.getId()));
	}

	/**
	 * DeleteMapping deletes a user with a particular id in the
	 * database.
	 * 
	 * @param User id
	 * @return Updated List of User.
	 */
	@DeleteMapping("/{id}") 
	public ResponseEntity<?> deleteUserById(@PathVariable int id){

		return this.userRepository.findById(id).map((toDelete) -> {
			this.userRepository.delete(toDelete);
			return ResponseEntity.ok("User id " + id + " deleted");
		}).orElseThrow(() -> new ResourceNotFoundException("User", id));
	}

	/**
	 *  PostMapping adds multiple comic book into the user library.
	 * 
	 * @param userId
	 * @param comicIds
	 */
	@PostMapping("/{userid}/comicbooks/{comicIds}")
	public void addMultiComicBookToUser(@PathVariable(value="userid") int userId,@PathVariable(value="comicIds") String comicIds) {

		// To split the ids.
		List<String> ids = Arrays.asList(comicIds.split(","));
		// Looping through each id.
		for(String i:ids)
		{
			// Get the user through user id.
			User users=this.userRepository.findById(userId).orElseThrow(
					() -> new ResourceNotFoundException("User", userId));
			ComicUserEnrollment enrollment=new ComicUserEnrollment();
			// Set user in comic user table.
			enrollment.setUser(users);
			// Get Comic Book through comic id.
			ComicBook comicBook = this.comicBookRepository.findById(Integer.parseInt(i)).orElseThrow(
					() -> new ResourceNotFoundException("ComicBook",Integer.parseInt(i)));	 
			// Set comic book in comic user table.
			enrollment.setComicBook(comicBook);
			enrollment.setRegDate(enrollment.getRegDate());

			this.comicUserRepository.save(enrollment);
		}
		return;


	}

	/**
	 * PostMapping add one comic book into the user library.
	 * @param User id
	 * @param comicBookId
	 * @return User
	 */
	@PostMapping("/{userid}/comicbook/{comicid}")
	public ComicUserEnrollment addComicBookToUser(@PathVariable(value="userid") int userId, @PathVariable(value="comicid") int comicId){
		// Finds the requested comic book.
		ComicBook comicBook = this.comicBookRepository.findById(comicId).orElseThrow(
				() -> new ResourceNotFoundException("ComicBook", comicId));

		// Finds the requested user.
		User users=this.userRepository.findById(userId).orElseThrow(
				() -> new ResourceNotFoundException("User", userId));

		// Inserting comic book and user into the comic user table.
		ComicUserEnrollment enrollment=new ComicUserEnrollment();
		enrollment.setComicBook(comicBook);
		enrollment.setUser(users);
		enrollment.setRegDate(enrollment.getRegDate());

		this.comicUserRepository.save(enrollment);
		return enrollment;
	}


	/**
	 * GetMapping finds all the user who read a particular genre of comic book.
	 * 
	 * @param genre
	 * @return
	 */
	@GetMapping("/genre/{genre}")
	public  ResponseEntity<List<User>> getUsersByComicBooksGenre(@PathVariable("genre") String genre){

		List<User> user = userRepository.findByComicUserEnrollmentsComicBookGenre(genre);
		if(user.isEmpty())
			throw new ResourceNotFoundException("User", genre);
		else
			return new ResponseEntity<List<User>>(user,HttpStatus.OK);
	}

	/**
	 * GetMapping finds all the user who read a particular genre of comic book and selected age.
	 * 
	 * @param genre
	 * @param age
	 * @return
	 */
	@GetMapping("/genre/{genre}/user/{age}")
	public  ResponseEntity<List<User>> getUsersByComicBooksGenreAndUserAge(@PathVariable("genre") String genre,@PathVariable("age") int age)
	{

		List<User> user = userRepository.findByComicUserEnrollmentsComicBookGenreAndAge(genre,age);
		if(user.isEmpty())
			throw new ResourceNotFoundException("User", genre,age);
		else
			return new ResponseEntity<List<User>>(user,HttpStatus.OK);
	}

}
