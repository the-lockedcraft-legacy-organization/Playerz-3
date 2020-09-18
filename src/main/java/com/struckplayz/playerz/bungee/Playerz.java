package com.struckplayz.playerz.bungee;

import java.io.File;
import java.io.IOException;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

public class Playerz
extends Plugin
{
    public static File config = new File("plugins/Playerz/config.yml");
    public void onEnable() {
        createFiles();
        getProxy().getPluginManager().registerCommand(this, new ListCommand());
    }
    public void onDisable() {}
    public void createFiles() {
        if (config.exists()) {
            return;
        }
        Configuration fc = null;
        try {
            fc = ConfigurationProvider.getProvider(YamlConfiguration.class).load(config);
        } catch (IOException e1) {
            File f = new File(config.getPath().replaceAll("config.yml", ""));
            f.mkdirs();
            try {
                config.createNewFile();
            } catch (IOException e) {
            e.printStackTrace();
            } 
            try {
                fc = ConfigurationProvider.getProvider(YamlConfiguration.class).load(config);
            } catch (IOException e) {
                return;
            } 
        } 
        /*fc.set("Permissions.Enabled.List", Boolean.valueOf(false));
        fc.set("Permissions.Enabled.Toggle", Boolean.valueOf(true));
        fc.set("Permissions.OpOverride", Boolean.valueOf(true));
        fc.set("Permissions.Denied", "&4[Playerz] &fYou aren't allowed to use this command.");*/
        fc.set("List.NumberOfGroups", Integer.valueOf(1));
        fc.set("List.DisplayPrefix", Boolean.valueOf(true));
        fc.set("List.DisplaySuffix", Boolean.valueOf(true));
        /*fc.set("List.NobodyOnline", "&cN/A");*/
        fc.set("List.Prefix", "&6----------------------[&ePlayerz&6]-----------------------");
        fc.set("List.PlayerAmount", "&3There are (&6%ONLINEPLAYERS%&3/&6%MAXPLAYERS%&3) players online.");
        int nog = fc.getInt("List.NumberOfGroups");
        for (int i = 1; i < nog + 1; i++) {
            fc.set("List." + i + ".Message", "&3Group " + i + ": &6%GROUP" + i + "%");
            fc.set("List." + i + ".JSON.Enabled", Boolean.valueOf(true));
            fc.set("List." + i + ".JSON.Hover.Enabled", Boolean.valueOf(true));
            fc.set("List." + i + ".JSON.Hover.String", "Server: %server%");
            fc.set("List." + i + ".JSON.Click.Enabled", Boolean.valueOf(true));
            fc.set("List." + i + ".JSON.Click.String", "server %server%;help");
        } 
        fc.set("List.Suffix", "&6-----------------------------------------------------");
        /*fc.set("Broadcast.RunOnStart", Boolean.valueOf(false));
        fc.set("Broadcast.Interbal", Integer.valueOf(300));
        fc.set("Broadcast.DisplayPrefix", Boolean.valueOf(true));
        fc.set("Broadcast.DisplaySuffix", Boolean.valueOf(true));
        fc.set("Broadcast.DisplayPlayerAmount", Boolean.valueOf(true));
        fc.set("Broadcast.DisplayGroups", Boolean.valueOf(true));
        fc.set("Broadcast.Start", "&4[Playerz] &fAutomatic list broadcasts started.");
        fc.set("Broadcast.Stop", "&4[Playerz] &fAutomatic list broadcasts stopped.");*/
        try {
            ConfigurationProvider.getProvider(YamlConfiguration.class).save(fc, config);
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }
}