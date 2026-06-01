package li.cil.oc.compat.vanilla.entity.projectile;

import li.cil.oc.compat.vanilla.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

/** Legacy arrow entity stub. */
public class EntityArrow extends Entity {
    public EntityArrow(Level level) {
        super(EntityType.ARROW, level);
    }
}
