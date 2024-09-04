package boxxed.create_trimmings;

import boxxed.create_trimmings.content.block.ColoredBacktankBlockEntity;
import boxxed.create_trimmings.content.block.ColoredBacktankInstance;
import boxxed.create_trimmings.content.block.ColoredBacktankRenderer;
import com.tterrag.registrate.util.entry.BlockEntityEntry;

import static boxxed.create_trimmings.Trimmings.REGISTRATE;

public class TrimmedBlockEntityTypes {
    public static final BlockEntityEntry<ColoredBacktankBlockEntity> COLORED_BACKTANK = REGISTRATE
            .blockEntity("colored_backtank", ColoredBacktankBlockEntity::new)
            .instance(() -> ColoredBacktankInstance::new)
            .validBlocks(TrimmedBlocks.TRANS_BACKTANK, TrimmedBlocks.RAINBOW_BACKTANK)
            .validBlocks(TrimmedBlocks.BACKTANKS.toArray())
            .renderer(() -> ColoredBacktankRenderer::new)
            .register();

    public static void init(){}
}
