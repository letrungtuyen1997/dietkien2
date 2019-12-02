package com.ss.object.TestFtr.FactoryBoss.FactoryFolder;

import com.ss.object.TestFtr.FactoryBoss.Boss.Boss;
import com.ss.object.TestFtr.FactoryBoss.Interfaces.BossAbtractFactory;

public class BossFactory {
  public static Boss getBoss(BossAbtractFactory factory){
    return factory.createBoss();
  }
}
