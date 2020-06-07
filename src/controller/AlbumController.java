package controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import beans.Album;
import business.MusicManager;
import exception.TracksNotFoundException;

@ManagedBean
@ViewScoped
public class AlbumController
{
	public String onSubmit(Album album)
	{
		// Forward to Test Response View along with the User Managed Bean
		FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("album", album);
		
		 // Call Music Manager Business Service
        //  Discussion: Why is this a bad coding technique or how could this be improved? You covered this in CST-235 and CST-341!
        MusicManager mgr = new MusicManager();
        try
        {
            album = mgr.addAlbum(album);
        } 
        catch (TracksNotFoundException e)
        {
            System.out.println("=================> Album not found");
        }
        
        // Forward to Test Response View along with the User Managed Bean
        FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("album", album);
        return "AddAlbumResponse.xhtml";
	}
}
