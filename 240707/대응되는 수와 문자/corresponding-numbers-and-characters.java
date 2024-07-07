import java.util.Scanner;
import java.util.HashMap;

public class Main {
    public static final int MAX_N = 100000;
    
    // 변수 선언
    public static int n, m;
    public static String[] words = new String[MAX_N + 1];
    public static HashMap<String, Integer> StringToNum = new HashMap<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 입력:
        n = sc.nextInt();
        m = sc.nextInt();
        
        // 각 숫자의 대응되는 문자를 array에,
        // 각 문자의 대응되는 숫자를 hashmap에 기록해줍니다.
        for(int i = 1; i <= n; i++) {
            words[i] = sc.next();
            StringToNum.put(words[i], i);
        }

        while(m-- > 0) {
            String key = sc.next();

            // 입력된 값이 숫자일 때에는 array에 저장한 문자를 출력합니다.
            if('0' <= key.charAt(0) && key.charAt(0) <= '9')
                System.out.println(words[Integer.parseInt(key)]);
            
            // 입력된 값이 문자일 때에는 hashmap에 기록된 대응되는 숫자를 출력합니다.
            else
                System.out.println(StringToNum.get(key));
        }
    }
}