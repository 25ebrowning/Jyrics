public class MusicLibrary {
  String libraryPath;
  Artist[] artists;

  public MusicLibrary(String libraryPath) {
    this.libraryPath = libraryPath;
    artists = YamlParser.parseLibrary(libraryPath);  
  }
  public Artist[] getArtists() {
    return artists;
  }
}