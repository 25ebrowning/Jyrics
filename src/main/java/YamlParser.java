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
}