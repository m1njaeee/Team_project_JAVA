package maze;

public class Player {
    // 기본 능력치
    public int hp;          // 현재 체력
    public int maxHp;       // 최대 체력
    public int mp;          // 현재 마나
    public int maxMp;       // 최대 마나
    public int atk;         // 공격력
    public int def;         // 방어력
    public int tempDef;     // 임시 방어력 (스킬 효과)
   
    // 플레이어 위치
    public int playerRow;
    public int playerCol;
    
    // 장비 단계
    public int equipmentLevel;

    // 생성자
    public Player() {
        this.maxHp = 100;
        this.hp = maxHp;
        this.maxMp = 50;
        this.mp = maxMp;
        this.atk = 10;
        this.def = 5;
        this.tempDef = 0;
        this.equipmentLevel = 0;
        this.playerRow = 9;
        this.playerCol = 6;
    }
    // 캐릭터 이동
    public void moveW() {
    		this.playerRow -= 1;
    		if(playerRow < 0) playerRow = 0;
    }
    public void moveS() {
		this.playerRow += 1;
		if(playerRow > 9) playerRow = 9;
    }
    public void moveA() {
		this.playerCol -= 1;
		if(playerCol < 0) playerCol = 0;
    }
    public void moveD() {
		this.playerCol += 1;
		if(playerCol > 9) playerCol = 9;
    }
    		
    	
    // 체력/마나 풀 회복
    public void healFull() {
        this.hp = maxHp;
        this.mp = maxMp;
    }

    // 체력 감소
    public void takeDamage(int dmg) {
        this.hp -= dmg;
        if (hp < 0) hp = 0;
    }

    // 마나 소모
    public boolean useMp(int amount) {
        if (mp >= amount) {
            mp -= amount;
            return true;
        }
        return false;
    }

    // 상태 출력
    public void printStatus() {
        System.out.println("HP: " + hp + "/" + maxHp + 
                           " | MP: " + mp + "/" + maxMp + 
                           " | ATK: " + atk + 
                           " | DEF: " + def);
    }
}