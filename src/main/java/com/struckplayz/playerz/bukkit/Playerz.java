package com.struckplayz.playerz.bukkit;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Playerz
extends JavaPlugin
{
    File config = new File("plugins/Playerz/config.yml");
    FileConfiguration fc = (FileConfiguration)new YamlConfiguration();
    public void onEnable() {
        createFiles();
    }
    public void onDisable() {}
    public void createFiles() {
        if (this.config.exists()) {
            return;
        }
        /*this.fc.set("Permissions.Enabled.List", Boolean.valueOf(false));
        this.fc.set("Permissions.Enabled.Toggle", Boolean.valueOf(true));
        this.fc.set("Permissions.OpOverride", Boolean.valueOf(true));
        this.fc.set("Permissions.Denied", "&4[Playerz] &fYou aren't allowed to use this command.");*/
        this.fc.set("List.NumberOfGroups", Integer.valueOf(1));
        this.fc.set("List.DisplayPrefix", Boolean.valueOf(true));
        this.fc.set("List.DisplaySuffix", Boolean.valueOf(true));
        this.fc.set("List.Prefix", "&6----------------------[&ePlayerz&6]-----------------------");
        this.fc.set("List.PlayerAmount", "&3There are (&6%ONLINEPLAYERS%&3/&6%MAXPLAYERS%&3) players online.");
        int nog = this.fc.getInt("List.NumberOfGroups");
        for (int i = 1; i < nog + 1; i++) {
            this.fc.set("List." + i + ".Message", "&3Group " + i + ": &6%GROUP" + i + "%");
        }
        this.fc.set("List.Suffix", "&6-----------------------------------------------------");
        /*this.fc.set("Broadcast.RunOnStart", Boolean.valueOf(false));
        this.fc.set("Broadcast.Interbal", Integer.valueOf(300));
        this.fc.set("Broadcast.DisplayPrefix", Boolean.valueOf(true));
        this.fc.set("Broadcast.DisplaySuffix", Boolean.valueOf(true));
        this.fc.set("Broadcast.DisplayPlayerAmount", Boolean.valueOf(true));
        this.fc.set("Broadcast.DisplayGroups", Boolean.valueOf(true));
        this.fc.set("Broadcast.Start", "&4[Playerz] &fAutomatic list broadcasts started.");
        this.fc.set("Broadcast.Stop", "&4[Playerz] &fAutomatic list broadcasts stopped.");*/
        try {
            this.fc.save(this.config);
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("list")) {
            try {
                this.fc.load(this.config);
            } catch (IOException|org.bukkit.configuration.InvalidConfigurationException e) {
                e.printStackTrace();
            } 
            String list = "";
            boolean display_prefix = this.fc.getBoolean("List.DisplayPrefix");
            boolean display_suffix = this.fc.getBoolean("List.DisplaySuffix");
            String prefix = color(this.fc.getString("List.Prefix"));
            String suffix = color(this.fc.getString("List.Suffix"));
            String max = color(this.fc.getString("List.PlayerAmount").replaceAll("%ONLINEPLAYERS%", (new StringBuilder(String.valueOf(Bukkit.getOnlinePlayers().size()))).toString()).replaceAll("%MAXPLAYERS%", (new StringBuilder(String.valueOf(Bukkit.getMaxPlayers()))).toString()));
            int nog = this.fc.getInt("List.NumberOfGroups");
            if (display_prefix) {
                list = String.valueOf(list) + prefix + "\n";
            }
            list = String.valueOf(list) + max + "\n";
            for (int i = 1; i < nog + 1; i++) {
                String g = this.fc.getString("List." + i + ".Message");
                String var = "%GROUP" + i + "%";
                ArrayList<String> a = new ArrayList<>();
                for (Player all : Bukkit.getOnlinePlayers()) {
                    if (all.hasPermission("playerz." + i)) {
                        a.add(all.getName());
                    }
                }
                String f = g.replaceAll(var, deconstructList(a)).replaceAll("%online%", (new StringBuilder(String.valueOf(a.size()))).toString());
                if (a.size() == 0) {
                    f = String.valueOf(f) + "&cnone";
                }
                list = String.valueOf(list) + f + "\n";
            } 
            if (display_suffix) {
                list = String.valueOf(list) + suffix;
            }
            sender.sendMessage(color(list));
        } 
        return true;
    }
    public static String deconstructList(List<String> s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.size(); i++) {
            if (i + 1 == s.size()) {
                sb.append(s.get(i));
                return sb.toString();
            } 
            sb.append(String.valueOf(s.get(i)) + ", ");
        } 
        return sb.toString();
    }
    public static String color(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }
}