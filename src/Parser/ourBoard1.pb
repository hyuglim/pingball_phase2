board name=ourBoard1 friction1=0.040 friction2=0.040 

# define a ball
ball name=BallA x=3 y=2.5 xVelocity=1.0 yVelocity=1.2 
ball name=BallB x=18 y=15 xVelocity=0.0 yVelocity=-10 

# define some bumpers
squareBumper name=Square1 x=12 y=11
squareBumper name=Square2 x=2 y=13
squareBumper name=Square3 x=4 y=7

circleBumper name=Circle x=4 y=3

triangleBumper name=Tri x=19 y=0 orientation=90
triangleBumper name=Tri x=0 y=0 orientation=0


# define some flippers
leftFlipper name=FlipL x=9 y=6 orientation=0 
rightFlipper name=FlipR x=12 y=5 orientation=0


# define an absorber to catch the ball
absorber name=Abs x=10 y=8 width=10 height=2 

# define events between gizmos
fire trigger=Square1 action=FlipL
fire trigger=Square2 action=FlipL
fire trigger=Square3 action=FlipL
fire trigger=Circle action=Abs
fire trigger=FlipL action=Abs 