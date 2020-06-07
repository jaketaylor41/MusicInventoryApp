package business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import beans.Album;
import beans.Track;
import exception.TracksNotFoundException;

@Stateless
@Local(MusicManagerInterface.class)
@LocalBean
public class MusicManager implements MusicManagerInterface {
	HashMap<String, List<Track>> trackInfo;

	/**
	 * Default constructor.
	 */
	public MusicManager() {
		// Initialize the Track Info
		trackInfo = new HashMap<String, List<Track>>();
		List<Track> tracks1 = new ArrayList<Track>();
		tracks1.add(new Track("More Than a Feeling", 1));
		tracks1.add(new Track("Peace of Mind", 2));
		tracks1.add(new Track("Foreplay / Long Time", 3));
		tracks1.add(new Track("Rock & Roll Band", 4));
		tracks1.add(new Track("Smokin'", 5));
		tracks1.add(new Track("Hitch a Ride", 6));
		tracks1.add(new Track("Something About You", 7));
		tracks1.add(new Track("Let Me Take You Home Tonight", 8));
		trackInfo.put("Boston - Boston - 1976", tracks1);
	}

	@Override
	public Album addAlbum(Album album) {
		// Discussion: How does the the MusicManager class and this method demonstrate
		// the use of the Facade Design Pattern?

		// Step 1: Get the Tracks for the specified Album
		album.setTracks(getTracks(album));

		// Step 2: Validate that Tracks were populated for the Album
		if (album.getNumberOfTracks() == 0)
			throw new TracksNotFoundException();

		// Step 3: Persist the Album in the database
		// TODO: will do this in a later In-Class Activity

		// Return Album
		return album;
	}

	private List<Track> getTracks(Album album) {
		String key = album.getArtist() + " - " + album.getTitle() + " - " + album.getYear();
		if (trackInfo.containsKey(key)) {
			return trackInfo.get(key);
		} else {
			return new ArrayList<Track>();
		}
	}
}
