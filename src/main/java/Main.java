public class Main {
  public static void main(String[] args) {
    String [] thing = { "what", "ooga booga" };
    String [] chorus = { "I'm going to eat snacks tonight",
                       "potatoes, potatoes.",
                       "potatoes, potatoes?"};
    SongComponent [] potato = { new SongComponent("Chorus", thing), new SongComponent("Verse", chorus)};
    Song song = new Song("johny johns", "best of johny johns", "john john", 1984, potato);
    System.out.println(song.toString());
  }
}