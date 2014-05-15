board name=sampleBoard gravity=0.0 friction1 = 0.0 friction2 = 0.0
# This is the staff's sample board.
# This board is meant for stand-alone play. It is a loose 
# representation of the screenshot in the project handout.

  # define a ball
  ball name=Ball x=15 y=9 xVelocity=0 yVelocity=20 radius=0.25
  #ball name = BallB x=3 y=2 xVelocity =50 yVelocity =50 radius = 0.5

  #ball name=Ball x=1 y=9 xVelocity=0 yVelocity=3 radius=0.25

  #ball name=Ball x=15 y=9 xVelocity=0 yVelocity=50 radius=0.25
  #ball name = BallB x=3 y=2 xVelocity =50 yVelocity =50 radius = 0.5
  #ball name=Ball x=1 y=9 xVelocity=0 yVelocity=3 radius=0.25
  
  # define a series of circle bumpers
  #circleBumper name=Circle5 x=5 y=10 
  
  # define a series of square bumpers
  squareBumper name=Square1 x=1 y=12
  
  # define some triangular bumpers
  circleBumper name=Tri2 x=11 y=9
  spawner name = spawn x=3 y=6
  
  # add some flippers
  leftFlipper name=FlipL2 x=5 y=10 orientation=90
  rightFlipper name=FlipR2 x=7 y=8 orientation=90
  
  portal name=p x=11 y=15 otherPortal=k
  portal name=k x= 15 y = 17 otherPortal=p
  #define an absorber to catch the ball at the bottom
  #absorber name=Abs x=0 y=19 width=20 height=1 
  
  #portal name=Alpha x=19 y=8 otherPortal=Gamma
  #portal name=Beta x=19 y=16 otherPortal=Delta
  #portal name=Gamma x=9 y=16 otherBoard = ourBoard1 otherPortal=Heyhey
  # make the absorber self-triggering
  
  fire trigger=Abs action=Abs
  fire trigger=FlipL2 action=FlipL2
  
  keydown key=shift action=FlipL2
  keyup key=ctrl action=FlipR2