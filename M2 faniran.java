//// Midterm code for 59CST112

/**************************************************************
BE SURE TO CHANGE THE NEXT SEVERAL LINES, USING YOUR REAL NAME.

    MY FIRST NAME IS......Richard
    MY MIDDLE NAME IS.....chris
    MY LAST NAME IS.......Faniran
    
PICK 3 WORDS THAT START WITH YOUR INITIALS, using lower case letters:

    first word............rabbit
    second word...........corn
    third word............fox
    !
USE THESE 3 WORDS TO NAME ALL VARIABLES FOR 3 POOL BALLS.
(You may abbreviate any words, but start with same letter.)
***************************************************************/


//// GLOBALS FOR 3 colored balls ////
//// (Assume ball diameter is 30.) ////
float rabX,  rabY,  rabDX,  rabDY;          //++++ MODIFY THIS.  Don' use "wolf". ++++
float cornX,  cornY,  cornDX,  cornDY;              //++++ MODIFY THIS.  Don' use "ham". ++++
float foxX,  foxY,  foxDX,  foxDY;      //++++ MODIFY THIS.  Don' use "hippo". ++++
//float mc;
float feet= 0;
            //++++ MODIFY THESE DECLARATIONS -- Do not use "wolf" or "ham" or "hippo" ++++


//// OTHER GLOBALS:  strings, pool table, etc ////

String title=  "CST112 MIDTERM EXAM";
String news=   "Click any ball to reset it to right half of table.  (r resets all.)";
String author=  "richard";
String number1 = "1";
String number2 = "2";
String number3 = "3";

float left=50, right=450, top=100, bottom=250;        // Table boundaries
float middle=250;
boolean wall=true;
int tableRed=150, tableGreen=250, tableBlue=150;      // green pool table
int score=0,m=0,k=0;

    // ADD YOUR OWN DECLARATIONS HERE. ++++


//// SETUP:  size and table
void setup() {
    size( 640, 480 );          //++++ CHANGE THE SIZE -- see instructions! ++++
    
    // Table boundaries
    left=50;
    right=590;
    top=200;
    bottom=400;
    middle=340;
  
  
  reset();
    //// MODIFY THIS CODE TO MAKE TABLE CENTERED IN WINDOW.  ++++

 }
void reset(){
  rabX = (right-20); rabY=random(top-20,bottom+50);       //position
  cornX= right-10; cornY=random(top-20, bottom+50);
  foxX= right-30; foxY= random(top-20, bottom+20);
  rabDX =random(1,4); rabDY= random(1,4);   /// movement /speed
  cornDX =random(1,4); cornDY= random(1,4);
  foxDX= random(1,4); foxDX= random(1,4);
 
  
}
//// NEXT FRAME:  table, bounce off walls, collisions, show all
void draw() {
  background( 250,250,200 );
  rectMode( CORNERS);
  table( left, top, right, bottom );
  buttons();
  bounce();
  collisions();
  show();
  messages();
  mouse();
  

}



//// HANDLERS:  keys, click

void keyPressed() {
  if (key == 'q') { exit();  }
    //++++ ADD YOUR OWN CODE HERE. ++++
   if (key == 'w') { wall= false; middle=0;}   //removes wall
   if (key == 'x') { wall= true; middle=340;}  // returns wall
   if (key == 'r') { reset();}
  if (keyPressed && key =='m') {                 //moves mouse
      mc +=5;
      if(mc>width) mc=0;
    
    if( frameCount/10 % 2 > 0) feet= -5;
   else feet=5;
      
  }
    
 
}




//// SCENE:  draw the table with wall down the middle
void table( float east, float north, float west, float south ) {
  fill( tableRed, tableGreen, tableBlue );    // pool table
  strokeWeight(20);
  stroke( 127, 0, 0 );      // Brown walls
  rect( east-20, north-20, west+20, south+20 );

            //++++ MODIFY THIS CODE, as necessary. ++++

  // Start with a WALL down the middle of the table! 
  if (wall) {
    float middle=  (east+west)/2;    
    stroke( 0, 127, 0 );
    line( middle,north+10, middle,south-10 );
  }
  stroke(0);
  strokeWeight(1);
}

//// ACTION:  bounce off walls, collisions
void bounce() {
    //// ADD YOUR OWN CODE HERE. ++++
    rabX += rabDX; if( rabX<left || rabX> right) rabDX *= -1;
    rabY += rabDY; if( rabY<top || rabY>bottom) rabDY *= -1;
    cornX += cornDX; if( cornX<left || cornX>right) cornDX *= -1;
    cornY += cornDY; if( cornY<top || cornY>bottom) cornDY *= -1;
    foxX += foxDX; if( foxX<left || foxX> right) foxDX *= -1;
    foxY += foxDY; if( foxY<top || foxY>bottom) foxDY *= -1;
    rabX += rabDX; if( rabX<middle) rabDX *=-1;    // collision with middle wall
    cornX += cornDX; if(cornX<middle) cornDX *=-1;
    foxX += foxDX; if(foxX<middle) foxDX *=-1;
}
void collisions() {
    //// ADD YOUR OWN CODE HERE. ++++
     float tmp;
     
     if (dist(rabX,rabY,cornX,cornY) <20){
       tmp=rabDX; rabDX=cornDX; cornDX=tmp;
       tmp=rabDY; rabDY=cornDY; cornDY=tmp;
     }
     if (dist(rabX,rabY, foxX, foxY) <20){
       tmp=rabDX; rabDX=foxDX; foxDX=tmp;
       tmp=rabDY; rabDY=foxDY; foxDY=tmp;
     }
     if (dist( cornX, cornY,foxX,foxY)<20){
       tmp=cornDX; cornDX=foxDX; foxDX=tmp;
       tmp=cornDY; cornDY=foxDY; foxDY=tmp;
     }
     
    
}


//// SHOW:  balls, messages, etc.
void show() {
   fill(255,255,255); ellipse(rabX, rabY,20,20); //// ball 1
   fill(50,70,255); ellipse( cornX, cornY,20,20); //ball 2
   fill( 70, 60,200); ellipse( foxX,foxY, 20,20); //ball 3
}
void buttons() {
    //++++ ADD YOUR OWN CODE HERE. ++++
}
void messages() {
  fill(0);
  text( title, width/3, 15 );
  text( news, width/9, 30 );
  text( author, 10, height-5 );
 text( number1, rabX-2, rabY+3); // numbers on balls
text(number2, cornX-2, cornY+3);
text(number3, foxX-2, foxY+3);
  // Also, display the number of collisions.
}
    //++++ ADD YOUR OWN CODE HERE. ++++

  void mouse(){
   fill(155,67,16); ellipse(mc+45,height-30,40,20); ///body
    fill(155,67,16); triangle(mc+50,height-40,mc+80, height-39,mc+50,height-50);///head
    fill(237,120,135); ellipse(mc+51,height-50,9,9);    //ears
   fill(237,120,135); ellipse(mc+56,height-50,9,9);     ///ears
   fill(0); ellipse(mc+56,height-43,3,3);       ///eyes
   fill(0); ellipse(mc+80,height-39,5,5); ////nose
   stroke(0);
   strokeWeight(2);
   //
  
   fill(0); line( mc+40+feet,height-10, mc+40, height-20); // back front legs
   fill(0); line( mc+35+feet,height-10, mc+35, height-20); ///back legs
   fill(0); line( mc+50+feet,height-10, mc+50, height-20); // front back legs
   fill(0); line( mc+55+feet,height-10, mc+55, height-20);  // front legs
   
    }
    
  
    int mc= 0;
