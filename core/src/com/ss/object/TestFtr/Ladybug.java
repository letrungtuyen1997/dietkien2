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

public class Ladybug extends Insect {
  public Ladybug(TextureAtlas atlas, GLayerGroup insectsGroup, PoolInsects pool){
    info = "Ladybug";
    this.atlas = atlas;
    this.insectsGroup = insectsGroup;
    this.pool = pool;
    id = pool.ID_LADYBUG;
    initGroup();
    initAni();
    initCorpses();
    initFrozen();
    initBomb();
    addClick();
    addClickCorpses();
  }

  @Override
  protected void initGroup() {
    super.initGroup();
    group.setSize(130, 190);
    group.setOrigin(Align.center);
    group.setVisible(false);
    groupDie.setVisible(false);

    insectsGroup.addActor(group);
    insectsGroup.addActor(groupDie);
  }

  private void initAni(){
    ani = new animation(group.getWidth()/2,group.getHeight()/2,"animationBo",1,4,0.1f,false,true);
    group.addActor(ani);
  }

  private void initCorpses(){
    corpses = GUI.createImage(atlas, "BoDie");
    corpses2 = GUI.createImage(atlas, "BoDie2");
    groupDie.addActor(corpses);
    groupDie.addActor(corpses2);
    corpses.setVisible(false);
    corpses2.setVisible(false);
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

  private void addClickCorpses(){
    corpses.addListener(new ClickListener(){
      @Override
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        corpses.setTouchable(Touchable.disabled);
        corpses.setVisible(false);
        corpses2.setVisible(true);
        corpses2.setPosition(group.getX()+ group.getWidth()/2,group.getY()+ group.getHeight()/2,Align.center);
        corpses2.setOrigin(Align.center);
        corpses2.setRotation(rotation);
        return super.touchDown(event, x, y, pointer, button);
      }
    });
  }

  private void addClick(){
    group.addListener(new ClickListener(){
      @Override
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
      group.setTouchable(Touchable.disabled);
      click(-1);
      pool.checkOverlapInsect(new Vector2(group.getX(), group.getY()), Ladybug.this);
      return super.touchDown(event, x, y, pointer, button);
      }
    });
  }

  @Override
  public void click(int index) {
    super.click(index);
    //isAvai = true;
    int indexTemp = pool.getIndexWok(id, Ladybug.this);
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
  protected void reset() {
    super.reset();
    corpses2.setVisible(false);
    corpses.setTouchable(Touchable.enabled);
  }

  @Override
  public String getInf() {
    return info;
  }
}
