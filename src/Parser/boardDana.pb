board name=Dana gravity=0 friction1=0 friction2=0
ball name=Ball1 x=11 y=5 xVelocity=0 yVelocity=25 radius=.25
ball name=Ball2 x=11 y=19 xVelocity=5 yVelocity=-80 radius=0.25


#flippers
rightFlipper name=rf1 x=15 y=2 orientation=0
leftFlipper name=lf1 x=3 y=2 orientation=0

#bumpers

portal name=a x=0 y=19 otherPortal=b
portal name=b x=10 y=12 otherPortal=c
spawner name=sp1 x=5 y=7

#make a heart
circleBumper name=c1 x=10 y=10
circleBumper name=c2 x=11 y=9
circleBumper name=c3 x=9 y=9
circleBumper name=c4 x=12 y=8
circleBumper name=c5 x=8 y=8
circleBumper name=c6 x=7 y=8
circleBumper name=c7 x=13 y=8
circleBumper name=c8 x=14 y=9
circleBumper name=c9 x=6 y=9
circleBumper name=c10 x=5 y=10
circleBumper name=c11 x=15 y=10
circleBumper name=c12 x=15 y=11
circleBumper name=c13 x=5 y=11
circleBumper name=c14 x=14 y=12
circleBumper name=c15 x=6 y=12
circleBumper name=c16 x=13 y=13
circleBumper name=c17 x=7 y=13
circleBumper name=c18 x=12 y=14
circleBumper name=c19 x=8 y=14
circleBumper name=c20 x=11 y=15
circleBumper name=c21 x=9 y=15
circleBumper name=c22 x=10 y=16

#triggers
fire trigger=c12 action =rf1
fire trigger=c9 action=lf1
fire trigger=rf1 action=rf1
fire trigger=lf1 action=lf1


