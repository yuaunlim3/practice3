package Calculator;

import java.util.*;

public class Calculator{
    protected ArrayList<String> operations;

    public Calculator(String op){
        this.operations = new ArrayList<String>();
        String[] opArray = op.split(" ");
        for (String item : opArray) {
            this.operations.add(item);
        }
    }
    public int calculate(){
        while(this.operations.size() > 1){
            if(this.operations.contains("(")){
                int indexOpen = this.operations.indexOf("(");
                int indexClose = this.operations.indexOf(")");
                List<String> inBrackets = this.operations.subList(indexOpen + 1, indexClose);
                Object[] brackArrays = inBrackets.toArray();
                String brackString = brackArrays.toString();
                Calculator brackCalculator = new Calculator(brackString);
                brackCalculator.calculate();
                
            }
            if(this.operations.contains("%")){
                int index = this.operations.indexOf("%");
                int result = Integer.parseInt(this.operations.get(index - 1)) % Integer.parseInt(this.operations.get(index + 1));
                System.out.println(result);
                this.operations.set(index - 1 , Integer.toString(result));
                this.operations.remove(index);
                this.operations.remove(index);
            }else if(this.operations.contains("/")){
                int index = this.operations.indexOf("/");
                int result = Integer.parseInt(this.operations.get(index - 1)) / Integer.parseInt(this.operations.get(index + 1));
                System.out.println(result);
                this.operations.set(index - 1 , Integer.toString(result));
                this.operations.remove(index);
                this.operations.remove(index);
            }
            else if(this.operations.contains("*")){
                int index = this.operations.indexOf("*");
                int result = Integer.parseInt(this.operations.get(index - 1)) * Integer.parseInt(this.operations.get(index + 1));
                System.out.println(result);
                this.operations.set(index - 1 , Integer.toString(result));
                this.operations.remove(index);
                this.operations.remove(index);
            }
            else if(this.operations.contains("+")){
                int index = this.operations.indexOf("+");
                int result = Integer.parseInt(this.operations.get(index - 1)) + Integer.parseInt(this.operations.get(index + 1));
                System.out.println(result);
                this.operations.set(index - 1 , Integer.toString(result));
                this.operations.remove(index);
                this.operations.remove(index);
            }
            else if(this.operations.contains("-")){
                int index = this.operations.indexOf("-");
                int result = Integer.parseInt(this.operations.get(index - 1)) - Integer.parseInt(this.operations.get(index + 1));
                System.out.println(result);
                this.operations.set(index - 1 , Integer.toString(result));
                this.operations.remove(index);
                this.operations.remove(index);
            }
        }
        int result = Integer.parseInt(this.operations.get(0));

        return result;
    }

}
