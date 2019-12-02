package com.ss.scene;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.ss.commons.Tweens;
import com.ss.core.action.exAction.GSimpleAction;
import com.ss.core.exSprite.GShapeSprite;
import com.ss.core.util.GAssetsManager;
import com.ss.core.util.GLayer;
import com.ss.core.util.GScreen;
import com.ss.core.util.GStage;
import com.ss.core.util.GUI;
import com.ss.object.GameOver;
import com.ss.object.TestFtr.Setting;
import com.ss.object.boardConfig;
import com.ss.object.frameRank;

public class gameStart extends GScreen {
    TextureAtlas atlas;
    BitmapFont font;
    Group group = new Group();
    Group groupCount = new Group();
    Group groupbtn = new Group();
    @Override
    public void dispose() {

    }

    @Override
    public void init() {
        GStage.addToLayer(GLayer.ui,group);
        GStage.addToLayer(GLayer.ui,groupCount);
        GStage.addToLayer(GLayer.ui,groupbtn);
        initAtlas();
        showBg();


    }
    void showBg(){
        Image bg = GUI.createImage(atlas,"bgStart");
        bg.setWidth(bg.getWidth()*boardConfig.ratioX);
        bg.setHeight(bg.getHeight()*boardConfig.ratioY);
        group.addActor(bg);
        Image logo = GUI.createImage(atlas,"logo");
        logo.setWidth(logo.getWidth()*boardConfig.ratioX);
        logo.setHeight(logo.getHeight()*boardConfig.ratioY);
        logo.setOrigin(Align.bottom);
        logo.setPosition(GStage.getWorldWidth()/2,-100,Align.bottom);
        groupbtn.addActor(logo);
        ////// ani lo go//////
        aniLogo(logo);
        ////// btn rank ///////
        Image btnRank = GUI.createImage(atlas,"btnRank");
        btnRank.setWidth(btnRank.getWidth()*boardConfig.ratioY);
        btnRank.setHeight(btnRank.getHeight()*boardConfig.ratioY);
        btnRank.setOrigin(Align.center);
        btnRank.setPosition(GStage.getWorldWidth()/2-btnRank.getWidth()+100,GStage.getWorldHeight()/2+200, Align.center);
        groupbtn.addActor(btnRank);
        ////// btn top//////
        Image btnTop = GUI.createImage(atlas,"btnTop");
        btnTop.setWidth(btnTop.getWidth()*boardConfig.ratioY);
        btnTop.setHeight(btnTop.getHeight()*boardConfig.ratioY);
        btnTop.setOrigin(Align.center);
        btnTop.setPosition(GStage.getWorldWidth()/2+btnTop.getWidth()-100,GStage.getWorldHeight()/2+200, Align.center);
        groupbtn.addActor(btnTop);
        ////// btn Satrt //////
        Image btnStart = GUI.createImage(atlas,"btnStart");
        btnStart.setWidth(btnStart.getWidth()*boardConfig.ratioY);
        btnStart.setHeight(btnStart.getHeight()*boardConfig.ratioY);
        btnStart.setOrigin(Align.center);
        btnStart.setPosition(GStage.getWorldWidth()/2,GStage.getWorldHeight()/2+btnStart.getHeight()+200,Align.center);
        groupbtn.addActor(btnStart);
        //////// btn Setting ////////
        Image btnSetting = GUI.createImage(atlas,"btnSetting");
        btnSetting.setWidth(btnSetting.getWidth()*boardConfig.ratioY);
        btnSetting.setHeight(btnSetting.getHeight()*boardConfig.ratioY);
        btnSetting.setOrigin(Align.center);
        btnSetting.setPosition(GStage.getWorldWidth()-btnSetting.getWidth()/2,btnSetting.getHeight()/2,Align.center);
        groupbtn.addActor(btnSetting);
        ///// animation btn /////////
        aniBtn(btnStart,btnTop,btnRank);
        ///////// evnet btn//////
        evenBtnRank(btnRank);
        evenBtnStart(btnStart);
        evenBtnSetting(btnSetting,btnStart,btnRank);
    }
    void aniBtn(Image btn1,Image btn2,Image btn3){
        btn1.addAction(Actions.sequence(
                Actions.scaleTo(0.8f,0.8f,1f),
                Actions.scaleTo(1f,1f,1f)
        ));
        Tweens.setTimeout(group,0.4f,()->{
            btn2.addAction(Actions.sequence(
                    Actions.scaleTo(0.8f,0.8f,1f),
                    Actions.scaleTo(1f,1f,1f)
            ));
        });
        Tweens.setTimeout(group,0.8f,()->{
            btn3.addAction(Actions.sequence(
                    Actions.scaleTo(0.8f,0.8f,1f),
                    Actions.scaleTo(1f,1f,1f)
            ));
        });
        Tweens.setTimeout(group,8f,()->{
            aniBtn(btn1,btn2,btn3);
        });

    }
    void aniLogo(Image logo){
        logo.addAction(Actions.sequence(
                Actions.rotateTo(5,3f),
                Actions.rotateTo(-5,3f),
                GSimpleAction.simpleAction((d,a)->{
                    aniLogo(logo);
                    return true;
                })
        ));
    }
    void evenBtnRank(Image btn){
        btn.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                btn.setTouchable(Touchable.disabled);
                btn.addAction(Actions.sequence(
                        Actions.scaleTo(0.8f,0.8f,0.1f),
                        Actions.scaleTo(1f,1f,0.1f),
                        GSimpleAction.simpleAction((d,a)->{
                            btn.setTouchable(Touchable.enabled);
                            new frameRank(atlas);
                            return true;
                        })
                ));
                return super.touchDown(event, x, y, pointer, button);
            }
        });
    }
    void evenBtnStart(Image btn){
        btn.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                btn.setTouchable(Touchable.disabled);
                btn.addAction(Actions.sequence(
                        Actions.scaleTo(0.8f,0.8f,0.1f),
                        Actions.scaleTo(1f,1f,0.1f),
                        GSimpleAction.simpleAction((d,a)->{
                            btn.setTouchable(Touchable.enabled);
                            groupbtn.addAction(Actions.moveBy(0,-2300,0.5f, Interpolation.swingIn));
                            Tweens.setTimeout(group,0.5f,()->{
                                darkbg();
                            });
                            return true;
                        })
                ));
                return super.touchDown(event, x, y, pointer, button);
            }
        });
    }
    void evenBtnSetting(Image btn,Image btnStart, Image btnRank){
        btn.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                btn.setTouchable(Touchable.disabled);
                btnStart.setTouchable(Touchable.disabled);
                btnRank.setTouchable(Touchable.disabled);
                btn.addAction(Actions.sequence(
                        Actions.scaleTo(0.8f,0.8f,0.1f),
                        Actions.scaleTo(1f,1f,0.1f),
                        GSimpleAction.simpleAction((d,a)->{
                           // new Setting(atlas,btn,btnStart,btnRank,"game");
                            new GameOver(atlas,10,10);
                            return true;
                        })
                ));
                return super.touchDown(event, x, y, pointer, button);
            }
        });
    }
    void darkbg(){
        final GShapeSprite blackOverlay = new GShapeSprite();
        blackOverlay.createRectangle(true, -GStage.getWorldWidth()/2,-GStage.getWorldHeight()/2, GStage.getWorldWidth()*2, GStage.getWorldHeight()*2);
        blackOverlay.setColor(0,0,0,0.5f);
        groupCount.addActor(blackOverlay);
        ActionCountDown();

    }
    void ActionCountDown(){
        Array<Group> ArrText = new Array<>();

        Label label;
        for(int i=3; i>=0;i--){
            Group groupText = new Group();
            GStage.addToLayer(GLayer.top,groupText);
            groupText.setPosition(GStage.getWorldWidth()/2,GStage.getWorldHeight()/2,Align.center);
            if(i==0)
                label = new Label("Go",new Label.LabelStyle(font, Color.RED));
            else
                label = new Label(""+i,new Label.LabelStyle(font, Color.RED));

            label.setOrigin(Align.center);
            label.setPosition(0,0,Align.center);
            groupText.addActor(label);
            ArrText.add(groupText);
            groupText.setVisible(false);
        }
        ActionText(ArrText,0);
    }
    void ActionText(Array<Group> ArrText,int index){
        if(index>3){
            groupCount.remove();
            return;
        }
        ArrText.get(index).setVisible(true);
        int finalIndex = index;
        ArrText.get(index).addAction(Actions.sequence(
                Actions.scaleTo(6,6,0.5f),
                Actions.scaleTo(0,0,0.5f),
                GSimpleAction.simpleAction((d,a)->{
                    ArrText.get(finalIndex).setVisible(false);
                    return true;
                })
        ));
        index++;
        int finalIndex1 = index;
        Tweens.setTimeout(group,1,()->{
            ActionText(ArrText, finalIndex1);
        });
    }

    @Override
    public void run() {

    }
    void initAtlas(){

        atlas = GAssetsManager.getTextureAtlas("uiStart.atlas");
        font = GAssetsManager.getBitmapFont("font_white.fnt");
    }
}
