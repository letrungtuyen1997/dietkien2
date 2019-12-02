package com.ss.object.TestFtr.Level;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Json;
import com.ss.commons.Tweens;
import com.ss.core.util.GLayerGroup;
import com.ss.core.util.GUI;
import com.ss.object.TestFtr.PoolInsects;
import com.ss.object.TestFtr.StaticObjects.Config;

public class LevelManager {
  private TextureAtlas atlas;
  private GLayerGroup insectsGroup;
  private PoolInsects pool;
  Image frozen;
  Image bomb;

  public LevelManager(TextureAtlas atlas, GLayerGroup insectsGroup){
    this.atlas = atlas;
    this.insectsGroup = insectsGroup;
    pool = new PoolInsects(this.atlas, this.insectsGroup, this);
    initTool();
  }

  private void initTool(){
    initFrozenBtn();
    initBomb();
  }

  private void initBomb(){
    bomb = GUI.createImage(atlas, "bomb");
    insectsGroup.addActor(bomb);
    bomb.setPosition(200, 400);
    bomb.addListener(new ClickListener(){
      @Override
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        pool.bombEffect();
        return super.touchDown(event, x, y, pointer, button);
      }
    });
  }

  private void initFrozenBtn(){
    frozen = GUI.createImage(atlas, "frozen");
    insectsGroup.addActor(frozen);
    frozen.setPosition(200, 200);
    frozen.addListener(new ClickListener(){
      @Override
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        pool.frozenInsects(5f);
        return super.touchDown(event, x, y, pointer, button);
      }
    });
  }

  public void begin(){
    startLv(Config.level);
  }

  public void startLv(int level){
    Config.level++;
    Level lv = makeLv(level);
    pool.getInsect(PoolInsects.ID_BEE, lv.cBee, lv.spd);
    pool.getInsect(PoolInsects.ID_BLUEANT, lv.cBlueAnt, lv.spd);
    pool.getInsect(PoolInsects.ID_REDANT, lv.cRedAnt, lv.spd);
    pool.getInsect(PoolInsects.ID_LADYBUG, lv.cLadyBug, lv.spd);
    //Boss
    pool.getBoss(PoolInsects.ID_B_BOXIT1, lv.cBoXit1, lv.spd);
    pool.getBoss(PoolInsects.ID_B_BOXIT2, lv.cBoXit2, lv.spd);
    pool.getBoss(PoolInsects.ID_B_COCONUTWEEVIL, lv.cCoWe, lv.spd);
    pool.getBoss(PoolInsects.ID_B_HORNEDBEETLE1, lv.cHoBe1, lv.spd);
    pool.getBoss(PoolInsects.ID_B_HORNEDBEETLE2, lv.cHoBe2, lv.spd);
  }

  private Level makeLv(int level){
    Level lv = new Level();
    Json js = new Json();
    switch (level) {
      case 1:{
        lv = js.fromJson(Level.class, "{spd: 100, cBee: 3, cBlueAnt: 2, cRedAnt: 0, cLadyBug: 0," +
                " cBoXit1: 0, cBoXit2: 0, cCoWe: 0, cHoBe1: 0, cHoBe2: 0}");
        break;
      }
      case 2:{
        lv = js.fromJson(Level.class, "{spd: 110, cBee: 5, cBlueAnt: 3, cRedAnt: 0, cLadyBug: 0," +
                " cBoXit1: 0, cBoXit2: 0, cCoWe: 0, cHoBe1: 0, cHoBe2: 0}");
        break;
      }
      case 3:{
        lv = js.fromJson(Level.class, "{spd: 120, cBee: 5, cBlueAnt: 3, cRedAnt: 3, cLadyBug: 0," +
                " cBoXit1: 0, cBoXit2: 0, cCoWe: 0, cHoBe1: 0, cHoBe2: 0}");
        break;
      }
      case 4:{
        lv = js.fromJson(Level.class, "{spd: 130, cBee: 5, cBlueAnt: 3, cRedAnt: 3, cLadyBug: 1," +
                " cBoXit1: 0, cBoXit2: 0, cCoWe: 0, cHoBe1: 0, cHoBe2: 0}");
        break;
      }
      case 5:{
        lv = js.fromJson(Level.class, "{spd: 140, cBee: 0, cBlueAnt: 0, cRedAnt: 0, cLadyBug: 0," +
                " cBoXit1: 1, cBoXit2: 0, cCoWe: 0, cHoBe1: 0, cHoBe2: 0}");
        break;
      }
      case 6: {
        lv = js.fromJson(Level.class, "{spd: 140, cBee: 5, cBlueAnt: 3, cRedAnt: 3, cLadyBug: 3," +
                " cBoXit1: 0, cBoXit2: 0, cCoWe: 0, cHoBe1: 0, cHoBe2: 0}");
        break;
      }
      case 7:{
        lv = js.fromJson(Level.class, "{spd: 150, cBee: 5, cBlueAnt: 3, cRedAnt: 5, cLadyBug: 3," +
                " cBoXit1: 0, cBoXit2: 0, cCoWe: 0, cHoBe1: 0, cHoBe2: 0}");
        break;
      }
      case 8:{
        lv = js.fromJson(Level.class, "{spd: 160, cBee: 5, cBlueAnt: 5, cRedAnt: 7, cLadyBug: 3," +
                " cBoXit1: 0, cBoXit2: 0, cCoWe: 0, cHoBe1: 0, cHoBe2: 0}");
        break;
      }
      case 9:{
        lv = js.fromJson(Level.class, "{spd: 170, cBee: 5, cBlueAnt: 5, cRedAnt: 7, cLadyBug: 4," +
                " cBoXit1: 0, cBoXit2: 0, cCoWe: 0, cHoBe1: 0, cHoBe2: 0}");
        break;
      }
      case 10:{
        lv = js.fromJson(Level.class, "{spd: 180, cBee: 0, cBlueAnt: 0, cRedAnt: 0, cLadyBug: 0," +
                " cBoXit1: 0, cBoXit2: 0, cCoWe: 0, cHoBe1: 0, cHoBe2: 1}");
        break;
      }
      case 11: {
        lv = js.fromJson(Level.class, "{spd: 190, cBee: 5, cBlueAnt: 5, cRedAnt: 7, cLadyBug: 4," +
                " cBoXit1: 0, cBoXit2: 0, cCoWe: 0, cHoBe1: 0, cHoBe2: 0}");
        break;
      }
      case 12:{
        lv = js.fromJson(Level.class, "{spd: 200, cBee: 5, cBlueAnt: 5, cRedAnt: 7, cLadyBug: 4," +
                " cBoXit1: 0, cBoXit2: 0, cCoWe: 0, cHoBe1: 0, cHoBe2: 0}");
        break;
      }
      case 13:{
        lv = js.fromJson(Level.class, "{spd: 210, cBee: 5, cBlueAnt: 5, cRedAnt: 7, cLadyBug: 4," +
                " cBoXit1: 0, cBoXit2: 0, cCoWe: 0, cHoBe1: 0, cHoBe2: 0}");
        break;
      }
      case 14:{
        lv = js.fromJson(Level.class, "{spd: 220, cBee: 6, cBlueAnt: 6, cRedAnt: 7, cLadyBug: 4," +
                " cBoXit1: 0, cBoXit2: 0, cCoWe: 0, cHoBe1: 0, cHoBe2: 0}");
        break;
      }
      case 15:{
        lv = js.fromJson(Level.class, "{spd: 230, cBee: 0, cBlueAnt: 0, cRedAnt: 0, cLadyBug: 0," +
                " cBoXit1: 0, cBoXit2: 0, cCoWe: 1, cHoBe1: 0, cHoBe2: 0}");
        break;
      }
      case 16:{
        lv = js.fromJson(Level.class, "{spd: 240, cBee: 5, cBlueAnt: 5, cRedAnt: 7, cLadyBug: 4," +
                " cBoXit1: 0, cBoXit2: 0, cCoWe: 0, cHoBe1: 0, cHoBe2: 0}");
        break;
      }
      case 17:{
        lv = js.fromJson(Level.class, "{spd: 250, cBee: 5, cBlueAnt: 5, cRedAnt: 7, cLadyBug: 4," +
                " cBoXit1: 0, cBoXit2: 0, cCoWe: 0, cHoBe1: 0, cHoBe2: 0}");
        break;
      }
      case 18:{
        lv = js.fromJson(Level.class, "{spd: 260, cBee: 5, cBlueAnt: 5, cRedAnt: 7, cLadyBug: 4," +
                " cBoXit1: 0, cBoXit2: 0, cCoWe: 0, cHoBe1: 0, cHoBe2: 0}");
        break;
      }
      case 19:{
        lv = js.fromJson(Level.class, "{spd: 270, cBee: 5, cBlueAnt: 5, cRedAnt: 7, cLadyBug: 4," +
                " cBoXit1: 0, cBoXit2: 0, cCoWe: 0, cHoBe1: 0, cHoBe2: 0}");
        break;
      }
      case 20:{
        lv = js.fromJson(Level.class, "{spd: 280, cBee: 0, cBlueAnt: 0, cRedAnt: 0, cLadyBug: 0," +
                " cBoXit1: 0, cBoXit2: 0, cCoWe: 0, cHoBe1: 1, cHoBe2: 0}");
        break;
      }
      case 21:{
        lv = js.fromJson(Level.class, "{spd: 290, cBee: 7, cBlueAnt: 5, cRedAnt: 7, cLadyBug: 4," +
                " cBoXit1: 0, cBoXit2: 0, cCoWe: 0, cHoBe1: 0, cHoBe2: 0}");
        break;
      }
      case 22:{
        lv = js.fromJson(Level.class, "{spd: 290, cBee: 7, cBlueAnt: 6, cRedAnt: 7, cLadyBug: 4," +
                " cBoXit1: 0, cBoXit2: 0, cCoWe: 0, cHoBe1: 0, cHoBe2: 0}");
        break;
      }
      case 23:{
        lv = js.fromJson(Level.class, "{spd: 300, cBee: 7, cBlueAnt: 6, cRedAnt: 7, cLadyBug: 5," +
                " cBoXit1: 0, cBoXit2: 0, cCoWe: 0, cHoBe1: 0, cHoBe2: 0}");
        break;
      }
      case 24:{
        lv = js.fromJson(Level.class, "{spd: 310, cBee: 7, cBlueAnt: 7, cRedAnt: 7, cLadyBug: 4," +
                " cBoXit1: 0, cBoXit2: 0, cCoWe: 0, cHoBe1: 0, cHoBe2: 0}");
        break;
      }
      case 25:{
        lv = js.fromJson(Level.class, "{spd: 320, cBee: 0, cBlueAnt: 0, cRedAnt: 0, cLadyBug: 0," +
                " cBoXit1: 0, cBoXit2: 0, cCoWe: 0, cHoBe1: 0, cHoBe2: 1}");
        break;
      }
      case 26:{
        lv = js.fromJson(Level.class, "{spd: 330, cBee: 7, cBlueAnt: 7, cRedAnt: 8, cLadyBug: 4," +
                " cBoXit1: 0, cBoXit2: 0, cCoWe: 0, cHoBe1: 0, cHoBe2: 0}");
        break;
      }
      case 27:{
        lv = js.fromJson(Level.class, "{spd: 340, cBee: 7, cBlueAnt: 7, cRedAnt: 8, cLadyBug: 5," +
                " cBoXit1: 0, cBoXit2: 0, cCoWe: 0, cHoBe1: 0, cHoBe2: 0}");
        break;
      }
      case 28:{
        lv = js.fromJson(Level.class, "{spd: 350, cBee: 8, cBlueAnt: 7, cRedAnt: 8, cLadyBug: 5," +
                " cBoXit1: 0, cBoXit2: 0, cCoWe: 0, cHoBe1: 0, cHoBe2: 0}");
        break;
      }
      case 29:{
        lv = js.fromJson(Level.class, "{spd: 360, cBee: 8, cBlueAnt: 8, cRedAnt: 8, cLadyBug: 5," +
                " cBoXit1: 0, cBoXit2: 0, cCoWe: 0, cHoBe1: 0, cHoBe2: 0}");
        break;
      }
      case 30:{
        lv = js.fromJson(Level.class, "{spd: 370, cBee: 0, cBlueAnt: 0, cRedAnt: 0, cLadyBug: 0," +
                " cBoXit1: 1, cBoXit2: 0, cCoWe: 0, cHoBe1: 0, cHoBe2: 0}");
        break;
      }
      default:{
        lv = js.fromJson(Level.class, "{spd: 380, cBee: 5, cBlueAnt: 5, cRedAnt: 7, cLadyBug: 5," +
                " cBoXit1: 0, cBoXit2: 0, cCoWe: 0, cHoBe1: 0, cHoBe2: 0}");
        break;
      }
    }
    return lv;
  }
}
