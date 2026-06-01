package cpw.mods.fml.relauncher;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.CLASS)
public @interface SideOnly {
    Side value();
}
