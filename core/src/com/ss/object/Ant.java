package com.ss.object;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.ss.GMain;
import com.ss.commons.Tweens;
import com.ss.core.action.exAction.GSimpleAction;
import com.ss.core.util.GAssetsManager;
import com.ss.core.util.GLayer;
import com.ss.core.util.GStage;
import com.ss.core.util.GUI;

public class Ant {
  public Group group = new Group();
  Group groupKienDie = new Group();
  animation ani;
  TextureAtlas atlas;
  String typeKien, typeKienDie;

  public Ant(String type){
    this.typeKien = type;
    initAtlas();
    GStage.addToLayer(GLayer.top,group);
    GStage.addToLayer(GLayer.ui,groupKienDie);
    group.setWidth(110);
    group.setHeight(112);
    group.setOrigin(Align.center);
    ani = new animation(group.getWidth()/2,group.getHeight()/2,type,1,4,0.1f,false,true);
    group.addActor(ani);
    setTypeKienDie();
    click();

  }
  void setTypeKienDie(){
    if(typeKien == "animationKien1"){
      typeKienDie = "KienDie01";
    }else if(typeKien == "animationKien2"){
      typeKienDie = "KienDie02";
    }else if(typeKien =="animationBo"){
      typeKienDie = "BoDie";
    }else if(typeKien == "animationOng"){
      typeKienDie = "OngDie";
    }
  }
  void click( ){

    group.addListener(new ClickListener(){
      @Override
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
      group.setTouchable(Touchable.disabled);
      ani.setVisible(false);
      Image kiendie = GUI.createImage(atlas,typeKienDie);
      kiendie.setPosition(group.getX()+group.getWidth()/2,group.getY()+group.getHeight()/2,Align.center);
      groupKienDie.addActor(kiendie);
      group.clearActions();
      groupKienDie.addAction(Actions.sequence(
        Actions.alpha(0,2f),
        GSimpleAction.simpleAction((d,a)->{
          group.clear();
          group.remove();
          return true;
        })
      ));
      //reset();
      return super.touchDown(event, x, y, pointer, button);
      }
    });
  }
  public void reset(){
    group.setTouchable(Touchable.enabled);
    group.setPosition(GStage.getWorldWidth()/2,0,Align.center);
    ani.setVisible(true);
  }

  public void moveStart(float x, float y){
    Vector2 pA = new Vector2(x, y);
    group.setPosition(x, y);
    int check = (int)Math.floor(Math.random()*2) == 0 ? 1 : -1;
    float operator = check > 0 ? -1 : 1;
    float rotationTemp = (float)(operator*Math.floor(Math.random()*89 + 1));
    Vector2 pB = getPositionB(rotationTemp, pA);
    moving(pA, pB, rotationTemp);
  }

  public void moving(Vector2 pA, Vector2 pB, float rotation){
    if(pA.y >= 1280){
      return;
    }
    float rotation_deg = rotation;
    float xA= pA.x, yA = pA.y;
    float xB= pB.x, yB = pB.y;
    float duaration = (float) Math.sqrt(Math.pow((xB-xA), 2) + Math.pow((yB-yA), 2))/100;

    group.addAction(
      Actions.sequence(
        Actions.rotateTo(rotation_deg,0.01f),
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
    Vector2 pBTemp = getPositionB(rotationTemp, pB);
    moving(pB, pBTemp, rotationTemp);
  }

  private Vector2 getPositionB(float rotation, Vector2 pA){
    Vector2 pB = new Vector2(0,0);
    float padding = 200;
    float rotationTemp = rotation - (int)(rotation/360)*360;
    if(rotationTemp > 180) rotationTemp -= 360;

    int operator = 1;

    if(rotationTemp > 0 && rotationTemp < 180){
      operator = -1;
    }
    else if(rotationTemp < 0 && rotationTemp > -180){
      operator = 1;
    }
    else {
      padding = 0;
    }
    float x = pA.x + operator*padding;
    float y = (float)-Math.tan(Math.toRadians(90-rotationTemp))*(x-pA.x) + pA.y;

    if(padding != 0)
      pB.set(x,y);
    else pB.set(pA.x, 1280);

    System.out.println("rotation: " + rotationTemp);
    System.out.println("xB-yB: " + pB.x + "-" + pB.y);
    return pB;
  }
  void initAtlas(){
    atlas = GAssetsManager.getTextureAtlas("atlasGame.atlas");
  }

}