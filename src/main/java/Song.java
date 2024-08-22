public class Song {
  // String artist;
  // String album;
  Album album;
  String track;
  int year;
  SongComponent [] components;
  public Song(Album album,
              String track,
              int year,
              SongComponent [] components) {
    this.artist = artist;
    this.album = album;
    this.track = track;
    this.year = year;
    this.components = components;
  }
  public Artist getArtist() {
    return album.getArtist();
  }
  public Album getAlbum() {
    return album;
  }
  public String getTrack() {
    return track;
  }
  public int getReleased() {
    return get
  }
  public SongComponent [] getComponents() {
    return components;
  }
  public String getLyrics() {
    String [] everything = new String[components.length];
    for (int i = 0; i < components.length; i++) {
      everything[i] = components[i].toString();
    }
    return String.join("\n\n", everything);
  }
  public String toString() {
    return "\"" + track + "\" by " + artist + "\n(" + album + ", " + released + ")\n\n" + getLyrics(); 
  }
}