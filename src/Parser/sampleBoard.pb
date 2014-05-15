board name=sampleBoard gravity=0.0 friction1 = 0.0 friction2 = 0.0

  # define a ball
ball name=Ball x=15 y=9 xVelocity=20 yVelocity=-3 radius=0.25

  
 circleBumper name=Circle5 x=5 y=18 
 squareBumper name=Square1 x=1 y=12
 triangleBumper name=Tri2 x=11 y=9 orientation=180
 spawner name = spawn x=3 y=6
  
  # add some flippers
  leftFlipper name=FlipL2 x=5 y=10 orientation=90
  rightFlipper name=FlipR2 x=7 y=8 orientation=90
  
  #define an absorber to catch the ball at the bottom
  absorber name=Abs x=0 y=19 width=20 height=1 
  
  portal name=Alpha x=19 y=8 otherPortal=Gamma
  portal name=Beta x=19 y=16 otherPortal=Delta
  portal name=Gamma x=9 y=16 otherBoard = ourBoard1 otherPortal=Heyhey
  # make the absorber self-triggering
  
  fire trigger=Abs action=Abs
  fire trigger=FlipL2 action=FlipL2
  
 keydown key=shift action=FlipL2
 keyup key=ctrl action=FlipR2
