package q004;

/**
 * Q004 ソートアルゴリズム
 * <p>
 * ListManagerクラスをnewして、小さい順に並び変えた上でcheckResult()を呼び出してください。
 * <p>
 * 実装イメージ:
 * ListManager data = new ListManager();
 * // TODO 並び換え
 * data.checkResult();
 * <p>
 * - ListManagerクラスを修正してはいけません
 * - ListManagerクラスの dataList を直接変更してはいけません
 * - ListManagerクラスの比較 compare と入れ替え exchange を使って実現してください
 */
public class Q004 {
    private static final ListManager data = new ListManager();

    public static void main(String[] args) {
        int maxIndex = data.size() - 1;
        for (int i = 0; i < maxIndex; i++) {
            for (int idx2 = maxIndex; 0 < idx2; idx2--) {
                int idx1 = idx2 - 1;
                int compare = data.compare(idx1, idx2);
                if (compare > 0) {
                    data.exchange(idx1, idx2);
                }
            }
        }

        try {
            data.checkResult();
        } catch (RuntimeException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
// 完成までの時間: 時間 33分