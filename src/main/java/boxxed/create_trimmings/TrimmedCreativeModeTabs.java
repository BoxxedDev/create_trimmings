package boxxed.create_trimmings;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllCreativeModeTabs;
import com.simibubi.create.AllItems;
import com.simibubi.create.Create;
import com.simibubi.create.content.contraptions.actors.seat.SeatBlock;
import com.simibubi.create.content.equipment.armor.BacktankItem;
import com.simibubi.create.content.equipment.armor.BacktankUtil;
import com.simibubi.create.content.equipment.armor.DivingBootsItem;
import com.simibubi.create.content.equipment.armor.DivingHelmetItem;
import com.simibubi.create.content.equipment.toolbox.ToolboxBlock;
import com.simibubi.create.content.kinetics.crank.ValveHandleBlock;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.foundation.item.TagDependentIngredientItem;
import com.simibubi.create.foundation.utility.Components;
import com.tterrag.registrate.util.entry.BlockEntry;
import com.tterrag.registrate.util.entry.ItemEntry;
import com.tterrag.registrate.util.entry.ItemProviderEntry;
import com.tterrag.registrate.util.entry.RegistryEntry;
import it.unimi.dsi.fastutil.objects.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import org.apache.commons.lang3.mutable.MutableObject;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class TrimmedCreativeModeTabs {
    private static final DeferredRegister<CreativeModeTab> REGISTER =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Trimmings.MODID);

    public static final RegistryObject<CreativeModeTab> BASE_CREATIVE_TAB = REGISTER.register("base",
            () -> CreativeModeTab.builder()
                    .title(Components.translatable("itemGroup.create.base"))
                    .withTabsBefore(AllCreativeModeTabs.PALETTES_CREATIVE_TAB.getId())
                    .icon(() -> TrimmedItems.HELMET_ITEMS.get(DyeColor.MAGENTA).asStack())
                    .displayItems(new RegistrateDisplayItemsGenerator(TrimmedCreativeModeTabs.BASE_CREATIVE_TAB))
                    .build());

    public static void register(IEventBus modEventBus) {
        REGISTER.register(modEventBus);
    }

    private static class RegistrateDisplayItemsGenerator implements CreativeModeTab.DisplayItemsGenerator {
        private final RegistryObject<CreativeModeTab> tabFilter;

        public RegistrateDisplayItemsGenerator(RegistryObject<CreativeModeTab> tabFilter) {
            this.tabFilter = tabFilter;
        }

        @Override
        public void accept(CreativeModeTab.ItemDisplayParameters itemDisplayParameters, CreativeModeTab.Output output) {
            Map<ItemProviderEntry<?>, Function<Item, ItemStack>> simpleFactories = new HashMap<>();
            for (ItemEntry<BacktankItem> itemEntry : TrimmedItems.BACKTANK_ITEMS) {
                simpleFactories.put(itemEntry, item -> {
                    ItemStack stack = new ItemStack(item);
                    stack.getOrCreateTag().putInt("Air", BacktankUtil.maxAirWithoutEnchants());
                    return stack;
                });
            }
            simpleFactories.put(TrimmedItems.RAINBOW_BACKTANK_ITEM, item -> {
                ItemStack stack = new ItemStack(item);
                stack.getOrCreateTag().putInt("Air", BacktankUtil.maxAirWithoutEnchants());
                return stack;
            });
            simpleFactories.put(TrimmedItems.TRANS_BACKTANK_ITEM, item -> {
                ItemStack stack = new ItemStack(item);
                stack.getOrCreateTag().putInt("Air", BacktankUtil.maxAirWithoutEnchants());
                return stack;
            });

            Map<Item, Function<Item, ItemStack>> factories = new Reference2ReferenceOpenHashMap<>();

            simpleFactories.forEach((entry, factory) -> {
                factories.put(entry.asItem(), factory);
            });

            Function<Item, ItemStack> stackFunc = item -> {
                Function<Item, ItemStack> factory = factories.get(item);
                if (factory != null) {
                    return factory.apply(item);
                }
                return new ItemStack(item);
            };

            List<Item> exclusions = new ReferenceArrayList<>();
            for (ItemEntry<?> item : TrimmedItems.BACKTANK_PLACEABLES.toArray()) {
                exclusions.add(item.asItem());
            }
            Predicate<Item> predicateExclusions = exclusions::contains;

            List<Item> items = new LinkedList<>();

            items.addAll(collectItems(predicateExclusions));

            for (Item item : items) {
                output.accept(stackFunc.apply(item));
            }
        }

        private List<Item> collectItems(Predicate<Item> exclusionPredicate) {
            List<Item> items = new ReferenceArrayList<>();
            for (RegistryEntry<Item> entry : Trimmings.REGISTRATE.getAll(Registries.ITEM)) {
                if (!CreateRegistrate.isInCreativeTab(entry, tabFilter))
                    continue;
                Item item = entry.get();
                if (item instanceof BlockItem)
                    continue;
                if (!exclusionPredicate.test(item))
                    items.add(item);
            }
            return items;
        }
    }
}
