package com.ss.object.TestFtr.FactoryFolder;

import com.badlogic.gdx.math.Vector2;
import com.ss.core.util.GStage;
import com.ss.object.TestFtr.Insect;
import com.ss.object.TestFtr.Interfaces.InsectAbtractFactory;

public class InsectFactory {
  public static Insect getInsect(InsectAbtractFactory factory){
    return factory.createInsect();
  }
  public static Vector2 getPositionB(float rotation, Vector2 pA){
    Vector2 pB = new Vector2(0,0);
    float w = GStage.getWorldWidth(), wpad = w*0.1f;
    float h = GStage.getWorldHeight(), hpad = h*0.1f;
    float padding = (GStage.getWorldWidth() - wpad*2);
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
      System.out.println("rotation exception: " + rotationTemp);
      padding = 0;
    }
    float x = pA.x + operator*padding;
    if(x < wpad) x = wpad;
    if(x > GStage.getWorldWidth() - wpad) x = GStage.getWorldWidth() - wpad;
    float y = (float)-Math.tan(Math.toRadians(90-rotationTemp))*(x-pA.x) + pA.y;
    //System.out.println("x: " + x);

    if(y > GStage.getWorldHeight() + hpad){
      y = GStage.getWorldHeight() + hpad;
      x = (y - pA.y)/((float)-Math.tan(Math.toRadians(90 - rotationTemp))) + pA.x;
    }

    if(padding != 0)
      pB.set(x,y);
    else pB.set(pA.x, pA.y + (1300-pA.y)/3);

    return pB;
  }
}
