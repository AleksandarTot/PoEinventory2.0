import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class PoEinventory extends PApplet {

int checkInventory;
int checkItem;
float inix;
float iniy;
int invy;
int invx;
Grid grid;
Item potion, sword, bow, shield;
int[] [] inventory;

public void setup()
{
  
  checkItem = 0;
  checkInventory = 0;
  grid = new Grid (275,100,4,3);
  potion = new Item (25,25,100,100,0,255,0,1);
  sword = new Item (25,150,100,200,255,0,0,2);
  bow = new Item (150,37,100,300,255,255,0,3);
  shield = new Item (37,375,200,200,0,0,255,4);
}

public void draw()
{
  background(0);
  grid.drawGrid();
  shield.drawItem();
  bow.drawItem();
  sword.drawItem();
  potion.drawItem();
  potion.pickUp();
  sword.pickUp();
  bow.pickUp();
  shield.pickUp();
}

public void mouseReleased()
{
  switch (checkInventory)
  {
    case 1:
      potion.drop();
    case 2:
      sword.drop(); 
    case 3:
      bow.drop();
    case 4:
      shield.drop();
  }  
}
public void deslot(int i, int j, float z1, float z2)
{
    inventory[i][j]=0;
    if(z1>100 && z2>100)
    {
      inventory[i][j+1]=0;
      inventory[i+1][j]=0;
      inventory[i+1][j+1]=0;
    }
    else if(z1>200)
    {
      inventory[i][j+1]=0;
      inventory[i][j+2]=0;
    }
    else if(z2>200)
    {
      inventory[i+1][j]=0;
      inventory[i+2][j]=0;
    }
    else if(z1>100)
    {
      inventory[i][j+1]=0;
    }
    else if(z2>100)
    {
      inventory[i+1][j]=0;
    }
}
class Grid
{
  Grid (float _inix, float _iniy, int _invy, int _invx)
  {
    inix=_inix;
    iniy=_iniy;
    invy=_invy;
    invx=_invx;
    inventory = new int [invy][invx];
  }
  public void drawGrid ()
  {
    for (float i = inix; i <= (inix+invx*100); i= i+100)
    {
      stroke(255);
      strokeWeight(2);
      line(i,iniy,i,iniy+invy*100);
    }
    for (float i = iniy; i <= (iniy+invy*100); i= i+100)
    {
      stroke(255);
      strokeWeight(2);
      line(inix,i,inix+invx*100,i);
    }
  }
}
boolean klick;
class Item
{
  float x;
  float y;
  float z1;
  float z2;
  float startx;
  float starty;
  float r;
  float g;
  float b;
  float strR;
  float strG;
  float strB;
  float a;
  int id;
  int inv1;
  int inv2;
  boolean off;
  int flip;
  Item (float _x,float _y, float _z1, float _z2, float _r, float _g, float _b, int _id)
  {
    x=_x;
    y=_y;
    z1=_z1;
    z2=_z2;
    r=_r;
    g=_g;
    b=_b;
    strR=_r;
    strG=_g;
    strB=_b;
    id=_id;
    startx=_x;
    starty=_y;
    flip=69;
  }
  public void drawItem ()
  {
    stroke(strR,strG,strB);
    fill(r,g,b);
    rect(x,y,z1,z2);
  }
  public void pickUp()
  {
    if (mouseX>=x && mouseX<=x+z1 && mouseY>=y && mouseY<=y+z2 && mousePressed==true && (checkItem==0 || checkItem==id))
    {
      if (off==true)
      {
        strR=r;
        strG=g;
        strB=b;
        deslot(inv1,inv2,z1,z2);
        off=false;
      }
      if (keyPressed && klick==false)
      {
        a=z1;
        z1=z2;
        z2=a;
        klick=true;
        flip=flip*(-1);
      }
      x=mouseX-25;
      y=mouseY-25;
      checkItem=id;
      checkInventory=id;
    }
    else if (mousePressed==false)
    {
      checkItem=0;
    }
  }
  public void drop()
  {
    if (mouseX>inix && mouseY>iniy && mouseX<inix+invx*100 && mouseY<iniy+invy*100){
    if (PApplet.parseInt((mouseX-inix+z1)/100)<=invx && PApplet.parseInt((mouseY-iniy+z2)/100)<=invy && test(PApplet.parseInt((mouseY-iniy)/100),PApplet.parseInt((mouseX-inix)/100),z1,z2)==true && checkInventory==id)
      {
        slot(PApplet.parseInt((mouseY-iniy)/100),PApplet.parseInt((mouseX-inix)/100),z1,z2);
        inv1=PApplet.parseInt((mouseY-iniy)/100);
        inv2=PApplet.parseInt((mouseX-inix)/100);
        x=inix+100*(PApplet.parseInt((mouseX-inix)/100));
        y=iniy+100*(PApplet.parseInt((mouseY-iniy)/100));
        strR=255;
        strG=0;
        strB=255;
        off=true;
        checkInventory=0;
      } 
      else if (off==false && checkInventory==id)
      {
        if (flip<0)
        {
        a=z1;
        z1=z2;
        z2=a;
        flip=flip*(-1);
        }
        x=startx;
        y=starty;
      }
    }
  }
}
public void keyReleased() {
  klick = false;
}
public void slot(int i, int j, float z1, float z2)
{
    inventory[i][j]=1;
    if(z1>100 && z2>100)
    {
      inventory[i][j+1]=1;
      inventory[i+1][j]=1;
      inventory[i+1][j+1]=1;
    }
    else if(z1>200)
    {
      inventory[i][j+1]=1;
      inventory[i][j+2]=1;
    }
    else if(z2>200)
    {
      inventory[i+1][j]=1;
      inventory[i+2][j]=1;
    }
    else if(z1>100)
    {
      inventory[i][j+1]=1;
    }
    else if(z2>100)
    {
      inventory[i+1][j]=1;
    }
}
public boolean test (int i,int j,float z1,float z2)
{
  if (inventory[i][j]==1)
  {
    return false;
  }
  else if (z1>100 && z2>100 && inventory[i+1][j+1]==1)
  {
    return false;
  }
  else if (z1>200 && inventory[i][j+2]==1)
  {
    return false;
  }
  else if (z2>200 && inventory[i+2][j]==1)
  {
    return false;
  }
  else if (z1>100 && inventory[i][j+1]==1)
  {
    return false;
  }
  else if (z2>100 && inventory[i+1][j]==1)
  {
    return false;
  }
  else return true;
}
  public void settings() {  size(600,600); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "PoEinventory" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
