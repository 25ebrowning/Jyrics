public class Main {
  public static void main(String[] args) {
    MusicLibrary library = new MusicLibrary("src/TemplateLibrary");
    System.out.println(library.getArtists()[0].getAlbums()[1].getSongs()[0].toString());

    System.out.println(library.getArtists()[0].toString());
  }
}