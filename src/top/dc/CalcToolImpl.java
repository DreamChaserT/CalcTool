package top.dc;

public class CalcToolImpl extends CalcTool {
    @Override
    boolean calc(CalcType type, String before, String after) {
        int left = Integer.parseInt(before);
        int right = Integer.parseInt(after);
        boolean result = false;
        switch (type) {
            case greater: {
                result = (left > right);
            }
            break;
            case less: {
                result = (left < right);
            }
            break;
            case greaterEqual: {
                result = (left >= right);
            }
            break;
            case lessEqual: {
                result = (left <= right);
            }
            break;
            case equal: {
                result = (left == right);
            }
            break;
        }
        System.out.println(before + " " + type + " " + right+" = "+result);
        return result;
    }
}
