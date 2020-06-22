package rest;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import beans.Album;
import beans.ResponseDataModel;
import business.MusicManager;
import exception.AlbumNotFoundException;

@RequestScoped
@Path("/music")
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class MusicRestService 
{
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getAlbumj/{title}/{artist}/{year}")
	public ResponseDataModel getAlbumj(@PathParam("title") String title, @PathParam("artist") String artist, @PathParam("year") int year)
	{
		MusicManager service = new MusicManager();
		Album album = new Album(title, artist, year, null);
		
		try 
		{
			ResponseDataModel returnModel = new ResponseDataModel(0, "", service.getAlbum(album));
			return returnModel;
		} 
		
		catch (AlbumNotFoundException e) {
			ResponseDataModel returnModel = new ResponseDataModel(-1, "Album not Found", new Album());
			System.out.println("=================> Album not found");
			return returnModel;
		}
		
		catch(Exception e2)
		{
			ResponseDataModel returnModel = new ResponseDataModel(-2, "System Exception", new Album());
			System.out.println("=================> System Exception");
			return returnModel;
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_XML)
	@Path("/getAlbumx/{title}/{artist}/{year}")
	public ResponseDataModel getAlbumx(@PathParam("title") String title, @PathParam("artist") String artist, @PathParam("year") int year)
	{
		MusicManager service = new MusicManager();
		Album album = new Album(title, artist, year, null);
		
		try 
		{
			ResponseDataModel returnModel = new ResponseDataModel(0, "", service.getAlbum(album));
			return returnModel;
		} 
		
		catch (AlbumNotFoundException e) {
			ResponseDataModel returnModel = new ResponseDataModel(-1, "Album not Found", new Album());
			System.out.println("=================> Album not found");
			return returnModel;
		}
		
		catch(Exception e2)
		{
			ResponseDataModel returnModel = new ResponseDataModel(-2, "System Exception", new Album());
			System.out.println("=================> System Exception");
			return returnModel;
		}
	}
}
