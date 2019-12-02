package com.ss.object.TestFtr.FactoryBoss.Boss;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.ss.commons.Tweens;
import com.ss.core.action.exAction.GSimpleAction;
import com.ss.core.util.GLayerGroup;
import com.ss.core.util.GUI;
import com.ss.object.TestFtr.PoolInsects;
import com.ss.object.animation;

public class BoXit1 extends Boss {
  public BoXit1(TextureAtlas atlas, GLayerGroup insectsGroup, PoolInsects pool){
    info = "BoXit1";
    this.id = pool.ID_B_BOXIT1;
    this.atlas = atlas;
    this.insectsGroup = insectsGroup;
    this.pool = pool;

    initGroup();
    initAni();
    initCorpses();
    addClick();
  }

  @Override
  protected void initGroup() {
    super.initGroup();
    group.setSize(130, 150);
    group.setOrigin(Align.center);
    group.setVisible(false);
    groupDie.setVisible(false);

    insectsGroup.addActor(group);
    insectsGroup.addActor(groupDie);
  }

  private void initAni(){
    ani = new animation(group.getWidth()/2,group.getHeight()/2,"animationBoxit1",1,4,0.1f,false,true);
    group.addActor(ani);
  }

  private void initCorpses(){
    corpses = GUI.createImage(atlas, "BoXitDie1");
    groupDie.addActor(corpses);
    corpses.setVisible(false);
  }

  private void addClick(){
    group.addListener(new ClickListener(){
      @Override
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
      clicked++;
      System.out.println("clicked: " + clicked);
      if(clicked >= clickNumber){
        group.setTouchable(Touchable.disabled);
        groupDie.setZIndex(1);
        ani.setVisible(false);
        corpses.setVisible(true);
        corpses.setPosition(group.getX()+ group.getWidth()/2,group.getY()+ group.getHeight()/2,Align.center);
        corpses.setOrigin(Align.center);
        corpses.setRotation(rotation);
        group.clearActions();
        groupDie.addAction(Actions.sequence(
          Actions.delay(2f),
          GSimpleAction.simpleAction((d, a)->{
            killInsect();
            Tweens.setTimeout(group, 0.5f, ()->{
              reset();
            });
            return true;
          })
        ));
      }
      return super.touchDown(event, x, y, pointer, button);
      }
    });
  }

  @Override
  public void moveStart(float x, float y) {
    super.moveStart(x, y);
    pool.addBossWok(id, this);
  }
}
