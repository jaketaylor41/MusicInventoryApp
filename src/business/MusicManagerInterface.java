package business;

import javax.ejb.Local;

import beans.Album;
import exception.AlbumNotFoundException;

@Local
public interface MusicManagerInterface {
	
	public Album addAlbum(Album album);
	public Album getAlbum(Album album) throws AlbumNotFoundException;

}
