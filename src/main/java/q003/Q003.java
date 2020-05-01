package q003;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Q003 集計と並べ替え
 * <p>
 * 以下のデータファイルを読み込んで、出現する単語ごとに数をカウントし、アルファベット辞書順に並び変えて出力してください。
 * resources/q003/data.txt
 * 単語の条件は以下となります
 * - "I"以外は全て小文字で扱う（"My"と"my"は同じく"my"として扱う）
 * - 単数形と複数形のように少しでも文字列が異れば別単語として扱う（"dream"と"dreams"は別単語）
 * - アポストロフィーやハイフン付の単語は1単語として扱う（"isn't"や"dead-end"）
 * <p>
 * 出力形式:単語=数
 * <p>
 * [出力イメージ]
 * （省略）
 * highest=1
 * I=3
 * if=2
 * ignorance=1
 * （省略）
 * <p>
 * 参考
 * http://eikaiwa.dmm.com/blog/4690/
 */
public class Q003 {
    /**
     * データファイルを開く
     * resources/q003/data.txt
     */
    private static InputStream openDataFile() {
        return Q003.class.getResourceAsStream("data.txt");
    }

    private static List<String> readSplitLine(InputStream inputStream) {
        BufferedReader bf = new BufferedReader(new InputStreamReader(inputStream));
        List<String> strings = new ArrayList<>();
        bf.lines().map(s -> s.split(",* |\\.\n*|;")).forEach(v -> strings.addAll(Arrays.asList(v)));

        return strings;
    }

    public static void main(String[] args) {
        InputStream inputStream = openDataFile();
        List<String> strings = readSplitLine(inputStream);

        Map<String, Integer> counts = new HashMap<>();

        for (String str : strings) {
            if (!str.isEmpty()) {
                if (!str.equals("I")) {
                    str = str.toLowerCase();
                }

                if (counts.containsKey(str)) {
                    counts.put(str, counts.get(str) + 1);
                } else {
                    counts.put(str, 1);
                }
            }
        }

        counts.forEach((s, integer) -> System.out.println(s + "=" + integer.toString()));
    }
}
// 完成までの時間: 0時間 32分