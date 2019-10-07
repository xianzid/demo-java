package com.example.demo.test.algorithm;

public class ExpressionTree {
    public static void main(String[] args) {
        Summary summary = new Summary(new Add(1,2).calculate(),
                new Reduce(
                        new Multiply(5,6).calculate(), 7).calculate(),
                new Divide(3,4).calculate());
        System.out.println(summary.calculate());
    }
}

abstract class Symbol{
    int[] ints;
    Symbol(int... ints){
        this.ints = ints;
    }
    abstract int calculate();
}

class Summary extends Symbol{
    Summary (int... ints) {
        super(ints);
    }

    int calculate(){
        int result = 0;
        for (int i : ints){
            result += i;
        }
        return result;
    }
}

class Add extends Symbol{
    Add(int... ints) {
        super(ints);
    }

    int calculate(){
        int result = 0;
        for (int i : ints){
            result += i;
        }
        return result;
    }
}

class Reduce extends Symbol{
    Reduce(int... ints) {
        super(ints);
    }

    int calculate(){
        int result = 0;
        for (int i = 0;i<ints.length;i++){
            if (0 == i){
                result = ints[i];
            } else{
                result -= ints[i];
            }
        }
        return result;
    }
}

class Multiply extends Symbol{
    Multiply(int... ints) {
        super(ints);
    }

    int calculate(){
        int result = 0;
        for (int i : ints){
            result *= i;
        }
        return result;
    }
}

class Divide extends Symbol{
    Divide(int... ints) {
        super(ints);
    }

    int calculate(){
        int result = 0;
        for (int i = 0;i<ints.length;i++){
            if (0 == i){
                result = ints[i];
            } else{
                result /= ints[i];
            }
        }
        return result;
    }
}
