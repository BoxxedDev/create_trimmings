package boxxed.create_trimmings;

import boxxed.create_trimmings.content.block.ColoredBacktankBlock;
import com.simibubi.create.AllCreativeModeTabs;
import com.simibubi.create.Create;
import com.simibubi.create.foundation.block.DyedBlockList;
import com.simibubi.create.foundation.data.BuilderTransformers;
import com.simibubi.create.foundation.data.SharedProperties;
import com.tterrag.registrate.util.entry.BlockEntry;

import static boxxed.create_trimmings.Trimmings.REGISTRATE;

public class TrimmedBlocks {
    static {
        REGISTRATE.setCreativeTab(TrimmedCreativeModeTabs.BASE_CREATIVE_TAB);
    }

    public static final DyedBlockList<ColoredBacktankBlock> BACKTANKS = new DyedBlockList<>(color -> {
        String colorName = color.getSerializedName();
        return REGISTRATE.block(colorName + "_backtank", ColoredBacktankBlock::new)
                .initialProperties(SharedProperties::copperMetal)
                .transform(BuilderTransformers.backtank(TrimmedItems.BACKTANK_ITEMS.get(color)::get))
                .register();
    });
    public static final BlockEntry<ColoredBacktankBlock> TRANS_BACKTANK = REGISTRATE.block("trans_backtank", ColoredBacktankBlock::new)
            .initialProperties(SharedProperties::copperMetal)
            .transform(BuilderTransformers.backtank(TrimmedItems.TRANS_BACKTANK_ITEM::get))
            .register();
    public static final BlockEntry<ColoredBacktankBlock> RAINBOW_BACKTANK = REGISTRATE.block("rainbow_backtank", ColoredBacktankBlock::new)
            .initialProperties(SharedProperties::copperMetal)
            .transform(BuilderTransformers.backtank(TrimmedItems.RAINBOW_BACKTANK_ITEM::get))
            .register();

    public static void init() {}
}
