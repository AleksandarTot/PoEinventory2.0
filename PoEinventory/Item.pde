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
  void drawItem ()
  {
    stroke(strR,strG,strB);
    fill(r,g,b);
    rect(x,y,z1,z2);
  }
  void pickUp()
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
  void drop()
  {
    if (mouseX>inix && mouseY>iniy && mouseX<inix+invx*100 && mouseY<iniy+invy*100){
    if (int((mouseX-inix+z1)/100)<=invx && int((mouseY-iniy+z2)/100)<=invy && test(int((mouseY-iniy)/100),int((mouseX-inix)/100),z1,z2)==true && checkInventory==id)
      {
        slot(int((mouseY-iniy)/100),int((mouseX-inix)/100),z1,z2);
        inv1=int((mouseY-iniy)/100);
        inv2=int((mouseX-inix)/100);
        x=inix+100*(int((mouseX-inix)/100));
        y=iniy+100*(int((mouseY-iniy)/100));
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
void keyReleased() {
  klick = false;
}
