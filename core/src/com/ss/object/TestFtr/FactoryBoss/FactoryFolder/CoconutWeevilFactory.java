package com.ss.object.TestFtr.FactoryBoss.FactoryFolder;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.ss.core.util.GLayerGroup;
import com.ss.object.TestFtr.FactoryBoss.Boss.Boss;
import com.ss.object.TestFtr.FactoryBoss.Boss.CoconutWeevil;
import com.ss.object.TestFtr.FactoryBoss.Interfaces.BossAbtractFactory;
import com.ss.object.TestFtr.PoolInsects;

public class CoconutWeevilFactory implements BossAbtractFactory {
  private TextureAtlas atlas;
  private GLayerGroup insectsGroup;
  private PoolInsects pool;

  public CoconutWeevilFactory(TextureAtlas atlas, GLayerGroup insectsGroup, PoolInsects pool){
    this.atlas = atlas;
    this.insectsGroup = insectsGroup;
    this.pool = pool;
  }
  @Override
  public Boss createBoss() {
    return new CoconutWeevil(atlas, insectsGroup, pool);
  }
}
