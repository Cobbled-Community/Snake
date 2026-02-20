package net.puffish.snakemod.game.entity;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import org.jspecify.annotations.NonNull;

public class SnakeFoodEntity extends Slime {
	public static final double RADIUS = 0.5;

	protected SnakeFoodEntity(Level world) {
		super(EntityType.SLIME, world);
	}

	public static SnakeFoodEntity create(Level world){
		var entity = new SnakeFoodEntity(world);
		entity.init();
		return entity;
	}

	protected void init(){
		this.goalSelector.getAvailableGoals().clear();
		this.setInvulnerable(true);
		this.setNoAi(true);
		this.setPersistenceRequired();
	}

	@Override
	public void tick() {

	}

	public Vec3 getCenter() {
		return position().add(0, getBbHeight(), 0);
	}

	@Override
	public boolean isPushable() {
		return false;
	}

	@Override
	protected boolean shouldDropLoot(@NonNull ServerLevel world) {
		return false;
	}

	@Override
	protected void dropExperience(@NonNull ServerLevel world, @Nullable Entity attacker) {
	}
}
