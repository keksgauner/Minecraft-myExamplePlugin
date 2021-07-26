package myplugin.apis;

import org.bukkit.*;
import org.bukkit.block.banner.Pattern;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFactory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.*;
import org.bukkit.material.MaterialData;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class ItemBuilder {

    private final ItemStack itemStack;
    private final ItemFactory itemFactory;
    private ItemMeta itemMeta;

    public ItemBuilder(ItemStack itemStack) {
        this.itemFactory = Bukkit.getItemFactory();
        this.itemStack = new ItemStack(itemStack);

        if (this.itemStack.getItemMeta() == null) {
            this.itemMeta = this.itemFactory.getItemMeta(this.itemStack.getType());
        } else {
            this.itemMeta = this.itemStack.getItemMeta();
        }
    }

    public ItemBuilder(Material type) {
        this(new ItemStack(type));
    }

    public ItemBuilder(Material type, int amount) {
        this(new ItemStack(type, amount));
    }

    @Deprecated
    public ItemBuilder(Material type, int amount, short damage) {
        this(new ItemStack(type, amount, damage));
    }

    public ItemStack toItemStack() {
        if (this.itemStack.getType() == Material.AIR) {
            return itemStack;
        }

        itemStack.setItemMeta(itemFactory.asMetaFor(itemMeta, itemStack));
        return itemStack;
    }

    public ItemBuilder setUnbreakable(boolean unbreakable) {
        this.itemMeta.setUnbreakable(unbreakable);
        return this;
    }

    public ItemBuilder addEnchantment(Enchantment ench, int level) {
        this.itemMeta.addEnchant(ench, level, false);
        return this;
    }

    public ItemBuilder addEnchantments(Map<Enchantment, Integer> enchantments) {
        enchantments.forEach(this::addEnchantment);
        return this;
    }

    public ItemBuilder addUnsafeEnchantment(Enchantment ench, int level) {
        this.itemMeta.addEnchant(ench, level, true);
        return this;
    }

    public ItemBuilder setAmount(int amount) {
        this.itemStack.setAmount(amount);
        return this;
    }

    public ItemBuilder removeEnchantment(Enchantment ench) {
        this.itemMeta.removeEnchant(ench);
        return this;
    }

    public ItemBuilder setDurability(int durability) {
        ItemMeta meta = this.itemMeta;
        if (meta instanceof Damageable) {
            ((Damageable) meta).setDamage((short) durability);
            this.setItemMeta(meta);
        }
        return this;
    }

    public ItemBuilder addDamage(int damage) {
        ItemMeta meta = this.itemMeta;
        if (meta instanceof Damageable) {
            ((Damageable) meta).setDamage(((Damageable) meta).getDamage() + damage);
            this.setItemMeta(meta);
        }
        return this;
    }

    @Deprecated
    public ItemBuilder setType(Material type) {
        this.itemStack.setType(type);
        return this;
    }

    @Deprecated
    public ItemBuilder setData(MaterialData data) {
        this.itemStack.setData(data);
        return this;
    }

    @Deprecated
    public ItemBuilder setItemMeta(ItemMeta itemMeta) {
        this.itemStack.setItemMeta(itemMeta);
        this.itemMeta = itemMeta;
        return this;
    }

    public ItemBuilder setDisplayName(String name) {
        this.itemMeta.setDisplayName(name);
        return this;
    }

    public ItemBuilder setName(String name) {
        return this.setDisplayName(name);
    }

    public ItemBuilder addWhiteSpaceLore() {
        addLore(" ");
        return this;
    }

    public ItemBuilder setLore(List<String> lore) {
        this.itemMeta.setLore(lore);
        return this;
    }

    public ItemBuilder addLore(String lore) {
        if (this.itemMeta.hasLore()) {
            List<String> lores = this.itemMeta.getLore();
            lores.add(lore);
            this.itemMeta.setLore(lores);
        } else {
            this.itemMeta.setLore(Collections.singletonList(lore));
        }
        return this;
    }

    public ItemBuilder setLore(String... lore) {
        this.itemMeta.setLore(Arrays.asList(lore));
        return this;
    }

    public ItemBuilder addItemFlags(ItemFlag... itemFlags) {
        this.itemMeta.addItemFlags(itemFlags);
        return this;
    }

    public ItemBuilder addItemFlag(ItemFlag... itemFlags) {
        this.itemMeta.addItemFlags(itemFlags);
        return this;
    }

    public ItemBuilder clearFlags() {
        itemMeta.getItemFlags().forEach(itemMeta::removeItemFlags);
        return this;
    }

    public ItemBuilder setOwningPlayer(OfflinePlayer player) {
        if (this.itemStack.getType() == Material.PLAYER_HEAD) {
            SkullMeta meta = (SkullMeta) this.itemMeta;
            meta.setOwningPlayer(player);
        }

        return this;
    }

    private boolean isPotion() {
        Material material = itemStack.getType();
        return material == Material.POTION;
    }

    public ItemBuilder addCustomEffect(PotionEffect effect, boolean overwrite) {
        if (this.isPotion()) {
            ((PotionMeta) this.itemMeta).addCustomEffect(effect, overwrite);
        }
        return this;
    }

    public ItemBuilder removeCustomEffect(PotionEffectType type) {
        if (this.isPotion()) {
            ((PotionMeta) this.itemMeta).removeCustomEffect(type);
        }
        return this;
    }

    public ItemBuilder removeEffect(PotionEffectType type) {
        return this.removeCustomEffect(type);
    }

    public ItemBuilder clearCustomEffects() {
        if (this.isPotion()) {
            ((PotionMeta) this.itemMeta).clearCustomEffects();
        }
        return this;
    }

    public ItemBuilder clearEffects() {
        return this.clearCustomEffects();
    }

    public ItemBuilder setMapScaling(boolean value) {
        if (this.itemStack.getType() == Material.MAP) {
            ((MapMeta) this.itemMeta).setScaling(value);
        }
        return this;
    }

    private boolean isLeatherArmor() {
        Material material = itemStack.getType();
        return material == Material.LEATHER_HELMET ||
                material == Material.LEATHER_CHESTPLATE ||
                material == Material.LEATHER_LEGGINGS ||
                material == Material.LEATHER_BOOTS;
    }

    private boolean isIronArmor() {
        Material material = itemStack.getType();
        return material == Material.IRON_HELMET ||
                material == Material.IRON_CHESTPLATE ||
                material == Material.IRON_LEGGINGS ||
                material == Material.IRON_BOOTS;
    }

    private boolean isGoldenArmor() {
        Material material = itemStack.getType();
        return material == Material.GOLDEN_HELMET ||
                material == Material.GOLDEN_CHESTPLATE ||
                material == Material.GOLDEN_LEGGINGS ||
                material == Material.GOLDEN_BOOTS;
    }

    private boolean isChainmailArmor() {
        Material material = itemStack.getType();
        return material == Material.CHAINMAIL_HELMET ||
                material == Material.CHAINMAIL_CHESTPLATE ||
                material == Material.CHAINMAIL_LEGGINGS ||
                material == Material.CHAINMAIL_BOOTS;
    }

    private boolean isDiamondArmor() {
        Material material = itemStack.getType();
        return material == Material.DIAMOND_HELMET ||
                material == Material.DIAMOND_CHESTPLATE ||
                material == Material.DIAMOND_LEGGINGS ||
                material == Material.DIAMOND_BOOTS;
    }

    public ItemBuilder setArmorColor(Color color) {
        if (this.isLeatherArmor()) {
            ((LeatherArmorMeta) this.itemMeta).setColor(color);
        }
        return this;
    }

    public ItemBuilder resetArmorColor() {
        if (this.isLeatherArmor()) {
            ((LeatherArmorMeta) this.itemMeta)
                    .setColor(this.itemFactory.getDefaultLeatherColor());
        }
        return this;
    }

    public ItemBuilder setFireworkPower(int power) {
        if (this.itemStack.getType() == Material.FIREWORK_ROCKET) {
            ((FireworkMeta) this.itemMeta).setPower(power);
        }
        return this;
    }

    public ItemBuilder removeFireworkEffect(int index) {
        if (this.itemStack.getType() == Material.FIREWORK_ROCKET) {
            ((FireworkMeta) this.itemMeta).removeEffect(index);
        }
        return this;
    }

    public ItemBuilder addFireworkEffect(FireworkEffect effect) {
        if (this.itemStack.getType() == Material.FIREWORK_ROCKET) {
            ((FireworkMeta) this.itemMeta).addEffect(effect);
        }
        return this;
    }

    public ItemBuilder addFireworkEffects(Iterable<FireworkEffect> effects) {
        if (this.itemStack.getType() == Material.FIREWORK_ROCKET) {
            ((FireworkMeta) this.itemMeta).addEffects(effects);
        }
        return this;
    }

    public ItemBuilder addFireworkEffects(FireworkEffect... effects) {
        if (this.itemStack.getType() == Material.FIREWORK_ROCKET) {
            ((FireworkMeta) this.itemMeta).addEffects(effects);
        }
        return this;
    }

    public ItemBuilder setChargeEffect(FireworkEffect effect) {
        if (this.itemStack.getType() == Material.FIREWORK_STAR) {
            ((FireworkEffectMeta) this.itemMeta).setEffect(effect);
        }
        return this;
    }

    public ItemBuilder setBannerPattern(int i, Pattern pattern) {
        if (this.itemStack.getType() == Material.BLACK_BANNER) {
            ((BannerMeta) this.itemMeta).setPattern(i, pattern);
        }
        return this;
    }

    public ItemBuilder setBannerPatterns(List<Pattern> patterns) {
        if (this.itemStack.getType() == Material.BLACK_BANNER) {
            ((BannerMeta) this.itemMeta).setPatterns(patterns);
        }
        return this;
    }

    public ItemBuilder removeBannerPattern(int i) {
        if (this.itemStack.getType() == Material.BLACK_BANNER) {
            ((BannerMeta) this.itemMeta).removePattern(i);
        }
        return this;
    }

    public ItemBuilder addBannerPattern(Pattern pattern) {
        if (this.itemStack.getType() == Material.BLACK_BANNER) {
            ((BannerMeta) this.itemMeta).addPattern(pattern);
        }
        return this;
    }

    public ItemBuilder addBannerPatterns(Pattern... patterns) {
        if (this.itemStack.getType() == Material.BLACK_BANNER) {
            BannerMeta bannerMeta = (BannerMeta) this.itemMeta;
            for (Pattern pattern : patterns) {
                bannerMeta.addPattern(pattern);
            }
        }
        return this;
    }

    private boolean isBook() {
        Material material = this.itemStack.getType();
        return material == Material.WRITABLE_BOOK || material == Material.WRITTEN_BOOK;
    }

    public ItemBuilder addBookPage(String... pages) {
        if (this.isBook()) {
            ((BookMeta) this.itemMeta).addPage(pages);
        }
        return this;
    }

    public ItemBuilder setBookAuthor(String author) {
        if (this.isBook()) {
            ((BookMeta) this.itemMeta).setAuthor(author);
        }
        return this;
    }

    public ItemBuilder setBookPage(int page, String data) {
        if (this.isBook()) {
            ((BookMeta) this.itemMeta).setPage(page, data);
        }
        return this;
    }

    public ItemBuilder setBookTitle(String title) {
        if (this.isBook()) {
            ((BookMeta) this.itemMeta).setTitle(title);
        }
        return this;
    }

    public ItemBuilder setPages(String... pages) {
        if (this.isBook()) {
            ((BookMeta) this.itemMeta).setPages(pages);
        }
        return this;
    }

    public ItemBuilder setPages(List<String> pages) {
        if (this.isBook()) {
            ((BookMeta) this.itemMeta).setPages(pages);
        }
        return this;
    }
}
