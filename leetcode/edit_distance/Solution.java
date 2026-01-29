package edit_distance;

import java.util.LinkedList;
import java.util.List;

public class Solution {
  LinkedList<String> closestWords = null;

  int closestDistance = -1;

  // global variabel för senaste rättstavade ordet
  String lastWord = "";

  // global variabel för en editeringsmatris
  int[][] d = new int[501][501];

  int partDist(String w1, String w2, int m, int n) {
    int start = 1;

    // Om det finns ett tidigare ord, alltså lastWord inte tom 
    if(!lastWord.isEmpty()) {
      // Inkrementera startpunkten en gång för varje första P matchande bokstäver med förra ordet
      for (int i = 0; i < lastWord.length() && i < w2.length(); i++){
        if(lastWord.charAt(i) == w2.charAt(i)){
          start++;
        }
        else {
          break;
        }
      }
    }

    // Sätt första raden till 1, 2, 3, ...
    for (int i = 0; i <= m; i++) {
      d[i][0] = i;
    }
    // Sätt första kolumnen till 1, 2, 3, ...
    for (int j = 0; j <= n; j++) {
      d[0][j] = j;
    }

    // Rekursion - börja iterera kolumnerna från startpunkten (antingen 1 eller P+1)
    for (int j = start; j <= n; j++) { 
      for (int i = 1; i <= m; i++) {    // iterera alla rader 
        int sub = (w1.charAt(i-1) == w2.charAt(j-1)) ? 0 : 1;   // om matchande bokstav, sub = 0, annars 1 
        d[i][j] = Math.min(Math.min(d[i-1][j] + 1, d[i][j-1] + 1), d[i-1][j-1] + sub); // Plocka minsta värdet
      }
    }
    lastWord = w2; // sätt nuvarande RÄTT stavat ord till nästa lastWord

    return d[m][n]; // returnera resultatet med minsta editeringslängden
  }

  int minDistance(String w1, String w2) {
    return partDist(w1, w2, w1.length(), w2.length());
  }

  public void ClosestWords(String w, List<String> wordList) {
    for (String s : wordList) {

      int lenDiff = Math.abs(w.length() - s.length()); // Absolutbelopp mellan jämförande ord

      /* när vi har giltigt closestDistance och diffen är större än den så behövs 
          MINST lenDiff antal insert/delete operationer, så skippa bara ordet för
          det finns kortare editeringsavstånd.
      */ 
      if (closestDistance != -1 && lenDiff > closestDistance) {
          continue;  // Skip this word
      }

      int dist = minDistance(w, s);
      if (dist == Integer.MAX_VALUE) {
          continue;
      }
      // System.out.println("d(" + w + "," + s + ")=" + dist);
      if (dist < closestDistance || closestDistance == -1) {
        closestDistance = dist;
        closestWords = new LinkedList<String>();
        closestWords.add(s);
      }
      else if (dist == closestDistance)
        closestWords.add(s);
    }
  }

  int getMinDistance() {
    return closestDistance;
  }

  List<String> getClosestWords() {
    return closestWords;
  }
}