package boxxed.create_trimmings;

import boxxed.create_trimmings.foundation.item.DyedItemList;
import com.simibubi.create.AllTags;
import com.simibubi.create.Create;
import com.simibubi.create.content.equipment.armor.AllArmorMaterials;
import com.simibubi.create.content.equipment.armor.BacktankItem;
import com.simibubi.create.foundation.data.AssetLookup;

import static com.simibubi.create.AllTags.forgeItemTag;
import static boxxed.create_trimmings.Trimmings.REGISTRATE;

public class TrimmedItems {
    public static final DyedItemList<BacktankItem.BacktankBlockItem> BACKTANK_PLACEABLES = new DyedItemList<>(color -> {
        String colorName = color.getSerializedName();
        return REGISTRATE.item(colorName + "_backtank_placeable",
                        p -> new BacktankItem.BacktankBlockItem(TrimmedBlocks.BACKTANKS.get(color).get(), TrimmedItems.BACKTANK_ITEMS.get(color)::get, p))
                .model((c, p) -> p.withExistingParent(c.getName(), p.mcLoc("item/barrier")))
                .register();
    });

    public static final DyedItemList<BacktankItem> BACKTANK_ITEMS = new DyedItemList<>(color -> {
        String colorName = color.getSerializedName();
        return REGISTRATE.item(colorName + "_backtank",
                        p -> new BacktankItem(AllArmorMaterials.COPPER, p, Create.asResource("copper_diving"),
                                TrimmedItems.BACKTANK_PLACEABLES.get(color)))
                .model(AssetLookup.customGenericItemModel("_", "item"))
                .tag(AllTags.AllItemTags.PRESSURIZED_AIR_SOURCES.tag)
                .tag(forgeItemTag("armors/chestplates"))
                .register();
    });

    public static void init() {}
}
