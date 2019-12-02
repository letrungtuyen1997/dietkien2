package com.ss.object.TestFtr;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.ss.commons.Tweens;
import com.ss.core.action.exAction.GSimpleAction;
import com.ss.core.util.GLayerGroup;
import com.ss.core.util.GUI;
import com.ss.object.animation;

public class RedAnt extends Insect {
  public RedAnt(TextureAtlas atlas, GLayerGroup insectsGroup, PoolInsects pool){
    info = "Red Ant";
    this.atlas = atlas;
    this.insectsGroup = insectsGroup;
    this.pool = pool;
    id = pool.ID_REDANT;
    initGroup();
    initAni();
    initCorpses();
    initFrozen();
    initBomb();
    addClick();
  }

  @Override
  protected void initGroup() {
    super.initGroup();
    group.setSize(130, 180);
    group.setOrigin(Align.center);
    group.setVisible(false);
    groupDie.setVisible(false);

    insectsGroup.addActor(group);
    insectsGroup.addActor(groupDie);
  }

  private void initAni(){
    ani = new animation(group.getWidth()/2,group.getHeight()/2,"animationKien1",1,4,0.1f,false,true);
    group.addActor(ani);
  }

  private void initCorpses(){
    corpses = GUI.createImage(atlas, "KienDie01");
    groupDie.addActor(corpses);
    corpses.setVisible(false);
  }

  private void initFrozen(){
    frozen = GUI.createImage(atlas, "frozen");
    group.addActor(frozen);
    frozen.setVisible(false);
  }

  private void initBomb(){
    bomb = GUI.createImage(atlas, "bomb");
    group.addActor(bomb);
    bomb.setVisible(false);
  }

  private void addClick(){
    group.addListener(new ClickListener(){
      @Override
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
      group.setTouchable(Touchable.disabled);
      click(-1);
      pool.checkOverlapInsect(new Vector2(group.getX(), group.getY()), RedAnt.this);
      return super.touchDown(event, x, y, pointer, button);
      }
    });
  }

  @Override
  public void click(int index) {
    super.click(index);
//    if(index != -1){
//      pool.removeInsWok(id, index);
////      int indexTemp = pool.getIndexWok(id, Bee.this);
////      System.out.println("id: " + id + " index: " + indexTemp);
////      if(index == -1){
////        System.out.println("Exception!!!1111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111");
////        //return;
////      }
////      else {
//      //}
//    }
//    else {
//
//    }

    //isAvai = true;

    int indexTemp = pool.getIndexWok(id, RedAnt.this);
    pool.removeInsWok(this, id);
    groupDie.setZIndex(1);
    ani.setVisible(false);
    frozen.setVisible(false);
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

  @Override
  public void moveStart(float x, float y) {
    pool.addInsWok(id, this);
    super.moveStart(x, y);
  }

  @Override
  public String getInf() {
    return info;
  }
}
