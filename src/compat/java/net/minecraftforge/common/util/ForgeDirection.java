package net.minecraftforge.common.util;

import net.minecraft.core.Direction;

/** Forge 1.7.10 facing compatibility for OpenComputers port. */
public enum ForgeDirection {
    DOWN(Direction.DOWN, 0, -1, 0),
    UP(Direction.UP, 0, 1, 0),
    NORTH(Direction.NORTH, 0, 0, -1),
    SOUTH(Direction.SOUTH, 0, 0, 1),
    WEST(Direction.WEST, -1, 0, 0),
    EAST(Direction.EAST, 1, 0, 0),
    UNKNOWN(null, 0, 0, 0);

    public static final ForgeDirection[] VALID_DIRECTIONS = {DOWN, UP, NORTH, SOUTH, WEST, EAST};

    public final int offsetX;
    public final int offsetY;
    public final int offsetZ;
    private final Direction mc;

    ForgeDirection(Direction mc, int offsetX, int offsetY, int offsetZ) {
        this.mc = mc;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.offsetZ = offsetZ;
    }

    public Direction toDirection() {
        return mc;
    }

    public static ForgeDirection getOrientation(int side) {
        if (side < 0 || side >= VALID_DIRECTIONS.length) return UNKNOWN;
        return VALID_DIRECTIONS[side];
    }

    public static ForgeDirection getOrientation(Direction direction) {
        if (direction == null) return UNKNOWN;
        return switch (direction) {
            case DOWN -> DOWN;
            case UP -> UP;
            case NORTH -> NORTH;
            case SOUTH -> SOUTH;
            case WEST -> WEST;
            case EAST -> EAST;
        };
    }

    public ForgeDirection getOpposite() {
        return switch (this) {
            case DOWN -> UP;
            case UP -> DOWN;
            case NORTH -> SOUTH;
            case SOUTH -> NORTH;
            case WEST -> EAST;
            case EAST -> WEST;
            default -> UNKNOWN;
        };
    }
}
