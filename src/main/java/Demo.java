import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

abstract class Character {
    protected int power;
    protected int hp;

    public Character(int power, int hp){
        this.power = power;
        this.hp = hp;
    }

    public void kick(Character c){
        c.hp -= this.power;
    }

    public boolean isAlive(){
        return this.hp > 0;
    }

}

class Hobbit extends Character{
    public Hobbit(){
        super(0, 3);
    }

    @Override
    public void kick(Character c){
        this.toCry();
        System.out.println("Hobbit cries because of " + c);
    }

    private void toCry(){
        System.out.println("Hobbit is crying");
    }

    @Override
    public String toString() {
        return "Hobbit(" + power + ", " + hp + ")";
}

}

class Elf extends Character{
    public Elf(){
        super(10, 10);
    }

    @Override
    public void kick(Character c){
        if (c.power < this.power) {
            c.hp = 0;
            System.out.println("Fatality! Elf kills " + c);     
        } else {
            c.hp -= 1;
            System.out.println("Elf: 'You got skills, you got skills' - about " + c);
        }
    }

    @Override
    public String toString() {
        return "Elf(" + power + ", " + hp + ")";
    }
}
class King extends Character {
    public King(){
        super(ThreadLocalRandom.current().nextInt(5, 15),
             ThreadLocalRandom.current().nextInt(5, 15));
    }

    @Override
    public void kick(Character c){
        int ppower = ThreadLocalRandom.current().nextInt(5, this.power + 1);
        c.hp -= ppower;
        System.out.println("King gives -" + ppower + " damage to " + c);
    }

    @Override
    public String toString() {
        return "King(" + power + ", " + hp + ")";
    }
}

class Knight extends Character {
    public Knight(){
        super(ThreadLocalRandom.current().nextInt(2, 12),
             ThreadLocalRandom.current().nextInt(2, 12));
    }

    @Override
    public void kick(Character c){
        int ppower = ThreadLocalRandom.current().nextInt(2, this.power + 1);
        c.hp -= ppower;
        System.out.println("Knight gives -" + ppower + " damage to " + c);
    }
    @Override
    public String toString() {
        return "Knight(" + power + ", " + hp + ")";
}
}

class CharacterFactory{
    public Character createCharacter(){
        List<String> heroes = new ArrayList<>();
        heroes.add("Knight");
        heroes.add("King");
        heroes.add("Elf");
        heroes.add("Hobbit");
        Random random = new Random();
        int randomIndex = random.nextInt(heroes.size());
        String randomHeroe = heroes.get(randomIndex);

        if (randomHeroe.equals("Knight")) {
            return new Knight();
        } else if (randomHeroe.equals("King")) {
            return new King();
        } else if (randomHeroe.equals("Elf")) {
            return new Elf();
        } else if (randomHeroe.equals("Hobbit")) {
            return new Hobbit();
        }
        return new Hobbit();
    }
}

class GameManager{
    public void fight(Character c1, Character c2){
        System.out.println("Battle starts: " + c1 + " vs " + c2);
        while (c1.isAlive() && c2.isAlive()) {
            c1.kick(c2);
            if (!c2.isAlive()) {
                System.out.println(c1 + " wins!");
                break;
            }
            c2.kick(c1);
            if (!c1.isAlive()) {
                System.out.println(c2 + " wins!");
                break;
            }
        }
    }
}


public class Demo {
    public static void main(String[] args) {
        GameManager gm = new GameManager();
        CharacterFactory factory = new CharacterFactory();
        
        Character c1 = factory.createCharacter();
        Character c2 = factory.createCharacter();

        gm.fight(c1, c2);
    }
}