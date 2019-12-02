package com.effect;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEmitter;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;

public class effectWin extends Actor {

  private static FileHandle light = Gdx.files.internal("particle/win");


  public ParticleEffect effect;
  private Actor parent = this.parent;
  private Group stage;

  public effectWin(int id, float f, float f2) {
    this.effect = new ParticleEffect();
    setX(f);
    setY(f2);
    if(id==0) {
      this.effect.load(light, Gdx.files.internal("particle"));
      this.effect.scaleEffect(1.5f);
      for (int i = 0; i < this.effect.getEmitters().size; i++) {
        ((ParticleEmitter) this.effect.getEmitters().get(i)).flipY();
      }
    }
    this.effect.setPosition(f, f2);
  }

  public void act(float f) {
    super.act(f);
    this.effect.setPosition(getX(), getY());
    this.effect.update(f);
  }

  public void draw(Batch batch, float f) {
    super.draw(batch, f);
    if (!this.effect.isComplete()) {
      this.effect.draw(batch);
      return;
    }
    this.effect.dispose();
  }

  public void start() {
    this.effect.start();
  }
}