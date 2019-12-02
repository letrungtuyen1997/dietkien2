package com.ss.object.TestFtr.FactoryFolder;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.ss.core.util.GLayerGroup;
import com.ss.object.TestFtr.Bee;
import com.ss.object.TestFtr.Insect;
import com.ss.object.TestFtr.Interfaces.InsectAbtractFactory;
import com.ss.object.TestFtr.PoolInsects;

public class BeeFactory implements InsectAbtractFactory {
  private TextureAtlas atlas;
  private GLayerGroup insectsGroup;
  private PoolInsects pool;

  public BeeFactory(TextureAtlas atlas, GLayerGroup insectsGroup, PoolInsects pool){
    this.atlas = atlas;
    this.insectsGroup = insectsGroup;
    this.pool = pool;
  }

  @Override
  public Insect createInsect() {
    return new Bee(atlas, insectsGroup, pool);
  }
}
