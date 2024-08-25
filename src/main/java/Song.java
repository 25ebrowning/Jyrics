public class Song {
  Album album;
  String title;
  SongComponent[] components;

  public Song(String title, SongComponent[] components) {
    this.title = title;
    this.components = components;
  }

  public void setAlbum(Album album) {
    this.album = album;
  }

  public Artist getArtist() {
    return album.getArtist();
  }

  public Album getAlbum() {
    return album;
  }

  public String getTitle() {
    return title;
  }

  public int getYear() {
    return album.getYear();
  }

  public int getLines() {
    int totalLines = 0;
    for (int i = 0; i < components.length; i++) {
      totalLines += components[i].getLineCount();
    }
    return totalLines;
  }

  public int getWordCount() {
    int totalWords = 0;
    for (int i = 0; i < components.length; i++) {
      totalWords += components[i].getWordCount();
    }
    return totalWords;
  }

  public SongComponent[] getComponents() {
    return components;
  }

  public String getLyrics() {
    String[] everything = new String[components.length];
    for (int i = 0; i < components.length; i++) {
      everything[i] = components[i].toString();
    }
    return String.join("\n\n", everything);
  }

  public String toString() {
    return "\"" + title + "\" by " + getArtist().getName() + "\n(" +
        album.getName() + ", " + getYear() + ")\n\n" +
        getLyrics() + "\n\n";
  }
}