public class Artist {
  String name;
  Album[] albums;

  public Artist(String name, Album[] albums) {
    this.name = name;
    this.albums = albums;
  }


  public String getName() {
    return name;
  }

  public Album[] getAlbums() {
    return albums;
  }

  public Song[] getSongs() {
    int totalSongs = 0;
    for (int i = 0; i < albums.length; i++) {
      totalSongs += albums[i].getSongs().length;
    }
    Song[] allSongs = new Song[totalSongs];
    int counter = 0;
    for (int i = 0; i < albums.length; i++) {
      for (int j = 0; j < albums[i].getSongs().length; j++) {
        allSongs[counter] = albums[i].getSongs()[j];
        counter++;
      }
    }
    return allSongs;
  }

  public String toString() {
    String intro = name + "\n\n";
    if (albums.length == 0) {
      return intro + "no albums";
    } else {
      String[] albumStrings = new String[albums.length];
      for (int i = 0; i < albums.length; i++) {
        albumStrings[i] = " - " + albums[i].getName() + "\n" +
            " ".repeat(5) + albums[i].getType() + ", " + albums[i].getYear() + "\n";
      }
      return intro + String.join("\n", albumStrings) + "\n";
    }
  }
  void setupChildren() {
    for (Album album : albums) {
      if (album != null) {
        album.setArtist(this);
      }
    }
  }
}