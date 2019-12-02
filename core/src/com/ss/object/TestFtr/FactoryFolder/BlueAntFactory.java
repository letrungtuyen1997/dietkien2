package com.ss.object.TestFtr.FactoryFolder;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.ss.core.util.GLayerGroup;
import com.ss.object.TestFtr.BlueAnt;
import com.ss.object.TestFtr.Insect;
import com.ss.object.TestFtr.Interfaces.InsectAbtractFactory;
import com.ss.object.TestFtr.PoolInsects;

public class BlueAntFactory implements InsectAbtractFactory {
  private TextureAtlas atlas;
  private GLayerGroup insectsGroup;
  private PoolInsects pool;
  public BlueAntFactory(TextureAtlas atlas, GLayerGroup insectsGroup, PoolInsects pool){
    this.atlas = atlas;
    this.insectsGroup = insectsGroup;
    this.pool = pool;
  }

  @Override
  public Insect createInsect() {
    return new BlueAnt(atlas, insectsGroup, pool);
  }
}
