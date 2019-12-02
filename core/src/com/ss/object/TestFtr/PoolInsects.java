package com.ss.object.TestFtr;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.ss.commons.Tweens;
import com.ss.core.util.GLayerGroup;
import com.ss.core.util.GStage;
import com.ss.object.TestFtr.FactoryBoss.Boss.Boss;
import com.ss.object.TestFtr.FactoryBoss.FactoryFolder.BoXit1Factory;
import com.ss.object.TestFtr.FactoryBoss.FactoryFolder.BoXit2Factory;
import com.ss.object.TestFtr.FactoryBoss.FactoryFolder.BossFactory;
import com.ss.object.TestFtr.FactoryBoss.FactoryFolder.CoconutWeevilFactory;
import com.ss.object.TestFtr.FactoryBoss.FactoryFolder.HornedBeetle1Factory;
import com.ss.object.TestFtr.FactoryBoss.FactoryFolder.HornedBeetle2Factory;
import com.ss.object.TestFtr.FactoryFolder.BeeFactory;
import com.ss.object.TestFtr.FactoryFolder.BlueAntFactory;
import com.ss.object.TestFtr.FactoryFolder.InsectFactory;
import com.ss.object.TestFtr.FactoryFolder.LadybugFactory;
import com.ss.object.TestFtr.FactoryFolder.RedAntFactory;
import com.ss.object.TestFtr.Level.LevelManager;
import com.ss.object.TestFtr.StaticObjects.Config;

public class PoolInsects {
  public static int ID_BEE = 0;
  public static int ID_BLUEANT = 1;
  public static int ID_REDANT = 2;
  public static int ID_LADYBUG = 3;

  public static int ID_B_BOXIT1 = 0;
  public static int ID_B_BOXIT2 = 1;
  public static int ID_B_COCONUTWEEVIL = 2;
  public static int ID_B_HORNEDBEETLE1 = 3;
  public static int ID_B_HORNEDBEETLE2 = 4;

  private Array<Array<Insect>> avaiIns;
  private Array<Array<Insect>> wokIns;
  private Array<Array<Boss>> avaiBo;
  private Array<Array<Boss>> wokBo;

  private TextureAtlas atlas;
  private LevelManager managerLevel;
  GLayerGroup insectsGroup;

  public PoolInsects(TextureAtlas atlas, GLayerGroup insectsGroup, LevelManager managerLevel){
    this.atlas = atlas;
    this.insectsGroup = insectsGroup;
    this.managerLevel = managerLevel;
    initPool();
  }

  public void initPool(){
    avaiIns = new Array<>();
    wokIns = new Array<>();
    avaiBo = new Array<>();
    wokBo = new Array<>();

    Array<Insect> a_b = new Array<>(); // todo: available_bee
    Array<Insect> a_ba = new Array<>();
    Array<Insect> a_ra = new Array<>();
    Array<Insect> a_lb = new Array<>();
    avaiIns.add(a_b, a_ba, a_ra, a_lb);
    initIns();

    Array<Insect> w_b = new Array<>(); //todo: working_bee
    Array<Insect> w_ba = new Array<>();
    Array<Insect> w_ra = new Array<>();
    Array<Insect> w_lb = new Array<>();
    wokIns.add(w_b, w_ba, w_ra, w_lb);

    Array<Boss> a_bx1 = new Array<>(); //todo: available_boxit1
    Array<Boss> a_bx2 = new Array<>();
    Array<Boss> a_coWe = new Array<>();
    Array<Boss> a_hoBe1 = new Array<>();
    Array<Boss> a_hoBe2 = new Array<>();
    avaiBo.add(a_bx1, a_bx2, a_coWe, a_hoBe1);
    avaiBo.add(a_hoBe2);
    initBoss();

    Array<Boss> w_bx1 = new Array<>();
    Array<Boss> w_bx2 = new Array<>();
    Array<Boss> w_coWe = new Array<>();
    Array<Boss> w_hoBe1 = new Array<>();
    Array<Boss> w_hoBe2 = new Array<>();
    wokBo.add(w_bx1, w_bx2, w_coWe, w_hoBe1);
    wokBo.add(w_hoBe2);
  }

  private void initIns(){
    for(int i = 0; i < 4; i++) {
      for(int j = 0; j < 50; j++) {
        switch (i){
          case 0: {
            Insect bee = InsectFactory.getInsect(new BeeFactory(atlas, insectsGroup, PoolInsects.this));
            avaiIns.get(0).add(bee);
            break;
          }
          case 1: {
            Insect blueAnt = InsectFactory.getInsect(new BlueAntFactory(atlas, insectsGroup, PoolInsects.this));
            avaiIns.get(1).add(blueAnt);
            break;
          }
          case 2: {
            Insect redAnt = InsectFactory.getInsect(new RedAntFactory(atlas, insectsGroup, PoolInsects.this));
            avaiIns.get(2).add(redAnt);
            break;
          }
          case 3: {
            Insect ladyBug = InsectFactory.getInsect(new LadybugFactory(atlas, insectsGroup, PoolInsects.this));
            avaiIns.get(3).add(ladyBug);
            break;
          }
          default: break;
        }
      }
    }
  }

  private void initBoss(){
    for(int i = 0; i < 5; i++) {
      for(int j = 0; j < 10; j++) {
        switch (i){
          case 0: {
            Boss bx1 = BossFactory.getBoss(new BoXit1Factory(atlas, insectsGroup, PoolInsects.this));
            avaiBo.get(0).add(bx1);
            break;
          }
          case 1: {
            Boss bx2 = BossFactory.getBoss(new BoXit2Factory(atlas, insectsGroup, PoolInsects.this));
            avaiBo.get(1).add(bx2);
            break;
          }
          case 2: {
            Boss coWe = BossFactory.getBoss(new CoconutWeevilFactory(atlas, insectsGroup, PoolInsects.this));
            avaiBo.get(2).add(coWe);
            break;
          }
          case 3: {
            Boss HoBe1 = BossFactory.getBoss(new HornedBeetle1Factory(atlas, insectsGroup, PoolInsects.this));
            avaiBo.get(3).add(HoBe1);
            break;
          }
          case 4: {
            Boss HoBe2 = BossFactory.getBoss(new HornedBeetle2Factory(atlas, insectsGroup, PoolInsects.this));
            avaiBo.get(4).add(HoBe2);
            break;
          }
          default: break;
        }
      }
    }
  }

  public void addInsAvai(int id, Insect insect){
    avaiIns.get(id).add(insect);
  }

  public void addInsWok(int id, Insect insect){
    wokIns.get(id).add(insect);
  }

  public void addBossAvai(int id, Boss boss){
    avaiBo.get(id).add(boss);
  }

  public void addBossWok(int id, Boss boss){
    wokBo.get(id).add(boss);
  }

  public void removeInsAvai(int id, int index){
    avaiIns.get(id).removeIndex(index);
  }

  public void removeInsWok(Insect insect, int id){
    wokIns.get(id).removeValue(insect, true);
    //wokIns.get(id).removeIndex(index);
  }

  public void removeBoWok(int id, int index){
    System.out.println("id here: " + id + " index: " + index);
    wokBo.get(id).removeIndex(index);
  }

  public void getBoss(int id, int count, float speed){
    float w = GStage.getWorldWidth(), wpad = w*0.1f;
    float h = GStage.getWorldHeight();

    if (avaiBo.get(id).size == 0){
      Tweens.setTimeout(insectsGroup, 2f, ()->{
        System.out.println("out of boss, hang on a second!!");
        getBoss(id, count, speed);
      });
      return;
    }
    if(count > avaiBo.get(id).size){
      int sizeSub = count - avaiBo.get(id).size;
      int size = avaiBo.get(id).size;

      for(int i = 0; i < size; i++) {
        float x = (float)Math.floor(Math.random()*(w - wpad*2) + wpad);
        Boss boss = avaiBo.get(id).removeIndex(0);
        boss.setSpeed(speed);
        boss.moveStart(x, -100);
      }
      System.out.println("khong du so luong: " + sizeSub + " So luong sau: " + avaiBo.get(id).size);
      return;
    }
    else {
      for(int i = 0; i < count; i++) {
        Tweens.setTimeout(insectsGroup, i*1.5f, ()->{
          float x = (float)Math.floor(Math.random()*(w - wpad*2) + wpad);
          Boss boss = avaiBo.get(id).removeIndex(0);
          boss.setSpeed(speed);
          boss.moveStart(x, -100);
        });
      }
      System.out.println("so luong sau: " + avaiBo.get(id).size);
      return;
    }
  }

  public void getInsect(int id, int count, float speed){
    float w = GStage.getWorldWidth(), wpad = w*0.1f;
    float h = GStage.getWorldHeight();

    if (getSizeAvai(id) == 0){
      Tweens.setTimeout(insectsGroup, 2f, ()->{
        System.out.println("out of bee, hang on a second!!");
        getInsect(id, count, speed);
      });
      return;
    }
    if(count > getSizeAvai(id)){
      int sizeSub = count - getSizeAvai(id);
      int size = getSizeAvai(id);

      for(int i = 0; i < size; i++) {
        float x = (float)Math.floor(Math.random()*(w - wpad*2) + wpad);
//        if(avaiIns.get(id).get(i).isAvai){
//          avaiIns.get(id).get(i).isAvai = false;
//          avaiIns.get(id).get(i).setSpeed(speed);
//          avaiIns.get(id).get(i).moveStart(x, -100);
//        }
        Insect insect = avaiIns.get(id).removeIndex(0);
        insect.setSpeed(speed);
        insect.moveStart(x, -100);
        insect.setLv(Config.level);
      }
      System.out.println("khong du so luong: " + sizeSub + " So luong sau: " + getSizeAvai(id));
      return;
    }
    else {
      for(int i = 0; i < count; i++) {
        final int itemp = i;
        Tweens.setTimeout(insectsGroup, i*1.5f, ()->{
          float x = (float)Math.floor(Math.random()*(w - wpad*2) + wpad);
//          if(avaiIns.get(id).get(itemp).isAvai){
//            avaiIns.get(id).get(itemp).isAvai = false;
//            avaiIns.get(id).get(itemp).setSpeed(speed);
//            avaiIns.get(id).get(itemp).moveStart(x, -100);
//          }
          Insect insect = avaiIns.get(id).removeIndex(0);
          insect.setSpeed(speed);
          insect.moveStart(x, -100);
          insect.setLv(Config.level);
        });
      }
      System.out.println("so luong sau: " + getSizeAvai(id));
      return;
    }
  }

  public void frozenInsects(float duaration){
    for(int i = 0; i < 4; i++){
      for(Insect insect : wokIns.get(i)){
        //if(!insect.isAvai)
          insect.setPause(true);
      }
    }
    Tweens.setTimeout(insectsGroup, duaration, ()->{
      for(int i = 0; i < 4; i++){
        for(Insect insect : wokIns.get(i)){
          //if(!insect.isAvai)
            insect.setPause(false);
            insect.frozen.setVisible(false);
        }
      }
    });
  }

  public void bombEffect(){
    for(int i = 0; i < 4; i++) {
      for(Insect insect : wokIns.get(i)){
        insect.setPause(true);
        insect.bomb.setVisible(true);

      }
      for(Insect ins : wokIns.get(i)){
        avaiIns.get(i).add(ins);
        wokIns.get(i).removeValue(ins, true);
        Tweens.setTimeout(insectsGroup, 0.5f, ()->{
          ins.killInsectByBomb();
        });
      }
    }
    Tweens.setTimeout(insectsGroup, 0.6f, ()->{
      nextLv();
    });
  }

  public int getIndexWok(int id, Insect insect){
    int index = 0;
    index = wokIns.get(id).indexOf(insect, true);
    return index;
  }

  public int getIndexBWok(int id, Boss boss){
    int index = 0;
    index = wokBo.get(id).indexOf(boss, true);
    return index;
  }

  public int getIndexAvai(int id, Insect insect){
    int index = 0;
    index = avaiIns.get(id).indexOf(insect, true);
    return index;
  }

//  public int getSizeWok(){
//    int size = 0;
//    for(int i = 0; i < wokIns.size; i++) {
//      size += wokIns.get(i).size;
//    }
//    return size;
//  }

  public int getSizeBWok(){
    int size = 0;
    for(int i = 0; i <wokBo.size; i++) {
      size += wokBo.get(i).size;
    }
    return size;
  }

  public void getSize(){
    System.out.println("size aB: " + getSizeAvai(0));
    System.out.println("size aBA: " + getSizeAvai(1));
    System.out.println("size aRA: " + getSizeAvai(2));
    System.out.println("size aL: " + getSizeAvai(3));
    System.out.println("-----------------------------------");
    System.out.println("size wB: " + getSizeWok(0));
    System.out.println("size wBA: " + getSizeWok(1));
    System.out.println("size wRA: " + getSizeWok(2));
    System.out.println("size wL: " + getSizeWok(3));
  }

  public void getSizeB(){
    System.out.println("size aBx1: " + avaiBo.get(0).size);
    System.out.println("size aBx2: " + avaiBo.get(1).size);
    System.out.println("size aCoBe: " + avaiBo.get(2).size);
    System.out.println("size aHor1: " + avaiBo.get(3).size);
    System.out.println("size aHor2: " + avaiBo.get(4).size);
    System.out.println("-----------------------------------");
    System.out.println("size wBx1: " + wokBo.get(0).size);
    System.out.println("size wBx2: " + wokBo.get(1).size);
    System.out.println("size wCoBe: " + wokBo.get(2).size);
    System.out.println("size wHor1: " + wokBo.get(3).size);
    System.out.println("size wHor2: " + wokBo.get(4).size);
  }

  public void nextLv(){
    managerLevel.startLv(Config.level);
  }

  public void checkOverlapInsect(Vector2 pS, Insect insect){
    for(int i = 0; i < wokIns.size; i++) {
      for (Insect ins : wokIns.get(i)){
        if(ins != insect){
          if(checkOverLap(pS, new Vector2(ins.group.getX(), ins.group.getY()), ins.group.getWidth(), ins.group.getHeight())){
            System.out.println("chet chummmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm");
            ins.click(wokIns.get(i).indexOf(ins, true));
          }
        }
      }
    }
  }

  private boolean checkOverLap(Vector2 pS, Vector2 pD, float w, float h){
    Vector2 vt1 = new Vector2(pD.x - w/2, pD.y - h/2);
    Vector2 vt2 = new Vector2(pD.x + w/2, pD.y - h/2);
    Vector2 vt3 = new Vector2(pD.x + w/2, pD.y + h/2);
    Vector2 vt4 = new Vector2(pD.x - w/2, pD.y + h/2);
    if(checkOverLapPoint(pS, vt1) || checkOverLapPoint(pS, vt2) ||
      checkOverLapPoint(pS, vt3) || checkOverLapPoint(pS, vt4))
      return true;
    return false;
  }

  private boolean checkOverLapPoint(Vector2 pS, Vector2 pD){
    if(pD.x >= pS.x - 50 && pD.x <= pS.x + 50 && pD.y >= pS.y - 50 && pD.y <= pS.y + 50){
      return true;
    }
    return false;
  }

  public int getSizeAvai(int id){
    int count = 0;
    for(Insect ins : avaiIns.get(id)){
      //if(ins.isAvai){
        count++;
      //}
    }
    return count;
  }

  private int getSizeWok(int id){
    int count = 0;
    for(Insect ins : wokIns.get(id)){
      //if(!ins.isAvai){
        count++;
      //}
    }
    return count;
  }

  public int getTotalSizeWok(){
    int count = 0;
    for(int i = 0; i < avaiIns.size; i++) {
      count += getSizeWok(i);
    }
    return count;
  }
}
