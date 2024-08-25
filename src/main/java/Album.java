public class Album {
  Artist artist;
  String album;
  int year;
  Song[] songs;

  public Album(String album, int year, Song[] songs) {
    this.album = album;
    this.year = year;
    this.songs = songs;
  }

  public void setArtist(Artist artist) {
    this.artist = artist;
  }

  public String getName() {
    return album;
  }

  public Song[] getSongs() {
    return songs;
  }

  public Artist getArtist() {
    return artist;
  }

  public int getYear() {
    return year;
  }

  public String getType() {
    if (songs.length == 0) {
      return "no songs";
    } else if (songs.length == 1) {
      return "single";
    } else {
      return songs.length + " songs";
    }
  }

  public String toString() {
    String[] songList = new String[songs.length];
    for (int i = 0; i < songs.length; i++) {
      songList[i] = songs[i].getTitle();
    }
    String intro = "\"" + album + "\" by " + artist.getName() + " (" + year + ")\n" + getType();
    if (songs.length == 0) {
      return intro;
    } else {
      return intro + "\n\n - " + String.join("\n - ", songList);
    }
  }
  void setupChildren() {
    for (Song song : songs) {
      song.setAlbum(this);
    }
  }
}