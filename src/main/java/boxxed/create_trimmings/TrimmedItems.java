package boxxed.create_trimmings;

import boxxed.create_trimmings.foundation.item.DyedItemList;
import com.simibubi.create.AllTags;
import com.simibubi.create.Create;
import com.simibubi.create.content.equipment.armor.AllArmorMaterials;
import com.simibubi.create.content.equipment.armor.BacktankItem;
import com.simibubi.create.content.equipment.armor.DivingBootsItem;
import com.simibubi.create.content.equipment.armor.DivingHelmetItem;
import com.simibubi.create.foundation.data.AssetLookup;
import com.tterrag.registrate.util.entry.ItemEntry;
import net.minecraft.resources.ResourceLocation;

import static com.simibubi.create.AllTags.forgeItemTag;
import static boxxed.create_trimmings.Trimmings.REGISTRATE;

public class TrimmedItems {
    static {
        REGISTRATE.setCreativeTab(TrimmedCreativeModeTabs.BASE_CREATIVE_TAB);
    }

    public static final DyedItemList<BacktankItem.BacktankBlockItem> BACKTANK_PLACEABLES = new DyedItemList<>(color -> {
        String colorName = color.getSerializedName();
        return REGISTRATE.item(colorName + "_backtank_placeable",
                        p -> new BacktankItem.BacktankBlockItem(TrimmedBlocks.BACKTANKS.get(color).get(), TrimmedItems.BACKTANK_ITEMS.get(color)::get, p))
                .model((c, p) -> p.withExistingParent(c.getName(), p.mcLoc("item/barrier")))
                .register();
    });
    public static final ItemEntry<BacktankItem.BacktankBlockItem> TRANS_BACKTANK_PLACEABLE = REGISTRATE.item("trans_backtank_placeable",
                p -> new BacktankItem.BacktankBlockItem(TrimmedBlocks.TRANS_BACKTANK.get(), TrimmedItems.TRANS_BACKTANK_ITEM::get, p))
            .model((c, p) -> p.withExistingParent(c.getName(), p.mcLoc("item/barrier")))
            .register();
    public static final ItemEntry<BacktankItem> TRANS_BACKTANK_ITEM = REGISTRATE.item("trans_backtank",
            p -> new BacktankItem(AllArmorMaterials.COPPER, p, new ResourceLocation(Trimmings.MODID, "white_diving"),
                    TrimmedItems.TRANS_BACKTANK_PLACEABLE))
            .model(AssetLookup.customGenericItemModel("_","item"))
            .tag(forgeItemTag("armors/chestplates"))
            .register();

    public static final ItemEntry<BacktankItem.BacktankBlockItem> RAINBOW_BACKTANK_PLACEABLE = REGISTRATE.item("rainbow_backtank_placeable",
                p -> new BacktankItem.BacktankBlockItem(TrimmedBlocks.RAINBOW_BACKTANK.get(), TrimmedItems.RAINBOW_BACKTANK_ITEM::get, p))
            .model((c, p) -> p.withExistingParent(c.getName(), p.mcLoc("item/barrier")))
            .register();
    public static final ItemEntry<BacktankItem> RAINBOW_BACKTANK_ITEM = REGISTRATE.item("rainbow_backtank",
            p -> new BacktankItem(AllArmorMaterials.COPPER, p, new ResourceLocation(Trimmings.MODID, "red_diving"),
                    TrimmedItems.RAINBOW_BACKTANK_PLACEABLE))
            .model(AssetLookup.customGenericItemModel("_","item"))
            .tag(forgeItemTag("armors/chestplates"))
            .register();

    public static final DyedItemList<BacktankItem> BACKTANK_ITEMS = new DyedItemList<>(color -> {
        String colorName = color.getSerializedName();
        return REGISTRATE.item(colorName + "_backtank",
                        p -> new BacktankItem(AllArmorMaterials.COPPER, p, new ResourceLocation(Trimmings.MODID,colorName + "_diving"),
                                TrimmedItems.BACKTANK_PLACEABLES.get(color)))
                .model(AssetLookup.customGenericItemModel("_", "item"))
                .tag(AllTags.AllItemTags.PRESSURIZED_AIR_SOURCES.tag)
                .tag(forgeItemTag("armors/chestplates"))
                .register();
    });

    public static final DyedItemList<DivingHelmetItem> HELMET_ITEMS = new DyedItemList<>(color -> {
        String colorName = color.getSerializedName();
        return REGISTRATE
                .item(colorName + "_diving_helmet",
                        p -> new DivingHelmetItem(AllArmorMaterials.COPPER, p, new ResourceLocation(Trimmings.MODID,colorName + "_diving")))
                .tag(forgeItemTag("armors/helmets"))
                .register();
    });

    public static final DyedItemList<DivingBootsItem> BOOTS_ITEMS = new DyedItemList<>(color -> {
        String colorName = color.getSerializedName();
        return REGISTRATE
                .item(colorName + "_diving_boots",
                        p -> new DivingBootsItem(AllArmorMaterials.COPPER, p, new ResourceLocation(Trimmings.MODID,colorName + "_diving")))
                .tag(forgeItemTag("armors/boots"))
                .register();
    });

    public static void init() {}
}
