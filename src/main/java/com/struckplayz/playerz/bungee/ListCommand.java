package com.struckplayz.playerz.bungee;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

public class ListCommand
    extends Command
{
    public ListCommand() {
        super("list");
    }
    public void execute(CommandSender sender, String[] strings) {
        Configuration fc = null;
        try {
            fc = ConfigurationProvider.getProvider(YamlConfiguration.class).load(Playerz.config);
        } catch (IOException iOException) {}
        
        
        String list = "";
        boolean display_prefix = fc.getBoolean("List.DisplayPrefix");
        boolean display_suffix = fc.getBoolean("List.DisplaySuffix");
        String prefix = color(fc.getString("List.Prefix"));
        String suffix = color(fc.getString("List.Suffix"));
        String max = color(fc.getString("List.PlayerAmount").replaceAll("%ONLINEPLAYERS%", (new StringBuilder(String.valueOf(ProxyServer.getInstance().getPlayers().size()))).toString()).replaceAll("%MAXPLAYERS%", (new StringBuilder(String.valueOf(ProxyServer.getInstance().getConfig().getPlayerLimit()))).toString()));
        int nog = fc.getInt("List.NumberOfGroups");
        if (display_prefix) {
            list = String.valueOf(list) + prefix + "\n";
        }
        list = String.valueOf(list) + max + "\n";
        for (int i = 1; i < nog + 1; i++) {
            String g = fc.getString("List." + i + ".Message");
            String var = "%GROUP" + i + "%";
            ArrayList<String> a = new ArrayList<>();
            for (ProxiedPlayer all : ProxyServer.getInstance().getPlayers()) {
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