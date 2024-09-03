package boxxed.create_trimmings;

import boxxed.create_trimmings.foundation.block.ColoredBacktankBlockEntity;
import boxxed.create_trimmings.foundation.block.ColoredBacktankInstance;
import boxxed.create_trimmings.foundation.block.ColoredBacktankRenderer;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.content.equipment.armor.BacktankBlockEntity;
import com.simibubi.create.content.equipment.armor.BacktankInstance;
import com.simibubi.create.content.equipment.armor.BacktankRenderer;
import com.tterrag.registrate.util.entry.BlockEntityEntry;
import net.minecraft.world.item.DyeColor;

import static boxxed.create_trimmings.Trimmings.REGISTRATE;

public class TrimmedBlockEntityTypes {
    public static final BlockEntityEntry<ColoredBacktankBlockEntity> COLORED_BACKTANK = REGISTRATE
            .blockEntity("colored_backtank", ColoredBacktankBlockEntity::new)
            .instance(() -> ColoredBacktankInstance::new)
            .validBlocks(TrimmedBlocks.BACKTANKS.toArray())
            .renderer(() -> ColoredBacktankRenderer::new)
            .register();

    public static void init(){}
}
