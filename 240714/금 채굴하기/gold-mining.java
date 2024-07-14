import java.util.Scanner;

public class Main {
    public static final int MAX_NUM = 20;
    
    public static int n, m;
    public static int[][] grid = new int[MAX_NUM][MAX_NUM];
    
    // 주어진 k에 대하여 마름모의 넓이를 반환합니다.
    public static int getArea(int k) {
        return k * k + (k+1) * (k+1); 
    }
    
    // 주어진 k에 대하여 채굴 가능한 금의 개수를 반환합니다.
    public static int getNumOfGold(int row, int col, int k) {
        int numOfGold = 0;
    
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                if(Math.abs(row - i) + Math.abs(col - j) <= k)
                    numOfGold += grid[i][j];
    
        return numOfGold;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int maxGold = 0;

        // 입력:
        n = sc.nextInt();
        m = sc.nextInt();
        for(int row = 0; row < n; row++)
            for(int col = 0; col < n; col++)
                grid[row][col] = sc.nextInt();

        // 격자의 각 위치가 마름모의 중앙일 때 채굴 가능한 금의 개수를 구합니다.
        for(int row = 0; row < n; row++) {
            for(int col = 0; col < n; col++) {
                for(int k = 0; k <= 2 * (n-1); k++) {
                    int numOfGold = getNumOfGold(row, col, k);

                    // 손해를 보지 않으면서 채굴할 수 있는 최대 금의 개수를 저장합니다.
                    if(numOfGold * m >= getArea(k))
                        maxGold = Math.max(maxGold, numOfGold);
                }
            }
        }

        System.out.print(maxGold);
    }
}