package business;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import beans.Album;
import beans.Track;
import data.DataAccessInterface;
import exception.AlbumNotFoundException;
import exception.TracksNotFoundException;

@Stateless
@Local(MusicManagerInterface.class)
@LocalBean
public class MusicManager implements MusicManagerInterface
{	
	@EJB
	DataAccessInterface<Album> doa;
	
	@Inject
	TrackFinderInterface tf; 
	
	@EJB
	Cache cache;
	
	/**
	 * Default constructor.
	 */
	public MusicManager()
	{	
	}
	
	@Override
	public Album addAlbum(Album album) 
	{

		// Step 1: Get the Tracks for the specified Album
		album.setTracks(getTracks(album));
		
		// Step 2: Validate that Tracks were populated for the Album
		if(album.getNumberOfTracks() == 0)
			throw new TracksNotFoundException();
		
		// Step 3: Persist the Album in the database
		doa.create(album);
		
		// Return Album
		return doa.findBy(album);
	}
	
	public Album getAlbum(Album album) throws AlbumNotFoundException
	{
		Album newAlbum = cache.getObject(album);
		
		if(newAlbum != null)
		{
			return newAlbum;
		}
		
		newAlbum = doa.findBy(album);
		
		if(newAlbum == null)
		{
			throw new AlbumNotFoundException();
		}
		
		else
		{
			cache.putObject(newAlbum);
			return newAlbum;
		}
	}
	
	private List<Track> getTracks(Album album)
	{
		return tf.getTracks(album);
	}

}

