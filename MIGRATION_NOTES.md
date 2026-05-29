# OpenComputers NeoForge Port — Migration Notes

## Phase 1 — Projekt-Setup (2026-05-29)

### Build-System

- **ForgeGradle 1.7.10** ersetzt durch **ModDevGradle 2.0.141** (NeoForge 21.1.231, MC 1.21.1).
- **Gradle 9.2.1**, **Java 21**, **Scala 3.4.2**.
- Mixed Java/Scala: beide Quellverzeichnisse unter `sourceSets.main.scala`; Java-`srcDirs` leer → Scala-Compiler zuerst.
- Alte `build.properties` bleibt vorerst im Repo (Integrations-Versionen für spätere Phasen).

### Mod-ID

- NeoForge verlangt lowercase: `opencomputers` (war `OpenComputers` auf 1.7.10).
- Paket `li.cil.oc` unverändert.

### Lua-VM-Bibliotheken (`libs/`)

| JAR | Quelle | Zweck |
|-----|--------|-------|
| `OC-LuaJ-20220907.1.jar` | asie.pl/javadeps (maven.cil.li offline) | Pure-Java LuaJ-VM |
| `OC-JNLua-20230530.0.jar` | asie.pl/javadeps | JNLua JNI-Bindings |
| `OC-JNLua-Natives-20220928.1.jar` | asie.pl/javadeps | Native `.dll`/`.so` (embedded im Mod-JAR) |

JARs werden wie zuvor per `embedded`-Configuration in das Mod-JAR gepackt.

### Metadaten

- `mcmod.info` → `src/main/templates/META-INF/neoforge.mods.toml` (Gradle expand).
- `@Mod`-Klasse: Constructor-Injection mit `IModEventBus` (NeoForge-Standard).
- `@SidedProxy` / `@EventHandler` / `FMLPreInitializationEvent` entfernt — Stub-Listener in `OpenComputers.scala`.

### Access Transformer

- Weiterhin `src/main/resources/oc_at.cfg`; Validierung via `neoForge.accessTransformers`.

### Bekannte TODOs (folgende Phasen)

- [ ] Gesamter Legacy-Quellcode (Forge 1.7.10 APIs) kompiliert noch nicht.
- [ ] `Proxy.preInit/init/postInit` auf DeferredRegister + ModEvents migrieren.
- [ ] Netzwerk: `SimpleNetworkWrapper` → `CustomPacketPayload`.
- [ ] Native-Library-Lademechanismus (`NativeLua53Architecture`) für NeoForge-Ressourcen anpassen.
- [ ] Alle Integrations-Module optional halten / deaktivieren wo Mod fehlt.

### Kompilierstatus Phase 1

Erwartet: `./gradlew build` schlägt fehl, bis Registry/BlockEntity/API-Migration (Phasen 2–6) abgeschlossen ist. Gradle-Setup und Mod-Metadaten-Generierung sollten funktionieren.

---

## Phase 2 — Event-System & DeferredRegister-Skelett (2026-05-29)

### Neue Dateien

| Datei | Zweck |
|-------|-------|
| `common/init/ModRegistries.scala` | `DeferredRegister` für Blocks, Items, BlockEntityTypes, EntityTypes, MenuTypes, CreativeModeTabs |
| `common/ModBootstrap.scala` | Lifecycle-Orchestrierung (ersetzt Proxy preInit/init/postInit) |
| `common/ModEventHandler.scala` | `@EventBusSubscriber(GAME)` — Server start/stop, Internet-Filter-Warnungen |
| `client/ClientModEventHandler.scala` | `@EventBusSubscriber(MOD, CLIENT)` — Client-Setup-Stub |
| `common/network/ModNetworking.scala` | `RegisterPayloadHandlersEvent`-Stub für CustomPacketPayload |

### OpenComputers.scala

- Constructor: Config laden → DeferredRegister registrieren → Game-Bus-Handler
- `FMLCommonSetupEvent` / `FMLClientSetupEvent` / `FMLLoadCompleteEvent` an `ModBootstrap` delegiert
- `LEGACY_ID = "OpenComputers"` für alte Saves/IMC-Pfade

### Settings.scala (NeoForge-kompatibel)

- `Loader.instance` → `FMLPaths.CONFIGDIR`
- FML `VersionRange` → internes `ConfigVersionRange`
- `JavaConverters` → `scala.jdk.CollectionConverters`
- `Platform.EOL` → `System.lineSeparator()`

### SideTracker.java

- `FMLCommonHandler.getEffectiveSide()` → `SidedThreadGroups.SERVER` + explizite Server-Threads

### Legacy Proxy-Klassen

`common/Proxy.scala`, `client/Proxy.scala`, `server/Proxy.scala` bleiben unverändert bis Phase 3 (Block/Item/BlockEntity-Migration). `@SidedProxy` entfällt zugunsten von `@EventBusSubscriber(Dist.CLIENT)`.

### Bekannte TODOs (Phase 3+)

- [ ] `Blocks.init()` / `Items.init()` → DeferredRegister-Supplier auf `ModRegistries`
- [ ] `ModBootstrap.commonSetup`: API-Bootstrap, Tags, Loot, Recipes, Integrations
- [ ] `ModNetworking`: alle Pakete als `CustomPacketPayload`-Records
- [ ] `ModEventHandler`: CommandHandler (Brigadier), ThreadPoolFactory
- [ ] `ClientModEventHandler`: Rendering, KeyBindings, GuiHandler
- [ ] Legacy-Event-Handler (`ModOpenComputers.initialize`) auf NeoForge-Bus umstellen

