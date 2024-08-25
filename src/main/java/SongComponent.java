public class SongComponent {
  String type;
  String[] lines;

  public SongComponent(String type, String[] lines) {
    this.type = type;
    this.lines = lines;
  }

  public String getType() {
    return type;
  }

  public String[] getLines() {
    return lines;
  }

  public int getWordCount() {
    int totalWords = 0;
    for (int i = 0; i < lines.length; i++) {
      String[] words = lines[i].split(" ");
      totalWords += words.length;
    }
    return totalWords;
  }

  public String getCombinedLines() {
    return String.join("\n", lines);
  }

  public int getLineCount() {
    return lines.length;
  }

  public String toString() {
    if (lines.length > 0) {
      return "[" + type + "]\n" + getCombinedLines();
    } else {
      return "[" + type + "]";
    }
  }
}