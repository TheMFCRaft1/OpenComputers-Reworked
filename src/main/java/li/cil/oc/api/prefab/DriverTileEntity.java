package li.cil.oc.api.prefab;

import li.cil.oc.compat.vanilla.tileentity.TileEntity;
import li.cil.oc.compat.vanilla.world.World;

/**
 * @deprecated Use {@link DriverSidedTileEntity} instead.
 */
@Deprecated // TODO Remove in OC 1.7
public abstract class DriverTileEntity implements li.cil.oc.api.driver.Block {
    public abstract Class<?> getTileEntityClass();

    @Override
    public boolean worksWith(final World world, final int x, final int y, final int z) {
        final Class<?> filter = getTileEntityClass();
        if (filter == null) {
            // This can happen if filter classes are deduced by reflection and
            // the class in question is not present.
            return false;
        }
        final TileEntity tileEntity = world.getTileEntity(x, y, z);
        return tileEntity != null && filter.isAssignableFrom(tileEntity.getClass());
    }
}
