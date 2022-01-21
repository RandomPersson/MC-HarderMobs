package pl.org.mensa.rp.mc.HarderMobs;

import java.util.Random;

import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.PigZombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

public class CreatureSpawnListener implements Listener {
	Random random = new Random();
	
	@EventHandler
	public void onCreatureSpawn(CreatureSpawnEvent e) {
		switch (e.getEntityType()) {
			case ZOMBIE:
			case ZOMBIE_VILLAGER:
			case HUSK: {
				buff(e.getEntity(), 2, 1.2, 0);
			} break;
//			case DROWNED: break;
			case SKELETON: {
				buff(e.getEntity(), 1.1, 1.1, 0);
			} break;
//			case STRAY: break;
			case SPIDER: {
				buff(e.getEntity(), 1.1, 1.1, 0);
			} break;
//			case CAVE_SPIDER: break;
			case CREEPER: {
				buff(e.getEntity(), 1, 1.2, 0);
			} break;
			case WITCH: {
				buff(e.getEntity(), 1, 1.1, 0);
			} break;
//			case ILLUSIONER: break;
//			case EVOKER: break;
//			case VEX: break;
//			case EVOKER_FANGS: break;
//			case PILLAGER: break;
//			case VINDICATOR: break;
//			case RAVAGER: break;
//			case PHANTOM: break;
//			case BLAZE: break;
//			case SHULKER: break;
//			case GUARDIAN: break;
//			case ELDER_GUARDIAN: break;
//			case SILVERFISH: break;
//			case ENDERMITE: break;
//			case SLIME: break;
//			case MAGMA_CUBE: break;
//			case GHAST: break;
//			case WITHER_SKELETON: break;
			case PIG_ZOMBIE:{
				angry((PigZombie)e.getEntity());
			} break;
//			case ENDER_DRAGON: break;
//			case WITHER: break;
			default: {}
		}
	}
	
	private void buff(LivingEntity enemy, double damage_mul, double speed_mul, double super_buff_chance) {
		if (damage_mul != 1) {
			AttributeInstance attr = enemy.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE);
			attr.setBaseValue(attr.getBaseValue()*damage_mul);
		}
		if (speed_mul != 1) {
			AttributeInstance attr = enemy.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED);
			attr.setBaseValue(attr.getBaseValue()*speed_mul);
		}
		
		while (super_buff_chance > 0) {
			if (random.nextDouble() < super_buff_chance--) superBuff(enemy, damage_mul, speed_mul);
		}
	}
	private void superBuff(LivingEntity enemy, double damage_mul, double speed_mul) {
		buff(enemy, damage_mul, speed_mul, 0);
		
		AttributeInstance attr = enemy.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE);
		attr.setBaseValue(attr.getBaseValue() > 0 ? attr.getBaseValue()*(2-attr.getBaseValue()) : 0.2D);
		
		if (enemy.getType() == EntityType.ZOMBIE)
		attr = enemy.getAttribute(Attribute.ZOMBIE_SPAWN_REINFORCEMENTS);
		attr.setBaseValue(1.0D);
	}
	private void angry(PigZombie enemy) {
		enemy.setAnger(630_720_000);
	}
}
