board name=ourBoard3 gravity=20.0

  # define a ball
  ball name=Ball1 x=0.5 y=5.5 xVelocity=1 yVelocity=0 radius = 0.25
  ball name=Ball2 x=6.0 y=5.0 xVelocity=3.5 yVelocity=-1.4 radius = 0.5
  
  # define a series of circle bumpers
  circleBumper name=Circle1 x=14 y=9
  
  # define a series of square bumpers
  squareBumper name=Square4 x=6 y=7
  squareBumper name=Square5 x=7 y=7
  squareBumper name=Square6 x=8 y=7
  squareBumper name=Square7 x=3 y=4
  squareBumper name=Square8 x=4 y=4
  squareBumper name=Square9 x=5 y=4
  
  # define some triangular bumpers
  triangleBumper name=Tri1 x=15 y=4 orientation=90
  triangleBumper name=Tri2 x=0 y=5 orientation=0
  
  # add some flippers
  leftFlipper name=FlipL1 x=3 y=14 orientation=90
  leftFlipper name=FlipR1 x=18 y=8 orientation=270
  rightFlipper name=FlipR2 x=7 y=9 orientation=270

  # define an absorber to catch the ball at the bottom
  absorber name=Abs x=0 y=19 width=16 height=1 
  
  # define events between gizmos
  fire trigger=Circle action=FlipL1
  fire trigger=Square5 action=FlipR1
  fire trigger=Square6 action=FlipR2


  # make the absorber self-triggering
  fire trigger=Abs action=Abs
  
  #keys
  keyup key=alt action=FlipL1
  keydown key=space action=FlipR2