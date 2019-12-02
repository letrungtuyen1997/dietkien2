package com.ss.scene;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Array;
import com.ss.commons.Tweens;
import com.ss.core.util.GAssetsManager;
import com.ss.core.util.GLayer;
import com.ss.core.util.GLayerGroup;
import com.ss.core.util.GScreen;
import com.ss.core.util.GStage;
import com.ss.core.util.GUI;
import com.ss.object.TestFtr.Level.LevelManager;
import com.ss.object.TestFtr.PoolInsects;
import com.ss.object.TestFtr.StaticObjects.Config;

public class gameScenes extends GScreen {
  private TextureAtlas atlas;
  private Group uiGroup;
  private GLayerGroup insectsGroup;
  private LevelManager managerLv;

  @Override
  public void dispose() {

  }

  @Override
  public void init() {
    initGroup();
    initAtlast();
    managerLv = new LevelManager(atlas, insectsGroup);
    Image bg = GUI.createImage(atlas,"bg1");
    bg.setWidth(GStage.getWorldWidth());
    bg.setHeight(GStage.getWorldHeight());
    uiGroup.addActor(bg);
    managerLv.begin();

  }

  private void initGroup(){
    uiGroup = new Group();
    insectsGroup = new GLayerGroup();

    GStage.addToLayer(GLayer.ui, uiGroup);
    GStage.addToLayer(GLayer.top, insectsGroup);
  }

  @Override
  public void run() {

  }

  void initAtlast(){
    atlas = GAssetsManager.getTextureAtlas("atlasGame.atlas");
  }
}
