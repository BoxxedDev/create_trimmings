package boxxed.create_trimmings.content.block;

import com.jozufozu.flywheel.api.Instancer;
import com.jozufozu.flywheel.api.MaterialManager;
import com.simibubi.create.content.kinetics.base.SingleRotatingInstance;
import com.simibubi.create.content.kinetics.base.flwdata.RotatingData;

public class ColoredBacktankInstance extends SingleRotatingInstance<ColoredBacktankBlockEntity> {
    public ColoredBacktankInstance(MaterialManager materialManager, ColoredBacktankBlockEntity blockEntity) {
        super(materialManager, blockEntity);
    }

    @Override
    protected Instancer<RotatingData> getModel() {
        return getRotatingMaterial().getModel(ColoredBacktankRenderer.getShaftModel(blockState), blockState);
    }
}
