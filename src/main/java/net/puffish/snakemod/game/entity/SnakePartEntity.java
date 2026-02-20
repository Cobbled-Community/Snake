package net.puffish.snakemod.game.entity;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.animal.sheep.Sheep;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import org.jspecify.annotations.NonNull;

public class SnakePartEntity extends Sheep {
	public static final double RADIUS = 0.55;

	protected SnakePartEntity(Level world) {
		super(EntityType.SHEEP, world);
	}

	public static SnakePartEntity create(Level world){
		var entity = new SnakePartEntity(world);
		entity.init();
		return entity;
	}

	protected void init(){
		this.goalSelector.getAvailableGoals().clear();
		this.setInvulnerable(true);
		this.setPersistenceRequired();
	}

	public void updateSimpleMovement(){
		this.move(MoverType.SELF, this.getDeltaMovement());

		Vec3 vel = this.getDeltaMovement();

		double velY = vel.y;
		if (this.horizontalCollision && this.onClimbable()) {
			velY = 0.2;
		}
		velY -= 0.16;

		this.setDeltaMovement(vel.x, velY, vel.z);

		this.needsSync = true;
	}

	public Vec3 getCenter() {
		return position().add(0, getBbHeight() / 2.0, 0);
	}

	@Override
	public void tick() {
		this.baseTick();
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return null;
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
