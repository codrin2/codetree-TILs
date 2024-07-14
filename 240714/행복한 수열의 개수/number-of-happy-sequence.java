import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] arr = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int now;
        int cont;
        int cnt=0;
        if(m==1){
            cnt = n*2;
        }
        for (int i = 0; i < n; i++) {
            now = arr[i][0];
            cont = 0;
            for (int j = 1; j < n; j++) {
                if(now == arr[i][j]) {
                    cont++;
                    if(cont == m-1) {
                        cnt++;
                        break;
                    }
                }else{
                    cont=0;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            now = arr[0][i];
            cont = 0;
            for (int j = 1; j < n; j++) {
                if(now == arr[j][i]) {
                    cont++;
                    if(cont == m-1) {
                        cnt++;
                        break;
                    }
                }else{
                    cont=0;
                }
            }
        }
        System.out.println(cnt);
    }
}