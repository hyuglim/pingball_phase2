board name=sampleBoard3 gravity=20.0
# This board is meant for stand-alone play. It is a loose 
# representation of the screenshot in the project handout.

  # define a ball
  ball name=Ball x=0.5 y=0.5 xVelocity=2.5 yVelocity=2.5 radius = 1
  ball name=Ball2 x=6.0 y=5.0 xVelocity=3.5 yVelocity=-1.4 radius = .25
  
  # define a series of circle bumpers
  circleBumper name=Circle5 x=5 y=4 
  
  # define a series of square bumpers
  squareBumper name=Square1 x=1 y=2
  
  # define some triangular bumpers
  triangleBumper name=Tri2 x=11 y=9 orientation=180
  
  # add some flippers
  leftFlipper name=FlipL2 x=8 y=7 orientation=0
  rightFlipper name=FlipR2 x=10 y=7 orientation=0