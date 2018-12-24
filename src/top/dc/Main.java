package top.dc;

public class Main {

    public static void main(String[] args) {
        CalcTool calcTool = new CalcToolImpl();
        System.out.println(calcTool.calcMain("((1>2&12>3)|(1>2|2>1))"));
        System.out.println(calcTool.calcMain("((1>2&12>3)&(1>2|2>1))"));
        System.out.println(calcTool.calcMain("((2>=2)|(1<=2))"));
    }

}
