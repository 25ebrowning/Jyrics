import org.apache.commons.text.similarity.FuzzyScore;
import java.util.Map;
import java.util.Locale;
import java.util.ArrayList;
import java.util.List;

public class Search {
  public static Object searchResult(String query, Map<String, Object> library) {
    query = query.toLowerCase().replaceAll("[^a-z0-9 ]", "");
    FuzzyScore fuzzyScore = new FuzzyScore(Locale.ENGLISH);
    List<String> keyList = new ArrayList<String>(library.keySet());
    int minScoreIndex = 0;
    int minScore = fuzzyScore.fuzzyScore(query, keyList.get(0));
    for (String key : keyList) {
      int score = fuzzyScore.fuzzyScore(query, key);
      if (score < minScore) {
        minScore = score;
        minScoreIndex = keyList.indexOf(key);
      }
    }
    return library.get(keyList.get(minScoreIndex));
  }
}

