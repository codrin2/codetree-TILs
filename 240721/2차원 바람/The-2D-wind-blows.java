import java.util.Scanner;

public class Main {
    public static final int MAX_M = 100;
    public static final int MAX_N = 100;
    public static final int DIR_NUM = 5;
    
    // 전역 변수 선언:
    public static int n, m, q;
    
    public static int[][] a = new int[MAX_N + 1][MAX_M + 1];
    public static int[][] tempArr = new int[MAX_N + 1][MAX_M + 1];
    
    
    // 직사각형의 경계에 있는 숫자들을 시계 방향으로 한 칸씩 회전해줍니다.
    public static void rotate(int startRow, int startCol, int endRow, int endCol) {
        // Step1-1. 직사각형 가장 왼쪽 위 모서리 값을 temp에 저장합니다.
        int temp = a[startRow][startCol];
    
        // Step1-2. 직사각형 가장 왼쪽 열을 위로 한 칸씩 shift 합니다.
        for(int row = startRow; row < endRow; row++)
            a[row][startCol] = a[row + 1][startCol];
        
        // Step1-3. 직사각형 가장 아래 행을 왼쪽으로 한 칸씩 shift 합니다.
        for(int col = startCol; col < endCol; col++)
            a[endRow][col] = a[endRow][col + 1];
    
        // Step1-4. 직사각형 가장 오른쪽 열을 아래로 한 칸씩 shift 합니다.
        for(int row = endRow; row > startRow; row--)
            a[row][endCol] = a[row - 1][endCol];
        
        // Step1-5. 직사각형 가장 위 행을 오른쪽으로 한 칸씩 shift 합니다.
        for(int col = endCol; col > startCol; col--)
            a[startRow][col] = a[startRow][col - 1];
    
        // Step1-6. temp를 가장 왼쪽 위 모서리를 기준으로 바로 오른쪽 칸에 넣습니다. 
        a[startRow][startCol + 1] = temp;
    }
    
    // 격자를 벗어나는지 판단합니다.
    public static boolean inRange(int x, int y) {
        return 1 <= x && x <= n && 1 <= y && y <= m;
    }
    
    // x행 y열 (x, y)과 인접한 숫자들과의 평균 값을 계산해줍니다.
    // 격자를 벗어나지 않는 숫자들만을 고려해줍니다.
    public static int average(int x, int y) {
        // 자기 자신의 위치를 포함하여 평균을 내야 하므로 
        // DIR_NUM을 5로 설정하면 한번에 처리가 가능합니다.
        int[] dx = new int[]{0, 1, -1, 0, 0};
        int[] dy = new int[]{0, 0, 0, 1, -1};
        
        int sum = 0, cnt = 0;
    
        for(int dir = 0; dir < DIR_NUM; dir++) {
            int nx = x + dx[dir], ny = y + dy[dir];
            if(inRange(nx, ny)) {
                sum += a[nx][ny]; cnt++;
            }
        }
    
        return sum / cnt;
    }
    
    // 직사각형 내 숫자들을 인접한 숫자들과의 평균값으로 바꿔줍니다.
    // 동시에 일어나야 하는 작업이므로 이미 바뀐 숫자에 주위 숫자들이 영향을 받으면 안되기 때문에 
    // tempArr 배열에 평균 값들을 먼저 전부 적어 준 다음, 그 값을 다시 복사해옵니다.
    public static void setAverage(int startRow, int startCol, int endRow, int endCol) {
        // Step2-1. tempArr에 평균 값을 적습니다.
        for(int row = startRow; row <= endRow; row++)
            for(int col = startCol; col <= endCol; col++)
                tempArr[row][col] = average(row, col);
        
        // Step2-2. tempArr 값을 다시 가져옵니다.
        for(int row = startRow; row <= endRow; row++)
            for(int col = startCol; col <= endCol; col++)
                a[row][col] = tempArr[row][col];
    }
    
    // 조건에 맞춰 값을 바꿔봅니다.
    public static void simulate(int startRow, int startCol, int endRow, int endCol) {
        // Step1
        // 직사각형의 경계에 있는 숫자들을 시계 방향으로 한 칸씩 회전해줍니다.
        rotate(startRow, startCol, endRow, endCol);
    
        // Step2
        // 직사각형 내 각각의 숫자들을 인접한 숫자들과의 평균값으로 바꿔줍니다.
        setAverage(startRow, startCol, endRow, endCol);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 입력:
        n = sc.nextInt();
        m = sc.nextInt();
        q = sc.nextInt();
        
        for(int row = 1; row <= n; row++)
            for(int col = 1; col <= m; col++)
                a[row][col] = sc.nextInt();
        
        while(q-- > 0) {
            int r1, c1, r2, c2;
            r1 = sc.nextInt();
            c1 = sc.nextInt();
            r2 = sc.nextInt();
            c2 = sc.nextInt();

            // 조건에 맞춰 값을 바꿔봅니다.
            simulate(r1, c1, r2, c2);
        }

        // 출력:
        for(int row = 1; row <= n; row++) {
            for(int col = 1; col <= m; col++)
                System.out.print(a[row][col] + " ");
            System.out.println();
        }
    }
}