package com.ss.gameLogic.scene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.ss.GMain;
import com.ss.commons.Tweens;
import com.ss.core.action.exAction.GSimpleAction;
import com.ss.core.transitions.GTransition;
import com.ss.core.transitions.GTransitionSlice;
import com.ss.core.util.GAssetsManager;
import com.ss.core.util.GLayer;
import com.ss.core.util.GScreen;
import com.ss.core.util.GStage;
import com.ss.core.util.GUI;
import com.ss.object.animation;

public class GMenu extends GScreen {
    TextureAtlas menuAtlas;
    Group group = new Group();


    @Override
    public void dispose() {

    }

    @Override
    public void init() {
        GStage.addToLayer(GLayer.top,group);
        Group menuGroup = new Group();
        GStage.addToLayer(GLayer.ui, menuGroup);

        menuAtlas = GAssetsManager.getTextureAtlas("menu.atlas");
        Image bg = GUI.createImage(menuAtlas, "bg");
        menuGroup.addActor(bg);

        Button btn = GUI.creatButton(menuAtlas.findRegion("newgame"));
//        btn.setPosition(200,200);
        menuGroup.addActor(btn);
        btn.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                GMain.platform.ShowVideoReward((finish) -> {
                    if (finish) {
                        Gdx.app.log("video reward", "view complete");
                    }
                    else {
                        Gdx.app.log("video reward", "view not complete");
                    }
                });
            }
        });
       // animation();
    }

    @Override
    public void run() {

    }




}
