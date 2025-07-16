/*
 * Decorator Design Pattern:
    The Decorator Design Pattern attaches additional responsibilities to an object dynamically.
    It provides a flexible alternative to subclassing for extending functionality.
 */

// Component Interface: defines a common interface for Mario and all power-ups decorators.
interface ICharacter{
   String getAbilities();
}

// Concrete Component: Basic Mario character with no power-ups.
class Mario implements ICharacter{
   @Override
   public String getAbilities() {
        return "Basic Mario";
    }
}

// Abstract Decorator: CharacterDecorator "is-a" Character and "has-a" Character.
abstract class ICharacterDecorator implements ICharacter{
   protected ICharacter character;

   public ICharacterDecorator(ICharacter character) {
       this.character = character;
   }
}

// Concrete Decorator: Fire Power Decorator adds fire power to Mario.
class FirePowerDecorator extends ICharacterDecorator {

    public FirePowerDecorator(ICharacter character) {
        super(character);
    }

    @Override
    public String getAbilities() {
        return character.getAbilities() + ", Fire Power";
    }
}

// Concrete Decorator: Super Jump Decorator adds super jump ability to Mario.
class SuperJumpDecorator extends ICharacterDecorator {

    public SuperJumpDecorator(ICharacter character) {
        super(character);
    }

    @Override
    public String getAbilities() {
        return character.getAbilities() + ", Super Jump";
    }
}

// Concrete Decorator: Gun Shooting Decorator adds gun shooting ability to Mario.
class GunShootingDecorator extends ICharacterDecorator {

    public GunShootingDecorator(ICharacter character) {
        super(character);
    }

    @Override
    public String getAbilities() {
        return character.getAbilities() + ", Gun Shooting";
    }
}


public class DecoratorDesignPattern {
   public static void main(String[] args) {
      // Create basic Mario character
      ICharacter mario = new Mario();
      System.out.println("Abilities: " + mario.getAbilities());

      // Decorate Mario with Fire Power
      mario = new FirePowerDecorator(mario);
      System.out.println("Abilities after Fire Power: " + mario.getAbilities());

      // Decorate Mario with Super Jump
      mario = new SuperJumpDecorator(mario);
      System.out.println("Abilities after Super Jump: " + mario.getAbilities()); 

      // Decorate Mario with Gun Shooting
      mario = new GunShootingDecorator(mario);
      System.out.println("Abilities after Gun Shooting: " + mario.getAbilities());
   }
}
