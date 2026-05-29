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
