board name=sampleBoard gravity=0.0 friction1 = 0.0 friction2 = 0.0
# This is the staff's sample board.
# This board is meant for stand-alone play. It is a loose 
# representation of the screenshot in the project handout.

  # define a ball
  ball name=Ball x=0.5 y=5.5 xVelocity=5 yVelocity=5 radius=0.25
  
  # define a series of circle bumpers
  circleBumper name=Circle5 x=5 y=4 
  
  # define a series of square bumpers
  squareBumper name=Square1 x=1 y=2
  
  # define some triangular bumpers
  triangleBumper name=Tri2 x=11 y=9 orientation=180
  
  
  # add some flippers
  leftFlipper name=FlipL2 x=8 y=7 orientation=90
  rightFlipper name=FlipR2 x=10 y=7 orientation=0

  # define an absorber to catch the ball at the bottom
  absorber name=Abs x=0 y=19 width=20 height=1 
  
  #portal name=Alpha x=19 y=8 otherPortal=Gamma
  #portal name=Beta x=19 y=16 otherPortal=Delta
  #portal name=Gamma x=9 y=16 otherBoard = ourBoard1 otherPortal=Heyhey

  # make the absorber self-triggering
  fire trigger=Abs action=Abs
  fire trigger=FlipL2 action=FlipL2
  
  keydown key=shift action=Abs
  keyup key=ctrl action=Abs