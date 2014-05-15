board name=ourBoard2 friction1=0.040 friction2=0.040 

# define a ball
ball name=Ball x=3 y=2.5 xVelocity=1.0 yVelocity=1.2 radius = 0.25

# define some bumpers
squareBumper name=Square1 x=12 y=11
squareBumper name=Square2 x=2 y=13
squareBumper name=Square3 x=4 y=7

circleBumper name=Circle x=3 y=5

triangleBumper name=Tri x=10 y=9 orientation=90

#define a spawner

spawner name=spaw x=18 y=15
#define portals 
portal name=p1 x=2 y=19 otherPortal = p2
portal name=p2 x=18 y=3 otherBoard = ourBoard2 otherPortal = p3
# define some flippers
leftFlipper name=FlipL x=14 y=5 orientation=0 
rightFlipper name=FlipR x=6 y=8 orientation=0


# define an absorber to catch the ball
 absorber name=Abs x=7 y=18 width=11 height=1 

# define events between gizmos
fire trigger=Square1 action=FlipL
fire trigger=Square2 action=FlipR
fire trigger=Square3 action=FlipR

# make the absorber self-triggering
 fire trigger=Abs action=Abs 