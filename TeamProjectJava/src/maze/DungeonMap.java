package maze;

public class DungeonMap {
    // 맵 구현
    private int[][] map = new int[10][10];
    private boolean[][] isRevealed = new boolean[10][10];

    public DungeonMap() {
        initializeMap();
    }

    //0:벽, 1:길, 2:시작, 3:보스
    private void initializeMap() {       
        map[0][0]=1; map[0][1]=1; map[0][2]=1; map[0][3]=1;
        map[1][0]=1;
        for(int j=3; j<=9; j++) map[1][j]=1;
        map[2][0]=3; map[2][4]=1; map[2][7]=1;
        map[3][4]=1; map[3][7]=1; map[3][8]=1;
        for(int j=0; j<=4; j++) map[4][j]=1;
        map[5][4]=1;
        for(int j=2; j<=8; j++) map[6][j]=1;
        map[7][0]=1; map[7][1]=1; map[7][2]=1; map[7][3]=1; map[7][6]=1;
        map[8][6]=1;
        map[9][6]=1;
        map[10][6]=2;
    }

    // 맵 출력
    public void printMap(int playerR, int playerC) {
        System.out.println("\n[ 지도 ]");
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (i == playerR && j == playerC) {
                    System.out.print("[P]");           //플레이어 현재 위치
                } else {
                    // 밝혀진 곳이거나, 플레이어 바로 주변 이동 가능한 곳이면 보여줌
                    boolean isRevealedSpot = isRevealed[i][j];
                    boolean isNeighbor = (Math.abs(i - playerR) + Math.abs(j - playerC) == 1);
                    boolean isWalkable = (map[i][j] != 0);

                    if (isRevealedSpot || (isNeighbor && isWalkable)) {
                        if (map[i][j] == 3) System.out.print("[*]");
                        else if (map[i][j] == 2) System.out.print("[&]");
                        else System.out.print("[ ]");
                    } else {
                        System.out.print("   ");
                    }
                }
            }
            System.out.println();
        }
    }

    // 이동 가능한지 체크
    public boolean isValidMove(int r, int c) {
        if (r < 0 || r >= 10 || c < 0 || c >= 10) return false;
        return map[r][c] != 0;
    }

    // 보스방인지 체크
    public boolean isBossRoom(int r, int c) {
        return map[r][c] == 3;
    }

    // 맵 밝히기
    public void reveal(int r, int c) {
        isRevealed[r][c] = true;
    }
}