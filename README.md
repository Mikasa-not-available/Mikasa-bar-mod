# Mikasa-bars

Lightweight Fabric HUD by **Mikasa** for vanilla-style target health bars.

**Mikasa-bars** is a small **client-side** mod. It does **not** need to be installed on the server — only on your game client. When you look at a living entity, it shows a clean, **static** overlay at the top center of the screen: name, red health bar, and `CurrentHP/MaxHP`. The bar stays fixed on the HUD and does **not** float or jump around following mobs in the world.

| | |
|---|---|
| **Mod id** | `mikasa-bars` |
| **Version** | `fabric-26.3-1.3` |
| **Minecraft** | `26.3` |
| **Loader** | Fabric **0.19.5+** (**IMPORTANT**) |
| **API** | Fabric API (required) |
| **Java** | 25+ |
| **License** | MIT |
| **Side** | **Client-side** (server install optional / not required) |

Jar name: `Mikasa-bars-fabric-26.3-1.3.jar`

---

## Why this mod

Many multiplayer setups stay on **Fabric** and do not want heavy UI packs. Mikasa-bars adds only what you need while targeting something:

- vanilla-looking HUD (simple fill bar + text)
- no config files, no commands, almost no overhead
- works on dedicated Fabric servers **without** putting the mod on the server
- static top-center layout — easy to read in combat, no world-space jitter

---

## What it shows

When your crosshair targets a living entity (player, mob, NPC, monster):

1. **Name** — display name when available (follows the client language for translated mob names)
2. **Health bar** — red fill proportional to current / max health
3. **Numbers** — `CurrentHP/MaxHP` under the bar

If you look away, target a non-living entity, or hide the GUI (F1), the overlay disappears.

### Layout

```
              [ Entity Name ]
           [====== HP BAR ======]
              CurrentHP/MaxHP
```

Position: **top center** of the screen (static HUD).  
Not attached above the entity’s head in the 3D world.

---

## Features

| Feature | Detail |
|--------|--------|
| Target-only | Shows only for the entity you are looking at |
| Static HUD | Fixed top-center; does not follow / jump with mobs |
| Red HP bar | Vanilla-simple style fill bar |
| HP text | `CurrentHP/MaxHP` |
| Name | From `getDisplayName()` when present |
| Range | **2×** vanilla entity interaction range |
| GUI hide | Respects F1 / hidden HUD |
| No commands | Nothing to type |
| No config | Works out of the box |

---

## Commands

This mod has **no commands**.

---

## Configuration

Mikasa-bars currently has **no config files**.

Nothing is generated under `config/` on first launch. Install the jar and play.

---

## Dependencies

| Dependency | Required? |
|------------|-----------|
| Minecraft **26.3** | Yes |
| Fabric Loader **0.19.5+** (**IMPORTANT**) | Yes — older loaders (e.g. 0.19.3) will fail to load |
| **Fabric API** for 26.3 | Yes |
| Java **25+** | Yes |
| Server-side install | **No** |

---

## Install (client)

1. Install Fabric Loader for Minecraft **26.3** (**IMPORTANT:** use **0.19.5+**).
2. Put into the **client** `mods` folder:
   - `Mikasa-bars-fabric-26.3-1.3.jar`
   - Fabric API for 26.3
3. Launch Minecraft and look at a living entity.

### Multiplayer note

- **You** need the mod on your client to see the bars.
- Other players only see bars if **they** install it too.
- The dedicated server does **not** require this mod.

---

## How targeting works

The mod raycasts along your view using `ProjectileUtil.getHitResultOnViewVector`:

- only **living** entities that are alive
- excludes yourself and spectators
- max distance = `player.entityInteractionRange() * 2`

HP values come from the client’s view of the entity (`getHealth()` / `getMaxHealth()`), same source vanilla uses for local entity state.

---

## Build

```bat
gradlew.bat build
```

Output: `build/libs/Mikasa-bars-fabric-26.3-1.3.jar`

---

## License

MIT — Author: **Mikasa**
