package business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;

import beans.Album;
import beans.Track;


@Stateless
@Local(TrackFinderInterface.class)
@LocalBean
@Alternative
public class TrackFinder implements TrackFinderInterface {
	
	HashMap<String, List<Track>> trackInfo;

	@Override
	public List<Track> getTracks(Album album) 
	{
		String key = album.getArtist() + " - " + album.getTitle() + " - " + album.getYear();
		if(trackInfo.containsKey(key))
		{
			return trackInfo.get(key);
		}
		else
		{
			return new ArrayList<Track>();
		}
	}
	
	/**
	 * Default constructor.
	 */
	public TrackFinder()
	{	
		// Initialize the Track Info
		trackInfo = new HashMap<String, List<Track>>();
		List<Track> tracks1 = new ArrayList<Track>();
		tracks1.add(new Track("More Than a Feeling", 1));
		tracks1.add(new Track("Peace of Mind", 2));
		tracks1.add(new Track("Foreplay / Long Time", 3));
		tracks1.add(new Track("Rock & Roll Band", 4));
		tracks1.add(new Track("Smokin", 5));
		tracks1.add(new Track("Hitch a Ride", 6));
		tracks1.add(new Track("Something About You", 7));
		tracks1.add(new Track("Let Me Take You Home Tonight", 8));
		trackInfo.put("Boston - Boston - 1976", tracks1);
	}

}
