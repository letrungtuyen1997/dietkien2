package com.ss.object;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.effect.effectWin;
import com.ss.core.action.exAction.GSimpleAction;
import com.ss.core.util.GLayer;
import com.ss.core.util.GStage;
import com.ss.core.util.GUI;

public class frameRank {
    TextureAtlas atlas;
    Group groupparticle = new Group();
    Group group = new Group();
    Array<Image> ArrArmorial = new Array<>();
    Array<Image> ArrArmorialLock = new Array<>();
    Array<Image> ArrName = new Array<>();
    Array<Group> ArrEffect = new Array<>();

    public frameRank(TextureAtlas atlas){
        GStage.addToLayer(GLayer.ui,groupparticle);
        GStage.addToLayer(GLayer.top,group);
        this.atlas = atlas;
        showframe();
        showachvts();
        ActionAni();
        showBtn();


    }
    void showframe(){
        groupparticle.setPosition(GStage.getWorldWidth(),0);
        Image frameSetting  = GUI.createImage(atlas,"frameRank");
        frameSetting.setWidth(frameSetting.getWidth()*boardConfig.ratioX);
        frameSetting.setHeight(frameSetting.getHeight()*boardConfig.ratioY);
        frameSetting.setOrigin(Align.bottom);
        frameSetting.setPosition(GStage.getWorldWidth()/2,0, Align.bottom);
        groupparticle.addActor(frameSetting);
        groupparticle.addAction(Actions.moveTo(0,0,0.3f, Interpolation.swingOut));

    }
    void showachvts()
    {
        Image armorialBoss, armorialBossLock, nameBoss;
        effectWin effect ;

        for (int i=0;i<5;i++){
            armorialBoss = GUI.createImage(atlas,"boss"+(i+1));
            armorialBossLock = GUI.createImage(atlas,"boss"+(i+1)+"Lock");
            nameBoss = GUI.createImage(atlas,"name"+(i+1));
            if(i==0||i==1){
                armorialBoss.setPosition(GStage.getWorldWidth()/2-armorialBoss.getWidth()*2+(i*armorialBoss.getWidth()*3),GStage.getWorldHeight()/2-400,Align.center);
                armorialBossLock.setPosition(GStage.getWorldWidth()/2-armorialBoss.getWidth()*2+(i*armorialBoss.getWidth()*3),GStage.getWorldHeight()/2-400,Align.center);
                nameBoss.setPosition(GStage.getWorldWidth()/2-armorialBoss.getWidth()*2+(i*armorialBoss.getWidth()*3),GStage.getWorldHeight()/2-200,Align.center);
            }
            else if(i==2||i==3){
                armorialBoss.setPosition(GStage.getWorldWidth()/2-armorialBoss.getWidth()*2+((i-2)*armorialBoss.getWidth()*3),GStage.getWorldHeight()/2+200,Align.center);
                armorialBossLock.setPosition(GStage.getWorldWidth()/2-armorialBossLock.getWidth()*2+((i-2)*armorialBossLock.getWidth()*3),GStage.getWorldHeight()/2+200,Align.center);
                nameBoss.setPosition(GStage.getWorldWidth()/2-armorialBoss.getWidth()*2+((i-2)*armorialBoss.getWidth()*3),GStage.getWorldHeight()/2+400,Align.center);

            }else if(i==4){
                armorialBoss.setPosition(GStage.getWorldWidth()/2-50,GStage.getWorldHeight()/2+700,Align.center);
                armorialBossLock.setPosition(GStage.getWorldWidth()/2-50,GStage.getWorldHeight()/2+700,Align.center);
                nameBoss.setPosition(GStage.getWorldWidth()/2-70,GStage.getWorldHeight()/2+900,Align.center);

            }
            armorialBoss.setWidth(armorialBoss.getWidth()*boardConfig.ratioX);
            armorialBoss.setHeight(armorialBoss.getHeight()*boardConfig.ratioX);
            armorialBoss.setOrigin(Align.center);
            group.addActor(armorialBoss);
            ArrArmorial.add(armorialBoss);
            ///////////// armorialBossLock///////////
            armorialBossLock.setWidth(armorialBossLock.getWidth()*boardConfig.ratioX);
            armorialBossLock.setHeight(armorialBossLock.getHeight()*boardConfig.ratioX);
            armorialBossLock.setOrigin(Align.center);
            group.addActor(armorialBossLock);
            ArrArmorialLock.add(armorialBossLock);
            //////////////////// name boss///////
            nameBoss.setHeight(nameBoss.getHeight()*boardConfig.ratioX);
            nameBoss.setWidth(nameBoss.getWidth()*boardConfig.ratioX);
            nameBoss.setOrigin(Align.center);
            group.addActor(nameBoss);
            ArrName.add(nameBoss);
            ///////// effect unlock//////
            Group groupT = new Group();
            GStage.addToLayer(GLayer.ui,groupT);
            effect = new effectWin(0,armorialBoss.getX()+armorialBoss.getWidth()/2,armorialBoss.getY()+armorialBoss.getHeight()/2-30);
            groupT.addActor(effect);
            ArrEffect.add(groupT);

        }
    }
    void ActionAni(){
        for(int i=0;i<ArrArmorial.size;i++){
                aniBoss(i);
        }
    }
    void aniBoss(int index ){
        float duration = (float)((Math.random()*5+2)/2);
        ArrArmorial.get(index).addAction(Actions.sequence(
                Actions.moveBy(0,-30,duration),
                Actions.moveBy(0,30,duration)
        ));
        ArrName.get(index).addAction(Actions.sequence(
                Actions.moveBy(0,-30,duration),
                Actions.moveBy(0,30,duration),
                GSimpleAction.simpleAction((d,a)->{
                    return true;
                })
        ));
        ArrArmorialLock.get(index).addAction(Actions.sequence(
                Actions.moveBy(0,-30,duration),
                Actions.moveBy(0,30,duration),
                GSimpleAction.simpleAction((d,a)->{
                    aniBoss(index);
                    return true;
                })
        ));
    }
    void showBtn(){
        Image btnClose = GUI.createImage(atlas,"btnClose");
        btnClose.setWidth(btnClose.getWidth()*boardConfig.ratioX);
        btnClose.setHeight(btnClose.getHeight()*boardConfig.ratioX);
        btnClose.setOrigin(Align.center);
        btnClose.setPosition(GStage.getWorldWidth()/2,GStage.getWorldHeight()-200,Align.center);
        group.addActor(btnClose);
        btnClose.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                btnClose.setTouchable(Touchable.disabled);
                btnClose.addAction(Actions.sequence(
                        Actions.scaleTo(0.8f,0.8f,0.1f),
                        Actions.scaleTo(1f,1f,0.1f),
                        GSimpleAction.simpleAction((d,a)->{
                            groupparticle.clear();
                            group.clear();
                            group.remove();
                            groupparticle.remove();
                            for (int i=0;i<ArrEffect.size;i++){
                                ArrEffect.get(i).clear();
                                ArrEffect.get(i).remove();
                            }
                            return true;
                        })
                ));
                return super.touchDown(event, x, y, pointer, button);
            }
        });
    }
}
