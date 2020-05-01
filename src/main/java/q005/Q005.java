package q005;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Q005 データクラスと様々な集計
 * <p>
 * 以下のファイルを読み込んで、WorkDataクラスのインスタンスを作成してください。
 * resources/q005/data.txt
 * (先頭行はタイトルなので読み取りをスキップする)
 * <p>
 * 読み込んだデータを以下で集計して出力してください。
 * (1) 役職別の合計作業時間
 * (2) Pコード別の合計作業時間
 * (3) 社員番号別の合計作業時間
 * 上記項目内での出力順は問いません。
 * <p>
 * 作業時間は "xx時間xx分" の形式にしてください。
 * また、WorkDataクラスは自由に修正してください。
 * <p>
 * [出力イメージ]
 * 部長: xx時間xx分
 * 課長: xx時間xx分
 * 一般: xx時間xx分
 * Z-7-31100: xx時間xx分
 * I-7-31100: xx時間xx分
 * T-7-30002: xx時間xx分
 * （省略）
 * 194033: xx時間xx分
 * 195052: xx時間xx分
 * 195066: xx時間xx分
 * （省略）
 */
public class Q005 {
    private static InputStream openDataFile() {
        return Q005.class.getResourceAsStream("data.txt");
    }

    private static List<WorkData> readWorkData(InputStream inputStream) {
        BufferedReader bf = new BufferedReader(new InputStreamReader(inputStream));
        return bf.lines().skip(1)
                .map(s -> s.split(","))
                .map(WorkData::of)
                .collect(Collectors.toList());
    }

    private static String workTimeFormatter(String key, Integer workTime) {
        Integer hours = workTime / 60;
        Integer second = workTime % 60;
        return key + ": " + hours.toString() + "時間" + second.toString() + "分";
    }

    public static void main(String[] args) {
        InputStream inputStream = openDataFile();
        List<WorkData> dataList = readWorkData(inputStream);

        // (1) 役職別の合計作業時間
        Map<String, Integer> positions = new HashMap<>();
        // (2) Pコード別の合計作業時間
        Map<String, Integer> pCodes = new HashMap<>();
        // (3) 社員番号別の合計作業時間
        Map<String, Integer> numbers = new HashMap<>();
        for (WorkData data : dataList) {
            int workTime = data.getWorkTime();
            String position = data.getPosition();
            if (positions.containsKey(position)) {
                positions.put(position, positions.get(position) + workTime);
            } else {
                positions.put(position, workTime);
            }

            String pCode = data.getpCode();
            if (pCodes.containsKey(pCode)) {
                pCodes.put(pCode, pCodes.get(pCode) + workTime);
            } else {
                pCodes.put(pCode, workTime);
            }

            String number = data.getNumber();
            if (numbers.containsKey(number)) {
                numbers.put(number, numbers.get(number) + workTime);
            } else {
                numbers.put(number, workTime);
            }
        }

        System.out.println("(1) 役職別の合計作業時間");
        positions.forEach((s, v) -> System.out.println(Q005.workTimeFormatter(s, v)));

        System.out.println("(2) Pコード別の合計作業時間");
        pCodes.forEach((s, v) -> System.out.println(Q005.workTimeFormatter(s, v)));

        System.out.println("(3) 社員番号別の合計作業時間");
        numbers.forEach((s, v) -> System.out.println(Q005.workTimeFormatter(s, v)));
    }
}
// 完成までの時間: 0時間 47分