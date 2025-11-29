package maze;
import java.util.Scanner;

public class EventManager {
    Scanner sc = MinotaurMaze.sc;

    // 랜덤 이벤트 
    public void triggerEvent(Player player) {
        double rand = Math.random();

        if (rand < 0.5) {
            startBattle(player, getRandomMob());  //전투
        }else if (rand < 0.9) {
            handleEquipmentEvent(player);         //장비획득
        }else {
            handleShrineEvent(player);            //회복
        }
    }

    //장비 획득 
    private void handleEquipmentEvent(Player player) {
        System.out.println("\n>> 장비를 발견했습니다!");

        if (player.equipmentLevel >= 4) {
            System.out.println(">>이미 모든 장비를 갖췄습니다. (더 이상 획득 불가)");
        } else {
            player.equipmentLevel++;
            String itemName = "";
            
            if (player.equipmentLevel == 1) {
                itemName = "강철 투구";
                player.def += 5;
                System.out.println(">> [장비 획득] " + itemName + "를 얻었습니다! (방어력 +5)");
            } else if (player.equipmentLevel == 2) {
                itemName = "용사의 검";
                player.atk += 10;
                System.out.println(">> [장비 획득] " + itemName + "를 얻었습니다! (공격력 +10)");
            } else if (player.equipmentLevel == 3) {
                itemName = "미스릴 갑옷";
                player.def += 5;
                System.out.println(">> [장비 획득] " + itemName + "를 얻었습니다! (방어력 +5)");
            } else if (player.equipmentLevel == 4) {
                itemName = "수호자의 방패";
                player.def += 5;
                System.out.println(">> [장비 획득] " + itemName + "를 얻었습니다! (방어력 +5)");
            }
        }
        waitEnter();
    }

    // 회복 성소
    private void handleShrineEvent(Player player) {
        System.out.println("\n>> 신성한 기운이 느껴집니다. 회복의 성소입니다.");
        player.healFull(); 
        System.out.println(">> 체력과 마나가 모두 회복되었습니다!");
        waitEnter();
    }

    //전투 이벤트
    private void startBattle(Player player, Monster mob) {
        System.out.println("\n[" + mob.name + "] 전투 시작!");
        
        while (player.hp > 0 && mob.hp > 0) {
            player.tempDef = 0; 
            System.out.println("------------------------------");
            System.out.println(mob.name + " HP:" + mob.hp + (mob.stunTurn > 0 ? "(기절)" : ""));
            System.out.println("나 HP:" + player.hp + " MP:" + player.mp);
            System.out.print("1.공격 2.스킬 3.도망 > ");
            String c = sc.nextLine();
            System.out.println("------------------------------");

            if (c.equals("1")) {            //일반 공격
                mob.hp -= player.atk;
                System.out.println(">> 공격! " + player.atk + "데미지");
            } else if (c.equals("2")) {     // 스킬 사용
                useSkill(player, mob);
            } else if (c.equals("3")) {     // 도망 사용
                if (mob.name.equals("미노타우로스")) {
                    System.out.println(">> 보스에게서는 도망칠 수 없습니다!");
                } else if (Math.random() < 0.5) {
                    System.out.println(">> 도망 성공!");
                    return;
                } else {
                    System.out.println(">> 도망 실패! (적이 공격합니다)");
                }
            } else {
                System.out.println(">> 행동 실패!");
            }
            
            //전투 승리
            if (mob.hp <= 0) {
                System.out.println(">> 승리했습니다!");
                waitEnter();
                return;
            }
            
            // 상대몹 공격
            if (mob.stunTurn > 0) {
                mob.stunTurn--;
            } else {
                int dmg = Math.max(0, mob.atk - (player.def + player.tempDef));
                player.hp -= dmg;
                System.out.println(">> 적의 공격! " + dmg + "피해");
            }
            
            //플레이어 사망
            if (player.hp <= 0) {
                System.out.println("GAME OVER");
            }
        }
    }
    
    //스킬 구현
    private void useSkill(Player player, Monster mob) {
        System.out.println("[ 스킬 선택 ]");
        System.out.println("1. 일격필살 (MP 10, 2배의 데미지)");
        System.out.println("2. 후두부 강타 (MP 15, 데미지 없음, 2턴간 기절)");
        System.out.println("3. 방패 막기 (MP 5, 1턴간 방어력 10 증가)");
        System.out.print("선택 > ");
        String s = sc.nextLine();
        System.out.println("------------------------------");

        if (s.equals("1")) {
            if (player.mp >= 10) {
                player.mp -= 10;
                int dmg = player.atk * 2;
                mob.hp -= dmg;
                System.out.println(">> 일격필살! " + dmg + "데미지");
            } else {
                System.out.println(">> MP부족! 일반공격");
                mob.hp -= player.atk;
            }
        } else if (s.equals("2")) {
            if (player.mp >= 15) {
                player.mp -= 15;
                mob.stunTurn = 2;
                System.out.println(">> 후두부 강타! 적을 2턴간 기절시켰습니다! (데미지 없음)");
            } else {
                System.out.println(">> MP부족! 일반공격");
                mob.hp -= player.atk;
            }
        } else if (s.equals("3")) {
            if (player.mp >= 5) {
                player.mp -= 5;
                player.tempDef = 10;
                System.out.println(">> 방패 막기! 방어태세를 갖춥니다.");
            } else {
                System.out.println(">> MP부족!");
            }
        }
    }
    
    //랜덤 미궁 몹
    private Monster getRandomMob() {
        double r = Math.random();
        if (r < 0.25) return new Monster("스켈레톤", 50, 15);
        else if (r < 0.5) return new Monster("박쥐", 40, 15);
        else if (r < 0.75) return new Monster("스파이더", 30, 20);
        else return new Monster("고스트", 50, 10);
    }
    
    //보스 등장
    public void bossBattle(Player player) {
        System.out.println("\n미노타우로스 등장! ");
        startBattle(player, new Monster("미노타우로스", 100, 25));
    }
    
    //키입력 대기
    private void waitEnter() {
        System.out.println("엔터 키를 누르면 계속합니다");
        sc.nextLine();
    }
}