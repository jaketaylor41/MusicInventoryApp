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
		tracks1.add(new Track("Taxman", 1));
		tracks1.add(new Track("Eleanor Rigby", 2));
		tracks1.add(new Track("I'm Only Sleeping", 3));
		tracks1.add(new Track("Love You To", 4));
		tracks1.add(new Track("Here, There and Everywhere", 5));
		tracks1.add(new Track("Yellow Submarine", 6));
		tracks1.add(new Track("She Said She Said", 7));
		tracks1.add(new Track("Good Day Sunshine", 8));
		tracks1.add(new Track("And Your Bird Can Sing", 9));
		tracks1.add(new Track("For No One", 10));
		tracks1.add(new Track("Doctor Robert", 11));
		tracks1.add(new Track("I Want to Tell You", 12));
		tracks1.add(new Track("Got to Get You into My Life", 13));
		tracks1.add(new Track("Tomorrow Never Knows", 14));
		trackInfo.put("The Beatles - Revolver - 1966", tracks1);
	}

	@Override
	public Album addAlbum(Album album) {

		// Step 1: Get the Tracks for the specified Album
		album.setTracks(getTracks(album));

		// Step 2: Validate that Tracks were populated for the Album
		if (album.getNumberOfTracks() == 0)
			throw new TracksNotFoundException();

		// Step 3: Persist the Album in the database
		// This will be activity 5 - Create a MySQL connection to add to DB

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
