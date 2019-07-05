package utils;

public class FormatterHelper {

    public static Double amountToDouble(String amount){
        String removedCurrency = amount.replace("kr","");
        String removedBlanks = removedCurrency.replace(" ","");
        return Double.parseDouble(removedBlanks);
    }


}
