package com.ss.object.TestFtr;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;
import com.ss.core.action.exAction.GSimpleAction;
import com.ss.core.util.GLayerGroup;
import com.ss.core.util.GStage;
import com.ss.object.TestFtr.FactoryFolder.InsectFactory;
import com.ss.object.TestFtr.StaticObjects.Config;
import com.ss.object.animation;

public class Insect {
  protected String info = "insects";
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
  protected int lv = Config.level;
  //protected boolean isAvai = true;

  protected  PoolInsects pool;
  public Image frozen;
  public Image bomb;

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
      pool.removeInsWok(this, id);
      //isAvai = true;
      killInsect();
      reset();
      return;
    }
    float rotation_deg = rotation;
    float xA = pA.x, yA = pA.y;
    float xB = pB.x, yB = pB.y;
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

  protected void initGroup(){
    group = new GLayerGroup();
    groupDie = new Group();
    group.debug();
    groupDie.debug();
  }

  protected void killInsect(){
    group.setVisible(false);
    groupDie.setVisible(false);
  }

  protected void reset(){
    ani.setVisible(true);
    corpses.setVisible(false);
    frozen.setVisible(false);
    group.setTouchable(Touchable.enabled);
    float x = (float) Math.floor(Math.random()*500 + 100);
    pool.addInsAvai(id, this);
    //isAvai = true;
    pool.getSize();
    if(((pool.getTotalSizeWok() <= 0 && Config.level %5 != 0) || (Config.level %5 == 0 && pool.getTotalSizeWok() <= 0)) && pool.getSizeBWok() <= 0){
      if(this.lv == Config.level){
        System.out.println("alooooooooooooooooooooooooooooo");
        pool.nextLv();
      }
    }
  }

  public void setPause(boolean isP){
    frozen.setVisible(true);
    //frozen.setPosition(group.getX()+ group.getWidth()/2,group.getY()+ group.getHeight()/2, Align.center);
    frozen.setOrigin(Align.center);
    frozen.setRotation(rotation);
    group.setPause(isP);
    ani.setPause(isP);
  }

  public void click(int index){

  }

  public void setSpeed(float speed){
    this.speed = speed;
  }

  @Override
  public String toString() {
    return this.getInf();
  }

  public void setLv(int lv){
    this.lv = lv;
  }

  public void killInsectByBomb(){
    ani.setVisible(false);
    bomb.setVisible(false);
  }

}
