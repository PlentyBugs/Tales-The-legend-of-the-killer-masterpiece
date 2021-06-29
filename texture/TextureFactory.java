package texture;

import support.CreatureProperty;

public class TextureFactory {

    private static final String path = "./texture/img/";

    private static Texture WOLF;
    private static Texture BANDIT;
    private static Texture ENT;
    private static Texture GOBLIN;
    private static Texture GOBLIN_KING;
    private static Texture KNIGHT;
    private static Texture SKELETON;
    private static Texture ZOMBIE;
    private static Texture DEAD_GUARDIAN;
    private static Texture FRANK;
    private static Texture HIGHER_GHOST;
    private static Texture STOCK;

    public static Texture get(CreatureProperty creature) {
        return switch (creature) {
            case WOLF -> {if (WOLF == null) WOLF = new Texture(path + "wolf.jpg"); yield  WOLF; }
            case BANDIT -> {if (BANDIT == null) BANDIT = new Texture(path + "bandit.jpg"); yield BANDIT; }
            case ENT -> {if (ENT == null) ENT = new Texture(path + "ent.jpg"); yield ENT; }
            case GOBLIN -> {if (GOBLIN == null) GOBLIN = new Texture(path + "goblin.jpg"); yield GOBLIN; }
            case GOBLIN_KING -> {if (GOBLIN_KING == null) GOBLIN_KING = new Texture(path + "goblinKing.jpg"); yield GOBLIN_KING; }
            case KNIGHT -> {if (KNIGHT == null) KNIGHT = new Texture(path + "knight.jpg"); yield KNIGHT; }
            case SKELETON -> {if (SKELETON == null) SKELETON = new Texture(path + "skeleton.jpg"); yield SKELETON; }
            case ZOMBIE -> {if (ZOMBIE == null) ZOMBIE = new Texture(path + "zombie.jpg"); yield ZOMBIE; }
            case DEAD_GUARDIAN -> {if (DEAD_GUARDIAN == null) DEAD_GUARDIAN = new Texture(path + "deadGuardian.jpg"); yield DEAD_GUARDIAN; }
            case FRANK -> {if (FRANK == null) FRANK = new Texture(path + "frank.jpg"); yield FRANK; }
            case HIGHER_GHOST -> {if (HIGHER_GHOST == null) HIGHER_GHOST = new Texture(path + "higherGhost.jpg"); yield HIGHER_GHOST; }
            default -> {if (STOCK == null) STOCK = new Texture(path + "stock.jpg"); yield STOCK; }
        };
    }
}
