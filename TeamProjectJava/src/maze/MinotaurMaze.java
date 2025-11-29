package maze;

import java.util.Scanner;

public class MinotaurMaze {
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args)  {
        //객체 생성
    	DungeonMap map = new DungeonMap();
        Player player = new Player();
        EventManager eventManager = new EventManager();
        //시작 좌표
        int r = 10;
        int c = 6;
        map.reveal(r, c);

        printStartScreen();
        //이동 루프 
        boolean isGameOver = false;
        while (!isGameOver) {
            map.printMap(r, c);
            System.out.print("이동 > ");
            String input = sc.nextLine();
            
            //이동 방향
            int dr = 0, dc = 0;
            if (input.equals("w")) dr = -1;
            else if (input.equals("s")) dr = 1;
            else if (input.equals("a")) dc = -1;
            else if (input.equals("d")) dc = 1;
            else { System.out.println("잘못된 입력."); continue; }

            int nextR = r + dr;
            int nextC = c + dc;

            if (!map.isValidMove(nextR, nextC)) {
                System.out.println(">> 벽");
                continue;
            }

            r = nextR;
            c = nextC;
            map.reveal(r, c);
            
            //보스 처치시 루프 탈출
            if (map.isBossRoom(r, c)) {
                eventManager.bossBattle(player);
                if (player.hp > 0) System.out.println(">> 미노타우로스 처치 완료! 게임 클리어!");
                isGameOver = true;
                return;
            }
            //사망시 루프 탈출
            if (Math.random() < 0.2) {
                eventManager.triggerEvent(player);
                if (player.hp <= 0) isGameOver = true;
            }
        }
    }
    //시작 화면
    static void printStartScreen() {
        System.out.println("--------------------------------------");
        System.out.println("미노타우로스 미궁 탐험");
        System.out.println("--------------------------------------");
        System.out.println("미궁을 탐험하여 장비를 모으고 미노타우로스를 쓰러뜨리세요!\n");
        System.out.println("이동은 w:위 a:왼쪽 s:아래 d:오른쪽 통해 이동 할 수 있습니다.");
        System.out.println("\n(엔터 키를 누르면 시작합니다)");
        sc.nextLine();
    }
}