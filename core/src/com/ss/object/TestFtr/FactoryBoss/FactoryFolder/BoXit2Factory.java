package com.ss.object.TestFtr.FactoryBoss.FactoryFolder;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.ss.core.util.GLayerGroup;
import com.ss.object.TestFtr.FactoryBoss.Boss.BoXit2;
import com.ss.object.TestFtr.FactoryBoss.Boss.Boss;
import com.ss.object.TestFtr.FactoryBoss.Interfaces.BossAbtractFactory;
import com.ss.object.TestFtr.PoolInsects;

public class BoXit2Factory implements BossAbtractFactory{
  private TextureAtlas atlas;
  private GLayerGroup insectsGroup;
  private PoolInsects pool;

  public BoXit2Factory(TextureAtlas atlas, GLayerGroup insectsGroup, PoolInsects pool){
    this.atlas = atlas;
    this.insectsGroup = insectsGroup;
    this.pool = pool;
  }
  @Override
  public Boss createBoss() {
    return new BoXit2(atlas, insectsGroup, pool);
  }
}
