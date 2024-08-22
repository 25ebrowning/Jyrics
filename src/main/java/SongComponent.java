public class SongComponent {
  String type;
  String [] lines;
  public SongComponent (String type, String [] lines) {
    this.type = type;
    this.lines = lines;
  }
  public String getType() {
    return type;
  }
  public String [] getLines() {
    return lines;
  }
  public String getCombinedLines() {
    return String.join("\n", lines);
  }
  public int getLineCount() {
    return lines.length;
  }
  public String toString() {
    return "[" + type + "]\n" + getCombinedLines();
  }
}