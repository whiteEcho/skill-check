package q005;

/**
 * 作業時間管理クラス
 * 自由に修正してかまいません
 */
public class WorkData {
    /** 社員番号 */
    private String number;

    /** 部署 */
    private String department;

    /** 役職 */
    private String position;

    /** Pコード */
    private String pCode;

    /** 作業時間(分) */
    private int workTime;

    private WorkData(String number, String department, String position, String pCode, int workTime) {
        this.number = number;
        this.department = department;
        this.position = position;
        this.pCode = pCode;
        this.workTime = workTime;
    }

    public static WorkData of(String[] args) {
        return new WorkData(
                args[0],
                args[1],
                args[2],
                args[3],
                Integer.parseInt(args[4])
        );
    }

    public String getNumber() {
        return number;
    }

    public String getDepartment() {
        return department;
    }

    public String getPosition() {
        return position;
    }

    public String getpCode() {
        return pCode;
    }

    public int getWorkTime() {
        return workTime;
    }
}
