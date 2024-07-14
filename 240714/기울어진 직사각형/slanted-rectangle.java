import java.util.Scanner;

public class Main {
    public static final int DIR_NUM = 4;
    public static final int MAX_N = 20;
    
    public static int n;
    public static int[][] grid = new int[MAX_N][MAX_N];
    
    public static boolean inRange(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < n;
    }
    
    public static int getScore(int x, int y, int k, int l) {
        int[] dx = new int[]{-1, -1, 1, 1};
        int[] dy = new int[]{1, -1, -1, 1};
        int[] moveNum = new int[]{k, l, k, l};
        
        int sumOfNums = 0;
    
        // 기울어진 직사각형의 경계를 쭉 따라가봅니다.
        for(int d = 0; d < DIR_NUM; d++)
            for(int q = 0; q < moveNum[d]; q++) {
                x += dx[d]; y += dy[d];
                    
                // 기울어진 직사각형이 경계를 벗어나는 경우라면
                // 불가능하다는 의미로 답이 갱신되지 않도록
                // 0을 반환합니다.
                if(!inRange(x, y))
                    return 0;
                
                sumOfNums += grid[x][y];
            }
        
        return sumOfNums;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                grid[i][j] = sc.nextInt();
        
        int ans = 0;
        
        // (i, j)를 시작으로 1, 2, 3, 4 방향
        // 순서대로 길이 [k, l, k, l] 만큼 이동하면 그려지는
        // 기울어진 직사각형을 잡아보는
        // 완전탐색을 진행해봅니다.
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                for(int k = 1; k < n; k++)
                    for(int l = 1; l < n; l++)
                        ans = Math.max(ans, getScore(i, j, k, l));

        System.out.print(ans);
    }
}