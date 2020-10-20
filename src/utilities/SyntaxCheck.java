package utilities;

public class SyntaxCheck {

    public static boolean insertSyntaxOK(String[] tokens){

        if(tokens.length != 6 ){
            return false;
        }

        try {
            double d = Double.parseDouble(tokens[5]);
        } catch (NumberFormatException nfe) {
            return false;
        }

        return true;
    }

    public static boolean isNullOrEmpty(String str) {
        return str == null || str.isEmpty();
    }

    public static boolean isNullOrEmpty(String[] str, int tokNumber) {
        for(String s : str){
            if(s != null && !s.isEmpty())
                return false;
        }
        return true;

    }


}
