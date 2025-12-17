package maze;

public class DungeonMap {
    // 맵 구현
    //private int[][] map = new int[11][11];
	//0:벽, 1:길, 2:시작, 3:보스
	private int[][] map = {
		        {1,1,1,1,0,0,0,0,0,0},
		        {1,0,0,1,1,1,1,1,1,1},
		        {3,0,0,0,1,0,0,1,0,0},
		        {0,0,0,0,1,0,0,1,1,0},
		        {1,1,1,1,1,0,0,0,0,0},
		        {0,0,0,0,1,0,0,0,0,0},
		        {0,0,1,1,1,1,1,1,1,0},
		        {1,1,1,1,0,0,1,0,0,0},
		        {0,0,0,0,0,0,1,0,0,0},
		        {0,0,0,0,0,0,2,0,0,0}
	};

    private boolean[][] isRevealed = new boolean[10][10];

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
