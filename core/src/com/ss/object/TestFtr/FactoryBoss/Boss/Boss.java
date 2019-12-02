package com.ss.object.TestFtr.FactoryBoss.Boss;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.ss.commons.Tweens;
import com.ss.core.action.exAction.GSimpleAction;
import com.ss.core.util.GLayerGroup;
import com.ss.core.util.GStage;
import com.ss.object.TestFtr.FactoryFolder.InsectFactory;
import com.ss.object.TestFtr.PoolInsects;
import com.ss.object.animation;

public class Boss {
  protected String info = "Boss";
  protected TextureAtlas atlas;
  protected GLayerGroup insectsGroup;
  protected GLayerGroup group;
  protected Group groupDie;
  protected animation ani;
  protected Image corpses;
  protected Image corpses2;
  protected float rotation = 0;
  protected float speed = 100;
  protected int id = -1;

  protected PoolInsects pool;

  protected int clickNumber = 10;
  protected int clicked = 0;

  public String getInf(){
    return info;
  }

  public void moveStart(float x, float y){
    group.setVisible(true);
    groupDie.setVisible(true);

    Vector2 pA = new Vector2(x, y);
    group.setPosition(x, y);
    int check = (int)Math.floor(Math.random()*2) == 0 ? 1 : -1;
    float operator = check > 0 ? -1 : 1;
    float rotationTemp = (float)(operator*Math.floor(Math.random()*89 + 1));
    Vector2 pB = InsectFactory.getPositionB(rotationTemp, pA);
    moving(pA, pB, rotationTemp);
  }

  private void moving(Vector2 pA, Vector2 pB, float rotation){
    float w = GStage.getWorldWidth(), wpad = w*0.1f;
    if(pA.y >= GStage.getWorldHeight()){
      //System.out.println("xong r");
      killInsect();
      reset();
      return;
    }
    float rotation_deg = rotation;
    float xA= pA.x, yA = pA.y;
    float xB= pB.x, yB = pB.y;
    float duaration = (float) Math.sqrt(Math.pow((xB-xA), 2) + Math.pow((yB-yA), 2))/speed;

    group.addAction(
      Actions.sequence(
        Actions.rotateTo(rotation_deg,0.01f),
        GSimpleAction.simpleAction((d, a)->{
          this.rotation = rotation;
          return true;
        }),
        Actions.moveTo(xB,yB,duaration),
        GSimpleAction.simpleAction((d, a)->{
          endAMoveStep(pB, rotation);
          return true;
        })
      )
    );
  }

  private void endAMoveStep(Vector2 pB, float rotation){
    float operator = rotation > 0 ? -1 : 1;
    float rotationTemp = (float)(operator*Math.floor(Math.random()*89 + 1));
    Vector2 pBTemp = InsectFactory.getPositionB(rotationTemp, pB);
    moving(pB, pBTemp, rotationTemp);
  }

  protected void killInsect(){
    group.setVisible(false);
    groupDie.setVisible(false);
  }

  protected void reset(){
    ani.setVisible(true);
    corpses.setVisible(false);
    group.setTouchable(Touchable.enabled);

    pool.addBossAvai(id, this);
    int index = pool.getIndexBWok(id, this);
    pool.removeBoWok(id, index);
    pool.getSizeB();
    if(pool.getTotalSizeWok() <= 2 && pool.getSizeBWok() <= 0){
      System.out.println("go here!!!");
      pool.nextLv();
    }
  }

  protected void initGroup(){
    group = new GLayerGroup();
    groupDie = new Group();
  }

  public void setSpeed(float speed){
    this.speed = speed;
  }

  @Override
  public String toString() {
    return this.getInf();
  }
}
