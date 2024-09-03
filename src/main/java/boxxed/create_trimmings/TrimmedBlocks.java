package boxxed.create_trimmings;

import boxxed.create_trimmings.foundation.block.ColoredBacktankBlock;
import com.simibubi.create.content.equipment.armor.BacktankBlock;
import com.simibubi.create.foundation.block.DyedBlockList;
import com.simibubi.create.foundation.data.BuilderTransformers;
import com.simibubi.create.foundation.data.SharedProperties;

import static boxxed.create_trimmings.Trimmings.REGISTRATE;

public class TrimmedBlocks {
    public static final DyedBlockList<ColoredBacktankBlock> BACKTANKS = new DyedBlockList<>(color -> {
        String colorName = color.getSerializedName();
        return REGISTRATE.block(colorName + "_backtank", ColoredBacktankBlock::new)
                .initialProperties(SharedProperties::copperMetal)
                .transform(BuilderTransformers.backtank(TrimmedItems.BACKTANK_ITEMS.get(color)::get))
                .register();
    });

    public static void init() {}
}
