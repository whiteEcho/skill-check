package q006;

import q006.value.*;

import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Q006 空気を読んで改修
 * <p>
 * 標準入力から「逆ポーランド記法」で記載された1行の入力を受け取り、その計算結果を出力する処理を実装してください。
 * 実装するのは四則演算（+ - * /）です。
 * <p>
 * https://ja.wikipedia.org/wiki/%E9%80%86%E3%83%9D%E3%83%BC%E3%83%A9%E3%83%B3%E3%83%89%E8%A8%98%E6%B3%95
 * <p>
 * ただし、現状は以下の実装が終わっています。
 * - 逆ポーランド記法を分解して、計算しやすい値リストに変換する処理の一部（Q006.parseLine）
 * - 計算しやすい値として管理するためのクラス群の一部（IValue,DecimalValue,PlusValue）
 * <p>
 * 途中まで終わっている実装を上手く流用しながら、残りの処理を実装してください。
 * エラー入力のチェックは不要です。
 * <p>
 * 実行例：
 * <p>
 * 入力） 3 1.1 0.9 + 2.0 * -
 * 出力） -1
 * （または -1.00 など、小数点に0がついてもよい）
 */
public class Q006 {
    /**
     * 逆ポーランドで記載された1行のテキストを分解する
     *
     * @param lineText 1行テキスト
     * @return 分解された値リスト
     */
    private static List<IValue> parseLine(String lineText) {
        List<IValue> resultList = new ArrayList<>();
        // 空白文字で区切ってループする
        for (String text : lineText.split("[\\s]+")) {
            switch (text) {
                case "+":   // 足し算
                    resultList.add(new PlusValue());
                    break;
                case "-": // 引き算
                    resultList.add(new MinusValue());
                    break;
                case "*": // 掛け算
                    resultList.add(new MultipleValue());
                    break;
                case "/": // 割り算
                    resultList.add(new DivideValue());
                    break;
                default:    // その他は数値として扱う
                    resultList.add(new DecimalValue(text));
                    break;
            }
        }
        return resultList;
    }

    private static String divide(String str) {
        Pattern p = Pattern.compile("(?<sign>\\*+)");
        Matcher m = p.matcher(str);
        if (m.find()) {
            return "*";
        }
        return str;
    }

    public static void main(String[] args) {
        try {
            StringJoiner sj = new StringJoiner(" ");
            Arrays.stream(args).map(Q006::divide).forEach(sj::add);
            List<IValue> iValues = parseLine(sj.toString());

            Stack<BigDecimal> decimalStack = new Stack<>();
            iValues.forEach(s -> s.execute(decimalStack));

            System.out.println(decimalStack.pop().toString());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
// 完成までの時間: 1時間 17分