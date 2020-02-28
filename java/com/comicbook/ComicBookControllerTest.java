package com.comicbook;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.comicbook.model.ComicBook;

/**
 * @author Ashish.manjhi
 * This {@link ComicBookControllerTest} class is used to test the rest api endpoints of Comic book controller.
 *
 */
public class ComicBookControllerTest extends ComicApplicationTests {
	@Override
	@Before
	public void setUp() {
		super.setUp();
	}
	
	/**
	 * This test case checks the GET request endpoint of the comic books controller to get the list of comic books.
	 * @throws Exception
	 */
	@Test
	public void getComicBooks() throws Exception{
		String uri="/api/comic";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		ComicBook[] comicBooklist = super.mapFromJson(content, ComicBook[].class);
		assertTrue(comicBooklist.length > 0);
	}

	/**
	 * This test case checks the POST request endpoint of the comic books controller it creates a new comic book.
	 * @throws Exception
	 */
	@Test
	public void createComicBooks() throws Exception{
		String uri ="/api/comic";
		ComicBook comic=new ComicBook();
		comic.setTitle("Killer");
		comic.setDescription("A story of a silent Killer");
		comic.setGenre("triller");
		comic.setPublisher("Alter");
		comic.setReadTime("25min");
		comic.setWriter("Adam");
		String inputJson = super.mapToJson(comic);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(inputJson)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(201, status);
		String content = mvcResult.getResponse().getContentAsString();
		System.out.println(content);
	}

	/**
	 * This test case checks the PUT request endpoint of the comic books controller and updates a comic book using id of the comic book.
	 * @throws Exception
	 */
	@Test
	public void updateComicBooks() throws Exception{
		String uri="/api/comic/1";
		ComicBook comic=new ComicBook();
		comic.setTitle("JACK");
		comic.setDescription("hi");
		comic.setGenre("Adventure");
		comic.setPublisher("Fun");
		comic.setReadTime("21min");
		comic.setWriter("Tom");
		String inputJson = super.mapToJson(comic);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(inputJson)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		System.out.println(content);
	}

	/**
	 * This test case checks the DELETE request endpoint of the comic books controller to delete a comic book using comic id.
	 * @throws Exception
	 */
	@Test
	public void deleteComicBooks() throws Exception{

		String uri="/api/comic/2";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		System.out.println(content);
		assertEquals("ComicBook id 2 deleted", content);
	}


}
