package maze;

public class Monster {
    public String name;   // 몬스터 이름
    public int hp;        // 체력
    public int atk;       // 공격력
    public int stunTurn;  // 기절 턴 수

    // 생성자
    public Monster(String name, int hp, int atk) {
        this.name = name;
        this.hp = hp;
        this.atk = atk;
        this.stunTurn = 0;
    }
}