package controller;

import java.util.ArrayList;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import beans.Album;
import beans.Track;
import business.MusicManagerInterface;
import exception.AlbumNotFoundException;
import exception.TracksNotFoundException;

@ManagedBean
@ViewScoped
public class AlbumController 
{
	@EJB
	MusicManagerInterface mgr;
	
	public String onSubmit(Album album)
	{		
		// Call Music Manager Business Service
		//  Discussion: Why is this a bad coding technique or how could this be improved? You covered this in CST-235 and CST-341!
		try
		{
			album = mgr.addAlbum(album);
		} 
		catch (TracksNotFoundException e)
		{
			System.out.println("=================> Track not found");
		}
		
		// Forward to Test Response View along with the User Managed Bean
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("album", album);
		return "AddAlbumResponse.xhtml";

	}
	
	public String onFind(Album album)
	{
		
		try
		{
			album = mgr.getAlbum(album);
		}
		
		catch (AlbumNotFoundException e)
		{
			album.setArtist("?");
			album.setTitle("?");
			album.setYear(-1);
			album.setTracks(new ArrayList<Track>());
			System.out.println("=================> Album not found");
		}
		
		// Forward to Test Response View along with the User Managed Bean
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("album", album);
		return "AddAlbumResponse.xhtml";
	}
}
