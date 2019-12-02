package com.ss.object;

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
import com.ss.commons.Tweens;
import com.ss.core.action.exAction.GSimpleAction;
import com.ss.core.exSprite.GShapeSprite;
import com.ss.core.util.GAssetsManager;
import com.ss.core.util.GLayer;
import com.ss.core.util.GStage;
import com.ss.core.util.GUI;

public class GameOver {
    TextureAtlas atlas;
    Group group = new Group();
    int score,quanlity;
    BitmapFont font;
    public GameOver(TextureAtlas atlas, int score, int quanlityInsect){
        initFont();
        this.score = score;
        this.quanlity = quanlityInsect;
        this.atlas = atlas;
        showFrame();
        showLabel();


    }
    void showFrame(){
        GStage.addToLayer(GLayer.top,group);
        final GShapeSprite blackOverlay = new GShapeSprite();
        blackOverlay.createRectangle(true, -GStage.getWorldWidth()/2,-GStage.getWorldHeight()/2, GStage.getWorldWidth()*2, GStage.getWorldHeight()*2);
        blackOverlay.setColor(0,0,0,0.5f);
        group.addActor(blackOverlay);
        group.setPosition(GStage.getWorldWidth()/2,-2000, Align.center);
        Image frame = GUI.createImage(atlas,"frameResult");
        frame.setWidth(frame.getWidth()*boardConfig.ratioY);
        frame.setHeight(frame.getHeight()*boardConfig.ratioY);
        frame.setOrigin(Align.center);
        frame.setPosition(0,0,Align.center);
        group.addActor(frame);
        group.addAction(Actions.moveTo(GStage.getWorldWidth()/2,GStage.getWorldHeight()/4,0.5f, Interpolation.swingOut));
        ////////// btn ////////
        Image btnReset = GUI.createImage(atlas,"btnReset");
        btnReset.setHeight(btnReset.getHeight()*boardConfig.ratioX);
        btnReset.setWidth(btnReset.getWidth()*boardConfig.ratioX);
        btnReset.setOrigin(Align.center);
        btnReset.setPosition(-btnReset.getWidth()/2-10,frame.getHeight()/2-btnReset.getHeight(),Align.center);
        group.addActor(btnReset);
        Image btnExit = GUI.createImage(atlas,"btnExit");
        btnExit.setHeight(btnExit.getHeight()*boardConfig.ratioX);
        btnExit.setWidth(btnExit.getWidth()*boardConfig.ratioX);
        btnExit.setOrigin(Align.center);
        btnExit.setPosition(btnExit.getWidth()/2+10,frame.getHeight()/2-btnExit.getHeight(),Align.center);
        group.addActor(btnExit);
        ///////// event button ///////
        eventbtnExit(btnExit);
        eventbtnReset(btnReset);

    }
    void showLabel(){
        Label scoreLabel = new Label(""+score,new Label.LabelStyle(font,null));
        scoreLabel.setFontScale(boardConfig.ratioX-0.5f);
        scoreLabel.setOrigin(Align.center);
        scoreLabel.setAlignment(Align.center);
        scoreLabel.setPosition(290,400,Align.center);
        group.addActor(scoreLabel);
        Label quanlityLabel = new Label(""+quanlity,new Label.LabelStyle(font,null));
        quanlityLabel.setFontScale(boardConfig.ratioX-0.5f);
        quanlityLabel.setOrigin(Align.center);
        quanlityLabel.setAlignment(Align.center);
        quanlityLabel.setPosition(290,630,Align.center);
        group.addActor(quanlityLabel);
    }
    void eventbtnExit(Image btn){
        btn.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                btn.setTouchable(Touchable.disabled);
                btn.addAction(Actions.sequence(
                        Actions.scaleTo(0.8f,0.8f,0.1f),
                        Actions.scaleTo(1f,1f,0.1f),
                        GSimpleAction.simpleAction((d, a)->{
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
    void eventbtnReset(Image btn){
        btn.addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                btn.setTouchable(Touchable.disabled);
                btn.addAction(Actions.sequence(
                        Actions.scaleTo(0.8f,0.8f,0.1f),
                        Actions.scaleTo(1f,1f,0.1f),
                        GSimpleAction.simpleAction((d, a)->{
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
    void initFont(){
        font = GAssetsManager.getBitmapFont("font_white.fnt");
    }

}
