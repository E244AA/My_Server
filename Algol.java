public class Algol {

    static String xor(String str, char key){
        String ans = "";
        for(int i = 0; i < str.length(); i++){
            ans += (char)((int)(str.charAt(i)^key));
        }
        return ans;
    }

}
