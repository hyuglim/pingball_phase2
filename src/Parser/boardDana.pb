board name=Dana gravity=0 friction1=0 friction2=0
ball name=Ball1 x=11 y=5 xVelocity=0 yVelocity=25 radius=.25
ball name=Ball2 x=11 y=19 xVelocity=5 yVelocity=-80 radius=0.25
#ball name=Ball3 x=4 y=10 xVelocity=20 yVelocity=0 radius=0.25
#ball name=Ball4 x=3 y=17 xVelocity=6 yVelocity=-6 radius=0.25

#flippers
rightFlipper name=rf1 x=15 y=2 orientation=0
leftFlipper name=lf1 x=3 y=2 orientation=0

#bumpers
triangleBumper name=t1 x=1 y=19 orientation=0
triangleBumper name=t2 x=3 y=19 orientation=90
triangleBumper name=t3 x=5 y=19 orientation=180
triangleBumper name=t4 x=7 y=19 orientation=270
triangleBumper name=t5 x=9 y=19 orientation=0
triangleBumper name=t6 x=11 y=19 orientation=0
triangleBumper name=t7 x=13 y=19 orientation=270
triangleBumper name=t8 x=15 y=19 orientation=180
triangleBumper name=t9 x=17 y=19 orientation=90
triangleBumper name=t10 x=19 y=19 orientation=0
squareBumper name=s1 x=2 y=19
squareBumper name=s2 x=4 y=19
squareBumper name=s3 x=6 y=19
squareBumper name=s4 x=8 y=19
squareBumper name=s5 x=10 y=19
squareBumper name=s6 x=12 y=19
squareBumper name=s7 x=14 y=19
squareBumper name=s8 x=16 y=19
squareBumper name=s9 x=18 y=19

portal name=a x=0 y=19 otherPortal=b
portal name=b x=10 y=12 otherPortal=c
spawner name=sp1 x=5 y=7
spawner name=sp2 x=15 y=7
absorber name=abs x=7 y=0 width=7 heigth=1
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
fire trigger=abs action=abs

