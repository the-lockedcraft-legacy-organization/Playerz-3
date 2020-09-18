# Description
A highly customizable player list for spigot and bungee.
https://www.spigotmc.org/resources/spigot-bungeecord-playerz.23597/

## Background
- PlayerZ is a bukkit plugin created by C_Kin for Bukkit some time before 2015 (v1).
- After having been dropped abandoned by C_Kin, the plugin was re-uploaded to spigot by BrettPlayMC
- In 2016, the plugin was forked by Struck713 to add support for newer versions and BungeeCord (v2).
- Dependency updates throughout 2017 broke a good many of this plugins features.
- This is a fork from the LCLO to attempt to resolve those issues.

# Permissions
```
playerz.<int> -- Show up under group <int> in the listing.
```

# Instructions
Information on configuring this plugin can be found in the v2 documentation at
https://www.spigotmc.org/wiki/playerz-spigot-official-config-documentation-2-0/

# Configuration
Configuration nodes are discussed at
https://www.spigotmc.org/wiki/playerz-spigot-official-config-documentation-2-0/

# Changes
#### [Version 3.1.0] LCLO Fork
 - Re-structured plugin to be more intuitive
 - Updated to 1.16.3
 - Removed unused sections of the config.
 - Fixed an issue where the plugin listing could break in 1.11+
 - Switched to ProxyServer over the internal BungeeCord (more stable)
 - Fixed an issue from which the default config could generate a null pointer.
#### [Version 2.0.1] Struck713 Fork
 - Added BungeeCord Support
#### [Version 2.0.0] Struck713 Fork
 - Plugin re-coded
 - Removed AutoList feature
 - Reworked permissions
 - Format codes now supported
 - Config updated
 - Optimized
 - Updated default package to include a readme
#### [Version 0.0.2] BrettPlayMC Update
 - Removed the /who alias
 - Bumped plugin version number.
#### [Version 0.0.1]
 - Plugin created by C_Kin.
