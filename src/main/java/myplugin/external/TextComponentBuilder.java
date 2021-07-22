package myplugin.external;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

/**
 * @author KeksGauner
 */
public class TextComponentBuilder {

    private final String text;
    private String hover;
    private String click;
    private ClickEvent.Action action;

    public TextComponentBuilder(String text) {
        this.text = ChatColor.translateAlternateColorCodes('&', text);
    }

    public TextComponentBuilder addHover(String hover) {
        this.hover = ChatColor.translateAlternateColorCodes('&', hover);
        return this;
    }

    public TextComponentBuilder addClickEvent(ClickEvent.Action clickEventAction, String value) {
        this.action = clickEventAction;
        this.click = ChatColor.translateAlternateColorCodes('&', value);
        return this;
    }

    public TextComponent build() {
        TextComponent textComponent = new TextComponent();
        textComponent.setText(this.text);
        if(this.hover != null) {
            textComponent.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(this.hover).create()));
        }
        if(this.click != null && (this.action != null)) {
            textComponent.setClickEvent(new ClickEvent(action, this.click));
        }
        return textComponent;
    }

    public enum ClickEventType {
        RUN_COMMAND, SUGGEST_COMMAND, OPEN_URL
    }

    public static TextComponent create(Object ... args) {
        TextComponent component = new TextComponent("");
        TextComponent tempComponent = new TextComponent("");

        Object[] newArgs = args;

        boolean lastString = false; //Used to check if the new tempComponent is added to the component
        for(Object object : newArgs) {
            lastString = false;
            if(object instanceof String) {
                lastString = true;

                tempComponent.addExtra( ChatColor.translateAlternateColorCodes('&', (String) object));
                component.addExtra(tempComponent);
                tempComponent = new TextComponent("");

            } else if (object instanceof TextComponent) {
                component.addExtra((TextComponent) object);
                tempComponent = new TextComponent("");
            }
        }

        if(!lastString) {
            component.addExtra(tempComponent);
        }

        return component;
    }

// Stringbuilder // TextComponent component = TextComponentBuilder.create("Sting davor", new TextComponentBuilder("Hallo das ist ein Text").addHover("> Hilfeseite").build(), "String danach");
// Simple Build  // TextComponent component = new TextComponentBuilder("Hallo das ist ein Text").addHover("> Hilfeseite").addClickEvent(ClickEvent.Action.RUN_COMMAND, "/help").build();
// Spigot        // player.spigot().sendMessage(component);
// Bungee    	 // player.sendMessage(component);

// Recommend 	// player.sendMessage(TextComponentBuilder.create("Sting davor", new TextComponentBuilder("Hallo das ist ein Text").addHover("> Hilfeseite").build(), "String danach"));

}