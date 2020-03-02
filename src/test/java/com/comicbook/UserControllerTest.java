package com.comicbook;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.comicbook.model.ComicBook;
import com.comicbook.model.ComicUserEnrollment;
import com.comicbook.model.User;

/**
 * @author Ashish.manjhi
 * This {@link UserControllerTest} class is used to test the rest api endpoints of User controller.
 *
 */
public class UserControllerTest extends ComicApplicationTests {
	@Override
	@Before
	public void setUp() {
		super.setUp();
	}
	
	/**
	 * This test case checks the GET request endpoint of the user controller to get all the users.
	 * @throws Exception
	 */
	@Test
	public void getUsers() throws Exception{
		String uri="/api/user";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		System.out.println(content);
		User[] userlist = super.mapFromJson(content, User[].class);
		assertTrue(userlist.length > 0);
	}

	/**
	 * This test case checks the POST request endpoint of the user controller to insert a new user.
	 * @throws Exception
	 */
	@Test
	public void createUser() throws Exception{
		String uri ="/api/user";
		User user=new User();
		user.setUsername("Suyash");
		user.setPassword("su123");
		user.setAge(26);
		user.setCountry("India");
		user.setEmail("suy25");
		user.setSex("Male");
		String inputJson = super.mapToJson(user);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(inputJson)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(201, status);
		String content = mvcResult.getResponse().getContentAsString();
		System.out.println(content);
	}

	/**
	 * This test case checks the PUT request endpoint of the user controller to update the user details using user id.
	 * @throws Exception
	 */
	@Test
	public void updateUser() throws Exception{
		String uri="/api/user/4";
		User user=new User();
		user.setUsername("Ashish");
		user.setPassword("red");
		user.setAge(23);
		user.setCountry("India");
		user.setEmail("ash123");
		user.setSex("Male");
		String inputJson = super.mapToJson(user);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(inputJson)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		System.out.println(content);
	}

	/**
	 * This test case checks the DELETE request endpoint of the user controller to delete a user by using user id.
	 * @throws Exception
	 */
	@Test
	public void deleteUser() throws Exception{
		String uri="/api/user/2";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		System.out.println(content);
	}
	
	/**
	 * This test case check the GET endpoint for user to get user by comic genre.
	 * 
	 * @throws Exception
	 */
	@Test
	public void getUserByGenre() throws Exception{
		String uri="/api/user/genre/Action";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		User[] userlist = super.mapFromJson(content, User[].class);
		
	}
	
	/**
	 *  This test case check the POST endpoint for mapping comic books and users with each other.
	 * @throws Exception
	 */
	@Test
	public void addComicToUser() throws Exception{
		String uri ="/api/user/4/comicbook/3";
		
		String uri1="/api/user/4";
		MvcResult mvcResultuser = mvc.perform(MockMvcRequestBuilders.get(uri1)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		String content1 = mvcResultuser.getResponse().getContentAsString();
		User userlist = super.mapFromJson(content1, User.class);
		
		String uri2="/api/comic/3";
		MvcResult mvcResultcomic = mvc.perform(MockMvcRequestBuilders.get(uri2)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		String content2 = mvcResultcomic.getResponse().getContentAsString();
		ComicBook comic= super.mapFromJson(content2, ComicBook.class);
		
		ComicUserEnrollment enrollment=new ComicUserEnrollment();
		enrollment.setComicBook(comic);
		enrollment.setUser(userlist);
		
		String inputJson = super.mapToJson(enrollment);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(inputJson)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}
}
