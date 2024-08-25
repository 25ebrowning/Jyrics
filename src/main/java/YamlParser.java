import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.io.FileReader;
import org.yaml.snakeyaml.Yaml;
import java.util.ArrayList;

public class YamlParser {
  public static Artist[] parseLibrary(String libraryPath) {
    File root = new File(libraryPath);
    ArrayList<Artist> artists = new ArrayList<Artist>();
    File[] artistFolders = root.listFiles();
    for (File artistFolder : artistFolders) {
      if (artistFolder.isDirectory()) {
        ArrayList<Album> albums = new ArrayList<Album>();
        File[] albumFolders = artistFolder.listFiles();
        for (File albumFolder : albumFolders) {
          if (albumFolder.isDirectory()) {
            ArrayList<Song> songs = new ArrayList<Song>();
            File[] songFiles = albumFolder.listFiles();
            for (File songFile : songFiles) {
              if (songFile.isFile() && songFile.getName().endsWith(".yaml")) {
                try {
                  Yaml yaml = new Yaml();
                  Map<String, Object> songMap = yaml.load(new FileReader(songFile));
                  ArrayList<SongComponent> components = new ArrayList<SongComponent>();
                  @SuppressWarnings("unchecked")
                  List<Map<String, Object>> componentMaps = (List<Map<String, Object>>) songMap.get("components");
                  for (Map<String, Object> componentMap : componentMaps) {
                    String[] lines = new String[0];
                    if (componentMap.containsKey("lines")) {
                      @SuppressWarnings("unchecked")
                      List<String> lineList = (List<String>) componentMap.get("lines");
                      lines = lineList.toArray(new String[0]);
                    }
                    
                    components.add(new SongComponent((String) componentMap.get("type"), lines));
                  }
                  String name = songFile.getName().substring(0, songFile.getName().length()-5);
                  songs.add(new Song(name, components.toArray(new SongComponent[0])));
                }
                catch (FileNotFoundException e) {}
              }
            }
            String folderName = albumFolder.getName();
            String name = folderName.substring(0, folderName.length() - 7).trim();
            int year = Integer.parseInt(folderName.substring(folderName.length() - 5, folderName.length() - 1));

            Album album = new Album(name, year, songs.toArray(new Song[0]));
            album.setupChildren();
            albums.add(album);
          }
        }
        Collections.sort(albums, new Comparator<Album>() {
            
            public int compare(Album a1, Album a2) {
                return Integer.compare(a1.getYear(), a2.getYear());
            }
        });
        Artist artist = new Artist(artistFolder.getName(), albums.toArray(new Album[0]));
        artist.setupChildren();
        artists.add(artist); 
      }
    }
    return artists.toArray(new Artist[0]);
  }
  /*
   * public static Artist[] parseLibrary(String libraryPath) {
   * File root = new File(libraryPath);
   * Artist[] artists = new Artist[0];
   * if (root.isDirectory()) {
   * File[] contents = root.listFiles();
   * int counter = 0;
   * for (File file : contents) {
   * if (file.isDirectory()) {
   * for (File subFile : file.listFiles()) {
   * if (subFile.isFile() && subFile.getName().equals("artist.yaml")) {
   * counter++;
   * }
   * }
   * }
   * }
   * artists = new Artist[counter];
   * File[] artistFolders = new File[counter];
   * counter = 0;
   * for (File file : contents) {
   * if (file.isDirectory()) {
   * for (File subFile : file.listFiles()) {
   * if (subFile.isFile() && subFile.getName().equals("artist.yaml")) {
   * artistFolders[counter] = file;
   * counter++;
   * }
   * }
   * }
   * }
   * for (int i = 0; i < artistFolders.length; i++) {
   * contents = artistFolders[i].listFiles();
   * File albumFolder = null;
   * Album[] albums = new Album[0];
   * for (File file : contents) {
   * if (file.isDirectory()) {
   * for (File subFile : file.listFiles()) {
   * for (File subSubFile : subFile.listFiles()) {
   * if (subSubFile.isFile() && subSubFile.getName().equals("album.yaml")) {
   * albumFolder = file;
   * break;
   * }
   * }
   * }
   * }
   * }
   * if (albumFolder != null) {
   * counter = 0;
   * for (File file : albumFolder.listFiles()) {
   * if (file.isDirectory()) {
   * for (File subFile : file.listFiles()) {
   * if (subFile.isFile() && subFile.getName().equals("album.yaml")) {
   * counter++;
   * }
   * }
   * }
   * }
   * File[] albumFolders = new File[counter];
   * albums = new Album[counter];
   * int albumCounter = 0;
   * for (File file : albumFolder.listFiles()) {
   * if (file.isDirectory()) {
   * for (File subFile : file.listFiles()) {
   * if (subFile.isFile() && subFile.getName().equals("album.yaml")) {
   * albumFolders[albumCounter] = file;
   * albumCounter++;
   * }
   * }
   * }
   * }
   * albumCounter = 0;
   * for (File file : albumFolders) {
   * File songFolder = null;
   * Song[] songs = new Song[0];
   * for (File subFile : file.listFiles()) {
   * if (subFile.isDirectory()) {
   * for (File subSubFile : subFile.listFiles()) {
   * if (subSubFile.isFile() && subSubFile.getName().endsWith(".yaml")) {
   * songFolder = subFile;
   * break;
   * }
   * }
   * }
   * }
   * if (songFolder != null) {
   * int songCounter = 0;
   * for (File songFiles : songFolder.listFiles()) {
   * if (songFiles.isFile() && songFiles.getName().endsWith(".yaml")) {
   * songCounter++;
   * }
   * }
   * songs = new Song[songCounter];
   * songCounter = 0;
   * for (File songFiles : songFolder.listFiles()) {
   * if (songFiles.isFile() && songFiles.getName().endsWith(".yaml")) {
   * try {
   * Map<String, Object> songMap = new Yaml().load(new FileReader(songFiles));
   * List<Map<String, Object>> components = (List<Map<String, Object>>)
   * songMap.get("components");
   * SongComponent[] songComponents = new SongComponent[components.size()];
   * int componentCounter = 0;
   * for (Map<String, Object> component : components) {
   * List<String> lines = (List<String>) component.get("lines");
   * songComponents[componentCounter] = new SongComponent((String)
   * component.get("type"),
   * lines.toArray(new String[0]));
   * componentCounter++;
   * }
   * songs[songCounter] = new Song((String) songMap.get("name"), songComponents);
   * songCounter++;
   * } catch(FileNotFoundException e) {
   * 
   * }
   * }
   * }
   * }
   * Album album;
   * try {
   * Map<String, Object> albumData = new Yaml().load(new FileReader(new
   * File(albumFolder, "album.yaml")));
   * albums[albumCounter] = new Album((String) albumData.get("name"),
   * (String) albumData.get("year"), songs);
   * albums[albumCounter].setupChildren();
   * albumCounter ++;
   * } catch(FileNotFoundException e) {
   * 
   * }
   * //albumCounter ++;
   * }
   * // sort albums by release date
   * }
   * try {
   * Map<String, Object> artistData = new Yaml().load(new FileReader(new
   * File(artistFolders[i], "artist.yaml")));
   * artists[i] = new Artist((String) artistData.get("name"), (String)
   * artistData.get("genre"), albums);
   * artists[i].setupChildren();
   * } catch(FileNotFoundException e) {
   * 
   * }
   * }
   * // sort artists alphabetically
   * }
   * return artists;
   * }
   */
}