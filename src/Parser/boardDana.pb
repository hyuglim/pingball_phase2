board name=Dana gravity=0 friction1=0 friction2=0
#ball name=Ball1 x=10 y=5 xVelocity=0 yVelocity=8 radius=.25
ball name=Ball2 x=11 y=19 xVelocity=0 yVelocity=-8 radius=0.25
#ball name=Ball3 x=4 y=10 xVelocity=20 yVelocity=0 radius=0.25
#ball name=Ball4 x=3 y=17 xVelocity=6 yVelocity=-6 radius=0.25

leftFlipper name=lf x=10 y=10 orientation=0
fire trigger =ls action=lf
#spawner name=sp x=18 y=9
keyup key=ctrl action=lf

