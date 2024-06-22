import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        int[] home = new int[n];
        for(int i =0;i<n;i++){
            home[i] = Integer.parseInt(st.nextToken());
        }
        int minDis = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int sumDis=0;
            for (int j = 0; j < n; j++) {
                sumDis += home[j]*Math.abs(j-i);
            }
            minDis = Math.min(sumDis, minDis);
        }
        System.out.println(minDis);
    }
}