package com.savage.chatmoderator;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.util.List;
import java.util.logging.Logger;

@SuppressWarnings({"unused"})
public class WatchChat implements Listener {

    ChatModerator plugin = ChatModerator.getPlugin();
    FileConfiguration config = ChatModerator.getPlugin().getConfig();
    public List<String> bannedWords;

    Logger log = plugin.getLogger();

    public void sendRequest(String url, String playerName) {
        try {
            // Create a URL object with the endpoint you want to send the POST request to.
            URL obj = new URL(url);
            // Open a connection to that URL.
            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();

            // Set the request method to POST.
            connection.setRequestMethod("POST");

            // Enable input and output streams for this connection.
            connection.setDoOutput(true);

            // Set the content type, if needed.
            connection.setRequestProperty("Content-Type", "application/json");

            // Create the data you want to send in your POST request.
            String roleID = config.getString("moderation-ping-role-id");
            String postData = "{\"content\": \"A user has said a banned word in Minecraft chat. <@&" + roleID + ">\", \"username\": \"" + playerName + "\"}";


            // Get an output stream from the connection and write your data to it.
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = postData.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // Now, you can connect and get the response, if needed.
            int responseCode = connection.getResponseCode();
            log.info("Response Code: " + responseCode);

            // Handle the response here.

            // Don't forget to close the connection.
            connection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @EventHandler
    public void scanChatMessages(AsyncPlayerChatEvent event) {
        String message = event.getMessage();
        Player player = event.getPlayer();
        bannedWords = config.getStringList("banned-words");
        for(CharSequence word : bannedWords) {
            if(message.toLowerCase().contains(word)) {
                event.setCancelled(true);
                sendRequest(config.getString("webhook-url"), player.getName());
            }
        }
    }

}
