package com.ss.object.TestFtr;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.effect.SoundEffect;
import com.platform.ToggleHandler;
import com.ss.commons.Tweens;
import com.ss.commons._ToggleButton;
import com.ss.core.action.exAction.GSimpleAction;
import com.ss.core.util.GLayer;
import com.ss.core.util.GStage;
import com.ss.core.util.GUI;
import com.ss.object.boardConfig;

public class Setting implements ToggleHandler {
    TextureAtlas atlas;
    Group group = new Group();
    Image btnSeeting, btnStart, btnRank;
    String mode;
    public Setting(TextureAtlas atlas,Image button,Image btnStart,Image btnRank, String mode){
        this.atlas = atlas;
        this.mode = mode;
        ShowFrame();
        this.btnSeeting = button;
        this.btnStart = btnStart;
        this.btnRank = btnRank;
    }
    void ShowFrame(){
        GStage.addToLayer(GLayer.top,group);
        group.setPosition(GStage.getWorldWidth()/2,-2000,Align.center);
        Image frame = GUI.createImage(atlas,"frameSetting");
        frame.setWidth(frame.getWidth()*boardConfig.ratioY);
        frame.setHeight(frame.getHeight()*boardConfig.ratioY);
        frame.setOrigin(Align.center);
        frame.setPosition(0,0,Align.center);
        group.addActor(frame);
        group.addAction(Actions.moveTo(GStage.getWorldWidth()/2,GStage.getWorldHeight()/4,0.5f, Interpolation.swingOut));

        //////////////////////// turn on turn off sound music/////////
        ////////// btn TurnOn /////////
        Image btnTurnOnSound = GUI.createImage(atlas,"turnOn");
        btnTurnOnSound.setWidth(btnTurnOnSound.getWidth()*boardConfig.ratioY);
        btnTurnOnSound.setHeight(btnTurnOnSound.getHeight()*boardConfig.ratioY);
        btnTurnOnSound.setOrigin(Align.center);
        btnTurnOnSound.setPosition(150,380,Align.center);
        group.addActor(btnTurnOnSound);
        ///////// btn TurnOff ///////
        Image btnTurnOffSound = GUI.createImage(atlas,"turnOff");
        btnTurnOffSound.setWidth(btnTurnOffSound.getWidth()*boardConfig.ratioY);
        btnTurnOffSound.setHeight(btnTurnOffSound.getHeight()*boardConfig.ratioY);
        btnTurnOffSound.setOrigin(Align.center);
        btnTurnOffSound.setPosition(150,380,Align.center);
        group.addActor(btnTurnOffSound);
        if(SoundEffect.mute==false){
            btnTurnOffSound.setVisible(false);
        }else {
            btnTurnOnSound.setVisible(false);
        }
        new _ToggleButton(btnTurnOnSound,btnTurnOffSound,"sound",this);
        ////////////////// music///////////////////
        ////////// btn TurnOn /////////
        Image btnTurnOnMusic = GUI.createImage(atlas,"turnOn");
        btnTurnOnMusic.setWidth(btnTurnOnMusic.getWidth()*boardConfig.ratioY);
        btnTurnOnMusic.setHeight(btnTurnOnMusic.getHeight()*boardConfig.ratioY);
        btnTurnOnMusic.setOrigin(Align.center);
        btnTurnOnMusic.setPosition(150,600,Align.center);
        group.addActor(btnTurnOnMusic);
        ///////// btn TurnOff ///////
        Image btnTurnOffMusic = GUI.createImage(atlas,"turnOff");
        btnTurnOffMusic.setWidth(btnTurnOffMusic.getWidth()*boardConfig.ratioY);
        btnTurnOffMusic.setHeight(btnTurnOffMusic.getHeight()*boardConfig.ratioY);
        btnTurnOffMusic.setOrigin(Align.center);
        btnTurnOffMusic.setPosition(150,600,Align.center);
        group.addActor(btnTurnOffMusic);
        if(SoundEffect.music==false){
            btnTurnOffMusic.setVisible(false);
        }else {
            btnTurnOnMusic.setVisible(false);
        }
        new _ToggleButton(btnTurnOnMusic,btnTurnOffMusic,"music",this);
        ////////////////// mode other ///////
        System.out.println("okok: "+this.mode);
        if(mode=="game"){
            ///////// btn //////////
            Image btn = GUI.createImage(atlas,"btnClose");
            btn.setWidth(btn.getWidth()*boardConfig.ratioX);
            btn.setHeight(btn.getHeight()*boardConfig.ratioX);
            btn.setOrigin(Align.center);
            btn.setPosition(0,frame.getHeight()/2-btn.getHeight(),Align.center);
            group.addActor(btn);
            ////////// eventBtn ///////////
            evenbtn(btn);
        }else {
            Image btn = GUI.createImage(atlas,"btnClose");
            btn.setWidth(btn.getWidth()*boardConfig.ratioX-0.4f);
            btn.setHeight(btn.getHeight()*boardConfig.ratioX-0.4f);
            btn.setOrigin(Align.center);
            btn.setPosition(-btn.getWidth()/2-20,frame.getHeight()/2-btn.getHeight(),Align.center);
            group.addActor(btn);
            //////////// btn thoat///////
            Image btnExit = GUI.createImage(atlas,"btnExit");
            btnExit.setWidth(btnExit.getWidth()*boardConfig.ratioX-0.4f);
            btnExit.setHeight(btnExit.getHeight()*boardConfig.ratioX-0.4f);
            btnExit.setOrigin(Align.center);
            btnExit.setPosition(btnExit.getWidth()/2,frame.getHeight()/2-btn.getHeight(),Align.center);
            group.addActor(btnExit);
            ////////// eventBtn ///////////
            evenbtn(btn);
            evenbtnExit(btnExit);
        }


    }
    void evenbtn(Image btn){
        btn.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                btn.setTouchable(Touchable.disabled);
                btn.addAction(Actions.sequence(
                        Actions.scaleTo(0.8f,0.8f,0.1f),
                        Actions.scaleTo(1f,1f,0.1f),
                        GSimpleAction.simpleAction((d,a)->{
                            group.addAction(Actions.moveTo(GStage.getWorldWidth()/2,-2000,0.3f));
                            btnSeeting.setTouchable(Touchable.enabled);
                            btnStart.setTouchable(Touchable.enabled);
                            btnRank.setTouchable(Touchable.enabled);
                            return true;
                        })
                ));
                return super.touchDown(event, x, y, pointer, button);
            }
        });

    }
    void evenbtnExit(Image btn){
        btn.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                btn.setTouchable(Touchable.disabled);
                btn.addAction(Actions.sequence(
                        Actions.scaleTo(0.8f,0.8f,0.1f),
                        Actions.scaleTo(1f,1f,0.1f),
                        GSimpleAction.simpleAction((d,a)->{
                            group.addAction(Actions.moveTo(GStage.getWorldWidth()/2,-2000,0.3f));
                            Tweens.setTimeout(group,0.3f,()->{
                                group.remove();
                                group.clear();
                            });
                            return true;
                        })
                ));
                return super.touchDown(event, x, y, pointer, button);
            }
        });

    }

    @Override
    public void activeHandler(String str) {
        if(str=="sound"){
            SoundEffect.mute = true;
        }
        if(str=="music"){
            if(str=="music"){
//                if(SoundEffect.music==false){
                    SoundEffect.Stopmusic();
//                }
            }
        }


    }

    @Override
    public void deactiveHandler(String str) {
        if(str=="sound"){
            SoundEffect.mute = false;
        }
        if(str=="music"){
//            if(SoundEffect.music==true){
                SoundEffect.Playmusic();

//            }

        }

    }
}
