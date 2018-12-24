package top.dc;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

//bool类型计算工具
public abstract class CalcTool {

    private List<Character> calcType = Arrays.asList('>', '<', '=');
    private List<Character> multiCalcType = Arrays.asList('|', '&');

    private Stack<Character> calcStack = new Stack<>();

    private boolean calcMulti(String multiCalc) {
        //当前计算结果
        Boolean current = null;
        //当前计算符号
        String currentType = "";

        StringBuilder tmp = new StringBuilder();

        char[] chars = multiCalc.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (multiCalcType.contains(c) || i == chars.length - 1) {
                if (i == chars.length - 1) {
                    tmp.append(c);
                }
                //计算结果，清空缓存
                boolean b = calcSingle(tmp.toString());
                if (null == current) {
                    current = b;
                } else {
                    switch (currentType) {
                        case "|": {
                            current = current || b;
                        }
                        break;
                        case "&": {
                            current = current && b;
                        }
                        break;
                    }
                }
                tmp = new StringBuilder();
                currentType = String.valueOf(c);
            } else {
                tmp.append(c);
            }
        }
        return current;
    }

    private boolean calcSingle(String singleCalc) {
        if("0".equals(singleCalc)){
            return false;
        }else if("1".equals(singleCalc)){
            return true;
        }
        boolean first = true;
        StringBuilder left = new StringBuilder();
        StringBuilder calc = new StringBuilder();
        StringBuilder right = new StringBuilder();
        char[] chars = singleCalc.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (calcType.contains(c)) {
                calc.append(c);
                first = false;
            } else {
                if (first) {
                    left.append(c);
                } else {
                    right.append(c);
                }
            }
        }
        return calc(CalcType.get(calc.toString()), left.toString(), right.toString());
    }

    //计算每个算式 type 计算符号  before 前运算值   after 后运算值
    abstract boolean calc(CalcType type, String before, String after);

    //总入口
    public boolean calcMain(String str) {
        char[] chars = str.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (')' == c) {
                char tmp = calcStack.pop();
                while ('(' != tmp) {
                    sb.insert(0, tmp);
                    tmp = calcStack.pop();
                    if (calcStack.empty()) {
                        break;
                    }
                }
                boolean b = calcMulti(sb.toString());
                calcStack.push(b ? '1' : '0');
                sb = new StringBuilder();
            } else {
                calcStack.push(c);
            }
        }
        sb = new StringBuilder();
        while (!calcStack.empty()) {
            sb.insert(0, calcStack.pop());
        }
        return calcMulti(sb.toString());
    }
}
