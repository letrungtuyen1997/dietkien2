package com.ss.object.TestFtr.FactoryBoss.FactoryFolder;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.ss.core.util.GLayerGroup;
import com.ss.object.TestFtr.FactoryBoss.Boss.Boss;
import com.ss.object.TestFtr.FactoryBoss.Boss.HornedBeetle2;
import com.ss.object.TestFtr.FactoryBoss.Interfaces.BossAbtractFactory;
import com.ss.object.TestFtr.PoolInsects;

public class HornedBeetle2Factory implements BossAbtractFactory {
  private TextureAtlas atlas;
  private GLayerGroup insectsGroup;
  private PoolInsects pool;

  public HornedBeetle2Factory(TextureAtlas atlas, GLayerGroup insectsGroup, PoolInsects pool){
    this.atlas = atlas;
    this.insectsGroup = insectsGroup;
    this.pool = pool;
  }
  @Override
  public Boss createBoss() {
    return new HornedBeetle2(atlas, insectsGroup, pool);
  }
}
